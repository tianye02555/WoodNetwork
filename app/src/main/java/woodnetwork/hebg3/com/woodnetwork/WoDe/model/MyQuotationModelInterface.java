package woodnetwork.hebg3.com.woodnetwork.WoDe.model;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;

/**
 * Created by ty on 2016/9/6 0006.
 */

public interface MyQuotationModelInterface {
    /**
     * 获取我的报价列表接口
     * @param object
     * @param onServiceBaceInterface
     */
    void getMyQuotationData(Object object, OnServiceBaceInterface onServiceBaceInterface);
}
