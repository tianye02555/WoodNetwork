package ShoppingMall.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import woodnetwork.hebg3.com.woodnetwork.R;


/**
 * Created by ty on 2016/8/19 0019.
 */

public class ShoppingMalAdapter extends RecyclerView.Adapter<ShoppingMalAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> productInfoList;
    public ShoppingMalAdapter(Context context,ArrayList<String> productInfoList){
        this.context=context;
        this.productInfoList=productInfoList;
    }
    @Override
    public ShoppingMalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.shoppingmalladapter_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(ShoppingMalAdapter.ViewHolder holder, int position) {
        holder.head.setImageURI(Uri.parse(productInfoList.get(position)));


    }

    @Override
    public int getItemCount() {
        return 0;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private SimpleDraweeView head;//木材头像
        private TextView name;//木材名字
        private TextView company;//木材所属公司
        private TextView producingArea;//木材产地
        private TextView price;//单价
        private TextView stock;//库存
        private Button putShoppingCart;//加入购物车按钮
        private Button buy;//立即购买按钮
        public ViewHolder(View itemView) {
            super(itemView);
            head=(SimpleDraweeView)itemView.findViewById(R.id.shopppingmalladapter_simpledraweeview_head);
            name=(TextView)itemView.findViewById(R.id.shopppingmalladapter_txt_name);
            company=(TextView) itemView.findViewById(R.id.shopppingmalladapter_txt_company);
            producingArea=(TextView) itemView.findViewById(R.id.shopppingmalladapter_txt_productarea);
            price=(TextView) itemView.findViewById(R.id.shopppingmalladapter_txt_price);
            stock=(TextView) itemView.findViewById(R.id.shopppingmalladapter_txt_stock);
            putShoppingCart=(Button)itemView.findViewById(R.id.shopppingmalladapter_btn_shoppingcart);
            buy=(Button)itemView.findViewById(R.id.shopppingmalladapter_btn_shoppingcart);
        }
    }
}
