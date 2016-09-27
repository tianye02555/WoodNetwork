package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.presenter;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemPayList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProPayList;
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
    public void getAllDemOrderData(MyRequestInfo myRequestInfo,final int flag) {
        if (0 == flag) {
            demOrderView.showProgress();
        }
        demOrderModel.getAllDemOrderData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                if (0 == flag) {
                    demOrderView.closeProgress();
                    demOrderView.showDemOrderInfo((OrderBuyerDemList)((((ResponseBody)object).obj)));
                } else if (1 == flag) {
                    demOrderView.refreshAll(((OrderBuyerDemList) ((ResponseBody) object).obj));

                } else if (2 == flag) {
                    demOrderView.loadMoreAll(((OrderBuyerDemList) ((ResponseBody) object).obj).list);
                }
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
    public void getorderBuyerDemFilterListData(MyRequestInfo myRequestInfo,final int flag) {
        if (0 == flag) {
            demOrderView.showProgress();
        }
        demOrderModel.getorderBuyerProFilterListData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                if (0 == flag) {
                    demOrderView.closeProgress();
                    demOrderView.showDemOrderInfo((OrderBuyerDemFilterList)((((ResponseBody)object).obj)));
                } else if (1 == flag) {
                    demOrderView.refreshFilter(((OrderBuyerDemFilterList) ((ResponseBody) object).obj));

                } else if (2 == flag) {
                    demOrderView.loadMoreFilter(((OrderBuyerDemFilterList) ((ResponseBody) object).obj).list);
                }
            }

            @Override
            public void onFailed(String string) {
                demOrderView.showMessage(string);
            }
        });
    }

    @Override
    public void getorderBuyerDemExceptionListData(MyRequestInfo myRequestInfo,final int flag) {
        if (0 == flag) {
            demOrderView.showProgress();
        }
        demOrderModel.getorderBuyerProExceptionListData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                if (0 == flag) {
                    demOrderView.closeProgress();
                    demOrderView.showDemOrderInfo((OrderBuyerDemExceptionList)((((ResponseBody)object).obj)));
                } else if (1 == flag) {
                    demOrderView.refreshException(((OrderBuyerDemExceptionList) ((ResponseBody) object).obj));

                } else if (2 == flag) {
                    demOrderView.loadMoreException(((OrderBuyerDemExceptionList) ((ResponseBody) object).obj).list);
                }

            }

            @Override
            public void onFailed(String string) {
                demOrderView.showMessage(string);
            }
        });
    }

    @Override
    public void getorderBuyerDemClose(MyRequestInfo myRequestInfo) {
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
    public void getOrderBuyerDemPaidListData(MyRequestInfo myRequestInfo, final int flag) {
        if (0 == flag) {
            demOrderView.showProgress();
        }
        demOrderModel.getOrderBuyerDemPaidListData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                if (0 == flag) {
                    demOrderView.closeProgress();
                    demOrderView.showDemOrderInfo((OrderBuyerDemPayList) ((((ResponseBody) object).obj)));
                } else if (1 == flag) {
                    demOrderView.refreshPay(((OrderBuyerDemPayList) ((ResponseBody) object).obj));
                } else if (2 == flag) {
                    demOrderView.loadMorePay(((OrderBuyerDemPayList) ((ResponseBody) object).obj).list);
                }

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
