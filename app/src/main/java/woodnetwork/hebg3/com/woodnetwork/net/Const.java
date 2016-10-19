package woodnetwork.hebg3.com.woodnetwork.net;

import java.io.File;

import android.os.Environment;

/**
 * 常量
 */
public class Const {
    public static final String APPNAME = "MULIANWANG";

    //云
    public static final String AUTHORITY = "www.woodlian.cn/dev/wood/v1";
    //外网图片前缀
    public static final String PICTURE = "http://www.woodlian.cn/";
    //外网图片前缀
    public static final String PICTURE_LUNBOTU = "http://www.woodlian.cn";


//    //本地
//    public static final String AUTHORITY = "192.168.2.12:8080/wood/dev/wood/v1";
//    //图片前缀
//    public static final String PICTURE = "http://192.168.2.12:8080/wood/";
//    //图片前缀
//    public static final String PICTURE_LUNBOTU = "http://192.168.2.12:8080";

//        //本地
//    public static final String AUTHORITY = "192.168.2.132:8006/wood/dev/wood/v1";
//    //图片前缀
//    public static final String PICTURE = "http://192.168.2.132:8006/wood/";
//    //图片前缀
//    public static final String PICTURE_LUNBOTU = "http://192.168.2.132:8006";

// 132 8006

    public static final String HTTP = "http://";

    public static final String PICTURE_UPLOAD_EXCEPTIONADD = HTTP + AUTHORITY + "/exception/add";
    public static final String PICTURE_UPLOAD_ORDERRECEIVE = HTTP + AUTHORITY + "/order/receive";
    public static final String PICTURE_UPLOAD_ORDERDELIVERY = HTTP + AUTHORITY + "/order/delivery";
    public static final int DEFAULT_TIMEOUT = 15 * 1000;// 默认的超时时间
    public static final int DOWNLOAD_UPLOAD_TIMEOUT = 50 * 1000;// 下载上传文件的超时时间

    // 手机本地的文件存放位置
    public static final String BASE_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator
            + APPNAME + File.separator;
    public static final String PICTURE_PATH = BASE_PATH + "picture" + File.separator;// 存放图片
    public static final String FILE_PATH = BASE_PATH + "file" + File.separator;// 存放文件


}
