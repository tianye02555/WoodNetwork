package woodnetwork.hebg3.com.woodnetwork.net;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;

import com.google.gson.Gson;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * 上传文件类
 * @author Administrator
 *
 */
public class AsyncTaskForUpLoadFilesNew extends
		AsyncTask<Object, Object, Object> {

	public String actionUrl;//提交服务器地址
	public Map<String, String> params;
	public Map<String, File> files;
	public String fileParamName;//文件类型
	public Message m;
	public Gson g = new Gson();
	public Context cont;
	public AsyncTaskForUpLoadFilesNew(Context cont, String actionUrl,
									  Map<String, String> params, Map<String, File> files,
									  String fileParamName, Message m) {
		this.actionUrl = actionUrl;
		this.files = files;
		this.fileParamName = fileParamName;
		this.m = m;
		this.params = params;
		this.cont = cont;
	}

	@Override
	protected String doInBackground(Object... paramss) {
		return post(actionUrl, params, files);
	}

	@Override
	protected void onPostExecute(Object result) {
		Base base = null;// 附件上传 服务器响应的json
		String response = (String) result;
		if (response.equals("-1")) {
			base = new Base();
			base.msg = "提交失败";
			base.code="1";
		} else {
			base = g.fromJson(response, Base.class);
		}
		if (base.code.equals("0")) {
			m.what = 0;
			m.obj = base;
			m.sendToTarget();
		}else{
			m.what = 1;
			m.obj = base;
			m.sendToTarget();
		}
	}

	/**
	 * 附件上传
	 * 
	 * @param actionUrl
	 *            附件上传目标地址url
	 * @param params
	 *            表单内容 包括文本内容
	 * @param files
	 *            附件集合 可以上传多个图片
	 * @return 成功与否
	 * @throws IOException
	 */
	public String post(String actionUrl, Map<String, String> params,
			Map<String, File> files) {
		
		//Map<String, File> files = new h...;
		//

		String BOUNDARY = "——XXXXXXXXXXXXXXXX";
		String PREFIX = "--";// 起始标示
		String LINEND = "\r\n"; // 换行
		String CHARSET = "UTF-8";// 字符集
		String MULTIPART_FROM_DATA = "multipart/form-data";// 表单提交 标示

		try {
			URL uri = new URL(actionUrl);
			HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
			conn.setConnectTimeout(60 * 1000);
			conn.setReadTimeout(60 * 1000);
			conn.setDoInput(true);// 允许输入
			conn.setDoOutput(true);// 允许输出
			conn.setUseCaches(false);
			conn.setRequestMethod("POST"); // Post方式
			conn.setRequestProperty("connection", "keep-alive");
			conn.setRequestProperty("Charsert", "UTF-8");
			conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA
					+ ";boundary=" + BOUNDARY);
			// 首先组拼文本类型的参数
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				sb.append(PREFIX);
				sb.append(BOUNDARY);
				sb.append(LINEND);
				sb.append("Content-Disposition: form-data; name=\""
						+ entry.getKey() + "\"" + LINEND);
				sb.append("Content-Type: text/plain; charset=" + CHARSET
						+ LINEND);
				sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
				sb.append(LINEND);
				sb.append(entry.getValue());
				sb.append(LINEND);
			}
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			BufferedOutputStream bos = new BufferedOutputStream(dos);
			bos.write(sb.toString().getBytes());
			int a=0;
			// 发送文件数据
			if (files != null && files.size() > 0) {
				for (Map.Entry<String, File> file : files.entrySet()) {
					StringBuilder sb1 = new StringBuilder();
					sb1.append(PREFIX);
					sb1.append(BOUNDARY);
					sb1.append(LINEND);
					sb1.append("Content-Disposition: form-data; name=\""
							+ fileParamName+a+ "\"; filename=\"" + file.getKey()
							+ "\"" + LINEND);
					
					sb1.append("Content-Type: multipart/form-data; charset="
							+ CHARSET + LINEND);
					sb1.append(LINEND);
					bos.write(sb1.toString().getBytes("utf-8"));
					InputStream is = new FileInputStream(file.getValue());
					byte[] buffer = new byte[10 * 1024];
					int len = 0;
					while ((len = is.read(buffer)) != -1) {
						bos.write(buffer, 0, len);
					}
					is.close();
					bos.write(LINEND.getBytes());
					a++;
				}
			}
			// 请求结束标志
			byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
			bos.write(end_data);
			bos.flush();
			// 得到响应码
			if (conn.getResponseCode() == 200) {
				InputStream in = conn.getInputStream();

				String json = convertStreamToStringUTF8(in);// 未来教室用的gbk字符集，一般的项目要用utf-8

				bos.close();
				dos.close();
				conn.disconnect();
				return json;
			} else {
				return "-1";
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "-1";
		}
	}

	// 流转字符串 GB2312
	private static String convertStreamToStringUTF8(InputStream is)
			throws IOException {
		StringBuffer sb = new StringBuffer();
		String line = null;

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					"utf-8"));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		is.close();
		return sb.toString();
	}

}
