package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.util.HashMap;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.AsyncTaskForUpLoadFilesNew;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ServiceInterfaceCont;
import woodnetwork.hebg3.com.woodnetwork.net.Base;
import woodnetwork.hebg3.com.woodnetwork.net.ClientParams;
import woodnetwork.hebg3.com.woodnetwork.net.NetTask;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/18 0018.
 */

public class OrderReceiveModel implements OrderReceiveModelInterface {
    private OnServiceBaceInterface onServiceBaceInterface;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Base base = (Base) msg.obj;
            switch (msg.what) {
                case 0://收货接口
                    if ("0".equals(base.code)) {//成功
                        onServiceBaceInterface.onSuccess(base.msg);
                    } else  {//失败
                        onServiceBaceInterface.onFailed(base.msg);
                    }
                    break;
            }

        }
    };

    @Override
    public void submitReceiveOrder(Context context, HashMap<String, String> params, HashMap<String, File> files, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface = onServiceBaceInterface;
        AsyncTaskForUpLoadFilesNew at = new AsyncTaskForUpLoadFilesNew(
                context, "http://192.168.2.12:8080/wood/dev/wood/v1/order/receive", params, files,
                handler.obtainMessage(0));
        at.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "0");
    }


    @Override
    public void submitDeliveryOrder(Context context, HashMap<String, String> params, HashMap<String, File> files, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface = onServiceBaceInterface;
        AsyncTaskForUpLoadFilesNew at = new AsyncTaskForUpLoadFilesNew(
                context, "http://192.168.2.12:8080/wood/dev/wood/v1/order/delivery", params, files,
                handler.obtainMessage(0));
        at.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "0");
    }
}
