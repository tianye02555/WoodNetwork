package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.model;

import android.os.Handler;
import android.os.Message;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ServiceInterfaceCont;
import woodnetwork.hebg3.com.woodnetwork.net.ClientParams;
import woodnetwork.hebg3.com.woodnetwork.net.NetTask;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/8/25 0025.
 */

public class BusnessInfoModel implements BusnessInfoModelInterface {
    private OnServiceBaceInterface onServiceBaceInterface_getBusnessInfo;
    private OnServiceBaceInterface onServiceBaceInterface_getSellerBusnessInfo;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ResponseBody body =(ResponseBody) msg.obj;
switch (msg.what){
    case 0:
        if("0".equals(body.base.code)){
            onServiceBaceInterface_getBusnessInfo.onSuccess(body);
        }else{
            onServiceBaceInterface_getBusnessInfo.onFailed(body.base.msg);
        }
        break;
    case 1:
        if("0".equals(body.base.code)){
            onServiceBaceInterface_getSellerBusnessInfo.onSuccess(body);
        }else{
            onServiceBaceInterface_getSellerBusnessInfo.onFailed(body.base.msg);
        }
        break;
}

        }
    };
    @Override
    public void getBusnessInfo(Object param, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getBusnessInfo = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.SELLERINFO;
        params.GETTYPE = "1";
        params.params = CommonUtils.getParamString(param);
        new NetTask(handler.obtainMessage(), params, BusnessInfo.class).execute();

    }

    @Override
    public void getSellerBusnessInfo(Object param, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getSellerBusnessInfo = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.SHOPINFO;
        params.GETTYPE = "1";
        params.params = CommonUtils.getParamString(param);
        new NetTask(handler.obtainMessage(), params, ShopInfo.class).execute();
    }
}
