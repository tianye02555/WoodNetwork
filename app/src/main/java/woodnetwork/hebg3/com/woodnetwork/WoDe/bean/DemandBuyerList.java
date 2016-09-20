package woodnetwork.hebg3.com.woodnetwork.WoDe.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by ty on 2016/9/7 0007.
 */

public class DemandBuyerList {
    @Expose
    public Integer page_size;
    @Expose
    public Integer page_no;
    @Expose
    public Integer total_page;
    @Expose
    public List<DemandBuyerList_listItem> list;
}
