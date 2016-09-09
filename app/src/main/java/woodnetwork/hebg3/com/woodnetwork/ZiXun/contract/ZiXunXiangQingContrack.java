package woodnetwork.hebg3.com.woodnetwork.ZiXun.contract;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.ArticleWebInfo;

/**
 * Created by ty on 2016/9/1 0001.
 */

public interface ZiXunXiangQingContrack {
    interface ZiXunXiangQingViewInterface extends BaseView<ZiXunXiangQingPresenterInterface> {
        /**
         * 加载uri
         * @param articleWebInfo
         */
        void showWebViewUri(ArticleWebInfo articleWebInfo);
    }
    interface ZiXunXiangQingPresenterInterface extends BasePresenter{
        /**
         * 获取uri
         * @param myRequestInfo
         */
        void getWebViewUri(MyRequestInfo myRequestInfo);
    }
}
