package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by ty on 2016/8/24 0024.
 */

public class WoodFilterInfo {
    @Expose
    public String attr_id;
    @Expose
    public String name;
    @Expose
    public List<WoodFilterValue> value;
}
