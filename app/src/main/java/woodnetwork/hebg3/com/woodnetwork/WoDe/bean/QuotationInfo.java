package woodnetwork.hebg3.com.woodnetwork.WoDe.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by ty on 2016/9/6 0006.
 */

public class QuotationInfo {
    @Expose
    public String id;
    @Expose
    public String name;
    @Expose
    public String buyer;
    @Expose
    public String number;
    @Expose
    public String delivery;
    @Expose
    public String receive;
    @Expose
    public Double price;
    @Expose
    public int type;
    @Expose
    public int status;
    @Expose
    public List<QuotationInfo_attributeItem> attribute;
}
