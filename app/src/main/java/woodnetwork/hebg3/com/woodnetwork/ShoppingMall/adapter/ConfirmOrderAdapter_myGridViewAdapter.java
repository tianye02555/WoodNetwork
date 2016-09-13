package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopcarList_listItem_attributeItem;

/**
 * Created by ty on 2016/9/12 0012.
 */

public class ConfirmOrderAdapter_myGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<ShopcarList_listItem_attributeItem> list;
    private static final int left = 0;
    private static final int right = 1;

    public ConfirmOrderAdapter_myGridViewAdapter(Context context, List<ShopcarList_listItem_attributeItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (0 == position % 2) {
            return left;
        } else {
            return right;
        }

    }

    @Override
    public int getViewTypeCount() {
        return 2;
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
        ViewHolder_left holder_left = null;
        ViewHolder_right holder_right = null;
        int type = getItemViewType(position);
        if (null == contentView) {
            switch (type) {
                case left:
                    holder_left = new ViewHolder_left();
                    contentView = LayoutInflater.from(context).inflate(R.layout.adapter_confirm_order_left, viewGroup, false);
                    holder_left.name_left = (TextView) contentView.findViewById(R.id.adapter_confirm_order_name);
                    holder_left.value_left = (TextView) contentView.findViewById(R.id.adapter_confirm_order_value);
                    contentView.setTag(holder_left);
                    break;
                case right:
                    holder_right = new ViewHolder_right();
                    contentView = LayoutInflater.from(context).inflate(R.layout.adapter_confirm_order_right, viewGroup, false);
                    holder_right.name_right = (TextView) contentView.findViewById(R.id.adapter_confirm_order_right_name);
                    holder_right.value_right = (TextView) contentView.findViewById(R.id.adapter_confirm_order_right_value);
                    contentView.setTag(holder_right);
                    break;
            }

        } else {
            switch (type) {
                case left:
                    holder_left = (ViewHolder_left) contentView.getTag();
                    break;
                case right:
                    holder_right = (ViewHolder_right) contentView.getTag();
                    break;
            }
        }
        switch (type) {
            case left:
                holder_left.name_left.setText(list.get(position).name+":");
                holder_left.value_left.setText(list.get(position).value);
                break;
            case right:
                holder_right.name_right.setText(list.get(position).name+":");
                holder_right.value_right.setText(list.get(position).value);
                break;
        }
        return contentView;
    }

    class ViewHolder_left {
        TextView name_left;
        TextView value_left;
    }

    class ViewHolder_right {
        TextView name_right;
        TextView value_right;
    }
}
