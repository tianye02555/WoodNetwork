package woodnetwork.hebg3.com.woodnetwork.WoDe.presenter;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.QuotationList;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.MyQuotationContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.model.MyQuotationModel;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/6 0006.
 */

public class MyQuotationPresenter implements MyQuotationContract.MyQuotationPresenterInterface {
    private MyQuotationContract.MyQuotationViewInterface myQuotationView;
    private MyQuotationModel myQuotationModel;

    public MyQuotationPresenter(MyQuotationContract.MyQuotationViewInterface myQuotationView) {
        if (null != myQuotationView) {
            this.myQuotationView = myQuotationView;
        }
        this.myQuotationView.setPresenter(this);
        myQuotationModel = new MyQuotationModel();
    }

    @Override
    public void getMyQuotationData(MyRequestInfo myRequestInfo, final int flag) {
        if (0 == flag) {
            myQuotationView.showProgress();
        }
        myQuotationModel.getMyQuotationData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                if (0 == flag) {
                    myQuotationView.closeProgress();
                    myQuotationView.setMyQuotationInfo((QuotationList) ((ResponseBody) object).obj);
                } else if (1 == flag) {
                    myQuotationView.refresh(((QuotationList) ((ResponseBody) object).obj));

                } else if (2 == flag) {
                    myQuotationView.loadMore(((QuotationList) ((ResponseBody) object).obj).quotation);
                }

            }

            @Override
            public void onFailed(String string) {
                myQuotationView.closeProgress();
                myQuotationView.showMessage(string);
            }
        });

    }

    @Override
    public void start() {

    }
}
