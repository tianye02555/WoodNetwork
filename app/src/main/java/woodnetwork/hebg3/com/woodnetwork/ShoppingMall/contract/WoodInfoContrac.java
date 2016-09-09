package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductInfo;

/**
 * Created by ty on 2016/8/25 0025.
 */

public interface WoodInfoContrac {
    interface WoodInfoViewInterFace extends BaseView<WoodInfoPresenterInterface>{
        /**
         * 显示商品信息
         * @param
         */
        void showWoodData(Object object);


    }
    interface WoodInfoPresenterInterface extends BasePresenter{
        /**
         * 添加到购物车
         */
        void addToShoppingCart();

        /**
         * 获取商品信息
         * @param pid
         */
        void getWoodInfo(String pid);
        /**
         * 获取商品信息（我的商品详情页）
         * @param pid
         */
        void getWoodInfoOther(String pid);

    }
}
