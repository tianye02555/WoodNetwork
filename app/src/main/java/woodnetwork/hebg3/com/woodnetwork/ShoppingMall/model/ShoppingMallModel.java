package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductFilterList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.WoodFilterAttribute;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ServiceInterfaceCont;
import woodnetwork.hebg3.com.woodnetwork.net.ClientParams;
import woodnetwork.hebg3.com.woodnetwork.net.NetTask;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;
import woodnetwork.hebg3.com.woodnetwork.sysfunction.bean.User;

/**
 * Created by ty on 2016/8/24 0024.
 */

public class ShoppingMallModel implements ShoppingMallModelInterface {
    private OnServiceBaceInterface onServiceBaceInterface_spinner;
    private OnServiceBaceInterface onServiceBaceInterface_goods;
    private OnServiceBaceInterface onServiceBaceInterface_getShopcarAdd;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ResponseBody body = (ResponseBody) msg.obj;
            switch (msg.what) {
                case 0://获取筛选属性列表接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_spinner.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_spinner.onFailed(body.base.msg);
                    }
                    break;
                case 1://根据属性获取产品列表接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_goods.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_goods.onFailed(body.base.msg);
                    }
                    break;
                case 2://加入购物车接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getShopcarAdd.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_getShopcarAdd.onFailed(body.base.msg);
                    }
                    break;
            }
        }
    };
    @Override
    public void getSpinnerData(Object param, final OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_spinner = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.ATTRIBUTEFILTER;
        params.GETTYPE="1";
        params.params = CommonUtils.getParamString(param);
        new NetTask(handler.obtainMessage(0), params,WoodFilterAttribute.class).execute();
    }

    @Override
    public void getGoodsData(Object param, final OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_goods = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_POST;
        params.getMethod = ServiceInterfaceCont.PRODUCTFILTERLIST;
        params.params = CommonUtils.getParamString(param);
        new NetTask(handler.obtainMessage(1), params, ProductFilterList.class).execute();
    }

    @Override
    public void getShopcarAdd(Object param, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getShopcarAdd = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method =ClientParams.HTTP_POST;
        params.getMethod = ServiceInterfaceCont.SHOPCARADD;
        params.params = CommonUtils.getParamString(param);
        new NetTask(handler.obtainMessage(2), params).execute();
    }

//    @Override
//    public void getBusinessData(Object param, final OnServiceBaceInterface onServiceBaceInterface) {
//        this.onServiceBaceInterface_Business = onServiceBaceInterface;
//        ClientParams params = new ClientParams();
//        params.http_method =ClientParams.HTTP_GET;
//        params.getMethod = ServiceInterfaceCont.SELLERFEATURED;
//        params.GETTYPE="1";
//        params.params = CommonUtils.getParamString(param);
//        new NetTask(handler.obtainMessage(2), params, BusnessListInfo.class).execute();
//    }
}
