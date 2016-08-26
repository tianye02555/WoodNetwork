package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment.ShoppingMallFragment;

public class ShoppingMallActivity extends FragmentActivity {

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_shopping_mall_rab_shangcheng)
    RadioButton Shangcheng;
    @Bind(R.id.activity_shopping_mall_rab_qiugou)
    RadioButton Qiugou;
    @Bind(R.id.activity_shopping_mall_rab_zixun)
    RadioButton Zixun;
    @Bind(R.id.activity_shopping_mall_rab_wode)
    RadioButton Wode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_mall);
        ButterKnife.bind(this);

        imgeTitleLeft.setVisibility(View.GONE);
        textTitle.setText("木联网");
        imageTitleRight.setVisibility(View.GONE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activity_shopping_mall_framelayout, new ShoppingMallFragment(), "shoppingmallfragment").commit();
    }

    @OnClick({R.id.activity_shopping_mall_rab_shangcheng, R.id.activity_shopping_mall_rab_qiugou, R.id.activity_shopping_mall_rab_zixun, R.id.activity_shopping_mall_rab_wode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_shopping_mall_rab_shangcheng:
                break;
            case R.id.activity_shopping_mall_rab_qiugou:
                break;
            case R.id.activity_shopping_mall_rab_zixun:
                break;
            case R.id.activity_shopping_mall_rab_wode:
                break;
        }
    }
}
