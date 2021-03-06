package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model;


import android.os.Handler;
import android.os.Message;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.ExceptionList;
import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ServiceInterfaceCont;
import woodnetwork.hebg3.com.woodnetwork.net.ClientParams;
import woodnetwork.hebg3.com.woodnetwork.net.NetTask;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/18 0018.
 */

public class OrderExceptionModel implements OrderExceptionModelInterface {
    private OnServiceBaceInterface onServiceBaceInterface;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ResponseBody body = (ResponseBody) msg.obj;
            switch (msg.what) {
                case 0://获取全部订单列表接口
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
    public void getOrderExceptionList(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.EXCEPTIONLIST;
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(0),params,ExceptionList.class).execute();
    }
}
