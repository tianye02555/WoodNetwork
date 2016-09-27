package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.OrderAdd_productsItem;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopcarList_listItem;
import woodnetwork.hebg3.com.woodnetwork.view.MyGridView;

/**
 * Created by ty on 2016/8/27 0027.
 */

public class ConfirmOrderAdapter extends BaseAdapter {

    private Context context;
    private List<ShopcarList_listItem> list;
    private ConfirmOrderAdapter_myGridViewAdapter adapter_myGridViewAdapter;


    public ConfirmOrderAdapter(Context context, List<ShopcarList_listItem> list) {
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
    public View getView(final int position, View contentView, ViewGroup viewGroup) {
        MyViewHolder holder;
        if (null == contentView) {
            holder = new MyViewHolder();
            contentView = LayoutInflater.from(context).inflate(R.layout.confirm_order_adapter, viewGroup, false);
            holder.name = (TextView) contentView.findViewById(R.id.confirm_order_adapter_text_product);
            holder.jingE = (TextView) contentView.findViewById(R.id.confirm_order_adapter_text_jinge);
            holder.price = (TextView) contentView.findViewById(R.id.confirm_order_adapter_text_price);
            holder.shuLiang = (TextView) contentView.findViewById(R.id.confirm_order_adapter_text_shuliang);
            holder.gengDuo = (TextView) contentView.findViewById(R.id.confirm_order_adapter_text_more);

            holder.gridView = (MyGridView) contentView.findViewById(R.id.confirm_order_adapter_gridview);

            contentView.setTag(holder);
        } else {
            holder = (MyViewHolder) contentView.getTag();
        }
        holder.name.setText("商   品：" + list.get(position).pname);
        holder.jingE.setText(String.valueOf(list.get(position).xiaoJi));
        holder.price.setText(String.valueOf(list.get(position).price));
        holder.shuLiang.setText(String.valueOf(list.get(position).stock));
        if (list.get(position).gridView) {
            holder.gridView.setVisibility(View.VISIBLE);
        }else{
            holder.gridView.setVisibility(View.GONE);
        }
        adapter_myGridViewAdapter = new ConfirmOrderAdapter_myGridViewAdapter(context, list.get(position).attribute);
        holder.gridView.setAdapter(adapter_myGridViewAdapter);
        holder.gengDuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (context.getResources().getString(R.string.gegnduoshuxing).equals(((TextView) view).getText())) {
                    ((TextView) view).setText(context.getResources().getString(R.string.shouqi));
                    list.get(position).gridView = true;

                } else {
                    ((TextView) view).setText(context.getResources().getString(R.string.gegnduoshuxing));
                    list.get(position).gridView = false;

                }

            }
        });
        return contentView;
    }


    class MyViewHolder {
        TextView name;
        TextView price;
        TextView shuLiang;
        TextView jingE;
        TextView gengDuo;
        MyGridView gridView;
    }
}
