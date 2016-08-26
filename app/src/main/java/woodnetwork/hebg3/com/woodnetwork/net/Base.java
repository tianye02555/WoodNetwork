package woodnetwork.hebg3.com.woodnetwork.net;

import java.io.Serializable;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;

/** 用于解析服务器返回的外层数据 */
public class Base implements Serializable {
	private static final long serialVersionUID = -3859826962412135268L;
	public int responseCode = 0;// 网络连接信息
	@Expose
	public String code = "999";// 错误信息
	@Expose
	public JsonElement res;// 数据
	@Expose
	public String msg = "暂无数据";// 错误信息
	public String content;// 服务器返回的原始json数据
	public ClientParams params;// 当次的请求参
	

	@Override
	public String toString() {
		return "Base [responseCode=" + responseCode + ", code=" + code + ", res=" + res + ", msg=" + msg
				+ ", content=" + content + ", params=" + params + "]";
	}

}
