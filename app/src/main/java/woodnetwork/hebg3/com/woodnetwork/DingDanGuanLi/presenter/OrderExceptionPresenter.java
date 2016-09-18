package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.presenter;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.ExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract.OrderExceptionContract;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model.OrderExceptionModel;
import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/18 0018.
 */

public class OrderExceptionPresenter implements OrderExceptionContract.OrderExceptionPresenterInterface {
    private OrderExceptionContract.OrderExceptionViewInterface orderExceptionView;
    private OrderExceptionModel orderExceptionModel;
    public OrderExceptionPresenter(OrderExceptionContract.OrderExceptionViewInterface orderExceptionView) {
        if(null!=orderExceptionView){
            this.orderExceptionView=orderExceptionView;
        }
        orderExceptionModel=new OrderExceptionModel();
    }

    @Override
    public void getOrderExceptionList(MyRequestInfo myRequestInfo) {
        orderExceptionView.showProgress();
        orderExceptionModel.getOrderExceptionList(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                orderExceptionView.closeProgress();
                orderExceptionView.showOrderExceptionInfo((ExceptionList) ((ResponseBody)object).obj);
            }

            @Override
            public void onFailed(String string) {
                orderExceptionView.closeProgress();
                orderExceptionView.showMessage(string);
            }
        });

    }

    @Override
    public void start() {

    }
}
