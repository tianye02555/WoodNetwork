package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model;


import android.os.Handler;
import android.os.Message;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerInfo;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderSellerInfo;
import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ServiceInterfaceCont;
import woodnetwork.hebg3.com.woodnetwork.net.ClientParams;
import woodnetwork.hebg3.com.woodnetwork.net.NetTask;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/18 0018.
 */

public class OrderBuyerInfoModel implements OrderBuyerInfoModelInterface {
    private OnServiceBaceInterface onServiceBaceInterface;
    private OnServiceBaceInterface onServiceBaceInterface_getSellerOrderData;
    private OnServiceBaceInterface onServiceBaceInterface_getDemOrderData;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ResponseBody body = (ResponseBody) msg.obj;
            switch (msg.what) {
                case 0://获取买家订单详情
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface.onSuccess(body);
                    } else  {//失败
                        onServiceBaceInterface.onFailed(body.base.msg);
                    }
                    break;
                case 1://获取卖家订单详情
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getSellerOrderData.onSuccess(body);
                    } else  {//失败
                        onServiceBaceInterface_getSellerOrderData.onFailed(body.base.msg);
                    }
                    break;
                case 2://获取求购订单详情
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getDemOrderData.onSuccess(body);
                    } else  {//失败
                        onServiceBaceInterface_getDemOrderData.onFailed(body.base.msg);
                    }
                    break;
            }

        }
    };
    @Override
    public void getOrderData(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.ORDERBUYERINFO;
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(0),params,OrderBuyerInfo.class).execute();
    }

    @Override
    public void getSellerOrderData(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getSellerOrderData = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.ORDERSELLERINFO;
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(1),params,OrderSellerInfo.class).execute();
    }

}
