package woodnetwork.hebg3.com.woodnetwork.Utils;

import android.content.Context;
import android.text.TextUtils;

/**显示loading*/
public class ProgressUtils {

	private static CustomProgressDialog pd;

	public static void show(Context context, int strId) {
		show(context, context.getResources().getString(strId));
	}

	public static void show(Context context, String msg) {

		if (pd == null || !pd.isShowing()) {
			pd = CustomProgressDialog.createDialog(context);
			if (!TextUtils.isEmpty(msg))
				pd.setMessage(msg);
			pd.setCancelable(false);
			pd.show();
		}
	}

	public static void setMessage(String msg) {
		if (pd != null && pd.isShowing() && !TextUtils.isEmpty(msg))
			pd.setMessage(msg);
	}

	public static void hide() {
		try {
			if (pd != null) {
				pd.cancel();
				pd = null;
			}
		} catch (Exception e) {

		}
	}
}
