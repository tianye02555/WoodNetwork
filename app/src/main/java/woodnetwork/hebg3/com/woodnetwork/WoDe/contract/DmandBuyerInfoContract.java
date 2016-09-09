package woodnetwork.hebg3.com.woodnetwork.WoDe.contract;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.DemandBuyerInfo;

/**
 * Created by ty on 2016/9/7 0007.
 */

public interface DmandBuyerInfoContract {
    interface DmandBuyerInfoViewInterface extends BaseView<DmandBuyerInfoPresenterInterface>{
        /**
         * 设置我的求购详情信息
         * @param dmandBuyerInfo
         */
        void setDmandBuyerInfo(DemandBuyerInfo dmandBuyerInfo);

    }
    interface DmandBuyerInfoPresenterInterface extends BasePresenter{
        /**
         *获取我的求购详情信息
         * @param myRequestInfo
         */
        void getDmandBuyerData(MyRequestInfo myRequestInfo);
    }
}
