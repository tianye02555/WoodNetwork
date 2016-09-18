package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.ExceptionList;
import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;

/**
 * Created by ty on 2016/9/18 0018.
 */

public interface OrderExceptionContract {
    interface OrderExceptionViewInterface extends BaseView<OrderExceptionPresenterInterface>{
        /**
         * 展示异常信息
         * @param exceptionList
         */
        void showOrderExceptionInfo(ExceptionList exceptionList);
    }
    interface OrderExceptionPresenterInterface extends BasePresenter{
        /**
         * 获取订单异常列表信息
         * @param myRequestInfo
         */
        void getOrderExceptionList(MyRequestInfo myRequestInfo);
    }
}
