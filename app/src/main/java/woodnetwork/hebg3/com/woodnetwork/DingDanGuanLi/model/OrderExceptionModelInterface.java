package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.model;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;

/**
 * Created by ty on 2016/9/18 0018.
 */

public interface OrderExceptionModelInterface {
    /**
     * 获取订单异常列表信息接口
     * @param
     */
    void getOrderExceptionList(Object object, OnServiceBaceInterface onServiceBaceInterface);
}
