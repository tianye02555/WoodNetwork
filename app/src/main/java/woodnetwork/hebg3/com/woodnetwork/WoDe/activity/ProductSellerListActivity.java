package woodnetwork.hebg3.com.woodnetwork.WoDe.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_productSellerList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment.DividerItemDecoration;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.WoDe.adapter.ProductSellerListAdapter;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.ProductSellerList;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.MyQuotationContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.ProductSellerListContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.presenter.ProductSellerListPrecenter;

public class ProductSellerListActivity extends AppCompatActivity implements ProductSellerListContract.ProductSellerListViewInterface{

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_product_seller_list_recyclerview)
    RecyclerView recyclerview;
    private ProductSellerListContract.ProductSellerListPresenterInterface presenter;
    private ProductSellerListAdapter adapter;
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

        Request_productSellerList request_productSellerList=new Request_productSellerList();
        request_productSellerList.page_no=1;
        request_productSellerList.page_size=10;
        MyRequestInfo myRequestInfo = new MyRequestInfo();
        myRequestInfo.req = request_productSellerList;
        myRequestInfo.req_meta = request_getAttribute;
        presenter.getProductSellerListData(myRequestInfo);
    }

    @OnClick(R.id.imge_title_left)
    public void onClick() {
        finish();
    }

    @Override
    public void showProductSellerListInfo(ProductSellerList productSellerList) {
        adapter=new ProductSellerListAdapter(this,productSellerList.products);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, 2));
        recyclerview.setAdapter(adapter);
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
