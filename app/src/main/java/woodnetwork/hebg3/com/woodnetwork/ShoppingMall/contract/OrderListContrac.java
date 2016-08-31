package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.OrderAdd;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;

/**
 * Created by ty on 2016/8/29 0029.
 */

public interface OrderListContrac {
    interface OrderListViewInterface extends BaseView<OrderListPresenterInterface>{
        void showOrderData(OrderAdd orderAdd);
        void showYuE(Double mDouble);

    }
    interface OrderListPresenterInterface extends BasePresenter{
        void getYuE(MyRequestInfo myRequestInfo);
    }
}
