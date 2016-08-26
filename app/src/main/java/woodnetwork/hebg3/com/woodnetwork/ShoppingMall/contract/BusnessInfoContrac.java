package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;

/**
 * Created by ty on 2016/8/25 0025.
 */

public interface BusnessInfoContrac {
    interface BusnessInfoViewInterface extends BaseView<BusnessInfoPresenterInterface>{
        void back();
        void showBusnessData(BusnessInfo busnessInfo);

    }
    interface BusnessInfoPresenterInterface extends BasePresenter{
        void getBussnessInfo(String sid);
    }
}

