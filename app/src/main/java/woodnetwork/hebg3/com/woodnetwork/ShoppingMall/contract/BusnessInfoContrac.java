package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopInfo;

/**
 * Created by ty on 2016/8/25 0025.
 */

public interface BusnessInfoContrac {
    interface BusnessInfoViewInterface extends BaseView<BusnessInfoPresenterInterface>{
        void back();

        /**
         * 设置商家信息
         * @param busnessInfo
         */
        void showBusnessData(BusnessInfo busnessInfo);
        /**
         * 设置卖家自己信息
         * @param
         */
        void showSellerBusnessData(ShopInfo shopInfo);

    }
    interface BusnessInfoPresenterInterface extends BasePresenter{
        /**
         * 获取商家信息
         * @param sid
         */
        void getBussnessInfo(String sid);
        /**
         * 获取卖家自己的信息
         * @param
         */
        void getSellerBussnessInfo();
    }
}

