package woodnetwork.hebg3.com.woodnetwork;

/**
 * Created by ty on 2016/8/15 0015.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);
    void showProgress();
    void closeProgress();
    void showfailMessage();
}
