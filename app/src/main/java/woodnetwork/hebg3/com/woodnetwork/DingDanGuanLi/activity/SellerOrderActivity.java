package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter.DemOrderAdapter;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter.DemOrder_exception_Adapter;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter.DemOrder_filter_Adapter;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter.SellerOrderAdapter;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter.SellerOrder_exception_Adapter;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter.SellerOrder_filter_Adapter;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemExceptionList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemFilterList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderSellerExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderSellerExceptionList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderSellerFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderSellerFilterList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderSellerList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderSellerList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract.DemOrderContract;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract.SellerOrderContract;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.presenter.DemOrderPresenter;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.presenter.SellerOrderPresenter;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_orderBuyerClose;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_order_buyer_dem_exception_list;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_order_buyer_dem_filter_list;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_order_buyer_dem_list;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_order_seller_exception_list;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_order_seller_filter_list;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_order_seller_list;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment.DividerItemDecoration;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;

public class SellerOrderActivity extends AppCompatActivity implements SellerOrderContract.SellerOrderViewInterface {

    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_seller_order_radiogroup)
    RadioGroup radiogroup;
    @Bind(R.id.activity_seller_order_recyclerview)
    RecyclerView recyclerview;
    public SellerOrderContract.SellerOrderPresenterInterface presenter;
    private  MyRequestInfo myRequestInfo;
    private SellerOrderAdapter adapter;
    private SellerOrder_filter_Adapter adapter_filter_weiFaHuo;
    private SellerOrder_exception_Adapter adapter_exception;
    /**
     * 关闭订单时，记录的position
     */
    private int closePosition;
    private int nowPosition=0;//当前所展示的订单类型 0全部，1未发货，2异常
    private Request_order_seller_filter_list request_order_seller_filter_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_order);
        ButterKnife.bind(this);

        textTitle.setText("求购订单");
        imageTitleRight.setVisibility(View.GONE);

        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(this);
        Request_getAttribute request_getAttribute = new Request_getAttribute();
        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");


         myRequestInfo = new MyRequestInfo();

        myRequestInfo.req_meta = request_getAttribute;


        new SellerOrderPresenter(this);
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.activity_seller_order_radiobutton_quanbudingdan://全部订单
                        Request_order_seller_list request_order_seller_list=new Request_order_seller_list();
                        request_order_seller_list.page_no=1;
                        request_order_seller_list.page_size=10;
                        myRequestInfo.req = request_order_seller_list;
                        presenter.getAllSellerOrderData(myRequestInfo);
                        nowPosition=0;
                        break;
                    case R.id.activity_seller_order_radiobutton_daifahuo://待发货
                        request_order_seller_filter_list=new Request_order_seller_filter_list();
                        request_order_seller_filter_list.page_no=1;
                        request_order_seller_filter_list.page_size=10;
                        request_order_seller_filter_list.order_status=2;
                        myRequestInfo.req = request_order_seller_filter_list;
                        presenter.getSellerOrderExceptionListData(myRequestInfo);
                        nowPosition=1;
                        break;
                    case R.id.activity_seller_order_radiobutton_yichangdindan://异常订单
                        Request_order_seller_exception_list request_order_seller_exception_list=new Request_order_seller_exception_list();
                        request_order_seller_exception_list.page_size=10;
                        request_order_seller_exception_list.page_no=1;
                        myRequestInfo.req = request_order_seller_exception_list;
                        presenter.getSellerOrderExceptionListData(myRequestInfo);
                        nowPosition=2;
                        break;
                }

            }
        });
        ((RadioButton)(radiogroup.getChildAt(0))).setChecked(true);
    }

    @Override
    public void showSellerOrderInfo(Object object) {
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, 5));
        if(object instanceof OrderSellerList){//根据传入的参数，看属于哪个返回类型，加载哪个adapter
            adapter = new SellerOrderAdapter(this, ((OrderSellerList)object).list);
            recyclerview.setAdapter(adapter);
        }else if (object instanceof OrderSellerFilterList){//0根据status 字段判断是待付款还是待收货
            if( ((OrderSellerFilterList)object).list.get(0).status==0){
                adapter_filter_weiFaHuo=new SellerOrder_filter_Adapter(this, ((OrderSellerFilterList)object).list);
                recyclerview.setAdapter(adapter_filter_weiFaHuo);
            }
        }else if (object instanceof OrderSellerExceptionList){
            adapter_exception = new SellerOrder_exception_Adapter(this, ((OrderSellerExceptionList)object).list);
            recyclerview.setAdapter(adapter_exception);
        }



    }

    @Override
    public void closeOrder(String oid, int position) {
        this.closePosition=position;
        Request_orderBuyerClose request_orderBuyerClose=new Request_orderBuyerClose();
        request_orderBuyerClose.oid=oid;
        myRequestInfo.req=request_orderBuyerClose;
        presenter.closecOrder(myRequestInfo);
    }

    @Override
    public void refreshOrder() {
        switch (nowPosition){
            case 0:
                List<OrderSellerList_listItem> list=adapter.getList();
                list.get(closePosition).status=4;
                adapter.setList(list);
                adapter.notifyItemChanged(closePosition);
                break;
            case 1:
                List<OrderSellerFilterList_listItem> list_filter_weiShouHuo=adapter_filter_weiFaHuo.getList();
                list_filter_weiShouHuo.get(closePosition).status=4;
                adapter_filter_weiFaHuo.setList(list_filter_weiShouHuo);
                adapter_filter_weiFaHuo.notifyItemChanged(closePosition);
                break;
            case 2:
                List<OrderSellerExceptionList_listItem> list_exception=adapter_exception.getList();
                list_exception.get(closePosition).status=4;
                adapter_exception.setList(list_exception);
                adapter_exception.notifyItemChanged(closePosition);
                break;
        }
    }


    @Override
    public void setPresenter(SellerOrderContract.SellerOrderPresenterInterface presenter) {
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
}
