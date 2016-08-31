package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by ty on 2016/8/27 0027.
 */

public class OrderAdd_ProductsItem_priceItem implements Serializable{
    @Expose
    public String name;
    @Expose
    public String value;

}
