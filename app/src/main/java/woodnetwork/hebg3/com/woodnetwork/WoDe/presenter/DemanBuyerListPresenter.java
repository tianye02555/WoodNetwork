package woodnetwork.hebg3.com.woodnetwork.WoDe.presenter;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.DemandBuyerList;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.DemanBuyerListContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.model.DemanBuyerListModel;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/7 0007.
 */

public class DemanBuyerListPresenter implements DemanBuyerListContract.DemanBuyerListPresenterInterface {
    private DemanBuyerListContract.DemanBuyerListViewInterface demanBuyerListView;
    private DemanBuyerListModel demanBuyerListModel;

    public DemanBuyerListPresenter(DemanBuyerListContract.DemanBuyerListViewInterface demanBuyerListView) {
        if (null != demanBuyerListView) {
            this.demanBuyerListView = demanBuyerListView;
        }
        this.demanBuyerListView.setPresenter(this);
        demanBuyerListModel = new DemanBuyerListModel();
    }

    @Override
    public void getDemanBuyerListData(MyRequestInfo myRequestInfo, final int flag) {
        if (0 == flag) {
            demanBuyerListView.showProgress();
        }
        demanBuyerListModel.getDemanBuyerListData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                if (0 == flag) {
                    demanBuyerListView.closeProgress();
                    demanBuyerListView.setDemanBuyerListInfo((DemandBuyerList) ((ResponseBody) object).obj);
                } else if (1 == flag) {
                    demanBuyerListView.refresh(((DemandBuyerList) ((ResponseBody) object).obj));

                } else if (2 == flag) {
                    demanBuyerListView.loadMore(((DemandBuyerList) ((ResponseBody) object).obj).list);
                }


            }

            @Override
            public void onFailed(String string) {
                demanBuyerListView.closeProgress();
                demanBuyerListView.showMessage(string);
            }
        });

    }

    @Override
    public void start() {

    }
}
