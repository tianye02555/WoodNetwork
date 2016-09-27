package woodnetwork.hebg3.com.woodnetwork.ZiXun.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.activity.ZiXunXiangQingActivity;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.ArticleList_listItem;
import woodnetwork.hebg3.com.woodnetwork.net.Const;

/**
 * Created by ty on 2016/8/31 0031.
 */
public class ZiXunListAdapter extends RecyclerView.Adapter<ZiXunListAdapter.ViewHolder> {
    private Context context;
    private List<ArticleList_listItem> articleList;

    public ZiXunListAdapter(Context context, List<ArticleList_listItem> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    public List<ArticleList_listItem> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<ArticleList_listItem> articleList) {
        this.articleList = articleList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_zixunlist, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.newName.setText(articleList.get(position).title);
        holder.newFrom.setText(articleList.get(position).author);
        holder.newDate.setText( articleList.get(position).time);
        holder.imag.setImageURI(Uri.parse(Const.PICTURE+articleList.get(position).img));
    }

    @Override
    public int getItemCount() {
        if (null == articleList || articleList.size() == 0) {
            return 0;
        }
        return articleList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView newName;//新闻头
        private TextView newFrom;//新闻来源
        private TextView newDate;//新闻日期
        private SimpleDraweeView imag;//新闻图片

        public ViewHolder(View itemView) {
            super(itemView);
            newName = (TextView) itemView.findViewById(R.id.adapter_zixunlist_txt_newsname);
            newFrom = (TextView) itemView.findViewById(R.id.adapter_zixunlist_txt_newsfrom);
            newDate = (TextView) itemView.findViewById(R.id.adapter_zixunlist_txt_newsdate);
            imag = (SimpleDraweeView) itemView.findViewById(R.id.adapter_zixunlist_imag);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ZiXunXiangQingActivity.class);
                    intent.putExtra("url", articleList.get(getAdapterPosition()).url);
                    context.startActivity(intent);
                }
            });
        }
    }
}