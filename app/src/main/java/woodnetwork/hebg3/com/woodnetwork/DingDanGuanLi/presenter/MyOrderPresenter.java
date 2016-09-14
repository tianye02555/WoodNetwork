package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.presenter;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract.MyOrderContract;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model.MyOrderModel;
import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/14 0014.
 */

public class MyOrderPresenter implements MyOrderContract.MyOrderPresenterInterface {
    private MyOrderContract.MyOrderViewInterface myOrderView;
    private MyOrderModel myOrderModel;
    public MyOrderPresenter(MyOrderContract.MyOrderViewInterface myOrderView) {
        if(null!=myOrderView){
            this.myOrderView=myOrderView;
        }
        this.myOrderView.setPresenter(this);
        myOrderModel=new MyOrderModel();
    }

    @Override
    public void getAllMyOrderData(MyRequestInfo myRequestInfo) {
        myOrderView.showProgress();
        myOrderModel.getAllMyOrderData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                myOrderView.closeProgress();
                myOrderView.showMyOrderInfo((OrderBuyerProList)((((ResponseBody)object).obj)));
            }

            @Override
            public void onFailed(String string) {
                myOrderView.showMessage(string);
            }
        });

    }

    @Override
    public void getorderBuyerProFilterListData(MyRequestInfo myRequestInfo) {
        myOrderView.showProgress();
        myOrderModel.getorderBuyerProFilterListData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                myOrderView.closeProgress();
                myOrderView.showMyOrderInfo((OrderBuyerProFilterList)((((ResponseBody)object).obj)));
            }

            @Override
            public void onFailed(String string) {
                myOrderView.showMessage(string);
            }
        });
    }

    @Override
    public void getorderBuyerProExceptionListData(MyRequestInfo myRequestInfo) {
        myOrderView.showProgress();
        myOrderModel.getorderBuyerProExceptionListData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                myOrderView.closeProgress();
                myOrderView.showMyOrderInfo((OrderBuyerProExceptionList)((((ResponseBody)object).obj)));
            }

            @Override
            public void onFailed(String string) {
                myOrderView.showMessage(string);
            }
        });
    }

    @Override
    public void getorderBuyerProClose(MyRequestInfo myRequestInfo) {
        myOrderView.showProgress();
        myOrderModel.getorderBuyerProClose(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                myOrderView.closeProgress();
                myOrderView.showMessage(((((ResponseBody)object).base)).msg);
                myOrderView.refreshOrder();
            }

            @Override
            public void onFailed(String string) {
                myOrderView.showMessage(string);
            }
        });
    }

    @Override
    public void start() {

    }
}
