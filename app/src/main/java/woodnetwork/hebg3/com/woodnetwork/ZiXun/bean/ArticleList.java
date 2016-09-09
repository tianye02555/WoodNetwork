package woodnetwork.hebg3.com.woodnetwork.ZiXun.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by ty on 2016/9/2 0002.
 */

public class ArticleList {
    @Expose
    public int page_size;
    @Expose
    public int page_no;
    @Expose
    public List<ArticleList_listItem> list;
}
