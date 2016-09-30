package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.WoodFilterValue;
import woodnetwork.hebg3.com.woodnetwork.net.Const;

/**
 * Created by ty on 2016/9/6 0006.
 */

public class ShaiXuanAdapter_gridAdapter extends BaseAdapter {
    private Context context;
    private List<WoodFilterValue> list;

    public ShaiXuanAdapter_gridAdapter(Context context, List<WoodFilterValue> list) {
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
            contentView = LayoutInflater.from(context).inflate(R.layout.shaixuan_adapter_gridadapter, viewGroup, false);
            holder.radioButton=(RadioButton) contentView.findViewById(R.id.radio);
            contentView.setTag(holder);
        } else {
            holder = (ViewHodler) contentView.getTag();
        }
        holder.radioButton.setText(list.get(position).value_name);
//        holder.radioButton.setChecked(list.get(position).checked);
        return contentView;
    }

    class ViewHodler {
        private RadioButton radioButton;
    }
}
