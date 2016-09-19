package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.activity.ExceptionAddActivity;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.activity.ZiXunXiangQingActivity;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.ArticleList_listItem;

import static android.R.attr.bitmap;

/**
 * Created by ty on 2016/8/31 0031.
 */
public class ExceptionAddAdapter extends RecyclerView.Adapter<ExceptionAddAdapter.ViewHolder> {
    private Context context;
    private List<Bitmap> list;

    public List<Bitmap> getList() {
        return list;
    }

    public void setList(List<Bitmap> list) {
        this.list = list;
    }

    public ExceptionAddAdapter(Context context, List<Bitmap> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_exceptionadd, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.imag.setImageURI(Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(), list.get(position), null,null)));
        if(position+1==list.size()){
            holder.cancle.setVisibility(View.GONE);
        }else{
            holder.cancle.setVisibility(View.VISIBLE);
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
        private SimpleDraweeView imag;//内容图片
        private SimpleDraweeView cancle;//小叉号
        public ViewHolder(View itemView) {
            super(itemView);
            imag = (SimpleDraweeView) itemView.findViewById(R.id.adapter_exceptionadd_image);
            cancle = (SimpleDraweeView) itemView.findViewById(R.id.adapter_exceptionadd_cancel);
            cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.remove(list.get(getAdapterPosition()));

                    notifyItemRemoved(getAdapterPosition());
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   if(list.size()==getAdapterPosition()+1){
                       ((ExceptionAddActivity)context).addExceptionPicture(view);
                   }
                }
            });
        }
    }
}