package woodnetwork.hebg3.com.woodnetwork.WoDe.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity.WoodInfoActivity;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductFilterList_productsItem;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.ProductSellerList_productsItem;
import woodnetwork.hebg3.com.woodnetwork.net.Const;


/**
 * Created by ty on 2016/8/19 0019.
 */

public class ProductSellerListAdapter extends RecyclerView.Adapter<ProductSellerListAdapter.ViewHolder> {
    private Context context;
    private List<ProductSellerList_productsItem> productInfoList;

    public ProductSellerListAdapter(Context context, List<ProductSellerList_productsItem> productInfoList) {
        this.context = context;
        this.productInfoList = productInfoList;
    }

    public List<ProductSellerList_productsItem> getProductInfoList() {
        return productInfoList;
    }

    public void setProductInfoList(List<ProductSellerList_productsItem> productInfoList) {
        this.productInfoList = productInfoList;
    }

    @Override
    public ProductSellerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_productsellerlist,null));
    }

    @Override
    public void onBindViewHolder(ProductSellerListAdapter.ViewHolder holder, int position) {
        holder.head.setImageURI(Uri.parse(Const.PICTURE+productInfoList.get(position).pimg));
        holder.name.setText(productInfoList.get(position).pname);
        holder.price.setText(String.valueOf(productInfoList.get(position).price));
        holder.stock.setText("库存：" + productInfoList.get(position).stock + "方");
        if (0 == productInfoList.get(position).status) {// 0表示下架，1表示上架
            holder.state.setText("已下架");
        } else {
            holder.state.setText("已上架");
        }

        holder.producingArea.setText("产地：" + productInfoList.get(position).attributes.get(0).attr_value);
        if (0==productInfoList.get(position).type) {//0：期货；1：现货；2：板材
            holder.qiHuo.setImageURI(Uri.parse("res://woodnetwork.hebg3.com.woodnetwork/"+R.drawable.qihuo));
        }else if(1==productInfoList.get(position).type){
            holder.qiHuo.setImageURI(Uri.parse("res://woodnetwork.hebg3.com.woodnetwork/"+R.drawable.xianhuo));
        }else if(3==productInfoList.get(position).type){
            holder.qiHuo.setImageURI(Uri.parse("res://woodnetwork.hebg3.com.woodnetwork/"+R.drawable.bancai));
        }

    }

    @Override
    public int getItemCount() {
        if (null == productInfoList || productInfoList.size() == 0) {
            return 0;
        }
        return productInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView head;//木材头像
        private TextView name;//木材名字
        private TextView state;//木材所属公司
        private TextView producingArea;//木材产地
        private TextView price;//单价
        private TextView stock;//库存
        private SimpleDraweeView qiHuo;//期货小角标


        public ViewHolder(View itemView) {
            super(itemView);
            head = (SimpleDraweeView) itemView.findViewById(R.id.adapter_productsellerlist_simpledraweeview_head);
            name = (TextView) itemView.findViewById(R.id.adapter_productsellerlist_txt_name);
            state = (TextView) itemView.findViewById(R.id.adapter_productsellerlist_txt_state);
            producingArea = (TextView) itemView.findViewById(R.id.adapter_productsellerlist_txt_productarea);
            price = (TextView) itemView.findViewById(R.id.adapter_productsellerlist_txt_price);
            stock = (TextView) itemView.findViewById(R.id.adapter_productsellerlist_txt_stock);
            qiHuo = (SimpleDraweeView) itemView.findViewById(R.id.adapter_productsellerlist_simpledraweeview_qihuo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, WoodInfoActivity.class);
                    intent.putExtra("pid", productInfoList.get(getAdapterPosition()-1).pid);
                    intent.putExtra("isShowButton", false);//是否显示商品详情页的 加入购物车按钮
                    context.startActivity(intent);
                }
            });
        }
    }
}
