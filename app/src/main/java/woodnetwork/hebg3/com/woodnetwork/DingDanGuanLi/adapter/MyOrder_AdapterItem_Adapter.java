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

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProList_listItem_productsItem;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.QuotationInfo_attributeItem;

/**
 * Created by ty on 2016/9/6 0006.
 */

public class MyOrder_AdapterItem_Adapter extends BaseAdapter {
    private Context context;
    private List<OrderBuyerProList_listItem_productsItem> list;
    private  String seller;

    public MyOrder_AdapterItem_Adapter(Context context, List<OrderBuyerProList_listItem_productsItem> list,String seller) {
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
            contentView = LayoutInflater.from(context).inflate(R.layout.adapter_adapter_myorder, viewGroup, false);
            holder.image = (SimpleDraweeView) contentView.findViewById(R.id.adapter_myorder_image_img);
            holder.name = (TextView) contentView.findViewById(R.id.adapter_myorder_text_name);
            holder.company = (TextView) contentView.findViewById(R.id.adapter_myorder_text_company);
            holder.price = (TextView) contentView.findViewById(R.id.adapter_myorder_text_price);
            holder.number = (TextView) contentView.findViewById(R.id.adapter_myorder_text_number);
            contentView.setTag(holder);
        } else {
            holder = (ViewHodler) contentView.getTag();
        }
        holder.image.setImageURI(Uri.parse(list.get(position).img));
        holder.name.setText(list.get(position).name);
        holder.company.setText(this.seller);
        holder.price.setText(String.valueOf(list.get(position).price)+"元");
        holder.number.setText(String.valueOf(list.get(position).number)+"方");
        return contentView;
    }

    class ViewHodler {
        private SimpleDraweeView image;
        private TextView name;
        private TextView company;
        private TextView price;
        private TextView number;
    }
}
