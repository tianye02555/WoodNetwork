package sysfunction.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sysfunction.contract.LoginContract;
import sysfunction.presenter.LoginPresenter;
import woodnetwork.hebg3.com.woodnetwork.R;

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
    private LoginContract.Presenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        new LoginPresenter(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mLoginPresenter.start();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        if (null != presenter) {
            mLoginPresenter = presenter;
        }
    }

    @Override
    public void getUserName() {

    }

    @Override
    public void getPassword() {

    }

    @Override
    public void clearUserNameAndPassword() {

    }

    @Override
    public void jumpActivity(Activity activity) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void closeProgress() {

    }

    @Override
    public void showfailMessage() {

    }

    @OnClick(R.id.button)
    public void onClick() {
    }
}
