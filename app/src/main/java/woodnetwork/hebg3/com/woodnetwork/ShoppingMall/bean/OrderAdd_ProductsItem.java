package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ty on 2016/8/27 0027.
 */

public class OrderAdd_productsItem implements Serializable{
    @Expose
    public String name;
    @Expose
    public Double price;
    @Expose
    public Double number;
    @Expose
    public Double total_price;
    @Expose
    public Integer type;
    @Expose
    public List<OrderAdd_ProductsItem_priceItem> attributes;

}
