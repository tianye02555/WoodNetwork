package woodnetwork.hebg3.com.woodnetwork.WoDe.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.net.Const;

/**
 * Created by ty on 2016/9/5 0005.
 */

public class MyInformationAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private List<String> listString=new ArrayList<String>();

    public MyInformationAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        for(String url:list){
            listString.add(Const.PICTURE+url);
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        MyViewHolder myViewHolder = null;
        if (convertView == null) {
            myViewHolder = new MyViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_myinformation, viewGroup, false);
            myViewHolder.image = (SimpleDraweeView) convertView.findViewById(R.id.adapter_myinformation_image);
            final MyViewHolder finalMyViewHolder = myViewHolder;
            myViewHolder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CommonUtils.launchNetPictureShow(context,listString,position, finalMyViewHolder.image);
                }
            });
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(width/3,width/3-20);

        myViewHolder.image.setLayoutParams(layoutParams);
        CommonUtils.displayImage(Uri.parse(Const.PICTURE+list.get(position)),myViewHolder.image,context,CommonUtils.isOnlyWIFIDownLoadPic(context));
        return convertView;
    }

    class MyViewHolder {
        private SimpleDraweeView image;
    }
}
