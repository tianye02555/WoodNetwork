package woodnetwork.hebg3.com.woodnetwork.QiuGou.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.adapter.QiuGouXiangQingAdapter;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.bean.DemandInfo;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.contract.WoYaoBaoJiaContract;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.presenter.WoYaoBaoJiaPresenter;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_demandInfo;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.view.MyListView;

public class QiuGouXiangQingActivity extends AppCompatActivity implements WoYaoBaoJiaContract.WoYaoBaoJiaViewInterface {

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_qiu_gou_xiang_qing_txt_creattime)
    TextView txtCreattime;
    @Bind(R.id.activity_qiu_gou_xiang_qing_txt_qiugouzhe)
    TextView txtQiugouzhe;
    @Bind(R.id.activity_qiu_gou_xiang_qing_txt_phone)
    TextView txtPhone;
    @Bind(R.id.activity_qiu_gou_xiang_qing_txt_name)
    TextView txtName;
    @Bind(R.id.activity_qiu_gou_xiang_qing_txt_number)
    TextView txtNumber;
    @Bind(R.id.activity_qiu_gou_xiang_qing_txt_kind)
    TextView txtKind;
    @Bind(R.id.activity_qiu_gou_xiang_qing_txt_jiaohuodi)
    TextView txtJiaohuodi;
    @Bind(R.id.activity_qiu_gou_xiang_qing_txt_beizhu)
    TextView text_beiZhu;
    @Bind(R.id.activity_qiu_gou_xiang_qing_listview)
    MyListView listview;
    @Bind(R.id.scrollView)
    ScrollView scrollView;
    private WoYaoBaoJiaContract.WoYaoBaoJiaPresenterInterface presenter;
    private QiuGouXiangQingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qiu_gou_xiang_qing);
        ButterKnife.bind(this);

        textTitle.setText("求购详情");
        imageTitleRight.setVisibility(View.GONE);
        new WoYaoBaoJiaPresenter(this);
        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(this);
        Request_getAttribute request_getAttribute = new Request_getAttribute();
        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");


        Request_demandInfo request_demandInfo = new Request_demandInfo();
       request_demandInfo.did=(String)getIntent().getStringExtra("did");

        MyRequestInfo myRequestInfo = new MyRequestInfo();
        myRequestInfo.req = request_demandInfo;
        myRequestInfo.req_meta = request_getAttribute;
        this.presenter.getWoYaoBaoJiaData(myRequestInfo);
    }

    @Override
    public void showWoYaoBaoJiaInfo(DemandInfo demandInfo) {
        txtCreattime.setText("创建时间："+demandInfo.create_time);
                txtQiugouzhe.setText("求购者："+demandInfo.buyer);
        txtPhone.setText("联系电话："+demandInfo.phone);
                txtName.setText("商品名称："+demandInfo.pname);
        txtNumber.setText("求购数量："+demandInfo.number);
        if(0==demandInfo.ptype){
            txtKind.setText("产品类型：期货");
        }else if(1==demandInfo.ptype){
            txtKind.setText("产品类型：现货");
        }

        txtJiaohuodi.setText("交货地："+demandInfo.receive_area);
                text_beiZhu.setText("备注："+demandInfo.remarks);
        adapter=new QiuGouXiangQingAdapter(this,demandInfo.attribute);
        listview.setAdapter(adapter);
        scrollView.smoothScrollTo(0,0);
    }


    @Override
    public void setPresenter(WoYaoBaoJiaContract.WoYaoBaoJiaPresenterInterface presenter) {
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

    @OnClick(R.id.imge_title_left)
    public void onClick() {
        finish();
    }
}
