package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;

/**
 * Created by ty on 2016/9/18 0018.
 */

public interface OrderReceiveModelInterface {
    /**
     * 提交收货信息接口
     * @param
     */
    void submitReceiveOrder(Object object, OnServiceBaceInterface onServiceBaceInterface);
}
