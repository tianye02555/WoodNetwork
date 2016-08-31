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

    //保存订单接口
    public static final String ORDERADD="/order/add";

    //获取区域接口
    public static final String AREALIST="/area/list?param=";

    //查询余额接口
    public static final String ACCOUNTBALANCEINFO="/account/balance/info?param=";

    //获取购物车列表接口
    public static final String SHOPCARLIST="/shopcar/list?param=";

    //删除购物车商品接口
    public static final String SHOPCARDELETE="/shopcar/delete";

    //修改购物车商品数量接口
    public static final String SHOPCARUPDATE="/shopcar/update";

    //获取求购列表接口
    public static final String DEMANDLIST="/demand/list?param=";

    //11.1获取求购详情接口
    public static final String DEMANDINFO="/demand/info?param=";

    //11.2保存报价接口
    public static final String QUOTATIONADD="/quotation/add";
}
