package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;

/**
 * Created by ty on 2016/9/14 0014.
 */

public interface MyOrderModelInterface {
    /**
     * 获取全部订单列表
     * @param
     */
    void getAllMyOrderData(Object object, OnServiceBaceInterface onServiceBaceInterface);
    /**
     * 获取未付款订单列表
     * @param
     */
    void getorderBuyerProFilterListData(Object object, OnServiceBaceInterface onServiceBaceInterface);
    /**
     * 获取待收货订单列表
     * @param
     */
    void getorderBuyerProExceptionListData(Object object, OnServiceBaceInterface onServiceBaceInterface);
    /**
     * 获取异常订单列表
     * @param
     */
    void getorderBuyerProClose(Object object, OnServiceBaceInterface onServiceBaceInterface);
}
