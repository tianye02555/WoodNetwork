package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract;

import java.util.ArrayList;
import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_shoppingMall_woodsList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductFilterList_productsItem;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.WoodFilterAttribute;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.WoodFilterInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;

/**
 * Created by ty on 2016/8/24 0024.
 */

public interface ShoppingMallContract {
    interface ShoppingMallView extends BaseView<ShoppingMallPresenter>{
        /**
         * 跳转页面
         * @param MyClass
         */
       void jumpActivity(Class MyClass);

        /**
         * 显示商品信息
         * @param list
         */
        void showGoodsData(List<ProductFilterList_productsItem> list);

        /**
         * 设置筛选内容
         * @param woodFilterAttribute
         */
        void showAttributeFilterListInfo(WoodFilterAttribute woodFilterAttribute);

        /**
         * 显示输入数量对话框
         *
         * id（控件id） 判断是点击了购买按钮还是加入购物车按钮
         * pid  点击的项的商品id
         */
        void showNumberDialog(int id,int position);
    }
    interface ShoppingMallPresenter extends BasePresenter{
        /**
         * 获取商品信息
         * @param request_shoppingMall_woodsList
         */
        void getWoodsList(Request_shoppingMall_woodsList request_shoppingMall_woodsList);

        /**
         * 获取筛选列表
         */
        void getAttributeFilterListData(MyRequestInfo myRequestInfo);

        /**
         * 添加到购物车
         * @param myRequestInfo
         */
        void shopcarAdd(MyRequestInfo myRequestInfo);
    }
}
