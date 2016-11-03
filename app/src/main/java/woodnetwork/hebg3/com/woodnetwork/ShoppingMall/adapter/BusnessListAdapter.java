package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity.BusnessInfoActivity;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.net.Const;

/**
 * Created by ty on 2016/8/25 0025.
 */

public class BusnessListAdapter extends RecyclerView.Adapter<BusnessListAdapter.BusnessHolder> {
    @Bind(R.id.busnesslistadapterlayout_layout)
    RelativeLayout busnesslistadapterlayoutLayout;
    private static Context context;
    private static List<BusnessInfo> list;

    public BusnessListAdapter(Context context, List<BusnessInfo> list) {
        this.context = context;
        this.list = list;
    }

    public static List<BusnessInfo> getList() {
        return list;
    }

    public static void setList(List<BusnessInfo> list) {
        BusnessListAdapter.list = list;
    }

    @Override
    public BusnessHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new BusnessHolder(LayoutInflater.from(context).inflate(R.layout.busnesslistadapterlayout, parent, false));
    }

    @Override
    public void onBindViewHolder(BusnessHolder holder, int position) {
        CommonUtils.displayImage(Uri.parse(Const.PICTURE+list.get(position).img),holder.image_head,context,CommonUtils.isOnlyWIFIDownLoadPic(context));
        holder.text_company.setText(list.get(position).name);
        holder.text_email.setText(list.get(position).mail);
        holder.text_phone.setText(list.get(position).phone);
    }

    @Override
    public int getItemCount() {
        if (null != list && 0 != list.size()) {
            return list.size();
        }
        return 0;
    }

    static class BusnessHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.busnesslistadapterlayout_image_head)
        SimpleDraweeView image_head;
        @Bind(R.id.busnesslistadapterlayout_text_company)
        TextView text_company;
        @Bind(R.id.busnesslistadapterlayout_text_email)
        TextView text_email;
        @Bind(R.id.busnesslistadapterlayout_text_phone)
        TextView text_phone;
        @Bind(R.id.busnesslistadapterlayout_image_phone)
        SimpleDraweeView image_phone;

        public BusnessHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            image_phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(context).setTitle("提示").setMessage("您将通过打电话的方式联系卖家").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+list.get(getAdapterPosition()-1).phone));
                            context.startActivity(intent);

                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).show();

                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context,BusnessInfoActivity.class);
                    intent.putExtra("sid",list.get(getAdapterPosition()-1).id);
                    intent.putExtra("from","BusnessInfoListActivity");
                    context.startActivity(intent);
                }
            });

        }
    }

}
