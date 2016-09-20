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
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter.MyOrderAdapter;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter.MyOrder_exception_Adapter;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter.MyOrder_filter_Adapter;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemExceptionList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemFilterList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerDemList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProExceptionList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProFilterList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract.DemOrderContract;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract.MyOrderContract;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.presenter.DemOrderPresenter;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.presenter.MyOrderPresenter;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_orderBuyerClose;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_orderBuyerProExceptionList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_orderBuyerProFilterList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_orderBuyerProList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_order_buyer_dem_exception_list;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_order_buyer_dem_filter_list;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_order_buyer_dem_list;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment.DividerItemDecoration;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;

public class DemOrderActivity extends AppCompatActivity implements DemOrderContract.DemOrderViewInterface {

    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_dem_order_radiogroup)
    RadioGroup radiogroup;
    @Bind(R.id.activity_dem_order_recyclerview)
    RecyclerView recyclerview;
    public DemOrderContract.DemOrderPresenterInterface presenter;
    private  MyRequestInfo myRequestInfo;
    private DemOrderAdapter adapter;
    private DemOrder_filter_Adapter adapter_filter_weiFuKuan;
    private DemOrder_filter_Adapter adapter_filter_weiShouHuo;
    private DemOrder_exception_Adapter adapter_exception;
    private int closePosition;
    private BaseAdapter baseAdapter;
    private int nowPosition=0;//当前所展示的订单类型
    private  Request_order_buyer_dem_filter_list request_order_buyer_dem_filter_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dem_order);
        ButterKnife.bind(this);

        textTitle.setText("求购订单");
        imageTitleRight.setVisibility(View.GONE);

        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(this);
        Request_getAttribute request_getAttribute = new Request_getAttribute();
        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");


         myRequestInfo = new MyRequestInfo();

        myRequestInfo.req_meta = request_getAttribute;


        new DemOrderPresenter(this);
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.activity_dem_order_radiobutton_quanbudingdan:
                        Request_order_buyer_dem_list request_order_buyer_dem_list=new Request_order_buyer_dem_list();
                        request_order_buyer_dem_list.page_no=1;
                        request_order_buyer_dem_list.page_size=10;
                        myRequestInfo.req = request_order_buyer_dem_list;
                        presenter.getAllDemOrderData(myRequestInfo);
                        nowPosition=0;
                        break;
                    case R.id.activity_dem_order_radiobutton_daifukuan:
                        request_order_buyer_dem_filter_list=new Request_order_buyer_dem_filter_list();
                        request_order_buyer_dem_filter_list.page_no=1;
                        request_order_buyer_dem_filter_list.page_size=10;
                        request_order_buyer_dem_filter_list.order_status=0;
                        myRequestInfo.req = request_order_buyer_dem_filter_list;
                        presenter.getorderBuyerProFilterListData(myRequestInfo);
                        nowPosition=1;
                        break;
                    case R.id.activity_dem_order_radiobutton_daishouhuo:
                        request_order_buyer_dem_filter_list.page_no=1;
                        request_order_buyer_dem_filter_list.page_size=10;
                        request_order_buyer_dem_filter_list.order_status=2;
                        myRequestInfo.req = request_order_buyer_dem_filter_list;
                        presenter.getorderBuyerProExceptionListData(myRequestInfo);
                        nowPosition=2;
                        break;
                    case R.id.activity_dem_order_radiobutton_yichangdindan:
                        Request_order_buyer_dem_exception_list request_order_buyer_dem_exception_list=new Request_order_buyer_dem_exception_list();
                        request_order_buyer_dem_exception_list.page_size=10;
                        request_order_buyer_dem_exception_list.page_no=1;
                        myRequestInfo.req = request_order_buyer_dem_exception_list;
                        presenter.getorderBuyerProClose(myRequestInfo);
                        nowPosition=3;
                        break;
                }

            }
        });
        ((RadioButton)(radiogroup.getChildAt(0))).setChecked(true);
    }

    @Override
    public void showDemOrderInfo(Object object) {
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, 5));
        if(object instanceof OrderBuyerDemList){//根据传入的参数，看属于哪个返回类型，加载哪个adapter
            adapter = new DemOrderAdapter(this, ((OrderBuyerDemList)object).list);
            recyclerview.setAdapter(adapter);
        }else if (object instanceof OrderBuyerProFilterList){//0根据status 字段判断是待付款还是待收货
            if( ((OrderBuyerProFilterList)object).list.get(0).status==0){
                adapter_filter_weiFuKuan=new DemOrder_filter_Adapter(this, ((OrderBuyerDemFilterList)object).list);
                recyclerview.setAdapter(adapter_filter_weiFuKuan);
            }else if( ((OrderBuyerProFilterList)object).list.get(0).status==2){
                adapter_filter_weiShouHuo = new DemOrder_filter_Adapter(this, ((OrderBuyerDemFilterList)object).list);
                recyclerview.setAdapter(adapter_filter_weiShouHuo);
            }
        }else if (object instanceof OrderBuyerProExceptionList){
            adapter_exception = new DemOrder_exception_Adapter(this, ((OrderBuyerDemExceptionList)object).list);
            recyclerview.setAdapter(adapter_exception);
        }



    }

    @Override
    public void closeOrder(String oid, int position) {
        this.closePosition=position;
        Request_orderBuyerClose request_orderBuyerClose=new Request_orderBuyerClose();
        request_orderBuyerClose.oid=oid;
        myRequestInfo.req=request_orderBuyerClose;
        presenter.getorderBuyerProClose(myRequestInfo);
    }

    @Override
    public void refreshOrder() {
        switch (nowPosition){
            case 0:
                List<OrderBuyerDemList_listItem> list=adapter.getList();
                list.get(closePosition).status=4;
                adapter.setList(list);
                adapter.notifyDataSetChanged();
                break;
            case 1:
                List<OrderBuyerDemFilterList_listItem> list_filter_weiFuKuan=adapter_filter_weiFuKuan.getList();
                list_filter_weiFuKuan.get(closePosition).status=4;
                adapter_filter_weiFuKuan.setList(list_filter_weiFuKuan);
                adapter_filter_weiFuKuan.notifyDataSetChanged();
                break;
            case 2:
                List<OrderBuyerDemFilterList_listItem> list_filter_weiShouHuo=adapter_filter_weiShouHuo.getList();
                list_filter_weiShouHuo.get(closePosition).status=4;
                adapter_filter_weiShouHuo.setList(list_filter_weiShouHuo);
                adapter_filter_weiShouHuo.notifyDataSetChanged();
                break;
            case 3:
                List<OrderBuyerDemExceptionList_listItem> list_exception=adapter_exception.getList();
                list_exception.get(closePosition).status=4;
                adapter_exception.setList(list_exception);
                adapter_exception.notifyDataSetChanged();
                break;
        }
    }


    @Override
    public void setPresenter(DemOrderContract.DemOrderPresenterInterface presenter) {
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
