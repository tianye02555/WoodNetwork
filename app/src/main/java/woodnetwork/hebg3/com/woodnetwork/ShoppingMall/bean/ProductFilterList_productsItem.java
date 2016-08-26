package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by ty on 2016/8/26 0026.
 */

public class ProductFilterList_productsItem {
    @Expose
    public String pid;
    @Expose
    public String pname;
    @Expose
    public String pimg;
    @Expose
    public String seller;
    @Expose
    public String price;
    @Expose
    public String stock;
    @Expose
    public String type;
    @Expose
    public String delivery;
    @Expose
    public List<ProductFilterList_productsItem_attributesItem> attributes;
}
