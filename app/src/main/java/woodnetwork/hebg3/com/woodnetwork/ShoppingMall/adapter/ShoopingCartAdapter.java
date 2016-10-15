package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import woodnetwork.hebg3.com.woodnetwork.net.Const;

/**
 * Created by ty on 2016/8/30 0030.
 */

public class ShoopingCartAdapter extends RecyclerView.Adapter<ShoopingCartAdapter.ViewHolder> {
    private Context context;
    private List<ShopcarList_listItem> list;
    private DecimalFormat df;

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
        CommonUtils.displayImage(Uri.parse(Const.PICTURE+list.get(position).pimg),holder.head,context,CommonUtils.isOnlyWIFIDownLoadPic(context));
        df = new DecimalFormat("###############0.00");//   16位整数位，两小数位
        Double number = (list.get(position).stock) * (list.get(position).price);//一个订单的小结
        String stringNumber = df.format(number);//字符串类型的小结
        list.get(position).xiaoJi = Double.parseDouble(stringNumber);
        holder.name.setText(list.get(position).pname);
        holder.company.setText(list.get(position).seller.sname);
        holder.faHuoDi.setText("发货地：" + list.get(position).delivery);
        holder.xuanZhong.setChecked(list.get(position).checkbox);
        holder.price.setText(String.valueOf(list.get(position).price));
        holder.number.setText(String.valueOf(list.get(position).stock));
        if(list.get(position).flag==0){
            holder.shiXiao.setVisibility(View.INVISIBLE);
            holder.xuanZhong.setVisibility(View.VISIBLE);
        }else{
            holder.shiXiao.setVisibility(View.VISIBLE);
            holder.xuanZhong.setVisibility(View.INVISIBLE);
            holder.number.setEnabled(false);
        }
        if(holder.number.getText().toString().trim().equals(String.valueOf(list.get(position).stock))){
            holder.baoCun.setBackgroundResource(R.drawable.button_shape_hui);
            holder.baoCun.setClickable(false);
        }else {
            holder.baoCun.setBackgroundResource(R.drawable.button_shape_title);
            holder.baoCun.setClickable(true);
        }
        holder.number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(holder.number.getText().toString().trim().equals(String.valueOf(list.get(position).stock))){
                    holder.baoCun.setBackgroundResource(R.drawable.button_shape_hui);
                    holder.baoCun.setClickable(false);
                }else {
                    holder.baoCun.setBackgroundResource(R.drawable.button_shape_title);
                    holder.baoCun.setClickable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {


                final String number = editable.toString().trim();
                if ("".equals(number)) {
                    return;
                }
                if ("-".equals(number) || ".".equals(number)) {
                    new AlertDialog.Builder(context).setMessage("只能输入数字").setNeutralButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                           holder.number.setText(null);
                            dialogInterface.dismiss();
                        }
                    }).show();
                    return;
                }
                if (number.contains(".")) {
                    int index = number.indexOf(".");
                    if (number.length() - 1 - index > 3) {
                        holder.number.setText(number.substring(0, index + 4));
                        new AlertDialog.Builder(context).setMessage("最多保留小数点后3位").setNeutralButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).show();
                        return;
                    }
                }
//                if ("".equals(holder.number.getText().toString().trim())||Double.parseDouble(holder.number.getText().toString().trim()) <= 0) {
//                    new AlertDialog.Builder(context).setMessage("数量不能小于0").setNeutralButton("确定", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
////                            holder.number.setText("1");
//                            dialogInterface.dismiss();
//                        }
//                    }).show();

//                    return;
//                }
//                if (Double.parseDouble(holder.number.getText().toString().trim()) >list.get(position).stock ) {
//                    new AlertDialog.Builder(context).setMessage("库存不足").setNeutralButton("确定", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            holder.number.setText(String.valueOf(list.get(position).stock));
//                            dialogInterface.dismiss();
//                        }
//                    }).show();
//                    return;
//                }
                list.get(position).stock = Double.parseDouble(number);

                Double numberTitle = (list.get(position).stock) * (list.get(position).price);


                String temp = df.format(numberTitle);
                holder.xiaoJi.setText(String.valueOf(temp));
                list.get(position).xiaoJi = Double.parseDouble(temp);
                ((ShoopingCartActivity) context).showTitlePrice();
            }
        });
        holder.xiaoJi.setText(String.valueOf(list.get(position).xiaoJi));
        holder.baoCun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.number.getText().toString().trim().equals(String.valueOf(list.get(position).stock))){
                   return;
                }
                if ("0".equals(holder.number.getText().toString().trim())) {
                    CommonUtils.showToast(context,"购买数量必须大于0");
                    return;
                }

                if ("".equals(holder.number.getText().toString().trim())) {
                    CommonUtils.showToast(context,"请输入购买数量");
                    return;
                }

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
                list.get(position).checkbox = b;
                ((ShoopingCartActivity) context).showTitlePrice();
            }
        });
        holder.rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WoodInfoActivity.class);
                intent.putExtra("pid", list.get(position).pid);
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
        private TextView shiXiao;//商品失效
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
            shiXiao = (TextView) itemView.findViewById(R.id.shopppingcart_adapter_text_shixiao);
            number = (EditText) itemView.findViewById(R.id.shopppingcart_adapter_edit_stock);
            rel = (RelativeLayout) itemView.findViewById(R.id.shoppingcar_rel);

            shanChu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    new AlertDialog.Builder(context).setTitle("提示").setMessage("确定删除商品吗").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            list.get(getAdapterPosition() - 1).checkbox = true;
                            SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(context);
                            Request_getAttribute request_getAttribute = new Request_getAttribute();
                            request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");

                            ArrayList<String> sidList = new ArrayList<String>();
                            sidList.add(list.get(getAdapterPosition() - 1).sid);
                            Request_shopcar_delete request_shopcar_delete = new Request_shopcar_delete();
                            request_shopcar_delete.sid = sidList;

                            MyRequestInfo myRequestInfo = new MyRequestInfo();
                            myRequestInfo.req = request_shopcar_delete;
                            myRequestInfo.req_meta = request_getAttribute;
                            ((ShoopingCartActivity) context).presenter.deleteGoods(myRequestInfo);

                        }
                    }).show();


                }
            });

        }
    }

    public List<ShopcarList_listItem> getShoopingCarList() {
        return list;
    }
}
