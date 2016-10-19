package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_area_list;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.AreaList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.AreaList_listItem;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract.ChooseAddressContract;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.presenter.ChooseAddressPresenter;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;

public class ChooseAddressActivity extends AppCompatActivity implements ChooseAddressContract.ChooseAddressViewInterface {

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_choose_address_btn_queding)
    Button queDing;
    @Bind(R.id.activity_choose_address)
    RelativeLayout rootLayout;
    private ChooseAddressContract.ChooseAddressPresenterInterface presenter;
    /**
     * 当前控件的id
     */
    private int nowView;
    /**
     * new 出来的spinner 的id
     */
    private int spinnerID = 0;
    /**
     * 页面回调的数据
     */
    private Intent intent = new Intent();
    /**
     * 存储生成的spinner key 为spinner生成的id,value 是否有子spinner
     */
    private ArrayList<Integer> spinnerList = new ArrayList<Integer>();
    /**
     * 存储选择的地址
     */
    private ConcurrentHashMap<Integer, String> addressMap = new ConcurrentHashMap<Integer, String>();
    private AreaList areaListNew;

    private ArrayList<String> stringArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_address);
        CommonUtils.addActivity(this);
        ButterKnife.bind(this);
        textTitle.setText("地址选择");
        imageTitleRight.setVisibility(View.GONE);
        new ChooseAddressPresenter(this);

        nowView = R.id.activity_choose_address_text_shouhuodizhi;
        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(ChooseAddressActivity.this);
        Request_getAttribute request_getAttribute = new Request_getAttribute();
        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");

        Request_area_list request_area_list = new Request_area_list();
        request_area_list.pid = "1";//areaList.list.get(i).id

        MyRequestInfo myRequestInfo = new MyRequestInfo();
        myRequestInfo.req = request_area_list;
        myRequestInfo.req_meta = request_getAttribute;
        presenter.getAreaData(myRequestInfo);

    }

    @OnClick({R.id.imge_title_left, R.id.activity_choose_address_btn_queding})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imge_title_left:
                finish();
                break;
            case R.id.activity_choose_address_btn_queding:
                String address = new String();
                //循环获取最终选择的地址
                for (int i = 0; i < spinnerList.size(); i++) {
                    //如果最后一项没有选择地区就不在拼接直接退出
                    if (null == addressMap.get(spinnerList.get(i))) {
                        break;
                    }
                    address = address + addressMap.get(spinnerList.get(i));
                }
                intent.putExtra("address", address);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }

    @Override
    public void showNewSpinner(final AreaList areaList) {
        if (0 == areaList.list.size()) {
            return;
        }
        final AreaList areaListNew = new AreaList();
        AreaList_listItem areaList_listItem = new AreaList_listItem();
        areaList_listItem.id = "1";
        areaList_listItem.name = "请选择地址";
        ArrayList<AreaList_listItem> list = new ArrayList<AreaList_listItem>();
        list.add(areaList_listItem);
        list.addAll(areaList.list);
        areaListNew.list = list;//拼接集合（在第一个位置加入了“选择地址”）
        //获取服务器数据中的value
        stringArrayList = new ArrayList<String>();
        for (AreaList_listItem value : areaListNew.list) {
            stringArrayList.add(value.name);
        }
//        //第一个spinner或者当前控件灭有子控件，生成新spinner
//        if (null==spinnerMap.get(spinnerID)||!spinnerMap.get(spinnerID)) {
//            //如果第一个spinner，下一次需要再判断 不赋值；
//            if(null!=spinnerMap.get(spinnerID)) {
//                spinnerMap.put(spinnerID, true);
//            }
        final Spinner spinner = new Spinner(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        layoutParams.addRule(RelativeLayout.BELOW, nowView);
        layoutParams.topMargin = (int) getResources().getDimension(R.dimen.distance_normal);
        spinner.setLayoutParams(layoutParams);
        spinnerID++;
        spinner.setId(spinnerID);
        spinnerList.add(spinnerID);
        rootLayout.addView(spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stringArrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (0 == i) {
                    return;
                }
                boolean isDelet = false;
                for (int j = 0; j < spinnerList.size(); j++) {
                    if (isDelet) {
                        Spinner spinner = (Spinner) ChooseAddressActivity.this.findViewById(spinnerList.get(j));
                        rootLayout.removeView(spinner);
                        addressMap.remove(spinner.getId());
                        spinnerList.remove(spinnerList.get(j));
                        j -= 1;//spinnerList的size 是变化的
                    }
                    if (spinnerList.get(j) == spinner.getId()) {
                        nowView = spinner.getId();
                        isDelet = true;
                    }
                }
                intent.putExtra("addressID", areaListNew.list.get(i - 1).id);//获取最后一个spinner的选中的项的id
                addressMap.put(spinner.getId(), areaListNew.list.get(i).name);
                SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(ChooseAddressActivity.this);
                Request_getAttribute request_getAttribute = new Request_getAttribute();
                request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");

                Request_area_list request_area_list = new Request_area_list();
                request_area_list.pid = areaList.list.get(i - 1).id;

                MyRequestInfo myRequestInfo = new MyRequestInfo();
                myRequestInfo.req = request_area_list;
                myRequestInfo.req_meta = request_getAttribute;
                presenter.getAreaData(myRequestInfo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        nowView = spinner.getId();
//        }

    }

    @Override
    public void setPresenter(ChooseAddressContract.ChooseAddressPresenterInterface presenter) {
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

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    public static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }
}
