package woodnetwork.hebg3.com.woodnetwork.WoDe.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_demandBuyerList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment.DividerItemDecoration;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.WoDe.adapter.DemanBuyerListAdapter;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.DemandBuyerList;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.AdviceContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.DemanBuyerListContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.presenter.DemanBuyerListPresenter;

public class DemandBuyerListActivity extends AppCompatActivity implements DemanBuyerListContract.DemanBuyerListViewInterface {

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_demand_buyer_list_recyclerView)
    RecyclerView recyclerView;
    private DemanBuyerListContract.DemanBuyerListPresenterInterface presenter;
    private DemanBuyerListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demand_buyer_list);
        CommonUtils.addActivity(this);
        ButterKnife.bind(this);

        textTitle.setText("我的求购信息");
        imageTitleRight.setVisibility(View.GONE);

        new DemanBuyerListPresenter(this);

        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(this);
        Request_getAttribute request_getAttribute = new Request_getAttribute();
        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");

        Request_demandBuyerList request_demandBuyerList = new Request_demandBuyerList();
        request_demandBuyerList.page_no = 1;
        request_demandBuyerList.page_size = 10;

        MyRequestInfo myRequestInfo = new MyRequestInfo();
        myRequestInfo.req = request_demandBuyerList;
        myRequestInfo.req_meta = request_getAttribute;
        presenter.getDemanBuyerListData(myRequestInfo);
    }

    @OnClick(R.id.imge_title_left)
    public void onClick() {
        finish();
    }

    @Override
    public void setDemanBuyerListInfo(DemandBuyerList demanBuyerList) {
        adapter = new DemanBuyerListAdapter(this, demanBuyerList.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, 2));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setPresenter(DemanBuyerListContract.DemanBuyerListPresenterInterface presenter) {
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
