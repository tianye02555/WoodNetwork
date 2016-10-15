package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract;

import java.util.ArrayList;
import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_shoppingMall_woodsList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductFilterList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductFilterList_productsItem;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.WoodFilterAttribute;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.WoodFilterInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.VersionInfo;

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
         * @param
         */
        void showGoodsData(ProductFilterList productFilterList);

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

        /**
         * 加载更多
         */
        void loadMore(List<ProductFilterList_productsItem> newList);
        /**
         * 更新
         */
        void refresh(ProductFilterList newList);

        /**
         * 更新版本
         */
        void uploadVerSion(VersionInfo versionInfo);
    }
    interface ShoppingMallPresenter extends BasePresenter{
        /**
         * 获取商品信息
         * @param request_shoppingMall_woodsList
         */
        void getWoodsList(Request_shoppingMall_woodsList request_shoppingMall_woodsList,int falg);

        /**
         * 获取筛选列表
         */
        void getAttributeFilterListData(MyRequestInfo myRequestInfo);

        /**
         * 添加到购物车
         * @param myRequestInfo
         */
        void shopcarAdd(MyRequestInfo myRequestInfo);

        /**
         * 自动更新
         * @param myRequestInfo
         */
        void getCheckUpdateData(MyRequestInfo myRequestInfo);
    }
}
