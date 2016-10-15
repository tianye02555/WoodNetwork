package woodnetwork.hebg3.com.woodnetwork.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.widget.Toast;

import woodnetwork.hebg3.com.woodnetwork.R;


/**
 * 程序版本更新
 * 
 * @author coolszy
 */

public class UpdateManager {
	/**
	 * 下载中
	 */
	private static final int DOWNLOAD = 1;
	/**
	 * 下载结束
	 */
	private static final int DOWNLOAD_FINISH = 2;
	/**
	 * 保存解析的XML信息
	 */
	HashMap<String, String> mHashMap;
	/**
	 * 下载保存路径
	 */
	private String mSavePath;
	/**
	 * 记录进度条数量
	 */
	private int progress;
	/**
	 * 是否取消更新
	 */
	private boolean cancelUpdate = false;

	private Context mContext;
	/**
	 * 更新进度条
	 */
	private ProgressDialog  softUpdate;
	private Dialog mDownloadDialog;
	/**
	 * 从服务器端获取的版本号、下载路径、版本名称
	 */
	private String versionId, versionUrl, versionName;
	/**
	 * 那个页面请求的标示
	 */
	private int flag;
	/**
	 * 强制更新
	 */
	private String force;

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {

			switch (msg.what) {
			// 正在下载
			case DOWNLOAD:
				// 设置进度条位置
			    softUpdate.setProgress(progress);
				break;
			case DOWNLOAD_FINISH:
				// 安装文件
				installApk();
				break;

			default:
				break;
			}
		};
	};

	public UpdateManager(Context context, String serviceCode, String url,
			String versionName, int flag,String force) {
		this.mContext = context;
		this.versionId =serviceCode;
		this.versionUrl = url;
		this.versionName = versionName;
		this.flag = flag;
		this.force=force;
	}

	/**
	 * 检测软件更新
	 */
	public void checkUpdate() {
		if (isUpdate()) {
			// 显示提示对话框
			showNoticeDialog();
		} else {
			if (flag == 1) {
				Toast.makeText(mContext,"已经是最新版本",
						Toast.LENGTH_LONG).show();
			} else {

			}

		}
	}

	/**
	 * 检查软件是否有更新版本
	 * 
	 * @return
	 */
	private boolean isUpdate() {

		// 获取程序版本号
		PackageManager manager = mContext.getPackageManager();// 程序包管理器
		PackageInfo info = null; // 程序包信息
		try {
			info = manager.getPackageInfo(mContext.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		// 获取PackageInfo对象就可以获取版本名称和版本号
		int versionCode = info.versionCode;// 获取版本号
		// 假设目前从服务器端获取的版本号为 2
		int serviceCode = Integer.parseInt(versionId);
		if (null != versionId) {
			// 版本判断
			if (serviceCode > versionCode) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 获取软件版本号
	 * 
	 * @param context
	 * @return
	 */
	private int getVersionCode(Context context) {
		int versionCode = 0;
		try {
			// 获取软件版本号，对应AndroidManifest.xml下android:versionCode
			versionCode = context.getPackageManager().getPackageInfo(
					"com.szy.update", 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}

	/**
	 * 显示软件更新对话框
	 */
	private void showNoticeDialog() {
		// 构造对话框
		Builder builder = new Builder(mContext);
		builder.setTitle("软件更新");
		builder.setMessage("发现新的版本，是否更新？");
//		View view=LayoutInflater.from(mContext).inflate(R.layout.update_software, null);
//		ListView listView=(ListView)view.findViewById(R.id.update_software_listview);
//		List <String> list=new ArrayList<String>();
//		list.add("1· 修改but");
//		list.add("2· 修改butsafsf");
//		list.add("3· 修改butfafsdf");
//		list.add("4· 修改but213123");
//		list.add("5· 修改butdfsdfsa");
//		
//		listView.setAdapter(new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1 , list));
//		builder.setView(view);
		// 更新
		builder.setPositiveButton("马上更新",
				new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 显示下载对话框
						showDownloadDialog();
					}
				});
		if("0".equals(force)){//1为强制更新，0 正常更新
			// 稍后更新
			builder.setNegativeButton("稍后更新",
					new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			}
		Dialog noticeDialog = builder.create();
		noticeDialog.setCanceledOnTouchOutside(false);
		noticeDialog.setCancelable(false);
		noticeDialog.show();
	}

	/**
	 * 显示软件下载对话框
	 */
	private void showDownloadDialog() {
		// 构造软件下载对话框
	    softUpdate=new ProgressDialog(mContext);
		softUpdate.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	    softUpdate.setTitle("提示");//标题
		softUpdate.setMessage("正在下载请稍后……");
//	    softUpdate.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//设置水平进度条\
	    softUpdate.setMax(100);//设置最大进度
	    softUpdate.setCanceledOnTouchOutside(false);//是否允许点击对话框外取消对话框
	    softUpdate.setCancelable(true);//是否允许点击back取消对话框
	    softUpdate.setButton(DialogInterface.BUTTON_NEGATIVE,"取消下载", new OnClickListener() {
            
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
               arg0.dismiss();
            // 设置取消状态
             cancelUpdate = true;
            }
        });
	    softUpdate.show();
		// 下载文件
		downloadApk();
	}

	/**
	 * 下载apk文件
	 */
	private void downloadApk() {
		// 启动新线程下载软件
		new downloadApkThread().start();
	}

	/**
	 * 下载文件线程
	 * 
	 * @author coolszy
	 * @date 2012-4-26
	 * @blog http://blog.92coding.com
	 */
	private class downloadApkThread extends Thread {
		@Override
		public void run() {
			try {
				// 判断SD卡是否存在，并且是否具有读写权限
				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					// 获得存储卡的路径
					String sdpath = Environment.getExternalStorageDirectory()
							+ "/";
					mSavePath = sdpath + "download";
					URL url = new URL(versionUrl);
					// 创建连接
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn .setRequestProperty("Accept-Encoding", "identity");
//					conn.connect();
					// 获取文件大小
					int length = conn.getContentLength();
					// 创建输入流--读取数据
					InputStream is = conn.getInputStream();

					File file = new File(mSavePath);
					// 判断文件目录是否存在
					if (!file.exists()) {
						file.mkdir();// 只能在已经存在的目录中创建创建文件夹。
					}
					// File apkFile = new File(mSavePath, mHashMap.get("name"));
					File apkFile = new File(mSavePath, versionName);// 第二个参数为下载apk的名字
					FileOutputStream fos = new FileOutputStream(apkFile); // 以字节流的方式保存到所指定文件
					int count = 0;
					// 缓存
					byte buf[] = new byte[1024 * 50];
					// 写入到文件中
					do {
						int numread = is.read(buf);
						count += numread;
						// 计算进度条位置
						progress = (int) (((float) count / length) * 100);
//						if(progress){
//
//						}
//						progress +=10;
						// 更新进度
						mHandler.sendEmptyMessage(DOWNLOAD);
						if (numread <= 0) {
							// 下载完成
							mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
							break;
						}
						// 写入文件
						fos.write(buf, 0, numread);
					} while (!cancelUpdate);// 点击取消就停止下载.
					fos.close();
					is.close();
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 取消下载对话框显示
			softUpdate.dismiss();
		}
	};

	/**
	 * 安装APK文件
	 */
	private void installApk() {
		File apkfile = new File(mSavePath, versionName);// 第二个参数为apk的名字
		if (!apkfile.exists()) {
			return;
		}
		// 通过Intent安装APK文件
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
				"application/vnd.android.package-archive");
		mContext.startActivity(i);
	}
}
