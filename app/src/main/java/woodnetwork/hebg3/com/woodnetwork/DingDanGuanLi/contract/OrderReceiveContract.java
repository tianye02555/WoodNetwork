package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract;

import android.view.View;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerInfo;
import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;

/**
 * Created by ty on 2016/9/18 0018.
 */

public interface OrderReceiveContract {
    interface OrderReceiveViewInterface extends BaseView<OrderReceivePresenterInterface>{
        /**
         * 获取edit的备注信息
         */
        String getBeiZhu();

        /**
         * 展示收获订单的信息
         */
        void showOrderInfo(OrderBuyerInfo orderBuyerInfo);

        /**
         * 添加收货图片
         */
        void addReceivePicture(View view);

    }
    interface OrderReceivePresenterInterface extends BasePresenter{
        /**
         * 提交收货信息接口
         * @param myRequestInfo
         */
        void submitReceiveOrder(MyRequestInfo myRequestInfo);
    }
}
