package woodnetwork.hebg3.com.testapplication.mvpDemo;

import android.content.Context;

/**
 * Created by ty on 2016/10/25 0025.
 */

public interface IBaseView {


    /**
     * 显示Progressbar
     */
    void showProgress();

    /**
     * 关闭Progressbar
     */
    void closeProgress();

    /**
     * 显示信息
     */
    void showMessage(String string);




}
