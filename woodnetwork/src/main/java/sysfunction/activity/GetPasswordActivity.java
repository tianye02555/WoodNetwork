package sysfunction.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import sysfunction.contract.GetPasswordContract;
import woodnetwork.hebg3.com.woodnetwork.R;

public class GetPasswordActivity extends AppCompatActivity implements GetPasswordContract.GetPasswordView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_password);
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
    public void setPresenter(Object presenter) {

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
}
