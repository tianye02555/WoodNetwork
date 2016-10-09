package woodnetwork.hebg3.com.woodnetwork.sysfunction.model;

import android.os.Handler;
import android.os.Message;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ServiceInterfaceCont;
import woodnetwork.hebg3.com.woodnetwork.net.ClientParams;
import woodnetwork.hebg3.com.woodnetwork.net.NetTask;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;
import woodnetwork.hebg3.com.woodnetwork.sysfunction.model.modelinterface.GetPasswordModelInterface;

/**
 * Created by ty on 2016/8/18 0018.
 */

public class GetPasswordModel implements GetPasswordModelInterface {
    private OnServiceBaceInterface onServiceBaceInterface;
    private OnServiceBaceInterface onServiceBaceInterface_getCode;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ResponseBody body = (ResponseBody) msg.obj;
            switch (msg.what) {
                case 0://获取验证码
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getCode.onSuccess(body);
                    } else  {//失败
                        onServiceBaceInterface_getCode.onFailed(body.base.msg);
                    }
                    break;
                case 1://更改密码
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface.onSuccess(body);
                    } else  {//失败
                        onServiceBaceInterface.onFailed(body.base.msg);
                    }
                    break;
            }
        }
    };

    @Override
    public void sendCode(Object param, final OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getCode = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_POST;
        params.getMethod = ServiceInterfaceCont.GETAUTHCODE;
        params.params = CommonUtils.getParamString(param);
        new NetTask(handler.obtainMessage(0), params).execute();
    }

    @Override
    public void submit(Object param, final OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_POST;
        params.getMethod = ServiceInterfaceCont.GETPASSWORD;
        params.params = CommonUtils.getParamString(param);
        new NetTask(handler.obtainMessage(1), params).execute();

    }
}
