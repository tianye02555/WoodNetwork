package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by ty on 2016/9/14 0014.
 */

public class OrderBuyerDemExceptionList_listItem {
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
    public List<OrderBuyerDemExceptionList_listItem_productsItem> products;

}
