package woodnetwork.hebg3.com.woodnetwork.ZiXun.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_CategoryList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_shopcarList;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.adapter.DividerGridItemDecoration;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.adapter.ZiXunHomeAdapter;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.BannerList;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.CategoryList;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.contract.ZiXunHomeContract;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.presenter.ZiXunHomePresenter;
import woodnetwork.hebg3.com.woodnetwork.view.MyGallery;

public class ZiXunHomeFragment extends Fragment implements ZiXunHomeContract.ZiXunHomeViewInterface {

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.home_customgallery)
    MyGallery gallery;
    @Bind(R.id.home_ll_dot_container)
    LinearLayout DotContainer;
    @Bind(R.id.home_rl_tp)
    RelativeLayout homeRlTp;
    @Bind(R.id.fragment_zi_xun_recyclerview)
    RecyclerView recyclerview_gridview;
    private ZiXunHomeContract.ZiXunHomePresenterInterface presenter;
    private ZiXunHomeAdapter ziXunHomeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zi_xun_home, container, false);
        ButterKnife.bind(this, view);

        imgeTitleLeft.setVisibility(View.GONE);
        textTitle.setText("咨讯");
        imageTitleRight.setVisibility(View.GONE);

        new ZiXunHomePresenter(this);

        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(getActivity());
        Request_getAttribute request_getAttribute = new Request_getAttribute();
        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");

        Request_CategoryList request_categoryList=new Request_CategoryList();
        request_categoryList.pid="1";

        MyRequestInfo myRequestInfo = new MyRequestInfo();
        myRequestInfo.req = new Object();
        myRequestInfo.req_meta = request_getAttribute;
        presenter.getMyGalleryData(myRequestInfo);

        myRequestInfo.req = request_categoryList;
         presenter.getCategoryListData(myRequestInfo);

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setMyGalleryInfo(BannerList bannerList) {
        // Inflate the layout for this fragment
        // 设置轮播图的 宽高比 为2:1 宽为屏幕宽度
        WindowManager manager = getActivity().getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
//        RelativeLayout.LayoutParams lay = new RelativeLayout.LayoutParams(
//                outMetrics.widthPixels, outMetrics.widthPixels * 2 / 3);
//        lay.addRule(RelativeLayout.BELOW, R.id.includ_title);
//        homeRlTp.setLayoutParams(lay);
        String[] pictures = new String[3];
        pictures[0] = "http://img5.imgtn.bdimg.com/it/u=3279813050,4113215971&fm=206&gp=0.jpg";
        pictures[1] = "http://img0.imgtn.bdimg.com/it/u=638420455,3521255219&fm=206&gp=0.jpg";
        pictures[2] = "http://img1.imgtn.bdimg.com/it/u=766966808,4047206931&fm=206&gp=0.jpg";
        gallery.start(getActivity(), pictures, 3000,
                DotContainer, R.drawable.dot_onn,
                R.drawable.dot_offf);
    }

    @Override
    public void setCategoryListInfo(CategoryList categoryList) {
        ziXunHomeAdapter = new ZiXunHomeAdapter(getActivity(), categoryList.list);
        recyclerview_gridview.setLayoutManager(new GridLayoutManager(getActivity(), 3));
//        recyclerview_gridview.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        recyclerview_gridview.setAdapter(ziXunHomeAdapter);
    }

    @Override
    public void setPresenter(ZiXunHomeContract.ZiXunHomePresenterInterface presenter) {
        if (null != presenter) {
            this.presenter = presenter;
        }
    }

    @Override
    public void showProgress() {
        ProgressUtils.show(getActivity(), getResources().getString(R.string.qingshaohou));
    }

    @Override
    public void closeProgress() {
        ProgressUtils.hide();
    }

    @Override
    public void showMessage(String string) {
        CommonUtils.showToast(getActivity(), string);
    }
}
