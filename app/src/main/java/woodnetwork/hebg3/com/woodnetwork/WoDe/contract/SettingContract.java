package woodnetwork.hebg3.com.woodnetwork.WoDe.contract;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.VersionInfo;

/**
 * Created by ty on 2016/9/5 0005.
 */

public interface SettingContract {
    interface SettingViewInterface extends BaseView<SettingPresenterInterface>{
        void setCheckUpdateInfo(VersionInfo versionInfo);
    }
    interface SettingPresenterInterface extends BasePresenter{
        void getCheckUpdateData(MyRequestInfo myRequestInfo);
    }
}
