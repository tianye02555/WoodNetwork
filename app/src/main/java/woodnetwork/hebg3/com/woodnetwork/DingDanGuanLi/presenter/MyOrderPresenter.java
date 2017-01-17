package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.presenter;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProPayList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract.MyOrderContract;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model.MyOrderModel;
import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
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
        if (null != myOrderView) {
            this.myOrderView = myOrderView;
        }
        this.myOrderView.setPresenter(this);
        myOrderModel = new MyOrderModel();
    }

    @Override
    public void getAllMyOrderData(MyRequestInfo myRequestInfo, final int flag) {
        if (0 == flag) {
            myOrderView.showProgress();
        }

        myOrderModel.getAllMyOrderData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {

                    if (0 == flag) {
                        myOrderView.closeProgress();
                        myOrderView.showMyOrderInfo((OrderBuyerProList) ((((ResponseBody) object).obj)));
                    } else if (1 == flag) {
                        myOrderView.refreshAll(((OrderBuyerProList) ((ResponseBody) object).obj));

                    } else if (2 == flag) {
                        myOrderView.loadMoreAll(((OrderBuyerProList) ((ResponseBody) object).obj).list);
                    }

            }

            @Override
            public void onFailed(String string) {
                myOrderView.closeProgress();
                myOrderView.showMessage(string);
            }
        });

    }

    @Override
    public void getorderBuyerProFilterListData(MyRequestInfo myRequestInfo, final int flag) {
        if (0 == flag) {
            myOrderView.showProgress();
        }
        myOrderModel.getorderBuyerProFilterListData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                if (0 == flag) {
                    myOrderView.closeProgress();
                    myOrderView.showMyOrderInfo((OrderBuyerProFilterList) ((((ResponseBody) object).obj)));
                } else if (1 == flag) {
                    myOrderView.refreshFilter(((OrderBuyerProFilterList) ((ResponseBody) object).obj));

                } else if (2 == flag) {
                    myOrderView.loadMoreFilter(((OrderBuyerProFilterList) ((ResponseBody) object).obj).list);
                }
            }

            @Override
            public void onFailed(String string) {
                myOrderView.closeProgress();
                myOrderView.showMessage(string);
            }
        });
    }

    @Override
    public void getorderBuyerProExceptionListData(MyRequestInfo myRequestInfo, final int flag) {
        if (0 == flag) {
            myOrderView.showProgress();
        }
        myOrderModel.getorderBuyerProExceptionListData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                if (0 == flag) {
                    myOrderView.closeProgress();
                    myOrderView.showMyOrderInfo((OrderBuyerProExceptionList) ((((ResponseBody) object).obj)));
                } else if (1 == flag) {
                    myOrderView.refreshException(((OrderBuyerProExceptionList) ((ResponseBody) object).obj));

                } else if (2 == flag) {
                    myOrderView.loadMoreException(((OrderBuyerProExceptionList) ((ResponseBody) object).obj).list);
                }


            }

            @Override
            public void onFailed(String string) {
                myOrderView.closeProgress();
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
                myOrderView.showMessage(((((ResponseBody) object).base)).msg);
                myOrderView.refreshOrder();
            }

            @Override
            public void onFailed(String string) {
                myOrderView.closeProgress();
                myOrderView.showMessage(string);
            }
        });
    }

    @Override
    public void getOrderBuyerProPaidListData(MyRequestInfo myRequestInfo, final int flag) {
        if (0 == flag) {
            myOrderView.showProgress();
        }
        myOrderModel.getOrderBuyerProPaidListData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                if (0 == flag) {
                    myOrderView.closeProgress();
                    myOrderView.showMyOrderInfo((OrderBuyerProPayList) ((((ResponseBody) object).obj)));
                } else if (1 == flag) {
                    myOrderView.refreshPay(((OrderBuyerProPayList) ((ResponseBody) object).obj));
                } else if (2 == flag) {
                    myOrderView.loadMorePay(((OrderBuyerProPayList) ((ResponseBody) object).obj).list);
                }

            }

            @Override
            public void onFailed(String string) {
                myOrderView.closeProgress();
                myOrderView.showMessage(string);
            }
        });
    }


    @Override
    public void start() {

    }
}
