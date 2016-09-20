package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.presenter;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract.DemOrderContract;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract.MyOrderContract;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model.DemOrderModel;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model.MyOrderModel;
import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/14 0014.
 */

public class DemOrderPresenter implements DemOrderContract.DemOrderPresenterInterface {
    private DemOrderContract.DemOrderViewInterface demOrderView;
    private DemOrderModel demOrderModel;
    public DemOrderPresenter(DemOrderContract.DemOrderViewInterface demOrderView) {
        if(null!=demOrderView){
            this.demOrderView=demOrderView;
        }
        this.demOrderView.setPresenter(this);
        demOrderModel=new DemOrderModel();
    }

    @Override
    public void getAllDemOrderData(MyRequestInfo myRequestInfo) {
        demOrderView.showProgress();
        demOrderModel.getAllDemOrderData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                demOrderView.closeProgress();
                demOrderView.showDemOrderInfo((OrderBuyerDemList)((((ResponseBody)object).obj)));
            }

            @Override
            public void onFailed(String string) {
                demOrderView.showMessage(string);
            }
        });

    }

    @Override
    public void getorderBuyerProFilterListData(MyRequestInfo myRequestInfo) {
        demOrderView.showProgress();
        demOrderModel.getorderBuyerProFilterListData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                demOrderView.closeProgress();
                demOrderView.showDemOrderInfo((OrderBuyerDemFilterList)((((ResponseBody)object).obj)));
            }

            @Override
            public void onFailed(String string) {
                demOrderView.showMessage(string);
            }
        });
    }

    @Override
    public void getorderBuyerProExceptionListData(MyRequestInfo myRequestInfo) {
        demOrderView.showProgress();
        demOrderModel.getorderBuyerProExceptionListData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                demOrderView.closeProgress();
                demOrderView.showDemOrderInfo((OrderBuyerDemExceptionList)((((ResponseBody)object).obj)));
            }

            @Override
            public void onFailed(String string) {
                demOrderView.showMessage(string);
            }
        });
    }

    @Override
    public void getorderBuyerProClose(MyRequestInfo myRequestInfo) {
        demOrderView.showProgress();
        demOrderModel.getorderBuyerProClose(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                demOrderView.closeProgress();
                demOrderView.showMessage(((((ResponseBody)object).base)).msg);
                demOrderView.refreshOrder();
            }

            @Override
            public void onFailed(String string) {
                demOrderView.closeProgress();
                demOrderView.showMessage(string);
            }
        });
    }

    @Override
    public void start() {

    }
}
