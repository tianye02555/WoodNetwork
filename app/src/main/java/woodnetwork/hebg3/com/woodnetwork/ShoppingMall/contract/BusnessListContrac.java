package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;

/**
 * Created by ty on 2016/8/25 0025.
 */

public interface BusnessListContrac {
    interface BusnessListViewInterface extends BaseView<BusnessListPresenterInterface>{
        void showBusnessListData(List<BusnessInfo> list);
    }
    interface BusnessListPresenterInterface extends BasePresenter{

    }
}
