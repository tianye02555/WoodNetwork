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
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_shopcarList;
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

    @OnClick({R.id.activity_woyaobaojia_simpleDraweeView_shouhuodi, R.id.activity_woyaobaojia_btn_tijiao, R.id.activity_woyaobaojia_image_dianhua})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_woyaobaojia_simpleDraweeView_shouhuodi:
//                startActivityForResult();
                break;
            case R.id.activity_woyaobaojia_btn_tijiao:
                break;
            case R.id.activity_woyaobaojia_image_dianhua://拨打电话
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + this.phone));
                startActivity(intent);
                break;
        }
    }

    @Override
    public void showWoYaoBaoJiaInfo(DemandInfo demandInfo) {
        maiJia.setText(demandInfo.buyer);
        chanPinYiXiang.setText(demandInfo.pname);
        gouMaiShuLiang.setText(demandInfo.number);
        qiTaXuQiu.setText(demandInfo.remarks);
        faBuYu.setText(demandInfo.create_time);
        jiaoHuoDiDian.setText(demandInfo.receive_area);
        lianXiFangShi.setText(demandInfo.phone);
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
        ProgressUtils.show(this, getResources().getString(R.string.chaxunyue));
    }

    @Override
    public void closeProgress() {
        ProgressUtils.hide();
    }

    @Override
    public void showfailMessage(String string) {
        CommonUtils.showToast(this, string);
    }
}
