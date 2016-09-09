package woodnetwork.hebg3.com.woodnetwork.WoDe.presenter;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.DemandBuyerInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.DmandBuyerInfoContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.model.DmandBuyerInfoModel;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/7 0007.
 */

public class DemandBuyerInfoPresenter implements DmandBuyerInfoContract.DmandBuyerInfoPresenterInterface {
    private DmandBuyerInfoContract.DmandBuyerInfoViewInterface dmandBuyerInfoView;
    private DmandBuyerInfoModel dmandBuyerInfoModel;
    public DemandBuyerInfoPresenter(DmandBuyerInfoContract.DmandBuyerInfoViewInterface dmandBuyerInfoView) {
        if(null!=dmandBuyerInfoView){
            this.dmandBuyerInfoView=dmandBuyerInfoView;
        }
        this.dmandBuyerInfoView.setPresenter(this);
        dmandBuyerInfoModel=new DmandBuyerInfoModel();
    }

    @Override
    public void getDmandBuyerData(MyRequestInfo myRequestInfo) {
        dmandBuyerInfoView.showProgress();
        dmandBuyerInfoModel.getDmandBuyerData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                dmandBuyerInfoView.closeProgress();
                dmandBuyerInfoView.setDmandBuyerInfo((DemandBuyerInfo) ((ResponseBody)object).obj);
            }

            @Override
            public void onFailed(String string) {
                dmandBuyerInfoView.closeProgress();
                dmandBuyerInfoView.showMessage(string);
            }
        });
    }

    @Override
    public void start() {

    }
}
