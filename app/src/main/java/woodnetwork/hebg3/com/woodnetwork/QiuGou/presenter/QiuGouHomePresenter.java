package woodnetwork.hebg3.com.woodnetwork.QiuGou.presenter;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.bean.DemandList;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.contract.QiuGouHomeContract;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.model.QiuGouHomeModel;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/8/31 0031.
 */

public class QiuGouHomePresenter implements QiuGouHomeContract.QiuGouHomePresenterInterface {
    private QiuGouHomeContract.QiuGouHomeViewInterface qiuGouHomeView;
    private QiuGouHomeModel qiuGouHomeModel;
    public QiuGouHomePresenter(QiuGouHomeContract.QiuGouHomeViewInterface qiuGouHomeView){
        if(null!=qiuGouHomeView){
            this.qiuGouHomeView=qiuGouHomeView;
        }
        this.qiuGouHomeView.setPresenter(this);
        qiuGouHomeModel=new QiuGouHomeModel();
    }
    @Override
    public void getQiuGouData(MyRequestInfo myRequestInfo,final int flag) {

        qiuGouHomeModel.getQiuGouData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                if (0 == flag) {
                    qiuGouHomeView.showQiuGouInfo((DemandList) ((ResponseBody)object).obj);
                } else if (1 == flag) {
                    qiuGouHomeView.refresh(((DemandList) ((ResponseBody) object).obj));

                } else if (2 == flag) {
                    qiuGouHomeView.loadMore(((DemandList) ((ResponseBody) object).obj).list);
                }

            }



            @Override
            public void onFailed(String string) {

                qiuGouHomeView.showMessage(string);
            }
        });
    }

    @Override
    public void start() {

    }
}
