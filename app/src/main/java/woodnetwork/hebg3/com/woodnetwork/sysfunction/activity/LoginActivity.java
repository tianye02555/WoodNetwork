package woodnetwork.hebg3.com.woodnetwork.sysfunction.activity;

import android.content.Intent;
import android.os.Bundle;
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
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.sysfunction.bean.User;
import woodnetwork.hebg3.com.woodnetwork.sysfunction.contract.LoginContract;
import woodnetwork.hebg3.com.woodnetwork.sysfunction.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;//顶部左侧返回键
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.text_user)
    EditText textUser;
    @Bind(R.id.text_password)
    EditText textPassword;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.activity_login)
    LinearLayout activityLogin;
    @Bind(R.id.activity_login_text_wangjimima)
    TextView wangJiMiMa;
    private LoginContract.Presenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        imageTitleRight.setVisibility(View.GONE);
        imgeTitleLeft.setVisibility(View.GONE);
        new LoginPresenter(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        if (null != presenter) {
            mLoginPresenter = presenter;
        }
    }

    @Override
    public String getUserName() {
        if (TextUtils.isEmpty(textUser.getText().toString().trim())) {
            return null;
        }
        return textUser.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        if (TextUtils.isEmpty(textPassword.getText().toString().trim())) {
            return null;
        }
        return textPassword.getText().toString().trim();
    }

    @Override
    public void clearUserNameAndPassword() {

    }

    @Override
    public void jumpActivity(Class mClass) {
        startActivity(new Intent(this, mClass));
    }

    @Override
    public void showProgress() {
        ProgressUtils.show(this, "登录中……");
    }

    @Override
    public void closeProgress() {
        ProgressUtils.hide();
    }

    @Override
    public void showfailMessage(String string) {
        CommonUtils.showToast(this, string);
    }

    @OnClick({R.id.button, R.id.activity_login_text_wangjimima})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                if(TextUtils.isEmpty(getUserName())&&TextUtils.isEmpty(getPassword())){
                    CommonUtils.showToast(this, "用户名或密码不能为空");
                    return;
                }
                mLoginPresenter.login();
                break;
            case R.id.activity_login_text_wangjimima:
                jumpActivity(GetPasswordActivity.class);
                break;
        }
    }

}
