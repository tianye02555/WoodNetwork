package woodnetwork.hebg3.com.woodnetwork.WoDe.contract;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.ReportsInfo;

/**
 * Created by ty on 2016/9/6 0006.
 */

public interface ReportsContract {
    interface ReportsViewInterface extends BaseView<ReportsPresenterInterface>{
        /**
         * 显示webview
         * @param reportsInfo
         */
        void showWebViewUri(ReportsInfo reportsInfo);
    }
    interface ReportsPresenterInterface extends BasePresenter{
        /**
         * 获取统计表URL
         * @param myRequestInfo
         */
        void getReportsURL(MyRequestInfo myRequestInfo);
    }
}
