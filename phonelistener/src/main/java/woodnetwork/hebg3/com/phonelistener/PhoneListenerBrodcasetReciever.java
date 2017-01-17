package woodnetwork.hebg3.com.phonelistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by ty on 2016/11/22 0022.
 */

public class PhoneListenerBrodcasetReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context,PhoneListenerService.class));
    }

}
