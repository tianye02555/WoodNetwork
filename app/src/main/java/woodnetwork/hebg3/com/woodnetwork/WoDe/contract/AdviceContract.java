package woodnetwork.hebg3.com.woodnetwork.WoDe.contract;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.GuestbookTypeList;

/**
 * Created by ty on 2016/9/6 0006.
 */

public interface AdviceContract {
    interface AdviceViewInterface extends BaseView<AdvicePresenterInterface>{
        /**
         * 获取用户输入的信息
         */
        void getAdviceInfo();

        /**
         * 设置建议分类信息
         * @param guestbookTypeList
         */
        void setGuestbookTypeData(GuestbookTypeList guestbookTypeList);

        /**
         * 关闭页面
         */
        void finish();

    }
    interface AdvicePresenterInterface extends BasePresenter{
        /**
         * 提交用户建议信息
         */
        void submitData(MyRequestInfo myRequestInfo);

        /**
         * 获取建议分类信息
         */
        void getGuestbookTypeData(MyRequestInfo myRequestInfo);
    }
}
