package woodnetwork.hebg3.com.woodnetwork.WoDe.presenter;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.GuestbookTypeList;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.AdviceContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.model.AdviceModel;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/6 0006.
 */

public class AdvicePresenter implements AdviceContract.AdvicePresenterInterface{
    private AdviceContract.AdviceViewInterface adviceView;
    private AdviceModel adviceModel;
    public AdvicePresenter(AdviceContract.AdviceViewInterface adviceView) {
        if(null!=adviceView){
            this.adviceView=adviceView;
        }
        this.adviceView.setPresenter(this);
        this.adviceModel=new AdviceModel();
    }
    @Override
    public void submitData(MyRequestInfo myRequestInfo) {
        adviceView.showProgress();
        adviceModel.submitData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                adviceView.closeProgress();
                adviceView.showMessage(((ResponseBody)object).base.msg);
            }

            @Override
            public void onFailed(String string) {
                adviceView.closeProgress();
                adviceView.showMessage(string);
            }
        });
    }

    @Override
    public void getGuestbookTypeData(MyRequestInfo myRequestInfo) {
        adviceView.showProgress();
        adviceModel.getGuestbookTypeData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                adviceView.closeProgress();
                adviceView.setGuestbookTypeData((GuestbookTypeList) (((ResponseBody)object).obj));
            }

            @Override
            public void onFailed(String string) {
                adviceView.closeProgress();
                adviceView.showMessage(string);
            }
        });
    }

    @Override
    public void start() {

    }
}
