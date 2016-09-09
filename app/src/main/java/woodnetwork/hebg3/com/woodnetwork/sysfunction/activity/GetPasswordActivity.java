package woodnetwork.hebg3.com.woodnetwork.sysfunction.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.sysfunction.contract.GetPasswordContract;
import woodnetwork.hebg3.com.woodnetwork.sysfunction.presenter.GetPasswordPresenter;
import woodnetwork.hebg3.com.woodnetwork.R;

public class GetPasswordActivity extends AppCompatActivity implements GetPasswordContract.GetPasswordView {

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.getpassword_edit_user)
    EditText getpasswordEditUser;
    @Bind(R.id.getpassword_btn_getcode)
    Button getpasswordBtnGetcode;
    @Bind(R.id.getpassword_edit_code)
    EditText getpasswordEditCode;
    @Bind(R.id.getpassword_edit_newpassword)
    EditText getpasswordEditNewpassword;
    @Bind(R.id.getpassword_edit_makesurepassword)
    EditText getpasswordEditMakesurepassword;
    @Bind(R.id.getpassword_btn_submit)
    Button getpasswordBtnSubmit;
    @Bind(R.id.activity_get_password)
    LinearLayout activityGetPassword;

    private GetPasswordPresenter getPasswordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_password);
        CommonUtils.addActivity(this);
        ButterKnife.bind(this);
        imageTitleRight.setVisibility(View.GONE);
        textTitle.setText("找回密码");
        new GetPasswordPresenter(this);
    }

    @Override
    public String getUserName() {
        if (!TextUtils.isEmpty(getpasswordEditUser.getText().toString().trim())) {
            return getpasswordEditUser.getText().toString().trim();
        }
        CommonUtils.showToast(this, "用户名不能为空");
        return null;
    }

    @Override
    public String getCode() {
        if (!TextUtils.isEmpty(getpasswordEditCode.getText().toString().trim())) {
            return getpasswordEditCode.getText().toString().trim();
        }
        CommonUtils.showToast(this, "");
        return null;
    }

    @Override
    public String getNewPassword() {
        if (!TextUtils.isEmpty(getpasswordEditNewpassword.getText().toString().trim())) {
            return getpasswordEditNewpassword.getText().toString().trim();
        }
        return null;
    }

    @Override
    public String getNewPasswordTwice() {
        if (!TextUtils.isEmpty(getpasswordEditMakesurepassword.getText().toString().trim())) {
            return getpasswordEditMakesurepassword.getText().toString().trim();
        }
        return null;
    }

    @Override
    public void sendCode() {

    }

    @Override
    public void showToastMessage(String message) {

    }

    @Override
    public void submitNewPassword() {

    }

    @Override
    public void setPresenter(GetPasswordPresenter presenter) {
        if (null != presenter) {
            getPasswordPresenter = presenter;
        }
    }

    @Override
    public void showProgress() {
        ProgressUtils.show(this, "请稍后……");
    }

    @Override
    public void closeActivity() {
        this.finish();
    }

    @Override
    public void countDown() {


    }

    @Override
    public void closeProgress() {
        ProgressUtils.hide();
    }

    @Override
    public void showMessage(String string) {
        CommonUtils.showToast(this, string);
    }

    @OnClick({R.id.getpassword_btn_getcode, R.id.activity_get_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getpassword_btn_getcode:
                if(!TextUtils.isEmpty(getUserName())){
                    new CountTimer(120000).start();
                    getPasswordPresenter.sendCode();
                }else{
                    CommonUtils.showToast(this,"用户名不能为空");
                }
                break;
            case R.id.activity_get_password:
                if (!TextUtils.isEmpty(getUserName()) && TextUtils.isEmpty(getCode()) && TextUtils.isEmpty(getNewPassword()) && TextUtils.isEmpty(getNewPasswordTwice())) {
                    if(getNewPassword().equals(getNewPasswordTwice())){
                        getPasswordPresenter.submit();
                    }
                }

                break;
        }
    }

    /**
     * 倒计时类
     */
    class CountTimer extends CountDownTimer{
        public CountTimer(long z){
            super(z,1000);

        }

        @Override
        public void onTick(long l) {
            getpasswordBtnGetcode.setClickable(false);
            getpasswordBtnGetcode.setText(l/1000+"s");
        }

        @Override
        public void onFinish() {
            getpasswordBtnGetcode.setText("获取验证码");
            getpasswordBtnGetcode.setClickable(true);
        }
    }
}
