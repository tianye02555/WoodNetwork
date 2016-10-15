package woodnetwork.hebg3.com.woodnetwork.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.activity.ShowNetPictureActivity;
import woodnetwork.hebg3.com.woodnetwork.WoDe.activity.SettingActivity;
import woodnetwork.hebg3.com.woodnetwork.net.Const;

/**
 * 工具类
 */
@SuppressLint({"SimpleDateFormat", "DefaultLocale"})
public class CommonUtils {

    private final static String customTagPrefix = "WoodNetwork";// tag前缀
    public static Context mContext = null;
    public static Toast mToast = null;
    public static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private static List<Activity> activityList = new ArrayList<Activity>();

//	/** 头像配置 */
//	public static final DisplayImageOptions displayHeaderOptions = new DisplayImageOptions.Builder()
//			// .showStubImage(R.drawable.ic_launcher)
//			.showImageForEmptyUri(R.drawable.default_avatar).showImageOnLoading(R.drawable.default_avatar)
//			// 加载时显示选择进度条
//			.showImageOnFail(R.drawable.default_avatar)
//			// 加载失败时显示缺省头像
//			.imageScaleType(ImageScaleType.EXACTLY).cacheInMemory(false).cacheOnDisk(isExistSdcard())
//			.bitmapConfig(Bitmap.Config.RGB_565).resetViewBeforeLoading(true).build();

//	public static void showToast(Context context, String msg) {
//		if (context != null && !TextUtils.isEmpty(msg)) {
//			Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
//			if (DEBUG)
//				Log.i(generateTag(), msg);
//		}
//	}
//
//	public static void showToast(Context context, int strId) {
//		if (context != null) {
//			Toast.makeText(context, strId, Toast.LENGTH_SHORT).show();
//			if (DEBUG)
//				Log.i(generateTag(), context.getString(strId));
//		}
//	}

    /**
     * 生成日志tag
     */
    private static String generateTag() {
        StackTraceElement caller = Thread.currentThread().getStackTrace()[4];
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }

    public static void log(String msg) {
        if (true)
            Log.i(generateTag(), msg);
    }

    public static void log(Exception e) {
        if (true)
            Log.i(generateTag(), e.toString(), e.getCause());
    }

    public static void log(Throwable tr) {
        if (true)
            Log.i(generateTag(), tr.toString(), tr);
    }


    /**
     * 获取toast对象，防止重复显示时间过长
     *
     * @param context 上下文对象 区别toast的标识
     * @param msg     需要提示的内容
     * @return
     */
    public static void showToast(Context context, String msg) {
        if (CommonUtils.mContext == context) {
            CommonUtils.mToast.setText(msg);
        } else {
            CommonUtils.mContext = context;
            CommonUtils.mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }
        CommonUtils.mToast.show();
    }

    /**
     * 获取toast对象，防止重复显示时间过长
     *
     * @param context 上下文对象 区别toast的标识
     * @param res     需要提示的内容id
     * @return
     */
    public static void showToast(Context context, int res) {
        if (CommonUtils.mContext == context) {
            CommonUtils.mToast.setText(context.getResources().getString(res));
        } else {
            CommonUtils.mContext = context;
            CommonUtils.mToast = Toast.makeText(context, context.getResources().getString(res), Toast.LENGTH_SHORT);
        }
        CommonUtils.mToast.show();
    }

    /**
     * 获取应用版本
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            return "";
        }
    }
    /**
     * 获取应用版本
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            return 0;
        }
    }

//	/** 获取屏幕宽度 */
//	@SuppressWarnings("deprecation")
//	public static int getWindowWidth() {
//		WindowManager wm = (WindowManager) g.getInstance().getSystemService(Context.WINDOW_SERVICE);
//		return wm.getDefaultDisplay().getWidth();
//	}
//
//	/** 获取屏幕高度 */
//	@SuppressWarnings("deprecation")
//	public static int getWindowHeight() {
//		WindowManager wm = (WindowManager) YTXApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
//		return wm.getDefaultDisplay().getHeight();
//	}

    public static int dipToPixel(Context context, int nDip) {
        final WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return (int) (dm.density * nDip);
    }

    /**
     * 检测网络是否可用
     *
     * @param context
     * @return boolean 如果当前网络可用则返回true，否则返回false
     */
    public static boolean isNetWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 检测WiFi是否可用
     */
    public static boolean isWiFiActive(Context inContext) {
        Context context = inContext.getApplicationContext();
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getTypeName().equals("WIFI") && info[i].isConnected()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

//	/** 是否需要省流量 */
//	public static boolean isSaveFlow(Context context) {
//		boolean blnSaveFlow;
//		if (isWiFiActive(context))
//			blnSaveFlow = false;
//		else {
//			PreferenceDao pd = new PreferenceDao(context);
//			blnSaveFlow = pd.isSaveFlow();
//		}
//		return blnSaveFlow;
//	}

    /**
     * 检测Sdcard是否存在
     *
     * @return
     */
    public static boolean isExistSdcard() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
            return true;
        else
            return false;
    }

//	/** 显示登录对话框 */
//	public static void showSdcardNotFoundDialog(Context context) {
//		new AlertDialog.Builder(context).setTitle(R.string.tip).setMessage(R.string.sdcard_is_not_ok)
//				.setPositiveButton(R.string.ok, null).show();
//	}

    @SuppressWarnings("deprecation")
    public static String getTopActivity(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
        if (runningTaskInfos != null)
            return runningTaskInfos.get(0).topActivity.getClassName();
        else
            return "";
    }

    /**
     * 获取输入框里的内容
     */
    public static String getEditTextString(EditText et) {
        return et.getText().toString().trim();
    }

    /**
     * 从网络路径里获取文件名称
     */
    public static String getFilePathFromString(String param) {
        return param.substring(param.lastIndexOf("/") + 1);
    }


    /**
     * 判断字符串是否为手机号
     * <p>
     * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
     *
     * @param param 要判断的字符串
     * @return boolean 如果是有效的手机号返回true，否则返回false
     */
    public static boolean isMobileNO(String param) {
        Pattern p = Pattern.compile("^((17[0-9])|(13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(param);
        return m.matches();
    }

    /**
     * 判断字符串是否为QQ号 [1-9][0-9]{4,}
     */
    public static boolean isQQ(String param) {
        Pattern p = Pattern.compile("[1-9][0-9]{4,}");
        Matcher m = p.matcher(param);
        return m.matches();
    }

    /**
     * 版本字符串比较
     *
     * @param v1
     * @param v2
     * @return v1大于v2返回1;v1等于v2返回0;v1小于v2返回-1
     */
    public static int versionNameCompare(String v1, String v2) {
        try {
            String[] tmp1 = v1.split("\\.");
            String[] tmp2 = v2.split("\\.");
            if (tmp1.length != tmp2.length) {
                if (tmp1.length > tmp2.length) {
                    tmp2 = fixStringArray(tmp2, tmp1.length);
                } else {
                    tmp1 = fixStringArray(tmp1, tmp2.length);
                }
            }
            int len = tmp1.length;
            for (int i = 0; i < len; i++) {
                int a = Integer.parseInt(tmp1[i]);
                int b = Integer.parseInt(tmp2[i]);
                if (a == b) {
                    continue;
                } else {
                    if (a > b) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static String[] fixStringArray(String[] arr, int length) {
        String[] tmp = Arrays.copyOf(arr, length);
        for (int i = 0; i < tmp.length; i++) {
            if (TextUtils.isEmpty(tmp[i])) {
                tmp[i] = "0";
            }
        }
        return tmp;
    }

    /**
     * 安装应用
     *
     * @param context
     * @param uri     应用apk的uri
     */
    public static void installAPK(Context context, Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 从map获取json字符串
     *
     * @param param Map<String, String> param
     * @return String
     */
    public static String getJsonString(Map<String, String> param) {
        JsonObject jo = new JsonObject();
        for (String key : param.keySet())
            jo.addProperty(key, param.get(key));
        return jo.toString();
    }


    /**
     * 比较两个list是否相等
     */
    public static <T extends Comparable<T>> boolean compare(List<T> a, List<T> b) {
        if (a.size() != b.size())
            return false;
        Collections.sort(a);
        Collections.sort(b);
        for (int i = 0; i < a.size(); i++) {
            if (!a.get(i).equals(b.get(i)))
                return false;
        }
        return true;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;
        try {
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            bitmap = Bitmap.createBitmap(width, height,
                    drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, width, height);
            drawable.draw(canvas);
        } catch (Exception e) {

        }
        return bitmap;
    }

    /**
     * 验证字符串是否只由字符或数字组成 "^[A-Za-z0-9]+$"
     *
     * @param param 要判断的字符串
     * @return boolean 如果是只由字符或数字组成返回true，否则返回false
     */
    public static boolean isOnlyCharOrNumber(String param) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
        Matcher m = pattern.matcher(param);
        return m.matches();
    }

    /**
     * 按照指定格式获取日期
     *
     * @param format       格式"yyyy-MM-dd HH-mm-ss" HH为24  hh为12
     * @param milliseconds 毫秒
     * @return
     */
    public static String getDate(String format, long milliseconds) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(new Date(milliseconds));
    }

    /**
     * 添加activity到管理集合
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 清除所有activity
     */
    public static void removeActivity() {
        for (Activity activity : activityList) {
            activity.finish();
        }
    }

    /**
     * 判断是否为数字
     *
     * @param string
     * @return
     */
    public static boolean isNumber(String string) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(string);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 获取文件夹大小
     *
     * @param file File实例
     * @return long
     */
    public static long getFolderSize(File file) {

        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);

                } else {
                    size = size + fileList[i].length();

                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block  
            e.printStackTrace();
        }
        //return size/1048576;
        return size;
    }

    /**
     * 删除文件夹下所有内容
     *
     * @param root 指定文件夹
     */
    public static void deleteAllFiles(File root) {
        File files[] = root.listFiles();
        if (files != null)
            for (File f : files) {
                if (f.isDirectory()) { // 判断是否为文件夹  
                    deleteAllFiles(f);
                    try {
                        f.delete();
                    } catch (Exception e) {
                    }
                } else {
                    if (f.exists()) { // 判断是否存在  
                        deleteAllFiles(f);
                        try {
                            f.delete();
                        } catch (Exception e) {
                        }
                    }
                }
            }
    }

    /**
     * 根据url地址获取okhttp的builder
     *
     * @param
     */
    public static String getParamString(Object pagram) {
        return new Gson().toJson(pagram);
    }

    /**
     * 获取请求对象
     *
     * @param object 参数对象
     * @return 请求对象
     */
    public static MyRequestInfo getRequestInfo(Object object, Object object2) {
        MyRequestInfo requestInfo = new MyRequestInfo();
        requestInfo.req = object;
        requestInfo.req_meta = object2;
        return requestInfo;
    }

    public static boolean isOnlyWIFIDownLoadPic(Context context) {

        SharePreferencesUtils sharePreferences = SharePreferencesUtils.getSharePreferencesUtils(context);
        return (Boolean) sharePreferences.getData("isdown", false);
    }

    /**
     * 加载图片  分是否要求wifi网络
     *
     * @param uri     图片地址
     * @param view    控件
     * @param context 上下文
     * @param isWIFI  是否要求wifi
     */
    public static void displayImage(Uri uri, SimpleDraweeView view, Context context, boolean isWIFI) {
        if (null != uri && !"".equals(uri) && null != view && !"".equals(view)) {
            if (isWIFI) {//要求在wifi下加载
                if (CommonUtils.isWiFiActive(context)) {//判断是否是wifi
                    view.setImageURI(uri);
                    return;
                } else {
                    showToast(context, "无WIFI网络！");
                    return;
                }
            } else {//在任何网络下
                view.setImageURI(uri);
            }

        }
    }
    /**
     * 加载图片  分是否要求wifi网络
     * @param uri 图片地址
     * @param view 控件
     * @param context 上下文
     * @param isWIFI 是否要求wifi
     */
    public static void displayImage(String uri,ImageView view,Context context,boolean isWIFI) {
        if (null != uri && !"".equals(uri) && null != view && !"".equals(view)) {
            if(isWIFI){//要求在wifi下加载
                if(CommonUtils.isWiFiActive(context)){//判断是否是wifi
                    Picasso.with(context).load(uri).into(view);
                    return;
                }else{
                    return;
                }
            }else{//在任何网络下
                Picasso.with(context).load(uri).into(view);
            }

        }
    }
    /**
     * 调用图片预览界面
     *
     * @param  context    activity调用者活动
     * @param  listPictureUrl 照片文件的路径的集合
     * @param imgPosition 界面最初显示是哪张图片
     */
    public static void launchNetPictureShow(Context context, List<String> listPictureUrl, int imgPosition) {
        if (!CommonUtils.isExistSdcard()) {
            CommonUtils.showToast(context, "sd卡不存在");
            return;
        }
        Intent intent = new Intent(context, ShowNetPictureActivity.class);
        intent.putExtra(ShowNetPictureActivity.LS_PHOTOFILEPATH, (Serializable) listPictureUrl);
        intent.putExtra(ShowNetPictureActivity.IMG_POSITION, imgPosition);
        context.startActivity(intent);
    }


    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 旋转图片
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        //旋转图片 动作
        Matrix matrix = new Matrix();
        ;
        matrix.postRotate(angle);
        System.out.println("angle2=" + angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    public static Bitmap getSmallAndRightBitmap(String filsPath) {
        /**
         * 获取图片的旋转角度，有些系统把拍照的图片旋转了，有的没有旋转
         */
        int degree = readPictureDegree(filsPath);

        BitmapFactory.Options opts = new BitmapFactory.Options();//获取缩略图显示到屏幕上
        opts.inSampleSize = 2;
        Bitmap cbitmap = BitmapFactory.decodeFile(filsPath, opts);

        /**
         * 把图片旋转为正的方向
         */

        return rotaingImageView(degree, cbitmap);
    }

    /**
     * 保存bitmap到文件
     *
     * @param bmp 要保存的bitmap
     * @return String 文件保存的路径
     */
    public static String saveBitmapToFile(Bitmap bmp) {
        String filePath = Const.PICTURE_PATH + System.currentTimeMillis()
                + ".png";
        File file = new File(filePath);
        try {
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            CommonUtils.log(e);
            filePath = "";
        }
        return filePath;
    }
}
