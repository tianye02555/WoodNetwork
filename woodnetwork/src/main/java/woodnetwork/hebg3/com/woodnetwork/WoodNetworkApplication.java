package woodnetwork.hebg3.com.woodnetwork;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by ty on 2016/8/15 0015.
 */

public class WoodNetworkApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        //初始化fresco
        Fresco.initialize(this);
        //初始化okhttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1000, TimeUnit.MILLISECONDS)
                .readTimeout(1000, TimeUnit.MILLISECONDS)
                .build();
        OkHttpUtils.initClient(okHttpClient);

    }

}
