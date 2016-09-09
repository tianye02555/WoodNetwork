package woodnetwork.hebg3.com.woodnetwork.WoDe.presenter;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.UserInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.MyInformationContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.model.MyInformationModel;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/5 0005.
 */

public class MyInformationPresenter implements MyInformationContract.MyInformationPresenterInterface {
    private MyInformationContract.MyInformationViewInterface myInformationView;
    private MyInformationModel myInformationModel;
    public MyInformationPresenter(MyInformationContract.MyInformationViewInterface myInformationView){
        if(null!=myInformationView){
            this.myInformationView=myInformationView;
        }
        this.myInformationView.setPresenter(this);
        myInformationModel=new MyInformationModel();
    }
    @Override
    public void getMyInformationData(MyRequestInfo myRequestInfo) {
        myInformationView.showProgress();
        myInformationModel.getMyInformationData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                myInformationView.closeProgress();
                myInformationView.setMyInformationInfo((UserInfo) ((ResponseBody)object).obj);
            }

            @Override
            public void onFailed(String string) {
                myInformationView.closeProgress();
                myInformationView.showMessage(string);
            }
        });
    }

    @Override
    public void start() {

    }
}
