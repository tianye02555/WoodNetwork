package woodnetwork.hebg3.com.woodnetwork.QiuGou.contract;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.bean.DemandList;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.bean.DemandList_listItem;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;

/**
 * Created by ty on 2016/8/31 0031.
 */

public interface QiuGouHomeContract {
    interface QiuGouHomeViewInterface extends BaseView<QiuGouHomePresenterInterface>{
        void showQiuGouInfo(DemandList demandList);
        /**
         * 加载更多
         */
        void loadMore(List<DemandList_listItem> list);
        /**
         * 更新
         */
        void refresh(DemandList demandList);

    }
    interface QiuGouHomePresenterInterface extends BasePresenter{
        void getQiuGouData(MyRequestInfo myRequestInfo,int flag);
    }
}
