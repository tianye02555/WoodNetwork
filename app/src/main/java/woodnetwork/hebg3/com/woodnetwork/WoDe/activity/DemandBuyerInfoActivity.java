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
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_demandBuyerInfo;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.WoDe.adapter.DemandBuyerInfoAdapter;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.DemandBuyerInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.DmandBuyerInfoContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.presenter.DemandBuyerInfoPresenter;
import woodnetwork.hebg3.com.woodnetwork.view.MyListView;

public class DemandBuyerInfoActivity extends AppCompatActivity implements DmandBuyerInfoContract.DmandBuyerInfoViewInterface {

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_demand_buyer_text_name)
    TextView name;
    @Bind(R.id.activity_demand_buyer_text_number)
    TextView number;
    @Bind(R.id.activity_demand_buyer_text_chanpinleixing)
    TextView chanPinLeiXing;
    @Bind(R.id.activity_demand_buyer_text_fahuodi)
    TextView faHuoDi;
    @Bind(R.id.activity_demand_buyer_text_zhuangtai)
    TextView zhuangTai;
    @Bind(R.id.activity_demand_buyer_text_time)
    TextView time;
    @Bind(R.id.activity_demand_buyer_text_listview)
    MyListView listview;
    private DmandBuyerInfoContract.DmandBuyerInfoPresenterInterface presenter;
    private DemandBuyerInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demand_buyer_info);
        CommonUtils.addActivity(this);
        ButterKnife.bind(this);

        textTitle.setText("我的求购信息");
        imageTitleRight.setVisibility(View.GONE);

        new DemandBuyerInfoPresenter(this);
        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(this);
        Request_getAttribute request_getAttribute = new Request_getAttribute();
        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");


        Request_demandBuyerInfo request_demandBuyerInfo = new Request_demandBuyerInfo();
        request_demandBuyerInfo.did = "10";//getIntent().getStringExtra("did")

        MyRequestInfo myRequestInfo = new MyRequestInfo();
        myRequestInfo.req = request_demandBuyerInfo;
        myRequestInfo.req_meta = request_getAttribute;
        presenter.getDmandBuyerData(myRequestInfo);
    }

    @OnClick(R.id.imge_title_left)
    public void onClick() {
        finish();
    }

    @Override
    public void setDmandBuyerInfo(DemandBuyerInfo dmandBuyerInfo) {
        adapter = new DemandBuyerInfoAdapter(this, dmandBuyerInfo.attribute);
        listview.setAdapter(adapter);
        name.setText("商品名称：" + dmandBuyerInfo.pname);
        number.setText("求购数量：" + String.valueOf(dmandBuyerInfo.number));
        if (0 == dmandBuyerInfo.ptype) {
            chanPinLeiXing.setText("产品类型：期货");
        } else if (1 == dmandBuyerInfo.ptype) {
            chanPinLeiXing.setText("产品类型：现货");
        }

        if (0 == dmandBuyerInfo.status) {// 0表示未发布，1表示已发布，2表示已完成
            zhuangTai.setText("状       态：未发布");
        } else if (1 == dmandBuyerInfo.status) {
            zhuangTai.setText("状       态：已发布");
        }
        if (2 == dmandBuyerInfo.status) {
            zhuangTai.setText("状       态：已完成");
        }

        faHuoDi.setText( dmandBuyerInfo.receive_area);
        time.setText("创建时间：" + dmandBuyerInfo.create_time);

    }

    @Override
    public void setPresenter(DmandBuyerInfoContract.DmandBuyerInfoPresenterInterface presenter) {
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
