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
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/8/25 0025.
 */

public class BusnessListPresenter implements BusnessListContrac.BusnessListPresenterInterface {
    private BusnessListContrac.BusnessListViewInterface mBusnessListView;
    private BusnessListModel busnessListModel;

    public BusnessListPresenter(BusnessListContrac.BusnessListViewInterface mBusnessListView) {
        if (null == mBusnessListView) {
            return;
        }
        this.mBusnessListView = mBusnessListView;
        this.mBusnessListView.setPresenter(this);
        busnessListModel = new BusnessListModel();
    }

    @Override
    public void getBusnessListData(MyRequestInfo myRequestInfo, final int flag) {
        busnessListModel.getBusnessListData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                if (0 == flag) {
                    mBusnessListView.showBusnessListData(((BusnessListInfo) ((ResponseBody) object).obj));
                } else if (1 == flag) {
                    mBusnessListView.refresh(((BusnessListInfo) ((ResponseBody) object).obj));

                } else if (2 == flag) {
                    mBusnessListView.loadMore(((BusnessListInfo) ((ResponseBody) object).obj).seller_list);
                }

            }

            @Override
            public void onFailed(String string) {

                mBusnessListView.showMessage(string);
            }
        });
    }

    @Override
    public void start() {

    }
}
