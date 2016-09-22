package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_shopcar_delete;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_shopcar_update;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_shopcar_update_listItem;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity.ShoopingCartActivity;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity.WoodInfoActivity;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopcarList_listItem;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;

/**
 * Created by ty on 2016/8/30 0030.
 */

public class ShoopingCartAdapter extends RecyclerView.Adapter<ShoopingCartAdapter.ViewHolder> {
    private Context context;
    private List<ShopcarList_listItem> list;
    private List<Double> xiaoJiList = new ArrayList<Double>();

    public ShoopingCartAdapter(Context context, List<ShopcarList_listItem> list) {
        this.context = context;
        this.list = list;
    }

    public List<ShopcarList_listItem> getList() {
        return list;
    }

    public void setList(List<ShopcarList_listItem> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shoopingcart_adapter, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        list.get(position).xiaoJi = (list.get(position).stock) * (list.get(position).price);
        holder.name.setText(list.get(position).pname);
        holder.company.setText(list.get(position).seller.sname);
        holder.faHuoDi.setText("发货地：" + list.get(position).delivery);
        holder.xuanZhong.setChecked(list.get(position).checkbox);
        holder.price.setText(String.valueOf(list.get(position).price));
        holder.number.setText(String.valueOf(list.get(position).stock));
        holder.number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                final String number = editable.toString().trim();
                if (number.contains(".")) {
                    int index = number.indexOf(".");
                    if (number.length() - 1 - index > 3) {
                        holder.number.setText(number.substring(0, index + 4));
                        new AlertDialog.Builder(context).setMessage("最多保留小数点后3位").setNeutralButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                holder.number.setSelection(holder.number.getText().length());//设置光标的位置到最后
                                dialogInterface.dismiss();
                            }
                        }).show();
                        return;
                    }
                }
                if (Double.parseDouble(holder.number.getText().toString().trim()) <= 1) {
                    new AlertDialog.Builder(context).setMessage("数量不能小于1").setNeutralButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            holder.number.setText("1");
                            dialogInterface.dismiss();
                        }
                    }).show();

                    return;
                }
                list.get(position).stock = Double.parseDouble(number);

                Double numberTitle = (list.get(position).stock) * (list.get(position).price);
//                if (numberTitle.contains(".")) {
//                    int index = numberTitle.indexOf(".");
//                    if (numberTitle.length() - 1 - index > 2) {
//                        numberTitle=numberTitle.substring(0, index + 3);
//                    }
//                }
//                numberTitle=(double)Math.round(numberTitle*100)/100;

                DecimalFormat   df   =     new DecimalFormat( "###############0.00 ");//   16位整数位，两小数位
                String   temp     =   df.format(numberTitle);
                holder.xiaoJi.setText(String.valueOf(temp));
                list.get(position).xiaoJi =Double.parseDouble(temp) ;
                ((ShoopingCartActivity) context).showTitlePrice();
            }
        });
        holder.xiaoJi.setText(String.valueOf(list.get(position).xiaoJi));
        holder.baoCun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(context);
                Request_getAttribute request_getAttribute = new Request_getAttribute();
                request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");

                ArrayList<Request_shopcar_update_listItem> updateList = new ArrayList<Request_shopcar_update_listItem>();

                Request_shopcar_update_listItem request_shopcar_update_listItem = new Request_shopcar_update_listItem();
                request_shopcar_update_listItem.sid = list.get(position).sid;
                request_shopcar_update_listItem.number = String.valueOf(list.get(position).stock);

                updateList.add(request_shopcar_update_listItem);
                Request_shopcar_update request_shopcar_update = new Request_shopcar_update();
                request_shopcar_update.list = updateList;

                MyRequestInfo myRequestInfo = new MyRequestInfo();
                myRequestInfo.req = request_shopcar_update;
                myRequestInfo.req_meta = request_getAttribute;
                ((ShoopingCartActivity) context).presenter.changeGoodsNumber(myRequestInfo);


            }
        });
        holder.xuanZhong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                    if(b){
//
//                        ((ShoopingCartActivity)context).setDeleteSid(list.get(getAdapterPosition()).sid,String.valueOf(getAdapterPosition()));
//                    }else{
//                        ((ShoopingCartActivity)context).setDeleteSid(list.get(getAdapterPosition()).sid,String.valueOf(getAdapterPosition()));
//                    }
                list.get(position).checkbox = b;
                ((ShoopingCartActivity) context).showTitlePrice();
            }
        });
        holder.rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, WoodInfoActivity.class);
                intent.putExtra("pid",list.get(position).pid);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (null != list && 0 != list.size()) {
            return list.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView head;//木材头像
        private TextView name;//木材名字
        private TextView company;//木材所属公司
        private TextView faHuoDi;//木材发货地
        private TextView price;//单价
        private TextView stock;//库存
        private Button shanChu;//删除
        private Button baoCun;//保存
        private CheckBox xuanZhong;//选中按钮
        private TextView xiaoJi;//小计
        private EditText number;//数量
        private RelativeLayout rel;

        public ViewHolder(View itemView) {
            super(itemView);
            head = (SimpleDraweeView) itemView.findViewById(R.id.shopppingcart_adapter_simpledraweeview_head);
            name = (TextView) itemView.findViewById(R.id.shopppingcart_adapter_txt_name);
            company = (TextView) itemView.findViewById(R.id.shopppingcart_adapter_txt_company);
            faHuoDi = (TextView) itemView.findViewById(R.id.shopppingcart_adapter_txt_productarea);
            price = (TextView) itemView.findViewById(R.id.shopppingcart_adapter_txt_price);
            stock = (TextView) itemView.findViewById(R.id.shopppingcart_adapter_txt_stock);
            shanChu = (Button) itemView.findViewById(R.id.shopppingcart_adapter_btn_shanchu);
            baoCun = (Button) itemView.findViewById(R.id.shopppingcart_adapter_btn_baocun);
            xuanZhong = (CheckBox) itemView.findViewById(R.id.shopppingcart_adapter_checkbox);
            xiaoJi = (TextView) itemView.findViewById(R.id.shopppingcart_adapter_txt_xiaojijinge);
            number = (EditText) itemView.findViewById(R.id.shopppingcart_adapter_edit_stock);
rel=(RelativeLayout) itemView.findViewById(R.id.shoppingcar_rel);

            shanChu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(context);
                    Request_getAttribute request_getAttribute = new Request_getAttribute();
                    request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");

                    ArrayList<String> sidList = new ArrayList<String>();
                    sidList.add("1234");
                    sidList.add("2234");
                    sidList.add("3234");
//                    sidList.add(list.get(getAdapterPosition()).sid);
                    Request_shopcar_delete request_shopcar_delete = new Request_shopcar_delete();
                    request_shopcar_delete.sid = sidList;

                    MyRequestInfo myRequestInfo = new MyRequestInfo();
                    myRequestInfo.req = request_shopcar_delete;
                    myRequestInfo.req_meta = request_getAttribute;
                    ((ShoopingCartActivity) context).presenter.deleteGoods(myRequestInfo);

                }
            });


//            itemVw.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, WoodInfoActivity.class);
//                    intent.putExtra("pid", list.get(getAdapterPosition()).pid);
//                    context.startActivity(intent);
//                }
//            });
        }
    }

    public List<ShopcarList_listItem> getShoopingCarList() {
        return list;
    }
}
