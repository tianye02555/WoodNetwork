package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_shoppingMall_woodsList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_spinnerInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity.ShoopingCartActivity;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter.ShoppingMalAdapter;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductFilterList_productsItem;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract.ShoppingMallContract;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.presenter.ShoppingMallPresenter;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;


public class ShoppingMallFragment extends Fragment implements ShoppingMallContract.ShoppingMallView {


    @Bind(R.id.fragment_shopping_mall_recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_gouwuche)
    ImageView gouWuChe;
    @Bind(R.id.image_title_shaixuan)
    ImageView shuaiXuan;

    private ShoppingMalAdapter shoppingMalAdapter;
    private ShoppingMallContract.ShoppingMallPresenter shoppingMallPresenter;
    private Request_shoppingMall_woodsList request_shoppingMall_woodsList;
    private List<Request_spinnerInfo> request_spinnerInfoList = new ArrayList<Request_spinnerInfo>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_mall, container, false);
        ButterKnife.bind(this, view);

        textTitle.setText("木联网");

        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, 5));

        Request_spinnerInfo request_spinnerInfo = new Request_spinnerInfo();
        request_spinnerInfo.attr_vaule_id = "111";
        request_spinnerInfo.attr_id = "1234";
        request_spinnerInfoList.add(request_spinnerInfo);
        Request_spinnerInfo request_spinnerInfo1 = new Request_spinnerInfo();
        request_spinnerInfo1.attr_vaule_id = "444";
        request_spinnerInfo1.attr_id = "2234";
        request_spinnerInfoList.add(request_spinnerInfo1);
        request_shoppingMall_woodsList = new Request_shoppingMall_woodsList();
        request_shoppingMall_woodsList.page_no = 1;
        request_shoppingMall_woodsList.page_size = 10;
        request_shoppingMall_woodsList.attribute = request_spinnerInfoList;


        new ShoppingMallPresenter(this);
        shoppingMallPresenter.getWoodsList(request_shoppingMall_woodsList);
        return view;
    }

    @Override
    public void jumpActivity(Class mClass) {
        startActivity(new Intent(getActivity(), mClass));
    }


    @Override
    public void showGoodsData(List<ProductFilterList_productsItem> list) {
        shoppingMalAdapter = new ShoppingMalAdapter(getActivity(), list);
        recyclerview.setAdapter(shoppingMalAdapter);
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
    public void showMessage(String string) {
        CommonUtils.showToast(getActivity(), string);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.image_title_gouwuche, R.id.image_title_shaixuan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_title_gouwuche:
                startActivity(new Intent(getActivity(), ShoopingCartActivity.class));
                break;
            case R.id.image_title_shaixuan:

                break;
        }
    }
}
