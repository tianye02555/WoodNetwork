package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.net.Const;

/**
 * Created by ty on 2016/9/6 0006.
 */

public class OrderException_yiChangChuLi_gridAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public OrderException_yiChangChuLi_gridAdapter(Context context, List<String> list) {
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
    public View getView(int position, View contentView, ViewGroup viewGroup) {
        ViewHodler holder = null;
        if (null == contentView) {
            holder = new ViewHodler();
            contentView = LayoutInflater.from(context).inflate(R.layout.adapter_orderexception_gridview, viewGroup, false);
            holder.image=(SimpleDraweeView) contentView.findViewById(R.id.adapter_orderexception_gridview_image);
            contentView.setTag(holder);
        } else {
            holder = (ViewHodler) contentView.getTag();
        }
        holder.image.setImageURI(Uri.parse(Const.PICTURE+list.get(position)));
        return contentView;
    }

    class ViewHodler {
        private SimpleDraweeView image;
    }
}
