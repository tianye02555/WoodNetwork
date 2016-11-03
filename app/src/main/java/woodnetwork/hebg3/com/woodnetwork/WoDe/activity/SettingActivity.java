package woodnetwork.hebg3.com.woodnetwork.WoDe.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_versionInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.UpdateManager;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.VersionInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.SettingContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.presenter.SettingPresenter;
import woodnetwork.hebg3.com.woodnetwork.net.Const;
import woodnetwork.hebg3.com.woodnetwork.sysfunction.activity.LoginActivity;

/**
 * 设置页
 */
public class SettingActivity extends AppCompatActivity implements SettingContract.SettingViewInterface {

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_setting_text_qingchuhuancun)
    TextView qingChuHuanCun;
    @Bind(R.id.activity_setting_text_yijianfankui)
    TextView yiJianFanKui;
    @Bind(R.id.activity_setting_text_guanyuwomen)
    TextView guanYuWoMen;
    @Bind(R.id.activity_setting_btn_zhuxiao)
    Button zhuXiao;
    @Bind(R.id.activity_setting_rel)
    RelativeLayout jianChaGengXin;
    @Bind(R.id.activity_my_information)
    LinearLayout activityMyInformation;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.activity_setting_text_version)
    TextView version;
    @Bind(R.id.activity_setting_checkbox_wifi)
    CheckBox checkBoxWIFI;
    private SettingContract.SettingPresenterInterface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        CommonUtils.addActivity(this);
        ButterKnife.bind(this);

        textTitle.setText("设置");
        imageTitleRight.setVisibility(View.GONE);
        version.setText(CommonUtils.getVersionName(this));

        checkBoxWIFI.setChecked(CommonUtils.isOnlyWIFIDownLoadPic(this));
        checkBoxWIFI.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharePreferencesUtils sharePreferences = SharePreferencesUtils.getSharePreferencesUtils(SettingActivity.this);
                sharePreferences.saveData("isdown", b);
            }
        });
        new SettingPresenter(this);
    }

    @OnClick({R.id.imge_title_left, R.id.activity_setting_text_qingchuhuancun, R.id.activity_setting_text_yijianfankui, R.id.activity_setting_text_guanyuwomen, R.id.activity_setting_btn_zhuxiao, R.id.activity_setting_rel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imge_title_left:
                finish();
                break;
            case R.id.activity_setting_text_qingchuhuancun:
                //fresco 手动清除缓存   一般fresco不需要手动清除  当后台退出app时  自己会清理
                ImagePipeline imagePipeline = Fresco.getImagePipeline();
                imagePipeline.clearCaches();
                CommonUtils.showToast(this, "清理成功");
                break;
            case R.id.activity_setting_rel:
                SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(this);
                Request_getAttribute request_getAttribute = new Request_getAttribute();
                request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");

                Request_versionInfo request_versionInfo = new Request_versionInfo();
                if (0 == CommonUtils.getVersionCode(SettingActivity.this)) {
                    CommonUtils.showToast(SettingActivity.this, "获取版本号失败");
                    return;
                } else {
                    request_versionInfo.os_type = CommonUtils.getVersionCode(SettingActivity.this);
                }


                MyRequestInfo myRequestInfo = new MyRequestInfo();
                myRequestInfo.req = request_versionInfo;
                myRequestInfo.req_meta = request_getAttribute;
                presenter.getCheckUpdateData(myRequestInfo);
                break;
            case R.id.activity_setting_text_yijianfankui:
                startActivity(new Intent(this, AdviceActivity.class));
                break;
            case R.id.activity_setting_text_guanyuwomen:
                startActivity(new Intent(this, AboutMeActivity.class));
                break;
            case R.id.activity_setting_btn_zhuxiao:
                new AlertDialog.Builder(this).setMessage("确认要注销吗？").setTitle("提示").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharePreferencesUtils sharePreferences = SharePreferencesUtils.getSharePreferencesUtils(SettingActivity.this);
                        sharePreferences.clearData();
                        startActivity(new Intent(SettingActivity.this, LoginActivity.class));

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();


                break;
        }
    }

    @Override
    public void setCheckUpdateInfo(VersionInfo versionInfo) {
        UpdateManager manager = new UpdateManager(
                SettingActivity.this, String.valueOf(versionInfo.number), Const.PICTURE_LUNBOTU+versionInfo.url,
                versionInfo.code, 1, "0");
        manager.checkUpdate();
    }

    @Override
    public void setPresenter(SettingContract.SettingPresenterInterface presenter) {
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
