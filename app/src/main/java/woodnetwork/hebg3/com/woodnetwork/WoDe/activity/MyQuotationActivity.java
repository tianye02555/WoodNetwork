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
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_quotationList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment.DividerItemDecoration;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.WoDe.adapter.MyQuotationAdapter;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.QuotationList;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.AdviceContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.MyQuotationContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.presenter.MyQuotationPresenter;

public class MyQuotationActivity extends AppCompatActivity implements MyQuotationContract.MyQuotationViewInterface {

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_MyQuotation_recyclerview)
    RecyclerView recyclerview;
    private MyQuotationContract.MyQuotationPresenterInterface presenter;
    private MyQuotationAdapter adapter;

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


        Request_quotationList request_quotationList=new Request_quotationList();
        request_quotationList.page_no=1;
        request_quotationList.page_size=10;
        MyRequestInfo myRequestInfo = new MyRequestInfo();
        myRequestInfo.req = request_quotationList;
        myRequestInfo.req_meta = request_getAttribute;
        presenter.getMyQuotationData(myRequestInfo);


    }

    @OnClick(R.id.imge_title_left)
    public void onClick() {
        finish();
    }

    @Override
    public void setMyQuotationInfo(QuotationList quotationList) {
        adapter = new MyQuotationAdapter(this, quotationList.quotation);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, 2));
        recyclerview.setAdapter(adapter);
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
