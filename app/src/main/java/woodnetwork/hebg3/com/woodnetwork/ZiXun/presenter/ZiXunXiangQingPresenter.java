package woodnetwork.hebg3.com.woodnetwork.ZiXun.presenter;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.ArticleWebInfo;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.contract.ZiXunXiangQingContrack;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.model.ZiXunXiangQingModel;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/1 0001.
 */

public class ZiXunXiangQingPresenter implements ZiXunXiangQingContrack.ZiXunXiangQingPresenterInterface {
    private ZiXunXiangQingContrack.ZiXunXiangQingViewInterface ziXunXiangQingView;
    private ZiXunXiangQingModel ziXunXiangQingModel;
    public ZiXunXiangQingPresenter(ZiXunXiangQingContrack.ZiXunXiangQingViewInterface ziXunXiangQingView){
        if(null!=ziXunXiangQingView){
            this.ziXunXiangQingView=ziXunXiangQingView;
        }
        this.ziXunXiangQingView.setPresenter(this);
        ziXunXiangQingModel=new ZiXunXiangQingModel();
    }
    @Override
    public void getWebViewUri(MyRequestInfo myRequestInfo) {
        ziXunXiangQingView.showProgress();
        ziXunXiangQingModel.getWebViewData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                ziXunXiangQingView.closeProgress();
                ziXunXiangQingView.showWebViewUri((ArticleWebInfo) ((ResponseBody)object).obj);
            }

            @Override
            public void onFailed(String string) {
                ziXunXiangQingView.closeProgress();
                ziXunXiangQingView.showMessage(string);
            }
        });
    }

    @Override
    public void start() {

    }
}
