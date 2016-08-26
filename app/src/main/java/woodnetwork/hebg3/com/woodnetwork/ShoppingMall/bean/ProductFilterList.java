package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by ty on 2016/8/26 0026.
 */

public class ProductFilterList {
    @Expose
    public String page_size;
    @Expose
    public String page_no;
    @Expose
    public List<ProductFilterList_productsItem> products;

}
