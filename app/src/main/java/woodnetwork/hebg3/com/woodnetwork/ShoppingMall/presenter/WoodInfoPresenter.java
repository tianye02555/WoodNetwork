package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.presenter;

import android.app.Activity;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_woodInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductFilterList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract.WoodInfoContrac;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.model.WoodInfoModel;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/8/25 0025.
 */

public class WoodInfoPresenter implements WoodInfoContrac.WoodInfoPresenterInterface {
    private WoodInfoContrac.WoodInfoViewInterFace woodInfoView;
    private WoodInfoModel woodInfoModel;
    public WoodInfoPresenter(WoodInfoContrac.WoodInfoViewInterFace woodInfoView){
        if(null!=woodInfoView){
            this.woodInfoView=woodInfoView;
        }
        this.woodInfoView.setPresenter(this);
        woodInfoModel=new WoodInfoModel();
    }
    @Override
    public void addToShoppingCart() {

    }

    @Override
    public void buy() {

    }

    @Override
    public void toShoppingCart() {

    }

    @Override
    public void getWoodInfo(String pid) {
        woodInfoView.showProgress();
        SharePreferencesUtils sharePreferencesUtils=SharePreferencesUtils.getSharePreferencesUtils((Activity)woodInfoView);
        Request_getAttribute request_getAttribute=new Request_getAttribute();
        request_getAttribute.user_id=(String)sharePreferencesUtils.getData("userid","");

        Request_woodInfo request_woodInfo=new Request_woodInfo();
        request_woodInfo.pid=pid;
        woodInfoModel.getWoodData(CommonUtils.getRequestInfo(request_woodInfo,request_getAttribute), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                woodInfoView.closeProgress();
                woodInfoView.showWoodData((ProductInfo)((ResponseBody)object).obj);
            }

            @Override
            public void onFailed(String string) {
                woodInfoView.closeProgress();
                woodInfoView.showfailMessage(string);
            }
        });

    }

    @Override
    public void start() {

    }
}
