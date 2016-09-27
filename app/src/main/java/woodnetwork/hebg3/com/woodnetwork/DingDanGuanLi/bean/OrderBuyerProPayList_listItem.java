package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ty on 2016/9/14 0014.
 */

public class OrderBuyerProPayList_listItem implements Serializable{
    @Expose
    public String id;
    @Expose
    public String number;
    @Expose
    public String creat_time;
    @Expose
    public Integer type;
    @Expose
    public Integer status;
    @Expose
    public Integer appeal_flag;
    @Expose
    public String seller;
    @Expose
    public Double total_number;
    @Expose
    public Double total_price;
    @Expose
    public List<OrderBuyerProPayList_listItem_productsItem> products;

}
