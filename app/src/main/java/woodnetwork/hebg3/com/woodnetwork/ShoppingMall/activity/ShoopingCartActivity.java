package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_shopcarList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_shopcar_delete;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter.ShoopingCartAdapter;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopcarList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopcarList_listItem;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract.ShoopingCartContract;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment.DividerItemDecoration;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.presenter.ShoopingCartPresenter;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.DialogUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;

public class ShoopingCartActivity extends AppCompatActivity implements ShoopingCartContract.ShoopingCartViewInterface {

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_shopping_cart_recyclerview)
    RecyclerView shoppingCartRecyclerview;
    @Bind(R.id.activity_shopping_cart_checkbox)
    CheckBox checkbox_quanXuan;
    @Bind(R.id.activity_shopping_cart_text_jinge)
    TextView jingE;
    @Bind(R.id.activity_shopping_cart_btn_jiesuan)
    Button jieSuan;
    @Bind(R.id.activity_shopping_cart_btn_shanchu)
    Button shanChu;
    public ShoopingCartContract.ShoopingCartPresenterInterface presenter;
    /**
     * g购物车列表集合
     */
    private ShopcarList shopcarList;
    /**
     * 购物车适配器
     */
    private ShoopingCartAdapter adapter;
    /**
     * 选中的商品的集合
     */
    private ArrayList<String> sidList;

    /**
     *
     */
    private ArrayList<ShopcarList_listItem>  ShopcarList_listItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shooping_cart);
        CommonUtils.addActivity(this);
        ButterKnife.bind(this);
        textTitle.setText("购物车");
        imageTitleRight.setVisibility(View.GONE);
        checkbox_quanXuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    for (ShopcarList_listItem shopcarList_listItem : shopcarList.list) {
                        if (!shopcarList_listItem.checkbox) {
                            shopcarList_listItem.checkbox = true;
                        }
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    for (ShopcarList_listItem shopcarList_listItem : shopcarList.list) {
                        shopcarList_listItem.checkbox = false;
                        adapter.notifyDataSetChanged();
                    }
                }
                showTitlePrice();
            }
        });
        new ShoopingCartPresenter(this);
        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(this);
        Request_getAttribute request_getAttribute = new Request_getAttribute();
        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");

        Request_shopcarList request_shopcarList = new Request_shopcarList();
        request_shopcarList.page_no = 1;
        request_shopcarList.page_size = 10;
        MyRequestInfo myRequestInfo = new MyRequestInfo();
        myRequestInfo.req = request_shopcarList;
        myRequestInfo.req_meta = request_getAttribute;
        presenter.getShoopingCartData(myRequestInfo);
    }

    @OnClick({R.id.imge_title_left, R.id.activity_shopping_cart_btn_jiesuan, R.id.activity_shopping_cart_btn_shanchu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imge_title_left:
                finish();
                break;
            case R.id.activity_shopping_cart_btn_jiesuan://结算按钮

               submitOrder();
                break;
            case R.id.activity_shopping_cart_btn_shanchu://删除按钮
                sidList = new ArrayList<String>();
//                for (ShopcarList_listItem item : adapter.getShoopingCarList()) {
//                    if (item.checkbox) {
////                        sidList.add(item.sid);
//                    }
//                }
                if (0 == sidList.size()) {
                    showMessage("请选择需要删除的商品");
                    return;
                }
                DialogUtils.showDialog(ShoopingCartActivity.this, "提示信息", "确认删除该商品吗？", "", "", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(ShoopingCartActivity.this);
                        Request_getAttribute request_getAttribute = new Request_getAttribute();
                        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");
                        //选中的商品的sid集合

                        sidList.add("1234");
                        sidList.add("2234");
                        sidList.add("3234");

                        Request_shopcar_delete request_shopcar_delete = new Request_shopcar_delete();
                        request_shopcar_delete.sid = sidList;

                        MyRequestInfo myRequestInfo = new MyRequestInfo();
                        myRequestInfo.req = request_shopcar_delete;
                        myRequestInfo.req_meta = request_getAttribute;
                        presenter.deleteGoods(myRequestInfo);
                    }
                });

                break;
        }
    }

    @Override
    public void showShoopingCartInfo(ShopcarList shopcarList) {
        this.shopcarList = shopcarList;
        adapter = new ShoopingCartAdapter(this, this.shopcarList.list);
        shoppingCartRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        shoppingCartRecyclerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, 5));
        shoppingCartRecyclerview.setAdapter(adapter);

    }

    @Override
    public void deleteGoods() {
        shopcarList.list=adapter.getShoopingCarList();
        for (ShopcarList_listItem item : shopcarList.list) {
            if (item.checkbox) {
                shopcarList.list.remove(item);
            }
        }

        adapter.notifyDataSetChanged();
        showTitlePrice();
    }

    @Override
    public void submitOrder() {
        ShopcarList mShopcarList=new ShopcarList();
        ShopcarList_listItemList= new ArrayList<ShopcarList_listItem>();
        Intent intent = new Intent(this, ConfirmOrderActivity.class);
        for (ShopcarList_listItem item : adapter.getShoopingCarList()) {
            if (item.checkbox) {
                ShopcarList_listItemList.add(item);
            }
        }
        if(0==ShopcarList_listItemList.size()){
            showMessage("请选择需要的商品");
            return;
        }
        mShopcarList.list=ShopcarList_listItemList;
        intent.putExtra("shopcarList", mShopcarList);
        startActivity(intent);
    }

    @Override
    public void changeNumber() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showTitlePrice() {
        //计算总价格
        double titlePrice = 0;
        //循环如果选中就叠加计算总价格
        for (ShopcarList_listItem shopcarList_listItem : adapter.getShoopingCarList()) {
            if (shopcarList_listItem.checkbox) {
                titlePrice += shopcarList_listItem.xiaoJi;
            }
        }
        jingE.setText(String.valueOf(titlePrice));

    }

    @Override
    public void setPresenter(ShoopingCartContract.ShoopingCartPresenterInterface presenter) {
        if (null != presenter) {
            this.presenter = presenter;
        }
    }

    @Override
    public void showProgress() {
        ProgressUtils.show(this, getResources().getString(R.string.qingshaohou));
    }

    @Override
    public void closeProgress() {
        ProgressUtils.hide();
    }

    @Override
    public void showMessage(String string) {
        CommonUtils.showToast(this, string);
    }


}
