package woodnetwork.hebg3.com.woodnetwork.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import woodnetwork.hebg3.com.woodnetwork.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/** 轮播图组件 */
@SuppressWarnings("deprecation")
public class MyGallery extends Gallery implements AdapterView.OnItemClickListener,
		AdapterView.OnItemSelectedListener, OnTouchListener {
	private Context mContext;
	private MyOnItemClickListener mMyOnItemClickListener;
	private int mSwitchTime;
	private Timer mTimer;
	private LinearLayout mOvalLayout;
	private int curIndex = 0;
	private int oldIndex = 0;
	private int mFocusedId;
	private int mNormalId;
	private String[] mUris;
	private String [] title;
	private TextView titleView;
	List<ImageView> listImgs;

//	private DisplayImageOptions displayPictureOptions = new DisplayImageOptions.Builder()
//			// .showStubImage(R.drawable.ic_launcher)
//			.showImageForEmptyUri(R.drawable.default_art)
//			// .showImageOnLoading(R.drawable.white)
//			// 加载时显示选择进度条
//			.showImageOnFail(R.drawable.default_art)
//			// 加载失败时显示缺省头像
//			.imageScaleType(ImageScaleType.EXACTLY).cacheInMemory(false).cacheOnDisk(true)
//			.bitmapConfig(Bitmap.Config.RGB_565).resetViewBeforeLoading(true).build();

	public MyGallery(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyGallery(Context context) {
		super(context);
	}

	public MyGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void start(Context context, String[] mris, int switchTime, LinearLayout ovalLayout, int focusedId,
					  int normalId, String[] title, TextView titleView) {
		this.mContext = context;
		this.mUris = mris;
		this.title=title;
		this.titleView=titleView;
		this.mSwitchTime = switchTime;
		this.mOvalLayout = ovalLayout;
		this.mFocusedId = focusedId;
		this.mNormalId = normalId;
		ininImages();
		setAdapter(new AdAdapter());
		this.setOnItemClickListener(this);
		this.setOnTouchListener(this);
		this.setOnItemSelectedListener(this);
		this.setSoundEffectsEnabled(false);
		this.setAnimationDuration(700);
		this.setUnselectedAlpha(1);
		setSpacing(0);
		setSelection((getCount() / 2 / listImgs.size()) * listImgs.size());
		setFocusableInTouchMode(true);
		initOvalLayout();
		startTimer();
	}

	private void ininImages() {
		listImgs = new ArrayList<ImageView>();
		for (int i = 0; i < mUris.length; i++) {
			ImageView imageview = new ImageView(mContext);
			imageview.setScaleType(ImageView.ScaleType.FIT_XY);
			imageview.setLayoutParams(
					new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//			ImageLoader.getInstance().displayImage(mUris[i], imageview, displayPictureOptions);
			Picasso.with(mContext).load(mUris[i]).into(imageview);
			listImgs.add(imageview);
		}

	}

	private void initOvalLayout() {
		if (mOvalLayout != null && listImgs.size() < 2) {
			mOvalLayout.getLayoutParams().height = 0;
		} else if (mOvalLayout != null) {
			int Ovalheight = (int) (mOvalLayout.getLayoutParams().height * 0.7);
			int Ovalmargin = (int) (mOvalLayout.getLayoutParams().height * 0.2);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Ovalheight,
					Ovalheight);
			layoutParams.setMargins(Ovalmargin, 0, Ovalmargin, 0);
			mOvalLayout.removeAllViews();
			for (int i = 0; i < listImgs.size(); i++) {
				View v = new View(mContext);
				v.setLayoutParams(layoutParams);
				v.setBackgroundResource(mNormalId);
				mOvalLayout.addView(v);
			}
			mOvalLayout.getChildAt(0).setBackgroundResource(mFocusedId);
		}
	}

	class AdAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			if (listImgs.size() < 2)
				return listImgs.size();
			return Integer.MAX_VALUE;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return listImgs.get(position % listImgs.size());
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
	}

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		int kEvent;
		if (isScrollingLeft(e1, e2)) {
			kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
		} else {
			kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
		}
		onKeyDown(kEvent, null);
		return true;

	}

	private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2) {
		return e2.getX() > (e1.getX() + 50);
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		return super.onScroll(e1, e2, distanceX, distanceY);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (MotionEvent.ACTION_UP == event.getAction() || MotionEvent.ACTION_CANCEL == event.getAction()) {
			startTimer();
		} else {
			stopTimer();
		}
		return false;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
		curIndex = position % listImgs.size();
		if (mOvalLayout != null && listImgs.size() > 1) {
			mOvalLayout.getChildAt(oldIndex).setBackgroundResource(mNormalId);
			mOvalLayout.getChildAt(curIndex).setBackgroundResource(mFocusedId);
			if(null!=titleView){
				this.titleView.setText(title[curIndex]);
			}
			oldIndex = curIndex;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		if (mMyOnItemClickListener != null) {
			mMyOnItemClickListener.onItemClick(curIndex);
		}
	}

	public void setMyOnItemClickListener(MyOnItemClickListener listener) {
		mMyOnItemClickListener = listener;
	}

	public interface MyOnItemClickListener {
		void onItemClick(int curIndex);
	}

	public void stopTimer() {
		if (mTimer != null) {
			mTimer.cancel();
			mTimer = null;
		}
	}

	public void startTimer() {
		if (mTimer == null && listImgs.size() > 1 && mSwitchTime > 0) {
			mTimer = new Timer();
			mTimer.schedule(new TimerTask() {
				public void run() {
					handler.sendMessage(handler.obtainMessage(1));
				}
			}, mSwitchTime, mSwitchTime);
		}
	}

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			onScroll(null, null, 1, 0);
			onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
		}
	};
}
