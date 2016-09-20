package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract;

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
    }
    interface DemOrderPresenterInterface extends BasePresenter {
        /**
         * 获取全部订单列表
         * @param myRequestInfo
         */
        void getAllDemOrderData(MyRequestInfo myRequestInfo);
        /**
         * 获取未付款订单列表
         * @param myRequestInfo
         */
        void getorderBuyerProFilterListData(MyRequestInfo myRequestInfo);
        /**
         * 获取待收货订单列表
         * @param myRequestInfo
         */
        void getorderBuyerProExceptionListData(MyRequestInfo myRequestInfo);
        /**
         * 获取异常订单列表
         * @param myRequestInfo
         */
        void getorderBuyerProClose(MyRequestInfo myRequestInfo);
    }
}
