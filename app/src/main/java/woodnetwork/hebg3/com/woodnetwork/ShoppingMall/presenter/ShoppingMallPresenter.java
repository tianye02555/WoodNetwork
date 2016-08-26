package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.presenter;

import android.support.v4.app.Fragment;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_busnessList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_shoppingMall_woodsList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductFilterList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductFilterList_productsItem;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.WoodFilterAttribute;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract.ShoppingMallContract;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.model.ShoppingMallModel;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/8/24 0024.
 */

public class ShoppingMallPresenter implements ShoppingMallContract.ShoppingMallPresenter {
    private ShoppingMallContract.ShoppingMallView shoppingMallView;
    private ShoppingMallModel shoppingMallModel;
    public ShoppingMallPresenter(ShoppingMallContract.ShoppingMallView view){
        if(null==view){
            return;
        }
        this.shoppingMallView=view;
        this.shoppingMallView.setPresenter(this);
        shoppingMallModel=new ShoppingMallModel();

    }

    @Override
    public void getMoreBusiness() {

    }

    @Override
    public void getWoodsList(Request_shoppingMall_woodsList request_shoppingMall_woodsList) {
        shoppingMallView.showProgress();
        SharePreferencesUtils sharePreferencesUtils=SharePreferencesUtils.getSharePreferencesUtils(((Fragment)shoppingMallView).getActivity());
        Request_getAttribute request_getAttribute=new Request_getAttribute();
        request_getAttribute.user_id=(String)sharePreferencesUtils.getData("userid","");

        shoppingMallModel.getGoodsData(CommonUtils.getRequestInfo(request_shoppingMall_woodsList, request_getAttribute), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                shoppingMallView.closeProgress();
                shoppingMallView.showGoodsData(((ProductFilterList)((ResponseBody)object).obj).products);
            }

            @Override
            public void onFailed(String string) {
                shoppingMallView.closeProgress();
                shoppingMallView.showfailMessage(string);
            }
        });


    }

    @Override
    public void start() {
        SharePreferencesUtils sharePreferencesUtils=SharePreferencesUtils.getSharePreferencesUtils(((Fragment)shoppingMallView).getActivity());
        Request_getAttribute request_getAttribute=new Request_getAttribute();
        request_getAttribute.user_id=(String)sharePreferencesUtils.getData("userid","");

        shoppingMallModel.getSpinnerData(CommonUtils.getRequestInfo(new Object(),request_getAttribute), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                shoppingMallView.showSpinnerData(((WoodFilterAttribute)((ResponseBody)object).obj).attribute);
            }

            @Override
            public void onFailed(String string) {

            }
        });
        Request_busnessList request_busnessList=new Request_busnessList();
        request_busnessList.page_size=4;
        request_busnessList.page_no=1;
        shoppingMallModel.getBusinessData(CommonUtils.getRequestInfo(request_busnessList,request_getAttribute), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                shoppingMallView.showBusnessInfo(((BusnessListInfo)((ResponseBody)object).obj).seller_list);
            }
            @Override
            public void onFailed(String string) {

            }
        });

    }
}
