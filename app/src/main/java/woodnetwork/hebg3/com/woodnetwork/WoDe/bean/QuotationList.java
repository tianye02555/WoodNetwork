package woodnetwork.hebg3.com.woodnetwork.WoDe.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by ty on 2016/9/6 0006.
 */

public class QuotationList {
    @Expose
    public int page_size;
    @Expose
    public int page_no;
    @Expose
    public int total_page;
    @Expose
    public List<QuotationList_quotationItem> quotation;
}
