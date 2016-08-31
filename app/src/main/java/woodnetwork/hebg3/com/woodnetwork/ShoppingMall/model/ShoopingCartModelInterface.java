package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.model;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;

/**
 * Created by ty on 2016/8/30 0030.
 */

public interface ShoopingCartModelInterface {
    void getShoopingCartData(Object object, OnServiceBaceInterface onServiceBaceInterface);
    void  changeGoodsNumber(Object object, OnServiceBaceInterface onServiceBaceInterface);
    void  deleteGoods(Object object, OnServiceBaceInterface onServiceBaceInterface);
}
