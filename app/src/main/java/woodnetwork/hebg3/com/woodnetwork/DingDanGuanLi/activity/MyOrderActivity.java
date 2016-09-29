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
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter.MyOrderAdapter;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter.MyOrder_exception_Adapter;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter.MyOrder_filter_Adapter;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter.MyOrder_pay_Adapter;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProExceptionList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProFilterList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProFilterList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProPayList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerProPayList_listItem;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract.MyOrderContract;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.presenter.MyOrderPresenter;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_busnessList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_orderBuyerClose;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_orderBuyerProExceptionList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_orderBuyerProFilterList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_orderBuyerProList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_orderBuyerProPayList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter.BusnessListAdapter;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment.DividerItemDecoration;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.WoDe.adapter.DemanBuyerListAdapter;

public class MyOrderActivity extends AppCompatActivity implements MyOrderContract.MyOrderViewInterface {

    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_my_order_radiogroup)
    RadioGroup radiogroup;
    @Bind(R.id.activity_my_order_recyclerview)
    XRecyclerView recyclerview;
    public MyOrderContract.MyOrderPresenterInterface presenter;
    private MyOrderAdapter adapter;
    private MyOrder_filter_Adapter adapter_filter_weiFuKuan;
    private MyOrder_exception_Adapter adapter_exception;
    private MyOrder_pay_Adapter adapter_pay;
    private int closePosition;
    private int nowPosition = 0;
    private int page_no = 1;
    /**
     * 第一次不刷新列表，onPause后，再刷新
     */
    private boolean isFirst = true;
    private List<OrderBuyerProList_listItem> list_all;
    private List<OrderBuyerProFilterList_listItem> list_Filter;
    private List<OrderBuyerProExceptionList_listItem> list_Exception;
    private List<OrderBuyerProPayList_listItem> list_pay;

    private static Object object;

    private MyRequestInfo myRequestInfo;
    private Request_orderBuyerProList request_orderBuyerProList;
    private Request_orderBuyerProFilterList request_orderBuyerProFilterList;
    private Request_orderBuyerProExceptionList request_orderBuyerProExceptionList;
    private Request_orderBuyerProPayList request_orderBuyerProPayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);

        textTitle.setText("我的订单");
        imageTitleRight.setVisibility(View.GONE);

        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(this);
        Request_getAttribute request_getAttribute = new Request_getAttribute();
        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");


        myRequestInfo = new MyRequestInfo();

        myRequestInfo.req_meta = request_getAttribute;


        new MyOrderPresenter(this);
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                recyclerview.refreshComplete();
                recyclerview.loadMoreComplete();
                switch (i) {
                    case R.id.activity_my_order_radiobutton_quanbudingdan:
                        request_orderBuyerProList = new Request_orderBuyerProList();
                        request_orderBuyerProList.page_no = 1;
                        request_orderBuyerProList.page_size = 10;
                        myRequestInfo.req = request_orderBuyerProList;
                        presenter.getAllMyOrderData(myRequestInfo, 0);
                        nowPosition = 0;
                        break;
                    case R.id.activity_my_order_radiobutton_daifukuan:
                        request_orderBuyerProFilterList = new Request_orderBuyerProFilterList();
                        request_orderBuyerProFilterList.page_no = 1;
                        request_orderBuyerProFilterList.page_size = 10;
                        request_orderBuyerProFilterList.order_status = 0;
                        myRequestInfo.req = request_orderBuyerProFilterList;
                        presenter.getorderBuyerProFilterListData(myRequestInfo, 0);
                        nowPosition = 1;
                        break;
                    case R.id.activity_my_order_radiobutton_yifukuan:
                        request_orderBuyerProPayList = new Request_orderBuyerProPayList();
                        request_orderBuyerProPayList.page_no = 1;
                        request_orderBuyerProPayList.page_size = 10;
                        myRequestInfo.req = request_orderBuyerProPayList;
                        presenter.getOrderBuyerProPaidListData(myRequestInfo, 0);
                        nowPosition = 2;
                        break;
                    case R.id.activity_my_order_radiobutton_yichangdindan:
                        request_orderBuyerProExceptionList = new Request_orderBuyerProExceptionList();
                        request_orderBuyerProExceptionList.page_size = 10;
                        request_orderBuyerProExceptionList.page_no = 1;
                        myRequestInfo.req = request_orderBuyerProExceptionList;
                        presenter.getorderBuyerProExceptionListData(myRequestInfo, 0);
                        nowPosition = 3;
                        break;
                }

            }
        });
        ((RadioButton) (radiogroup.getChildAt(0))).setChecked(true);
    }

    @Override
    public void showMyOrderInfo(Object object) {
        this.object = object;
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
                                            @Override
                                            public void onRefresh() {
                                                page_no = 1;
                                                switch (nowPosition) {
                                                    case 0://全部订单
                                                        request_orderBuyerProList.page_no = page_no;
                                                        myRequestInfo.req = request_orderBuyerProList;
                                                        presenter.getAllMyOrderData(myRequestInfo, 1);
                                                        break;
                                                    case 1://待付款订单
                                                        request_orderBuyerProFilterList.page_no = page_no;
                                                        request_orderBuyerProFilterList.order_status = 0;
                                                        myRequestInfo.req = request_orderBuyerProFilterList;
                                                        presenter.getorderBuyerProFilterListData(myRequestInfo, 1);
                                                        break;
                                                    case 2://已付款订单
                                                        request_orderBuyerProPayList.page_no = page_no;
                                                        myRequestInfo.req = request_orderBuyerProPayList;
                                                        presenter.getOrderBuyerProPaidListData(myRequestInfo, 1);
                                                        break;
                                                    case 3://异常订单
                                                        request_orderBuyerProExceptionList.page_no = page_no;
                                                        myRequestInfo.req = request_orderBuyerProExceptionList;
                                                        presenter.getorderBuyerProExceptionListData(myRequestInfo, 1);
                                                        break;
                                                }
                                            }

                                            @Override
                                            public void onLoadMore() {
                                                page_no++;
                                                switch (nowPosition) {
                                                    case 0://全部订单
                                                        if (page_no > ((OrderBuyerProList) (MyOrderActivity.object)).total_page) {//判断是否为最后一页
                                                            recyclerview.setIsnomore(true);//底部显示没有更多数据
                                                        }
                                                        request_orderBuyerProList.page_no = page_no;
                                                        myRequestInfo.req = request_orderBuyerProList;
                                                        presenter.getAllMyOrderData(myRequestInfo, 2);
                                                        break;
                                                    case 1://待付款订单
                                                        if (page_no > ((OrderBuyerProFilterList) (MyOrderActivity.object)).total_page) {//判断是否为最后一页
                                                            recyclerview.setIsnomore(true);//底部显示没有更多数据
                                                        }
                                                        request_orderBuyerProFilterList.page_no = page_no;
                                                        request_orderBuyerProFilterList.order_status = 0;
                                                        myRequestInfo.req = request_orderBuyerProFilterList;
                                                        presenter.getorderBuyerProFilterListData(myRequestInfo, 2);
                                                        break;
                                                    case 2://已付款订单
                                                        if (page_no > ((OrderBuyerProPayList) (MyOrderActivity.object)).total_page) {//判断是否为最后一页
                                                            recyclerview.setIsnomore(true);//底部显示没有更多数据
                                                        }
                                                        request_orderBuyerProPayList.page_no = page_no;
                                                        myRequestInfo.req = request_orderBuyerProPayList;
                                                        presenter.getOrderBuyerProPaidListData(myRequestInfo, 2);
                                                        break;
                                                    case 3://异常订单
                                                        if (page_no > ((OrderBuyerProExceptionList) (MyOrderActivity.object)).total_page) {//判断是否为最后一页
                                                            recyclerview.setIsnomore(true);//底部显示没有更多数据
                                                        }
                                                        request_orderBuyerProExceptionList.page_no = page_no;
                                                        myRequestInfo.req = request_orderBuyerProExceptionList;
                                                        presenter.getorderBuyerProExceptionListData(myRequestInfo, 2);
                                                        break;
                                                }


                                            }
                                        }

        );


        if (object instanceof OrderBuyerProList)

        {//根据传入的参数，看属于哪个返回类型，加载哪个adapter
            list_all = ((OrderBuyerProList) object).list;
            adapter = new MyOrderAdapter(this, ((OrderBuyerProList) object).list);
            if (1 == ((OrderBuyerProList) object).total_page)

            {//如果总页数一共就一页，关闭加载更多功能
                recyclerview.setLoadingMoreEnabled(false);
            }
            recyclerview.setAdapter(adapter);
        } else if (object instanceof OrderBuyerProFilterList)

        {
            if (1 == ((OrderBuyerProFilterList) object).total_page)

            {//如果总页数一共就一页，关闭加载更多功能
                recyclerview.setLoadingMoreEnabled(false);
            }
            list_Filter = ((OrderBuyerProFilterList) object).list;
            //0根据status 字段判断是待付款还是待收货
//            if (((OrderBuyerProFilterList) object).list.get(0).status == 0) {
            adapter_filter_weiFuKuan = new MyOrder_filter_Adapter(this, ((OrderBuyerProFilterList) object).list);
            recyclerview.setAdapter(adapter_filter_weiFuKuan);
//            } else if (((OrderBuyerProFilterList) object).list.get(0).status == 2) {
//                adapter_filter_weiShouHuo = new MyOrder_filter_Adapter(this, ((OrderBuyerProFilterList) object).list);
//                recyclerview.setAdapter(adapter_filter_weiShouHuo);
//            }
        } else if (object instanceof OrderBuyerProExceptionList)

        {
            list_Exception = ((OrderBuyerProExceptionList) object).list;
            adapter_exception = new MyOrder_exception_Adapter(this, ((OrderBuyerProExceptionList) object).list);
            if (1 == ((OrderBuyerProExceptionList) object).total_page)

            {//如果总页数一共就一页，关闭加载更多功能
                recyclerview.setLoadingMoreEnabled(false);
            }

            recyclerview.setAdapter(adapter_exception);
        }else if (object instanceof OrderBuyerProPayList)

        {
            list_pay = ((OrderBuyerProPayList) object).list;
            adapter_pay = new MyOrder_pay_Adapter(this, ((OrderBuyerProPayList) object).list);
            if (1 == ((OrderBuyerProPayList) object).total_page)

            {//如果总页数一共就一页，关闭加载更多功能
                recyclerview.setLoadingMoreEnabled(false);
            }
            recyclerview.setAdapter(adapter_pay);
        }


    }

    @Override
    public void closeOrder(String oid, int position) {
        this.closePosition = position;
        Request_orderBuyerClose request_orderBuyerClose = new Request_orderBuyerClose();
        request_orderBuyerClose.oid = oid;
        myRequestInfo.req = request_orderBuyerClose;
        presenter.getorderBuyerProClose(myRequestInfo);
    }

    @Override
    public void refreshOrder() {
        switch (nowPosition) {
            case 0:
                List<OrderBuyerProList_listItem> list = adapter.getList();
                list.get(closePosition).status = 4;
                adapter.setList(list);
                adapter.notifyDataSetChanged();
                break;
            case 1:
                List<OrderBuyerProFilterList_listItem> list_filter_weiFuKuan = adapter_filter_weiFuKuan.getList();
                list_filter_weiFuKuan.get(closePosition).status = 4;
                adapter_filter_weiFuKuan.setList(list_filter_weiFuKuan);
                adapter_filter_weiFuKuan.notifyDataSetChanged();
                break;
            case 2:
                List<OrderBuyerProPayList_listItem> list_filter_weiShouHuo = adapter_pay.getList();
                list_pay.get(closePosition).status = 4;
                adapter_pay.setList(list_pay);
                adapter_pay.notifyDataSetChanged();
                break;
            case 3:
                List<OrderBuyerProExceptionList_listItem> list_exception = adapter_exception.getList();
                list_exception.get(closePosition).status = 4;
                adapter_exception.setList(list_exception);
                adapter_exception.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void loadMoreAll(List<OrderBuyerProList_listItem> list) {
        recyclerview.loadMoreComplete();
        list_all = adapter.getList();
        list_all.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refreshAll(OrderBuyerProList OrderBuyerProList) {
        recyclerview.refreshComplete();
        if (1 < OrderBuyerProList.total_page) {//如果刷新后数据多余一页，加载更多功能启用
            recyclerview.setLoadingMoreEnabled(true);
        }
        list_all = OrderBuyerProList.list;
        adapter.setList(list_all);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadMoreFilter(List<OrderBuyerProFilterList_listItem> list) {
        recyclerview.loadMoreComplete();
//        if (list.get(0).status == 0) {//未付款
        list_Filter = adapter_filter_weiFuKuan.getList();
//        } else if (list.get(0).status == 2) {//未收货
//            list_Filter = adapter_filter_weiShouHuo.getList();
//        }
        list_Filter.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refreshFilter(OrderBuyerProFilterList orderBuyerProFilterList) {
        recyclerview.refreshComplete();
        if (1 < orderBuyerProFilterList.total_page) {//如果刷新后数据多余一页，加载更多功能启用
            recyclerview.setLoadingMoreEnabled(true);
        }
        list_Filter = orderBuyerProFilterList.list;
        if (orderBuyerProFilterList.list.get(0).status == 0) {//未付款
            adapter_filter_weiFuKuan.setList(list_Filter);
            adapter_filter_weiFuKuan.notifyDataSetChanged();
        }
    }

    @Override
    public void loadMoreException(List<OrderBuyerProExceptionList_listItem> list) {
        recyclerview.loadMoreComplete();
        list_Exception = adapter_exception.getList();
        list_Exception.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refreshException(OrderBuyerProExceptionList orderBuyerProExceptionList) {
        recyclerview.refreshComplete();
        if (1 < orderBuyerProExceptionList.total_page) {//如果刷新后数据多余一页，加载更多功能启用
            recyclerview.setLoadingMoreEnabled(true);
        }
        list_Exception = orderBuyerProExceptionList.list;
        adapter_exception.setList(list_Exception);
        adapter_exception.notifyDataSetChanged();
    }

    @Override
    public void loadMorePay(List<OrderBuyerProPayList_listItem> list) {
        recyclerview.loadMoreComplete();
        list_pay = adapter_pay.getList();
        list_pay.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refreshPay(OrderBuyerProPayList orderBuyerProPayList) {
        recyclerview.refreshComplete();
        if (1 < orderBuyerProPayList.total_page) {//如果刷新后数据多余一页，加载更多功能启用
            recyclerview.setLoadingMoreEnabled(true);
        }
        list_pay = orderBuyerProPayList.list;
        adapter_pay.setList(list_pay);
        adapter_pay.notifyDataSetChanged();
    }

    @Override
    public void orderReceive(int position) {
        Intent intent = new Intent(this, OrderReceiveActivity.class);
        switch (nowPosition) {
            case 0://全部订单
                list_all = adapter.getList();
                intent.putExtra("id", list_all.get(position).number);
                intent.putExtra("oid", list_all.get(position).id);
                intent.putExtra("creat_time", list_all.get(position).creat_time);
                intent.putExtra("seller", list_all.get(position).seller);
                intent.putExtra("total_price",String.valueOf(list_all.get(position).total_price));
                intent.putExtra("number", String.valueOf(list_all.get(position).products.size()));
                break;
            case 2://已付款订单
                list_pay = adapter_pay.getList();
                intent.putExtra("id", list_pay.get(position).number);
                intent.putExtra("oid", list_pay.get(position).id);
                intent.putExtra("creat_time", list_pay.get(position).creat_time);
                intent.putExtra("seller", list_pay.get(position).seller);
                intent.putExtra("total_price", String.valueOf(list_pay.get(position).total_price));
                intent.putExtra("number", String.valueOf(list_pay.get(position).products.size()));
                break;
            case 3://异常订单
                list_Exception = adapter_exception.getList();
                intent.putExtra("id", list_Exception.get(position).number);
                intent.putExtra("oid", list_Exception.get(position).id);
                intent.putExtra("creat_time", list_Exception.get(position).creat_time);
                intent.putExtra("seller", list_Exception.get(position).seller);
                intent.putExtra("total_price", String.valueOf(list_Exception.get(position).total_price));
                intent.putExtra("number", String.valueOf(list_Exception.get(position).products.size()));
                break;
        }
        startActivityForResult(intent, 0);

    }


    @Override
    public void setPresenter(MyOrderContract.MyOrderPresenterInterface presenter) {
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
        if (!isFirst) {
            switch (nowPosition) {
                case 0://全部订单
                    request_orderBuyerProList.page_no = 1;
                    myRequestInfo.req = request_orderBuyerProList;
                    presenter.getAllMyOrderData(myRequestInfo, 1);
                    break;
                case 1://待付款订单
                    request_orderBuyerProFilterList.page_no = 1;
                    request_orderBuyerProFilterList.order_status = 0;
                    myRequestInfo.req = request_orderBuyerProFilterList;
                    presenter.getorderBuyerProFilterListData(myRequestInfo, 1);
                    break;
                case 2://已付款订单
                    request_orderBuyerProPayList.page_no = 1;
                    myRequestInfo.req = request_orderBuyerProPayList;
                    presenter.getOrderBuyerProPaidListData(myRequestInfo, 1);
                    break;
                case 3://异常订单
                    request_orderBuyerProExceptionList.page_no = 1;
                    myRequestInfo.req = request_orderBuyerProExceptionList;
                    presenter.getorderBuyerProExceptionListData(myRequestInfo, 1);
                    break;
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isFirst = false;
    }
}
