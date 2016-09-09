package woodnetwork.hebg3.com.woodnetwork.WoDe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.WoDe.adapter.MyInformationAdapter;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.UserInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.MyInformationContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.presenter.MyInformationPresenter;
import woodnetwork.hebg3.com.woodnetwork.sysfunction.activity.GetPasswordActivity;
import woodnetwork.hebg3.com.woodnetwork.view.MyGridView;

public class MyInformationActivity extends AppCompatActivity implements MyInformationContract.MyInformationViewInterface {

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_my_information_text_user)
    TextView user;
    @Bind(R.id.activity_my_information_text_phone)
    TextView phone;
    @Bind(R.id.activity_my_information_text_email)
    TextView email;
    @Bind(R.id.activity_my_information_text_name)
    TextView name;
    @Bind(R.id.activity_my_information_text_address)
    TextView address;
    @Bind(R.id.activity_my_information_mygridview)
    MyGridView myGridView;
    @Bind(R.id.activity_my_information_btn_xiugaimima)
    Button xiuGaiMiMa;
    private MyInformationContract.MyInformationPresenterInterface presenter;
    private MyInformationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_information);
        CommonUtils.addActivity(this);
        ButterKnife.bind(this);

        textTitle.setText("我的信息");
        imageTitleRight.setVisibility(View.GONE);

        new MyInformationPresenter(this);

        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(this);
        Request_getAttribute request_getAttribute = new Request_getAttribute();
        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");

        MyRequestInfo myRequestInfo = new MyRequestInfo();
        myRequestInfo.req = new Object();
        myRequestInfo.req_meta = request_getAttribute;
        presenter.getMyInformationData(myRequestInfo);
    }

    @OnClick({R.id.imge_title_left, R.id.image_title_right, R.id.activity_my_information_btn_xiugaimima})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imge_title_left:
                finish();
                break;
            case R.id.image_title_right:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.activity_my_information_btn_xiugaimima:
                startActivity(new Intent(this, GetPasswordActivity.class));
                break;
        }
    }

    @Override
    public void setMyInformationInfo(UserInfo userInfo) {
        user.setText("用  户  名  " + userInfo.login_name);
        phone.setText("手机号码  " + userInfo.phone);
        email.setText("联系邮箱  " + userInfo.mail);
        if (0 == userInfo.company_flag) {
            name.setText("企业名称  " + userInfo.company.name);
            address.setText("企业地址  " + userInfo.company.address);
            adapter = new MyInformationAdapter(this, userInfo.company.img);
        } else if (1 == userInfo.company_flag) {
            name.setText("用户昵称  " + userInfo.personal.name);
            address.setText("身份证号  " + userInfo.personal.card);
            adapter = new MyInformationAdapter(this, userInfo.personal.img);
        }

        myGridView.setAdapter(adapter);
    }

    @Override
    public void setPresenter(MyInformationContract.MyInformationPresenterInterface presenter) {
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
