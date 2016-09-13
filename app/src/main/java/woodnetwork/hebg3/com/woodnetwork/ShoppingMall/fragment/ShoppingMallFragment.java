package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_shopcarAdd;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_shoppingMall_woodsList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_spinnerInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity.ConfirmOrderActivity;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity.ShoopingCartActivity;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter.ShaiXuanAdapter;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter.ShoppingMalAdapter;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductFilterList_productsItem;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductFilterList_productsItem_attributesItem;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopcarList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopcarList_listItem;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopcarList_listItem_attributeItem;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopcarList_listItem_seller;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.WoodFilterAttribute;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract.ShoppingMallContract;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.presenter.ShoppingMallPresenter;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.view.MyListView;


public class ShoppingMallFragment extends Fragment implements ShoppingMallContract.ShoppingMallView {


    @Bind(R.id.fragment_shopping_mall_recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_gouwuche)
    ImageView gouWuChe;
    @Bind(R.id.image_title_shaixuan)
    ImageView shuaiXuan;
    @Bind(R.id.mylistview)
    ListView mylistview;
    @Bind(R.id.text_chongzhi)
    TextView textChongzhi;
    @Bind(R.id.text_queding)
    TextView textQueding;
    @Bind(R.id.rel_shaixuan)
    RelativeLayout relShaixuan;
    /**
     * 首页物品适配器
     */
    private ShoppingMalAdapter shoppingMalAdapter;
    private ShoppingMallContract.ShoppingMallPresenter shoppingMallPresenter;
    private Request_shoppingMall_woodsList request_shoppingMall_woodsList;
    private List<Request_spinnerInfo> request_spinnerInfoList = new ArrayList<Request_spinnerInfo>();
    /**
     * 筛选适配器
     */
    private ShaiXuanAdapter shaiXuanAdapter;
    /**
     * 重置筛选用
     */
    private WoodFilterAttribute woodFilterAttribute;
    private SharePreferencesUtils sharePreferencesUtils;
    private MyRequestInfo myRequestInfo;
    private List<ProductFilterList_productsItem> list;
    private EditText number;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_mall, container, false);
        ButterKnife.bind(this, view);

        textTitle.setText("木联网");


        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, 5));

        Request_spinnerInfo request_spinnerInfo = new Request_spinnerInfo();
        request_spinnerInfo.attr_vaule_id = "111";
        request_spinnerInfo.attr_id = "1234";
        request_spinnerInfoList.add(request_spinnerInfo);
        Request_spinnerInfo request_spinnerInfo1 = new Request_spinnerInfo();
        request_spinnerInfo1.attr_vaule_id = "444";
        request_spinnerInfo1.attr_id = "2234";
        request_spinnerInfoList.add(request_spinnerInfo1);
        request_shoppingMall_woodsList = new Request_shoppingMall_woodsList();
        request_shoppingMall_woodsList.page_no = 1;
        request_shoppingMall_woodsList.page_size = 10;
        request_shoppingMall_woodsList.attribute = request_spinnerInfoList;


        new ShoppingMallPresenter(this);
        sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(getActivity());
        Request_getAttribute request_getAttribute = new Request_getAttribute();
        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");
        myRequestInfo = new MyRequestInfo();
        myRequestInfo.req = new Object();
        myRequestInfo.req_meta = request_getAttribute;
        shoppingMallPresenter.getAttributeFilterListData(myRequestInfo);
        shoppingMallPresenter.getWoodsList(request_shoppingMall_woodsList);

        return view;
    }

    @Override
    public void jumpActivity(Class mClass) {
        startActivity(new Intent(getActivity(), mClass));
    }


    @Override
    public void showGoodsData(List<ProductFilterList_productsItem> list) {
        this.list = list;
        shoppingMalAdapter = new ShoppingMalAdapter(getActivity(), list, this);
        recyclerview.setAdapter(shoppingMalAdapter);
        closeProgress();
    }

    @Override
    public void showAttributeFilterListInfo(WoodFilterAttribute woodFilterAttribute) {
        this.woodFilterAttribute = woodFilterAttribute;
        shaiXuanAdapter = new ShaiXuanAdapter(getActivity(), woodFilterAttribute.attribute);
        mylistview.setAdapter(shaiXuanAdapter);
    }

    @Override
    public void showNumberDialog(final int id, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.inputnumber, null);
        number = (EditText) view.findViewById(R.id.inputnumber_edit);
        number.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().contains(".")) {
                    int index = editable.toString().indexOf(".");
                    if (editable.toString().trim().length() - 1 - index > 3) {
                        number.setText(editable.toString().trim().substring(0, index + 4));
                        new AlertDialog.Builder(getActivity()).setMessage("最多保留小数点后3位").setNeutralButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                number.setSelection(number.getText().length());//设置光标的位置到最后
                                dialogInterface.dismiss();
                            }
                        }).show();
                    }
                }

            }
        });
        builder.setView(view);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNeutralButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (Double.parseDouble(number.getText().toString().trim()) > list.get(position).stock) {
                    new AlertDialog.Builder(getActivity()).setMessage("库存不足").setNeutralButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).show();
                    number.setText(String.valueOf(list.get(position).stock));
                } else if (Double.parseDouble(number.getText().toString().trim()) <= 0) {
                    new AlertDialog.Builder(getActivity()).setMessage("输入错误").setNeutralButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).show();
                    number.setText(String.valueOf(list.get(position).stock));
                } else {
                    if (id == R.id.shopppingmalladapter_btn_buy) {//直接购买
                        Intent intent = new Intent(getActivity(), ConfirmOrderActivity.class);
                        ShopcarList shopcarList;
                        //构造ShopcarList对象，适配结算页面
                        {
                            shopcarList = new ShopcarList();

                            ShopcarList_listItem_seller shopcarList_listItem_seller = new ShopcarList_listItem_seller();
                            shopcarList_listItem_seller.sid = list.get(position).sid;
                            shopcarList_listItem_seller.sname = list.get(position).seller;

                            List<ShopcarList_listItem_attributeItem> shopcarList_listItem_attributeItem_list = new ArrayList<ShopcarList_listItem_attributeItem>();

                            ShopcarList_listItem_attributeItem shopcarList_listItem_attributeItem = null;

                            for (ProductFilterList_productsItem_attributesItem productFilterList_productsItem_attributesItem : list.get(position).attributes) {
                                shopcarList_listItem_attributeItem = new ShopcarList_listItem_attributeItem();
                                shopcarList_listItem_attributeItem.name = productFilterList_productsItem_attributesItem.attr_name;
                                shopcarList_listItem_attributeItem.value = productFilterList_productsItem_attributesItem.attr_value;
                                shopcarList_listItem_attributeItem_list.add(shopcarList_listItem_attributeItem);
                            }

                            List<ShopcarList_listItem> shopcarList_listItem_list = new ArrayList<ShopcarList_listItem>();
                            ShopcarList_listItem shopcarList_listItem = new ShopcarList_listItem();
                            shopcarList_listItem.attribute = shopcarList_listItem_attributeItem_list;
                            shopcarList_listItem.pid = list.get(position).pid;
                            shopcarList_listItem.seller = shopcarList_listItem_seller;
                            shopcarList_listItem.pname = list.get(position).pname;
                            shopcarList_listItem.price = list.get(position).price;
                            shopcarList_listItem.stock = Double.parseDouble(number.getText().toString().trim());
                            shopcarList_listItem.zongJia = (list.get(position).price) * (Double.parseDouble(number.getText().toString().trim()));
                            shopcarList_listItem_list.add(shopcarList_listItem);

                            shopcarList.list = shopcarList_listItem_list;
                        }

                        intent.putExtra("shopcarList", shopcarList);
                        startActivity(intent);
                    } else if (id == R.id.shopppingmalladapter_btn_shoppingcart) {//加入购物车
                        Request_shopcarAdd request_shopcarAdd = new Request_shopcarAdd();
                        request_shopcarAdd.number = 500;
                        request_shopcarAdd.pid = "1234";//实际为参数list.get(position).pid

                        myRequestInfo.req = request_shopcarAdd;
                        shoppingMallPresenter.shopcarAdd(myRequestInfo);

                    }
                    dialogInterface.dismiss();
                }


            }
        });
        builder.show();
    }


    @Override
    public void setPresenter(ShoppingMallContract.ShoppingMallPresenter presenter) {
        if (null == presenter) {
            return;
        }
        this.shoppingMallPresenter = presenter;

    }

    @Override
    public void showProgress() {
        ProgressUtils.show(getActivity(), "请稍后……");
    }

    @Override
    public void closeProgress() {
        ProgressUtils.hide();
    }

    @Override
    public void showMessage(String string) {
        CommonUtils.showToast(getActivity(), string);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.image_title_gouwuche, R.id.image_title_shaixuan, R.id.text_chongzhi, R.id.text_queding})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_title_gouwuche://购物车
                startActivity(new Intent(getActivity(), ShoopingCartActivity.class));
                break;
            case R.id.image_title_shaixuan://筛选图标
                if (View.GONE == relShaixuan.getVisibility()) {
                    relShaixuan.setVisibility(View.VISIBLE);
                    recyclerview.setVisibility(View.GONE);
                } else {
                    relShaixuan.setVisibility(View.GONE);
                    recyclerview.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.text_chongzhi://筛选重置
                shaiXuanAdapter = new ShaiXuanAdapter(getActivity(), woodFilterAttribute.attribute);
                mylistview.setAdapter(shaiXuanAdapter);
                break;
            case R.id.text_queding://筛选确定
                relShaixuan.setVisibility(View.GONE);
                recyclerview.setVisibility(View.VISIBLE);
                request_shoppingMall_woodsList.attribute = shaiXuanAdapter.getShaiXuanList();
                shoppingMallPresenter.getWoodsList(request_shoppingMall_woodsList);
                break;
        }
    }

}
