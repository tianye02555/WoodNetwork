package woodnetwork.hebg3.com.phone.reciver;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.List;

import woodnetwork.hebg3.com.phone.service.RecorderService;

/**
 * Created by ty on 2016/11/23 0023.
 */

public class PhoneReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!isServiceRun(context, "woodnetwork.hebg3.com.phone.service.RecorderService")) {
                Intent intent1=new Intent(context, RecorderService.class);
            intent1.putExtra("path", context.getSharedPreferences("phone",Context.MODE_PRIVATE).getString("path","/"));
            context.startService(intent1);
        }
    }

    public boolean isServiceRun(Context context, String service) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServiceInfoList = activityManager.getRunningServices(50);
        if (runningServiceInfoList.size() <= 0) {
            return false;
        }
        for (int i = 0; i < runningServiceInfoList.size(); i++) {
            if (runningServiceInfoList.get(i).service.getClassName().toString().equals(service)) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }
}
