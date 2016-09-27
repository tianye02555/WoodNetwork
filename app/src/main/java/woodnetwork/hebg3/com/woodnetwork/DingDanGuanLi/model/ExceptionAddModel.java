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

public class ExceptionAddModel implements ExceptionAddModelInterface {
    private OnServiceBaceInterface onServiceBaceInterface;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Base base = (Base) msg.obj;
            switch (msg.what) {
                case 0://异常提交接口
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
    public void submitExceptionOrder(Context context,HashMap<String, String> params, HashMap<String, File> files, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface = onServiceBaceInterface;
        AsyncTaskForUpLoadFilesNew at = new AsyncTaskForUpLoadFilesNew(
                context, "http://192.168.2.12:12306/dev/wood/v1/exception/add", params, files,
                handler.obtainMessage(0));
        at.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "0");
    }
}
