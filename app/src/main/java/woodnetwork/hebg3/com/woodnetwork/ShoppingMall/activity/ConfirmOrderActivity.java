package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_orderAdd;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_orderAdd_productsItem;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter.ConfirmOrderAdapter;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.OrderAdd;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopcarList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopcarList_listItem;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract.ConfirmOrderContrac;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.presenter.ConfirmOrderPresenter;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.view.MyListView;

public class ConfirmOrderActivity extends AppCompatActivity implements ConfirmOrderContrac.ConfirmOrderViewInterface {

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_confirm_order_text_sellername)
    TextView sellerName;
    @Bind(R.id.activity_confirm_order_text_buyername)
    TextView buyerName;
    @Bind(R.id.activity_confirm_order_text_ordertype)
    TextView orderType;
    @Bind(R.id.activity_confirm_order_edit_harvestplace)
    EditText harvestPlace;
    @Bind(R.id.simpleDraweeView)
    SimpleDraweeView simpleDraweeView;
    @Bind(R.id.activity_confirm_order_edit_address)
    EditText address;
    @Bind(R.id.activity_confirm_order_btn_querenxiadan)
    Button queRenXiaDan;
    @Bind(R.id.activity_confirm_order_btn_quxiao)
    Button quXiao;
    @Bind(R.id.activity_confirm_order_text_yingfujinge)
    TextView yingFuJinge;
    @Bind(R.id.activity_confirm_order_spinner_extracttype)
    Spinner extractType;
    @Bind(R.id.activity_confirm_order_listview)
    MyListView listView;
    @Bind(R.id.ll)
    LinearLayout ll;
    @Bind(R.id.ll2)
    LinearLayout ll2;
    private int extractTypeNumber;
    private ConfirmOrderContrac.ConfirmOrderPresenterInterface presenter;
    private SharePreferencesUtils sharePreferencesUtils;
    private ConfirmOrderAdapter adapter;
    private ShopcarList shopcarList;
    private ArrayList<String> shopCarPosition = new ArrayList<String>();
    private String addressID;//地址id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        CommonUtils.addActivity(this);
        ButterKnife.bind(this);
        sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(this);

        textTitle.setText("确认订单");
        imageTitleRight.setVisibility(View.GONE);

        new ConfirmOrderPresenter(this);
        shopcarList=(ShopcarList) getIntent().getSerializableExtra("shopcarList");
        showOrderData(shopcarList);
        shopCarPosition=(ArrayList<String>) getIntent().getStringArrayListExtra("shopcarPositonList");
        final String[] extractArray = getResources().getStringArray(R.array.tiqufangshi);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, extractArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        extractType.setAdapter(adapter);
        extractType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if ("自提".equals(extractArray[i])) {//0表示自提，1表示送货
                    extractTypeNumber = 0;
                    address.setEnabled(false);
                    harvestPlace.setEnabled(false);
                    ll.setVisibility(View.GONE);
                    ll2.setVisibility(View.GONE);
                } else if ("送货".equals(extractArray[i])) {
                    address.setEnabled(true);
                    ll.setVisibility(View.VISIBLE);
                    ll2.setVisibility(View.VISIBLE);
                    extractTypeNumber = 1;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @OnClick({R.id.activity_confirm_order_btn_querenxiadan, R.id.activity_confirm_order_btn_quxiao, R.id.simpleDraweeView, R.id.imge_title_left})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_confirm_order_btn_querenxiadan://确认下单
                if(extractTypeNumber == 1){
                    if(TextUtils.isEmpty(harvestPlace.getText().toString().trim())||TextUtils.isEmpty(address.getText().toString().trim())){
                        CommonUtils.showToast(ConfirmOrderActivity.this,"请填写收货地点和详细地址");
                        return;
                    }
                }

                Request_orderAdd request_orderAdd = new Request_orderAdd();
                request_orderAdd.seller_id =shopcarList.list.get(0).seller.sid ;
                if("".equals((String) sharePreferencesUtils.getData("userid",""))){
                    showMessage("身份信息失效，请重新登录");
                   return;
                }
                request_orderAdd.buyer_id = (String) sharePreferencesUtils.getData("userid","");
                ArrayList<Request_orderAdd_productsItem> list = new ArrayList<Request_orderAdd_productsItem>();
                for(int i=0;i<shopcarList.list.size();i++){
                    Request_orderAdd_productsItem request_orderAdd_productsItem = new Request_orderAdd_productsItem();
                    request_orderAdd_productsItem.number = shopcarList.list.get(i).stock;
                    request_orderAdd_productsItem.pid = shopcarList.list.get(i).pid;
                    request_orderAdd_productsItem.price = shopcarList.list.get(i).price;
                    list.add(request_orderAdd_productsItem);
                    if(extractTypeNumber == 0){//自提
                        request_orderAdd.receive_area =shopcarList.list.get(i).delivery_area ;
                        request_orderAdd.receive_id =shopcarList.list.get(i).delivery_id ;
                    }
                }
                request_orderAdd.products = list;
                if(extractTypeNumber == 0){//自提
                    request_orderAdd.receive_area =shopcarList.list.get(0).delivery_area ;
                    request_orderAdd.receive_id =shopcarList.list.get(0).delivery_id ;
                }else if(extractTypeNumber == 1){//收货
                    request_orderAdd.receive_area = harvestPlace.getText().toString().trim()+address.getText().toString().trim();
                    request_orderAdd.receive_id = String.valueOf(addressID);
                }

                request_orderAdd.receive_type = extractTypeNumber;
                request_orderAdd.shopcar_ids = shopCarPosition;
                request_orderAdd.type =1 ;
                //添加属性
                presenter.saveOrder(request_orderAdd);
                break;
            case R.id.activity_confirm_order_btn_quxiao:
                finish();
                break;
            case R.id.simpleDraweeView:
                startActivityForResult(new Intent(this, ChooseAddressActivity.class), 0);
                break;
            case R.id.imge_title_left:
                finish();
                break;
        }
    }

    @Override
    public void showOrderData(ShopcarList shopcarList) {
        sellerName.setText("卖家名称：" + shopcarList.list.get(0).seller.sname);
        buyerName.setText("买家名称：" + (String) sharePreferencesUtils.getData("user_name", ""));
        orderType.setText("订单类型：销售订单");
        //计算总价格
        double totlePrice = 0;
        for (ShopcarList_listItem shopcarList_listItem : shopcarList.list) {
            totlePrice += shopcarList_listItem.xiaoJi;
        }
        DecimalFormat df = new DecimalFormat("###############0.00");//   16位整数位，两小数位
        String temp = df.format(totlePrice);
        yingFuJinge.setText(temp);
        adapter = new ConfirmOrderAdapter(this, shopcarList.list);
        listView.setAdapter(adapter);

    }

    @Override
    public String getHarvestPlace() {
        return harvestPlace.getText().toString().trim();
    }

    @Override
    public String getAddress() {
        return address.getText().toString().trim();
    }

    @Override
    public void showMoreAttribute() {

    }

    @Override
    public void jumpActivitywithAttribute(OrderAdd orderAdd) {

        Intent intent = new Intent(this, OrderListActivity.class);
        intent.putExtra("orderadd", orderAdd);
        intent.putExtra("shopcarList", shopcarList);
        startActivity(intent);
        finish();
    }

    @Override
    public void setPresenter(ConfirmOrderContrac.ConfirmOrderPresenterInterface presenter) {
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

    @OnClick(R.id.simpleDraweeView)
    public void onClick() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            harvestPlace.setText(data.getStringExtra("address"));
            addressID=data.getStringExtra("addressID");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
