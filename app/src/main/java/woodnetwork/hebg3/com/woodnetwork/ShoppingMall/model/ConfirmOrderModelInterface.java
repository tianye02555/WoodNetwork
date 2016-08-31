package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.model;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;

/**
 * Created by ty on 2016/8/27 0027.
 */

public interface ConfirmOrderModelInterface {
    void getOrderInfo(Object object, OnServiceBaceInterface onServiceBaceInterface);
    void saveOrder(Object object, OnServiceBaceInterface onServiceBaceInterface);
}
