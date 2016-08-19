package sysfunction.model;

import sysfunction.model.modelinterface.GetPasswordModelInterface;

/**
 * Created by ty on 2016/8/18 0018.
 */

public class GetPasswordModel implements GetPasswordModelInterface {
    @Override
    public void sendCode() {

    }

    @Override
    public void submit() {

    }
    public interface GetPasswordLisenter{
        void onSuccessed();
        void onFailed();
    }
}
