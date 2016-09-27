package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract.BusnessInfoContrac;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.presenter.BusnessInfoPresenter;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.net.Const;

public class BusnessInfoActivity extends AppCompatActivity implements BusnessInfoContrac.BusnessInfoViewInterface {

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_busness_info_image_head)
    SimpleDraweeView image_head;
    @Bind(R.id.activity_busness_info_txt_name)
    TextView text_name;
    @Bind(R.id.activity_busness_info_txt_phone)
    TextView text_phone;
    @Bind(R.id.activity_busness_info_txt_email)
    TextView text_email;
    @Bind(R.id.activity_busness_info_txt_info)
    TextView text_description;
    private String sid;
    private BusnessInfoContrac.BusnessInfoPresenterInterface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busness_info);
        CommonUtils.addActivity(this);
        ButterKnife.bind(this);
        imageTitleRight.setVisibility(View.GONE);
        textTitle.setText("商家信息");
        sid = getIntent().getStringExtra("sid");
        new BusnessInfoPresenter(this);
        if("BusnessInfoListActivity".equals(getIntent().getStringExtra("from"))){
            sid = getIntent().getStringExtra("sid");
            presenter.getBussnessInfo(sid);
        }else if("MyFragment".equals(getIntent().getStringExtra("from"))){
            presenter.getSellerBussnessInfo();
        }

    }

    @Override
    public void back() {
        finish();
    }

    @Override
    public void showBusnessData(BusnessInfo busnessInfo) {
        image_head.setImageURI(Uri.parse(Const.PICTURE+busnessInfo.img));
        text_name.setText("商家名称："+busnessInfo.name);
        text_email.setText("联系邮箱："+busnessInfo.mail);
        text_phone.setText("手机号码："+busnessInfo.phone);
        text_description.setText("商家简介："+busnessInfo.description);
    }

    @Override
    public void showSellerBusnessData(ShopInfo shopInfo) {
        image_head.setImageURI(Uri.parse(shopInfo.img));
        text_name.setText("商家名称："+shopInfo.name);
        text_email.setText("联系邮箱："+shopInfo.mail);
        text_phone.setText("手机号码："+shopInfo.phone);
        text_description.setText("商家简介："+shopInfo.content);
    }

    @Override
    public void setPresenter(BusnessInfoContrac.BusnessInfoPresenterInterface presenter) {
        if(null==presenter){
            return;
        }
        this.presenter=presenter;
    }

    @Override
    public void showProgress() {
        ProgressUtils.show(this,getResources().getString(R.string.qingshaohou));
    }

    @Override
    public void closeProgress() {
        ProgressUtils.hide();
    }

    @Override
    public void showMessage(String string) {
        CommonUtils.showToast(this,string);
    }

    @OnClick(R.id.imge_title_left)
    public void onClick() {
        back();
    }
}
