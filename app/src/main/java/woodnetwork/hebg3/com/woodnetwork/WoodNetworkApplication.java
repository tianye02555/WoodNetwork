package woodnetwork.hebg3.com.woodnetwork;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import me.iwf.photopicker.PhotoPicker;

/**
 * Created by ty on 2016/8/15 0015.
 */

public class WoodNetworkApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化fresco
        Fresco.initialize(this);

    }

}
