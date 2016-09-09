package woodnetwork.hebg3.com.woodnetwork.WoDe.activity;

import android.content.pm.ActivityInfo;
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
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_article_web_info;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.ReportsInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.ReportsContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.presenter.ReportsPresenter;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.contract.ZiXunXiangQingContrack;

/**
 * 报表页
 */
public class ReportsActivity extends AppCompatActivity implements ReportsContract.ReportsViewInterface{

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_reports_webview)
    WebView webView;
    private ReportsContract.ReportsPresenterInterface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        CommonUtils.addActivity(this);
        ButterKnife.bind(this);
        textTitle.setText("统计查询");
        imageTitleRight.setVisibility(View.GONE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        new ReportsPresenter(this);
        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(this);
        Request_getAttribute request_getAttribute = new Request_getAttribute();
        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");

        MyRequestInfo myRequestInfo = new MyRequestInfo();
        myRequestInfo.req =new Object() ;
        myRequestInfo.req_meta = request_getAttribute;
        presenter.getReportsURL(myRequestInfo);
    }

    @OnClick(R.id.imge_title_left)
    public void onClick() {
        finish();
    }

    @Override
    public void showWebViewUri(ReportsInfo reportsInfo) {
        webView.loadUrl("http://baidu.com");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl("http://baidu.com");
                return true;
            }
        });
    }

    @Override
    public void setPresenter(ReportsContract.ReportsPresenterInterface presenter) {
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
