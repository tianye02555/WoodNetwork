package woodnetwork.hebg3.com.phone.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import woodnetwork.hebg3.com.phone.R;
import woodnetwork.hebg3.com.phone.adapter.FileChooseAdapter;
import woodnetwork.hebg3.com.phone.reciver.PhoneReciver;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.edit_phone)
    EditText editPhone;
    @Bind(R.id.edit_filepath)
    EditText editFilepath;
    @Bind(R.id.btn_choosepath)
    Button choosepath;
    @Bind(R.id.btn_queding)
    Button btnQueding;
    @Bind(R.id.activity_main)
    RelativeLayout activityMain;
    @Bind(R.id.btn_xiugai)
    Button btnXiugai;
    @Bind(R.id.btn_fanhui)
    Button btnFanhui;
    private FileChooseAdapter adapter;
    private SharedPreferences sharedPreferences;
    private File[] fileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("phone", Context.MODE_PRIVATE);
        editPhone.setText(sharedPreferences.getString("phone", ""));
        editFilepath.setText(sharedPreferences.getString("path", ""));
        if ("0".equals(sharedPreferences.getString("inNumber", "0"))) {

        } else {//不是第一次进入输入框不可用，点击修改后 可用
            editPhone.setEnabled(false);
            Intent intent = new Intent(MainActivity.this, PhoneReciver.class);
            intent.setAction("BEGIN");
            sendBroadcast(intent);
        }

    }

    @OnClick({R.id.btn_choosepath, R.id.btn_queding, R.id.btn_xiugai,R.id.btn_fanhui})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_choosepath://选择路径
                recyclerview.setVisibility(View.VISIBLE);
                //隐藏软键盘
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                File file = Environment.getRootDirectory().getParentFile();//获取系统根目录
                final File[] files = file.listFiles();//获取指定目录下的所有文件夹和文件
                adapter = new FileChooseAdapter(files, MainActivity.this);
                recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                adapter.setItemClickListener(new FileChooseAdapter.ItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                        File[] filesFather = adapter.getFileName();//获取以显示的所有的文件夹和文件
                        if (null == filesFather) {
                            return;
                        }
                        File[] filesChild = filesFather[position].listFiles();//获取点击的文件夹内的所有文件和文件夹
                        if (null != filesChild) {
                            fileName = filesChild.clone();//保存当前目录的所有文件名
                        }
                        filesFather[position].getParentFile();
                        if (filesFather[position].isFile()) {//如果没有文件就直接退出
                            return;
                        }
                        editFilepath.setText(filesFather[position].getPath());
                        adapter.setFileName(filesChild);
                        adapter.notifyDataSetChanged();
                        recyclerview.smoothScrollToPosition(0);
                    }

                    @Override
                    public void setOnBack(View view, int position) {
                        if (null == editFilepath.getText().toString().trim() || "".equals(editFilepath.getText().toString().trim()) || "/".equals(editFilepath.getText().toString().trim())) {
                            return;
                        }
                        File file = new File(editFilepath.getText().toString().trim());
                        File files = file.getParentFile();//获取父级文件目录
                        File[] filesChild = files.listFiles();//获取点击的文件夹内的所有文件和文件夹
                        editFilepath.setText(files.getPath());
                        adapter.setFileName(filesChild);
                        adapter.notifyDataSetChanged();
                        recyclerview.smoothScrollToPosition(0);
                    }
                });
                recyclerview.setAdapter(adapter);
                break;
            case R.id.btn_queding:
                new AlertDialog.Builder(MainActivity.this).setTitle("提示").setMessage("您选择的录音文件路径为：" + editFilepath.getText().toString().trim())
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (null == editFilepath.getText().toString().trim() || null == editPhone.getText().toString().trim()) {
                            Toast.makeText(MainActivity.this, "请完善信息", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        recyclerview.setVisibility(View.INVISIBLE);
                        SharedPreferences.Editor editor = MainActivity.this.getSharedPreferences("phone", Context.MODE_PRIVATE).edit();

                        editor.putString("path", editFilepath.getText().toString().trim());
                        editor.putString("phone", editPhone.getText().toString().trim());
                        editor.putString("inNumber", "1");
                        editor.commit();
                        Intent intent = new Intent(MainActivity.this, PhoneReciver.class);
                        intent.setAction("BEGIN");
                        sendBroadcast(intent);
                        finish();
                    }
                }).show();

                break;
            case R.id.btn_xiugai://点击修改，输入框可用
                editPhone.setEnabled(true);
                break;
            case R.id.btn_fanhui:
                finish();
                break;
        }
    }


}
