package woodnetwork.hebg3.com.woodnetwork.QiuGou.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by ty on 2016/8/31 0031.
 */

public class DemandInfo {
    @Expose
    public String id;
    @Expose
    public String receive_area;
    @Expose
    public String create_time;
    @Expose
    public String pname;
    @Expose
    public Integer ptype;
    @Expose
    public Integer number;
    @Expose
    public String buyer;
    @Expose
    public String phone;
    @Expose
    public String remarks;
    @Expose
    public List<DemandInfo_attributeItem> attribute;
}
