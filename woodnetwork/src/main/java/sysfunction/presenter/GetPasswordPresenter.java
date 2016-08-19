package sysfunction.presenter;

import sysfunction.contract.GetPasswordContract;
import sysfunction.model.GetPasswordModel;
import woodnetwork.hebg3.com.woodnetwork.BasePresenter;

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

    }

    @Override
    public void submit() {

    }

    @Override
    public void start() {

    }
}
