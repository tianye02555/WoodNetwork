package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerInfo;
import woodnetwork.hebg3.com.woodnetwork.R;

public class OrderReceiveActivity extends AppCompatActivity {
    private OrderBuyerInfo orderBuyerInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_receive);

        orderBuyerInfo=(OrderBuyerInfo)getIntent().getSerializableExtra("OrderBuyerInfo");

    }
}
