package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_spinnerInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.WoodFilterInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.view.FlowRadioGroup;

/**
 * Created by ty on 2016/9/6 0006.
 */

public class ShaiXuanAdapter extends BaseAdapter {
    private Context context;
    private List<WoodFilterInfo> list;
    private List<Request_spinnerInfo> shaiXuanList;//筛选最后的值得集合
    private Request_spinnerInfo request_spinnerInfo;//每一项里边的筛选值

    public ShaiXuanAdapter(Context context, List<WoodFilterInfo> list) {
        this.context = context;
        this.list = list;
        shaiXuanList = new ArrayList<Request_spinnerInfo>();
        for (int i = 0; i < list.size(); i++) {
            request_spinnerInfo = new Request_spinnerInfo();
            shaiXuanList.add(request_spinnerInfo);
        }
    }

    public List<Request_spinnerInfo> getShaiXuanList() {
        return shaiXuanList;
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
    public View getView(final int position, View contentView, ViewGroup viewGroup) {
        ViewHodler holder = null;
        if (null == contentView) {
            holder = new ViewHodler();
            contentView = LayoutInflater.from(context).inflate(R.layout.adapter_shuaixuan, viewGroup, false);
            holder.radioGroup = (FlowRadioGroup) contentView.findViewById(R.id.adapter_shaixuan_radiogroup);
            holder.textView = (TextView) contentView.findViewById(R.id.adapter_shaixuan_text);

            holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    RadioButton radioButton=(RadioButton) radioGroup.findViewById(i);
                    //获取选择的分类类型
                    shaiXuanList.get(position).attr_id = list.get(position).attr_id;
                    shaiXuanList.get(position).attr_vaule_id = (String) radioButton.getTag();
                }
            });
            RadioButton radioButton = null;
            //根据返回的类型数 动态加载radiobutton
            for (int i = 0; i < list.get(position).value.size(); i++) {
                //根据xml  获取radiobutton
                radioButton = (RadioButton) LayoutInflater.from(context).inflate(R.layout.radiobutton, null);
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(150,100);
                layoutParams.setMargins(10,10,10,10);
                radioButton.setLayoutParams(layoutParams);
                radioButton.setGravity(Gravity.CENTER);
                radioButton.setBackgroundResource(R.drawable.selector_shaixuan_drawable);
                radioButton.setText(list.get(position).value.get(i).value_name);
                radioButton.setTag(list.get(position).value.get(i).value_id);
//            radioButton.setId(Integer.parseInt(list.get(position).value.get(i).value_id, 10));
//            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                    if(b){
//                        compoundButton.setBackgroundColor(context.getResources().getColor(R.color.title));
//                    }
//                }
//            });
                holder.radioGroup.addView(radioButton, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
            contentView.setTag(holder);
        } else {
            holder = (ViewHodler) contentView.getTag();
        }


        holder.textView.setText(list.get(position).name);
        ((RadioButton) holder.radioGroup.getChildAt(0)).setChecked(true);//默认选择第一个
        return contentView;
    }

    class ViewHodler {
        private FlowRadioGroup radioGroup;
        private TextView textView;
    }
}
