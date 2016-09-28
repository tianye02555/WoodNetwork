package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.activity;

import android.content.Intent;
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

import com.jcodecraeer.xrecyclerview.XRecyclerView;

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
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProExceptionList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProFilterList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProList_listItem;
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
    XRecyclerView recyclerview;
    public SellerOrderContract.SellerOrderPresenterInterface presenter;

    private SellerOrderAdapter adapter;
    private SellerOrder_filter_Adapter adapter_filter_weiFaHuo;
    private SellerOrder_exception_Adapter adapter_exception;
    /**
     * 关闭订单时，记录的position
     */
    private int closePosition;
    private int nowPosition = 0;//当前所展示的订单类型 0全部，1未发货，2异常
    private int page_no = 1;

    /**
     * 第一次不刷新列表，onPause后，再刷新
     */
    private boolean isFirst=true;
    private static Object object;

    private MyRequestInfo myRequestInfo;
    private Request_order_seller_list request_order_seller_list;
    private Request_order_seller_filter_list request_order_seller_filter_list;
    private Request_order_seller_exception_list request_order_seller_exception_list;

    private List<OrderSellerList_listItem> list_all;
    private List<OrderSellerFilterList_listItem> list_Filter;
    private List<OrderSellerExceptionList_listItem> list_Exception;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_order);
        ButterKnife.bind(this);

        textTitle.setText("卖家订单");
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
                switch (i) {
                    case R.id.activity_seller_order_radiobutton_quanbudingdan://全部订单
                        request_order_seller_list = new Request_order_seller_list();
                        request_order_seller_list.page_no = 1;
                        request_order_seller_list.page_size = 10;
                        myRequestInfo.req = request_order_seller_list;
                        presenter.getAllSellerOrderData(myRequestInfo, 0);
                        nowPosition = 0;
                        break;
                    case R.id.activity_seller_order_radiobutton_daifahuo://待发货
                        request_order_seller_filter_list = new Request_order_seller_filter_list();
                        request_order_seller_filter_list.page_no = 1;
                        request_order_seller_filter_list.page_size = 10;
                        request_order_seller_filter_list.order_status = 2;
                        myRequestInfo.req = request_order_seller_filter_list;
                        presenter.getSellerFilterOrderData(myRequestInfo, 0);
                        nowPosition = 1;
                        break;
                    case R.id.activity_seller_order_radiobutton_yichangdindan://异常订单
                        request_order_seller_exception_list = new Request_order_seller_exception_list();
                        request_order_seller_exception_list.page_size = 10;
                        request_order_seller_exception_list.page_no = 1;
                        myRequestInfo.req = request_order_seller_exception_list;
                        presenter.getSellerOrderExceptionListData(myRequestInfo, 0);
                        nowPosition = 2;
                        break;
                }

            }
        });
        ((RadioButton) (radiogroup.getChildAt(0))).setChecked(true);
    }

    @Override
    public void showSellerOrderInfo(Object object) {
        this.object=object;
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
                                            @Override
                                            public void onRefresh() {
                                                page_no = 1;
                                                switch (nowPosition) {
                                                    case 0://全部订单
                                                        request_order_seller_list.page_no = page_no;
                                                        myRequestInfo.req = request_order_seller_list;
                                                        presenter.getAllSellerOrderData(myRequestInfo, 1);
                                                        break;
                                                    case 1://待付款订单
                                                        request_order_seller_filter_list.page_no = page_no;
                                                        request_order_seller_filter_list.order_status = 0;
                                                        myRequestInfo.req = request_order_seller_filter_list;
                                                        presenter.getSellerFilterOrderData(myRequestInfo, 1);
                                                        break;
                                                    case 2://异常订单
                                                        request_order_seller_exception_list.page_no = page_no;
                                                        myRequestInfo.req = request_order_seller_exception_list;
                                                        presenter.getSellerOrderExceptionListData(myRequestInfo, 1);
                                                        break;
                                                }
                                            }

                                            @Override
                                            public void onLoadMore() {
                                                page_no++;
                                                switch (nowPosition) {
                                                    case 0://全部订单
                                                        if (page_no >= ((OrderSellerList) (SellerOrderActivity.object)).total_page) {//判断是否为最后一页
                                                            recyclerview.setIsnomore(true);//底部显示没有更多数据
                                                        }
                                                        request_order_seller_list.page_no = page_no;
                                                        myRequestInfo.req = request_order_seller_list;
                                                        presenter.getAllSellerOrderData(myRequestInfo, 2);
                                                        break;

                                                    case 1://待发货订单
                                                        if (page_no >= ((OrderSellerFilterList) (SellerOrderActivity.object)).total_page) {//判断是否为最后一页
                                                            recyclerview.setIsnomore(true);//底部显示没有更多数据
                                                        }
                                                        request_order_seller_filter_list.page_no = page_no;
                                                        request_order_seller_filter_list.order_status = 2;
                                                        myRequestInfo.req = request_order_seller_filter_list;
                                                        presenter.getSellerFilterOrderData(myRequestInfo, 2);
                                                        break;
                                                    case 2://异常订单
                                                        if (page_no >= ((OrderSellerExceptionList) (SellerOrderActivity.object)).total_page) {//判断是否为最后一页
                                                            recyclerview.setIsnomore(true);//底部显示没有更多数据
                                                        }
                                                        request_order_seller_exception_list.page_no = page_no;
                                                        myRequestInfo.req = request_order_seller_exception_list;
                                                        presenter.getSellerOrderExceptionListData(myRequestInfo, 2);
                                                        break;
                                                }


                                            }
                                        }

        );
        if (object instanceof OrderSellerList) {//根据传入的参数，看属于哪个返回类型，加载哪个adapter
            adapter = new SellerOrderAdapter(this, ((OrderSellerList) object).list);
            if (1 == ((OrderSellerList) object).total_page)

            {//如果总页数一共就一页，关闭加载更多功能
                recyclerview.setLoadingMoreEnabled(false);
            }
            recyclerview.setAdapter(adapter);
        } else if (object instanceof OrderSellerFilterList) {//0根据status 字段判断是待付款还是待收货
            if (((OrderSellerFilterList) object).list.get(0).status == 0) {
                adapter_filter_weiFaHuo = new SellerOrder_filter_Adapter(this, ((OrderSellerFilterList) object).list);
                if (1 == ((OrderSellerList) object).total_page)

                {//如果总页数一共就一页，关闭加载更多功能
                    recyclerview.setLoadingMoreEnabled(false);
                }
                recyclerview.setAdapter(adapter_filter_weiFaHuo);
            }
        } else if (object instanceof OrderSellerExceptionList) {
            adapter_exception = new SellerOrder_exception_Adapter(this, ((OrderSellerExceptionList) object).list);
            if (1 == ((OrderSellerExceptionList) object).total_page)

            {//如果总页数一共就一页，关闭加载更多功能
                recyclerview.setLoadingMoreEnabled(false);
            }
            recyclerview.setAdapter(adapter_exception);
        }


    }

    @Override
    public void closeOrder(String oid, int position) {
        this.closePosition = position;
        Request_orderBuyerClose request_orderBuyerClose = new Request_orderBuyerClose();
        request_orderBuyerClose.oid = oid;
        myRequestInfo.req = request_orderBuyerClose;
        presenter.closecOrder(myRequestInfo);
    }

    @Override
    public void refreshOrder() {
        switch (nowPosition) {
            case 0:
                List<OrderSellerList_listItem> list = adapter.getList();
                list.get(closePosition).status = 4;
                adapter.setList(list);
                adapter.notifyItemChanged(closePosition);
                break;
            case 1:
                List<OrderSellerFilterList_listItem> list_filter_weiShouHuo = adapter_filter_weiFaHuo.getList();
                list_filter_weiShouHuo.get(closePosition).status = 4;
                adapter_filter_weiFaHuo.setList(list_filter_weiShouHuo);
                adapter_filter_weiFaHuo.notifyItemChanged(closePosition);
                break;
            case 2:
                List<OrderSellerExceptionList_listItem> list_exception = adapter_exception.getList();
                list_exception.get(closePosition).status = 4;
                adapter_exception.setList(list_exception);
                adapter_exception.notifyItemChanged(closePosition);
                break;
        }
    }

    @Override
    public void loadMoreAll(List<OrderSellerList_listItem> list) {
        recyclerview.loadMoreComplete();
        list_all = adapter.getList();
        list_all.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refreshAll(OrderSellerList OrderSellerList) {
        recyclerview.refreshComplete();
        if (1 < OrderSellerList.total_page) {//如果刷新后数据多余一页，加载更多功能启用
            recyclerview.setLoadingMoreEnabled(true);
        }
        list_all = OrderSellerList.list;
        adapter.setList(list_all);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadMoreFilter(List<OrderSellerFilterList_listItem> list) {
        recyclerview.loadMoreComplete();

        list_Filter = adapter_filter_weiFaHuo.getList();
        list_Filter.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refreshFilter(OrderSellerFilterList orderSellerFilterList) {
        recyclerview.refreshComplete();
        if (1 < orderSellerFilterList.total_page) {//如果刷新后数据多余一页，加载更多功能启用
            recyclerview.setLoadingMoreEnabled(true);
        }
        list_Filter = orderSellerFilterList.list;

        adapter_filter_weiFaHuo.setList(list_Filter);
        adapter_filter_weiFaHuo.notifyDataSetChanged();

    }

    @Override
    public void loadMoreException(List<OrderSellerExceptionList_listItem> list) {
        recyclerview.loadMoreComplete();
        list_Exception = adapter_exception.getList();
        list_Exception.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refreshException(OrderSellerExceptionList orderSellerExceptionList) {
        recyclerview.refreshComplete();
        if (1 < orderSellerExceptionList.total_page) {//如果刷新后数据多余一页，加载更多功能启用
            recyclerview.setLoadingMoreEnabled(true);
        }
        list_Exception = orderSellerExceptionList.list;
        adapter_exception.setList(list_Exception);
        adapter_exception.notifyDataSetChanged();
    }

    @Override
    public void orderDelivery(int position) {
        Intent intent=new Intent(this,OrderReceiveActivity.class);
        switch (nowPosition) {
            case 0://全部订单
                list_all=adapter.getList();
                intent.putExtra("id",list_all.get(position).number);
                intent.putExtra("creat_time",list_all.get(position).creat_time);
                intent.putExtra("seller",list_all.get(position).buyer);
                intent.putExtra("total_price",list_all.get(position).total_price);
                intent.putExtra("number",String.valueOf(list_all.get(position).products.size()));
                intent.putExtra("flag","1");
                break;
            case 2://待收货订单
                list_Filter=adapter_filter_weiFaHuo.getList();
                intent.putExtra("id",list_Filter.get(position).number);
                intent.putExtra("creat_time",list_Filter.get(position).creat_time);
                intent.putExtra("seller",list_Filter.get(position).buyer);
                intent.putExtra("total_price",list_Filter.get(position).total_price);
                intent.putExtra("number",String.valueOf(list_Filter.get(position).products.size()));
                intent.putExtra("flag","1");
                break;
            case 3://异常订单
                list_Exception=adapter_exception.getList();
                intent.putExtra("id",list_Exception.get(position).number);
                intent.putExtra("creat_time",list_Exception.get(position).creat_time);
                intent.putExtra("seller",list_Exception.get(position).buyer);
                intent.putExtra("total_price",list_Exception.get(position).total_price);
                intent.putExtra("number",String.valueOf(list_Exception.get(position).products.size()));
                intent.putExtra("flag","1");
                break;
        }
        startActivityForResult(intent,0);

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
    @Override
    protected void onResume() {
        super.onResume();
        if(!isFirst) {
            switch (nowPosition) {
                case 0://全部订单
                    request_order_seller_list.page_no = page_no;
                    myRequestInfo.req = request_order_seller_list;
                    presenter.getAllSellerOrderData(myRequestInfo, 1);
                    break;
                case 1://待付款订单
                    request_order_seller_filter_list.page_no = page_no;
                    request_order_seller_filter_list.order_status = 0;
                    myRequestInfo.req = request_order_seller_filter_list;
                    presenter.getSellerFilterOrderData(myRequestInfo, 1);
                    break;
                case 2://异常订单
                    request_order_seller_exception_list.page_no = page_no;
                    myRequestInfo.req = request_order_seller_exception_list;
                    presenter.getSellerOrderExceptionListData(myRequestInfo, 1);
                    break;
            }

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isFirst=false;
    }
}
