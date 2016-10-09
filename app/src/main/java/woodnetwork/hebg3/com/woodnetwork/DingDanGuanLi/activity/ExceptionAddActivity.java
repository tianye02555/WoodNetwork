package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter.UploadPictureAdapter;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.ExceptionList;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerInfo;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract.ExceptionAddContract;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.presenter.ExceptionAddPresenter;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.RequestParam.Request_getAttribute;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment.DividerItemDecoration;
import woodnetwork.hebg3.com.woodnetwork.Utils.AsyncTaskForUpLoadFilesNew;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.net.Const;

public class ExceptionAddActivity extends AppCompatActivity implements ExceptionAddContract.ExceptionAddViewInterface {

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
    @Bind(R.id.activity_exception_add)
    RelativeLayout relativeLayout;
    private UploadPictureAdapter addAdapter;
    private List<Bitmap> list;
    private final int TAKE_PHOTO = 1;// 拍照
    private final int ALBUM = 2;// 相册
    private String seller = "";
    private String number = "";
    private String flag = "";
    private String oid = "";
    private String title_price = "";
    private String creatTime = "";
    private String id;
    private ExceptionAddContract.ExceptionAddPresenterInterface presenter;
    private SharePreferencesUtils sharePreferencesUtils;
    private HashMap<String, File> files = new HashMap<String, File>();
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exception_add);
        ButterKnife.bind(this);
        new ExceptionAddPresenter(this);
        sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(this);
        textTitle.setText("添加订单异常");
        imageTitleRight.setVisibility(View.GONE);
        if (null != getIntent()) {
            seller = getIntent().getStringExtra("seller");
            number = getIntent().getStringExtra("number");
            flag = getIntent().getStringExtra("flag");
            oid = getIntent().getStringExtra("oid");
            title_price = getIntent().getStringExtra("total_price");
            creatTime = getIntent().getStringExtra("creat_time");
            id = getIntent().getStringExtra("id");
        }
        list = new ArrayList<Bitmap>();
        list.add(BitmapFactory.decodeResource(getResources(), R.drawable.defaultimg));
        addAdapter = new UploadPictureAdapter(this, list, 0);
        recyclerview.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, 2));
        recyclerview.setAdapter(addAdapter);
        showOrderExceptionInfo();
    }

    @OnClick({R.id.imge_title_left, R.id.activity_order_exceptionadd_btn_tijiao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imge_title_left:
                finish();
                break;
            case R.id.activity_order_exceptionadd_btn_tijiao:

                HashMap<String, String> params = new HashMap<String, String>();

                params.put("content", edit_yiChangYuanYin.getText().toString().trim());
                params.put("oid", oid);
                if (!"".equals((String) sharePreferencesUtils.getData("userid", ""))) {
                    params.put("sid", (String) sharePreferencesUtils.getData("userid", ""));
                } else {
                    showMessage("身份信息失效，请重新登录");
                    return;
                }
                int times;//存放文件的循环次数
                if (addAdapter.getList().size() < 4) {
                    times = addAdapter.getList().size() - 1;
                } else {
                    times = 4;
                }
                for (int i = 0; i < times; i++) {
                    File file = new File(CommonUtils.saveBitmapToFile(addAdapter.getList().get(i)));
                    files.put("image", file);
                }
                if(null==files||files.size()==0){
                    showMessage("请添加异常图片");
                    return;
                }
                showProgress();
                presenter.submitExceptionOrder(ExceptionAddActivity.this, params, files);
                break;
        }
    }

    @Override
    public String getYiChangYuanYin() {
        return edit_yiChangYuanYin.getText().toString().trim();

    }

    @Override
    public void showOrderExceptionInfo() {
        text_dinDanBianHao.setText("订单编号：" + id);
        text_date.setText("下单日期：" + creatTime);
        if ("1".equals(flag)) {
            text_maiJiaXinXi.setText("买家信息：" + seller);
        } else {
            text_maiJiaXinXi.setText("卖家信息：" + seller);
        }

        text_price.setText(String.valueOf(title_price));
        text_jian.setText(number);
    }

    @Override
    public void addExceptionPicture(View view) {
        showPopupWindow(view);
    }

    @Override
    public void closeActivity() {
        setResult(RESULT_OK);
        finish();
    }


    @Override
    public void setPresenter(ExceptionAddContract.ExceptionAddPresenterInterface presenter) {
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

    private void showPopupWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(this)
                .inflate(R.layout.dialog_photo_album, null);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // 拍照
        TextView fromPhoto = (TextView) contentView
                .findViewById(R.id.dialog_xiangji);
        fromPhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (!CommonUtils.isExistSdcard()) {
                    CommonUtils.showToast(ExceptionAddActivity.this,
                            "SD卡不存在");
                    return;
                }
                // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                File file = new File(Const.PICTURE_PATH);
                if (!file.exists())
                    file.mkdirs();
                file = new File(Const.PICTURE_PATH + "image.jpg");
                if (file.exists()) {
                    file.delete();
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        CommonUtils.log(e);
                    }
                }
                Uri imageUri = Uri.fromFile(file);
                Intent openCameraIntent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                popupWindow.dismiss();
                startActivityForResult(openCameraIntent, TAKE_PHOTO);
            }

        });
        // 相册
        TextView fromAlbum = (TextView) contentView
                .findViewById(R.id.dialog_xiangce);
        fromAlbum.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent;
                if (Build.VERSION.SDK_INT < 19) {
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");

                } else {
                    intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                }
                popupWindow.dismiss();
                startActivityForResult(intent, ALBUM);

            }

        });
        // 取消
        TextView back = (TextView) contentView.findViewById(R.id.dialogquxiao);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                // TODO Auto-generated method stub
                return false;
            }
        });
        // 设置好参数之后再show
        popupWindow.showAtLocation(view, 0, 0, 0);

    }

    @SuppressLint("NewApi")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case TAKE_PHOTO:
                    File file = new File(Const.PICTURE_PATH + "image.jpg");
                    if (!file.exists()) {
                        CommonUtils.showToast(ExceptionAddActivity.this,
                                "获取照片失败");
                        return;
                    }
                    list = addAdapter.getList();
                    list.remove(list.size() - 1);
                    list.add(CommonUtils.getSmallAndRightBitmap(file.getAbsolutePath()));
                    if (list.size() < 4) {//限制上传4张图片
                        list.add(BitmapFactory.decodeResource(getResources(), R.drawable.chuangjian));
                    }
                    addAdapter.setList(list);
                    addAdapter.notifyItemRangeChanged(list.size() - 2, list.size() - 1);
                    break;
                case ALBUM:
                    if (data != null) {
                        try {
                            Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                            String[] filePathColumn = {MediaStore.Images.Media.DATA};
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                            cursor.moveToFirst();
                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            String picturePath = cursor.getString(columnIndex);  //获取照片路径
                            cursor.close();

                            list = addAdapter.getList();
                            list.remove(list.size() - 1);
                            list.add(CommonUtils.getSmallAndRightBitmap(picturePath));
                            if (list.size() < 4) {//限制上传4张图片
                                Bitmap photoDefault = BitmapFactory.decodeResource(getResources(), R.drawable.chuangjian);
                                list.add(photoDefault);
                            }
                            addAdapter.setList(list);
                            addAdapter.notifyItemRangeChanged(list.size() - 2, list.size() - 1);
                        } catch (Exception e) {
                            // TODO Auto-generatedcatch block
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    }
}
