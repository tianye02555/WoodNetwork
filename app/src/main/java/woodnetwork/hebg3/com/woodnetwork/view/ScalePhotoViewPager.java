package woodnetwork.hebg3.com.woodnetwork.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;

/**
 * 用于处理图片缩放崩溃;普通ViewPager中使用PhotoView，在缩放图片时，会报IllegalArgumentException异常，
 * 网上说这是ViewPager的bug
 */
public class ScalePhotoViewPager extends ViewPager {
	public ScalePhotoViewPager(Context context) {
		super(context);
	}

	public ScalePhotoViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		try {
			return super.onTouchEvent(ev);
		} catch (IllegalArgumentException ex) {
			CommonUtils.log(ex);
		}
		return false;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		try {
			return super.onInterceptTouchEvent(ev);
		} catch (IllegalArgumentException ex) {
			CommonUtils.log(ex);
		}
		return false;
	}

}
