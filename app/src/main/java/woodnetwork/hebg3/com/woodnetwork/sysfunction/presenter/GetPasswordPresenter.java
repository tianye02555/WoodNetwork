package woodnetwork.hebg3.com.woodnetwork.sysfunction.presenter;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getCode;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getPassword;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;
import woodnetwork.hebg3.com.woodnetwork.sysfunction.contract.GetPasswordContract;
import woodnetwork.hebg3.com.woodnetwork.sysfunction.model.GetPasswordModel;

/**
 * Created by ty on 2016/8/18 0018.
 */

public class GetPasswordPresenter implements GetPasswordContract.GetPasswordPresenterInterface {
    private GetPasswordContract.GetPasswordView mGetPasswordView;
    private GetPasswordModel getPasswordModel;

    public GetPasswordPresenter(GetPasswordContract.GetPasswordView mGetPasswordView) {
        if(null==mGetPasswordView){
            return;
        }
        this.mGetPasswordView=mGetPasswordView;
        this.mGetPasswordView.setPresenter(this);
        getPasswordModel=new GetPasswordModel();


    }

    @Override
    public void sendCode() {
        mGetPasswordView.showProgress();
        Request_getPassword request_getPassword=new Request_getPassword();
        request_getPassword.name=mGetPasswordView.getUserName();
        request_getPassword.auth_code=mGetPasswordView.getCode();
        request_getPassword.password=mGetPasswordView.getNewPassword();
        getPasswordModel.sendCode(CommonUtils.getRequestInfo(request_getPassword,new Object()), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                mGetPasswordView.closeProgress();
                mGetPasswordView.showToastMessage(((ResponseBody)object).base.msg);
//                mGetPasswordView.closeActivity();
            }

            @Override
            public void onFailed(String string) {
                mGetPasswordView.closeProgress();
                mGetPasswordView.showMessage(string);
            }
        });
    }

    @Override
    public void submit() {
        mGetPasswordView.showProgress();
        Request_getCode request_getCode=new Request_getCode();
        request_getCode.name=mGetPasswordView.getUserName();
        getPasswordModel.submit(CommonUtils.getRequestInfo(request_getCode,new Object()), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                mGetPasswordView.closeProgress();
                mGetPasswordView.showToastMessage(((ResponseBody)object).base.msg);

            }

            @Override
            public void onFailed(String string) {
                mGetPasswordView.closeProgress();
                mGetPasswordView.showMessage(string);
            }
        });
    }

    @Override
    public void start() {

    }
}
