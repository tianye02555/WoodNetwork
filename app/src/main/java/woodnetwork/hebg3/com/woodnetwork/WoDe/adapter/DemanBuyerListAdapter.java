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
import woodnetwork.hebg3.com.woodnetwork.QiuGou.bean.DemandList_listItem;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity.BusnessInfoActivity;
import woodnetwork.hebg3.com.woodnetwork.WoDe.activity.DemandBuyerInfoActivity;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.DemandBuyerInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.DemandBuyerList_listItem;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.QuotationList_quotationItem;

/**
 * Created by ty on 2016/8/31 0031.
 */
public class DemanBuyerListAdapter extends RecyclerView.Adapter<DemanBuyerListAdapter.ViewHolder> {
    private Context context;
    private List<DemandBuyerList_listItem> list;

    public DemanBuyerListAdapter(Context context, List<DemandBuyerList_listItem> list) {
        this.context = context;
        this.list = list;
    }

    public List<DemandBuyerList_listItem> getList() {
        return list;
    }

    public void setList(List<DemandBuyerList_listItem> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_demandbuyerlist, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.name.setText("商品名称："+list.get(position).pname);
        holder.shouHuoDi.setText(list.get(position).receive_area);
        holder.shuliang.setText("购买数量："+list.get(position).number+"方");
        holder.baoJiaRenShu.setText( "报价人数："+list.get(position).qnumber);
        if (0 == list.get(position).status) {// 0表示未发布，1表示已发布，2表示已完成
            holder.faBu.setText("未发布");
        } else if (1 == list.get(position).status) {
            holder.faBu.setText("已发布");
        }else{
            holder.faBu.setText("已完成");
        }
    }

    @Override
    public int getItemCount() {
        if (null == list || list.size() == 0) {
            return 0;
        }
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.adapter_demandbuyerlist_text_name)
        TextView name;
        @Bind(R.id.adapter_demandbuyerlist_text_shouhuodi)
        TextView shouHuoDi;
        @Bind(R.id.adapter_demandbuyerlist_text_shuliang)
        TextView shuliang;
        @Bind(R.id.adapter_demandbuyerlist_text_fabu)
        TextView faBu;
        @Bind(R.id.adapter_demandbuyerlist_text_baojiarenshu)
        TextView baoJiaRenShu;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DemandBuyerInfoActivity.class);
                    intent.putExtra("did", list.get(getAdapterPosition()-1).id);
                    context.startActivity(intent);
                }
            });
        }
    }
}