package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by ty on 2016/8/24 0024.
 */

public class BusnessListInfo {
    @Expose
    public String page_size;
    @Expose
    public String page_no;
    @Expose
    public List<BusnessInfo> seller_list;
}
