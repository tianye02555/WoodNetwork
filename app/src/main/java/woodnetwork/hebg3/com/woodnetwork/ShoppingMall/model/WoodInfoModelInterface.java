package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.model;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;

/**
 * Created by ty on 2016/8/25 0025.
 */

public interface WoodInfoModelInterface {
    void getWoodData(Object object, OnServiceBaceInterface onServiceBaceInterface);
    void addToShoppingCart(Object object, OnServiceBaceInterface onServiceBaceInterface);
    void buy(Object object, OnServiceBaceInterface onServiceBaceInterface);
    void toShoppingCart(Object object, OnServiceBaceInterface onServiceBaceInterface);
}
