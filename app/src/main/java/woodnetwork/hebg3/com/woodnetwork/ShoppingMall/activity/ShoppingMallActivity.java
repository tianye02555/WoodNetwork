package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.QiuGou.fragment.QiuGouHomeFragment;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment.BusnessListFragment;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment.ShoppingMallFragment;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.WoDe.fragment.MyFragment;
import woodnetwork.hebg3.com.woodnetwork.ZiXun.fragment.ZiXunHomeFragment;
import woodnetwork.hebg3.com.woodnetwork.sysfunction.activity.LoginActivity;

public class ShoppingMallActivity extends FragmentActivity {


    @Bind(R.id.activity_shopping_mall_rab_shangcheng)
    RadioButton Shangcheng;
    @Bind(R.id.activity_shopping_mall_rab_qiugou)
    RadioButton Qiugou;
    @Bind(R.id.activity_shopping_mall_rab_zixun)
    RadioButton Zixun;
    @Bind(R.id.activity_shopping_mall_rab_wode)
    RadioButton Wode;
    @Bind(R.id.activity_shopping_mall_rab_shangjia)
    RadioButton shangjia;
    private MyFragment myFragment;
    private ZiXunHomeFragment ziXunHomeFragment;
    private QiuGouHomeFragment qiuGouHomeFragment;
    private BusnessListFragment busnessListFragment;
    private ShoppingMallFragment shoppingMallFragment;
    private SharePreferencesUtils sharePreferencesUtils;
    /**
     * MyFragment的tab
     */
    private String TAB_MY = "myFragment";
    /**
     * ZiXunHomeFragment的tab
     */
    private String TAB_ZiXunHome = "ziXunHomeFragment";
    /**
     * QiuGouHomeFragment的tab
     */
    private String TAB_QiuGouHome = "qiuGouHomeFragment";
    /**
     * BusnessListFragment的tab
     */
    private String TAB_BusnessList = "busnessListFragment";
    /**
     * ShoppingMallFragment的tab
     */
    private String TAB_ShoppingMall = "shoppingMallFragment";

    /**
     * fragment的集合
     */
    private List<Fragment> list_fragment;
    /**
     * fragment管理者中存储的所有的fragment的集合
     */
    private List<Fragment> list_fragmentManager;
    /**
     * fragment管理
     */
    private FragmentManager fragmentManager;
    /**
     * fragment事务管理
     */
    private FragmentTransaction transaction;
    /**
     * 记录当前fragment
     */
    private int nowPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_mall);
        CommonUtils.addActivity(this);
        ButterKnife.bind(this);
        sharePreferencesUtils=SharePreferencesUtils.getSharePreferencesUtils(this);
        if ("null".equals(sharePreferencesUtils.getData("userid", "null"))) {
            startActivity(new Intent(this, LoginActivity.class));
            return;
        }
        data();
    }

    private void data() {
        // 防止fragment重叠问题
        if (null != list_fragmentManager && list_fragmentManager.size() != 0) {

            for (Fragment fragment : list_fragmentManager) {
                transaction.remove(fragment);
                transaction.commit();
            }
        }
        list_fragment = new ArrayList<Fragment>();
        myFragment = new MyFragment();
        ziXunHomeFragment = new ZiXunHomeFragment();
        qiuGouHomeFragment = new QiuGouHomeFragment();
        ziXunHomeFragment = new ZiXunHomeFragment();
        busnessListFragment = new BusnessListFragment();
        shoppingMallFragment = new ShoppingMallFragment();

        list_fragment.add(shoppingMallFragment);
        list_fragment.add(busnessListFragment);
        list_fragment.add(qiuGouHomeFragment);
        list_fragment.add(ziXunHomeFragment);
        list_fragment.add(myFragment);

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        // manager管理所有的fragment
        list_fragmentManager = fragmentManager.getFragments();

        transaction.add(R.id.activity_shopping_mall_framelayout, shoppingMallFragment, TAB_ShoppingMall);
        transaction.add(R.id.activity_shopping_mall_framelayout, busnessListFragment, TAB_BusnessList);
        transaction.add(R.id.activity_shopping_mall_framelayout, qiuGouHomeFragment, TAB_QiuGouHome);
        transaction.add(R.id.activity_shopping_mall_framelayout, ziXunHomeFragment, TAB_ZiXunHome);
        transaction.add(R.id.activity_shopping_mall_framelayout, myFragment, TAB_MY);
        transaction.commit();
        setvisible(0);

    }

    /**
     * 切换fragment
     *
     * @param position 切换第几个
     */
    public void setvisible(int position) {
        nowPosition = position;
        FragmentTransaction ft1 = getSupportFragmentManager()
                .beginTransaction();
        for (int i = 0; i < list_fragment.size(); i++) {
            if (i == position) {
                ft1.show(list_fragment.get(i));
            } else {
                ft1.hide(list_fragment.get(i));
            }
        }
        switch (position) {
            case 0:
                Shangcheng.setChecked(true);
                break;
            case 1:
                shangjia.setChecked(true);
                break;
            case 2:
                Qiugou.setChecked(true);
                break;
            case 3:
                Zixun.setChecked(true);
                break;
            case 4:
                Wode.setChecked(true);
                break;
        }
        ft1.commit();

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            showTips();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showTips() {

        AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle("提醒")
                .setMessage("是否退出程序")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        CommonUtils.removeActivity();
                        Process.killProcess(Process.myPid());

                    }

                }).setNegativeButton("取消",

                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        }).create(); // 创建对话框
        alertDialog.show(); // 显示对话框
    }

    @OnClick({R.id.activity_shopping_mall_rab_shangcheng, R.id.activity_shopping_mall_rab_shangjia, R.id.activity_shopping_mall_rab_qiugou, R.id.activity_shopping_mall_rab_zixun, R.id.activity_shopping_mall_rab_wode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_shopping_mall_rab_shangcheng:
                setvisible(0);
                break;
            case R.id.activity_shopping_mall_rab_shangjia:
                setvisible(1);
                break;
            case R.id.activity_shopping_mall_rab_qiugou:
                setvisible(2);
                break;
            case R.id.activity_shopping_mall_rab_zixun:
                setvisible(3);
                break;
            case R.id.activity_shopping_mall_rab_wode:
                setvisible(4);
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("position", nowPosition);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        nowPosition = savedInstanceState.getInt("position");
        setvisible(nowPosition);
        super.onRestoreInstanceState(savedInstanceState);
    }
}
