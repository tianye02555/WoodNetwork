package sysfunction.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sysfunction.contract.GetPasswordContract;
import sysfunction.presenter.GetPasswordPresenter;
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
        ButterKnife.bind(this);
        new GetPasswordPresenter(this);
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getNewPassword() {
        return null;
    }

    @Override
    public String getNewPasswordTwice() {
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
        if(null!=presenter){
            getPasswordPresenter=presenter;
        }
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

    @OnClick({R.id.getpassword_btn_getcode, R.id.activity_get_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getpassword_btn_getcode:
                break;
            case R.id.activity_get_password:
                break;
        }
    }
}
