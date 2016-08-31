package woodnetwork.hebg3.com.woodnetwork.QiuGou.model;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;

/**
 * Created by ty on 2016/8/31 0031.
 */

public interface WoYaoBaoJiaModelInterface {
    /**
     * 获取我要报价数据
     * @param object
     * @param onServiceBaceInterface
     */
    void getWoYaoBaoJiaData(Object object, OnServiceBaceInterface onServiceBaceInterface);

    /**
     * 保存报价数据
     * @param object
     * @param onServiceBaceInterface
     */
    void saveWoDeBaoJia(Object object, OnServiceBaceInterface onServiceBaceInterface);
}
