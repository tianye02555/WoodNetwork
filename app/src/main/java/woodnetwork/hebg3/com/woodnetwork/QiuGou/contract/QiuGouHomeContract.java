package woodnetwork.hebg3.com.woodnetwork.QiuGou.contract;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.bean.DemandList;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;

/**
 * Created by ty on 2016/8/31 0031.
 */

public interface QiuGouHomeContract {
    interface QiuGouHomeViewInterface extends BaseView<QiuGouHomePresenterInterface>{
        void showQiuGouInfo(DemandList demandList);

    }
    interface QiuGouHomePresenterInterface extends BasePresenter{
        void getQiuGouData(MyRequestInfo myRequestInfo);
    }
}
