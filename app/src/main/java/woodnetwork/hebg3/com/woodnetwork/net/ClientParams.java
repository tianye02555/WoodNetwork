package woodnetwork.hebg3.com.woodnetwork.net;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ClientParams implements Serializable {
	private static final long serialVersionUID = -1325540944167846125L;
	/**
	 * post方式
	 */
	public final static String HTTP_POST = "POST";
	/**
	 * get方式
	 */
	public final static String HTTP_GET = "GET";
	// param String login 调用接口参数
	// jsonParam String 访问接口json数据
	// token String 用户登录链接�?
	public static final String PARAM = "param";
	public static final String JSON_PARAM = "jsonParam";
	public static final String TOKEN = "token";
	/**
	 * 
	 */
	public static  String GETTYPE ="1";//0为没有参数 1为有参数
	public static final String EMPTY_LIST_CODE = "10001";// 空数组code�?
	/**
	 * 请求方式
	 */
	public String http_method = HTTP_POST;
	public int timeout = Const.DEFAULT_TIMEOUT;// 超时时间
	/**
	 * 网络协议
	 */
	public String scheme = "http://";
	/**
	 * 方法名
	 */
	public String getMethod = "";
	/**
	 * 访问地址
	 */
	public String authority = Const.AUTHORITY;
//	public String path = Const.COMMON_PATH;// url中路�?
	public String params ="";// 请求参数字符串
	public Map<String, Object> extras = new HashMap<String, Object>();// �?次请求需要携带的额外信息

	/** 拼接多个参数，之间用&连接;param=login&jsonParam={}&token= */
//	public String getParams() {
//		if (params.isEmpty())
//			return "";
//		StringBuilder sb = new StringBuilder();
//		for (String key : params.keySet()) {
//			if (sb.length() == 0)
//				sb.append(key + "=" + params.get(key));
//			else
//				sb.append("&" + key + "=" + params.get(key));
//		}
//		return sb.toString();
//	}

	public String toString() {
		return "ClientParams [http_method=" + http_method + ", timeout=" + timeout + ", scheme=" + scheme
				+ ", authority=" + authority + ", params=" + params + ", extras=" + extras + "]";
	}

}
