package woodnetwork.hebg3.com.woodnetwork.RequestParam;

import java.util.List;

/**
 * Created by ty on 2016/8/27 0027.
 */

public class Request_orderAdd {
    public String seller_id;
    public String buyer_id;
    public int type;
    public String receive_id;
    public String receive_area;
    public int receive_type;
    public List<Request_orderAdd_productsItem> products;
    public List<String> shopcar_ids;
}
