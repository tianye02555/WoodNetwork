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

import woodnetwork.hebg3.com.woodnetwork.QiuGou.activity.QiuGouXiangQingActivity;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.activity.WoYaoBaoJiaActivity;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.bean.DemandList_listItem;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;

/**
 * Created by ty on 2016/8/31 0031.
 */
public class QiuGouHomeAdapter extends RecyclerView.Adapter<QiuGouHomeAdapter.ViewHolder> {
    private Context context;
    private List<DemandList_listItem> demandList;
    private SharePreferencesUtils sharePreferencesUtils;

    public QiuGouHomeAdapter(Context context, List<DemandList_listItem> demandList) {
        this.context = context;
        this.demandList = demandList;
        this.sharePreferencesUtils=SharePreferencesUtils.getSharePreferencesUtils(context);
    }

    public List<DemandList_listItem> getDemandList() {
        return demandList;
    }

    public void setDemandList(List<DemandList_listItem> demandList) {
        this.demandList = demandList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.qiugouhome_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.jiaoHuoDiDian.setText("交货地点："+demandList.get(position).receive_area);
        holder.yiXiangChanPin.setText("意向产品:"+demandList.get(position).pname);
        holder.gouMaiShuLiang.setText("购买数量：" + demandList.get(position).number);
        holder.maiJia.setText("买家" + demandList.get(position).buyer);
        holder.lianXiDianHua.setText("联系电话："+demandList.get(position).phone);
        if(1==demandList.get(position).qtype){
            holder.woYaoBaoJia.setText("已报价");
            holder.woYaoBaoJia.setEnabled(false);
        }
        holder.woYaoBaoJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtils.showToast(context,"请注册卖家进行报价"+sharePreferencesUtils.getData("seller_flag",999));
                if(0==(Integer)sharePreferencesUtils.getData("seller_flag",999)){
                    CommonUtils.showToast(context,"请注册卖家进行报价");
                    return;
                }
                if("已报价".equals(((Button)view).getText())){
                    CommonUtils.showToast(context,"已报价");
                }
                Intent intet=new Intent(context, WoYaoBaoJiaActivity.class);
                intet.putExtra("did",demandList.get(position).id);
                context.startActivity(intet);
            }
        });

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
            jiaoHuoDiDian = (TextView) itemView.findViewById(R.id.qiugouhome_adapter_txt_jiaohuodidian);
            yiXiangChanPin = (TextView) itemView.findViewById(R.id.qiugouhome_adapter_txt_yixiangchanping);
            maiJia = (TextView) itemView.findViewById(R.id.qiugouhome_adapter_txt_maijia);
            gouMaiShuLiang = (TextView) itemView.findViewById(R.id.qiugouhome_adapter_txt_goumaishuliang);
            lianXiDianHua= (TextView) itemView.findViewById(R.id.qiugouhome_adapter_txt_lianxidianhua);
            woYaoBaoJia = (Button) itemView.findViewById(R.id.qiugouhome_adapter_btn_woyaobaojia);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, QiuGouXiangQingActivity.class);
                    intent.putExtra("did", demandList.get(getAdapterPosition()-1).id);
                    context.startActivity(intent);
                }
            });
        }
    }
}