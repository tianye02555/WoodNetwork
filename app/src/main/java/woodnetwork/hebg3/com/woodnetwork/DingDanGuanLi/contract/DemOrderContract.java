package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemExceptionList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemFilterList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProExceptionList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProFilterList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProList_listItem;
import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;

/**
 * Created by ty on 2016/9/20 0020.
 */

public interface DemOrderContract {
    interface DemOrderViewInterface extends BaseView<DemOrderContract.DemOrderPresenterInterface> {
        /**
         * 展示全部订单列表
         * @param
         */
        void showDemOrderInfo(Object object);

        /**
         * 关闭订单
         * @param
         */
        void closeOrder(String oid,int position);

        /**
         * 更新订单数据
         */
        void refreshOrder();
        /**
         * 全部订单加载更多
         */
        void loadMoreAll(List<OrderBuyerDemList_listItem> list);
        /**
         * 全部订单更新
         */
        void refreshAll(OrderBuyerDemList orderBuyerDemList);
        /**
         * 分类订单加载更多
         */
        void loadMoreFilter(List<OrderBuyerDemFilterList_listItem> list);
        /**
         * 分类订单更新
         */
        void refreshFilter(OrderBuyerDemFilterList orderBuyerDemFilterList);
        /**
         * 异常订单加载更多
         */
        void loadMoreException(List<OrderBuyerDemExceptionList_listItem> list);
        /**
         * 异常订单更新
         */
        void refreshException(OrderBuyerDemExceptionList orderBuyerDemExceptionList);
    }
    interface DemOrderPresenterInterface extends BasePresenter {
        /**
         * 获取全部订单列表
         * @param myRequestInfo
         */
        void getAllDemOrderData(MyRequestInfo myRequestInfo,int flag);
        /**
         * 获取未付款订单列表
         * @param myRequestInfo
         */
        void getorderBuyerDemFilterListData(MyRequestInfo myRequestInfo,int flag);
        /**
         * 获取待收货订单列表
         * @param myRequestInfo
         */
        void getorderBuyerDemExceptionListData(MyRequestInfo myRequestInfo,int flag);
        /**
         * 获取异常订单列表
         * @param myRequestInfo
         */
        void getorderBuyerDemClose(MyRequestInfo myRequestInfo);
    }
}
