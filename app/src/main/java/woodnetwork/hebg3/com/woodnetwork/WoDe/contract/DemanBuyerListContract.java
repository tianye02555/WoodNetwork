package woodnetwork.hebg3.com.woodnetwork.WoDe.contract;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.DemandBuyerList;

/**
 * Created by ty on 2016/9/7 0007.
 */

public interface DemanBuyerListContract {
    interface DemanBuyerListViewInterface extends BaseView<DemanBuyerListPresenterInterface>{
        /**
         * 设置我的求购信息列表
         * @param demanBuyerList
         */
        void setDemanBuyerListInfo(DemandBuyerList demanBuyerList);
    }
    interface DemanBuyerListPresenterInterface extends BasePresenter{
        /**
         * 获取我的求购信息列表信息
         * @param myRequestInfo
         */
        void getDemanBuyerListData(MyRequestInfo myRequestInfo);
    }
}
