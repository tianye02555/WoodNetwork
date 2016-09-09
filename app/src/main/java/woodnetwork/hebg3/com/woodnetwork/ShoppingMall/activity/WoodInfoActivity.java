package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter.WoodInfoAdapter;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.adapter.WoodInfoOtherAdapter;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean.ProductInfo;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.contract.WoodInfoContrac;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.presenter.WoodInfoPresenter;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.ProductSellerInfo;
import woodnetwork.hebg3.com.woodnetwork.view.MyGallery;
import woodnetwork.hebg3.com.woodnetwork.view.MyListView;

/**
 * 商品详情页
 */
public class WoodInfoActivity extends AppCompatActivity implements WoodInfoContrac.WoodInfoViewInterFace {


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
    @Bind(R.id.activity_wood_info_txt_name)
    TextView name;
    @Bind(R.id.activity_wood_info_txt_price)
    TextView price;
    @Bind(R.id.activity_wood_info_txt_kucun)
    TextView stock;
    @Bind(R.id.activity_wood_info_txt_chukudi)
    TextView address;
    @Bind(R.id.activity_wood_info_txt_kind)
    TextView kind;
    @Bind(R.id.activity_wood_info_txt_state)
    TextView state;
    @Bind(R.id.activity_wood_info_txt_shangpingguige)
    TextView shangpingguige;
    @Bind(R.id.activity_wood_info_txt)
    TextView activityWoodInfoTxt;
    @Bind(R.id.activity_wood_info_btn_lijigoumai)
    Button buy;
    @Bind(R.id.activity_wood_info_btn_jiarugouwuche)
    Button addToShoppingCart;
    @Bind(R.id.activity_wood_info_listview)
    MyListView listview;
    @Bind(R.id.line)
    View line;
    @Bind(R.id.activity_wood_info)
    RelativeLayout activityWoodInfo;
    private WoodInfoContrac.WoodInfoPresenterInterface presenter;
    private String pid;
    private WoodInfoAdapter adapter;
    private WoodInfoOtherAdapter adapterOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wood_info);
        CommonUtils.addActivity(this);
        ButterKnife.bind(this);

        if (null != getIntent()) {
            pid = getIntent().getStringExtra("pid");
            if (!getIntent().getBooleanExtra("isShowButton", true)) {
                buy.setVisibility(View.GONE);
                addToShoppingCart.setVisibility(View.GONE);
                line.setVisibility(View.GONE);
                imageTitleRight.setVisibility(View.GONE);
            }
        }

        new WoodInfoPresenter(this);
        if (null != getIntent()) {
            if (!getIntent().getBooleanExtra("isShowButton", true)) {
                presenter.getWoodInfoOther(pid);
            }else{
                presenter.getWoodInfo(pid);
            }
        }



    }

    @Override
    public void showWoodData(Object object) {
        if (null != getIntent()) {
            if (!getIntent().getBooleanExtra("isShowButton", true)) {
                ProductSellerInfo productSellerInfo=(ProductSellerInfo)object;
                textTitle.setText(productSellerInfo.pname);
                name.setText("名        称：" + productSellerInfo.pname);
                price.setText("价        格：" + productSellerInfo.price + "元/方");
                stock.setText("库  存  量：" + productSellerInfo.stock + "方");
                address.setText("出  库  地：" + productSellerInfo.delivery);
                if (1==productSellerInfo.type) {
                    kind.setText("产品类型： 现货");
                }else{
                    kind.setText("产品类型： 期货");
                }
                state.setText("卖        家：" + productSellerInfo.seller);

                adapterOther = new WoodInfoOtherAdapter(this, productSellerInfo.attribute);
                listview.setAdapter(adapterOther);
            }else {
                ProductInfo productInfo=(ProductInfo)object;
                textTitle.setText(productInfo.pname);
                name.setText("名        称：" + productInfo.pname);
                price.setText("价        格：" + productInfo.price + "元/方");
                stock.setText("库  存  量：" + productInfo.stock + "方");
                address.setText("出  库  地：" + productInfo.delivery);
                if (1==productInfo.type) {
                    kind.setText("产品类型： 现货");
                }else{
                    kind.setText("产品类型： 期货");
                }
                state.setText("卖        家：" + productInfo.seller);

                adapter = new WoodInfoAdapter(this, productInfo.attribute);
                listview.setAdapter(adapter);
            }
        }


//        // 设置轮播图的 宽高比 为2:1 宽为屏幕宽度
//        WindowManager manager = getWindowManager();
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        manager.getDefaultDisplay().getMetrics(outMetrics);
//        RelativeLayout.LayoutParams lay = new RelativeLayout.LayoutParams(
//                outMetrics.widthPixels, 1000);
//        lay.addRule(RelativeLayout.BELOW, R.id.includ_title);
//        homeRlTp.setLayoutParams(lay);
        String[] pictures = new String[3];
        pictures[0] = "http://img5.imgtn.bdimg.com/it/u=3279813050,4113215971&fm=206&gp=0.jpg";
        pictures[1] = "http://img0.imgtn.bdimg.com/it/u=638420455,3521255219&fm=206&gp=0.jpg";
        pictures[2] = "http://img1.imgtn.bdimg.com/it/u=766966808,4047206931&fm=206&gp=0.jpg";
        gallery.start(this, pictures, 3000,
                DotContainer, R.drawable.dot_onn,
                R.drawable.dot_offf);


    }

    @Override
    public void setPresenter(WoodInfoContrac.WoodInfoPresenterInterface presenter) {
        if (null != presenter) {
            this.presenter = presenter;
        }
    }

    @Override
    public void showProgress() {
        ProgressUtils.show(this, "请稍后……");
    }

    @Override
    public void closeProgress() {
        ProgressUtils.hide();
    }

    @Override
    public void showMessage(String string) {
        CommonUtils.showToast(this, string);
    }

    @OnClick({R.id.activity_wood_info_btn_lijigoumai, R.id.activity_wood_info_btn_jiarugouwuche,R.id.imge_title_left})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_wood_info_btn_lijigoumai:
                break;
            case R.id.activity_wood_info_btn_jiarugouwuche:
                break;
            case R.id.imge_title_left:
                finish();
                break;
        }
    }
}
