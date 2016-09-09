package woodnetwork.hebg3.com.woodnetwork.WoDe.contract;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.UserInfo;

/**
 * Created by ty on 2016/9/5 0005.
 */

public interface MyInformationContract {
    interface MyInformationViewInterface extends BaseView<MyInformationPresenterInterface>{
        void setMyInformationInfo(UserInfo userInfo);
    }
    interface MyInformationPresenterInterface extends BasePresenter{
        void getMyInformationData(MyRequestInfo myRequestInfo);
    }
}
