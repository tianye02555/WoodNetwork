package woodnetwork.hebg3.com.woodnetwork.WoDe.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by ty on 2016/9/7 0007.
 */

public class ProductSellerList {
    @Expose
    public int page_size;
    @Expose
    public int page_no;
    @Expose
    public int total_page;
    @Expose
    public List<ProductSellerList_productsItem> products;
}
