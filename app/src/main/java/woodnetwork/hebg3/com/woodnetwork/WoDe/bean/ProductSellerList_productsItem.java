package woodnetwork.hebg3.com.woodnetwork.WoDe.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by ty on 2016/9/7 0007.
 */

public class ProductSellerList_productsItem {
    @Expose
    public String pid;
    @Expose
    public String pname;
    @Expose
    public String pimg;
    @Expose
    public Double price;
    @Expose
    public Double stock;
    @Expose
    public int type;
    @Expose
    public int status;
    @Expose
    public String delivery;
    @Expose
    public List<ProductSellerList_productsItem_attributesItem> attributes;
}
