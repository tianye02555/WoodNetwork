package sysfunction.presenter;

import sysfunction.contract.LoginContract;
import sysfunction.model.LoginModel;

/**
 * Created by ty on 2016/8/15 0015.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View mLoginView;
    private LoginModel loginModel;
    public LoginPresenter(LoginContract.View loginView){
        //如果view为空直接退出
        if(null==loginView){
           return;
        }
        //赋值view
        mLoginView=loginView;
        //回调presenter函数，给view的presenter赋值
        mLoginView.setPresenter(this);
        //实例化modle ,监听成功或失败
        loginModel=new LoginModel();
    }
    @Override
    public void start() {
        //登录借口
//        loginModel.login("",);
    }
}
