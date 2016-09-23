package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model;

import android.os.Handler;
import android.os.Message;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProList;
import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ServiceInterfaceCont;
import woodnetwork.hebg3.com.woodnetwork.net.ClientParams;
import woodnetwork.hebg3.com.woodnetwork.net.NetTask;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/14 0014.
 */

public class MyOrderModel implements MyOrderModelInterface {
    private OnServiceBaceInterface onServiceBaceInterface_getAllMyOrderData;
    private OnServiceBaceInterface onServiceBaceInterface_getorderBuyerProFilterListData;
    private OnServiceBaceInterface onServiceBaceInterface_getorderBuyerProExceptionListData;
    private OnServiceBaceInterface onServiceBaceInterface_getorderBuyerProClose;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ResponseBody body = (ResponseBody) msg.obj;
            switch (msg.what) {
                case 0://获取全部订单列表接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getAllMyOrderData.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_getAllMyOrderData.onFailed(body.base.msg);
                    }
                    break;
                case 1://根据订单类型获取订单列表接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getorderBuyerProFilterListData.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_getorderBuyerProFilterListData.onFailed(body.base.msg);
                    }
                    break;
                case 2://获取异常订单列表接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getorderBuyerProExceptionListData.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_getorderBuyerProExceptionListData.onFailed(body.base.msg);
                    }
                    break;
                case 3://关闭订单接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getorderBuyerProClose.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_getorderBuyerProClose.onFailed(body.base.msg);
                    }
                    break;

            }

        }
    };

    @Override
    public void getAllMyOrderData(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getAllMyOrderData = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.ORDERBUYERPROLIST;
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(0),params,OrderBuyerProList.class).execute();
    }

    @Override
    public void getorderBuyerProFilterListData(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getorderBuyerProFilterListData = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.ORDERBUYERPROFILTER;
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(1),params,OrderBuyerProFilterList.class).execute();
    }

    @Override
    public void getorderBuyerProExceptionListData(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getorderBuyerProExceptionListData = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.ORDERBUYERPROEXCEPTIONLIST;
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(2),params, OrderBuyerProExceptionList.class).execute();
    }

    @Override
    public void getorderBuyerProClose(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getorderBuyerProClose = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.ORDERBUYERCLOSE;
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(3),params).execute();
    }


}
