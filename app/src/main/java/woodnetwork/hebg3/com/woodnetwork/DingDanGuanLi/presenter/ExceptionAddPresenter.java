package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.presenter;


import android.content.Context;

import java.io.File;
import java.util.HashMap;

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
    public void submitExceptionOrder(Context context, HashMap<String, String> params, HashMap<String, File> files) {
        exceptionAddView.showProgress();
        exceptionAddModel.submitExceptionOrder(context, params, files, new OnServiceBaceInterface() {

            @Override
            public void onSuccess(Object object) {
                exceptionAddView.closeProgress();
                exceptionAddView.showMessage((String)object);
                exceptionAddView.closeActivity();
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
