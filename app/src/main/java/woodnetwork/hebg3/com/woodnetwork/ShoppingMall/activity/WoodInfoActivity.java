package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_shopcarAdd;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter.WoodInfoAdapter;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter.WoodInfoOtherAdapter;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopcarList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopcarList_listItem;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopcarList_listItem_attributeItem;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopcarList_listItem_seller;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.WoodSpecifications;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract.WoodInfoContrac;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.presenter.WoodInfoPresenter;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.ProductSellerInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.ProductSellerInfo_attributeItem;
import woodnetwork.hebg3.com.woodnetwork.net.Const;
import woodnetwork.hebg3.com.woodnetwork.view.MyGallery;
import woodnetwork.hebg3.com.woodnetwork.view.MyListView;

/**
 * 商品详情页
 */
public class WoodInfoActivity extends AppCompatActivity implements WoodInfoContrac.WoodInfoViewInterFace {


    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.home_customgallery)
    MyGallery gallery;
    @Bind(R.id.home_ll_dot_container)
    LinearLayout DotContainer;
    @Bind(R.id.home_rl_tp)
    RelativeLayout homeRlTp;
    @Bind(R.id.activity_wood_info_txt_name)
    TextView name;
    @Bind(R.id.activity_wood_info_txt_price)
    TextView price;
    @Bind(R.id.activity_wood_info_txt_kucun)
    TextView stock;
    @Bind(R.id.activity_wood_info_txt_chukudi)
    TextView address;
    @Bind(R.id.activity_wood_info_txt_kind)
    TextView kind;
    @Bind(R.id.activity_wood_info_txt_state)
    TextView state;
    @Bind(R.id.activity_wood_info_txt_shangpingguige)
    TextView shangpingguige;
    @Bind(R.id.activity_wood_info_txt)
    TextView activityWoodInfoTxt;
    @Bind(R.id.activity_wood_info_btn_lijigoumai)
    Button buy;
    @Bind(R.id.activity_wood_info_btn_jiarugouwuche)
    Button addToShoppingCart;
    @Bind(R.id.activity_wood_info_listview)
    MyListView listview;
    @Bind(R.id.line)
    View line;
    @Bind(R.id.activity_wood_info)
    RelativeLayout activityWoodInfo;
    @Bind(R.id.activity_wood_info_scrollview)
    ScrollView Scrollview;
    @Bind(R.id.activity_wood_info_linearlayout_error)
    LinearLayout layoutError;
    private WoodInfoContrac.WoodInfoPresenterInterface presenter;
    private String pid;
    private WoodInfoAdapter adapter;
    private WoodInfoOtherAdapter adapterOther;
    private EditText number;
    private MyRequestInfo myRequestInfo;
    private ProductSellerInfo productSellerInfo = null;
    private ProductInfo productInfo = null;
    private String[] pictures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wood_info);
        CommonUtils.addActivity(this);
        ButterKnife.bind(this);
        textTitle.setText("商品详情");
        if (null != getIntent()) {
            pid = getIntent().getStringExtra("pid");
            if (!getIntent().getBooleanExtra("isShowButton", true)) {//卖家查看商品详情
                buy.setVisibility(View.GONE);
                addToShoppingCart.setVisibility(View.GONE);
                line.setVisibility(View.GONE);
                imageTitleRight.setVisibility(View.GONE);
                state.setVisibility(View.GONE);
            }
        }

        new WoodInfoPresenter(this);
        if (null != getIntent()) {
            if (!getIntent().getBooleanExtra("isShowButton", true)) {
                presenter.getWoodInfoOther(pid);
            } else {
                presenter.getWoodInfo(pid);
            }
        }


    }

    @Override
    public void showWoodData(Object object) {
        if (null != getIntent()) {
            if (!getIntent().getBooleanExtra("isShowButton", true)) {
                productSellerInfo = (ProductSellerInfo) object;
                textTitle.setText(productSellerInfo.pname);
                name.setText("名        称：" + productSellerInfo.pname);
                price.setText("价        格：" + productSellerInfo.price + "元/方");
                stock.setText("库  存  量：" + productSellerInfo.stock + "方");
                address.setText("出  库  地：" + productSellerInfo.delivery);
                if (1 == productSellerInfo.type) {
                    kind.setText("产品类型： 现货");
                } else {
                    kind.setText("产品类型： 期货");
                }
                String[] picture = new String[productSellerInfo.pimg.size()];
                for (int i = 0; i < productSellerInfo.pimg.size(); i++) {
                    picture[i] = Const.PICTURE + productSellerInfo.pimg.get(i);
                }
                pictures = picture;
                adapterOther = new WoodInfoOtherAdapter(this, productSellerInfo.attribute);
                listview.setAdapter(adapterOther);
            } else {
                productInfo = (ProductInfo) object;
                textTitle.setText(productInfo.pname);
                name.setText("名        称：" + productInfo.pname);
                price.setText("价        格：" + productInfo.price + "元/方");
                stock.setText("库  存  量：" + productInfo.stock + "方");
                address.setText("出  库  地：" + productInfo.delivery);
                if (1 == productInfo.type) {
                    kind.setText("产品类型： 现货");
                } else {
                    kind.setText("产品类型： 期货");
                }
                state.setText("卖        家：" + productInfo.seller);
                String[] picture = new String[productInfo.pimg.size()];
                for (int i = 0; i < productInfo.pimg.size(); i++) {
                    picture[i] = Const.PICTURE + productInfo.pimg.get(i);
                }
                pictures = picture;
                adapter = new WoodInfoAdapter(this, productInfo.attribute);
                listview.setAdapter(adapter);
            }
        }

        gallery.start(this, pictures, 3000,
                DotContainer, R.drawable.dot_onn,
                R.drawable.dot_offf, null, null);

        Scrollview.smoothScrollTo(0, 0);
    }

    @Override
    public void showNumberDialog(final int id, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.inputnumber, null);
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
                        new AlertDialog.Builder(WoodInfoActivity.this).setMessage("最多保留小数点后3位").setNeutralButton("确定", new DialogInterface.OnClickListener() {
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
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if ("".equals(number.getText().toString().trim()) || "-".equals(number.getText().toString().trim()) || ".".equals(number.getText().toString().trim())) {
                    showMessage("请输入正确购买数量");
                    return;
                }
                if (Double.parseDouble(number.getText().toString().trim()) <= 0) {
                    new AlertDialog.Builder(WoodInfoActivity.this).setMessage("输入错误").setNeutralButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).show();
                    number.setText("");
                } else {
                    if (id == R.id.activity_wood_info_btn_lijigoumai) {//直接购买
                        Intent intent = new Intent(WoodInfoActivity.this, ConfirmOrderActivity.class);
                        ShopcarList shopcarList;
                        //构造ShopcarList对象，适配结算页面
                        {
                            shopcarList = new ShopcarList();

                            ShopcarList_listItem_seller shopcarList_listItem_seller = new ShopcarList_listItem_seller();
                            if (null != productSellerInfo) {
                                shopcarList_listItem_seller.sid = productSellerInfo.sid;
                                shopcarList_listItem_seller.sname = productSellerInfo.seller;
                            } else if (null != productInfo) {
                                shopcarList_listItem_seller.sid = productInfo.sid;
                                shopcarList_listItem_seller.sname = productInfo.seller;
                            }


                            List<ShopcarList_listItem_attributeItem> shopcarList_listItem_attributeItem_list = new ArrayList<ShopcarList_listItem_attributeItem>();

                            ShopcarList_listItem_attributeItem shopcarList_listItem_attributeItem = null;
                            if (null != productSellerInfo) {
                                for (ProductSellerInfo_attributeItem productSellerInfo_attributeItem : productSellerInfo.attribute) {
                                    shopcarList_listItem_attributeItem = new ShopcarList_listItem_attributeItem();
                                    shopcarList_listItem_attributeItem.name = productSellerInfo_attributeItem.name;
                                    shopcarList_listItem_attributeItem.value = productSellerInfo_attributeItem.value;
                                    shopcarList_listItem_attributeItem_list.add(shopcarList_listItem_attributeItem);
                                }
                            } else if (null != productInfo) {
                                for (WoodSpecifications woodSpecifications : productInfo.attribute) {
                                    shopcarList_listItem_attributeItem = new ShopcarList_listItem_attributeItem();
                                    shopcarList_listItem_attributeItem.name = woodSpecifications.name;
                                    shopcarList_listItem_attributeItem.value = woodSpecifications.value;
                                    shopcarList_listItem_attributeItem_list.add(shopcarList_listItem_attributeItem);
                                }
                            }


                            List<ShopcarList_listItem> shopcarList_listItem_list = new ArrayList<ShopcarList_listItem>();
                            ShopcarList_listItem shopcarList_listItem = new ShopcarList_listItem();
                            shopcarList_listItem.attribute = shopcarList_listItem_attributeItem_list;
                            shopcarList_listItem.seller = shopcarList_listItem_seller;
                            shopcarList_listItem.stock = Double.parseDouble(number.getText().toString().trim());
                            if (null != productSellerInfo) {
                                shopcarList_listItem.pid = productSellerInfo.pid;
                                shopcarList_listItem.pname = productSellerInfo.pname;
                                shopcarList_listItem.price = productSellerInfo.price;
                                shopcarList_listItem.xiaoJi = (productSellerInfo.price) * (Double.parseDouble(number.getText().toString().trim()));
                            } else if (null != productInfo) {
                                shopcarList_listItem.pid = productInfo.pid;
                                shopcarList_listItem.pname = productInfo.pname;
                                shopcarList_listItem.price = productInfo.price;
                                shopcarList_listItem.xiaoJi = (productInfo.price) * (Double.parseDouble(number.getText().toString().trim()));
                            }
                            shopcarList_listItem_list.add(shopcarList_listItem);

                            shopcarList.list = shopcarList_listItem_list;
                        }

                        intent.putExtra("shopcarList", shopcarList);
                        startActivity(intent);
                    } else if (id == R.id.activity_wood_info_btn_jiarugouwuche) {//加入购物车
                        Request_shopcarAdd request_shopcarAdd = new Request_shopcarAdd();
                        request_shopcarAdd.number = Double.parseDouble(number.getText().toString().trim());

                        if (null != productSellerInfo) {
                            request_shopcarAdd.pid = productSellerInfo.pid;
                        } else if (null != productInfo) {
                            request_shopcarAdd.pid = productInfo.pid;
                        }
                        MyRequestInfo myRequestInfo = new MyRequestInfo();
                        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(WoodInfoActivity.this);
                        Request_getAttribute request_getAttribute = new Request_getAttribute();
                        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");
                        myRequestInfo.req_meta = request_getAttribute;
                        myRequestInfo.req = request_shopcarAdd;
                        presenter.shopcarAdd(myRequestInfo);

                    }
                    dialogInterface.dismiss();
                }
            }
        });
        builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }

        });
        builder.show();
    }

    @Override
    public void setPresenter(WoodInfoContrac.WoodInfoPresenterInterface presenter) {
        if (null != presenter) {
            this.presenter = presenter;
        }
    }

    @Override
    public void showProgress() {
        ProgressUtils.show(this, "请稍后……");
    }

    @Override
    public void closeProgress() {
        ProgressUtils.hide();
    }

    @Override
    public void showMessage(String string) {
        CommonUtils.showToast(this, string);
    }

    @OnClick({R.id.activity_wood_info_btn_lijigoumai, R.id.activity_wood_info_btn_jiarugouwuche, R.id.imge_title_left,R.id.image_title_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_wood_info_btn_lijigoumai://立即购买
                showNumberDialog(R.id.activity_wood_info_btn_lijigoumai, 0);
                break;
            case R.id.activity_wood_info_btn_jiarugouwuche://加入购物车
                showNumberDialog(R.id.activity_wood_info_btn_jiarugouwuche, 0);
                break;
            case R.id.imge_title_left:
                finish();
                break;
            case R.id.image_title_right:
                startActivity(new Intent(this, ShoopingCartActivity.class));
                break;
        }
    }
}
