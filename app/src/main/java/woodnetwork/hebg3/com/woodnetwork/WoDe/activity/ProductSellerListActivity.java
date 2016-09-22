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
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_productSellerList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter.BusnessListAdapter;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment.DividerItemDecoration;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.WoDe.adapter.ProductSellerListAdapter;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.ProductSellerList;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.ProductSellerList_productsItem;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.MyQuotationContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.ProductSellerListContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.presenter.ProductSellerListPrecenter;

public class ProductSellerListActivity extends AppCompatActivity implements ProductSellerListContract.ProductSellerListViewInterface {

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_product_seller_list_recyclerview)
    XRecyclerView recyclerview;
    private ProductSellerListContract.ProductSellerListPresenterInterface presenter;
    private ProductSellerListAdapter adapter;
    private int page_no = 1;
    private Request_productSellerList request_productSellerList;
    private MyRequestInfo myRequestInfo;
    private List<ProductSellerList_productsItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_seller_list);
        CommonUtils.addActivity(this);
        ButterKnife.bind(this);

        textTitle.setText("我的商品");
        imageTitleRight.setVisibility(View.GONE);

        new ProductSellerListPrecenter(this);
        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(this);
        Request_getAttribute request_getAttribute = new Request_getAttribute();
        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");

        request_productSellerList = new Request_productSellerList();
        request_productSellerList.page_no = 1;
        request_productSellerList.page_size = 10;
        myRequestInfo = new MyRequestInfo();
        myRequestInfo.req = request_productSellerList;
        myRequestInfo.req_meta = request_getAttribute;
        presenter.getProductSellerListData(myRequestInfo, 0);
    }

    @OnClick(R.id.imge_title_left)
    public void onClick() {
        finish();
    }

    @Override
    public void showProductSellerListInfo(final ProductSellerList productSellerList) {
        adapter = new ProductSellerListAdapter(this, productSellerList.products);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page_no = 1;
                request_productSellerList.page_no = page_no;
                myRequestInfo.req = request_productSellerList;
                presenter.getProductSellerListData(myRequestInfo, 1);
            }

            @Override
            public void onLoadMore() {
                page_no++;
                if (page_no >= productSellerList.total_page) {//判断是否为最后一页
                    recyclerview.setIsnomore(true);//底部显示没有更多数据
                }
                request_productSellerList.page_no = page_no;
                myRequestInfo.req = request_productSellerList;
                presenter.getProductSellerListData(myRequestInfo, 2);


            }
        });
        if (1 == productSellerList.total_page) {//如果总页数一共就一页，关闭加载更多功能
            recyclerview.setLoadingMoreEnabled(false);
        }
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void loadMore(List<ProductSellerList_productsItem> list) {

        recyclerview.loadMoreComplete();
        list = adapter.getProductInfoList();
        list.addAll(list);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void refresh(ProductSellerList productSellerList) {
        recyclerview.refreshComplete();
        if (1 < productSellerList.total_page) {//如果刷新后数据多余一页，加载更多功能启用
            recyclerview.setLoadingMoreEnabled(true);
        }
        list = productSellerList.products;
        adapter.setProductInfoList(list);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void setPresenter(ProductSellerListContract.ProductSellerListPresenterInterface presenter) {
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
