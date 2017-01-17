package woodnetwork.hebg3.com.phone.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import woodnetwork.hebg3.com.phone.bean.NewTime;
import woodnetwork.hebg3.com.phone.database.FileDataBase;
import woodnetwork.hebg3.com.phone.net.Base;
import woodnetwork.hebg3.com.phone.net.ClientParams;
import woodnetwork.hebg3.com.phone.net.Const;
import woodnetwork.hebg3.com.phone.net.NetTask;
import woodnetwork.hebg3.com.phone.net.AsyncTaskForUpLoadFilesNew;
import woodnetwork.hebg3.com.phone.net.ResponseBody;
import woodnetwork.hebg3.com.phone.utils.CallUtils;
import woodnetwork.hebg3.com.phone.utils.InterfaceUtils;

/**
 * Created by ty on 2016/11/23 0023.
 */

public class RecorderService extends Service {
    private File[] newFiles;
    private FileDataBase fileDataBase;
    private SQLiteDatabase db;
    private SharedPreferences sharedPreferences;
    private List<File> upFiles = new ArrayList<File>();
    private List<File> allFiles = new ArrayList<File>();
    private boolean flag = false;
    private HashMap<String, File> files;
    private HashMap<String, String> params;
    private int a = 0;//文件标记
    public static boolean isLoading = false;//是否正在上传
    private List<Map<String, String>> mapList;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            try {
                switch (msg.what) {
                    case 0:
                        ResponseBody responseBody = (ResponseBody) msg.obj;
                        if ("0".equals(responseBody.base.code)) {
                            files.put(upFiles.get(a).getName(), upFiles.get(a));

                            params.put("count", String.valueOf(allFiles.size()));
                            params.put("mobile", sharedPreferences.getString("phone", ""));
                            AsyncTaskForUpLoadFilesNew at = new AsyncTaskForUpLoadFilesNew(
                                    "http://" + Const.AUTHORITY + InterfaceUtils.RECORD_UPLOAD, params, upFiles.get(a), "photo[]",
                                    handler.obtainMessage(1));
                            at.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "1");

                        } else if ("1".equals(responseBody.base.code)) {
                            //插入操作
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("name", upFiles.get(a).getAbsolutePath());
                            db.insert("file", null, contentValues);
                            a++;
                            if (a < upFiles.size()) {
                                ClientParams params = new ClientParams();
                                params.http_method = ClientParams.HTTP_POST;
                                params.getMethod = InterfaceUtils.RECORD_REPETITION;
                                params.params.put("name", upFiles.get(a).getName());
                                new NetTask(handler.obtainMessage(0), params).execute();
                            } else {
                                isLoading = false;//上传结束  标记还原
                            }

                        }
                        break;
                    case 1:
                        Base base = (Base) msg.obj;
                        if ("0".equals(base.code)) {
//                        插入操作
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("name", upFiles.get(a).getAbsolutePath());
                            db.insert("file", null, contentValues);

                        }
                        a++;
                        if (a < upFiles.size()) {
                            ClientParams params = new ClientParams();
                            params.http_method = ClientParams.HTTP_POST;
                            params.getMethod = InterfaceUtils.RECORD_REPETITION;
                            params.params.put("name", upFiles.get(a).getName());
                            new NetTask(handler.obtainMessage(0), params).execute();
                        } else {
                            isLoading = false;//上传结束  标记还原
                        }

                        break;
                    case 2:
                        ResponseBody responseBody2 = (ResponseBody) msg.obj;
                        if ("0".equals(responseBody2.base.code)) {
                            NewTime newTime = (NewTime) responseBody2.obj;
                            String serviceTime = data(newTime.newtime);
                            String phoneTime="";
//                            Log.w("time",newRecordTime);
                            for(int i=0;i<mapList.size();i++){
//                                Log.w("name",mapList.get(i).get("name"));
//                                Log.w("number", mapList.get(i).get("number"));
//                                Log.w("date", mapList.get(i).get("date"));
//                                Log.w("duration", mapList.get(i).get("duration"));
                                phoneTime=mapList.get(i).get("date");
                                if(comparData(serviceTime,phoneTime)<0){
                                    String date=mapList.get(i).get("date");
                                    date=date.replace("-","");
                                    date=date.replace(" ","_");

                                    String fileName=mapList.get(i).get("name")+"_"+date+".amr";

                                    String filePath=sharedPreferences.getString("path", "/")+"/"+fileName;
                                    File file=new File("/storage/sdcard1/360OS/My Records/Call Records/中国移动_20170110_153816.amr");

                                    if(file.exists()){
Long a=file.lastModified();
                                        Log.w("affasfsadfasdf",data(String.valueOf(a/1000)));
//                                        /storage/sdcard1/360OS/My Records/Call Records/中国移动_20170110_153816.amr
                                    }

                                }
                            }
                        }
                        break;

                    default:
                        break;
                }
            } catch (Exception e) {
                isLoading = false;//发生异常  标记还原
            }
        }

    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //数据库操作  上传成功后执行数据库操作
        fileDataBase = new FileDataBase(getApplicationContext(), "JINSHAHE_File", 1);
        db = fileDataBase.getReadableDatabase();
        sharedPreferences = getApplicationContext().getSharedPreferences("phone", Context.MODE_PRIVATE);
        files = new HashMap<String, File>();
        params = new HashMap<String, String>();
        mapList = CallUtils.getDataList(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(phoneReceiver);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
        registerReceiver(phoneReceiver, filter);
        // 对电话的来电状态进行监听
        TelephonyManager telManager = (TelephonyManager) this
                .getSystemService(Context.TELEPHONY_SERVICE);
        // 注册一个监听器对电话状态进行监听
        telManager.listen(new MyPhoneStateListener(),
                PhoneStateListener.LISTEN_CALL_STATE);
//
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_POST;
        params.getMethod = InterfaceUtils.RECORD_NEWTIME;
        params.params.put("mobile", sharedPreferences.getString("phone", ""));
        new NetTask(handler.obtainMessage(2), params, NewTime.class).execute();

//            }
//        }, 60 * 10);
        return Service.START_STICKY;
    }

    private void processPhone() {
        if (!isLoading) {//如果正在上传禁止本次上传
            upFiles.clear();//清空上传文件
            allFiles.clear();//清空
            a = 0;//上传集合下标还原；
            File pa = new File(sharedPreferences.getString("path", "/"));//---------需要改为从shap中获取路径
            newFiles = pa.listFiles();//目录下所有文件
            List<String> filesName = new ArrayList<String>();//数据库中已存在的录音文件的名字,用于对比  新文件是否已存在数据库中
            Cursor cursor = db.rawQuery("select * from file ", null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor
                            .getColumnIndex("name"));
                    filesName.add(name);
                } while (cursor.moveToNext());

            }
            cursor.close();

            File f = null;
            for (int i = 0; i < newFiles.length; i++) {//循环目录下所有文件，筛选出符合格式并没有上传的
                f = newFiles[i];//需要上传的文件，用于存入数据库
                String fileNam = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("/") + 1);
                if (fileNam.endsWith(".amr") || fileNam.endsWith(".mp3") || fileNam.endsWith(".MP3")) {
                    allFiles.add(f);
                    if (!isExet(fileNam, filesName)) {
                        upFiles.add(f);
                    }
                }
            }
            if (null != upFiles && 0 != upFiles.size()) {
                ClientParams params = new ClientParams();
                params.http_method = ClientParams.HTTP_POST;
                params.getMethod = InterfaceUtils.RECORD_REPETITION;
                params.params.put("name", upFiles.get(a).getName());
                new NetTask(handler.obtainMessage(0), params).execute();
                isLoading = true;
            }
            flag = false;

        }

    }

    private class MyPhoneStateListener extends PhoneStateListener {

        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {

                case TelephonyManager.CALL_STATE_IDLE: /* 接起电话时 */

                    if (flag) {
                        processPhone();
                    }
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:

                    break;

                case TelephonyManager.CALL_STATE_RINGING: /* 电话进来时 */
                    flag = true;
                    break;
                default:
                    break;
            }
            super.onCallStateChanged(state, incomingNumber);
        }
    }

    private BroadcastReceiver phoneReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Intent.ACTION_NEW_OUTGOING_CALL.equals(action)) {
                flag = true;
            }
        }
    };

//    class SDCardFileObserver extends FileObserver {
//        //mask:指定要监听的事件类型，默认为FileObserver.ALL_EVENTS
//        public SDCardFileObserver(String path, int mask) {
//            super(path, mask);
//        }
//
//        public SDCardFileObserver(String path) {
//            super(path);
//        }
//
//        @Override
//        public void onEvent(int event, String path) {
//            final int action = event & FileObserver.ALL_EVENTS;
//            switch (action) {
//                case FileObserver. ://监听有新的文件生成上传文件
//                    HashMap<String, File> files = new HashMap<String, File>();
//                    File pa = new File(sharedPreferences.getString("path", "/"));//---------需要改为从shap中获取路径
//                    newFiles = pa.listFiles();//目录下所有文件
//                    int a = 0;//录音文件数
//                    List<String> filesName = new ArrayList<String>();//数据库中已存在的录音文件的名字,用于对比  新文件是否已存在数据库中
//                    Cursor cursor = db.rawQuery("select * from file ", null);
//                    if (cursor != null && cursor.moveToFirst()) {
//                        do {
//                            String name = cursor.getString(cursor
//                                    .getColumnIndex("name"));
//                            filesName.add(name);
//                        } while (cursor.moveToNext());
//
//                    }
//                    cursor.close();
//
//                    for (int i = 0; i < newFiles.length; i++) {//循环目录下所有文件，筛选出符合格式并没有上传的
//                        File f = newFiles[i];
//                        String fileNam = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("/") + 1);
//                        if (fileNam.endsWith(".amr") || fileNam.endsWith(".mp3") || fileNam.endsWith(".MP3")) {
//                            if (!isExet(fileNam, filesName)) {
//                                uploadFiles.add(f);//需要上传的文件，用于存入数据库
//                                files.put(f.getName(), f);
//                                a++;
//                            }
//
//                        }
//                    }
//
//
//                    HashMap<String, String> params = new HashMap<String, String>();
//
//                    params.put("mobile", sharedPreferences.getString("phone", ""));
//                    params.put("name", sharedPreferences.getString("name", ""));
//
//                    AsyncTaskForUpLoadFilesNew at = new AsyncTaskForUpLoadFilesNew(
//                            "http://192.168.2.190/jinsh/home/Record/upload", params, files, "photo[]",
//                            handler.obtainMessage(1));
//                    at.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "1");
//                    break;
//            }
//        }
//
//    }

    /**
     * 文件是否存在
     *
     * @param fileNam
     * @return
     */
    public boolean isExet(String fileNam, List<String> files) {

        for (String f : files) {
            if (fileNam.endsWith(f.substring(f.lastIndexOf("/") + 1))) {
                return true;
            }
        }
        return false;
    }

    public String data(String time) {

        String date=time;

            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss",
                    Locale.CHINA);
            date = format2.format(new Date((Long.parseLong(time) * 1000)));

        return date;
    }

    /**
     * 比较两个字符串类型的日期的大小 c1相等c2返回0  c1小于c2 返回值<0  c1大于c2 返回值大于0
     * @param data1
     * @param data2
     * @return
     */
    public int comparData(String data1,String data2){

        java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        java.util.Calendar c1=java.util.Calendar.getInstance();
        java.util.Calendar c2=java.util.Calendar.getInstance();
        try
        {
            c1.setTime(df.parse(data1));
            c2.setTime(df.parse(data2));
        }catch(java.text.ParseException e){
            System.err.println("格式不正确");
        }
        return c1.compareTo(c2);

    }


    public static String getFileCreated(String path)
    {
        if(null == path)
        {
            return null;
        }

        return getFileCreated(new File(path));
    }

    public static String getFileCreated(final File file)
    {
        if(null == file)
        {
            return null;
        }

        String rs = null;
        final StringBuilder sb = new StringBuilder();
        Process p = null;

        try
        {
            p = Runtime.getRuntime().exec(String.format("cmd /C dir %s /tc", file.getAbsolutePath()));
        }
        catch(IOException e)
        {
            return rs;
        }

        final InputStream is = p.getInputStream();
        final InputStreamReader ir = new InputStreamReader(is);
        final BufferedReader br = new BufferedReader(ir);

        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                String data = null;

                try
                {
                    while(null != (data = br.readLine()))
                    {
                        if(-1 != data.toLowerCase().indexOf(file.getName().toLowerCase()))
                        {
                            String[] temp = data.split(" +");

                            if(2 <= temp.length)
                            {
                                String time = String.format("%s %s", temp[0], temp[1]);
                                sb.append(time);
                            }

                            break;
                        }
                    }
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    try
                    {
                        if(null != br)
                        {
                            br.close();
                        }

                        if(null != ir)
                        {
                            ir.close();
                        }

                        if(null != is)
                        {
                            is.close();
                        }
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        try
        {
            thread.join();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        if(0 != sb.length())
        {
            rs = sb.toString();
        }

        return rs;
    }
}
