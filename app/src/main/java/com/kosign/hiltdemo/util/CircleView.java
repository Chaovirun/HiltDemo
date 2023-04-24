package com.kosign.hiltdemo.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.RequiresApi;

import com.kosign.hiltdemo.R;


public class CircleView extends FrameLayout {

    private Bitmap maskBitmap;
    private Paint paint, maskPaint;
    private int radius = 0;
    private Context mContext;

    public CircleView(Context context) {
        super(context);
        init(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        radius = a.getDimensionPixelSize(R.styleable.CircleView_circleRadius, 0);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        maskPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        setWillNotDraw(false);
    }

    public void setCornerRadius(int radius){
        this.radius = UtilsSize.getValueInDP(mContext, radius);
    }

    @Override
    public void draw(Canvas canvas) {
        Bitmap offscreenBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas offscreenCanvas = new Canvas(offscreenBitmap);

        super.draw(offscreenCanvas);

        if (maskBitmap == null) {
            maskBitmap = createMask(getWidth(), getHeight());
        }

        offscreenCanvas.drawBitmap(maskBitmap, 0f, 0f, maskPaint);
        canvas.drawBitmap(offscreenBitmap, 0f, 0f, paint);
    }

    private Bitmap createMask(int width, int height) {
        Bitmap mask = Bitmap.createBitmap(width, height, Bitmap.Config.ALPHA_8);
        Canvas canvas = new Canvas(mask);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);

        canvas.drawRect(0, 0, width, height, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        canvas.drawRoundRect(new RectF(0, 0, width, height), (radius != 0)?radius:width/2f, (radius != 0)?radius:height/2f, paint);

        return mask;
    }

}
