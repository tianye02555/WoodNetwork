package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;

/**
 * Created by ty on 2016/9/18 0018.
 */

public interface OrderBuyerInfoModelInterface {
    /**
     * 获取订单详情接口
     */
    void getOrderData(Object object, OnServiceBaceInterface onServiceBaceInterface);
    /**
     * 获取卖家订单详情接口
     */
    void getSellerOrderData(Object object, OnServiceBaceInterface onServiceBaceInterface);
}
