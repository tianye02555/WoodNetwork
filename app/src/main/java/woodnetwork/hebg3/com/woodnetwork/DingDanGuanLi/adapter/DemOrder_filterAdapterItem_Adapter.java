package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemFilterList_listItem_productsItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProFilterList_listItem_productsItem;
import woodnetwork.hebg3.com.woodnetwork.R;

/**
 * Created by ty on 2016/9/6 0006.
 */

public class DemOrder_filterAdapterItem_Adapter extends BaseAdapter {
    private Context context;
    private List<OrderBuyerDemFilterList_listItem_productsItem> list;
    private  String seller;

    public DemOrder_filterAdapterItem_Adapter(Context context, List<OrderBuyerDemFilterList_listItem_productsItem> list, String seller) {
        this.context = context;
        this.list = list;
        this.seller=seller;
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
            contentView = LayoutInflater.from(context).inflate(R.layout.adapter_adapter_demorder, viewGroup, false);
            holder.name = (TextView) contentView.findViewById(R.id.adapter_demorder_text_name);
            holder.company = (TextView) contentView.findViewById(R.id.adapter_demorder_text_company);
            holder.price = (TextView) contentView.findViewById(R.id.adapter_demorder_text_price);
            holder.number = (TextView) contentView.findViewById(R.id.adapter_demorder_text_number);
            contentView.setTag(holder);
        } else {
            holder = (DemOrder_filterAdapterItem_Adapter.ViewHodler) contentView.getTag();
        }
        holder.name.setText(list.get(position).name);
        holder.company.setText(this.seller);
        holder.price.setText(String.valueOf(list.get(position).price)+"元");
        holder.number.setText(String.valueOf(list.get(position).number)+"方");
        return contentView;
    }

    class ViewHodler {
        private TextView name;
        private TextView company;
        private TextView price;
        private TextView number;
    }
}
