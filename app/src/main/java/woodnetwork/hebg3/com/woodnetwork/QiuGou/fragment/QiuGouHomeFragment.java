package woodnetwork.hebg3.com.woodnetwork.QiuGou.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

import woodnetwork.hebg3.com.woodnetwork.QiuGou.adapter.QiuGouHomeAdapter;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.bean.DemandList;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.contract.QiuGouHomeContract;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.presenter.QiuGouHomePresenter;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_DemandList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_demandBuyerList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_demandInfo;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment.DividerItemDecoration;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;


public class QiuGouHomeFragment extends Fragment implements QiuGouHomeContract.QiuGouHomeViewInterface{


    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.fragment_qiu_gou_recyclerview)
    RecyclerView recyclerView;
    private QiuGouHomeContract.QiuGouHomePresenterInterface presenter;

    public QiuGouHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_qiu_gou_home, container, false);
        ButterKnife.bind(this, view);
        imgeTitleLeft.setVisibility(View.GONE);
        textTitle.setText("木联网");
        imageTitleRight.setVisibility(View.GONE);

        new QiuGouHomePresenter(this);
        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(getActivity());
        Request_getAttribute request_getAttribute = new Request_getAttribute();
        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");


        Request_DemandList request_demandList = new Request_DemandList();
//        request_demandInfo.did=(String)getIntent().getStringExtra("did");//正确代码，下为测试
        request_demandList.page_no = 1;
        request_demandList.page_size=10;
        MyRequestInfo myRequestInfo = new MyRequestInfo();
        myRequestInfo.req = request_demandList;
        myRequestInfo.req_meta = request_getAttribute;
        presenter.getQiuGouData(myRequestInfo);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showQiuGouInfo(DemandList demandList) {
        QiuGouHomeAdapter qiuGouHomeAdapter=new QiuGouHomeAdapter(getActivity(),demandList.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL,5));
        recyclerView.setAdapter(qiuGouHomeAdapter);
    }

    @Override
    public void setPresenter(QiuGouHomeContract.QiuGouHomePresenterInterface presenter) {
    if(null!=presenter){
        this.presenter=presenter;
    }
    }

    @Override
    public void showProgress() {
        ProgressUtils.show(getActivity(),getResources().getString(R.string.qingshaohou));
    }

    @Override
    public void closeProgress() {
        ProgressUtils.hide();
    }

    @Override
    public void showMessage(String string) {
        CommonUtils.showToast(getActivity(),string);
    }
}
