package woodnetwork.hebg3.com.woodnetwork.WoDe.model;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;

/**
 * Created by ty on 2016/9/6 0006.
 */

public interface ReportsModelInterface {
    /**
     * 获取统计表URL接口
     *
     */
    void getReportsURL(Object object, OnServiceBaceInterface onServiceBaceInterface);
}
