package woodnetwork.hebg3.com.woodnetwork.ZiXun.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity.WoodInfoActivity;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductFilterList_productsItem;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.activity.ZiXunListActivity;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.CategoryList;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.CategoryList_listItem;


/**
 * Created by ty on 2016/8/19 0019.
 */

public class ZiXunHomeAdapter extends RecyclerView.Adapter<ZiXunHomeAdapter.ViewHolder> {
    private Context context;
    private List<CategoryList_listItem> list;

    public ZiXunHomeAdapter(Context context, List<CategoryList_listItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ZiXunHomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_fragment_zi_xun, parent, false));
    }

    @Override
    public void onBindViewHolder(ZiXunHomeAdapter.ViewHolder holder, int position) {
        holder.name.setText(list.get(position).name);
//        holder.image.setImageURI(Uri.parse(list.get(position).img));
    }

    @Override
    public int getItemCount() {
        if (null == list || list.size() == 0) {
            return 0;
        }
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;//咨询类别名
        private SimpleDraweeView image;//咨询分类图片
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.adapter_zi_xun_text);
            image=(SimpleDraweeView)itemView.findViewById(R.id.adapter_zi_xun_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ZiXunListActivity.class);
                    intent.putExtra("pid", list.get(getAdapterPosition()).id);
                    intent.putExtra("title", list.get(getAdapterPosition()).name);
                    context.startActivity(intent);
                }
            });
        }
    }
}
