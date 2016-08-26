package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract;

import java.util.ArrayList;
import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_shoppingMall_woodsList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductFilterList_productsItem;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.WoodFilterInfo;

/**
 * Created by ty on 2016/8/24 0024.
 */

public interface ShoppingMallContract {
    interface ShoppingMallView extends BaseView<ShoppingMallPresenter>{
       void jumpActivity(Class MyClass);
        void showSpinnerData(List<WoodFilterInfo> list);
        void showGoodsData(List<ProductFilterList_productsItem> list);
        void showBusnessInfo(List<BusnessInfo> list);

    }
    interface ShoppingMallPresenter extends BasePresenter{
        void getMoreBusiness();
        void getWoodsList(Request_shoppingMall_woodsList request_shoppingMall_woodsList);
    }
}
