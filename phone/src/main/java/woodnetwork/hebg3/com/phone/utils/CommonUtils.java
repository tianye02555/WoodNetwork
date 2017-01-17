package woodnetwork.hebg3.com.phone.utils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


/** 工具类 */
public class CommonUtils {

	private final static String customTagPrefix = "JINGSHAHE";// tag前缀
	public static final boolean DEBUG = false;// 调试模式

	public static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

//	/** 头像配置 */
//	public static final DisplayImageOptions displayHeaderOptions = new DisplayImageOptions.Builder()
//			// .showStubImage(R.drawable.ic_launcher)
//			.showImageForEmptyUri(R.drawable.default_avatar).showImageOnLoading(R.drawable.default_avatar)
//			// 加载时显示选择进度条
//			.showImageOnFail(R.drawable.default_avatar)
//			// 加载失败时显示缺省头像
//			.imageScaleType(ImageScaleType.EXACTLY).cacheInMemory(false).cacheOnDisk(isExistSdcard())
//			.bitmapConfig(Bitmap.Config.RGB_565).resetViewBeforeLoading(true).build();

	public static void showToast(Context context, String msg) {
		if (context != null && !TextUtils.isEmpty(msg)) {
			Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
//			if (DEBUG)
//				Log.i(generateTag(), msg);
		}
	}

	public static void showToast(Context context, int strId) {
		if (context != null) {
			Toast.makeText(context, strId, Toast.LENGTH_SHORT).show();
//			if (DEBUG)
//				Log.i(generateTag(), context.getString(strId));
		}
	}

	/** 生成日志tag */
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
		if (DEBUG)
			Log.i(generateTag(), msg);
	}

	public static void log(Exception e) {
		if (DEBUG)
			Log.i(generateTag(), e.toString(), e.getCause());
	}

	public static void log(Throwable tr) {
		if (DEBUG)
			Log.i(generateTag(), tr.toString(), tr);
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

	/** 检测WiFi是否可用 */
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

	/** 获取输入框里的内容 */
	public static String getEditTextString(EditText et) {
		return et.getText().toString().trim();
	}

	/** 从网络路径里获取文件名称 */
	public static String getFilePathFromString(String param) {
		return param.substring(param.lastIndexOf("/") + 1);
	}

	

	/**
	 * 判断字符串是否为手机号
	 * 
	 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
	 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
	 * 
	 * 
	 * @param param
	 *            要判断的字符串
	 * @return boolean 如果是有效的手机号返回true，否则返回false
	 * 
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
	 * @param uri
	 *            应用apk的uri
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
	 * @param param
	 *            Map<String, String> param
	 * @return String
	 */
	public static String getJsonString(Map<String, String> param) {
		JsonObject jo = new JsonObject();
		for (String key : param.keySet())
			jo.addProperty(key, param.get(key));
		return jo.toString();
	}

	

	

	/** 比较两个list是否相等 */
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
//			log(e);
		}
		return bitmap;
	}

	/**
	 * 验证字符串是否只由字符或数字组成 "^[A-Za-z0-9]+$"
	 * 
	 * @param param
	 *            要判断的字符串
	 * @return boolean 如果是只由字符或数字组成返回true，否则返回false
	 */
	public static boolean isOnlyCharOrNumber(String param) {
		Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
		Matcher m = pattern.matcher(param);
		return m.matches();
	}
}
