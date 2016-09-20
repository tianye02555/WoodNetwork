package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.presenter;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.ExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract.OrderExceptionContract;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract.OrderReceiveContract;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model.OrderExceptionModel;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model.OrderReceiveModel;
import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/18 0018.
 */

public class OrderReceivePresenter implements OrderReceiveContract.OrderReceivePresenterInterface {
    private OrderReceiveContract.OrderReceiveViewInterface orderReceiveView;
    private OrderReceiveModel orderExceptionModel;
    public OrderReceivePresenter(OrderReceiveContract.OrderReceiveViewInterface orderReceiveView) {
        if(null!=orderReceiveView){
            this.orderReceiveView=orderReceiveView;
        }
        this.orderReceiveView.setPresenter(this);
        orderExceptionModel=new OrderReceiveModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void submitReceiveOrder(MyRequestInfo myRequestInfo) {
        orderReceiveView.showProgress();
        orderExceptionModel.submitReceiveOrder(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                orderReceiveView.closeProgress();
                orderReceiveView.showMessage((((ResponseBody)object).base).msg);
            }

            @Override
            public void onFailed(String string) {
                orderReceiveView.closeProgress();
                orderReceiveView.showMessage(string);
            }
        });

    }
}
