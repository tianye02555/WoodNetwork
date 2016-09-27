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

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerInfo_productsItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderSellerInfo_productsItem;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.net.Const;

/**
 * Created by ty on 2016/9/6 0006.
 */

public class SellerOrderDetailsAdapter extends BaseAdapter {
    private Context context;
    private List<OrderSellerInfo_productsItem> list;

    public SellerOrderDetailsAdapter(Context context, List<OrderSellerInfo_productsItem> list) {
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
            contentView = LayoutInflater.from(context).inflate(R.layout.adapter_orderdetails, viewGroup, false);
            holder.name = (TextView) contentView.findViewById(R.id.adapter_order_details_text_name);
            holder.price = (TextView) contentView.findViewById(R.id.adapter_order_details_text_price);
            holder.number = (TextView) contentView.findViewById(R.id.adapter_order_details_text_number);
            holder.image=(SimpleDraweeView) contentView.findViewById(R.id.adapter_order_details_image);
            holder.qiHuo=(SimpleDraweeView)contentView.findViewById(R.id.adapter_order_details_imag_qihuo);
            contentView.setTag(holder);
        } else {
            holder = (ViewHodler) contentView.getTag();
        }
        if (0==list.get(position).type) {//0：期货；1：现货；2：板材
            holder.qiHuo.setImageURI(Uri.parse("res://woodnetwork.hebg3.com.woodnetwork/"+R.drawable.qihuo));
        }else if(1==list.get(position).type){
            holder.qiHuo.setImageURI(Uri.parse("res://woodnetwork.hebg3.com.woodnetwork/"+R.drawable.xianhuo));
        }else if(3==list.get(position).type){
            holder.qiHuo.setImageURI(Uri.parse("res://woodnetwork.hebg3.com.woodnetwork/"+R.drawable.bancai));
        }
        holder.name.setText(list.get(position).name);
        holder.image.setImageURI(Uri.parse(Const.PICTURE+list.get(position).img));
        holder.price.setText("￥"+list.get(position).total_price);
        holder.number.setText(list.get(position).number+"m³");
        return contentView;
    }

    class ViewHodler {
        private SimpleDraweeView image;
        private TextView name;
        private TextView price;
        private TextView number;
        private SimpleDraweeView qiHuo;
    }
}
