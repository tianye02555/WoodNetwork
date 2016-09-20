package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract;

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
    }
    interface SellerOrderPresenterInterface extends BasePresenter {
        /**
         * 获取全部订单列表
         * @param myRequestInfo
         */
        void getAllSellerOrderData(MyRequestInfo myRequestInfo);
        /**
         * 获取待收货订单列表
         * @param myRequestInfo
         */
        void getSellerFilterOrderData(MyRequestInfo myRequestInfo);
        /**
         * 获取异常订单列表
         * @param myRequestInfo
         */
        void getSellerOrderExceptionListData(MyRequestInfo myRequestInfo);
        /**
         * 获取异常订单列表
         * @param myRequestInfo
         */
        void closecOrder(MyRequestInfo myRequestInfo);
    }
}
