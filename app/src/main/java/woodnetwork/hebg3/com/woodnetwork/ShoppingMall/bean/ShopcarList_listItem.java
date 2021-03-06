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
    public Integer type;
    @Expose
    public String pimg;
    @Expose
    public Boolean checkbox=false;
    @Expose
    public Double xiaoJi;
    @Expose
    public Boolean gridView=false;
    @Expose
    public String delivery;
    @Expose
    public String delivery_area;
    @Expose
    public String delivery_id;
    @Expose
    public Integer flag;
    @Expose
    public Integer saveEnable=0;//0 不可用 1//可用
    @Expose
    public List<ShopcarList_listItem_attributeItem> attribute;

}
