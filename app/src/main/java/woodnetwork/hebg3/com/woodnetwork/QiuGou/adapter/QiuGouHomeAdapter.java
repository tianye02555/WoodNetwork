package woodnetwork.hebg3.com.woodnetwork.QiuGou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.QiuGou.activity.WoYaoBaoJiaActivity;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.bean.DemandList_listItem;
import woodnetwork.hebg3.com.woodnetwork.R;

/**
 * Created by ty on 2016/8/31 0031.
 */
public class QiuGouHomeAdapter extends RecyclerView.Adapter<QiuGouHomeAdapter.ViewHolder> {
    private Context context;
    private List<DemandList_listItem> demandList;

    public QiuGouHomeAdapter(Context context, List<DemandList_listItem> demandList) {
        this.context = context;
        this.demandList = demandList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.qiugouhome_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.jiaoHuoDiDian.setText("交货地点："+demandList.get(position).receive_area);
        holder.yiXiangChanPin.setText("意向产品"+demandList.get(position).pname);
        holder.gouMaiShuLiang.setText("购买数量：" + demandList.get(position).number);
        holder.maiJia.setText("买家" + demandList.get(position).buyer);
        holder.lianXiDianHua.setText("联系电话："+demandList.get(position).phone);

    }

    @Override
    public int getItemCount() {
        if (null == demandList || demandList.size() == 0) {
            return 0;
        }
        return demandList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView jiaoHuoDiDian;//交货地点
        private TextView yiXiangChanPin;//意向产品
        private TextView gouMaiShuLiang;//购买数量
        private TextView maiJia;//买家
        private TextView lianXiDianHua;//联系电话
        private Button woYaoBaoJia;//我要报价

        public ViewHolder(View itemView) {
            super(itemView);
            jiaoHuoDiDian = (TextView) itemView.findViewById(R.id.shopppingmalladapter_txt_name);
            yiXiangChanPin = (TextView) itemView.findViewById(R.id.shopppingmalladapter_txt_company);
            gouMaiShuLiang = (TextView) itemView.findViewById(R.id.shopppingmalladapter_txt_productarea);
            maiJia = (TextView) itemView.findViewById(R.id.shopppingmalladapter_txt_price);
            lianXiDianHua = (TextView) itemView.findViewById(R.id.shopppingmalladapter_txt_stock);
            woYaoBaoJia = (Button) itemView.findViewById(R.id.shopppingmalladapter_btn_shoppingcart);
            woYaoBaoJia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intet=new Intent(context, WoYaoBaoJiaActivity.class);
                    intet.putExtra("did",demandList.get(getAdapterPosition()).id);
                    context.startActivity(intet);
                }
            });
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, WoodInfoActivity.class);
//                    intent.putExtra("pid", productInfoList.get(getAdapterPosition()).pid);
//                    context.startActivity(intent);
//                }
//            });
        }
    }
}