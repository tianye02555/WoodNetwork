package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.R;

public class ExceptionAddActivity extends AppCompatActivity {

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_order_exceptionadd_text_dinDanBianHao)
    TextView text_dinDanBianHao;
    @Bind(R.id.activity_order_exceptionadd_text_date)
    TextView text_date;
    @Bind(R.id.activity_order_exceptionadd_text_maiJiaXinXi)
    TextView text_maiJiaXinXi;
    @Bind(R.id.activity_order_exceptionadd_text_price)
    TextView text_price;
    @Bind(R.id.activity_order_exceptionadd_text_jian)
    TextView text_jian;
    @Bind(R.id.activity_order_exceptionadd_edit_yiChangYuanYin)
    EditText edit_yiChangYuanYin;
    @Bind(R.id.activity_order_exceptionadd_recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.activity_order_exceptionadd_btn_tijiao)
    Button btn_tiJiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exception_add);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imge_title_left, R.id.activity_order_exceptionadd_btn_tijiao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imge_title_left:
                break;
            case R.id.activity_order_exceptionadd_btn_tijiao:
                break;
        }
    }
}
