package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.presenter;

import android.app.Activity;
import android.support.v4.app.Fragment;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_busnessList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract.BusnessListContrac;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.model.BusnessListModel;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/8/25 0025.
 */

public class BusnessListPresenter implements BusnessListContrac.BusnessListPresenterInterface {
    private BusnessListContrac.BusnessListViewInterface mBusnessListView;
    private BusnessListModel busnessListModel;
    public BusnessListPresenter(BusnessListContrac.BusnessListViewInterface mBusnessListView){
        if(null==mBusnessListView){
            return;
        }
        this.mBusnessListView=mBusnessListView;
        this.mBusnessListView.setPresenter(this);
        busnessListModel=new BusnessListModel();
    }
    @Override
    public void start() {

        SharePreferencesUtils sharePreferencesUtils=SharePreferencesUtils.getSharePreferencesUtils(((Fragment)mBusnessListView).getActivity());
        Request_getAttribute request_getAttribute=new Request_getAttribute();
        request_getAttribute.user_id=(String)sharePreferencesUtils.getData("userid","");

        Request_busnessList request_busnessList=new Request_busnessList();
        request_busnessList.page_size=10;
        request_busnessList.page_no=1;
        busnessListModel.getBusnessListData(CommonUtils.getRequestInfo(request_busnessList,request_getAttribute), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {

                mBusnessListView.showBusnessListData(((BusnessListInfo)((ResponseBody)object).obj).seller_list);
            }
            @Override
            public void onFailed(String string) {

                mBusnessListView.showMessage(string);
            }
        });
    }
}
