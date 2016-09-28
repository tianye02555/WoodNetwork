package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter.OrderDetailsAdapter;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter.SellerOrderDetailsAdapter;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerInfo;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderSellerInfo;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract.OrderBuyerInfoContract;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.presenter.OrderBuyerInfoPresenter;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_orderBuyInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.view.MyListView;

public class OrderDetailsActivity extends AppCompatActivity implements OrderBuyerInfoContract.OrderBuyerInfoViewInterface {


    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_order_details_text_maijiaxinxi)
    TextView text_maiJia;
    @Bind(R.id.activity_order_details_shouhuodizhi)
    TextView text_shouHuoDiZhi;
    @Bind(R.id.activity_order_details_fahuodizhi)
    TextView text_faHuoDiZhi;
    @Bind(R.id.adapter_order_details_text_dingdanhao)
    TextView text_dingDanHao;
    @Bind(R.id.adapter_order_details_text_date)
    TextView text_date;
    @Bind(R.id.adapter_order_details_listview)
    MyListView listView;
    @Bind(R.id.adapter_order_details_text_price)
    TextView text_price;
    @Bind(R.id.adapter_order_details_text_jian)
    TextView text_jian;
    @Bind(R.id.adapter_order_details_text_dingdanzhuangtai)
    TextView text_dingDanZhuangTai;
    @Bind(R.id.adapter_order_details_btn_querenshouhuo)
    Button btn_queRenShouHuo;
    @Bind(R.id.adapter_order_details_btn_guanbidingdan)
    Button btn_yiChangShenBao;
private OrderBuyerInfoContract.OrderBuyerInfoPresenterInterface presenter;
    private MyRequestInfo myRequestInfo;
    private OrderDetailsAdapter adapter;
    private OrderBuyerInfo orderBuyerInfo;
    private OrderSellerInfo orderSellerInfo;
    private SellerOrderDetailsAdapter adapter_seller;
    private String oid="";
    private String flag="";//判断s
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        ButterKnife.bind(this);
        textTitle.setText("订单详情");
        imageTitleRight.setVisibility(View.GONE);

        if(null!=getIntent()){
            oid=getIntent().getStringExtra("oid");
            flag=getIntent().getStringExtra("flag");
            if("1".equals(flag)){
                btn_queRenShouHuo.setText("发货");
            }
        }

        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(this);
        Request_getAttribute request_getAttribute = new Request_getAttribute();
        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");

        Request_orderBuyInfo request_orderBuyInfo=new Request_orderBuyInfo();
        request_orderBuyInfo.oid=oid;

        myRequestInfo = new MyRequestInfo();
        myRequestInfo.req=request_orderBuyInfo;
        myRequestInfo.req_meta = request_getAttribute;

        new OrderBuyerInfoPresenter(this);

        presenter.getOrderData(myRequestInfo);
    }

    @Override
    public void showOrderInfo(OrderBuyerInfo orderBuyerInfo) {
        this.orderBuyerInfo=orderBuyerInfo;

            text_maiJia.setText("卖家信息:"+orderBuyerInfo.seller);


        text_shouHuoDiZhi.setText(orderBuyerInfo.receive_area);
        text_faHuoDiZhi.setText(orderBuyerInfo.delivery_area);
                text_dingDanHao.setText(orderBuyerInfo.number);
        text_date.setText(orderBuyerInfo.creat_time);
        text_price.setText(String.valueOf(orderBuyerInfo.total_price));
                text_jian.setText(String.valueOf(orderBuyerInfo.products.size()));
        switch (orderBuyerInfo.status){//0：待付款；1：已付款；2：已发货；3：已到货；4：订单取消
            case 0:
                text_dingDanZhuangTai.setText("待付款");
                btn_queRenShouHuo.setVisibility(View.GONE);
                btn_yiChangShenBao.setVisibility(View.GONE);
                break;
            case 1:
                text_dingDanZhuangTai.setText("已付款");
                break;
            case 2:
                text_dingDanZhuangTai.setText("已发货");
                break;
            case 3:
                text_dingDanZhuangTai.setText("已到货");
                break;
            case 4:
                text_dingDanZhuangTai.setText("订单取消");
                btn_queRenShouHuo.setVisibility(View.GONE);
                btn_yiChangShenBao.setVisibility(View.GONE);
                break;
        }
        adapter=new OrderDetailsAdapter(this,orderBuyerInfo.products);
        listView.setAdapter(adapter);


    }
    @Override
    public void showSellerOrderInfo(OrderSellerInfo orderSellerInfo) {
        this.orderSellerInfo=orderSellerInfo;

            text_maiJia.setText("买家信息:"+orderSellerInfo.seller);


        text_shouHuoDiZhi.setText(orderSellerInfo.receive_area);
        text_faHuoDiZhi.setText(orderSellerInfo.delivery_area);
        text_dingDanHao.setText(orderSellerInfo.number);
        text_date.setText(orderSellerInfo.creat_time);
        text_price.setText(String.valueOf(orderSellerInfo.total_price));
        text_jian.setText(String.valueOf(orderSellerInfo.products.size()));
        switch (orderSellerInfo.status){//0：待付款；1：已付款；2：已发货；3：已到货；4：订单取消
            case 0:
                text_dingDanZhuangTai.setText("待付款");
                btn_queRenShouHuo.setVisibility(View.GONE);
                        btn_yiChangShenBao.setVisibility(View.GONE);
                break;
            case 1:
                text_dingDanZhuangTai.setText("已付款");
                break;
            case 2:
                text_dingDanZhuangTai.setText("已发货");
                break;
            case 3:
                text_dingDanZhuangTai.setText("已到货");
                break;
            case 4:
                text_dingDanZhuangTai.setText("订单取消");
                btn_queRenShouHuo.setVisibility(View.GONE);
                btn_yiChangShenBao.setVisibility(View.GONE);
                break;
        }
        adapter_seller=new SellerOrderDetailsAdapter(this,orderSellerInfo.products);
        listView.setAdapter(adapter);


    }

    @Override
    public void setPresenter(OrderBuyerInfoContract.OrderBuyerInfoPresenterInterface presenter) {
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

    @OnClick(R.id.imge_title_left)
    public void onClick() {
        finish();
    }

    @OnClick({R.id.imge_title_left, R.id.adapter_order_details_btn_querenshouhuo, R.id.adapter_order_details_btn_guanbidingdan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imge_title_left:
                finish();
                break;
            case R.id.adapter_order_details_btn_querenshouhuo://确认收货
                Intent intent_receive =new Intent(OrderDetailsActivity.this,OrderReceiveActivity.class);
                if("1".equals(flag)){
                    intent_receive.putExtra("id",this.orderSellerInfo.number);
                    intent_receive.putExtra("oid",oid);
                    intent_receive.putExtra("creat_time",this.orderSellerInfo.creat_time);
                    intent_receive.putExtra("seller",this.orderSellerInfo.seller);
                    intent_receive.putExtra("total_price",this.orderSellerInfo.total_price);
                    intent_receive.putExtra("number",String.valueOf(this.orderSellerInfo.products.size()));
                    intent_receive.putExtra("flag","1");
                }else{
                    intent_receive.putExtra("oid",oid);
                    intent_receive.putExtra("id",this.orderBuyerInfo.number);
                    intent_receive.putExtra("creat_time",this.orderBuyerInfo.creat_time);
                    intent_receive.putExtra("seller",this.orderBuyerInfo.seller);
                    intent_receive.putExtra("total_price",String.valueOf(this.orderBuyerInfo.total_price));
                    intent_receive.putExtra("number",String.valueOf(this.orderBuyerInfo.products.size()));
                }
                startActivityForResult(intent_receive,0);
                break;
            case R.id.adapter_order_details_btn_guanbidingdan://异常申报

                Intent intent_exception =new Intent(OrderDetailsActivity.this,OrderExceptionActivity.class);
                if("1".equals(flag)){
                    intent_exception.putExtra("seller", orderSellerInfo.seller);
                    intent_exception.putExtra("oid", oid);
                    intent_exception.putExtra("number", String.valueOf(orderSellerInfo.products.size()));
                }else {
                    intent_exception.putExtra("seller", orderBuyerInfo.seller);
                    intent_exception.putExtra("oid", oid);
                    intent_exception.putExtra("number", String.valueOf(orderBuyerInfo.products.size()));
                }
                startActivityForResult(intent_exception,0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            finish();
        }

    }
}
