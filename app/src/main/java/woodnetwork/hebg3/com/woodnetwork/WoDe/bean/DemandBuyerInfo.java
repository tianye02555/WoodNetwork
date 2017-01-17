package woodnetwork.hebg3.com.woodnetwork.WoDe.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by ty on 2016/9/7 0007.
 */

public class DemandBuyerInfo {
    @Expose
    public String id;
    @Expose
    public String receive_area;
    @Expose
    public String createTime;
    @Expose
    public String pname;
    @Expose
    public Integer ptype;
    @Expose
    public Integer status;
    @Expose
    public Double number;
    @Expose
    public String remarks;
    @Expose
    public List<DemandBuyerInfo_attributeItem> attribute;

}
