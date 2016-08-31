package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ty on 2016/8/30 0030.
 */

public class ShopcarList_listItem implements Serializable{
    @Expose
    public String sid;
    @Expose
    public String pid;
    @Expose
    public String pname;
    @Expose
    public Double price;
    @Expose
    public Double stock;
    @Expose
    public ShopcarList_listItem_seller seller;
    @Expose
    public int type;
    @Expose
    public String pimg;
    @Expose
    public Boolean checkbox;
    @Expose
    public String xiaoJi;
    @Expose
    public List<ShopcarList_listItem_attributeItem> attribute;

}