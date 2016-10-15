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

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_busnessList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_shopcarList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_shopcar_delete;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter.ShoopingCartAdapter;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
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
    XRecyclerView shoppingCartRecyclerview;
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
    private ArrayList<ShopcarList_listItem> ShopcarList_listItemList;
    private int page_no = 1;
    private Request_shopcarList request_shopcarList;
    private MyRequestInfo myRequestInfo;
    /**
     * 存放选中的订单在购物车中的位置
     */
    private ArrayList<String> shopCarPosition = new ArrayList<String>();

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
                        if (!shopcarList_listItem.checkbox && 0 == shopcarList_listItem.flag) {
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

        request_shopcarList = new Request_shopcarList();
        request_shopcarList.page_no = 1;
        request_shopcarList.page_size = 10;
        myRequestInfo = new MyRequestInfo();
        myRequestInfo.req = request_shopcarList;
        myRequestInfo.req_meta = request_getAttribute;
        presenter.getShoopingCartData(myRequestInfo, 0);
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
                for (ShopcarList_listItem item : adapter.getShoopingCarList()) {
                    if (item.checkbox) {
                        sidList.add(item.sid);//选中的商品的sid集合，同时使用这个集合判断是否有订单选中
                    }
                }
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
    public void showShoopingCartInfo(final ShopcarList shopcarList) {
        this.shopcarList = shopcarList;
        adapter = new ShoopingCartAdapter(this, this.shopcarList.list);
        shoppingCartRecyclerview.setLayoutManager(new LinearLayoutManager(this));
//        shoppingCartRecyclerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, 5));
        shoppingCartRecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page_no = 1;
                request_shopcarList.page_no = page_no;
                myRequestInfo.req = request_shopcarList;
                presenter.getShoopingCartData(myRequestInfo, 1);
            }

            @Override
            public void onLoadMore() {
                page_no++;
                if (page_no > shopcarList.total_page) {//判断是否为最后一页
                    shoppingCartRecyclerview.setIsnomore(true);//底部显示没有更多数据
                }
                request_shopcarList.page_no = page_no;
                myRequestInfo.req = request_shopcarList;
                presenter.getShoopingCartData(myRequestInfo, 2);


            }
        });
        if (1 == shopcarList.total_page) {//如果总页数一共就一页，关闭加载更多功能
            shoppingCartRecyclerview.setLoadingMoreEnabled(false);
        }
        shoppingCartRecyclerview.setAdapter(adapter);

    }

    @Override
    public void loadMore(List<ShopcarList_listItem> list) {

        shoppingCartRecyclerview.loadMoreComplete();//停止加载更多动画
        shopcarList.list = adapter.getList();
        shopcarList.list.addAll(list);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void refresh(ShopcarList shopcarList) {
        shoppingCartRecyclerview.refreshComplete();//停止更新动画
        if (1 < shopcarList.total_page) {//如果刷新后数据多余一页，加载更多功能启用
            shoppingCartRecyclerview.setLoadingMoreEnabled(true);
        }
        this.shopcarList.list = shopcarList.list;
        adapter.setList(shopcarList.list);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void deleteGoods() {
        shopcarList.list = adapter.getList();
//        for (ShopcarList_listItem item : shopcarList.list) {
//            if (item.checkbox) {
//                shopcarList.list.remove(item);
//            }
//        }
        for (int i = 0; i < shopcarList.list.size(); i++) {
            if (shopcarList.list.get(i).checkbox) {
                shopcarList.list.remove(i);
                i -= 1;//由于直接删除集合数据，所以需要更改角标，删一个 减一
            }
        }
        adapter.setList(shopcarList.list);
        adapter.notifyDataSetChanged();
        showTitlePrice();
    }

    @Override
    public void submitOrder() {
        ShopcarList mShopcarList = new ShopcarList();
        ShopcarList_listItemList = new ArrayList<ShopcarList_listItem>();
        Intent intent = new Intent(this, ConfirmOrderActivity.class);
//        for (ShopcarList_listItem item : adapter.getList()) {
//            if (item.checkbox) {
//                ShopcarList_listItemList.add(item);
//                shopCarPosition.add(String.valueOf())
//            }
//        }
        //提取选中的订单
        for (int i = 0; i < adapter.getList().size(); i++) {
            if (adapter.getList().get(i).checkbox) {
                ShopcarList_listItemList.add(adapter.getList().get(i));
                shopCarPosition.add(String.valueOf(i + 1));
            }
        }
        //判断是否选择订单
        if (0 == ShopcarList_listItemList.size()) {
            showMessage("请选择需要的商品");
            return;
        }
        //判断发货人和发货地是否一致
        String company = ShopcarList_listItemList.get(0).seller.sname;
        for (ShopcarList_listItem shopcarList_listItem : ShopcarList_listItemList) {
            if (!company.equals(shopcarList_listItem.seller.sname)) {
                showMessage("同一订单的发货人必须相同");
                return;
            }
        }
        String delivery = ShopcarList_listItemList.get(0).delivery;
        for (ShopcarList_listItem shopcarList_listItem : ShopcarList_listItemList) {
            if (!delivery.equals(shopcarList_listItem.delivery)) {
                showMessage("同一订单的发货地必须相同");
                return;
            }
        }
        mShopcarList.list = ShopcarList_listItemList;
        intent.putExtra("shopcarList", mShopcarList);
        intent.putStringArrayListExtra("shopcarPositonList", shopCarPosition);
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
        for (ShopcarList_listItem shopcarList_listItem : adapter.getList()) {
            if (shopcarList_listItem.checkbox) {
                titlePrice += shopcarList_listItem.xiaoJi;
            }
        }
        DecimalFormat df = new DecimalFormat("###############0.00");//   16位整数位，两小数位
        String temp = df.format(titlePrice);
        jingE.setText(String.valueOf(temp));

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
