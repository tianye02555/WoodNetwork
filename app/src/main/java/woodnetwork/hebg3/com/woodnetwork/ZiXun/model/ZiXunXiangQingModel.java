package woodnetwork.hebg3.com.woodnetwork.ZiXun.model;

import android.os.Handler;
import android.os.Message;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ServiceInterfaceCont;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.ArticleWebInfo;
import woodnetwork.hebg3.com.woodnetwork.net.ClientParams;
import woodnetwork.hebg3.com.woodnetwork.net.NetTask;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/1 0001.
 */

public class ZiXunXiangQingModel implements ZiXunXiangQingModelInterface {
    private OnServiceBaceInterface onServiceBaceInterface;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ResponseBody body = (ResponseBody) msg.obj;

            if ("0".equals(body.base.code)) {//成功
                onServiceBaceInterface.onSuccess(body);
            } else if ("1".equals(body.base.code)) {//失败
                onServiceBaceInterface.onFailed(body.base.msg);
            }

        }
    };

    @Override
    public void getWebViewData(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.ARTICLEWEBINFO;
        params.GETTYPE = "1";
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(), params, ArticleWebInfo.class).execute();
    }
}
