package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ty on 2016/8/30 0030.
 */

public class ShopcarList implements Serializable{
    @Expose
    public int page_size;
    @Expose
    public int page_no;
    @Expose
    public List<ShopcarList_listItem> list;
}
