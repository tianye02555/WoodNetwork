package woodnetwork.hebg3.com.woodnetwork.WoDe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.DemandBuyerInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.DemandBuyerInfo_attributeItem;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.QuotationInfo_attributeItem;

/**
 * Created by ty on 2016/9/6 0006.
 */

public class DemandBuyerInfoAdapter extends BaseAdapter {
    private Context context;
    private List<DemandBuyerInfo_attributeItem> list;

    public DemandBuyerInfoAdapter(Context context, List<DemandBuyerInfo_attributeItem> list) {
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
            contentView = LayoutInflater.from(context).inflate(R.layout.adapter_quotation, viewGroup, false);
            holder.name = (TextView) contentView.findViewById(R.id.adapter_quotationinfo_txt_name);
            holder.value = (TextView) contentView.findViewById(R.id.adapter_quotationinfo_txt_value);
            contentView.setTag(holder);
        } else {
            holder = (ViewHodler) contentView.getTag();
        }
        holder.name.setText(list.get(position).name+" : ");
        holder.value.setText(list.get(position).value);
        return contentView;
    }

    class ViewHodler {
        private TextView name;
        private TextView value;
    }
}
