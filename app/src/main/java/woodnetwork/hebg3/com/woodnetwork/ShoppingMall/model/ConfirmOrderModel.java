package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.OrderAdd;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.WoodFilterAttribute;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ServiceInterfaceCont;
import woodnetwork.hebg3.com.woodnetwork.net.ClientParams;
import woodnetwork.hebg3.com.woodnetwork.net.NetTask;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/8/27 0027.
 */

public class ConfirmOrderModel implements ConfirmOrderModelInterface {
    private OnServiceBaceInterface onServiceBaceInterface_orderInfo;
    private OnServiceBaceInterface onServiceBaceInterface_saveOrder;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ResponseBody body = (ResponseBody) msg.obj;
            switch (msg.what) {
                case 0://获取筛选属性列表接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_orderInfo.onSuccess(body);
                    } else   {//失败
                        onServiceBaceInterface_orderInfo.onFailed(body.base.msg);
                    }
                    break;
                case 1://保存订单接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_saveOrder.onSuccess(body);
                    } else  {//失败
                        onServiceBaceInterface_saveOrder.onFailed(body.base.msg);
                    }
                    break;
            }
        }
    };
    @Override
    public void getOrderInfo(Object param, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_orderInfo = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.ATTRIBUTEFILTER;
        params.GETTYPE="1";
        params.params = CommonUtils.getParamString(param);
        new NetTask(handler.obtainMessage(0), params,WoodFilterAttribute.class).execute();
    }

    @Override
    public void saveOrder(Object param, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_saveOrder = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_POST;
        params.getMethod = ServiceInterfaceCont.ORDERADD;
        params.params = CommonUtils.getParamString(param);
        new NetTask(handler.obtainMessage(1), params,OrderAdd.class).execute();
    }
}
