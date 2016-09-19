package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.activity.MyOrderActivity;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.activity.OrderDetailsActivity;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProList_listItem;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity.BusnessInfoActivity;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.view.MyListView;

/**
 * Created by ty on 2016/8/25 0025.
 */

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.BusnessHolder> {
    private static   Context context;
    private  static  List<OrderBuyerProList_listItem> list;
    private  MyOrder_AdapterItem_Adapter adapter;


    public MyOrderAdapter(Context context, List<OrderBuyerProList_listItem> list) {
        this.context = context;
        this.list = list;

    }
    public  List<OrderBuyerProList_listItem> getList() {
        return list;
    }

    public  void setList(List<OrderBuyerProList_listItem> list) {
        MyOrderAdapter.list = list;
    }

    @Override
    public BusnessHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new BusnessHolder(LayoutInflater.from(context).inflate(R.layout.adapter_myorder, parent, false));
    }

    @Override
    public void onBindViewHolder(BusnessHolder holder, int position) {

        holder.text_id.setText("订单编号："+list.get(position).number);
        holder.text_date.setText("下单时间："+list.get(position).creat_time);
        holder.text_jian.setText(String.valueOf(list.get(position).products.size()));
        holder.text_titlePrice.setText(String.valueOf(list.get(position).total_price)+"元");
        if(0==list.get(position).status){ // 0：待付款；1：已付款；2：已发货；3：已到货；4：订单取消
            holder.text_daiShouHuo.setText("待付款");
        }else if(1==list.get(position).status){
            holder.text_daiShouHuo.setText("已付款");
        }else if(2==list.get(position).status){
            holder.text_daiShouHuo.setText("已发货");
        }else if(3==list.get(position).status){
            holder.text_daiShouHuo.setText("已到货");
        }else if(4==list.get(position).status){
            holder.text_daiShouHuo.setText("订单取消");
        }
        if(0==list.get(position).type){//0：订单正常；1：订单异常
            holder.text_yiChang.setVisibility(View.GONE);
        }
        adapter=new MyOrder_AdapterItem_Adapter(context,list.get(position).products,list.get(position).seller);
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
        @Bind(R.id.adapter_myorder_text_daishouhuo)
        TextView text_daiShouHuo;
        @Bind(R.id.adapter_myorder_btn_guanbidingdan)
        Button btn_guanBiDingDan;
        @Bind(R.id.adapter_myorder_btn_querenshouhuo)
        Button btn_queRenDingDan;
        @Bind(R.id.adapter_myorder_btn_yichangshenbao)
        Button btn_yiChangDingDan;
        @Bind(R.id.adapter_myorder_mylistview)
        MyListView listView;
        @Bind(R.id.adapter_myorder_text_yichang)
        TextView text_yiChang;
        public BusnessHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            btn_guanBiDingDan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MyOrderActivity)context).closeOrder(list.get(getAdapterPosition()).id,getAdapterPosition());

                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context,OrderDetailsActivity.class);
                    intent.putExtra("sid",list.get(getAdapterPosition()).id);
                    intent.putExtra("from","BusnessInfoListActivity");
                    context.startActivity(intent);
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent=new Intent(context,OrderDetailsActivity.class);
                    intent.putExtra("sid",list.get(getAdapterPosition()).id);
                    intent.putExtra("from","BusnessInfoListActivity");
                    context.startActivity(intent);
                }
            });

        }
    }


}
