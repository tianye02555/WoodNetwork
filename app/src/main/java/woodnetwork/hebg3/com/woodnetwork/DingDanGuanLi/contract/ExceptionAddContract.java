package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract;

import android.view.View;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;

/**
 * Created by ty on 2016/9/18 0018.
 */

public interface ExceptionAddContract {
    interface ExceptionAddViewInterface extends BaseView<ExceptionAddPresenterInterface>{
        /**
         * 获取edit的异常原因
         */
        void getYiChangYuanYin();

        /**
         * 展示异常订单的信息
         */
        void showOrderExceptionInfo();

        /**
         * 添加异常图片
         */
        void addExceptionPicture(View view);

    }
    interface ExceptionAddPresenterInterface extends BasePresenter{
        /**
         * 提交异常信息接口
         * @param myRequestInfo
         */
        void submitExceptionOrder(MyRequestInfo myRequestInfo);
    }
}
