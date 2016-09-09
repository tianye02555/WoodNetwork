package woodnetwork.hebg3.com.woodnetwork.ZiXun.contract;

import woodnetwork.hebg3.com.woodnetwork.Interface.BasePresenter;
import woodnetwork.hebg3.com.woodnetwork.Interface.BaseView;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.BannerList;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.CategoryList;

/**
 * Created by ty on 2016/9/1 0001.
 */

public interface ZiXunHomeContract {
    interface ZiXunHomeViewInterface extends BaseView<ZiXunHomePresenterInterface> {
        /**
         * 设置轮播图数据
         */
        void setMyGalleryInfo(BannerList bannerList);

        /**
         * 设置新闻分类列表数据
         */
        void setCategoryListInfo(CategoryList categoryList);

    }

    interface ZiXunHomePresenterInterface extends BasePresenter {
        /**
         * 获取轮播图数据
         */
        void getMyGalleryData(MyRequestInfo myRequestInfo);

        /**
         * 获取新闻分类列表
         */
        void getCategoryListData(MyRequestInfo myRequestInfo);
    }
}
