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

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import woodnetwork.hebg3.com.woodnetwork.QiuGou.adapter.QiuGouHomeAdapter;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.bean.DemandList;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.bean.DemandList_listItem;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.contract.QiuGouHomeContract;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.presenter.QiuGouHomePresenter;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_DemandList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_busnessList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_demandBuyerList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_demandInfo;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter.BusnessListAdapter;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment.DividerItemDecoration;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;


public class QiuGouHomeFragment extends Fragment implements QiuGouHomeContract.QiuGouHomeViewInterface {


    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.fragment_qiu_gou_recyclerview)
    XRecyclerView recyclerView;
    private QiuGouHomeContract.QiuGouHomePresenterInterface presenter;
    private int page_no = 1;
    private Request_DemandList request_demandList;
    private MyRequestInfo myRequestInfo;
    private QiuGouHomeAdapter qiuGouHomeAdapter;
    private List<DemandList_listItem> list;

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


        request_demandList = new Request_DemandList();
        request_demandList.page_no = 1;
        request_demandList.page_size = 10;
        myRequestInfo = new MyRequestInfo();
        myRequestInfo.req = request_demandList;
        myRequestInfo.req_meta = request_getAttribute;
        presenter.getQiuGouData(myRequestInfo, 0);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showQiuGouInfo(final DemandList demandList) {
        qiuGouHomeAdapter = new QiuGouHomeAdapter(getActivity(), demandList.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page_no = 1;
                demandList.page_no = page_no;
                myRequestInfo.req = request_demandList;
                presenter.getQiuGouData(myRequestInfo, 1);
            }

            @Override
            public void onLoadMore() {
                page_no++;
                if (page_no >= demandList.total_page) {//判断是否为最后一页
                    recyclerView.setIsnomore(true);//底部显示没有更多数据
                }
                request_demandList.page_no = page_no;
                myRequestInfo.req = request_demandList;
                presenter.getQiuGouData(myRequestInfo, 2);


            }
        });
        if (1 == demandList.total_page) {//如果总页数一共就一页，关闭加载更多功能
            recyclerView.setLoadingMoreEnabled(false);
        }
        recyclerView.setAdapter(qiuGouHomeAdapter);
    }

    @Override
    public void loadMore(List<DemandList_listItem> list) {

        recyclerView.loadMoreComplete();
        list = qiuGouHomeAdapter.getDemandList();
        list.addAll(list);
        qiuGouHomeAdapter.notifyDataSetChanged();

    }

    @Override
    public void refresh(DemandList demandList) {
        recyclerView.refreshComplete();
        if (1 < demandList.total_page) {//如果刷新后数据多余一页，加载更多功能启用
            recyclerView.setLoadingMoreEnabled(true);
        }
        list = demandList.list;
        qiuGouHomeAdapter.setDemandList(list);
        qiuGouHomeAdapter.notifyDataSetChanged();

    }

    @Override
    public void setPresenter(QiuGouHomeContract.QiuGouHomePresenterInterface presenter) {
        if (null != presenter) {
            this.presenter = presenter;
        }
    }

    @Override
    public void showProgress() {
        ProgressUtils.show(getActivity(), getResources().getString(R.string.qingshaohou));
    }

    @Override
    public void closeProgress() {
        ProgressUtils.hide();
    }

    @Override
    public void showMessage(String string) {
        CommonUtils.showToast(getActivity(), string);
    }
}
