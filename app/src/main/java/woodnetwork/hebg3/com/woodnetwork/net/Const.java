package woodnetwork.hebg3.com.woodnetwork.net;

import java.io.File;
import android.os.Environment;

/** 常量 */
public class Const {
	public static final String APPNAME = "MULIANWANG";

//	public static final String AUTHORITY = "192.168.2.159:8080/wood/dev/wood/v1";
	//云
	public static final String AUTHORITY = "192.168.2.12:8080/wood/dev/wood/v1";
	//图片前缀
	 public static final String PICTURE = "http//:192.168.2.159:8080/wood/";
	public static final int DEFAULT_TIMEOUT = 15 * 1000;// 默认的超时时间
	public static final int DOWNLOAD_UPLOAD_TIMEOUT = 50 * 1000;// 下载上传文件的超时时间

	// 手机本地的文件存放位置
	public static final String BASE_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator
			+ APPNAME + File.separator;
	public static final String PICTURE_PATH = BASE_PATH + "picture" + File.separator;// 存放图片
	public static final String FILE_PATH = BASE_PATH + "file" + File.separator;// 存放文件

////	public static final String COMMON_PATH = "101.200.77.60/xuejiguanli/Api.php";
//	public static final String COMMON_PATH = "192.168.2.50/xuejiguanli/Api.php";
//	public static final String COMMON_URL = "http://" + COMMON_PATH;
	//云
		public static final String COMMON_PATH = "192.168.2.12:12306/dev/wood/v1/user/login";
}
