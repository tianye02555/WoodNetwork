package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.presenter;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerInfo;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderSellerInfo;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract.OrderBuyerInfoContract;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model.OrderBuyerInfoModel;
import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/18 0018.
 */

public class OrderBuyerInfoPresenter implements OrderBuyerInfoContract.OrderBuyerInfoPresenterInterface {
    private OrderBuyerInfoContract.OrderBuyerInfoViewInterface orderBuyerInfoView;
    private OrderBuyerInfoModel orderBuyerInfoModel;
    public OrderBuyerInfoPresenter(OrderBuyerInfoContract.OrderBuyerInfoViewInterface orderBuyerInfoView) {
        if(null!=orderBuyerInfoView){
            this.orderBuyerInfoView=orderBuyerInfoView;
        }
        this.orderBuyerInfoView.setPresenter(this);
        this.orderBuyerInfoModel=new OrderBuyerInfoModel();
    }

    @Override
    public void getOrderData(MyRequestInfo myRequestInfo) {
        orderBuyerInfoView.showProgress();
        orderBuyerInfoModel.getOrderData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                orderBuyerInfoView.closeProgress();
                orderBuyerInfoView.showOrderInfo((OrderBuyerInfo) ((ResponseBody)object).obj);
            }

            @Override
            public void onFailed(String string) {
                orderBuyerInfoView.closeProgress();
                orderBuyerInfoView.showMessage(string);
            }
        });

    }

    @Override
    public void getSellerOrderData(MyRequestInfo myRequestInfo) {
        orderBuyerInfoView.showProgress();
        orderBuyerInfoModel.getSellerOrderData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                orderBuyerInfoView.closeProgress();
                orderBuyerInfoView.showSellerOrderInfo((OrderSellerInfo) ((ResponseBody)object).obj);
            }

            @Override
            public void onFailed(String string) {
                orderBuyerInfoView.closeProgress();
                orderBuyerInfoView.showMessage(string);
            }
        });
    }

    @Override
    public void start() {

    }
}
