package woodnetwork.hebg3.com.woodnetwork.ZiXun.model;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;

/**
 * Created by ty on 2016/9/2 0002.
 */

public interface ZiXunListModelInterface {

    /**
     * 获取新闻分类列表
     */
    void getCategoryListData(Object object, OnServiceBaceInterface onServiceBaceInterface);

    /**
     * 获取新闻列表
     * @param object
     * @param onServiceBaceInterface
     */
    void getArticleListData(Object object, OnServiceBaceInterface onServiceBaceInterface);
}
