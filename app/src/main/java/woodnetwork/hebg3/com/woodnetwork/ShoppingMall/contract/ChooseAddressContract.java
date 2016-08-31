package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.AreaList;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;

/**
 * Created by ty on 2016/8/29 0029.
 */

public interface ChooseAddressContract {
    interface ChooseAddressViewInterface extends BaseView<ChooseAddressPresenterInterface>{
//void showSpinnerInfo(AreaList areaList);
        void showNewSpinner(AreaList areaList);

    }
    interface ChooseAddressPresenterInterface extends BasePresenter{
        void getAreaData(MyRequestInfo myRequestInfo);
    }
}
