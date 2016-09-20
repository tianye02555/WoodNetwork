package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model;

import android.os.Handler;
import android.os.Message;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderSellerExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderSellerFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderSellerList;
import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ServiceInterfaceCont;
import woodnetwork.hebg3.com.woodnetwork.net.ClientParams;
import woodnetwork.hebg3.com.woodnetwork.net.NetTask;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/14 0014.
 */

public class SellerOrderModel implements SellerOrderModelInterface {
    private OnServiceBaceInterface onServiceBaceInterface_getAllSellerOrderData;
    private OnServiceBaceInterface onServiceBaceInterface_getorderSellerFilterListData;
    private OnServiceBaceInterface onServiceBaceInterface_getorderSellerExceptionListData;
    private OnServiceBaceInterface onServiceBaceInterface_getorderSellerClose;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ResponseBody body = (ResponseBody) msg.obj;
            switch (msg.what) {
                case 0://获取全部订单列表接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getAllSellerOrderData.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_getAllSellerOrderData.onFailed(body.base.msg);
                    }
                    break;
                case 1://根据订单类型获取订单列表接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getorderSellerFilterListData.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_getorderSellerFilterListData.onFailed(body.base.msg);
                    }
                    break;
                case 2://获取异常订单列表接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getorderSellerExceptionListData.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_getorderSellerExceptionListData.onFailed(body.base.msg);
                    }
                    break;
                case 3://关闭订单接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getorderSellerClose.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_getorderSellerClose.onFailed(body.base.msg);
                    }
                    break;
            }

        }
    };

    @Override
    public void getAllSellerOrderData(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getAllSellerOrderData = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.ORDERSELLERLIST;
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(0),params,OrderSellerList.class).execute();
    }

    @Override
    public void getSellerFilterOrderData(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getorderSellerFilterListData = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.ORDERSELLERFILTERLIST;
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(1),params,OrderSellerFilterList.class).execute();
    }

    @Override
    public void getSellerOrderExceptionListData(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getorderSellerExceptionListData = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.ORDERSELLEREXCEPTIONLIST;
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(2),params, OrderSellerExceptionList.class).execute();
    }

    @Override
    public void closecOrder(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getorderSellerClose = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.ORDERSELLERCLOSE;
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(3),params).execute();
    }
}
