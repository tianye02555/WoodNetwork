package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter;

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

import java.util.ArrayList;
import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity.WoodInfoActivity;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductFilterList_productsItem;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment.ShoppingMallFragment;


/**
 * Created by ty on 2016/8/19 0019.
 */

public class ShoppingMalAdapter extends RecyclerView.Adapter<ShoppingMalAdapter.ViewHolder> {
    private Context context;
    private List<ProductFilterList_productsItem> productInfoList;
    private ShoppingMallFragment shoppingMallFragment;

    public ShoppingMalAdapter(Context context, List<ProductFilterList_productsItem> productInfoList, ShoppingMallFragment shoppingMallFragment) {
        this.context = context;
        this.productInfoList = productInfoList;
        this.shoppingMallFragment=shoppingMallFragment;
    }

    public List<ProductFilterList_productsItem> getProductInfoList() {
        return productInfoList;
    }

    public void setProductInfoList(List<ProductFilterList_productsItem> productInfoList) {
        this.productInfoList = productInfoList;
    }

    @Override
    public ShoppingMalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.shoppingmalladapter_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ShoppingMalAdapter.ViewHolder holder, int position) {
        holder.head.setImageURI(Uri.parse(productInfoList.get(position).pimg));
        holder.name.setText(productInfoList.get(position).pname);
        holder.price.setText(String.valueOf(productInfoList.get(position).price));
        holder.stock.setText("库存：" + productInfoList.get(position).stock + "方");
        holder.company.setText(productInfoList.get(position).seller);
        if (0==productInfoList.get(position).type) {//0：期货；1：现货；2：板材
            holder.qiHuo.setText("期货");
        }else if(1==productInfoList.get(position).type){
            holder.qiHuo.setText("现货");
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
        private TextView company;//木材所属公司
        private TextView price;//单价
        private TextView stock;//库存
        private Button putShoppingCart;//加入购物车按钮
        private Button buy;//立即购买按钮
        private TextView qiHuo;//期货小角标

        public ViewHolder(View itemView) {
            super(itemView);
            head = (SimpleDraweeView) itemView.findViewById(R.id.shopppingmalladapter_simpledraweeview_head);
            name = (TextView) itemView.findViewById(R.id.shopppingmalladapter_txt_name);
            company = (TextView) itemView.findViewById(R.id.shopppingmalladapter_txt_company);
            price = (TextView) itemView.findViewById(R.id.shopppingmalladapter_txt_price);
            stock = (TextView) itemView.findViewById(R.id.shopppingmalladapter_txt_stock);
            buy = (Button) itemView.findViewById(R.id.shopppingmalladapter_btn_buy);
            buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shoppingMallFragment.showNumberDialog(view.getId(),getAdapterPosition());
                }
            });
            putShoppingCart = (Button) itemView.findViewById(R.id.shopppingmalladapter_btn_shoppingcart);
            putShoppingCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shoppingMallFragment.showNumberDialog(view.getId(),getAdapterPosition());
                }
            });
            qiHuo = (TextView) itemView.findViewById(R.id.shopppingmalladapter_text_qihuo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, WoodInfoActivity.class);
                    intent.putExtra("pid", productInfoList.get(getAdapterPosition()).pid);
                    context.startActivity(intent);
                }
            });
        }
    }
}
