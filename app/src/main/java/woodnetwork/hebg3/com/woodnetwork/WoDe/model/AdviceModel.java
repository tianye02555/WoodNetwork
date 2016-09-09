package woodnetwork.hebg3.com.woodnetwork.WoDe.model;

import android.os.Handler;
import android.os.Message;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ServiceInterfaceCont;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.GuestbookTypeList;
import woodnetwork.hebg3.com.woodnetwork.net.ClientParams;
import woodnetwork.hebg3.com.woodnetwork.net.NetTask;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/6 0006.
 */

public class AdviceModel implements AdviceModelInterface {
    private OnServiceBaceInterface onServiceBaceInterface_submitData;
    private OnServiceBaceInterface onServiceBaceInterface_getGuestbookTypeData;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ResponseBody body = (ResponseBody) msg.obj;
            switch (msg.what) {
                case 0://获取购物车列表接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_submitData.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_submitData.onFailed(body.base.msg);
                    }
                    break;
                case 1://更改物品数量接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getGuestbookTypeData.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_getGuestbookTypeData.onFailed(body.base.msg);
                    }
                    break;
            }

        }
    };
    @Override
    public void submitData(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_submitData = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_POST;
        params.getMethod = ServiceInterfaceCont.GUESTBOOKADD;
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(0), params).execute();
    }

    @Override
    public void getGuestbookTypeData(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getGuestbookTypeData = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.GUESTBOOKTYPELIST;
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(1),params,GuestbookTypeList.class).execute();
    }
}
