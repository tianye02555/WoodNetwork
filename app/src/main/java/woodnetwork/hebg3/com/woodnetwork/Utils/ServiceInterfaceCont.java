package woodnetwork.hebg3.com.woodnetwork.Utils;

/**
 * Created by ty on 2016/8/23 0023.
 */

public class ServiceInterfaceCont {

    //登录接口
    public static final String LOGIN="/user/login";

    //更改密码接口
    public static final String GETPASSWORD="/user/password/update";

    //获取筛选属性列表
    public static final String ATTRIBUTEFILTER="/attribute/filter/list?param=";

    //根据属性获取产品列表接口
    public static final String PRODUCTFILTERLIST="/product/filter/list";

    //获取推荐商家列表接口
    public static final String SELLERFEATURED="/seller/featured/list?param=";

    //获取商家列表接口
    public static final String SELLERLIST="/seller/list?param=";

    //获取商家详情接口
    public static final String SELLERINFO="/seller/info?param=";

    //获取产品详情接口
    public static final String PRODUCTINFO="/product/info?param=";
}
