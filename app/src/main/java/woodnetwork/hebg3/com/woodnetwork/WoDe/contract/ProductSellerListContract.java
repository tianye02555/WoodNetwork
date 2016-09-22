package woodnetwork.hebg3.com.woodnetwork.WoDe.contract;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.ProductSellerList;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.ProductSellerList_productsItem;

/**
 * Created by ty on 2016/9/7 0007.
 */

public interface ProductSellerListContract {
    interface ProductSellerListViewInterface extends BaseView<ProductSellerListPresenterInterface>{
        /**
         * 显示我的商品列表数据
         * @param productSellerList
         */
        void showProductSellerListInfo(ProductSellerList productSellerList);
        /**
         * 加载更多
         */
        void loadMore(List<ProductSellerList_productsItem> list);
        /**
         * 更新
         */
        void refresh(ProductSellerList productSellerList);
    }
    interface ProductSellerListPresenterInterface extends BasePresenter{
        /**
         * 获取我的商品列表数据
         * @param myRequestInfo
         */
        void getProductSellerListData(MyRequestInfo myRequestInfo,int flag);
    }
}
