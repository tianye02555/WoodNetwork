package woodnetwork.hebg3.com.woodnetwork.QiuGou.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.bean.DemandInfo;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.contract.WoYaoBaoJiaContract;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.presenter.WoYaoBaoJiaPresenter;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_demandInfo;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_quotation_add;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_shopcarList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity.ChooseAddressActivity;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;

public class WoYaoBaoJiaActivity extends AppCompatActivity implements WoYaoBaoJiaContract.WoYaoBaoJiaViewInterface {

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_woyaobaojia_text_chanpinyixiang)
    TextView chanPinYiXiang;
    @Bind(R.id.activity_woyaobaojia_text_goumaishuliang)
    TextView gouMaiShuLiang;
    @Bind(R.id.activity_woyaobaojia_text_qitaxuqiu)
    TextView qiTaXuQiu;
    @Bind(R.id.activity_woyaobaojia_text_fabuyu)
    TextView faBuYu;
    @Bind(R.id.activity_woyaobaojia_text_jiaohuodidian)
    TextView jiaoHuoDiDian;
    @Bind(R.id.activity_woyaobaojia_text_lianxifangshi)
    TextView lianXiFangShi;
    @Bind(R.id.activity_woyaobaojia_edit_nindebaojia)
    EditText editNinDeBaoJia;
    @Bind(R.id.activity_woyaobaojia_text_maijia)
    TextView maiJia;
    @Bind(R.id.activity_woyaobaojia_edit_shouhuodi)
    EditText editShouHuoDi;
    @Bind(R.id.activity_woyaobaojia_edit_xiangxidizhi)
    EditText editXiangXiDiZhi;
    @Bind(R.id.activity_woyaobaojia_edit_beizhu)
    EditText editBeiZhu;
    @Bind(R.id.activity_woyaobaojia_simpleDraweeView_shouhuodi)
    SimpleDraweeView imageShouHuoDi;
    @Bind(R.id.activity_woyaobaojia_btn_tijiao)
    Button BtnTiJiao;
    @Bind(R.id.activity_woyaobaojia_image_dianhua)
    SimpleDraweeView imageDianHua;
    private WoYaoBaoJiaContract.WoYaoBaoJiaPresenterInterface presenter;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_yao_bao_jia);
        ButterKnife.bind(this);

        textTitle.setText("我要报价");
        imageTitleRight.setVisibility(View.GONE);

        editShouHuoDi.setFocusable(false);
        editShouHuoDi.setEnabled(false);

        new WoYaoBaoJiaPresenter(this);
        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(this);
        Request_getAttribute request_getAttribute = new Request_getAttribute();
        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");


        Request_demandInfo request_demandInfo = new Request_demandInfo();
//        request_demandInfo.did=(String)getIntent().getStringExtra("did");//正确代码，下为测试
        request_demandInfo.did = "10";

        MyRequestInfo myRequestInfo = new MyRequestInfo();
        myRequestInfo.req = request_demandInfo;
        myRequestInfo.req_meta = request_getAttribute;
        this.presenter.getWoYaoBaoJiaData(myRequestInfo);
    }

    @OnClick({R.id.imge_title_left,R.id.activity_woyaobaojia_simpleDraweeView_shouhuodi, R.id.activity_woyaobaojia_btn_tijiao, R.id.activity_woyaobaojia_image_dianhua})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_woyaobaojia_simpleDraweeView_shouhuodi:
                startActivityForResult(new Intent(this, ChooseAddressActivity.class),0);
                break;
            case R.id.activity_woyaobaojia_btn_tijiao:
                SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(this);
                Request_getAttribute request_getAttribute = new Request_getAttribute();
                request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");


               Request_quotation_add request_quotation_add = new Request_quotation_add();
                request_quotation_add.delivery_address ="新中路228号";
                request_quotation_add.delivery_area="1234";
                request_quotation_add.demand_id="1234";
                request_quotation_add.price=250.00;
                request_quotation_add.remarks="无";

                MyRequestInfo myRequestInfo = new MyRequestInfo();
                myRequestInfo.req = request_quotation_add;
                myRequestInfo.req_meta = request_getAttribute;

                presenter.saveWoDeBaoJia(myRequestInfo);
                break;
            case R.id.activity_woyaobaojia_image_dianhua://拨打电话
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + this.phone));
                startActivity(intent);
                break;
            case R.id.imge_title_left:
                finish();
                break;
        }
    }

    @Override
    public void showWoYaoBaoJiaInfo(DemandInfo demandInfo) {
        maiJia.setText("买        家："+demandInfo.buyer);
        chanPinYiXiang.setText("意向产品："+demandInfo.pname);
        gouMaiShuLiang.setText("购买数量："+String.valueOf(demandInfo.number)+"方");
        qiTaXuQiu.setText("其他需求："+demandInfo.remarks);
        faBuYu.setText("发  布  于："+demandInfo.create_time);
        jiaoHuoDiDian.setText("交货地点："+demandInfo.receive_area);
        lianXiFangShi.setText("联系方式："+demandInfo.phone);
        this.phone = demandInfo.phone;

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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            editShouHuoDi.setText(data.getStringExtra("address"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
