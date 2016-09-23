package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerInfo;
import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;

/**
 * Created by ty on 2016/9/18 0018.
 */

public interface OrderBuyerInfoContract {
    interface OrderBuyerInfoViewInterface extends BaseView<OrderBuyerInfoPresenterInterface>{
        /**
         * 展示订单详情
         */
        void showOrderInfo(Object object);
    }
    interface OrderBuyerInfoPresenterInterface extends BasePresenter{
        /**
         * 获取订单详情
         */
        void getOrderData(MyRequestInfo myRequestInfo);
        /**
         * 获取卖家订单详情接口
         */
        void getSellerOrderData(MyRequestInfo myRequestInfo);
    }
}
