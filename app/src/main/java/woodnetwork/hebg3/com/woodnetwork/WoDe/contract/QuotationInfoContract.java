package woodnetwork.hebg3.com.woodnetwork.WoDe.contract;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.QuotationInfo;

/**
 * Created by ty on 2016/9/6 0006.
 */

public interface QuotationInfoContract {
    interface QuotationViewInterface extends BaseView<QuotationPresenterInterface>{
        /**
         * 设置报价详情
         * @param quotationInfo
         */
        void setQuotationInfo(QuotationInfo quotationInfo);
    }
    interface   QuotationPresenterInterface extends BasePresenter{
        /**
         * 获取报价详情数据
         * @param myRequestInfo
         */
        void getQuotationData(MyRequestInfo myRequestInfo);
    }

}
