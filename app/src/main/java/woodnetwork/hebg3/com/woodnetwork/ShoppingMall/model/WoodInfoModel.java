package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.model;

import android.os.Handler;
import android.os.Message;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ServiceInterfaceCont;
import woodnetwork.hebg3.com.woodnetwork.net.ClientParams;
import woodnetwork.hebg3.com.woodnetwork.net.NetTask;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/8/25 0025.
 */

public class WoodInfoModel implements WoodInfoModelInterface {
    private OnServiceBaceInterface onServiceBaceInterface_woodData;
    private OnServiceBaceInterface onServiceBaceInterface_addToShoppingCart;
    private OnServiceBaceInterface onServiceBaceInterface_buy;
    private OnServiceBaceInterface onServiceBaceInterface_toShoppingCart;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ResponseBody body = (ResponseBody) msg.obj;
            switch (msg.what) {
                case 1://根据属性获取产品列表接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_woodData.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_woodData.onFailed(body.base.msg);
                    }
                    break;
                case 2://获取推荐商家列表接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_addToShoppingCart.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_addToShoppingCart.onFailed(body.base.msg);
                    }
                    break;
                case 3://获取推荐商家列表接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_buy.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_buy.onFailed(body.base.msg);
                    }
                    break;
                case 4://获取推荐商家列表接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_toShoppingCart.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_toShoppingCart.onFailed(body.base.msg);
                    }
                    break;
            }
        }
    };

    @Override
    public void getWoodData(Object param, OnServiceBaceInterface onServiceBaceInterface_woodData) {
        this.onServiceBaceInterface_woodData = onServiceBaceInterface_woodData;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.PRODUCTINFO;
        params.GETTYPE = "1";
        params.params = CommonUtils.getParamString(param);
        new NetTask(handler.obtainMessage(1), params, ProductInfo.class).execute();

    }

    @Override
    public void addToShoppingCart(Object param, OnServiceBaceInterface onServiceBaceInterface_addToShoppingCart) {
        this.onServiceBaceInterface_addToShoppingCart = onServiceBaceInterface_addToShoppingCart;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.SELLERINFO;
        params.GETTYPE = "1";
        params.params = CommonUtils.getParamString(param);
        new NetTask(handler.obtainMessage(2), params, BusnessInfo.class).execute();

    }

    @Override
    public void buy(Object param, OnServiceBaceInterface onServiceBaceInterface_buy) {
        this.onServiceBaceInterface_buy = onServiceBaceInterface_buy;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.SELLERINFO;
        params.GETTYPE = "1";
        params.params = CommonUtils.getParamString(param);
        new NetTask(handler.obtainMessage(3), params, BusnessInfo.class).execute();

    }

    @Override
    public void toShoppingCart(Object param, OnServiceBaceInterface onServiceBaceInterface_toShoppingCart) {
        this.onServiceBaceInterface_toShoppingCart = onServiceBaceInterface_toShoppingCart;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.SELLERINFO;
        params.GETTYPE = "1";
        params.params = CommonUtils.getParamString(param);
        new NetTask(handler.obtainMessage(4), params, BusnessInfo.class).execute();

    }
}
