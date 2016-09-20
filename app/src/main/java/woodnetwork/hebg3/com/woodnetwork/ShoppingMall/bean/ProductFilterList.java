package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by ty on 2016/8/26 0026.
 */

public class ProductFilterList {
    @Expose
    public Integer page_size;
    @Expose
    public Integer page_no;
    @Expose
    public Integer total_page;
    @Expose
    public List<ProductFilterList_productsItem> products;

}
