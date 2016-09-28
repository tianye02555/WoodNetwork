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
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter.OrderException_yiChangXinXi_listAdapter;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.ExceptionList;
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
    private String seller="";
    private String number="";
    private String flag="";
    private String oid="";
    private ExceptionList exceptionList;
    private OrderExceptionContract.OrderExceptionPresenterInterface presenter;
    private MyRequestInfo myRequestInfo;
    private OrderException_yiChangXinXi_listAdapter listAdapter_yiChangXinXi;
    private OrderException_yiChangXinXi_listAdapter listAdapter_yiChangChuLi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_exception);
        ButterKnife.bind(this);

        if(null!=getIntent()){
            seller=getIntent().getStringExtra("seller");
            number=getIntent().getStringExtra("number");
            flag=getIntent().getStringExtra("flag");
            oid=getIntent().getStringExtra("oid");
        }

//        orderBuyerInfo = (OrderBuyerInfo) getIntent().getSerializableExtra("OrderBuyerInfo");
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
                intent.putExtra("ExceptionList",this.exceptionList);
                if("1".equals(flag)){
                    intent.putExtra("flag","1");
                }
                startActivity(intent);
                break;
        }
    }

    @Override
    public void showOrderExceptionInfo(ExceptionList exceptionList) {
        exceptionList.seller=seller;
        exceptionList.shop_number=number;
        this.exceptionList=exceptionList;
        date.setText("下单日期："+exceptionList.creat_time);
        if("1".equals(flag)){
            maiJiaXinXi.setText("买家信息："+seller);
        }else{
            maiJiaXinXi.setText("卖家信息："+seller);
        }

        price.setText(String.valueOf(exceptionList.total_price));
        jian.setText(number);
        dinDanBianHao.setText("订单编号："+exceptionList.number);


        exceptionList.exception.get(0).imgs.add("http://img5.imgtn.bdimg.com/it/u=3279813050,4113215971&fm=206&gp=0.jpg");
        exceptionList.exception.get(0).imgs.add("http://img5.imgtn.bdimg.com/it/u=3279813050,4113215971&fm=206&gp=0.jpg");
        exceptionList.exception.get(0).imgs.add("http://img5.imgtn.bdimg.com/it/u=3279813050,4113215971&fm=206&gp=0.jpg");

        listAdapter_yiChangXinXi = new OrderException_yiChangXinXi_listAdapter(this, exceptionList.exception);
        listview_yichangxinxi.setAdapter(listAdapter_yiChangXinXi);
        listAdapter_yiChangChuLi = new OrderException_yiChangXinXi_listAdapter(this, exceptionList.exception);
        listview_yichangchuli.setAdapter(listAdapter_yiChangChuLi);

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
}
