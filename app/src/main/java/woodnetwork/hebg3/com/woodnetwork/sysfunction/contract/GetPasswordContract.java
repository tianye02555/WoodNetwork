package woodnetwork.hebg3.com.woodnetwork.sysfunction.contract;

import woodnetwork.hebg3.com.woodnetwork.sysfunction.presenter.GetPasswordPresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;

/**
 * Created by ty on 2016/8/18 0018.
 */

public interface GetPasswordContract {
    interface GetPasswordView extends BaseView<GetPasswordPresenter>{
        String getUserName();
        String getCode();
        String getNewPassword();
        String getNewPasswordTwice();
        void sendCode();
        void showToastMessage(String message);
        void submitNewPassword();
        void closeActivity();
        void countDown();

    }
    interface GetPasswordPresenterInterface extends BasePresenter{
        void sendCode();
        void submit();
    }
}
