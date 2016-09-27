package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.model;

import android.os.Handler;
import android.os.Message;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ServiceInterfaceCont;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.ProductSellerInfo;
import woodnetwork.hebg3.com.woodnetwork.net.ClientParams;
import woodnetwork.hebg3.com.woodnetwork.net.NetTask;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/8/25 0025.
 */

public class WoodInfoModel implements WoodInfoModelInterface {
    private OnServiceBaceInterface onServiceBaceInterface_woodData;
    private OnServiceBaceInterface onServiceBaceInterface_addToShoppingCart;
    private OnServiceBaceInterface onServiceBaceInterface_getWoodDataOther;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ResponseBody body = (ResponseBody) msg.obj;
            switch (msg.what) {
                case 1://获取产品详情接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_woodData.onSuccess(body);
                    } else  {//失败
                        onServiceBaceInterface_woodData.onFailed(body.base.msg);
                    }
                    break;
                case 2://添加到购物车接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_addToShoppingCart.onSuccess(body);
                    } else  {//失败
                        onServiceBaceInterface_addToShoppingCart.onFailed(body.base.msg);
                    }
                    break;
                case 3://直接购买接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getWoodDataOther.onSuccess(body);
                    } else  {//失败
                        onServiceBaceInterface_getWoodDataOther.onFailed(body.base.msg);
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
        params.http_method = ClientParams.HTTP_POST;
        params.getMethod = ServiceInterfaceCont.SHOPCARADD;
        params.params = CommonUtils.getParamString(param);
        new NetTask(handler.obtainMessage(2), params).execute();

    }

    @Override
    public void getWoodDataOther(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getWoodDataOther = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.PRODUCTSELLERINFO;
        params.GETTYPE = "1";
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(3), params, ProductSellerInfo.class).execute();
    }


}
