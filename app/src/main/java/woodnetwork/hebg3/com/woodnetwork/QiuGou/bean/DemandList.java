package woodnetwork.hebg3.com.woodnetwork.QiuGou.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by ty on 2016/8/31 0031.
 */

public class DemandList {
    @Expose
    public Integer page_size;
    @Expose
    public Integer page_no;
    @Expose
    public Integer total_page;
    @Expose
    public List<DemandList_listItem> list;
}
