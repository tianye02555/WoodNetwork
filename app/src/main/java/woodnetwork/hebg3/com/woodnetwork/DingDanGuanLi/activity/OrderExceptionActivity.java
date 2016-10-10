package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter.OrderException_yiChangXinXi_listAdapter;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.ExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.ExceptionList_exceptionItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerInfo;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract.OrderExceptionContract;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.presenter.OrderExceptionPresenter;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_exceptionList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.view.MyListView;

public class OrderExceptionActivity extends AppCompatActivity implements OrderExceptionContract.OrderExceptionViewInterface {

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.image_title_right)
    Button imageTitleRight;
    @Bind(R.id.activity_order_exception_text_dinDanBianHao)
    TextView dinDanBianHao;
    @Bind(R.id.activity_order_exception_text_date)
    TextView date;
    @Bind(R.id.activity_order_exception_text_yichangchuli)
    TextView text_yiChangChuLi;
    @Bind(R.id.activity_order_exception_text_yichangxinxi)
    TextView text_yiChangXinXi;
    @Bind(R.id.activity_order_exception_text_maiJiaXinXi)
    TextView maiJiaXinXi;
    @Bind(R.id.activity_order_exception_text_price)
    TextView price;
    @Bind(R.id.activity_order_exception_text_jian)
    TextView jian;
    @Bind(R.id.activity_order_exception_listview_yichangxinxi)
    MyListView listview_yichangxinxi;
    @Bind(R.id.activity_order_exception_listview_yichangchuli)
    MyListView listview_yichangchuli;
    @Bind(R.id.scrollView)
    ScrollView scrollView;
    private String seller = "";
    private String number = "";
    private String flag = "";
    private String oid = "";
    private String title_price = "";
    private String creatTime = "";
    private String id;
    private ExceptionList exceptionList;
    private OrderExceptionContract.OrderExceptionPresenterInterface presenter;
    private MyRequestInfo myRequestInfo;
    private ArrayList<ExceptionList_exceptionItem> exceptionList_wenTi;
    private ArrayList<ExceptionList_exceptionItem> exceptionList_jieGuo;
    private OrderException_yiChangXinXi_listAdapter listAdapter_yiChangXinXi;
    private OrderException_yiChangXinXi_listAdapter listAdapter_yiChangChuLi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_exception);
        ButterKnife.bind(this);

        if (null != getIntent()) {
            seller = getIntent().getStringExtra("seller");
            number = getIntent().getStringExtra("number");
            flag = getIntent().getStringExtra("flag");
            oid = getIntent().getStringExtra("oid");
            title_price = getIntent().getStringExtra("total_price");
            creatTime = getIntent().getStringExtra("creat_time");
            id = getIntent().getStringExtra("id");
        }

        new OrderExceptionPresenter(this);
        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(this);
        Request_getAttribute request_getAttribute = new Request_getAttribute();
        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");

        Request_exceptionList request_exceptionList = new Request_exceptionList();
        request_exceptionList.oid = oid;

        myRequestInfo = new MyRequestInfo();
        myRequestInfo.req = request_exceptionList;
        myRequestInfo.req_meta = request_getAttribute;
        presenter.getOrderExceptionList(myRequestInfo);
    }

    @OnClick({R.id.imge_title_left, R.id.image_title_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imge_title_left:
                finish();
                break;
            case R.id.image_title_right:
                Intent intent = new Intent(OrderExceptionActivity.this, ExceptionAddActivity.class);
                intent.putExtra("seller", seller);
                intent.putExtra("number", number);
                intent.putExtra("oid",oid);
                intent.putExtra("id", id);
                intent.putExtra("creat_time", creatTime);
                intent.putExtra("total_price", title_price);
                if ("1".equals(flag)) {
                    intent.putExtra("flag", "1");
                }
                startActivityForResult(intent,0);
                break;
        }
    }

    @Override
    public void showOrderExceptionInfo(ExceptionList exceptionList) {

        date.setText("下单日期：" + creatTime);
        if ("1".equals(flag)) {
            maiJiaXinXi.setText("买家信息：" + seller);
        } else {
            maiJiaXinXi.setText("卖家信息：" + seller);
        }

        price.setText(String.valueOf(title_price));
        jian.setText(number);
        dinDanBianHao.setText("订单编号：" + id);

        if (null == exceptionList) {
            text_yiChangXinXi.setVisibility(View.VISIBLE);
            text_yiChangChuLi.setVisibility(View.VISIBLE);
            return;
        }
        exceptionList.seller = seller;
        exceptionList.shop_number = number;
        this.exceptionList = exceptionList;

        exceptionList_wenTi=new ArrayList<ExceptionList_exceptionItem>();
        exceptionList_jieGuo=new ArrayList<ExceptionList_exceptionItem>();
        for(ExceptionList_exceptionItem item :exceptionList.exception){
            if(0==item.type){//0表示提交异常，1表示问题答复
                exceptionList_wenTi.add(item);
            }else if(1==item.type){
                exceptionList_jieGuo.add(item);
            }
        }
        if(0==exceptionList_wenTi.size()){
            text_yiChangXinXi.setVisibility(View.VISIBLE);
        }
        if(0==exceptionList_jieGuo.size()){
            text_yiChangChuLi.setVisibility(View.VISIBLE);
        }

        listAdapter_yiChangXinXi = new OrderException_yiChangXinXi_listAdapter(this, exceptionList_wenTi);
        listview_yichangxinxi.setAdapter(listAdapter_yiChangXinXi);
        listAdapter_yiChangChuLi = new OrderException_yiChangXinXi_listAdapter(this, exceptionList_jieGuo);
        listview_yichangchuli.setAdapter(listAdapter_yiChangChuLi);
        scrollView.smoothScrollTo(0,20);//滚动到顶部
    }

    @Override
    public void setPresenter(OrderExceptionContract.OrderExceptionPresenterInterface presenter) {
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

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK&&null!=myRequestInfo){
            presenter.getOrderExceptionList(myRequestInfo);
        }
    }
}
