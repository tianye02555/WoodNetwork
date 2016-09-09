package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter.BusnessListAdapter;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract.BusnessListContrac;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.presenter.BusnessListPresenter;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;

public class BusnessListFragment extends Fragment implements BusnessListContrac.BusnessListViewInterface{

    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_busness_list_recyclerview)
    RecyclerView recyclerview;
    private BusnessListContrac.BusnessListPresenterInterface presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_busness_list, container, false);
        ButterKnife.bind(this,view);
        imageTitleRight.setVisibility(View.GONE);
        textTitle.setText("商家信息");
        new BusnessListPresenter(this);
        presenter.start();
        return view;
    }

    @Override
    public void showBusnessListData(List<BusnessInfo> list) {
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL,5));
        BusnessListAdapter adapter=new BusnessListAdapter(getActivity(),list);
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
        ProgressUtils.show(getActivity(), "请稍后……");
    }

    @Override
    public void closeProgress() {
        ProgressUtils.hide();
    }

    @Override
    public void showMessage(String string) {
        CommonUtils.showToast(getActivity(),string);
    }


}
