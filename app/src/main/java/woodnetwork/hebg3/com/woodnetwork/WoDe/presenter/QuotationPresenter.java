package woodnetwork.hebg3.com.woodnetwork.WoDe.presenter;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.QuotationInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.QuotationInfoContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.model.QuotationModel;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/6 0006.
 */

public class QuotationPresenter implements QuotationInfoContract.QuotationPresenterInterface {
    private QuotationInfoContract.QuotationViewInterface quotationView;
    private QuotationModel quotationModel;

    public QuotationPresenter(QuotationInfoContract.QuotationViewInterface quotationView) {
        if (null != quotationView) {
            this.quotationView = quotationView;
        }
        this.quotationView.setPresenter(this);
        quotationModel = new QuotationModel();
    }

    @Override
    public void getQuotationData(MyRequestInfo myRequestInfo) {
        quotationView.showProgress();
        quotationModel.getQuotationData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                quotationView.closeProgress();
                quotationView.setQuotationInfo((QuotationInfo) ((ResponseBody) object).obj);
            }

            @Override
            public void onFailed(String string) {
                quotationView.closeProgress();
                quotationView.showMessage(string);
            }
        });
    }

    @Override
    public void start() {

    }
}
