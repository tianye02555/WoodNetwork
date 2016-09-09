package woodnetwork.hebg3.com.woodnetwork.WoDe.presenter;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.ReportsInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.ReportsContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.model.ReportsModel;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/6 0006.
 */

public class ReportsPresenter implements ReportsContract.ReportsPresenterInterface {
    private ReportsContract.ReportsViewInterface reportsView;
    private ReportsModel reportsModel;
    public ReportsPresenter(ReportsContract.ReportsViewInterface reportsView) {
        if(null!=reportsView){
            this.reportsView=reportsView;
        }
        this.reportsView.setPresenter(this);
        reportsModel=new ReportsModel();
    }

    @Override
    public void getReportsURL(MyRequestInfo myRequestInfo) {
        reportsView.showProgress();
        reportsModel.getReportsURL(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                reportsView.closeProgress();
                reportsView.showWebViewUri((ReportsInfo) ((ResponseBody)object).obj);
            }

            @Override
            public void onFailed(String string) {
                reportsView.closeProgress();
                reportsView.showMessage(string);
            }
        });
    }

    @Override
    public void start() {

    }
}
