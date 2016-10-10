package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.ExceptionList_exceptionItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerInfo_productsItem;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.net.Const;

/**
 * Created by ty on 2016/9/6 0006.
 */

public class OrderException_yiChangXinXi_gridAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private List<String> finalList=new ArrayList<String>();
    private int type;
    public OrderException_yiChangXinXi_gridAdapter(Context context, List<String> list,int type) {
        this.context = context;
        this.type=type;
        this.list = list;
        for(String url:list){
            if(0==type){
                finalList.add(Const.PICTURE+url);
            }else{
                finalList.add(Const.PICTURE_LUNBOTU+url);
            }

        }
    }

    @Override
    public int getCount() {
        if (null != list && 0 != list.size()) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View contentView, ViewGroup viewGroup) {
        ViewHodler holder = null;
        if (null == contentView) {
            holder = new ViewHodler();
            contentView = LayoutInflater.from(context).inflate(R.layout.adapter_orderexception_gridview, viewGroup, false);
            holder.image=(SimpleDraweeView) contentView.findViewById(R.id.adapter_orderexception_gridview_image);
            contentView.setTag(holder);
        } else {
            holder = (ViewHodler) contentView.getTag();
        }
        if(0==type){
            CommonUtils.displayImage(Uri.parse(Const.PICTURE+list.get(position)),holder.image,context,CommonUtils.isOnlyWIFIDownLoadPic(context));
        }else{
            CommonUtils.displayImage(Uri.parse(Const.PICTURE_LUNBOTU+list.get(position)),holder.image,context,CommonUtils.isOnlyWIFIDownLoadPic(context));
        }

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtils.launchNetPictureShow(context,finalList,position);
            }
        });
        return contentView;
    }

    class ViewHodler {
        private SimpleDraweeView image;
    }

}
