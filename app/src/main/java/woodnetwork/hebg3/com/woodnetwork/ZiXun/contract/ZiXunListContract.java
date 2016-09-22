package woodnetwork.hebg3.com.woodnetwork.ZiXun.contract;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.ArticleList;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.ArticleList_listItem;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.CategoryList;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.CategoryList_listItem;

/**
 * Created by ty on 2016/9/2 0002.
 */

public interface ZiXunListContract {
    interface ZiXunListViewInterface extends BaseView <ZiXunListPresenterInterface> {
        /**
         * 新建一个text 加入到 水平滚动条中
         * @param
         */
        void newTitleText(CategoryList categoryList);
        /**
         * 设置新闻分类列表数据
         */
        void setCategoryListInfo(CategoryList categoryList);

        /**
         * 设置新闻列表数据
         * @param articleList
         */
        void setArticleListInfo(ArticleList articleList);
        /**
         * 加载更多
         */
        void loadMore(List<ArticleList_listItem> list);
        /**
         * 更新
         */
        void refresh(ArticleList articleList);

    }
    interface ZiXunListPresenterInterface extends BasePresenter{
        /**
         * 获取新闻分类列表
         */
        void getCategoryListData(MyRequestInfo myRequestInfo);

        /**
         * 获取新闻列表
         * @param myRequestInfo
         */
        void getArticleListData(MyRequestInfo myRequestInfo,int flag);
    }
}
