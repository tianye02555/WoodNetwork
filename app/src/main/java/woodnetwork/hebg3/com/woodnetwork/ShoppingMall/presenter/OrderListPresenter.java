package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.presenter;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.AccountBalanceInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract.OrderListContrac;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.model.OrderListModel;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/8/29 0029.
 */

public class OrderListPresenter implements OrderListContrac.OrderListPresenterInterface {
    private OrderListContrac.OrderListViewInterface orderListView;
    private OrderListModel orderListModel;
    public  OrderListPresenter(OrderListContrac.OrderListViewInterface orderListView){
        if(null!=orderListView){
            this.orderListView=orderListView;
        }
        this.orderListView.setPresenter(this);
        orderListModel=new OrderListModel();
    }
    @Override
    public void getYuE(MyRequestInfo myRequestInfo) {
        orderListView.showProgress();
        orderListModel.getYuEInfo(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                orderListView.closeProgress();
                orderListView.showYuE(((AccountBalanceInfo)((ResponseBody)object).obj).balance);
            }

            @Override
            public void onFailed(String string) {
                orderListView.closeProgress();
                orderListView.showfailMessage(string);
            }
        });
    }

    @Override
    public void start() {

    }
}
