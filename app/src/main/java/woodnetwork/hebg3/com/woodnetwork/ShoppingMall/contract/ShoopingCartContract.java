package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopcarList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopcarList_listItem;
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
        void changeNumber(int position);

        /**
         * 显示总价
         */
        void showTitlePrice();
        /**
         * 加载更多
         */
        void loadMore(List<ShopcarList_listItem> list);
        /**
         * 更新
         */
        void refresh(ShopcarList shopcarList);

    }

    interface ShoopingCartPresenterInterface extends BasePresenter {
        /**
         * 获取购物车列表接口
         *
         * @param myRequestInfo
         */
        void getShoopingCartData(MyRequestInfo myRequestInfo,int flag);

        /**
         * 改变商品数量接口
         *
         * @param myRequestInfo
         */
        void changeGoodsNumber(MyRequestInfo myRequestInfo,int position);

        /**
         * 删除商品接口
         *
         * @param myRequestInfo
         */
        void deleteGoods(MyRequestInfo myRequestInfo);
    }
}
