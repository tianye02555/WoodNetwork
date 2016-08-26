package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductInfo;

/**
 * Created by ty on 2016/8/25 0025.
 */

public interface WoodInfoContrac {
    interface WoodInfoViewInterFace extends BaseView<WoodInfoPresenterInterface>{
        void showWoodData(ProductInfo productInfo);

    }
    interface WoodInfoPresenterInterface extends BasePresenter{
        void addToShoppingCart();
        void buy();
        void toShoppingCart();
        void getWoodInfo(String pid);
    }
}
