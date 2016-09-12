package woodnetwork.hebg3.com.woodnetwork.ZiXun.presenter;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.BannerList;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.CategoryList;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.contract.ZiXunHomeContract;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.model.ZiXunHomeModel;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/1 0001.
 */

public class ZiXunHomePresenter implements ZiXunHomeContract.ZiXunHomePresenterInterface{
    private ZiXunHomeContract.ZiXunHomeViewInterface ziXunHomeView;
    private ZiXunHomeModel ziXunHomeModel;
    public ZiXunHomePresenter(ZiXunHomeContract.ZiXunHomeViewInterface ziXunHomeView){
        if(null!=ziXunHomeView){
            this.ziXunHomeView=ziXunHomeView;
        }
        this.ziXunHomeView.setPresenter(this);
        ziXunHomeModel=new ZiXunHomeModel();
    }

    @Override
    public void getMyGalleryData(MyRequestInfo myRequestInfo) {
        ziXunHomeModel.getMyGalleryData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                ziXunHomeView.setMyGalleryInfo((BannerList) ((ResponseBody)object).obj);
            }

            @Override
            public void onFailed(String string) {
                ziXunHomeView.showMessage(string);
            }
        });
    }

    @Override
    public void getCategoryListData(MyRequestInfo myRequestInfo) {

        ziXunHomeModel.getCategoryListData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {

                ziXunHomeView.setCategoryListInfo((CategoryList) ((ResponseBody)object).obj);
            }

            @Override
            public void onFailed(String string) {

                ziXunHomeView.showMessage(string);
            }
        });
    }

    @Override
    public void start() {

    }
}
