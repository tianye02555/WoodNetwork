package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProExceptionList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProFilterList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderSellerExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderSellerExceptionList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderSellerFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderSellerFilterList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderSellerList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderSellerList_listItem;
import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;

/**
 * Created by ty on 2016/9/20 0020.
 */

public interface SellerOrderContract {
    interface SellerOrderViewInterface extends BaseView<SellerOrderContract.SellerOrderPresenterInterface> {
        /**
         * 展示全部订单列表
         * @param
         */
        void showSellerOrderInfo(Object object);

        /**
         * 关闭订单
         * @param
         */
        void closeOrder(String oid, int position);

        /**
         * 更新订单数据
         */
        void refreshOrder();
        /**
         * 全部订单加载更多
         */
        void loadMoreAll(List<OrderSellerList_listItem> list);
        /**
         * 全部订单更新
         */
        void refreshAll(OrderSellerList orderSellerList);
        /**
         * 分类订单加载更多
         */
        void loadMoreFilter(List<OrderSellerFilterList_listItem> list);
        /**
         * 分类订单更新
         */
        void refreshFilter(OrderSellerFilterList orderSellerFilterList);
        /**
         * 异常订单加载更多
         */
        void loadMoreException(List<OrderSellerExceptionList_listItem> list);
        /**
         * 异常订单更新
         */
        void refreshException(OrderSellerExceptionList orderSellerExceptionList);
    }
    interface SellerOrderPresenterInterface extends BasePresenter {
        /**
         * 获取全部订单列表
         * @param myRequestInfo
         */
        void getAllSellerOrderData(MyRequestInfo myRequestInfo,int flag);
        /**
         * 获取待收货订单列表
         * @param myRequestInfo
         */
        void getSellerFilterOrderData(MyRequestInfo myRequestInfo,int flag);
        /**
         * 获取异常订单列表
         * @param myRequestInfo
         */
        void getSellerOrderExceptionListData(MyRequestInfo myRequestInfo,int flag);
        /**
         * 获取异常订单列表
         * @param myRequestInfo
         */
        void closecOrder(MyRequestInfo myRequestInfo);
    }
}
