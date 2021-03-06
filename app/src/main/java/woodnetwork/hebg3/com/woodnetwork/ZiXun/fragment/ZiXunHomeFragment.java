package woodnetwork.hebg3.com.woodnetwork.ZiXun.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.adapter.ZiXunHomeAdapter;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.BannerList;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.CategoryList;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.contract.ZiXunHomeContract;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.presenter.ZiXunHomePresenter;
import woodnetwork.hebg3.com.woodnetwork.net.Const;
import woodnetwork.hebg3.com.woodnetwork.view.MyGallery;

public class ZiXunHomeFragment extends Fragment implements ZiXunHomeContract.ZiXunHomeViewInterface ,View.OnTouchListener{

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.text_new)
    TextView textNews;
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
    private String[] title;//轮播图下方的文字数组

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zi_xun_home, container, false);
        view.setOnTouchListener(this);
        ButterKnife.bind(this, view);

        imgeTitleLeft.setVisibility(View.GONE);
        textTitle.setText("咨讯");
        imageTitleRight.setVisibility(View.GONE);

        new ZiXunHomePresenter(this);

        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(getActivity());
        Request_getAttribute request_getAttribute = new Request_getAttribute();
        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");

        Request_CategoryList request_categoryList = new Request_CategoryList();
        request_categoryList.pid = "1";

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
    public void setMyGalleryInfo(final BannerList bannerList) {
        title = new String[bannerList.list.size()];
        WindowManager manager = getActivity().getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        String[] pictures = new String[bannerList.list.size()];
        for (int i = 0; i < bannerList.list.size(); i++) {
            pictures[i] = Const.PICTURE_LUNBOTU + bannerList.list.get(i).img;
            title[i] = bannerList.list.get(i).title;
        }
        gallery.start(getActivity(), pictures, 3000,
                DotContainer, R.drawable.dot_onn,
                R.drawable.dot_offf, title, textNews);
    }

    @Override
    public void setCategoryListInfo(CategoryList categoryList) {
        ziXunHomeAdapter = new ZiXunHomeAdapter(getActivity(), categoryList.list);
        recyclerview_gridview.setLayoutManager(new GridLayoutManager(getActivity(), 3));
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

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }
}
