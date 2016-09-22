package woodnetwork.hebg3.com.woodnetwork.WoDe.contract;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.QuotationList;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.QuotationList_quotationItem;

/**
 * Created by ty on 2016/9/6 0006.
 */

public interface MyQuotationContract {
    interface MyQuotationViewInterface extends BaseView<MyQuotationPresenterInterface>{
        /**
         * 设置我的报价列表数据
         * @param quotationList
         */
        void setMyQuotationInfo(QuotationList quotationList);
        /**
         * 加载更多
         */
        void loadMore(List<QuotationList_quotationItem> list);
        /**
         * 更新
         */
        void refresh(QuotationList quotationList);

    }
    interface MyQuotationPresenterInterface extends BasePresenter{
        /**
         * 获取我的报价列表数据
         * @param myRequestInfo
         */
        void getMyQuotationData(MyRequestInfo myRequestInfo,int flag);
    }
}
