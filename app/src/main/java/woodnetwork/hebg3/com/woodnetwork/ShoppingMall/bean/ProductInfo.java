package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by ty on 2016/8/26 0026.
 */

public class ProductInfo {
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
    public List<WoodSpecifications> attribute;
}
