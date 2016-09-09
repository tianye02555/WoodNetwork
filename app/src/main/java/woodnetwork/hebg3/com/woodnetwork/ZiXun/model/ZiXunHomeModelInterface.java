package woodnetwork.hebg3.com.woodnetwork.ZiXun.model;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;

/**
 * Created by ty on 2016/9/1 0001.
 */

public interface ZiXunHomeModelInterface {
    /**
     * 获取轮播图数据
     */
    void getMyGalleryData(Object object, OnServiceBaceInterface onServiceBaceInterface);

    /**
     * 获取新闻分类列表
     */
    void getCategoryListData(Object object, OnServiceBaceInterface onServiceBaceInterface);
}
