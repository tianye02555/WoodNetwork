package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model;

import android.content.Context;

import java.io.File;
import java.util.HashMap;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;

/**
 * Created by ty on 2016/9/18 0018.
 */

public interface ExceptionAddModelInterface {
    /**
     * 提交异常信息接口
     * @param
     */
    void submitExceptionOrder(Context context, HashMap<String, String> params, HashMap<String, File> files, OnServiceBaceInterface onServiceBaceInterface);
}
