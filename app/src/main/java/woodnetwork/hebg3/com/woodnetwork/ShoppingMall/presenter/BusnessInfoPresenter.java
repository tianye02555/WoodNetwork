package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.presenter;

import android.app.Activity;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_busnessInfo;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract.BusnessInfoContrac;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.model.BusnessInfoModel;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/8/25 0025.
 */

public class BusnessInfoPresenter implements BusnessInfoContrac.BusnessInfoPresenterInterface {
    private BusnessInfoContrac.BusnessInfoViewInterface busnessInfoView;
    private BusnessInfoModel busnessInfoModel;
    public BusnessInfoPresenter(BusnessInfoContrac.BusnessInfoViewInterface busnessInfoView){
        if(null==busnessInfoView){
           return;
    }
        this.busnessInfoView=busnessInfoView;
        this.busnessInfoView.setPresenter(this);
        busnessInfoModel=new BusnessInfoModel();
    }
    @Override
    public void start() {

    }

    @Override
    public void getBussnessInfo(String sid) {
        busnessInfoView.showProgress();
        SharePreferencesUtils sharePreferencesUtils=SharePreferencesUtils.getSharePreferencesUtils((Activity)busnessInfoView);
        Request_getAttribute request_getAttribute=new Request_getAttribute();
        request_getAttribute.user_id=(String)sharePreferencesUtils.getData("userid","");

        Request_busnessInfo request_busnessInfo=new Request_busnessInfo();
        request_busnessInfo.sid=sid;

        busnessInfoModel.getBusnessInfo(CommonUtils.getRequestInfo(request_busnessInfo, request_getAttribute), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                busnessInfoView.closeProgress();
                busnessInfoView.showBusnessData((BusnessInfo)((ResponseBody)object).obj);
            }

            @Override
            public void onFailed(String string) {
                busnessInfoView.closeProgress();
                busnessInfoView.showfailMessage(string);
            }
        });


    }
}
