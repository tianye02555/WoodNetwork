package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model;

import android.os.Handler;
import android.os.Message;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemPayList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProPayList;
import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ServiceInterfaceCont;
import woodnetwork.hebg3.com.woodnetwork.net.ClientParams;
import woodnetwork.hebg3.com.woodnetwork.net.NetTask;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/14 0014.
 */

public class DemOrderModel implements DemOrderModelInterface {
    private OnServiceBaceInterface onServiceBaceInterface_getAllDemOrderData;
    private OnServiceBaceInterface onServiceBaceInterface_getorderBuyerDemFilterListData;
    private OnServiceBaceInterface onServiceBaceInterface_getorderBuyerDemExceptionListData;
    private OnServiceBaceInterface onServiceBaceInterface_getOrderBuyerDemPaidListData;
    private OnServiceBaceInterface onServiceBaceInterface_getorderBuyerDemClose;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ResponseBody body = (ResponseBody) msg.obj;
            switch (msg.what) {
                case 0://获取全部订单列表接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getAllDemOrderData.onSuccess(body);
                    } else {//失败
                        onServiceBaceInterface_getAllDemOrderData.onFailed(body.base.msg);
                    }
                    break;
                case 1://根据订单类型获取订单列表接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getorderBuyerDemFilterListData.onSuccess(body);
                    } else  {//失败
                        onServiceBaceInterface_getorderBuyerDemFilterListData.onFailed(body.base.msg);
                    }
                    break;
                case 2://获取异常订单列表接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getorderBuyerDemExceptionListData.onSuccess(body);
                    } else  {//失败
                        onServiceBaceInterface_getorderBuyerDemExceptionListData.onFailed(body.base.msg);
                    }
                    break;
                case 3://关闭订单接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getorderBuyerDemClose.onSuccess(body);
                    } else  {//失败
                        onServiceBaceInterface_getorderBuyerDemClose.onFailed(body.base.msg);
                    }
                    break;
                case 4://关闭订单接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getOrderBuyerDemPaidListData.onSuccess(body);
                    } else  {//失败
                        onServiceBaceInterface_getOrderBuyerDemPaidListData.onFailed(body.base.msg);
                    }
                    break;
            }

        }
    };

    @Override
    public void getAllDemOrderData(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getAllDemOrderData = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.ORDERBUYERDEMLIST;
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(0),params,OrderBuyerDemList.class).execute();
    }

    @Override
    public void getorderBuyerProFilterListData(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getorderBuyerDemFilterListData = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.ORDERBUYERDEMFILTERLIST;
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(1),params,OrderBuyerDemFilterList.class).execute();
    }

    @Override
    public void getorderBuyerProExceptionListData(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getorderBuyerDemExceptionListData = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.ORDERBUYERDEMEXCEPTIONLIST;
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(2),params, OrderBuyerDemExceptionList.class).execute();
    }

    @Override
    public void getorderBuyerProClose(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getorderBuyerDemClose = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.ORDERBUYERCLOSE;
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(3),params).execute();
    }
    @Override
    public void getOrderBuyerDemPaidListData(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getOrderBuyerDemPaidListData = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.ORDERBUYERDEMPAIDLIST;
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(4),params, OrderBuyerDemPayList.class).execute();
    }
}
