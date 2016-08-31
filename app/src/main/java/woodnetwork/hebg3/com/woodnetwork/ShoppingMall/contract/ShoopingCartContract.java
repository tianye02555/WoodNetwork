package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopcarList;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;

/**
 * Created by ty on 2016/8/30 0030.
 */

public interface ShoopingCartContract {
    interface ShoopingCartViewInterface extends BaseView<ShoopingCartPresenterInterface>{
        void showShoopingCartInfo(ShopcarList shopcarList);
        void deleteGoods();
        void submitOrder();
        void changeNumber();

    }
    interface ShoopingCartPresenterInterface extends BasePresenter{
      void  getShoopingCartData(MyRequestInfo myRequestInfo);
        void  changeGoodsNumber(MyRequestInfo myRequestInfo);
        void  deleteGoods(MyRequestInfo myRequestInfo);
    }
}
