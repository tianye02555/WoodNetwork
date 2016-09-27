package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by ty on 2016/9/14 0014.
 */

public class OrderBuyerProPayList_listItem_productsItem implements Serializable {
    @Expose
    public Double price;
    @Expose
    public Double number;
    @Expose
    public String name;
    @Expose
    public Double total_price;
    @Expose
    public String img;

    @Expose
    public Integer type;

}
