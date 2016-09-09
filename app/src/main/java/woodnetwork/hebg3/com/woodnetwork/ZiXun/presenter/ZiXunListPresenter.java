package woodnetwork.hebg3.com.woodnetwork.ZiXun.presenter;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.ArticleList;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.CategoryList;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.contract.ZiXunListContract;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.model.ZiXunListModel;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/2 0002.
 */

public class ZiXunListPresenter implements ZiXunListContract.ZiXunListPresenterInterface {
    private ZiXunListContract.ZiXunListViewInterface ziXunListView;
    private ZiXunListModel ziXunListMode;
    public ZiXunListPresenter(ZiXunListContract.ZiXunListViewInterface ziXunListView){
        if(null!=ziXunListView){
            this.ziXunListView=ziXunListView;
        }
        this.ziXunListView.setPresenter(this);
        ziXunListMode=new ZiXunListModel();
    }
    @Override
    public void getCategoryListData(MyRequestInfo myRequestInfo) {
        ziXunListView.showProgress();
        ziXunListMode.getCategoryListData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                ziXunListView.closeProgress();
                ziXunListView.setCategoryListInfo((CategoryList) ((ResponseBody)object).obj);
            }

            @Override
            public void onFailed(String string) {
                ziXunListView.closeProgress();
                ziXunListView.showMessage(string);
            }
        });
    }

    @Override
    public void getArticleListData(MyRequestInfo myRequestInfo) {
        ziXunListView.showProgress();
        ziXunListMode.getArticleListData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                ziXunListView.closeProgress();
                ziXunListView.setArticleListInfo((ArticleList) ((ResponseBody)object).obj);
            }

            @Override
            public void onFailed(String string) {
                ziXunListView.closeProgress();
                ziXunListView.showMessage(string);
            }
        });
    }

    @Override
    public void start() {

    }
}
