package woodnetwork.hebg3.com.woodnetwork.Utils;

/**
 * Created by ty on 2016/8/23 0023.
 */

public class ServiceInterfaceCont {

    //1.1登录接口
    public static final String LOGIN = "/user/login";

    //2.2更改密码接口
    public static final String GETPASSWORD = "/user/password/update";

    //2.1 获取验证码接口
    public static final String GETAUTHCODE = "/user/authcode";

    //获取筛选属性列表
    public static final String ATTRIBUTEFILTER = "/attribute/filter/list?param=";

    //根据属性获取产品列表接口
    public static final String PRODUCTFILTERLIST = "/product/filter/list";

    //获取推荐商家列表接口
    public static final String SELLERFEATURED = "/seller/featured/list?param=";

    //获取商家列表接口
    public static final String SELLERLIST = "/seller/list?param=";

    //获取商家详情接口
    public static final String SELLERINFO = "/seller/info?param=";

    //获取产品详情接口
    public static final String PRODUCTINFO = "/product/info?param=";

    //保存订单接口
    public static final String ORDERADD = "/order/add";

    //获取区域接口
    public static final String AREALIST = "/area/list?param=";

    //查询余额接口
    public static final String ACCOUNTBALANCEINFO = "/account/balance/info?param=";

    //获取购物车列表接口
    public static final String SHOPCARLIST = "/shopcar/list?param=";

    //删除购物车商品接口
    public static final String SHOPCARDELETE = "/shopcar/delete";

    //修改购物车商品数量接口
    public static final String SHOPCARUPDATE = "/shopcar/update";

    //获取求购列表接口
    public static final String DEMANDLIST = "/demand/list?param=";

    //3.4添加购物车接口
    public static final String SHOPCARADD = "/shopcar/add";

    //11.1获取求购详情接口
    public static final String DEMANDINFO = "/demand/info?param=";

    //11.2保存报价接口
    public static final String QUOTATIONADD = "/quotation/add";

    //12.1获取轮播图接口
    public static final String BANNERLIST = "/banner/list?param=";

    //12.2获取新闻分类列表接口
    public static final String CATEGORYLIST = "/category/list?param=";

    //13.1获取新闻列表接口
    public static final String ARTICLELIST = "/article/list?param=";

    //14.1获取新闻详情接口
    public static final String ARTICLEWEBINFO = "/article/web/info?param=";

    //15.1获取个人信息接口
    public static final String USERINFO = "/user/info?param=";

    //16.1获取全部订单列表接口
    public static final String ORDERBUYERPROLIST = "/order/buyer/pro/list?param=";

    //16.2根据订单类型获取订单列表接口
    public static final String ORDERBUYERPROFILTER = "/order/buyer/pro/filter/list?param=";

    //16.3获取异常订单列表接口
    public static final String ORDERBUYERPROEXCEPTIONLIST = "/order/buyer/pro/exception/list?param=";

    //16.4关闭订单接口
    public static final String ORDERBUYERCLOSE = "/order/buyer/close?param=";

    //17.1获取订单详情接口
    public static final String ORDERBUYERINFO = "/order/buyer/info?param=";
    //17.2获取卖家订单详情接口
    public static final String ORDERSELLERINFO = "/order/seller/info?param=";

    //18.1获取订单异常列表
    public static final String EXCEPTIONLIST = "/exception/list?param=";

    //19.1异常添加接口
    public static final String EXCEPTIONADD = "/exception/add";

    //20.1确认收货接口
    public static final String ORDERRECEIVE = "/order/receive";

    //21.1获取我的求购信息列表接口
    public static final String DEMANDBUYERLIST = "/demand/buyer/list?param=";

    //22.1获取我的求购详情接口
    public static final String DEMANDBUYERINFO = "/demand/buyer/info?param=";

    //23.1获取全部求购订单列表
    public static final String ORDERBUYERDEMLIST = "/order/buyer/dem/list?param=";

    //23.2根据订单类型获取求购订单列表接口
    public static final String ORDERBUYERDEMFILTERLIST = "/order/buyer/dem/filter/list?param=";

    //23.3获取异常求购订单列表接口
    public static final String ORDERBUYERDEMEXCEPTIONLIST = "/order/buyer/dem/exception/list?param=";

    //24.1获取我的店铺详情接口
    public static final String SHOPINFO = "/shop/info?param=";

    //25.1获取我的商品列表接口
    public static final String PRODUCTSELLERLIST = "/product/seller/list?param=";

    //25.2获取我的商品详情接口
    public static final String PRODUCTSELLERINFO = "/product/seller/info?param=";

    //26.1获取卖家订单列表接口
    public static final String ORDERSELLERLIST = "/order/seller/list?param=";

    //26.2根据订单类型获取卖家订单列表接口
    public static final String ORDERSELLERFILTERLIST = "/order/seller/filter/list?param=";

    //26.3获取卖家异常订单列表接口
    public static final String ORDERSELLEREXCEPTIONLIST = "/order/seller/exception/list?param=";

    //26.4卖家关闭订单接口
    public static final String ORDERSELLERCLOSE = "/order/seller/close?param=";

    //28.1获取统计详情接口
    public static final String REPORTSINFO = "/reports/info?param=";

    //29.1获取报价列表接口
    public static final String QUOTATIONLIST = "/quotation/list?param=";

    //30.1获取报价详情接口
    public static final String QUOTATIONINFO = "/quotation/info?param=";

    //31.1获取意见反馈分类接口
    public static final String GUESTBOOKTYPELIST = "/guestbook/type/list?param=";

    //31.2保存意见反馈接口
    public static final String GUESTBOOKADD = "/guestbook/add";

    //32.1获取版本接口
    public static final String VERSIONINFO = "/version/info?param=";

    //获取我的已付款订单
    public static final String ORDERBUYERPROPAIDLIST = "/order/buyer/pro/paid/list?param=";

    //获取求购已付款订单
    public static final String ORDERBUYERDEMPAIDLIST = "/order/buyer/dem/paid/list?param=";


}
