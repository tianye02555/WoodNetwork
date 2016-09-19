package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.fragment;

import com.squareup.picasso.Picasso;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import woodnetwork.hebg3.com.woodnetwork.R;

/** 显示网络图片碎片【com.hebg3.yitianxia.activity.ShowNetPictureActivity】 */
public class ShowNetPictureFragment extends Fragment {
	public static final String IMG_URL = "img_url";// 图片的url
	private View view;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.showphoto_item, null);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ImageView img = (ImageView) view.findViewById(R.id.img);
		Bundle args = getArguments();
		if (args != null) {
			String url = args.getString(IMG_URL);
			if (!TextUtils.isEmpty(url))
//				ImageLoader.getInstance().displayImage(url, img);// 加载并显示图片
			Picasso.with(getActivity()).load(url).into(img);
		}
	}
}
