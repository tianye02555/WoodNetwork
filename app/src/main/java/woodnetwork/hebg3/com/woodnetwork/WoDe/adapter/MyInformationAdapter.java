package woodnetwork.hebg3.com.woodnetwork.WoDe.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.R;

/**
 * Created by ty on 2016/9/5 0005.
 */

public class MyInformationAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public MyInformationAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        MyViewHolder myViewHolder = null;
        if (convertView == null) {
            myViewHolder = new MyViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_myinformation, viewGroup, false);
            myViewHolder.image = (SimpleDraweeView) convertView.findViewById(R.id.adapter_myinformation_image);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        myViewHolder.image.setImageURI(Uri.parse(list.get(position)));
        return convertView;
    }

    class MyViewHolder {
        private SimpleDraweeView image;
    }
}
