package woodnetwork.hebg3.com.woodnetwork.ZiXun.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by ty on 2016/9/2 0002.
 */

public class ArticleList {
    @Expose
    public Integer page_size;
    @Expose
    public Integer page_no;
    @Expose
    public Integer total_page;
    @Expose
    public List<ArticleList_listItem> list;
}
