package woodnetwork.hebg3.com.woodnetwork.ZiXun.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_CategoryList;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_article_web_info;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.ArticleWebInfo;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.BannerList;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.bean.CategoryList;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.contract.ZiXunHomeContract;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.contract.ZiXunXiangQingContrack;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.presenter.ZiXunXiangQingPresenter;

public class ZiXunXiangQingActivity extends AppCompatActivity implements ZiXunXiangQingContrack.ZiXunXiangQingViewInterface{

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_zi_xun_xiang_qing_webview)
    WebView webview;
    private ZiXunXiangQingContrack.ZiXunXiangQingPresenterInterface presenter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zi_xun_xiang_qing);
        CommonUtils.addActivity(this);
        ButterKnife.bind(this);


        textTitle.setText("资讯");
        imageTitleRight.setVisibility(View.GONE);
        webview.loadUrl("http://baidu.com");//getIntent().getStringExtra("url");
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl("http://baidu.com");
                return true;
            }
        });
    }

    @OnClick(R.id.imge_title_left)
    public void onClick() {
        finish();
    }


    @Override
    public void setPresenter(ZiXunXiangQingContrack.ZiXunXiangQingPresenterInterface presenter) {
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

    @Override
    public void showWebViewUri(ArticleWebInfo articleWebInfo) {

    }
}
