package woodnetwork.hebg3.com.woodnetwork.sysfunction.model;

import android.os.Handler;
import android.os.Message;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ServiceInterfaceCont;

import woodnetwork.hebg3.com.woodnetwork.net.ClientParams;
import woodnetwork.hebg3.com.woodnetwork.net.NetTask;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;
import woodnetwork.hebg3.com.woodnetwork.sysfunction.bean.User;
import woodnetwork.hebg3.com.woodnetwork.sysfunction.model.modelinterface.LoginModelInterface;

/**
 * Created by ty on 2016/8/16 0016.
 */

public class LoginModel implements LoginModelInterface {
    private OnServiceBaceInterface onLoginLisenter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ResponseBody body = (ResponseBody) msg.obj;
            if ("0".equals(body.base.code)) {//成功
                onLoginLisenter.onSuccess(body);
            } else if ("1".equals(body.base.code)) {//失败
                onLoginLisenter.onFailed(body.base.msg);
            }
        }
    };

    @Override
    public void login(Object param, final OnServiceBaceInterface onLoginLisenter) {
        this.onLoginLisenter = onLoginLisenter;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_POST;
        params.getMethod = ServiceInterfaceCont.LOGIN;
        params.params = CommonUtils.getParamString(param);
        new NetTask(handler.obtainMessage(), params, User.class).execute();

    }


}
