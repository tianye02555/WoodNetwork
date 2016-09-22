package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.presenter;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderSellerExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderSellerFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderSellerList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract.DemOrderContract;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract.SellerOrderContract;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model.DemOrderModel;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model.SellerOrderModel;
import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/14 0014.
 */

public class SellerOrderPresenter implements SellerOrderContract.SellerOrderPresenterInterface {
    private SellerOrderContract.SellerOrderViewInterface sellerOrderView;
    private SellerOrderModel sellerOrderModel;
    public SellerOrderPresenter(SellerOrderContract.SellerOrderViewInterface sellerOrderView) {
        if(null!=sellerOrderView){
            this.sellerOrderView=sellerOrderView;
        }
        this.sellerOrderView.setPresenter(this);
        sellerOrderModel=new SellerOrderModel();
    }

    @Override
    public void getAllSellerOrderData(MyRequestInfo myRequestInfo,final int flag) {
        if (0 == flag) {
            sellerOrderView.showProgress();
        }
        sellerOrderModel.getAllSellerOrderData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                if (0 == flag) {
                    sellerOrderView.closeProgress();
                    sellerOrderView.showSellerOrderInfo((OrderSellerList)((((ResponseBody)object).obj)));
                } else if (1 == flag) {
                    sellerOrderView.refreshAll(((OrderSellerList) ((ResponseBody) object).obj));

                } else if (2 == flag) {
                    sellerOrderView.loadMoreAll(((OrderSellerList) ((ResponseBody) object).obj).list);
                }
            }

            @Override
            public void onFailed(String string) {
                sellerOrderView.showMessage(string);
            }
        });

    }

    @Override
    public void getSellerFilterOrderData(MyRequestInfo myRequestInfo,final int flag) {
        if (0 == flag) {
            sellerOrderView.showProgress();
        }
        sellerOrderModel.getSellerFilterOrderData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                if (0 == flag) {
                    sellerOrderView.closeProgress();
                    sellerOrderView.showSellerOrderInfo((OrderSellerFilterList)((((ResponseBody)object).obj)));
                } else if (1 == flag) {
                    sellerOrderView.refreshFilter(((OrderSellerFilterList) ((ResponseBody) object).obj));

                } else if (2 == flag) {
                    sellerOrderView.loadMoreFilter(((OrderSellerFilterList) ((ResponseBody) object).obj).list);
                }

            }

            @Override
            public void onFailed(String string) {
                sellerOrderView.showMessage(string);
            }
        });
    }

    @Override
    public void getSellerOrderExceptionListData(MyRequestInfo myRequestInfo,final int flag) {
        if (0 == flag) {
            sellerOrderView.showProgress();
        }
        sellerOrderModel.getSellerOrderExceptionListData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                if (0 == flag) {
                    sellerOrderView.closeProgress();
                    sellerOrderView.showSellerOrderInfo((OrderSellerExceptionList)((((ResponseBody)object).obj)));
                } else if (1 == flag) {
                    sellerOrderView.refreshException(((OrderSellerExceptionList) ((ResponseBody) object).obj));

                } else if (2 == flag) {
                    sellerOrderView.loadMoreException(((OrderSellerExceptionList) ((ResponseBody) object).obj).list);
                }
            }

            @Override
            public void onFailed(String string) {
                sellerOrderView.showMessage(string);
            }
        });
    }

    @Override
    public void closecOrder(MyRequestInfo myRequestInfo) {
        sellerOrderView.showProgress();
        sellerOrderModel.closecOrder(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                sellerOrderView.closeProgress();
                sellerOrderView.showMessage(((((ResponseBody)object).base)).msg);
                sellerOrderView.refreshOrder();
            }

            @Override
            public void onFailed(String string) {
                sellerOrderView.closeProgress();
                sellerOrderView.showMessage(string);
            }
        });
    }

    @Override
    public void start() {

    }
}
