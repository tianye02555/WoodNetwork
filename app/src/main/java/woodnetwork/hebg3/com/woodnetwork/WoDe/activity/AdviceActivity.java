package woodnetwork.hebg3.com.woodnetwork.WoDe.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_guestbookAdd;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_quotation_add;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.GuestbookTypeList;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.GuestbookTypeList_listItem;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.AdviceContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.MyInformationContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.presenter.AdvicePresenter;

public class AdviceActivity extends AppCompatActivity implements AdviceContract.AdviceViewInterface {

    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_advice_edit_name)
    EditText editName;
    @Bind(R.id.activity_advice_edit_email)
    EditText editEmail;
    @Bind(R.id.activity_advice_edit_phone)
    EditText editPhone;
    @Bind(R.id.activity_advice_spinner_fenlei)
    Spinner spinnerFenlei;
    @Bind(R.id.activity_advice_edit_liuyanneirong)
    EditText editLiuyanneirong;
    @Bind(R.id.activity_advice_btn_tijiao)
    Button btnTijiao;
    private AdviceContract.AdvicePresenterInterface presenter;
    private Request_guestbookAdd request_guestbookAdd;
    private ArrayList<String> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        CommonUtils.addActivity(this);
        ButterKnife.bind(this);
        textTitle.setText("意见反馈");
        imageTitleRight.setVisibility(View.GONE);


        new AdvicePresenter(this);
        SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(this);
        Request_getAttribute request_getAttribute = new Request_getAttribute();
        request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");


        MyRequestInfo myRequestInfo = new MyRequestInfo();
        myRequestInfo.req = new Object();
        myRequestInfo.req_meta = request_getAttribute;

        presenter.getGuestbookTypeData(myRequestInfo);
    }

    @OnClick({R.id.imge_title_left, R.id.activity_advice_btn_tijiao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imge_title_left:
                finish();
                break;
            case R.id.activity_advice_btn_tijiao:
                if(0==editPhone.getText().toString().trim().length()||0==editEmail.getText().toString().trim().length()||0==editName.getText().toString().trim().length()||0==editLiuyanneirong.getText().toString().trim().length()){
                    showMessage("请完善建议信息");
                    return;
                }
                SharePreferencesUtils sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(this);
                Request_getAttribute request_getAttribute = new Request_getAttribute();
                request_getAttribute.user_id = (String) sharePreferencesUtils.getData("userid", "");


                MyRequestInfo myRequestInfo = new MyRequestInfo();
                myRequestInfo.req = request_guestbookAdd;
                myRequestInfo.req_meta = request_getAttribute;
                presenter.submitData(myRequestInfo);
                break;
        }
    }

    @Override
    public void getAdviceInfo() {

        request_guestbookAdd.content = editLiuyanneirong.getText().toString().trim();
        request_guestbookAdd.mail = editEmail.getText().toString().trim();
        request_guestbookAdd.name = editName.getText().toString().trim();
        request_guestbookAdd.phone = editPhone.getText().toString().trim();
    }

    @Override
    public void setGuestbookTypeData(final GuestbookTypeList guestbookTypeList) {
        mItems = new ArrayList<String>();
        for (GuestbookTypeList_listItem item : guestbookTypeList.list) {
            mItems.add(item.name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFenlei.setAdapter(adapter);

        spinnerFenlei.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                request_guestbookAdd = new Request_guestbookAdd();
                request_guestbookAdd.type = guestbookTypeList.list.get(i).id;
                request_guestbookAdd.content=editLiuyanneirong.getText().toString().trim();
                request_guestbookAdd.phone=editPhone.getText().toString().trim();
                request_guestbookAdd.mail=editEmail.getText().toString().trim();
                request_guestbookAdd.name=editName.getText().toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void setPresenter(AdviceContract.AdvicePresenterInterface presenter) {
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


