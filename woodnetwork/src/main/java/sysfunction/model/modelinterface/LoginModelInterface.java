package sysfunction.model.modelinterface;

import java.util.HashMap;

import sysfunction.model.LoginModel;

/**
 * Created by ty on 2016/8/16 0016.
 */

public interface LoginModelInterface {
    void login(String url, HashMap<String,String> param, LoginModel.OnLoginLisenter onLoginLisenter);

}
