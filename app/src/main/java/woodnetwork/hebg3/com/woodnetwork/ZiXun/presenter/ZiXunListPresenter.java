package woodnetwork.hebg3.com.woodnetwork.ZiXun.presenter;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
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

    public ZiXunListPresenter(ZiXunListContract.ZiXunListViewInterface ziXunListView) {
        if (null != ziXunListView) {
            this.ziXunListView = ziXunListView;
        }
        this.ziXunListView.setPresenter(this);
        ziXunListMode = new ZiXunListModel();
    }

    @Override
    public void getCategoryListData(MyRequestInfo myRequestInfo) {
        ziXunListView.showProgress();
        ziXunListMode.getCategoryListData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                ziXunListView.closeProgress();
                ziXunListView.setCategoryListInfo((CategoryList) ((ResponseBody) object).obj);
            }

            @Override
            public void onFailed(String string) {
                ziXunListView.closeProgress();
                ziXunListView.showMessage(string);
            }
        });
    }

    @Override
    public void getArticleListData(MyRequestInfo myRequestInfo, final int flag) {
        if (0 == flag) {
            ziXunListView.showProgress();
        }
        ziXunListMode.getArticleListData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                if (0 == flag) {
                    ziXunListView.closeProgress();
                    ziXunListView.setArticleListInfo((ArticleList) ((ResponseBody) object).obj);
                } else if (1 == flag) {
                    ziXunListView.refresh(((ArticleList) ((ResponseBody) object).obj));

                } else if (2 == flag) {
                    ziXunListView.loadMore(((ArticleList) ((ResponseBody) object).obj).list);
                }


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
