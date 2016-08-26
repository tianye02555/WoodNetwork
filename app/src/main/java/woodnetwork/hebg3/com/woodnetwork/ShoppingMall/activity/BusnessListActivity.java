package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter.BusnessListAdapter;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract.BusnessListContrac;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment.DividerItemDecoration;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.presenter.BusnessListPresenter;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;

public class BusnessListActivity extends AppCompatActivity implements BusnessListContrac.BusnessListViewInterface{

    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_busness_list_recyclerview)
    RecyclerView recyclerview;
    private BusnessListContrac.BusnessListPresenterInterface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busness_list);
        ButterKnife.bind(this);
        imageTitleRight.setVisibility(View.GONE);
        textTitle.setText("商家信息");
        new BusnessListPresenter(this);
        presenter.start();
    }

    @OnClick(R.id.imge_title_left)
    public void onClick() {
        finish();
    }

    @Override
    public void showBusnessListData(List<BusnessInfo> list) {
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        BusnessListAdapter adapter=new BusnessListAdapter(this,list);
        recyclerview.setAdapter(adapter);

    }

    @Override
    public void setPresenter(BusnessListContrac.BusnessListPresenterInterface presenter) {
        if(null!=presenter){
            this.presenter=presenter;
        }

    }

    @Override
    public void showProgress( ) {
        ProgressUtils.show(this, "请稍后……");
    }

    @Override
    public void closeProgress() {
        ProgressUtils.hide();
    }

    @Override
    public void showfailMessage(String string) {
        CommonUtils.showToast(this,string);
    }
}
