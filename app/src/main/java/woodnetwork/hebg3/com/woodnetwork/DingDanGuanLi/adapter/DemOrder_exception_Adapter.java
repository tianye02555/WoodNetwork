package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.activity.DemOrderActivity;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.activity.MyOrderActivity;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.activity.OrderDetailsActivity;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.activity.OrderExceptionActivity;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemExceptionList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProExceptionList_listItem;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity.BusnessInfoActivity;
import woodnetwork.hebg3.com.woodnetwork.view.MyListView;

/**
 * Created by ty on 2016/8/25 0025.
 */

public class DemOrder_exception_Adapter extends RecyclerView.Adapter<DemOrder_exception_Adapter.BusnessHolder> {
    private static   Context context;
    private  static  List<OrderBuyerDemExceptionList_listItem> list;
    private  DemOrder_exceptionAdapterItem_Adapter adapter;


    public DemOrder_exception_Adapter(Context context, List<OrderBuyerDemExceptionList_listItem> list) {
        this.context = context;
        this.list = list;

    }

    public static List<OrderBuyerDemExceptionList_listItem> getList() {
        return list;
    }

    public static void setList(List<OrderBuyerDemExceptionList_listItem> list) {
        DemOrder_exception_Adapter.list = list;
    }

    @Override
    public BusnessHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new BusnessHolder(LayoutInflater.from(context).inflate(R.layout.adapter_myorder, parent, false));
    }

    @Override
    public void onBindViewHolder(BusnessHolder holder,final int position) {

        holder.text_id.setText("订单编号：" + list.get(position).number);
        holder.text_date.setText("下单时间：" +list.get(position).creat_time);
        holder.text_jian.setText(String.valueOf(list.size()));
        holder.text_titlePrice.setText(String.valueOf(list.get(position).total_price));
        int status=list.get(position).status;
        if (0 == status) { // 0：待付款；1：已付款；2：已发货；3：已到货；4：订单取消
            holder.text_daiShouHuo.setText("待付款");
            holder.btn_queRenDingDan.setVisibility(View.GONE);
            holder.btn_yiChangDingDan.setVisibility(View.GONE);
            holder.btn_guanBiDingDan.setVisibility(View.VISIBLE);
        } else if (1 == status) {
            holder.text_daiShouHuo.setText("已付款");
            holder.btn_yiChangDingDan.setVisibility(View.VISIBLE);
            holder.btn_guanBiDingDan.setVisibility(View.GONE);
            holder.btn_queRenDingDan.setVisibility(View.VISIBLE);
        } else if (2 == status) {
            holder.text_daiShouHuo.setText("已发货");
            holder.btn_guanBiDingDan.setVisibility(View.GONE);
            holder.btn_queRenDingDan.setVisibility(View.VISIBLE);
            holder.btn_yiChangDingDan.setVisibility(View.VISIBLE);
        } else if (3 == status) {
            holder.text_daiShouHuo.setText("已到货");
            holder.btn_guanBiDingDan.setVisibility(View.GONE);
            holder.btn_queRenDingDan.setVisibility(View.GONE);
            holder.btn_yiChangDingDan.setVisibility(View.VISIBLE);
        } else if (4 == status) {
            holder.text_daiShouHuo.setText("订单取消");
            holder.btn_guanBiDingDan.setVisibility(View.GONE);
            holder.btn_queRenDingDan.setVisibility(View.GONE);
            holder.btn_yiChangDingDan.setVisibility(View.GONE);
        }else{
            holder.btn_guanBiDingDan.setVisibility(View.VISIBLE);
            holder.btn_queRenDingDan.setVisibility(View.VISIBLE);
            holder.btn_yiChangDingDan.setVisibility(View.VISIBLE);
        }
        holder.btn_queRenDingDan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((DemOrderActivity) context).orderReceive(position);

            }
        });
        holder.btn_yiChangDingDan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OrderExceptionActivity.class);
                intent.putExtra("seller", list.get(position).seller);
                intent.putExtra("number", String.valueOf(list.size()));
                intent.putExtra("oid",list.get(position).id);
                intent.putExtra("id", list.get(position).number);
                intent.putExtra("creat_time", list.get(position).creat_time);
                intent.putExtra("total_price", String.valueOf(list.get(position).total_price));
                context.startActivity(intent);

            }
        });
        adapter=new DemOrder_exceptionAdapterItem_Adapter(context,list.get(position).products,list.get(position).seller);
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
        @Bind(R.id.adapter_myorder_btn_querenshouhuo)
        Button btn_queRenDingDan;
        @Bind(R.id.adapter_myorder_btn_yichangshenbao)
        Button btn_yiChangDingDan;
        @Bind(R.id.adapter_myorder_btn_guanbidingdan)
        Button btn_guanBiDingDan;
        @Bind(R.id.adapter_myorder_text_yichang)
        TextView text_yiChang;
        @Bind(R.id.adapter_myorder_text_daishouhuo)
        TextView text_daiShouHuo;

        public BusnessHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, OrderDetailsActivity.class);
                    intent.putExtra("oid", list.get(getAdapterPosition()-1).id);
                    context.startActivity(intent);
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(context, OrderDetailsActivity.class);
                    intent.putExtra("oid", list.get(getAdapterPosition()-1).id);
                    context.startActivity(intent);
                }
            });
        }
    }

}
