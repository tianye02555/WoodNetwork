package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.presenter;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopcarList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract.ShoopingCartContract;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.model.ShoopingCartModel;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/8/30 0030.
 */

public class ShoopingCartPresenter implements ShoopingCartContract.ShoopingCartPresenterInterface {
    private ShoopingCartContract.ShoopingCartViewInterface shoopingCartView;
    private ShoopingCartModel shoopingCartModel;

    public ShoopingCartPresenter(ShoopingCartContract.ShoopingCartViewInterface shoopingCartView) {
        if (null != shoopingCartView) {
            this.shoopingCartView = shoopingCartView;
        }
        this.shoopingCartView.setPresenter(this);
        shoopingCartModel = new ShoopingCartModel();
    }

    @Override
    public void getShoopingCartData(MyRequestInfo myRequestInfo, final int flag) {
        if (0 == flag) {
            shoopingCartView.showProgress();
        }

        shoopingCartModel.getShoopingCartData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                shoopingCartView.closeProgress();
                if (0 == flag) {
                    shoopingCartView.showShoopingCartInfo((ShopcarList) ((ResponseBody) object).obj);
                } else if (1 == flag) {
                    shoopingCartView.refresh(((ShopcarList) ((ResponseBody) object).obj));

                } else if (2 == flag) {
                    shoopingCartView.loadMore(((ShopcarList) ((ResponseBody) object).obj).list);
                }


            }

            @Override
            public void onFailed(String string) {
                shoopingCartView.closeProgress();
                shoopingCartView.showMessage(string);
            }
        });
    }

    @Override
    public void changeGoodsNumber(MyRequestInfo myRequestInfo, final int position) {
//        shoopingCartView.showProgress();
        shoopingCartModel.changeGoodsNumber(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
//                shoopingCartView.closeProgress();
                shoopingCartView.changeNumber(position);
                shoopingCartView.showMessage(((ResponseBody)object).base.msg);
            }

            @Override
            public void onFailed(String string) {
//                shoopingCartView.closeProgress();
                shoopingCartView.showMessage(string);
            }
        });
    }

    @Override
    public void deleteGoods(MyRequestInfo myRequestInfo) {
        shoopingCartView.showProgress();
        shoopingCartModel.deleteGoods(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                shoopingCartView.closeProgress();
                shoopingCartView.deleteGoods();
            }

            @Override
            public void onFailed(String string) {
                shoopingCartView.closeProgress();
                shoopingCartView.showMessage(string);
            }
        });
    }

    @Override
    public void start() {

    }
}
