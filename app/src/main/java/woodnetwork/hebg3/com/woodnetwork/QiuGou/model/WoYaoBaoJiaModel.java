package woodnetwork.hebg3.com.woodnetwork.QiuGou.model;

import android.os.Handler;
import android.os.Message;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.bean.DemandInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.WoodFilterAttribute;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ServiceInterfaceCont;
import woodnetwork.hebg3.com.woodnetwork.net.ClientParams;
import woodnetwork.hebg3.com.woodnetwork.net.NetTask;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/8/31 0031.
 */

public class WoYaoBaoJiaModel implements WoYaoBaoJiaModelInterface {
    private OnServiceBaceInterface onServiceBaceInterface_getWoYaoBaoJiaData;
    private OnServiceBaceInterface onServiceBaceInterface_saveWoDeBaoJia;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ResponseBody body = (ResponseBody) msg.obj;
            switch (msg.what) {
                case 0://获取筛选属性列表接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getWoYaoBaoJiaData.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_getWoYaoBaoJiaData.onFailed(body.base.msg);
                    }
                    break;
                case 1://保存订单接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_saveWoDeBaoJia.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_saveWoDeBaoJia.onFailed(body.base.msg);
                    }
                    break;
            }
        }
    };
    @Override
    public void getWoYaoBaoJiaData(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getWoYaoBaoJiaData = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.DEMANDINFO;
        params.GETTYPE="1";
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(0), params,DemandInfo.class).execute();
    }

    @Override
    public void saveWoDeBaoJia(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_saveWoDeBaoJia = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_POST;
        params.getMethod = ServiceInterfaceCont.QUOTATIONADD;
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(1), params).execute();
    }
}
