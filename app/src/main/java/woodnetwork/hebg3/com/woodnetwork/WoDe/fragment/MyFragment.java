package woodnetwork.hebg3.com.woodnetwork.WoDe.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.activity.DemOrderActivity;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.activity.MyOrderActivity;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.activity.SellerOrderActivity;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.activity.BusnessInfoActivity;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.WoDe.activity.DemandBuyerListActivity;
import woodnetwork.hebg3.com.woodnetwork.WoDe.activity.MyInformationActivity;
import woodnetwork.hebg3.com.woodnetwork.WoDe.activity.MyQuotationActivity;
import woodnetwork.hebg3.com.woodnetwork.WoDe.activity.ProductSellerListActivity;
import woodnetwork.hebg3.com.woodnetwork.WoDe.activity.ReportsActivity;
import woodnetwork.hebg3.com.woodnetwork.WoDe.activity.SettingActivity;


public class MyFragment extends Fragment {

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    TextView sheZhi;
    @Bind(R.id.myfragment_text_wodexinxi)
    TextView woDeXinXi;
    @Bind(R.id.myfragment_text_wodedingdan)
    TextView woDeDinDan;
    @Bind(R.id.myfragment_text_wodeqiugouxinxi)
    TextView woDeQiuGouXinXi;
    @Bind(R.id.myfragment_text_qiugoudindan)
    TextView qiuGouDinDan;
    @Bind(R.id.myfragment_text_wodeshangpu)
    TextView woDeShangPu;
    @Bind(R.id.myfragment_text_wodeshangping)
    TextView woDeShangPing;
    @Bind(R.id.myfragment_text_dingdanliebiao)
    TextView dingDanLieBiao;
    @Bind(R.id.myfragment_text_tongjichaxun)
    TextView tongJiChaXun;
    @Bind(R.id.myfragment_text_wodebaojia)
    TextView woDeBaoJia;
    @Bind(R.id.linearlayout)
    LinearLayout maiJiaZhongXin;
    private SharePreferencesUtils sharePreferencesUtils;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, view);
        imgeTitleLeft.setVisibility(View.GONE);
        textTitle.setText("个人首页");
        sharePreferencesUtils=SharePreferencesUtils.getSharePreferencesUtils(getActivity());
        if(0==(Integer) sharePreferencesUtils.getData("seller_flag",1)){
            maiJiaZhongXin.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.image_title_right,R.id.myfragment_text_wodexinxi, R.id.myfragment_text_wodedingdan, R.id.myfragment_text_wodeqiugouxinxi, R.id.myfragment_text_qiugoudindan, R.id.myfragment_text_wodeshangpu, R.id.myfragment_text_wodeshangping, R.id.myfragment_text_dingdanliebiao, R.id.myfragment_text_tongjichaxun, R.id.myfragment_text_wodebaojia})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_title_right:
                startActivity(SettingActivity.class);
                break;
            case R.id.myfragment_text_wodexinxi:
                startActivity(MyInformationActivity.class);
                break;
            case R.id.myfragment_text_wodedingdan:
                startActivity(MyOrderActivity.class);
                break;
            case R.id.myfragment_text_wodeqiugouxinxi:
                startActivity(DemandBuyerListActivity.class);
                break;
            case R.id.myfragment_text_qiugoudindan:
                startActivity(DemOrderActivity.class);
                break;
            case R.id.myfragment_text_wodeshangpu:
                Intent intent=new Intent(getActivity(),BusnessInfoActivity.class);
                intent.putExtra("from","MyFragment");
                startActivity(intent);
                break;
            case R.id.myfragment_text_wodeshangping:
                startActivity(ProductSellerListActivity.class);
                break;
            case R.id.myfragment_text_dingdanliebiao:
                startActivity(SellerOrderActivity.class);
                break;
            case R.id.myfragment_text_tongjichaxun:
                startActivity(ReportsActivity.class);
                break;
            case R.id.myfragment_text_wodebaojia:
                startActivity(MyQuotationActivity.class);
                break;
        }
    }
    public void startActivity(Class mClass){
        startActivity(new Intent(getActivity(),mClass));
    }
}
