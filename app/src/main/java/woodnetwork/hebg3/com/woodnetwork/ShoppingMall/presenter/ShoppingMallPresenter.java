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
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.WoodFilterInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract.ShoppingMallContract;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.model.ShoppingMallModel;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
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
    public void getWoodsList(Request_shoppingMall_woodsList request_shoppingMall_woodsList) {
        shoppingMallView.showProgress();
        SharePreferencesUtils sharePreferencesUtils=SharePreferencesUtils.getSharePreferencesUtils(((Fragment)shoppingMallView).getActivity());
        Request_getAttribute request_getAttribute=new Request_getAttribute();
        request_getAttribute.user_id=(String)sharePreferencesUtils.getData("userid","");

        shoppingMallModel.getGoodsData(CommonUtils.getRequestInfo(request_shoppingMall_woodsList, request_getAttribute), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {

                shoppingMallView.showGoodsData(((ProductFilterList)((ResponseBody)object).obj).products);
            }

            @Override
            public void onFailed(String string) {
                shoppingMallView.closeProgress();
                shoppingMallView.showMessage(string);
            }
        });


    }

    @Override
    public void getAttributeFilterListData(MyRequestInfo myRequestInfo) {
        shoppingMallModel.getSpinnerData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                shoppingMallView.showAttributeFilterListInfo((WoodFilterAttribute) ((ResponseBody)object).obj);
            }

            @Override
            public void onFailed(String string) {
                shoppingMallView.showMessage(string);
            }
        });
    }

    @Override
    public void shopcarAdd(MyRequestInfo myRequestInfo) {
            shoppingMallModel.getShopcarAdd(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
                @Override
                public void onSuccess(Object object) {
                    shoppingMallView.showMessage(((ResponseBody)object).base.msg);
                }

                @Override
                public void onFailed(String string) {
                    shoppingMallView.showMessage(string);
                }
            });
    }

    @Override
    public void start() {

    }
}
