package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.presenter;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.AreaList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract.ChooseAddressContract;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.model.ChooseAddressModel;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/8/29 0029.
 */

public class ChooseAddressPresenter implements ChooseAddressContract.ChooseAddressPresenterInterface {
    private ChooseAddressContract.ChooseAddressViewInterface chooseAddressView;
    private ChooseAddressModel chooseAddressModel;

    public ChooseAddressPresenter(ChooseAddressContract.ChooseAddressViewInterface chooseAddressView) {
        if (null != chooseAddressView) {
            this.chooseAddressView = chooseAddressView;
        }
        this.chooseAddressView.setPresenter(this);
        chooseAddressModel = new ChooseAddressModel();
    }

    @Override
    public void getAreaData(MyRequestInfo myRequestInfo) {
        chooseAddressView.showProgress();
        chooseAddressModel.getAreaData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                chooseAddressView.closeProgress();
                if(null!=((AreaList) ((ResponseBody)object).obj).list){
                    chooseAddressView.showNewSpinner((AreaList) ((ResponseBody)object).obj);
                }
            }

            @Override
            public void onFailed(String string) {
                chooseAddressView.closeProgress();
                chooseAddressView.showMessage(string);
            }
        });
    }

    @Override
    public void start() {

    }
}
