package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_shoppingMall_woodsList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_spinnerInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity.BusnessInfoActivity;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity.BusnessListActivity;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter.ShoppingMalAdapter;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter.ShoppingMallSpinnerAdapter;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductFilterList_productsItem;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.WoodFilterInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract.ShoppingMallContract;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.presenter.ShoppingMallPresenter;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;


public class ShoppingMallFragment extends Fragment implements ShoppingMallContract.ShoppingMallView {

    @Bind(R.id.fragment_shopping_mall_spinner_dengji)
    Spinner spinner_rades;
    @Bind(R.id.fragment_shopping_mall_spinner_changdi)
    Spinner spinner_area;
    @Bind(R.id.fragment_shopping_mall_spinner_shuzhong)
    Spinner spinner_kind;
    @Bind(R.id.fragment_shopping_mall_spinnerlayout)
    LinearLayout fragmentShoppingMallSpinnerlayout;
    @Bind(R.id.fragment_shopping_mall_recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.fragment_shopping_mall_text_more)
    TextView moreBusness;
    @Bind(R.id.fragment_shopping_mall_image_1)
    SimpleDraweeView busnessOne;
    @Bind(R.id.fragment_shopping_mall_image_2)
    SimpleDraweeView busnessTw;
    @Bind(R.id.fragment_shopping_mall_image_3)
    SimpleDraweeView busnessTh;
    @Bind(R.id.fragment_shopping_mall_image_4)
    SimpleDraweeView busnessOneFo;
    @Bind(R.id.line1)
    TextView line1;

    private ShoppingMalAdapter shoppingMalAdapter;
    private ShoppingMallContract.ShoppingMallPresenter shoppingMallPresenter;
    private String rades_id;
    private String area_id;
    private String kind_id;
    private List<BusnessInfo> list;
    private List<Request_spinnerInfo> request_spinnerInfoList;
    private Request_shoppingMall_woodsList request_shoppingMall_woodsList;

    public ShoppingMallFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_mall, container, false);
        ButterKnife.bind(this, view);
//        ArrayList<String> list = new ArrayList<String>();
//        list.add("1");
//        list.add("2");
//        list.add("2");
//        list.add("2");
//        shoppingMalAdapter = new ShoppingMalAdapter(getActivity(), list);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerview.setAdapter(shoppingMalAdapter);

        request_spinnerInfoList=new ArrayList<Request_spinnerInfo>();

        request_shoppingMall_woodsList=new Request_shoppingMall_woodsList();
        request_shoppingMall_woodsList.page_no=1;
        request_shoppingMall_woodsList.page_size=10;
        request_shoppingMall_woodsList.attribute=request_spinnerInfoList;


        new ShoppingMallPresenter(this);

        return view;
    }

    @Override
    public void jumpActivity(Class mClass) {
        startActivity(new Intent(getActivity(), mClass));
    }

    @Override
    public void showSpinnerData(final List<WoodFilterInfo> list) {

        ShoppingMallSpinnerAdapter radesAdapter = new ShoppingMallSpinnerAdapter(getActivity(), list.get(0).value);
        spinner_rades.setAdapter(radesAdapter);
        spinner_rades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                rades_id = list.get(0).value.get(i).value_id;
                Request_spinnerInfo request_spinnerInfo=new Request_spinnerInfo();
                request_spinnerInfo.attr_id=list.get(0).attr_id;
                request_spinnerInfo.attr_vaule_id=rades_id;
                request_spinnerInfoList.add(request_spinnerInfo);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ShoppingMallSpinnerAdapter areaAdapter = new ShoppingMallSpinnerAdapter(getActivity(), list.get(1).value);
        spinner_area.setAdapter(areaAdapter);
        spinner_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                rades_id = list.get(1).value.get(i).value_id;
                Request_spinnerInfo request_spinnerInfo=new Request_spinnerInfo();
                request_spinnerInfo.attr_id=list.get(1).attr_id;
                request_spinnerInfo.attr_vaule_id=rades_id;
                request_spinnerInfoList.add(request_spinnerInfo);

                shoppingMallPresenter.getWoodsList(request_shoppingMall_woodsList);
                request_spinnerInfoList.clear();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        ShoppingMallSpinnerAdapter kindAdapter = new ShoppingMallSpinnerAdapter(getActivity(), list.get(2).value);
//        spinner_kind.setAdapter(kindAdapter);
    }

    @Override
    public void showGoodsData(List <ProductFilterList_productsItem> list) {
        shoppingMalAdapter = new ShoppingMalAdapter(getActivity(), list);
        recyclerview.setAdapter(shoppingMalAdapter);
    }

    @Override
    public void showBusnessInfo(List<BusnessInfo> list) {
        this.list=list;
        busnessOne.setImageURI(Uri.parse(list.get(0).img));
        busnessTw.setImageURI(Uri.parse(list.get(1).img));
//        busnessTh.setImageURI(Uri.parse(list.get(2).img));
//        busnessTh.setImageURI(Uri.parse(list.get(3).img));
    }

    @Override
    public void setPresenter(ShoppingMallContract.ShoppingMallPresenter presenter) {
        if (null == presenter) {
            return;
        }
        this.shoppingMallPresenter = presenter;

    }

    @Override
    public void showProgress() {
        ProgressUtils.show(getActivity(), "请稍后……");
    }

    @Override
    public void closeProgress() {
        ProgressUtils.hide();
    }

    @Override
    public void showfailMessage(String string) {
        CommonUtils.showToast(getActivity(), string);
    }

    @Override
    public void onResume() {
        super.onResume();
        shoppingMallPresenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.fragment_shopping_mall_text_more, R.id.fragment_shopping_mall_image_1, R.id.fragment_shopping_mall_image_2, R.id.fragment_shopping_mall_image_3, R.id.fragment_shopping_mall_image_4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_shopping_mall_text_more:
                jumpActivity(BusnessListActivity.class);
                break;
            case R.id.fragment_shopping_mall_image_1:
                Intent intent0=new Intent(getActivity(),BusnessInfoActivity.class);
                if(null!=list&&0!=list.size()){
                    intent0.putExtra("sid",list.get(0).id);
                }
                startActivity(intent0);
                break;
            case R.id.fragment_shopping_mall_image_2:
                Intent intent1=new Intent(getActivity(),BusnessInfoActivity.class);
                if(null!=list&&0!=list.size()){
                    intent1.putExtra("sid",list.get(1).id);
                }
                startActivity(intent1);
                break;
            case R.id.fragment_shopping_mall_image_3:
                Intent intent2=new Intent(getActivity(),BusnessInfoActivity.class);
                if(null!=list&&0!=list.size()){
                    intent2.putExtra("sid",list.get(2).id);
                }
                startActivity(intent2);
                break;
            case R.id.fragment_shopping_mall_image_4:

                Intent intent3=new Intent(getActivity(),BusnessInfoActivity.class);
                if(null!=list&&0!=list.size()){
                    intent3.putExtra("sid",list.get(3).id);
                }
                startActivity(intent3);
                break;
        }
    }
}
