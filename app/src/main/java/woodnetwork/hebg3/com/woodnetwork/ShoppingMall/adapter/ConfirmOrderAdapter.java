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

/**
 * Created by ty on 2016/8/27 0027.
 */

public class ConfirmOrderAdapter extends BaseAdapter {

    private Context context;
    private List<OrderAdd_productsItem> list;

    public ConfirmOrderAdapter(Context context, List<OrderAdd_productsItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if(null!=list&&0!=list.size()){
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
        MyViewHolder holder;
        if(null==contentView){
            holder=new MyViewHolder();
            contentView= LayoutInflater.from(context).inflate(R.layout.confirm_order_adapter,viewGroup,false);
            holder.name=(TextView)contentView.findViewById(R.id.confirm_order_adapter_text_product);
            holder.jingE=(TextView)contentView.findViewById(R.id.confirm_order_adapter_text_jinge);
            holder.price=(TextView)contentView.findViewById(R.id.confirm_order_adapter_text_price);
            holder.shuLiang=(TextView)contentView.findViewById(R.id.confirm_order_adapter_text_shuliang);
            contentView.setTag(holder);
        }else{
            holder=(MyViewHolder) contentView.getTag();
        }
        holder.name.setText("商   品："+list.get(position).name);
        holder.jingE.setText(String.valueOf(list.get(position).total_price));
        holder.price.setText(String.valueOf(list.get(position).price));
        holder.shuLiang.setText(String.valueOf(list.get(position).number));

        return contentView;
    }


    class MyViewHolder  {
        TextView name;
        TextView price;
        TextView gongYingShang;
        TextView shuLiang;
        TextView dengJi;
        TextView jingE;
        TextView jinge;
    }
}
