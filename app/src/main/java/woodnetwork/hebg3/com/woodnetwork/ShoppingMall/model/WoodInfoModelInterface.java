package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.model;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;

/**
 * Created by ty on 2016/8/25 0025.
 */

public interface WoodInfoModelInterface {
    /**
     * 获取商品详情接口
     * @param object
     * @param onServiceBaceInterface
     */
    void getWoodData(Object object, OnServiceBaceInterface onServiceBaceInterface);

    /**
     * 添加到购物车接口
     * @param object
     * @param onServiceBaceInterface
     */
    void addToShoppingCart(Object object, OnServiceBaceInterface onServiceBaceInterface);
    /**
     * 获取商品详情接口(我的商品详情页)
     * @param object
     * @param onServiceBaceInterface
     */
    void getWoodDataOther(Object object, OnServiceBaceInterface onServiceBaceInterface);

}
