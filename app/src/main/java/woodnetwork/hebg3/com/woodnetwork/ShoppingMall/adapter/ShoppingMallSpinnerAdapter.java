package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.WoodFilterValue;

/**
 * Created by ty on 2016/8/24 0024.
 */

public class ShoppingMallSpinnerAdapter extends BaseAdapter {
    private Context context;
    private List<WoodFilterValue> list;

    public ShoppingMallSpinnerAdapter(Context context, List<WoodFilterValue> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (null != list && list.size() != 0) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (null == view) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, viewGroup,false);
            holder.text = (TextView) view.findViewById(android.R.id.text1);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        holder.text.setText(list.get(i).value_name);
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (null == convertView) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.spinnerlayout, parent,false);
            holder.text = (TextView) convertView.findViewById(R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.text.setText(list.get(position).value_name);
        return convertView;
    }

    class Holder {
        private TextView text;
    }
}
