package Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;

/**
 * Created by ty on 2016/8/18 0018.
 */

public class FrescoUtils {
    /**
     * 如果图片过大，可以将图片进行缩放在显示
     * 获取controller,
     *
     * @param context
     * @param bitmap
     */
    public static DraweeController getNewDraweeController(Context context, Bitmap bitmap) {
        //对Bitmap进行后处理
        Postprocessor redMeshPostprocessor = new BasePostprocessor() {
            @Override
            public String getName() {
                return "redMeshPostprocessor";
            }

            @Override
            public void process(Bitmap bitmap) {
                for (int x = 0; x < bitmap.getWidth(); x += 2) {
                    for (int y = 0; y < bitmap.getHeight(); y += 2) {
                        bitmap.setPixel(x, y, Color.RED);
                    }
                }
            }
        };

        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, null, null)))
                .setProgressiveRenderingEnabled(true)
                //重新设置图片尺寸
                .setResizeOptions(new ResizeOptions(200, 100))
                .setAutoRotateEnabled(true)
                .setPostprocessor(redMeshPostprocessor)
                .build();

        //监听图片下载过程
        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {

            }

            @Override
            public void onFailure(String id, Throwable throwable) {

            }
        };


        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .setImageRequest(imageRequest)
                .setControllerListener(controllerListener)
                .build();
        return draweeController;

//        //设置圆角
//        RoundingParams roundingParams = RoundingParams.fromCornersRadius(10)
//                .setRoundingMethod(RoundingParams.RoundingMethod.BITMAP_ONLY);
//        roundingParams.setCornersRadii(10, 10, 0, 0);
//
//
//        GenericDraweeHierarchy genericDraweeHierarchy = new GenericDraweeHierarchyBuilder(context.getResources())
//                .setRoundingParams(roundingParams)
//                //设置缩放类型
//                .setActualImageScaleType(ScalingUtils.ScaleType.CENTER)
//                //设置加载图片的淡入淡出的时间
//                .setFadeDuration(300)
//                //设置加载的进度条
//                .setProgressBarImage(new ProgressBarDrawable())
//                .build();
    }
}
