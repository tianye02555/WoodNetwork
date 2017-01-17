package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.presenter;

import android.app.Activity;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_busnessInfo;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_orderAdd;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.OrderAdd;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract.ConfirmOrderContrac;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.model.ConfirmOrderModel;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/8/27 0027.
 */

public class ConfirmOrderPresenter implements ConfirmOrderContrac.ConfirmOrderPresenterInterface {
    private ConfirmOrderContrac.ConfirmOrderViewInterface confirmOrderView;
    private ConfirmOrderModel confirmOrderModel;
    public ConfirmOrderPresenter(ConfirmOrderContrac.ConfirmOrderViewInterface confirmOrderView){
        if(null!=confirmOrderView){
            this.confirmOrderView=confirmOrderView;
        }
        this.confirmOrderView.setPresenter(this);
        confirmOrderModel= new ConfirmOrderModel();
    }
    @Override
    public void ConfirmOrder() {
//        confirmOrderModel.getOrderInfo();

    }

    @Override
    public void saveOrder(Request_orderAdd request_orderAdd) {
        confirmOrderView.showProgress();
        SharePreferencesUtils sharePreferencesUtils=SharePreferencesUtils.getSharePreferencesUtils((Activity)confirmOrderView);
        Request_getAttribute request_getAttribute=new Request_getAttribute();
        request_getAttribute.user_id=(String)sharePreferencesUtils.getData("userid","");

        confirmOrderModel.saveOrder(CommonUtils.getRequestInfo(request_orderAdd,request_getAttribute), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                confirmOrderView.closeProgress();
                confirmOrderView.jumpActivitywithAttribute((OrderAdd) ((ResponseBody)object).obj);
                confirmOrderView.showMessage(((ResponseBody)object).base.msg);
            }

            @Override
            public void onFailed(String string) {
                confirmOrderView.closeProgress();
                confirmOrderView.showMessage(string);
            }
        });
    }

    @Override
    public void start() {

    }
}
