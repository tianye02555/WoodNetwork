package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ty on 2016/9/18 0018.
 */

public class OrderBuyerInfo implements Serializable{
    @Expose
    public String id ;
    @Expose
    public String number ;
    @Expose
    public String creat_time ;
    @Expose
    public Integer type ;
    @Expose
    public Integer status ;
    @Expose
    public Integer appeal_flag ;
    @Expose
    public String seller ;
    @Expose
    public String delivery_area ;
    @Expose
    public String receive_area ;
    @Expose
    public Integer receive_type ;
    @Expose
    public Double total_number ;
    @Expose
    public Double total_price ;
    @Expose
    public List<OrderBuyerInfo_productsItem> products ;
}
