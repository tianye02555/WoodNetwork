package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPicker;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter.UploadPictureAdapter;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerInfo;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract.ExceptionAddContract;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract.OrderReceiveContract;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.presenter.OrderReceivePresenter;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment.DividerItemDecoration;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.SharePreferencesUtils;
import woodnetwork.hebg3.com.woodnetwork.net.Const;

public class OrderReceiveActivity extends AppCompatActivity implements OrderReceiveContract.OrderReceiveViewInterface {
    @Bind(R.id.imge_title_left)
    ImageView imgeTitleLeft;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.image_title_right)
    ImageView imageTitleRight;
    @Bind(R.id.activity_order_receive_text_dinDanBianHao)
    TextView text_dinDanBianHao;
    @Bind(R.id.activity_order_receive_text_date)
    TextView text_date;
    @Bind(R.id.activity_order_receive_text_maiJiaXinXi)
    TextView text_maiJiaXinXi;
    @Bind(R.id.activity_order_receive_text_price)
    TextView text_price;
    @Bind(R.id.activity_order_receive_text_jian)
    TextView text_jian;
    @Bind(R.id.activity_order_receive_text_yiChangtupian)
    TextView text_tuPian;
    @Bind(R.id.activity_order_receive_edit_beizhu)
    EditText edit_beiZhu;
    @Bind(R.id.activity_order_receive_recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.activity_order_receive_btn_tijiao)
    Button btn_tijiao;
    //    private String m_strAvatar;
    private HashMap<String, File> files = new HashMap<String, File>();
    ;
    private UploadPictureAdapter addAdapter;
    private List<Bitmap> list;
    private final int TAKE_PHOTO = 1;// 拍照
    private final int ALBUM = 2;// 相册
    private int chooseNumber = 4;
    private String id;
    private String creat_time;
    private String seller;
    private String total_price;
    private String number;
    private String oid;
    private SharePreferencesUtils sharePreferencesUtils;
    private OrderReceiveContract.OrderReceivePresenterInterface presenter;
    private HashMap<String, String> params;
    private int times;//存放文件的循环次数
    public int getChooseNumber() {
        return chooseNumber;
    }

    public void setChooseNumber(int chooseNumber) {
        this.chooseNumber = chooseNumber;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_receive);
        ButterKnife.bind(this);

        sharePreferencesUtils = SharePreferencesUtils.getSharePreferencesUtils(this);
        imageTitleRight.setVisibility(View.GONE);
        if (null != getIntent()) {
            id = getIntent().getStringExtra("id");
            creat_time = getIntent().getStringExtra("creat_time");
            seller = getIntent().getStringExtra("seller");
            total_price = getIntent().getStringExtra("total_price");
            number = getIntent().getStringExtra("number");
            oid = getIntent().getStringExtra("oid");
            if ("1".equals(getIntent().getStringExtra("flag"))) {
                textTitle.setText("确认发货");
                text_tuPian.setText(getResources().getString(R.string.fahuotupian));
            } else {
                textTitle.setText("确认收货");
            }
        } else {
            textTitle.setText("确认收货");
        }
        showOrderInfo();
        new OrderReceivePresenter(this);
        list = new ArrayList<Bitmap>();
        list.add(BitmapFactory.decodeResource(getResources(), R.drawable.chuangjian));
        addAdapter = new UploadPictureAdapter(this, list, 1);
        recyclerview.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, 2));
        recyclerview.setAdapter(addAdapter);

    }

    @OnClick({R.id.imge_title_left, R.id.activity_order_receive_btn_tijiao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imge_title_left:
                finish();
                break;
            case R.id.activity_order_receive_btn_tijiao:
                showProgress();
                 params = new HashMap<String, String>();

                params.put("content", edit_beiZhu.getText().toString().trim());
                params.put("oid", oid);

                if (!"".equals((String) sharePreferencesUtils.getData("userid", ""))) {
                    params.put("sid", (String) sharePreferencesUtils.getData("userid", ""));
                } else {
                    showMessage("身份信息失效，请重新登录");
                    return;
                }

                if (addAdapter.getList().size() < 4) {
                    times = addAdapter.getList().size() - 1;
                } else {
                    times = 4;
                }
                Bitmap2FileTask bitmap2FileTask=new Bitmap2FileTask();
                bitmap2FileTask.execute("");
//                for (int i = 0; i < times; i++) {
//                    File file = new File(CommonUtils.saveBitmapToFile(addAdapter.getList().get(i)));
//                    files.put(file.getName(), file);
//                }
//                if(null==files||files.size()==0){
//                    showMessage("请添加确认图片");
//                    return;
//                }

//                if (null != getIntent()) {
//                    if ("1".equals(getIntent().getStringExtra("flag"))) {
//                        //发货接口
//                        presenter.submitDelevryOrder(OrderReceiveActivity.this, params, files);
//                    } else {
//                        //收货接口
//                        presenter.submitReceiveOrder(OrderReceiveActivity.this, params, files);
//                    }
//                } else {
//                    //收货接口
//                    presenter.submitReceiveOrder(OrderReceiveActivity.this, params, files);
//                }
                break;
        }
    }

    @Override
    public String getBeiZhu() {
        return edit_beiZhu.getText().toString().trim();
    }

    @Override
    public void showOrderInfo() {
        text_dinDanBianHao.setText("订单编号：" + id);
        text_date.setText("下单日期：" + creat_time);
        if("1".equals(getIntent().getStringExtra("flag"))){
            text_maiJiaXinXi.setText("买家信息：" + seller);
        }else{
            text_maiJiaXinXi.setText("卖家信息：" + seller);
        }
        text_price.setText(total_price);
        text_jian.setText(number);
    }

    @Override
    public void addReceivePicture(View view) {
        showPopupWindow(view);
    }

    @Override
    public void setPresenter(OrderReceiveContract.OrderReceivePresenterInterface presenter) {
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
                    CommonUtils.showToast(OrderReceiveActivity.this,
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
                PhotoPicker.PhotoPickerBuilder builder = PhotoPicker.builder();
                builder.setPhotoCount(chooseNumber)
                        .setShowCamera(false)
                        .setShowGif(true)
                        .setPreviewEnabled(false)
                        .start(OrderReceiveActivity.this, PhotoPicker.REQUEST_CODE);
                popupWindow.dismiss();

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
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {

            if (data != null) {
                ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                list = addAdapter.getList();
                list.remove(list.size() - 1);
                Bitmap bitmap;
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inTempStorage = new byte[100 * 1024];
                opts.inPreferredConfig = Bitmap.Config.RGB_565;
                opts.inPurgeable = true;
                opts.inSampleSize = 4;
                for (String url : photos) {
                    bitmap = BitmapFactory.decodeFile(url, opts);
                    list.add(bitmap);
                    chooseNumber = chooseNumber - 1;
                }
                if (list.size() < 4) {//限制上传4张图片
                    Bitmap photoDefault = BitmapFactory.decodeResource(getResources(), R.drawable.chuangjian);
                    list.add(photoDefault);
                    addAdapter.setLastIsAdd(true);
                } else if (list.size() == 4) {
                    addAdapter.setShow(true);
                    addAdapter.setLastIsAdd(false);
                }
                addAdapter.setList(list);
                addAdapter.notifyDataSetChanged();
                return;
            }
        }
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case TAKE_PHOTO:
                    File file = new File(Const.PICTURE_PATH + "image.jpg");
                    if (!file.exists()) {
                        CommonUtils.showToast(OrderReceiveActivity.this,
                                "获取照片失败");
                        return;
                    }
                    list = addAdapter.getList();
                    list.remove(list.size() - 1);
                    list.add(CommonUtils.getSmallAndRightBitmap(file.getAbsolutePath()));
                    if (list.size() < 4) {//限制上传4张图片
                        list.add(BitmapFactory.decodeResource(getResources(), R.drawable.chuangjian));
                    } else if (list.size() == 4) {
                        addAdapter.setShow(true);
                        addAdapter.setLastIsAdd(false);
                    }
                    addAdapter.setList(list);
                    addAdapter.notifyItemRangeChanged(list.size() - 2, list.size() - 1);
                    break;

            }
        }
    }

    @Override
    public void closeActivity() {
        setResult(RESULT_OK);
        finish();
    }
    class Bitmap2FileTask extends AsyncTask<String,Integer,HashMap<String, File>> {

        @Override
        protected HashMap<String, File> doInBackground(String... params) {
            for (int i = 0; i < times; i++) {
                File file = new File(CommonUtils.saveBitmapToFile(addAdapter.getList().get(i)));
                files.put(file.getName(), file);
            }
            return files;
        }
        @Override
        protected void onPostExecute(HashMap<String, File> file) {
            super.onPostExecute(file);
//            if (null == files || files.size() == 0 || "".equals(edit_yiChangYuanYin.getText().toString().trim())) {
//                showMessage("请完善异常信息");
//                return;
//            }
            if(null==files||files.size()==0){
                showMessage("请添加确认图片");
                return;
            }
            if (null != getIntent()) {
                    if ("1".equals(getIntent().getStringExtra("flag"))) {
                        //发货接口
                        presenter.submitDelevryOrder(OrderReceiveActivity.this, params, files);
                    } else {
                        //收货接口
                        presenter.submitReceiveOrder(OrderReceiveActivity.this, params, files);
                    }
                } else {
                    //收货接口
                    presenter.submitReceiveOrder(OrderReceiveActivity.this, params, files);
                }
        }
    }
}
