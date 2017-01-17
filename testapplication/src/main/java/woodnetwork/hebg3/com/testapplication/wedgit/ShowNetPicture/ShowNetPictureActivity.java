package woodnetwork.hebg3.com.testapplication.wedgit.ShowNetPicture;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import woodnetwork.hebg3.com.testapplication.R;

/** 显示网络图片;艺术品图片和动态图片用到 */
public class ShowNetPictureActivity extends FragmentActivity {
	public static final String LS_PHOTOFILEPATH = "listPhotoFilePath";// 图片路径集合
	public static final String IMG_POSITION = "imgPosition";// 用户点击图片item位置
	private List<String> mListPhotoFilePath;// 图片路径集合
	private int mImgPosition;// 用户点击图片item位置
	private ViewPager vp;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_net_picture);
		vp = (ViewPager) findViewById(R.id.vp);
		Intent intent = getIntent();
		mListPhotoFilePath = (List<String>) intent.getSerializableExtra(LS_PHOTOFILEPATH);
		mImgPosition = intent.getIntExtra(IMG_POSITION, 0);
		if (mListPhotoFilePath != null && mListPhotoFilePath.size() != 0) {
			List<Fragment> ls = new ArrayList<Fragment>();
			ShowNetPictureFragment snpf;
			Bundle args;
			for (String item : mListPhotoFilePath) {
				snpf = new ShowNetPictureFragment();
				args = new Bundle();
				args.putString(ShowNetPictureFragment.IMG_URL, item);// 将图片的url传给碎片，由碎片来处理图片的加载
				snpf.setArguments(args);
				ls.add(snpf);
			}
			vp.setAdapter(new VPAdapter(getSupportFragmentManager(), ls));
			vp.setCurrentItem(mImgPosition);// 显示用户点击的那张图片
		}
	}

}
