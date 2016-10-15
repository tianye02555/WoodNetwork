package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_busnessList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter.BusnessListAdapter;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductFilterList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductFilterList_productsItem;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract.BusnessListContrac;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.presenter.BusnessListPresenter;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;

public class BusnessListFragment extends Fragment implements BusnessListContrac.BusnessListViewInterface ,View.OnTouchListener{

    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_busness_list_recyclerview)
    XRecyclerView recyclerview;
    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    private int page_no = 1;
    private Request_busnessList request_busnessList;
    private MyRequestInfo myRequestInfo;
    private BusnessListAdapter adapter;
    private List<BusnessInfo> list;

    private BusnessListContrac.BusnessListPresenterInterface presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_busness_list, container, false);
        view.setOnTouchListener(this);
        ButterKnife.bind(this, view);
        imgeTitleLeft.setVisibility(View.GONE);
        imageTitleRight.setVisibility(View.GONE);
        textTitle.setText("商家信息");
        new BusnessListPresenter(this);
        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(getActivity());
        Request_getAttribute request_getAttribute = new Request_getAttribute();
        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");

        request_busnessList = new Request_busnessList();
        request_busnessList.page_size = 10;
        request_busnessList.page_no = 1;
        myRequestInfo = new MyRequestInfo();
        myRequestInfo.req_meta = request_getAttribute;
        myRequestInfo.req = request_busnessList;
        presenter.getBusnessListData(myRequestInfo, 0);
        return view;
    }

    @Override
    public void showBusnessListData(final BusnessListInfo busnessListInfo) {
        list = busnessListInfo.seller_list;
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, 5));
        adapter = new BusnessListAdapter(getActivity(), busnessListInfo.seller_list);
        recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page_no = 1;
                request_busnessList.page_no = page_no;
                myRequestInfo.req = request_busnessList;
                presenter.getBusnessListData(myRequestInfo, 1);
            }

            @Override
            public void onLoadMore() {
                page_no++;
                if (page_no >= busnessListInfo.total_page) {//判断是否为最后一页
                    recyclerview.setIsnomore(true);//底部显示没有更多数据
                }
                request_busnessList.page_no = page_no;
                myRequestInfo.req = request_busnessList;
                presenter.getBusnessListData(myRequestInfo,2);


            }
        });
        if (1 == busnessListInfo.total_page) {//如果总页数一共就一页，关闭加载更多功能
            recyclerview.setLoadingMoreEnabled(false);
        }
        recyclerview.setAdapter(adapter);

    }

    @Override
    public void loadMore(List<BusnessInfo> newList) {

        recyclerview.loadMoreComplete();
        list = adapter.getList();
        list.addAll(newList);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void refresh(BusnessListInfo busnessListInfo) {
        recyclerview.refreshComplete();
        if (1 < busnessListInfo.total_page) {//如果刷新后数据多余一页，加载更多功能启用
            recyclerview.setLoadingMoreEnabled(true);
        }
        list = busnessListInfo.seller_list;
        adapter.setList(list);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void setPresenter(BusnessListContrac.BusnessListPresenterInterface presenter) {
        if (null != presenter) {
            this.presenter = presenter;
        }

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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }
}
