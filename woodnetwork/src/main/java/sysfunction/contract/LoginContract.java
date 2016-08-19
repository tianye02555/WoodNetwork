package sysfunction.contract;

import android.app.Activity;

import woodnetwork.hebg3.com.woodnetwork.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.BaseView;

/**
 * Created by ty on 2016/8/15 0015.
 */

public interface LoginContract {

    interface View extends BaseView<Presenter>{
        void getUserName();
        void getPassword();
        void clearUserNameAndPassword();
        void jumpActivity(Activity activity);

    }

    interface Presenter extends BasePresenter{

    }
}
