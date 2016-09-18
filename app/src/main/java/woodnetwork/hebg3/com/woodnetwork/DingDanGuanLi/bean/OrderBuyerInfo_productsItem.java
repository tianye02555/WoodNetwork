package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ty on 2016/9/18 0018.
 */

public class OrderBuyerInfo_productsItem implements Serializable {
    @Expose
    public String name ;
    @Expose
    public Double price ;
    @Expose
    public Double number ;
    @Expose
    public Double total_price ;
    @Expose
    public String img ;
    @Expose
    public Integer type ;
    @Expose
    public List<OrderBuyerInfo_productsItem_attributesItem> attributes ;
}
