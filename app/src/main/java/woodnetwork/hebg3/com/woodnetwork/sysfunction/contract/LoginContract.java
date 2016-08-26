package woodnetwork.hebg3.com.woodnetwork.sysfunction.contract;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.sysfunction.model.LoginModel;

/**
 * Created by ty on 2016/8/15 0015.
 */

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        /**
         * 输入框中获取用户名
         *
         * @return 用户名
         */
        String getUserName();

        /**
         * @return 密码
         */
        String getPassword();

        /**
         * 清除输入框内容
         */
        void clearUserNameAndPassword();

        /**
         * 跳转页面
         *
         * @param mClass 跳转的页面类
         */
        void jumpActivity(Class mClass);

        /**
         * 显示progressbar
         */
        void showProgress();

        /**
         * 关闭progressbar
         */
        void closeProgress();

    }

    interface Presenter extends BasePresenter {
        /**
         * 登录
         */
        void login();
    }
}
