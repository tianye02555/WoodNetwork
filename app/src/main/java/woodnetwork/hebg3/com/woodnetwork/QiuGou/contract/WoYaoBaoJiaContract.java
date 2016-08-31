package woodnetwork.hebg3.com.woodnetwork.QiuGou.contract;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.bean.DemandInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;

/**
 * Created by ty on 2016/8/31 0031.
 */

public interface WoYaoBaoJiaContract {
    interface WoYaoBaoJiaViewInterface extends BaseView<WoYaoBaoJiaPresenterInterface>{
        void showWoYaoBaoJiaInfo(DemandInfo demandInfo);

    }
    interface WoYaoBaoJiaPresenterInterface extends BasePresenter{
        void getWoYaoBaoJiaData(MyRequestInfo myRequestInfo);
        void saveWoDeBaoJia(MyRequestInfo myRequestInfo);
    }
}
