package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.presenter;


import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract.ExceptionAddContract;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model.ExceptionAddModel;
import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/19 0019.
 */

public class ExceptionAddPresenter implements ExceptionAddContract.ExceptionAddPresenterInterface {
    private ExceptionAddContract.ExceptionAddViewInterface exceptionAddView;
    private ExceptionAddModel exceptionAddModel;
    public ExceptionAddPresenter(ExceptionAddContract.ExceptionAddViewInterface exceptionAddView) {
        if(null!=exceptionAddView){
            this.exceptionAddView=exceptionAddView;
        }
        this.exceptionAddView.setPresenter(this);
        exceptionAddModel=new ExceptionAddModel();
    }

    @Override
    public void submitExceptionOrder(MyRequestInfo myRequestInfo) {
        exceptionAddView.showProgress();
        exceptionAddModel.submitExceptionOrder(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                exceptionAddView.closeProgress();
                exceptionAddView.showMessage((((ResponseBody)object).base).msg);
            }

            @Override
            public void onFailed(String string) {
                exceptionAddView.closeProgress();
                exceptionAddView.showMessage(string);
            }
        });
    }

    @Override
    public void start() {

    }
}
