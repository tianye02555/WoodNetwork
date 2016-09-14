package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by ty on 2016/9/14 0014.
 */

public class OrderBuyerProExceptionList {
    @Expose
    public Integer  page_size ;
    @Expose
    public Integer  page_no ;
    @Expose
    public Integer  total_page ;
    @Expose
    public List<OrderBuyerProExceptionList_listItem> list ;
}
