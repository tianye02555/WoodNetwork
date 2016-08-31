package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_orderAdd;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.OrderAdd;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopcarList;

/**
 * Created by ty on 2016/8/27 0027.
 */

public interface ConfirmOrderContrac {
    interface ConfirmOrderViewInterface extends BaseView<ConfirmOrderPresenterInterface>{
        void showOrderData(ShopcarList shopcarList);
        String getHarvestPlace();
        String getAddress();
        void showMoreAttribute();
        void jumpActivitywithAttribute(OrderAdd orderAdd);

    }
    interface ConfirmOrderPresenterInterface extends BasePresenter{
        void ConfirmOrder();
        void saveOrder(Request_orderAdd request_orderAdd);
    }
}
