package woodnetwork.hebg3.com.woodnetwork.Interface;

/**
 * Created by ty on 2016/8/15 0015.
 */

public interface BaseView<T> {
    /**
     * 设置presenter
     * @param presenter
     */
    void setPresenter(T presenter);
    /**
     *显示Progressbar
     */
    void showProgress();
    /**
     *关闭Progressbar
     */
    void closeProgress();
    /**
     *显示信息
     */
    void showMessage(String string);
}
