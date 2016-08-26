package woodnetwork.hebg3.com.woodnetwork.sysfunction.model.modelinterface;

import android.content.Context;

import java.util.HashMap;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.sysfunction.bean.User;
import woodnetwork.hebg3.com.woodnetwork.sysfunction.model.LoginModel;

/**
 * Created by ty on 2016/8/16 0016.
 */

public interface LoginModelInterface {
    /**
     * 登录
     * @param param
     * @param onLoginLisenter
     */
    void login(Object param, OnServiceBaceInterface onLoginLisenter);

}
