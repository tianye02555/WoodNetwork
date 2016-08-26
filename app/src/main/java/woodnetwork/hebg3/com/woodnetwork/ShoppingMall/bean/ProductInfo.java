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
    public String price;
    @Expose
    public String stock;
    @Expose
    public String type;
    @Expose
    public String seller;
    @Expose
    public String address;
    @Expose
    public List<String> pimg;
    @Expose
    public List<WoodSpecifications> attribute;
}
