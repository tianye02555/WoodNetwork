package woodnetwork.hebg3.com.testapplication.mvpDemo.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

//import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
//import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import rx.Subscriber;
import woodnetwork.hebg3.com.testapplication.HttpMethods;
import woodnetwork.hebg3.com.testapplication.LoginButtonsActivity;
import woodnetwork.hebg3.com.testapplication.MovieEntity;
import woodnetwork.hebg3.com.testapplication.R;
import woodnetwork.hebg3.com.testapplication.Rxbus.RxBus;
import woodnetwork.hebg3.com.testapplication.mvpDemo.BaseActivity;
import woodnetwork.hebg3.com.testapplication.mvpDemo.bean.LoginBean;
import woodnetwork.hebg3.com.testapplication.mvpDemo.contract.LoginContract;
import woodnetwork.hebg3.com.testapplication.mvpDemo.presenter.LoginPresenter;
import woodnetwork.hebg3.com.testapplication.wedgit.ShowNetPicture.TestView;


public class LoginActivity extends BaseActivity<LoginContract.LoginViewInterface, LoginPresenter> implements LoginContract.LoginViewInterface {

    @Bind(R.id.content)
    ImageView content;
    @Bind(R.id.get)
    Button get;
    @Bind(R.id.activity_login_buttons)
    RelativeLayout activityLoginButtons;
    @Bind(R.id.switchView)
    Switch switchView;
    @Bind(R.id.testview)
    TestView testview;
    //    @Bind(R.id.custom_videoplayer_standard)
//    JCVideoPlayerStandard customVideoplayerStandard;
    private Subscriber<List<MovieEntity>> subscriber;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_buttons);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
//        switchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//
//                Toast.makeText(LoginActivity.this, String.valueOf(b), Toast.LENGTH_LONG).show();
//
//            }
//        });
//        Picasso.with(this).load("http://photo.enterdesk.com/2010-10-24/enterdesk.com-3B11711A460036C51C19F87E7064FE9D.jpg").into(content);
//        customVideoplayerStandard.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4", JCVideoPlayer.SCREEN_LAYOUT_LIST, "ffa");
//        Picasso.with(this).load("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640").into(customVideoplayerStandard.thumbImageView);

    }

//    @Override
//    public void onBackPressed() {
//        if (JCVideoPlayer.backPress()) {
//            return;
//        }
//        super.onBackPressed();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        JCVideoPlayer.releaseAllVideos();
//    }

    @OnClick(R.id.get)
    public void onClick() {
//        showPopuoWindow();
//        mPresenter.getTop250(0,10);
        LoginBean loginBean=new LoginBean();
        loginBean.nihao="hello,RxBus";
        RxBus.get().post("hello",loginBean);
//        File file = new File("/storage/sdcard1/360OS/My Records", "中国移动.amr");
//        if (!file.exists()) {
//            try {
//                //在指定的文件夹中创建文件
//                file.createNewFile();
//            } catch (Exception e) {
//            }
//        }
//        List<File> files=new ArrayList<File>();
//        files.add(file);
//        mPresenter.uploadFiles(files);



//        Intent intent = new Intent(LoginActivity.this, LoginButtonsActivity.class);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, content, "aaa").toBundle());
//        } else {
//            startActivity(intent);
//        }
//        overridePendingTransition(R.anim.come_right,R.anim.leave_left);
//    }

//    public void getContent() {
//        subscriber = new Subscriber<List<MovieEntity>>() {//访问成功后的操作
//            @Override
//            public void onCompleted() {
////                Toast.makeText(LoginActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(Throwable e) {
////                Toast.makeText(LoginActivity.this, "Get Top Movie onError", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNext(List<MovieEntity> movieEntity) {
////                Toast.makeText(LoginActivity.this, movieEntity.toString(), Toast.LENGTH_SHORT).show();
//            }
//        };
//        HttpMethods.getInstance().getMovie(subscriber, 0, 10);
    }

    @Override
    public void showData() {

    }

    @Override
    protected LoginPresenter createPresenter() {

        return new LoginPresenter(this);
    }

    private void showPopuoWindow() {
        View parent = LayoutInflater.from(this).inflate(R.layout.activity_login_buttons, null);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow, null);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setAnimationStyle(R.style.vvv);
        popupWindow.showAsDropDown(content, 100, 100);


    }
}
