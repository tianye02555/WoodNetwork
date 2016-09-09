package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.model;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;

/**
 * Created by ty on 2016/8/25 0025.
 */

public interface BusnessInfoModelInterface {
    /**
     * 获取商家信息接口
     * @param param
     * @param onServiceBaceInterface
     */
    void getBusnessInfo(Object param, OnServiceBaceInterface onServiceBaceInterface);
    /**
     * 获取卖家自己的信息接口（）
     * @param param
     * @param onServiceBaceInterface
     */
    void getSellerBusnessInfo(Object param, OnServiceBaceInterface onServiceBaceInterface);
}
