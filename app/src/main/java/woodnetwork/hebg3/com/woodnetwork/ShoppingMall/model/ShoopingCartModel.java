package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.model;

import android.os.Handler;
import android.os.Message;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopcarList;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ServiceInterfaceCont;
import woodnetwork.hebg3.com.woodnetwork.net.ClientParams;
import woodnetwork.hebg3.com.woodnetwork.net.NetTask;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/8/30 0030.
 */

public class ShoopingCartModel implements ShoopingCartModelInterface {
    private OnServiceBaceInterface onServiceBaceInterface_getShoopingCartData;
    private OnServiceBaceInterface onServiceBaceInterface_changeGoodsNumber;
    private OnServiceBaceInterface onServiceBaceInterface_deleteGoods;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ResponseBody body = (ResponseBody) msg.obj;
            switch (msg.what) {
                case 0://获取购物车列表接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getShoopingCartData.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_getShoopingCartData.onFailed(body.base.msg);
                    }
                    break;
                case 1://更改物品数量接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_changeGoodsNumber.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_changeGoodsNumber.onFailed(body.base.msg);
                    }
                    break;
                case 2://删除物品接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_deleteGoods.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_deleteGoods.onFailed(body.base.msg);
                    }
                    break;
            }
        }
    };
    @Override
    public void getShoopingCartData(Object param, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getShoopingCartData = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.SHOPCARLIST;
        params.GETTYPE = "1";
        params.params = CommonUtils.getParamString(param);
        new NetTask(handler.obtainMessage(0), params, ShopcarList.class).execute();

    }

    @Override
    public void changeGoodsNumber(Object param, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_changeGoodsNumber = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_POST;
        params.getMethod = ServiceInterfaceCont.SHOPCARUPDATE;
        params.params = CommonUtils.getParamString(param);
        new NetTask(handler.obtainMessage(1), params).execute();

    }

    @Override
    public void deleteGoods(Object param, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_deleteGoods = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_POST;
        params.getMethod = ServiceInterfaceCont.SHOPCARDELETE;
        params.params = CommonUtils.getParamString(param);
        new NetTask(handler.obtainMessage(2), params).execute();

    }
}
