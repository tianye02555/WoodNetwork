package woodnetwork.hebg3.com.woodnetwork.WoDe.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.WoodSpecifications;

/**
 * Created by ty on 2016/9/7 0007.
 */

public class ProductSellerInfo {
    @Expose
    public String pid;
    @Expose
    public String pname;
    @Expose
    public Double price;
    @Expose
    public Double stock;
    @Expose
    public Integer type;
    @Expose
    public String sid;
    @Expose
    public String seller;
    @Expose
    public String delivery;
    @Expose
    public List<String> pimg;
    @Expose
    public List<ProductSellerInfo_attributeItem> attribute;
}
