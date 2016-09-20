package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.adapter.UploadPictureAdapter;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean.OrderBuyerInfo;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract.ExceptionAddContract;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.contract.OrderReceiveContract;
import woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.presenter.OrderReceivePresenter;
import woodnetwork.hebg3.com.woodnetwork.R;
import woodnetwork.hebg3.com.woodnetwork.ShoppingMall.fragment.DividerItemDecoration;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.ProgressUtils;
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
    @Bind(R.id.activity_order_receive_edit_yiChangYuanYin)
    EditText edit_beiZhu;
    @Bind(R.id.activity_order_receive_recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.activity_order_receive_btn_tijiao)
    Button btn_tijiao;
    private UploadPictureAdapter addAdapter;
    private List<Bitmap> list;
    private final int TAKE_PHOTO = 1;// 拍照
    private final int ALBUM = 2;// 相册
    private final int CROP = 3;// 剪切
    private OrderBuyerInfo orderBuyerInfo;
    private OrderReceiveContract.OrderReceivePresenterInterface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_receive);
        ButterKnife.bind(this);


        imageTitleRight.setVisibility(View.GONE);
        if(null!=getIntent()){
            if("1".equals(getIntent().getStringExtra("flag"))){
                textTitle.setText("确认发货");
                text_tuPian.setText(getResources().getString(R.string.fahuotupian));
            }else{
                textTitle.setText("确认收货");
            }
        }else{
            textTitle.setText("确认收货");
        }

        orderBuyerInfo = (OrderBuyerInfo) getIntent().getSerializableExtra("OrderBuyerInfo");
        showOrderInfo(orderBuyerInfo);
        new OrderReceivePresenter(this);
        list = new ArrayList<Bitmap>();
        list.add(BitmapFactory.decodeResource(getResources(), R.drawable.defaultimg));
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
                if(null!=getIntent()){
                    if("1".equals(getIntent().getStringExtra("flag"))){
                        //发货接口
                    }else{
                        //收货接口
                    }
                }else{
                    //收货接口
                }
//                presenter.submitReceiveOrder();
                break;
        }
    }

    @Override
    public String getBeiZhu() {
return edit_beiZhu.getText().toString().trim();
    }

    @Override
    public void showOrderInfo(OrderBuyerInfo orderBuyerInfo) {
        text_dinDanBianHao.setText("订单编号：" + orderBuyerInfo.number);
        text_date.setText("下单日期：" + orderBuyerInfo.creat_time);
        text_maiJiaXinXi.setText("卖家信息：" + orderBuyerInfo.seller);
        text_price.setText(String.valueOf(orderBuyerInfo.total_price));
        text_jian.setText(String.valueOf(orderBuyerInfo.products.size()));
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
                        CommonUtils.showToast(OrderReceiveActivity.this,
                                "获取照片失败");
                        return;
                    }
                    startPhotoZoom(Uri.fromFile(file));
                    break;
                case CROP:// 裁剪圖片
                    if (data != null) {
                        Bundle extras = data.getExtras();
                        if (extras != null) {
                            Bitmap photo = extras.getParcelable("data");
//                            m_strAvatar = saveBitmapToFile(photo);
                            list = addAdapter.getList();
                            list.remove(list.size() - 1);
                            list.add(photo);
                            list.add(BitmapFactory.decodeResource(getResources(), R.drawable.defaultimg));
                            addAdapter.setList(list);
                            addAdapter.notifyItemRangeChanged(list.size()-2,list.size()-1);
                        }
                    }
//                    if (!TextUtils.isEmpty(m_strAvatar)) {
//                        // img_avatar.setImageBitmap(photo);
//                        // img_avatar.setTag(m_strAvatar);
//                        System.out.println(m_strAvatar);
////                        ProgressUtils.show(this, "请稍后……");
//                        String actionUrl = "http://" + Const.AUTHORITY
//                                + "/File/uploadPicture";
//                        HashMap<String, File> files = new HashMap<String, File>();
//                        File f = new File(m_strAvatar);
//                        files.put("image", f);

//                        HashMap<String, String> params = new HashMap<String, String>();
//
//                        params.put("access_token",
//                                CommonUtils.getAccessToken("FileuploadPicture"));
//                        params.put("uid", shared.getString("uid"));
//
//                        AsyncTaskForUpLoadFilesNew at = new AsyncTaskForUpLoadFilesNew(
//                                this, actionUrl, params, files,
//                                handler.obtainMessage(1));
//                        at.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "1");
//                    }
                    break;
                case ALBUM:
                    if (data != null) {
                        startPhotoZoom(data.getData());
                    }
                    break;

            }
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) {
        try {
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                    this.getContentResolver(), uri);
            return bitmap;
        } catch (Exception e) {
            Log.e("[Android]", e.getMessage());
            Log.e("[Android]", "目录为：" + uri);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP);
    }

    /**
     * 保存bitmap到文件
     *
     * @param bmp 要保存的bitmap
     * @return String 文件保存的路径
     */
    private String saveBitmapToFile(Bitmap bmp) {
        String filePath = Const.PICTURE_PATH + System.currentTimeMillis()
                + ".png";
        File file = new File(filePath);
        try {
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            CommonUtils.log(e);
            filePath = "";
        }
        return filePath;
    }
}
