package woodnetwork.hebg3.com.woodnetwork.QiuGou.model;

import android.os.Handler;
import android.os.Message;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.bean.DemandList;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ServiceInterfaceCont;
import woodnetwork.hebg3.com.woodnetwork.net.ClientParams;
import woodnetwork.hebg3.com.woodnetwork.net.NetTask;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/8/31 0031.
 */

public class QiuGouHomeModel implements QiuGouHomeModelInterface {
    private OnServiceBaceInterface onServiceBaceInterface;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ResponseBody body =(ResponseBody) msg.obj;
            if("0".equals(body.base.code)){
                onServiceBaceInterface.onSuccess(body);
            }else{
                onServiceBaceInterface.onFailed(body.base.msg);
            }
        }
    };
    @Override
    public void getQiuGouData(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.DEMANDLIST;
        params.GETTYPE = "1";
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(), params, DemandList.class).execute();
    }
}
