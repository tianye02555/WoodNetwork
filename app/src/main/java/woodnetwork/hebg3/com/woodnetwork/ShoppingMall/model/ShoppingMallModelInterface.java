package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.model;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;

/**
 * Created by ty on 2016/8/24 0024.
 */

public interface ShoppingMallModelInterface {
    /**
     * 获取筛选信息
     * @param param
     * @param onLoginLisenter
     */
    void getSpinnerData(Object param, final OnServiceBaceInterface onLoginLisenter);

    /**
     * 根据筛选条件获取商品信息
     * @param param
     * @param onLoginLisenter
     */
    void getGoodsData(Object param, final OnServiceBaceInterface onLoginLisenter);

    /**
     *
     * @param param
     * @param onLoginLisenter
     */
    void getShopcarAdd(Object param, final OnServiceBaceInterface onLoginLisenter);
}
