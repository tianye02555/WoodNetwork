package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils_shaiXuan;
import woodnetwork.hebg3.com.woodnetwork.view.FlowRadioGroup;
import woodnetwork.hebg3.com.woodnetwork.view.MyGridView;

/**
 * Created by ty on 2016/9/6 0006.
 */

public class ShaiXuanAdapter extends BaseAdapter {
    private Context context;
    private List<WoodFilterInfo> list;
    private List<Request_spinnerInfo> shaiXuanList;//筛选中间的值得集合
    private Request_spinnerInfo request_spinnerInfo;//每一项里边的筛选值
    private List<Request_spinnerInfo> shaiXuanListFinal;//筛选最后的值得集合
    private SharePreferencesUtils_shaiXuan sharePreferencesUtils;

    public void setNeedGetsp(boolean needGetsp) {
        isNeedGetsp = needGetsp;
    }

    private boolean isNeedGetsp = true;

    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    public void setEditor(SharedPreferences.Editor editor) {
        this.editor = editor;
    }

    private SharedPreferences.Editor editor;

    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

    private int selectPosition = 0;

    public ShaiXuanAdapter(Context context, List<WoodFilterInfo> list) {
        this.context = context;
        this.list = list;
        shaiXuanList = new ArrayList<Request_spinnerInfo>();
        shaiXuanListFinal = new ArrayList<Request_spinnerInfo>();
        sharePreferencesUtils = SharePreferencesUtils_shaiXuan.getSharePreferencesUtils(context);
        editor = sharePreferencesUtils.getEditor();
        for (int i = 0; i < list.size(); i++) {//添加中间请求体
            request_spinnerInfo = new Request_spinnerInfo();
            shaiXuanList.add(request_spinnerInfo);
        }
    }

    public List<Request_spinnerInfo> getShaiXuanList() {
        for (int j = 0; j < shaiXuanList.size(); j++) {//添加最终请求体
            shaiXuanListFinal.add(shaiXuanList.get(j));
        }
        for (int i = 0; i < shaiXuanListFinal.size(); i++) {//去除所选的全部的请求体
            if ("quanbu".equals(shaiXuanListFinal.get(i).attr_vaule_id)) {
                shaiXuanListFinal.remove(i);
                i -= 1;//改变了集合的数量，减一适配remove操作
            }
        }
        return shaiXuanListFinal;
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
        if (isNeedGetsp) {
            for (int l = 0; l < list.get(position).value.size(); l++) {
                if (list.get(position).value.get(l).value_name.equals((String) (sharePreferencesUtils.getData(list.get(position).name, "")))) {
                    setSelectPosition(l);
                }
            }
        }


        ViewHodler holder = null;
        if (null == contentView) {
            holder = new ViewHodler();
            contentView = LayoutInflater.from(context).inflate(R.layout.adapter_shuaixuan, viewGroup, false);
            holder.radioGroup = (FlowRadioGroup) contentView.findViewById(R.id.adapter_shaixuan_radiogroup);
            holder.textView = (TextView) contentView.findViewById(R.id.adapter_shaixuan_text);

            holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {

                    RadioButton radioButton = (RadioButton) radioGroup.findViewById(i);
                        editor.putString(list.get(position).name, radioButton.getText().toString());
                    //获取选择的分类类型
                    shaiXuanList.get(position).attr_id = list.get(position).attr_id;
                    shaiXuanList.get(position).attr_vaule_id = (String) radioButton.getTag();
                }
            });
            RadioButton radioButton = null;
//            View view=null;
            //根据返回的类型数 动态加载radiobutton
            for (int i = 0; i < list.get(position).value.size(); i++) {
                //根据xml  获取radiobutton
                radioButton = new RadioButton(context);
                RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(
                        30, RadioGroup.LayoutParams.WRAP_CONTENT);//这里设置radiogroup的宽高
                radioButton.setLayoutParams(layoutParams);
                radioButton.setGravity(Gravity.CENTER);
                radioButton.setButtonDrawable(android.R.color.transparent);//隐藏单选圆形按钮
                radioButton.setBackgroundResource(R.drawable.selector_shaixuan_drawable);
                radioButton.setText(list.get(position).value.get(i).value_name);
                radioButton.setTag(list.get(position).value.get(i).value_id);
                holder.radioGroup.addView(radioButton);
            }
            contentView.setTag(holder);
        } else {
            holder = (ViewHodler) contentView.getTag();
        }
        holder.textView.setText(list.get(position).name);
        ((RadioButton) holder.radioGroup.getChildAt(selectPosition)).setChecked(true);//默认选择第一个
        return contentView;
    }

    class ViewHodler {
        private FlowRadioGroup radioGroup;
        private TextView textView;

    }
}
