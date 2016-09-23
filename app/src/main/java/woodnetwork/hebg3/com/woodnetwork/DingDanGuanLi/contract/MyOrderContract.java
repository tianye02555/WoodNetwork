package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract;

import android.widget.BaseAdapter;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProExceptionList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProFilterList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProList_listItem;
import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;

/**
 * Created by ty on 2016/9/14 0014.
 */

public interface MyOrderContract {
    interface MyOrderViewInterface extends BaseView<MyOrderPresenterInterface> {
        /**
         * 展示全部订单列表
         *
         * @param
         */
        void showMyOrderInfo(Object object);

        /**
         * 关闭订单
         *
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
        void loadMoreAll(List<OrderBuyerProList_listItem> list);

        /**
         * 全部订单更新
         */
        void refreshAll(OrderBuyerProList OrderBuyerProList);

        /**
         * 分类订单加载更多
         */
        void loadMoreFilter(List<OrderBuyerProFilterList_listItem> list);

        /**
         * 分类订单更新
         */
        void refreshFilter(OrderBuyerProFilterList orderBuyerProFilterList);

        /**
         * 异常订单加载更多
         */
        void loadMoreException(List<OrderBuyerProExceptionList_listItem> list);

        /**
         * 异常订单更新
         */
        void refreshException(OrderBuyerProExceptionList orderBuyerProExceptionList);

        /**
         * 确认收货
         */
        void orderReceive(int position);
    }

    interface MyOrderPresenterInterface extends BasePresenter {
        /**
         * 获取全部订单列表
         *
         * @param myRequestInfo
         */
        void getAllMyOrderData(MyRequestInfo myRequestInfo, int flag);

        /**
         * 获取未付款订单列表
         *
         * @param myRequestInfo
         */
        void getorderBuyerProFilterListData(MyRequestInfo myRequestInfo, int flag);

        /**
         * 获取待收货订单列表
         *
         * @param myRequestInfo
         */
        void getorderBuyerProExceptionListData(MyRequestInfo myRequestInfo, int flag);

        /**
         * 获取异常订单列表
         *
         * @param myRequestInfo
         */
        void getorderBuyerProClose(MyRequestInfo myRequestInfo);

    }
}
