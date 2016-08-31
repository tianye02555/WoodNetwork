package woodnetwork.hebg3.com.woodnetwork.Utils;

import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by ty on 2016/8/31 0031.
 */

public class DialogUtils {
    /**
     *  AlertDialog
     */
    public static void showDialog(Context context,String title, String message, String negativeButtonString, String positiveButtonString, DialogInterface.OnClickListener onClickListener) {
  /*
  这里使用了 android.support.v7.app.AlertDialog.Builder
  可以直接在头部写 import android.support.v7.app.AlertDialog
  那么下面就可以写成 AlertDialog.Builder
  */
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        if(null==negativeButtonString||"".equals(negativeButtonString)){
            negativeButtonString="取消";
        }
        if(null==positiveButtonString||"".equals(positiveButtonString)){
            positiveButtonString="确定";
        }
        builder.setNegativeButton(negativeButtonString, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton(positiveButtonString, onClickListener);
        builder.show();
    }
}
