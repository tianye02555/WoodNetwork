package sysfunction.presenter;

import sysfunction.contract.GetPasswordContract;
import woodnetwork.hebg3.com.woodnetwork.BasePresenter;

/**
 * Created by ty on 2016/8/18 0018.
 */

public class GetPassworPresenter implements GetPasswordContract.GetPasswordPresenter {
    private GetPasswordContract.GetPasswordView mGetPasswordView;

    public GetPassworPresenter(GetPasswordContract.GetPasswordView mGetPasswordView) {
        if(null==mGetPasswordView){
            return;
        }
        this.mGetPasswordView=mGetPasswordView;
        this.mGetPasswordView.setPresenter(this);



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
