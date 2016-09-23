package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ty on 2016/9/18 0018.
 */

public class ExceptionList implements Serializable{
    @Expose
    public String id;
    @Expose
    public String number;
    @Expose
    public String creat_time;
    @Expose
    public Double total_number;
    @Expose
    public Double total_price;
    @Expose
    public String seller;
    @Expose
    public String shop_number;
    @Expose
    public List<ExceptionList_exceptionItem> exception;
}
