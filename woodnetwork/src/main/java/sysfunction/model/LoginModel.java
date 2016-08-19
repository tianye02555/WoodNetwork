package sysfunction.model;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.Callback;

import java.util.HashMap;

import Utils.CommonUtils;
import sysfunction.bean.User;
import okhttp3.Call;
import okhttp3.Response;
import sysfunction.model.modelinterface.LoginModelInterface;

/**
 * Created by ty on 2016/8/16 0016.
 */

public class LoginModel implements LoginModelInterface {
    @Override
    public void login(String url, HashMap<String,String> param, final OnLoginLisenter onLoginLisenter) {
       CommonUtils.getOkhttpBuilder(url, param).build().execute(new Callback<User>() {
            @Override
            public User parseNetworkResponse(Response response, int id) throws Exception {
                String string = response.body().string();
                User user = new Gson().fromJson(string, User.class);
                return user;
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                onLoginLisenter.onFailed();
            }

            @Override
            public void onResponse(User response, int id) {
                onLoginLisenter.onSuccess();
            }
        });

    }

    public interface OnLoginLisenter {
        void onSuccess();

        void onFailed();
    }
}
