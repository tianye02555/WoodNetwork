package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter.ConfirmOrderAdapter;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.OrderAdd;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ShopcarList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract.OrderListContrac;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.presenter.OrderListPresenter;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.view.MyListView;

/**
 * 订单列表页
 */
public class OrderListActivity extends AppCompatActivity implements OrderListContrac.OrderListViewInterface {

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_order_list_text_sellername)
    TextView sellerName;
    @Bind(R.id.activity_order_list_text_buyername)
    TextView buyerName;
    @Bind(R.id.activity_order_list_text_ordertype)
    TextView orderType;
    @Bind(R.id.activity_order_list_listview)
    MyListView listView;
    @Bind(R.id.activity_order_list_btn_querenxiadan)
    Button chaXunYuE;
    @Bind(R.id.activity_order_list_text_yingfujinge)
    TextView yingFuFinge;
    @Bind(R.id.activity_order_list_text_yue)
    TextView yuE;
    @Bind(R.id.activity_order_list_text_harvestplace)
    TextView harvestPlace;
    @Bind(R.id.activity_order_list_text_extracttype)
    TextView extractType;
    @Bind(R.id.activity_order_list_text_orderstate)
    TextView orderState;
    @Bind(R.id.activity_order_list_text_ordernumber)
    TextView orderNumber;
    @Bind(R.id.activity_order_list_text_ordertime)
    TextView orderTime;
    private OrderListContrac.OrderListPresenterInterface presenter;
private ShopcarList shopcarList;
    private OrderAdd orderAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        CommonUtils.addActivity(this);
        ButterKnife.bind(this);
        new OrderListPresenter(this);
        if(null!=getIntent()){
            shopcarList=(ShopcarList) getIntent().getSerializableExtra("shopcarList");
            orderAdd=(OrderAdd) getIntent().getSerializableExtra("orderadd");
        }
        showOrderData(orderAdd);
    }

    @OnClick({R.id.imge_title_left, R.id.activity_order_list_btn_querenxiadan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imge_title_left:
                finish();
                break;
            case R.id.activity_order_list_btn_querenxiadan:
                SharePreferencesUtils sharePreferencesUtils=SharePreferencesUtils.getSharePreferencesUtils(this);
                Request_getAttribute request_getAttribute=new Request_getAttribute();
                request_getAttribute.user_id=(String)sharePreferencesUtils.getData("userid","");

                MyRequestInfo myRequestInfo=new MyRequestInfo();
                myRequestInfo.req=new Object();
                myRequestInfo.req_meta=request_getAttribute;
                presenter.getYuE(myRequestInfo);
                break;
        }
    }

    @Override
    public void showOrderData(OrderAdd orderAdd) {
        textTitle.setText("订单列表");
        imageTitleRight.setVisibility(View.GONE);
        sellerName.setText("卖家名称："+orderAdd.seller);
        buyerName.setText("买家名称："+orderAdd.buyer);
        if (0 == orderAdd.type) {// 0表示求购订单，1表示销售订单
            orderType.setText("订单类型：求购订单");
        } else if (1 == orderAdd.type) {
            orderType.setText("订单类型：销售订单");
        }
        harvestPlace.setText("收货地点："+orderAdd.receive_area);
        if (0 == orderAdd.receive_type) { // 0表示自提，1表示送货
            extractType.setText("提取方式：自提");
        } else if (1 == orderAdd.receive_type) {
            extractType.setText("提取方式：送货");
        }
        if(0 == orderAdd.status){
            orderState.setText("订单状态：待付款");
        }
        orderNumber.setText("订单编号："+orderAdd.number);
        orderTime.setText("下单时间："+orderAdd.creat_time);
        DecimalFormat df = new DecimalFormat("###############0.00");//   16位整数位，两小数位
        String temp = df.format(orderAdd.total);
        yingFuFinge.setText(temp);

        ConfirmOrderAdapter adapter=new ConfirmOrderAdapter(this,shopcarList.list);

        listView.setAdapter(adapter);
    }

    @Override
    public void showYuE(Double mDouble) {
        yuE.setText("余额："+String.valueOf(mDouble));
    }

    @Override
    public void setPresenter(OrderListContrac.OrderListPresenterInterface presenter) {
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
