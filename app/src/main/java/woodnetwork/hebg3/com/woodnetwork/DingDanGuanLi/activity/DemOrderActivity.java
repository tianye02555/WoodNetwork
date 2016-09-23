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
    XRecyclerView recyclerview;
    public DemOrderContract.DemOrderPresenterInterface presenter;

    private DemOrderAdapter adapter;
    private DemOrder_filter_Adapter adapter_filter_weiFuKuan;
    private DemOrder_filter_Adapter adapter_filter_weiShouHuo;
    private DemOrder_exception_Adapter adapter_exception;

    private int closePosition;
    private int nowPosition = 0;//当前所展示的订单类型
    private int page_no = 1;

    /**
     * 第一次不刷新列表，onPause后，再刷新
     */
    private boolean isFirst=true;
    private static Object object;

    private List<OrderBuyerDemList_listItem> list_all;
    private List<OrderBuyerDemFilterList_listItem> list_Filter;
    private List<OrderBuyerDemExceptionList_listItem> list_Exception;

    private MyRequestInfo myRequestInfo;
    private Request_order_buyer_dem_filter_list request_order_buyer_dem_filter_list;
    private Request_order_buyer_dem_list request_order_buyer_dem_list;
    private Request_order_buyer_dem_exception_list request_order_buyer_dem_exception_list;

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
                switch (i) {
                    case R.id.activity_dem_order_radiobutton_quanbudingdan:
                        request_order_buyer_dem_list = new Request_order_buyer_dem_list();
                        request_order_buyer_dem_list.page_no = 1;
                        request_order_buyer_dem_list.page_size = 10;
                        myRequestInfo.req = request_order_buyer_dem_list;
                        presenter.getAllDemOrderData(myRequestInfo,0);
                        nowPosition = 0;
                        break;
                    case R.id.activity_dem_order_radiobutton_daifukuan:
                        request_order_buyer_dem_filter_list = new Request_order_buyer_dem_filter_list();
                        request_order_buyer_dem_filter_list.page_no = 1;
                        request_order_buyer_dem_filter_list.page_size = 10;
                        request_order_buyer_dem_filter_list.order_status = 0;
                        myRequestInfo.req = request_order_buyer_dem_filter_list;
                        presenter.getorderBuyerDemFilterListData(myRequestInfo,0);
                        nowPosition = 1;
                        break;
                    case R.id.activity_dem_order_radiobutton_daishouhuo:
                        request_order_buyer_dem_filter_list.page_no = 1;
                        request_order_buyer_dem_filter_list.page_size = 10;
                        request_order_buyer_dem_filter_list.order_status = 2;
                        myRequestInfo.req = request_order_buyer_dem_filter_list;
                        presenter.getorderBuyerDemFilterListData(myRequestInfo,0);
                        nowPosition = 2;
                        break;
                    case R.id.activity_dem_order_radiobutton_yichangdindan:
                        request_order_buyer_dem_exception_list = new Request_order_buyer_dem_exception_list();
                        request_order_buyer_dem_exception_list.page_size = 10;
                        request_order_buyer_dem_exception_list.page_no = 1;
                        myRequestInfo.req = request_order_buyer_dem_exception_list;
                        presenter.getorderBuyerDemExceptionListData(myRequestInfo,0);
                        nowPosition = 3;
                        break;
                }

            }
        });
        ((RadioButton) (radiogroup.getChildAt(0))).setChecked(true);
    }

    @Override
    public void showDemOrderInfo(Object object) {
        this.object=object;
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
                                            @Override
                                            public void onRefresh() {
                                                page_no = 1;
                                                switch (nowPosition) {
                                                    case 0://全部订单
                                                        request_order_buyer_dem_list.page_no = page_no;
                                                        myRequestInfo.req = request_order_buyer_dem_list;
                                                        presenter.getAllDemOrderData(myRequestInfo, 1);
                                                        break;
                                                    case 1://待付款订单
                                                        request_order_buyer_dem_filter_list.page_no = page_no;
                                                        request_order_buyer_dem_filter_list.order_status = 0;
                                                        myRequestInfo.req = request_order_buyer_dem_filter_list;
                                                        presenter.getorderBuyerDemFilterListData(myRequestInfo, 1);
                                                        break;
                                                    case 2://待收货订单
                                                        request_order_buyer_dem_filter_list.page_no = page_no;
                                                        request_order_buyer_dem_filter_list.order_status = 2;
                                                        myRequestInfo.req = request_order_buyer_dem_filter_list;
                                                        presenter.getorderBuyerDemFilterListData(myRequestInfo, 1);
                                                        break;
                                                    case 3://异常订单
                                                        request_order_buyer_dem_exception_list.page_no = page_no;
                                                        myRequestInfo.req = request_order_buyer_dem_exception_list;
                                                        presenter.getorderBuyerDemExceptionListData(myRequestInfo, 1);
                                                        break;
                                                }
                                            }

                                            @Override
                                            public void onLoadMore() {
                                                page_no++;
                                                switch (nowPosition) {
                                                    case 0://全部订单
                                                        if (page_no >= ((OrderBuyerDemList) (DemOrderActivity.object)).total_page) {//判断是否为最后一页
                                                            recyclerview.setIsnomore(true);//底部显示没有更多数据
                                                        }
                                                        request_order_buyer_dem_list.page_no = page_no;
                                                        myRequestInfo.req = request_order_buyer_dem_list;
                                                        presenter.getAllDemOrderData(myRequestInfo, 2);
                                                        break;
                                                    case 1://待付款订单
                                                        if (page_no >= ((OrderBuyerDemFilterList) (DemOrderActivity.object)).total_page) {//判断是否为最后一页
                                                            recyclerview.setIsnomore(true);//底部显示没有更多数据
                                                        }
                                                        request_order_buyer_dem_filter_list.page_no = page_no;
                                                        request_order_buyer_dem_filter_list.order_status = 0;
                                                        myRequestInfo.req = request_order_buyer_dem_filter_list;
                                                        presenter.getorderBuyerDemFilterListData(myRequestInfo, 2);
                                                        break;
                                                    case 2://待收货订单
                                                        if (page_no >= ((OrderBuyerDemFilterList) (DemOrderActivity.object)).total_page) {//判断是否为最后一页
                                                            recyclerview.setIsnomore(true);//底部显示没有更多数据
                                                        }
                                                        request_order_buyer_dem_filter_list.page_no = page_no;
                                                        request_order_buyer_dem_filter_list.order_status = 2;
                                                        myRequestInfo.req = request_order_buyer_dem_filter_list;
                                                        presenter.getorderBuyerDemFilterListData(myRequestInfo, 2);
                                                        break;
                                                    case 3://异常订单
                                                        if (page_no >= ((OrderBuyerDemExceptionList) (DemOrderActivity.object)).total_page) {//判断是否为最后一页
                                                            recyclerview.setIsnomore(true);//底部显示没有更多数据
                                                        }
                                                        request_order_buyer_dem_exception_list.page_no = page_no;
                                                        myRequestInfo.req = request_order_buyer_dem_exception_list;
                                                        presenter.getorderBuyerDemExceptionListData(myRequestInfo, 2);
                                                        break;
                                                }


                                            }
                                        }

        );
        if (object instanceof OrderBuyerDemList) {//根据传入的参数，看属于哪个返回类型，加载哪个adapter
            list_all= ((OrderBuyerDemList) object).list;
            adapter = new DemOrderAdapter(this, ((OrderBuyerDemList) object).list);
            if (1 == ((OrderBuyerDemList) object).total_page)

            {//如果总页数一共就一页，关闭加载更多功能
                recyclerview.setLoadingMoreEnabled(false);
            }
            recyclerview.setAdapter(adapter);
        } else if (object instanceof OrderBuyerDemFilterList) {//0根据status 字段判断是待付款还是待收货
            list_Filter=((OrderBuyerDemFilterList) object).list;
            if (((OrderBuyerDemFilterList) object).list.get(0).status == 0) {
                adapter_filter_weiFuKuan = new DemOrder_filter_Adapter(this, ((OrderBuyerDemFilterList) object).list);
                if (1 == ((OrderBuyerDemFilterList) object).total_page)

                {//如果总页数一共就一页，关闭加载更多功能
                    recyclerview.setLoadingMoreEnabled(false);
                }
                recyclerview.setAdapter(adapter_filter_weiFuKuan);
            } else if (((OrderBuyerDemFilterList) object).list.get(0).status == 2) {
                adapter_filter_weiShouHuo = new DemOrder_filter_Adapter(this, ((OrderBuyerDemFilterList) object).list);
                if (1 == ((OrderBuyerDemFilterList) object).total_page)

                {//如果总页数一共就一页，关闭加载更多功能
                    recyclerview.setLoadingMoreEnabled(false);
                }
                recyclerview.setAdapter(adapter_filter_weiShouHuo);
            }
        } else if (object instanceof OrderBuyerDemExceptionList) {
            list_Exception= ((OrderBuyerDemExceptionList) object).list;
            adapter_exception = new DemOrder_exception_Adapter(this, ((OrderBuyerDemExceptionList) object).list);
            if (1 == ((OrderBuyerDemExceptionList) object).total_page)

            {//如果总页数一共就一页，关闭加载更多功能
                recyclerview.setLoadingMoreEnabled(false);
            }
            recyclerview.setAdapter(adapter_exception);
        }


    }

    @Override
    public void loadMoreAll(List<OrderBuyerDemList_listItem> list) {
        recyclerview.loadMoreComplete();
        list_all = adapter.getList();
        list_all.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refreshAll(OrderBuyerDemList OrderBuyerDemList) {
        recyclerview.refreshComplete();
        if (1 < OrderBuyerDemList.total_page) {//如果刷新后数据多余一页，加载更多功能启用
            recyclerview.setLoadingMoreEnabled(true);
        }
        list_all = OrderBuyerDemList.list;
        adapter.setList(list_all);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadMoreFilter(List<OrderBuyerDemFilterList_listItem> list) {
        recyclerview.loadMoreComplete();
        if (list.get(0).status == 0) {//未付款
            list_Filter = adapter_filter_weiFuKuan.getList();
        } else if (list.get(0).status == 2) {//未收货
            list_Filter = adapter_filter_weiShouHuo.getList();
        }
        list_Filter.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refreshFilter(OrderBuyerDemFilterList orderBuyerDemFilterList) {
        recyclerview.refreshComplete();
        if (1 < orderBuyerDemFilterList.total_page) {//如果刷新后数据多余一页，加载更多功能启用
            recyclerview.setLoadingMoreEnabled(true);
        }
        list_Filter = orderBuyerDemFilterList.list;
        if (orderBuyerDemFilterList.list.get(0).status == 0) {//未付款
            adapter_filter_weiFuKuan.setList(list_Filter);
            adapter_filter_weiFuKuan.notifyDataSetChanged();
        } else if (orderBuyerDemFilterList.list.get(0).status == 2) {//未收货
            adapter_filter_weiShouHuo.setList(list_Filter);
            adapter_filter_weiShouHuo.notifyDataSetChanged();
        }
    }

    @Override
    public void loadMoreException(List<OrderBuyerDemExceptionList_listItem> list) {
        recyclerview.loadMoreComplete();
        list_Exception = adapter_exception.getList();
        list_Exception.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refreshException(OrderBuyerDemExceptionList orderBuyerDemExceptionList) {
        recyclerview.refreshComplete();
        if (1 < orderBuyerDemExceptionList.total_page) {//如果刷新后数据多余一页，加载更多功能启用
            recyclerview.setLoadingMoreEnabled(true);
        }
        list_Exception = orderBuyerDemExceptionList.list;
        adapter_exception.setList(list_Exception);
        adapter_exception.notifyDataSetChanged();
    }

    @Override
    public void closeOrder(String oid, int position) {
        this.closePosition = position;
        Request_orderBuyerClose request_orderBuyerClose = new Request_orderBuyerClose();
        request_orderBuyerClose.oid = oid;
        myRequestInfo.req = request_orderBuyerClose;
        presenter.getorderBuyerDemClose(myRequestInfo);
    }

    @Override
    public void refreshOrder() {
        switch (nowPosition) {
            case 0:
                List<OrderBuyerDemList_listItem> list = adapter.getList();
                list.get(closePosition).status = 4;
                adapter.setList(list);
                adapter.notifyDataSetChanged();
                break;
            case 1:
                List<OrderBuyerDemFilterList_listItem> list_filter_weiFuKuan = adapter_filter_weiFuKuan.getList();
                list_filter_weiFuKuan.get(closePosition).status = 4;
                adapter_filter_weiFuKuan.setList(list_filter_weiFuKuan);
                adapter_filter_weiFuKuan.notifyDataSetChanged();
                break;
            case 2:
                List<OrderBuyerDemFilterList_listItem> list_filter_weiShouHuo = adapter_filter_weiShouHuo.getList();
                list_filter_weiShouHuo.get(closePosition).status = 4;
                adapter_filter_weiShouHuo.setList(list_filter_weiShouHuo);
                adapter_filter_weiShouHuo.notifyDataSetChanged();
                break;
            case 3:
                List<OrderBuyerDemExceptionList_listItem> list_exception = adapter_exception.getList();
                list_exception.get(closePosition).status = 4;
                adapter_exception.setList(list_exception);
                adapter_exception.notifyDataSetChanged();
                break;
        }
    }
    @Override
    public void orderReceive(int position) {
        Intent intent=new Intent(this,OrderReceiveActivity.class);
        switch (nowPosition) {
            case 0://全部订单
                list_all=adapter.getList();
                intent.putExtra("id",list_all.get(position).number);
                intent.putExtra("creat_time",list_all.get(position).creat_time);
                intent.putExtra("seller",list_all.get(position).seller);
                intent.putExtra("total_price",list_all.get(position).total_price);
                intent.putExtra("number",String.valueOf(list_all.get(position).products.size()));
                break;
            case 2://待收货订单
                list_Filter=adapter_filter_weiShouHuo.getList();
                intent.putExtra("id",list_Filter.get(position).number);
                intent.putExtra("creat_time",list_Filter.get(position).creat_time);
                intent.putExtra("seller",list_Filter.get(position).seller);
                intent.putExtra("total_price",list_Filter.get(position).total_price);
                intent.putExtra("number",String.valueOf(list_Filter.get(position).products.size()));
                break;
            case 3://异常订单
                list_Exception=adapter_exception.getList();
                intent.putExtra("id",list_Exception.get(position).number);
                intent.putExtra("creat_time",list_Exception.get(position).creat_time);
                intent.putExtra("seller",list_Exception.get(position).seller);
                intent.putExtra("total_price",list_Exception.get(position).total_price);
                intent.putExtra("number",String.valueOf(list_Exception.get(position).products.size()));
                break;
        }
        startActivityForResult(intent,0);

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
    @Override
    protected void onResume() {
        super.onResume();
        if(!isFirst) {
            switch (nowPosition) {
                case 0://全部订单
                    request_order_buyer_dem_list.page_no = page_no;
                    myRequestInfo.req = request_order_buyer_dem_list;
                    presenter.getAllDemOrderData(myRequestInfo, 1);
                    break;
                case 1://待付款订单
                    request_order_buyer_dem_filter_list.page_no = page_no;
                    request_order_buyer_dem_filter_list.order_status = 0;
                    myRequestInfo.req = request_order_buyer_dem_filter_list;
                    presenter.getorderBuyerDemFilterListData(myRequestInfo, 1);
                    break;
                case 2://待收货订单
                    request_order_buyer_dem_filter_list.page_no = page_no;
                    request_order_buyer_dem_filter_list.order_status = 2;
                    myRequestInfo.req = request_order_buyer_dem_filter_list;
                    presenter.getorderBuyerDemFilterListData(myRequestInfo, 1);
                    break;
                case 3://异常订单
                    request_order_buyer_dem_exception_list.page_no = page_no;
                    myRequestInfo.req = request_order_buyer_dem_exception_list;
                    presenter.getorderBuyerDemExceptionListData(myRequestInfo, 1);
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
