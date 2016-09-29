package woodnetwork.hebg3.com.woodnetwork.ZiXun.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_busnessList;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter.BusnessListAdapter;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.BusnessListInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment.DividerItemDecoration;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.adapter.ZiXunListAdapter;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_CategoryList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_article_list;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.ArticleList;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.ArticleList_listItem;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.CategoryList;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.contract.ZiXunListContract;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.presenter.ZiXunListPresenter;

public class ZiXunListActivity extends AppCompatActivity implements ZiXunListContract.ZiXunListViewInterface {

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_zi_xun_horizontalScrollView)
    HorizontalScrollView horizontalScrollView;
    @Bind(R.id.activity_zi_xun_recyclerView)
    XRecyclerView recyclerView;
    @Bind(R.id.radiogroup)
    RadioGroup radiogroup;
    private ZiXunListContract.ZiXunListPresenterInterface presenter;
    private ZiXunListAdapter ziXunListAdapter;
    private int page_no = 1;
    private Request_article_list request_article_list;
    private MyRequestInfo myRequestInfo;
    private List<ArticleList_listItem> list;
    private String pid;//父级分类编号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zi_xun_list);
        CommonUtils.addActivity(this);
        ButterKnife.bind(this);

        imageTitleRight.setVisibility(View.GONE);
        if (null != getIntent()) {
            textTitle.setText(getIntent().getStringExtra("title"));
            pid=getIntent().getStringExtra("pid");
        }


        new ZiXunListPresenter(this);

        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(this);
        Request_getAttribute request_getAttribute = new Request_getAttribute();
        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");

        Request_CategoryList request_CategoryList = new Request_CategoryList();
        request_CategoryList.pid = pid;

        myRequestInfo = new MyRequestInfo();
        myRequestInfo.req = request_CategoryList;
        myRequestInfo.req_meta = request_getAttribute;
        presenter.getCategoryListData(myRequestInfo);
    }

    @OnClick(R.id.imge_title_left)
    public void onClick() {
        finish();
    }

    @Override
    public void newTitleText(final CategoryList categoryList) {
        RadioButton rb;
        for (int i = 0; i < categoryList.list.size(); i++) {
            rb = (RadioButton) LayoutInflater.from(this).inflate(R.layout.radiobutton, null);
            rb.setTag(categoryList.list.get(i).id);
            if (i == 0) {
                rb.setText("    " + categoryList.list.get(i).name);
            } else {
                rb.setText("                 " + categoryList.list.get(i).name);
            }
            rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        request_article_list = new Request_article_list();
                        request_article_list.cid =(String)compoundButton.getTag();
                        request_article_list.page_no = 1;
                        request_article_list.page_size = 10;
                        myRequestInfo.req = request_article_list;
                        presenter.getArticleListData(myRequestInfo, 0);
                    }
                }
            });

            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            radiogroup.addView(rb, i, layoutParams);

        }
        ((RadioButton) (radiogroup.getChildAt(0))).setChecked(true);
        radiogroup.invalidate();
    }

    @Override
    public void setCategoryListInfo(CategoryList categoryList) {
        newTitleText(categoryList);
    }

    @Override
    public void setArticleListInfo(final ArticleList articleList) {
        ziXunListAdapter = new ZiXunListAdapter(this, articleList.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page_no = 1;
                request_article_list.page_no = page_no;
                myRequestInfo.req = request_article_list;
                presenter.getArticleListData(myRequestInfo, 1);
            }

            @Override
            public void onLoadMore() {
                page_no++;
                if (page_no > articleList.total_page) {//判断是否为最后一页
                    recyclerView.setIsnomore(true);//底部显示没有更多数据
                }
                request_article_list.page_no = page_no;
                myRequestInfo.req = request_article_list;
                presenter.getArticleListData(myRequestInfo, 2);


            }
        });
        if (1 == articleList.total_page) {//如果总页数一共就一页，关闭加载更多功能
            recyclerView.setLoadingMoreEnabled(false);
        }
        recyclerView.setAdapter(ziXunListAdapter);
    }

    @Override
    public void loadMore(List<ArticleList_listItem> list) {

        recyclerView.loadMoreComplete();
        list = ziXunListAdapter.getArticleList();
        list.addAll(list);
        ziXunListAdapter.notifyDataSetChanged();

    }

    @Override
    public void refresh(ArticleList articleList) {
        recyclerView.refreshComplete();
        if (1 < articleList.total_page) {//如果刷新后数据多余一页，加载更多功能启用
            recyclerView.setLoadingMoreEnabled(true);
        }
        list = articleList.list;
        ziXunListAdapter.setArticleList(list);
        ziXunListAdapter.notifyDataSetChanged();

    }

    @Override
    public void setPresenter(ZiXunListContract.ZiXunListPresenterInterface presenter) {
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
