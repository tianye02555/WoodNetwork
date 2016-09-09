package woodnetwork.hebg3.com.woodnetwork.WoDe.model;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;

/**
 * Created by ty on 2016/9/6 0006.
 */

public interface AdviceModelInterface {
    /**
     * 提交用户建议接口
     * @param object
     * @param onServiceBaceInterface
     */
    void submitData(Object object, OnServiceBaceInterface onServiceBaceInterface);

    /**
     * 获取建议分类接口
     * @param object
     * @param onServiceBaceInterface
     */
    void getGuestbookTypeData(Object object, OnServiceBaceInterface onServiceBaceInterface);
}
