package woodnetwork.hebg3.com.woodnetwork.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;

import com.google.gson.JsonSyntaxException;

import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;

/** 网络请求线程;用于获取网络json数据并转换成�?要的对象或集�? */
public class NetTask extends AsyncTask<Void, Void, ResponseBody> {

	private Message msg;
	private ClientParams params;// 当次请求的请求参�?
	private Class<?> clazz;// 对象
	private Type typeToken;// 结合
	private ResponseBody body;

	private interface OnEntityLoadCompleteListener {
		/** 数据正常获取时调�? */
		void onEntityLoadComplete(Base entity);

		/** 数据未能正常加载时调�? */
		void onError(Base entity);
	}

	private OnEntityLoadCompleteListener listener = new OnEntityLoadCompleteListener() {

		@Override
		public void onEntityLoadComplete(Base entity) {
			if (body == null)
				body = new ResponseBody();
			if ("0".equals(entity.code)||"1".equals(entity.code)) {
				try {
					if (clazz != null)
						body.obj = CommonUtils.gson.fromJson(entity.res,  clazz);
					else if (typeToken != null)
						body.list = CommonUtils.gson.fromJson(entity.res, typeToken);
				} catch (Exception e) {
					CommonUtils.log(e);
				}
			}
			body.base = entity;

		}

		@Override
		public void onError(Base entity) {
			if (body == null)
				body = new ResponseBody();
			body.base = entity;
			ProgressUtils.hide();
		}
	};

	/** 不需要转换数�? */
	public NetTask(Message msg, ClientParams params) {
		this.msg = msg;
		this.params = params;
		CommonUtils.log(params.toString());
	}

	/**
	 * 
	 * @param msg  消息对象
	 * @param params  访问服务器附带的参数
	 * @param clazz    接收的json 需要转化的对象
	 */
	public NetTask(Message msg, ClientParams params, Class clazz) {
		this.msg = msg;
		this.params = params;
		this.clazz = clazz;
		CommonUtils.log(params.toString());
	}

	/** �?要把数据转换成集�? */
	public NetTask(Message msg, ClientParams params, Type typeToken) {
		this.msg = msg;
		this.params = params;
		this.typeToken = typeToken;
		CommonUtils.log(params.toString());
	}

	@Override
	protected void onPostExecute(ResponseBody result) {
		super.onPostExecute(result);
		try {
			msg.obj = result;
			msg.sendToTarget();
		} catch (Exception e) {
			CommonUtils.log(e);
		}
	}

	@Override
	protected ResponseBody doInBackground(Void... arg0) {
		doNetRequest(params, listener);
		return body;
	}

	private void doNetRequest(ClientParams params, OnEntityLoadCompleteListener listener) {
		String url = "", content = "";
		Base base = new Base();
		HttpURLConnection connection = null;
		OutputStream os = null;
		InputStream is = null;
		try {
			if (ClientParams.HTTP_GET.equals(params.http_method)) {// get方式
				if("0".equals(params.GETTYPE)){
					url = params.scheme + params.authority+params.getMethod ;
					System.out.println(url);
				}else{
				url = params.scheme + params.authority + params.getMethod  + params.params;
				System.out.println(url);
				}
			} else {// post方式
				url = params.scheme + params.authority + params.getMethod;
			}
			CommonUtils.log(url);
			connection = (HttpURLConnection) new URL(url).openConnection();
			if("POST".equals(params.http_method)) {
				connection.setDoInput(true);
				connection.setDoOutput(true);
			}
			connection.setUseCaches(false);
			connection.setRequestMethod(params.http_method);
			connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			connection.setConnectTimeout(params.timeout);
			connection.setReadTimeout(params.timeout);
			connection.connect();
			if (ClientParams.HTTP_POST.equals(params.http_method)) {// post方式
				os = connection.getOutputStream();
				os.write(params.params.getBytes("UTF-8"));
				os.flush();
//				CommonUtils.log(params.getParams());
			}

			CommonUtils.log("responseCode: " + connection.getResponseCode());
			switch (connection.getResponseCode()) {
			case HttpURLConnection.HTTP_OK:// 连接成功
				is = connection.getInputStream();
				content = convertStreamToString(is);
				base = CommonUtils.gson.fromJson(content, Base.class);
				base.responseCode = connection.getResponseCode();
				CommonUtils.log(content == null ? "null" : content);
				base.params = params;
				listener.onEntityLoadComplete(base);
				listener = null;// 将listener置空，是为了避免回调多次
				break;
			default:// 连接失败
				if (listener != null) {
					base.responseCode = connection.getResponseCode();
					base.params = params;
					listener.onError(base);
					listener = null;
				}
				break;
			}
		} catch (Exception e) {
			// 发生了异�?
			CommonUtils.log(e);
			if (listener != null) {
				if (e instanceof JsonSyntaxException) {
					CommonUtils.log(content == null ? "null" : content);
					base.msg = "json数据格式错误";
					base.content = content;
				} else if (e instanceof MalformedURLException) {
					base.msg = "URL格式错误";
				} else if (e instanceof UnknownHostException) {
					base.msg = "不能解析服务器地�?";
				} else if (e instanceof SocketTimeoutException) {
					base.msg = "网络不给�?";
				} else if (e instanceof ConnectException) {
					base.msg = "连接失败";
				} else {
					if (base == null) {
						base = new Base();
						base.params = params;
						base.msg = "未知错误";
					} else {
						base.msg = e.getMessage();
					}
				}
				base.params = params;
				listener.onError(base);
				listener = null;
			}
		} finally {
			// 关闭输出输入流�?�连�?
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					CommonUtils.log(e);
				}
				os = null;
			}
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					CommonUtils.log(e);
				}
				is = null;
			}
			if (connection != null) {
				connection.disconnect();
				connection = null;
			}
			if (listener != null) {
				base.params = params;
				listener.onError(base);
			}
		}
	}

	/** 从输入流中获取字符串 */
	private String convertStreamToString(InputStream is) {
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			CommonUtils.log(e);
		} finally {
			try {
				is.close();
			} catch (Exception e) {
				CommonUtils.log(e);
			}
		}
		return sb.toString();
	}
}
