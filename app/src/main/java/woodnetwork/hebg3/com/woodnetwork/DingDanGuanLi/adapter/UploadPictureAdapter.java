package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.activity.ExceptionAddActivity;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.activity.OrderReceiveActivity;
import woodnetwork.hebg3.com.woodnetwork.R;

/**
 * Created by ty on 2016/8/31 0031.
 */
public class UploadPictureAdapter extends RecyclerView.Adapter<UploadPictureAdapter.ViewHolder> {
    private Context context;
    private List<Bitmap> list;
    public int flag=0;

    public List<Bitmap> getList() {
        return list;
    }

    public void setList(List<Bitmap> list) {
        this.list = list;
    }

    public UploadPictureAdapter(Context context, List<Bitmap> list,int flag) {
        this.context = context;
        this.list = list;
        this.flag=flag;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_uppictrue, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.imag.setImageBitmap(list.get(position));
        if(position+1==list.size()){
            holder.cancle.setVisibility(View.GONE);
            holder.baiDi.setVisibility(View.GONE);
        }else{
            holder.cancle.setVisibility(View.VISIBLE);
            holder.baiDi.setVisibility(View.VISIBLE);
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
        private ImageView imag;//内容图片
        private ImageView baiDi;//小叉号的白色底图
        private SimpleDraweeView cancle;//小叉号
        public ViewHolder(View itemView) {
            super(itemView);
            imag = (ImageView) itemView.findViewById(R.id.adapter_upload_image);
            cancle = (SimpleDraweeView) itemView.findViewById(R.id.adapter_upload_cancel);
            baiDi= (ImageView) itemView.findViewById(R.id.baidi);
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
                       if(flag==1){
                           ((OrderReceiveActivity)context).addReceivePicture(view);
                       }else {
                           ((ExceptionAddActivity) context).addExceptionPicture(view);
                       }
                   }
                }
            });
        }
    }
}