package woodnetwork.hebg3.com.woodnetwork.ZiXun.model;

import android.os.Handler;
import android.os.Message;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ServiceInterfaceCont;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.BannerList;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.CategoryList;
import woodnetwork.hebg3.com.woodnetwork.net.ClientParams;
import woodnetwork.hebg3.com.woodnetwork.net.NetTask;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/1 0001.
 */

public class ZiXunHomeModel implements ZiXunHomeModelInterface {
    private OnServiceBaceInterface onServiceBaceInterface_getMyGalleryData;
    private OnServiceBaceInterface onServiceBaceInterface_getCategoryListData;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ResponseBody body = (ResponseBody) msg.obj;
            switch (msg.what) {
                case 0://获取购物车列表接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getMyGalleryData.onSuccess(body);
                    } else  {//失败
                        onServiceBaceInterface_getMyGalleryData.onFailed(body.base.msg);
                    }
                    break;
                case 1://更改物品数量接口
                    if ("0".equals(body.base.code)) {//成功
                        onServiceBaceInterface_getCategoryListData.onSuccess(body);
                    } else {//失败
                        onServiceBaceInterface_getCategoryListData.onFailed(body.base.msg);
                    }
                    break;
            }
        }
    };


        @Override
        public void getMyGalleryData(Object object, OnServiceBaceInterface onServiceBaceInterface) {
            this.onServiceBaceInterface_getMyGalleryData = onServiceBaceInterface;
            ClientParams params = new ClientParams();
            params.http_method = ClientParams.HTTP_GET;
            params.getMethod = ServiceInterfaceCont.BANNERLIST;
            params.GETTYPE = "1";
            params.params = CommonUtils.getParamString(object);
            new NetTask(handler.obtainMessage(0), params, BannerList.class).execute();
        }

        @Override
        public void getCategoryListData(Object object, OnServiceBaceInterface onServiceBaceInterface) {
            this.onServiceBaceInterface_getCategoryListData = onServiceBaceInterface;
            ClientParams params = new ClientParams();
            params.http_method = ClientParams.HTTP_GET;
            params.getMethod = ServiceInterfaceCont.CATEGORYLIST;
            params.GETTYPE = "1";
            params.params = CommonUtils.getParamString(object);
            new NetTask(handler.obtainMessage(1), params, CategoryList.class).execute();
        }
    }
