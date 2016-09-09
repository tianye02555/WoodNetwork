package woodnetwork.hebg3.com.woodnetwork.WoDe.presenter;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.VersionInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.SettingContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.model.SettingModel;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/5 0005.
 */

public class SettingPresenter implements SettingContract.SettingPresenterInterface {
    private SettingContract.SettingViewInterface settingView;
    private SettingModel settingModel;
    public SettingPresenter(SettingContract.SettingViewInterface settingView){
        if(null!=settingView){
            this.settingView=settingView;
        }
        this.settingView.setPresenter(this);
        settingModel=new SettingModel();
    }
    @Override
    public void getCheckUpdateData(MyRequestInfo myRequestInfo) {
        settingView.showProgress();
        settingModel.getVersionData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                settingView.closeProgress();
                settingView.setCheckUpdateInfo((VersionInfo) ((ResponseBody)object).obj);
            }

            @Override
            public void onFailed(String string) {
                settingView.closeProgress();
                settingView.showMessage(string);
            }
        });
    }

    @Override
    public void start() {

    }
}
