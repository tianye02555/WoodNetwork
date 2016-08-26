package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.model;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;

/**
 * Created by ty on 2016/8/24 0024.
 */

public interface ShoppingMallModelInterface {
    void getSpinnerData(Object param, final OnServiceBaceInterface onLoginLisenter);
    void getGoodsData(Object param, final OnServiceBaceInterface onLoginLisenter);
    void getBusinessData(Object param, final OnServiceBaceInterface onLoginLisenter);
}
