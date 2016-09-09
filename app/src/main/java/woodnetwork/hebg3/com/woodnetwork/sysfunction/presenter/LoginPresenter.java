package woodnetwork.hebg3.com.woodnetwork.sysfunction.presenter;

import android.app.Activity;

import com.google.gson.Gson;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_login;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity.ShoppingMallActivity;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MD5Utils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.WoodNetworkApplication;
import woodnetwork.hebg3.com.woodnetwork.net.Base;
import woodnetwork.hebg3.com.woodnetwork.net.Const;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;
import woodnetwork.hebg3.com.woodnetwork.sysfunction.activity.LoginActivity;
import woodnetwork.hebg3.com.woodnetwork.sysfunction.bean.User;
import woodnetwork.hebg3.com.woodnetwork.sysfunction.contract.LoginContract;
import woodnetwork.hebg3.com.woodnetwork.sysfunction.model.LoginModel;

/**
 * Created by ty on 2016/8/15 0015.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View mLoginView;
    private LoginModel loginModel;

    public LoginPresenter(LoginContract.View loginView) {
        //如果view为空直接退出
        if (null == loginView) {
            return;
        }
        //赋值view
        mLoginView = loginView;
        //回调presenter函数，给view的presenter赋值
        mLoginView.setPresenter(this);
        //实例化modle ,监听成功或失败
        loginModel = new LoginModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void login() {
        mLoginView.showProgress();
        Request_login login = new Request_login();
        login.name = mLoginView.getUserName();
        login.password = MD5Utils.getMD5(mLoginView.getPassword());
        //登录接口
        loginModel.login(CommonUtils.getRequestInfo(login,new Object()), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                User user=(User)((ResponseBody)object).obj;
                putUserInfo2Shareprefermence(user);
                mLoginView.closeProgress();
                mLoginView.jumpActivity(ShoppingMallActivity.class);
            }

            @Override
            public void onFailed(String string) {
                mLoginView.closeProgress();
                mLoginView.showMessage(string);
            }
        });
    }
    public void putUserInfo2Shareprefermence(User user) {
        SharePreferencesUtils sharePreferencesUtils=SharePreferencesUtils.getSharePreferencesUtils((Activity)mLoginView);
        sharePreferencesUtils.saveData("userid",user.id);
        sharePreferencesUtils.saveData("login_name",user.login_name);
        sharePreferencesUtils.saveData("company_flag",user.company_flag);
        sharePreferencesUtils.saveData("seller_flag",user.seller_flag);
        sharePreferencesUtils.saveData("status",user.status);
        sharePreferencesUtils.saveData("user_name",user.user_name);
        sharePreferencesUtils.saveData("password",user.password);
    }
}
