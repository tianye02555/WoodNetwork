package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProExceptionList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderSellerExceptionList_listItem;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity.BusnessInfoActivity;
import woodnetwork.hebg3.com.woodnetwork.view.MyListView;

/**
 * Created by ty on 2016/8/25 0025.
 */

public class SellerOrder_exception_Adapter extends RecyclerView.Adapter<SellerOrder_exception_Adapter.BusnessHolder> {
    private static   Context context;
    private  static  List<OrderSellerExceptionList_listItem> list;
    private  SellerOrder_exceptionAdapterItem_Adapter adapter;


    public SellerOrder_exception_Adapter(Context context, List<OrderSellerExceptionList_listItem> list) {
        this.context = context;
        this.list = list;

    }

    public static List<OrderSellerExceptionList_listItem> getList() {
        return list;
    }

    public static void setList(List<OrderSellerExceptionList_listItem> list) {
        SellerOrder_exception_Adapter.list = list;
    }

    @Override
    public BusnessHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new BusnessHolder(LayoutInflater.from(context).inflate(R.layout.adapter_myorder, parent, false));
    }

    @Override
    public void onBindViewHolder(BusnessHolder holder, int position) {

        holder.text_id.setText(list.get(position).number);
        holder.text_date.setText(list.get(position).creat_time);
        holder.text_jian.setText(String.valueOf(list.size()));
        holder.text_titlePrice.setText(String.valueOf(list.get(position).total_price));
        adapter=new SellerOrder_exceptionAdapterItem_Adapter(context,list.get(position).products,list.get(position).buyer);
        holder.listView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        if (null != list && 0 != list.size()) {
            return list.size();
        }
        return 0;
    }

    static class BusnessHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.adapter_myorder_text_id)
        TextView text_id;
        @Bind(R.id.adapter_myorder_text_date)
        TextView text_date;
        @Bind(R.id.adapter_myorder_text_jian)
        TextView text_jian;
        @Bind(R.id.adapter_myorder_text_titleprice)
        TextView text_titlePrice;
        @Bind(R.id.adapter_myorder_mylistview)
        MyListView listView;

        public BusnessHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context,BusnessInfoActivity.class);
                    intent.putExtra("sid",list.get(getAdapterPosition()).id);
                    intent.putExtra("from","BusnessInfoListActivity");
                    context.startActivity(intent);
                }
            });

        }
    }

}
