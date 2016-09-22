package woodnetwork.hebg3.com.woodnetwork.WoDe.contract;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.DemandBuyerList;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.DemandBuyerList_listItem;

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
        /**
         * 加载更多
         */
        void loadMore(List<DemandBuyerList_listItem> list);
        /**
         * 更新
         */
        void refresh(DemandBuyerList demanBuyerList);
    }
    interface DemanBuyerListPresenterInterface extends BasePresenter{
        /**
         * 获取我的求购信息列表信息
         * @param myRequestInfo
         */
        void getDemanBuyerListData(MyRequestInfo myRequestInfo,int flag);
    }
}
