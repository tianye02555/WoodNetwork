package woodnetwork.hebg3.com.woodnetwork.ZiXun.model;

import android.os.Handler;
import android.os.Message;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ServiceInterfaceCont;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.ArticleList;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.CategoryList;
import woodnetwork.hebg3.com.woodnetwork.net.ClientParams;
import woodnetwork.hebg3.com.woodnetwork.net.NetTask;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/2 0002.
 */

public class ZiXunListModel implements ZiXunListModelInterface {
    private OnServiceBaceInterface onServiceBaceInterface_getCategoryListData;
    private OnServiceBaceInterface onServiceBaceInterface_getArticleListData;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ResponseBody body = (ResponseBody) msg.obj;
            switch (msg.what) {
                case 0://获取购物车列表接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getCategoryListData.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_getCategoryListData.onFailed(body.base.msg);
                    }
                    break;
                case 1://更改物品数量接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getArticleListData.onSuccess(body);
                    } else if ("1".equals(body.base.code)) {//失败
                        onServiceBaceInterface_getArticleListData.onFailed(body.base.msg);
                    }
                    break;
            }
        }
    };
    @Override
    public void getCategoryListData(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getCategoryListData = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.CATEGORYLIST;
        params.GETTYPE = "1";
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(0), params, CategoryList.class).execute();
    }

    @Override
    public void getArticleListData(Object object, OnServiceBaceInterface onServiceBaceInterface) {
        this.onServiceBaceInterface_getArticleListData = onServiceBaceInterface;
        ClientParams params = new ClientParams();
        params.http_method = ClientParams.HTTP_GET;
        params.getMethod = ServiceInterfaceCont.ARTICLELIST;
        params.GETTYPE = "1";
        params.params = CommonUtils.getParamString(object);
        new NetTask(handler.obtainMessage(1), params, ArticleList.class).execute();
    }
}
