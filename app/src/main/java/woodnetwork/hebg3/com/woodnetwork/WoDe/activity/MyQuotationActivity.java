package woodnetwork.hebg3.com.woodnetwork.WoDe.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_busnessList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_quotationList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter.BusnessListAdapter;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment.DividerItemDecoration;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.WoDe.adapter.MyQuotationAdapter;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.QuotationList;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.QuotationList_quotationItem;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.AdviceContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.MyQuotationContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.presenter.MyQuotationPresenter;

/**
 * 我的报价
 */
public class MyQuotationActivity extends AppCompatActivity implements MyQuotationContract.MyQuotationViewInterface {

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_MyQuotation_recyclerview)
    XRecyclerView recyclerview;
    private MyQuotationContract.MyQuotationPresenterInterface presenter;
    private MyQuotationAdapter adapter;
    private int page_no = 1;
    private Request_quotationList request_quotationList;
    private MyRequestInfo myRequestInfo;
    private List<QuotationList_quotationItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_quotation);
        CommonUtils.addActivity(this);
        ButterKnife.bind(this);
        textTitle.setText("我的报价");
        imageTitleRight.setVisibility(View.GONE);

        new MyQuotationPresenter(this);

        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(this);
        Request_getAttribute request_getAttribute = new Request_getAttribute();
        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");


        request_quotationList = new Request_quotationList();
        request_quotationList.page_no = 1;
        request_quotationList.page_size = 10;
        myRequestInfo = new MyRequestInfo();
        myRequestInfo.req = request_quotationList;
        myRequestInfo.req_meta = request_getAttribute;
        presenter.getMyQuotationData(myRequestInfo, 0);


    }

    @OnClick(R.id.imge_title_left)
    public void onClick() {
        finish();
    }

    @Override
    public void setMyQuotationInfo(final QuotationList quotationList) {

        adapter = new MyQuotationAdapter(this, quotationList.quotation);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page_no = 1;
                request_quotationList.page_no = page_no;
                myRequestInfo.req = request_quotationList;
                presenter.getMyQuotationData(myRequestInfo, 1);
            }

            @Override
            public void onLoadMore() {
                page_no++;
                if (page_no > quotationList.total_page) {//判断是否为最后一页
                    recyclerview.setIsnomore(true);//底部显示没有更多数据
                }
                request_quotationList.page_no = page_no;
                myRequestInfo.req = request_quotationList;
                presenter.getMyQuotationData(myRequestInfo, 2);


            }
        });
        if (1 == quotationList.total_page) {//如果总页数一共就一页，关闭加载更多功能
            recyclerview.setLoadingMoreEnabled(false);
        }
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void loadMore(List<QuotationList_quotationItem> list_new) {
        if (null != list_new) {
            recyclerview.loadMoreComplete();
            list = adapter.getList();
            list.addAll(list_new);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void refresh(QuotationList quotationList) {
        recyclerview.refreshComplete();
        if (1 < quotationList.total_page) {//如果刷新后数据多余一页，加载更多功能启用
            recyclerview.setLoadingMoreEnabled(true);
        }
        list = quotationList.quotation;
        adapter.setList(list);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void setPresenter(MyQuotationContract.MyQuotationPresenterInterface presenter) {
        if (null != presenter) {
            this.presenter = presenter;
        }
    }

    @Override
    public void showProgress() {
        ProgressUtils.show(this, getResources().getString(R.string.qingshaohou));
    }

    @Override
    public void closeProgress() {
        ProgressUtils.hide();
    }

    @Override
    public void showMessage(String string) {
        CommonUtils.showToast(this, string);
    }
}
