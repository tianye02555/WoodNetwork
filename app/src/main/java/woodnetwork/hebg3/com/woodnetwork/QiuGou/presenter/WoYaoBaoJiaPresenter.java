package woodnetwork.hebg3.com.woodnetwork.QiuGou.presenter;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.bean.DemandInfo;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.contract.WoYaoBaoJiaContract;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.model.WoYaoBaoJiaModel;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/8/31 0031.
 */

public class WoYaoBaoJiaPresenter implements WoYaoBaoJiaContract.WoYaoBaoJiaPresenterInterface {
    private WoYaoBaoJiaContract.WoYaoBaoJiaViewInterface woYaoBaoJiaView;
    private WoYaoBaoJiaModel woYaoBaoJiaModel;
    public WoYaoBaoJiaPresenter(WoYaoBaoJiaContract.WoYaoBaoJiaViewInterface woYaoBaoJiaView){
        if(null!=woYaoBaoJiaView){
            this.woYaoBaoJiaView=woYaoBaoJiaView;
        }
        this.woYaoBaoJiaView.setPresenter(this);
        woYaoBaoJiaModel=new WoYaoBaoJiaModel();
    }
    @Override
    public void getWoYaoBaoJiaData(MyRequestInfo myRequestInfo) {
        woYaoBaoJiaView.showProgress();
        woYaoBaoJiaModel.getWoYaoBaoJiaData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                woYaoBaoJiaView.closeProgress();
                woYaoBaoJiaView.showWoYaoBaoJiaInfo((DemandInfo) ((ResponseBody)object).obj);
            }

            @Override
            public void onFailed(String string) {
                woYaoBaoJiaView.closeProgress();
                woYaoBaoJiaView.showfailMessage(string);
            }
        });
    }

    @Override
    public void saveWoDeBaoJia(MyRequestInfo myRequestInfo) {

    }

    @Override
    public void start() {

    }
}
