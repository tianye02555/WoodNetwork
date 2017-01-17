package woodnetwork.hebg3.com.testapplication.wedgit.ShowNetPicture;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import woodnetwork.hebg3.com.testapplication.R;

/**
 * Created by ty on 2016/11/2 0002.
 */

public class TestView extends ImageView {
    private Matrix matrix;
    private Context context;
    public TestView(Context context) {
        super(context);
        this.context=context;
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Matrix matrix=new Matrix();
        Bitmap bitmap= BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        Paint paint=new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        canvas.drawBitmap(bitmap,matrix,paint);
        matrix.setTranslate(300,300);//平移
        matrix.preScale(1.1f,1.1f);
//        matrix.setScale(2,2,100,100);//放大缩小（小数为缩小）   正数放大  第一个参数为x轴放大倍数，第二个参数为Y轴放大倍数 第三四 为针对某一个坐标点放大缩小
//        matrix.setRotate(30,100,100);//针对某一个点旋转  默认为0.0点
//        matrix.setSkew(1,0);//拉伸  0为不拉伸

        canvas.drawBitmap(bitmap,matrix,paint);
        super.onDraw(canvas);
    }

}
