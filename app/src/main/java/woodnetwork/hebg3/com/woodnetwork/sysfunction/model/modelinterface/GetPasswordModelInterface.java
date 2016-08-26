package woodnetwork.hebg3.com.woodnetwork.sysfunction.model.modelinterface;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;

/**
 * Created by Administrator on 2016/8/18 0018.
 */

public interface GetPasswordModelInterface {
    void sendCode(Object param, final OnServiceBaceInterface onLoginLisenter);

    void submit(Object param, final OnServiceBaceInterface onLoginLisenter);

}
