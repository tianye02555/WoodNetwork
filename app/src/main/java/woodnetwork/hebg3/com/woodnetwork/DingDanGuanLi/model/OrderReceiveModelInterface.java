package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model;

import android.content.Context;

import java.io.File;
import java.util.HashMap;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;

/**
 * Created by ty on 2016/9/18 0018.
 */

public interface OrderReceiveModelInterface {
    /**
     * 提交收货信息接口
     * @param
     */
    void submitReceiveOrder(Context context, HashMap<String, String> params, HashMap<String, File> files, OnServiceBaceInterface onServiceBaceInterface);
    /**
     * 提交发货信息接口
     * @param
     */
    void submitDeliveryOrder(Context context, HashMap<String, String> params, HashMap<String, File> files, OnServiceBaceInterface onServiceBaceInterface);
}
