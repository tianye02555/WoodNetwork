package Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ty on 2016/8/15 0015.
 */

public class SharePreferencesUtils {
    private static final String FILE_NAME = "app_data";
    private static SharedPreferences mSharedPreferences;// 单例
    private static Context context;

    private SharePreferencesUtils(Context context) {
        mSharedPreferences = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharePreferencesUtils.context=context;
    }

    /**
     * 内部类实现线程安全的单利模式
     */
    private static class SharePreferencesHelper{
        private static SharePreferencesUtils sharePreferencesUtils=new SharePreferencesUtils(context);
    }

    /**
     * 内部获取单利，不对外
     * @return
     */
    private static SharePreferencesUtils getInstance(){
        return SharePreferencesHelper.sharePreferencesUtils;
    }

    /**
     * 外部获取单利对象
     * @return SharePreferencesUtils单利对象
     */
    public static SharePreferencesUtils getSharePreferencesUtils(){
        return getInstance();/**/
    }
    /**
     * 保存数据
     *
     * @param key
     * @param data
     */
    public void saveData(String key, Object data) {
        String type = data.getClass().getSimpleName();

        SharedPreferences.Editor editor = mSharedPreferences.edit();

        if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) data);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) data);
        } else if ("String".equals(type)) {
            editor.putString(key, (String) data);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) data);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) data);
        }

        editor.commit();
    }

    /**
     * 得到数据
     *
     * @param key
     * @param defValue
     * @return
     */
    public Object getData(String key, Object defValue) {

        String type = defValue.getClass().getSimpleName();
        if ("Integer".equals(type)) {
            return mSharedPreferences.getInt(key, (Integer) defValue);
        } else if ("Boolean".equals(type)) {
            return mSharedPreferences.getBoolean(key, (Boolean) defValue);
        } else if ("String".equals(type)) {
            return mSharedPreferences.getString(key, (String) defValue);
        } else if ("Float".equals(type)) {
            return mSharedPreferences.getFloat(key, (Float) defValue);
        } else if ("Long".equals(type)) {
            return mSharedPreferences.getLong(key, (Long) defValue);
        }

        return null;
    }
}
