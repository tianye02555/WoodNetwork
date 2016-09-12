package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopcarList;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;

/**
 * Created by ty on 2016/8/30 0030.
 */

public interface ShoopingCartContract {
    interface ShoopingCartViewInterface extends BaseView<ShoopingCartPresenterInterface> {
        /**
         * 显示购物车商品列表
         *
         * @param shopcarList
         */
        void showShoopingCartInfo(ShopcarList shopcarList);

        /**
         * 删除商品
         */
        void deleteGoods();

        /**
         *
         */
        void submitOrder();

        /**
         * 修改数量
         */
        void changeNumber();

        /**
         * 显示总价
         */
        void showTitlePrice();

    }

    interface ShoopingCartPresenterInterface extends BasePresenter {
        /**
         * 获取购物车列表接口
         *
         * @param myRequestInfo
         */
        void getShoopingCartData(MyRequestInfo myRequestInfo);

        /**
         * 改变商品数量接口
         *
         * @param myRequestInfo
         */
        void changeGoodsNumber(MyRequestInfo myRequestInfo);

        /**
         * 删除商品接口
         *
         * @param myRequestInfo
         */
        void deleteGoods(MyRequestInfo myRequestInfo);
    }
}
