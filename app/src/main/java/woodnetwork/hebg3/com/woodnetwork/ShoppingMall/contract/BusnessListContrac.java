package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductFilterList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductFilterList_productsItem;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;

/**
 * Created by ty on 2016/8/25 0025.
 */

public interface BusnessListContrac {
    interface BusnessListViewInterface extends BaseView<BusnessListPresenterInterface>{
        void showBusnessListData(BusnessListInfo busnessListInfo);
        /**
         * 加载更多
         */
        void loadMore(List<BusnessInfo> list);
        /**
         * 更新
         */
        void refresh(BusnessListInfo busnessListInfo);
    }
    interface BusnessListPresenterInterface extends BasePresenter{
      void  getBusnessListData(MyRequestInfo myRequestInfo,int flag);
    }
}
