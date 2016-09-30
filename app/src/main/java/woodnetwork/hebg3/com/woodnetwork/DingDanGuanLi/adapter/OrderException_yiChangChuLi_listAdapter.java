package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.ExceptionList_exceptionItem;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.view.MyGridView;

/**
 * Created by ty on 2016/9/6 0006.
 */

public class OrderException_yiChangChuLi_listAdapter extends BaseAdapter {
    private Context context;
    private List<ExceptionList_exceptionItem> list;
    private OrderException_yiChangXinXi_gridAdapter adapter;
    public OrderException_yiChangChuLi_listAdapter(Context context, List<ExceptionList_exceptionItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (null != list && 0 != list.size()) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup viewGroup) {
        ViewHodler holder = null;
        if (null == contentView) {
            holder = new ViewHodler();
            contentView = LayoutInflater.from(context).inflate(R.layout.adapter_orderexception, viewGroup, false);
            holder.content = (TextView) contentView.findViewById(R.id.activity_order_exception_text_yichangxinxi);
            holder.gridView=(MyGridView) contentView.findViewById(R.id.activity_order_exception_gridview);
            contentView.setTag(holder);
        } else {
            holder = (ViewHodler) contentView.getTag();
        }
        holder.content.setText(list.get(position).content);
        adapter=new OrderException_yiChangXinXi_gridAdapter(context,list.get(position).imgs,list.get(position).type);
        holder.gridView.setAdapter(adapter);
        return contentView;
    }

    class ViewHodler {
        private TextView content;
        private MyGridView gridView;
    }
}
