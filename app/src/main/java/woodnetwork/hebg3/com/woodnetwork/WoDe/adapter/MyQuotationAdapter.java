package woodnetwork.hebg3.com.woodnetwork.WoDe.adapter;

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
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity.BusnessInfoActivity;
import woodnetwork.hebg3.com.woodnetwork.WoDe.activity.QuotationInfoActivity;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.QuotationList_quotationItem;

/**
 * Created by ty on 2016/8/31 0031.
 */
public class MyQuotationAdapter extends RecyclerView.Adapter<MyQuotationAdapter.ViewHolder> {
    @Bind(R.id.adapter_myquotation_layout_text_fahuodi)
    TextView faHuoDi;
    private Context context;
    private List<QuotationList_quotationItem> list;

    public MyQuotationAdapter(Context context, List<QuotationList_quotationItem> list) {
        this.context = context;
        this.list = list;
    }

    public List<QuotationList_quotationItem> getList() {
        return list;
    }

    public void setList(List<QuotationList_quotationItem> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_myquotation, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.faBuRen.setText("发布人："+list.get(position).buyer);
        holder.pingMing.setText("品   名："+list.get(position).name);
        holder.shuliang.setText("数   量："+list.get(position).number);
        holder.shouHuoDi.setText( "收货地："+list.get(position).receive);
        holder.faHuoDi.setText( "发货地："+list.get(position).delivery);
        if (0 == list.get(position).status) {// 0表示报价中，1表示报价成功，2表示报价失败
            holder.baoJia.setText("报价中");
        } else if (1 == list.get(position).status) {
            holder.baoJia.setText("报价成功");
        } else if (2 == list.get(position).status) {
            holder.baoJia.setText("报价失败");
        }

        holder.jiaGe.setText(String.valueOf(list.get(position).price));

    }

    @Override
    public int getItemCount() {
        if (null == list || list.size() == 0) {
            return 0;
        }
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.adapter_myquotation_layout_text_faburen)
        TextView faBuRen;
        @Bind(R.id.adapter_myquotation_layout_text_pingming)
        TextView pingMing;
        @Bind(R.id.adapter_myquotation_layout_text_shuliang)
        TextView shuliang;
        @Bind(R.id.adapter_myquotation_layout_text_shouhuodi)
        TextView shouHuoDi;
        @Bind(R.id.adapter_myquotation_layout_text_baojia)
        TextView baoJia;
        @Bind(R.id.adapter_myquotation_layout_text_jiage)
        TextView jiaGe;
        @Bind(R.id.adapter_myquotation_layout_text_fahuodi)
        TextView faHuoDi;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, QuotationInfoActivity.class);
                    intent.putExtra("qid", list.get(getAdapterPosition()).id);
                    context.startActivity(intent);
                }
            });
        }
    }
}