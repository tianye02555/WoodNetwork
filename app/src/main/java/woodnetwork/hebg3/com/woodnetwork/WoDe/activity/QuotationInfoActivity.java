package woodnetwork.hebg3.com.woodnetwork.WoDe.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_quotationInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.WoDe.adapter.QuotationAdapter;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.QuotationInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.AdviceContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.QuotationInfoContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.presenter.QuotationPresenter;
import woodnetwork.hebg3.com.woodnetwork.view.MyListView;

/**
 * 报价详情页
 */
public class QuotationInfoActivity extends AppCompatActivity implements QuotationInfoContract.QuotationViewInterface {

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_quotation_info_txt_name)
    TextView name;
    @Bind(R.id.activity_quotation_info_txt_number)
    TextView number;
    @Bind(R.id.activity_quotation_info_listview)
    MyListView listview;
    @Bind(R.id.activity_quotation_info_txt_type)
    TextView type;
    @Bind(R.id.activity_quotation_info_txt_state)
    TextView state;
    @Bind(R.id.activity_quotation_info_txt_myprice)
    TextView myPrice;
    @Bind(R.id.activity_quotation_info_txt_recevi)
    TextView recevie;
    private QuotationInfoContract.QuotationPresenterInterface presenter;
    private QuotationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation_info);
        CommonUtils.addActivity(this);
        ButterKnife.bind(this);
        textTitle.setText("报价信息");
        imageTitleRight.setVisibility(View.GONE);

        new QuotationPresenter(this);
        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(this);
        Request_getAttribute request_getAttribute = new Request_getAttribute();
        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");

        Request_quotationInfo request_quotationInfo = new Request_quotationInfo();
        if(null!=getIntent()){
            request_quotationInfo.qid = getIntent().getStringExtra("qid");
        }else{
            showMessage("获取信息失败");
        }


        MyRequestInfo myRequestInfo = new MyRequestInfo();
        myRequestInfo.req = request_quotationInfo;
        myRequestInfo.req_meta = request_getAttribute;
        presenter.getQuotationData(myRequestInfo);
    }

    @OnClick(R.id.imge_title_left)
    public void onClick() {
        finish();
    }

    @Override
    public void setQuotationInfo(QuotationInfo quotationInfo) {

        name.setText("商品名称："+quotationInfo.name);
        number.setText("求购数量："+quotationInfo.number);
        if (0 == quotationInfo.type) {// 0表示期货，1表示现货...
            type.setText("产品类型："+"期货");
        } else {
            type.setText("产品类型："+"现货");
        }
        if (0 == quotationInfo.status) {// 0表示报价中，1表示报价成功，2表示报价失败
            state.setText("状        态："+"报价中");
        } else if (1 == quotationInfo.status) {
            state.setText("状        态："+"报价成功");
        } else if (2 == quotationInfo.status) {
            state.setText("状        态："+"报价失败");
        }
//                createtime.setText(quotationInfo.);
        myPrice.setText("我的报价："+String.valueOf(quotationInfo.price));
        recevie.setText("收货地址："+quotationInfo.receive);
//        beiZhu.setText(quotationInfo.);
        adapter = new QuotationAdapter(this, quotationInfo.attribute);
        listview.setAdapter(adapter);
    }

    @Override
    public void setPresenter(QuotationInfoContract.QuotationPresenterInterface presenter) {
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
