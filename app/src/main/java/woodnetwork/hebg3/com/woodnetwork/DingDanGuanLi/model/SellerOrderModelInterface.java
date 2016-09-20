package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;

/**
 * Created by ty on 2016/9/14 0014.
 */

public interface SellerOrderModelInterface {
    /**
     * 获取卖家全部订单列表
     * @param
     */
    void getAllSellerOrderData(Object object, OnServiceBaceInterface onServiceBaceInterface);
    /**
     * 获取卖家待发货订单列表
     * @param
     */
    void getSellerFilterOrderData(Object object, OnServiceBaceInterface onServiceBaceInterface);
    /**
     * 获取异常订单列表
     * @param
     */
    void getSellerOrderExceptionListData(Object object, OnServiceBaceInterface onServiceBaceInterface);
    /**
     * 关闭订单
     * @param
     */
    void closecOrder(Object object, OnServiceBaceInterface onServiceBaceInterface);
}
