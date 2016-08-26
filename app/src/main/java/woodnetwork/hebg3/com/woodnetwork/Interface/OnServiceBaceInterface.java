package woodnetwork.hebg3.com.woodnetwork.Interface;

/**
 * Created by ty on 2016/8/23 0023.
 */

public interface OnServiceBaceInterface {
    /**
     * 访问服务器成功
     * @param object
     */
    public void onSuccess(Object object);

    /**
     * 访问服务器失败
     */
    public void onFailed(String string);
}
