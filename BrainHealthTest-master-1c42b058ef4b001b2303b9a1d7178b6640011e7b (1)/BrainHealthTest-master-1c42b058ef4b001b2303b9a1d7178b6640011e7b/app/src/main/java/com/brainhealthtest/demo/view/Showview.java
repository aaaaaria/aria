package com.brainhealthtest.demo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import com.brainhealthtest.R;
import com.brainhealthtest.demo.activity.MainActivity;
import com.brainhealthtest.demo.util.ProUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class Showview extends View
{
    public static Paint erasePaint;
    public static boolean isHanging = false;
    public static Bitmap mBitmap;
    public static Bitmap mBitmapBackground = null;
    public static Canvas mCanvas;
    public static Paint mPaint;
    public static Path mPath;
    public static float mX;
    public static float mY;
    public static boolean penOrtouch = false;
    private Bitmap erasemap;
    private Paint mPaintBackground = new Paint(4);
    private Paint p;
    private Bitmap penmap;

    public Showview(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mPath = new Path();
        mPaint = new Paint();
        mBitmap = Bitmap.createBitmap(MainActivity.maxWidth,
                MainActivity.maxHeight, Config.ARGB_4444);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(0);
        mCanvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setColor(-16777216);
        mPaint.setStyle(Style.STROKE);
        mPaint.setStrokeWidth(2.0f);
        erasePaint = new Paint();
        erasePaint.setColor(0);
        mPaint.setStrokeCap(Cap.ROUND);
        mPaint.setStrokeJoin(Join.ROUND);
        this.mPaintBackground.setFilterBitmap(true);
        InputStream is = context.getResources().openRawResource(ImageAdapter.backId[0].intValue());
        Bitmap mbitmap = BitmapFactory.decodeStream(is);
        float scaleX = (float) (((MainActivity.maxWidth) * 1.0d) / (mbitmap.getWidth()));
        float scaleY = (float) (((MainActivity.maxHeight) * 1.0d) / (mbitmap.getHeight()));
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX, scaleY);
        mBitmapBackground = Bitmap.createBitmap(mbitmap, 0, 0, mbitmap.getWidth(), mbitmap.getHeight(), matrix, true);
        try
        {
            is.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        InputStream iss = getResources().openRawResource(R.drawable.earse_icon);
        this.erasemap = BitmapFactory.decodeStream(iss);
        try
        {
            iss.close();
        } catch (IOException e2)
        {
            e2.printStackTrace();
        }
        this.erasemap = createSizeImage(this.erasemap, 20, 20);
        InputStream peniss = getResources()
                .openRawResource(R.drawable.pen_icon);
        this.penmap = BitmapFactory.decodeStream(peniss);
        try
        {
            peniss.close();
        } catch (IOException e22)
        {
            e22.printStackTrace();
        }
        this.penmap = createSizeImage(this.penmap, 20, 20);
        this.p = new Paint();
        this.p.setStyle(Style.FILL);
        this.p.setStrokeJoin(Join.ROUND);
        this.p.setStrokeCap(Cap.ROUND);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        if (!(mBitmapBackground == null || mBitmapBackground.isRecycled()))
        {
            canvas.drawBitmap(
                    mBitmapBackground,
                    (MainActivity.maxWidth - mBitmapBackground.getWidth()) / 2,
                    (MainActivity.maxHeight - mBitmapBackground.getHeight()) / 2 - 100,
                    this.mPaintBackground);
        }
        if (mPaint.getXfermode() == null)
        {
            canvas.drawPath(mPath, mPaint);
            canvas.drawBitmap(this.penmap, mX - 10.0f, mY - 10.0f, this.p);
        } else
        {
            canvas.drawBitmap(this.erasemap, mX - mPaint.getStrokeWidth(), mY - mPaint.getStrokeWidth(), this.p);
            canvas.drawPath(mPath, erasePaint);
        }
        canvas.drawBitmap(mBitmap, 0.0f, 0.0f, this.mPaintBackground);
        postInvalidate();
    }

    public static Bitmap createSizeImage(Bitmap bitmap, int newWidth,
                                         int newHeight)
    {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width == newWidth && height == newHeight)
        {
            return bitmap;
        }
        float scaleWidth = ((float) newWidth) / ((float) width);
        float scaleHeight = ((float) newHeight) / ((float) height);
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static void startDraw(float x, float y)
    {
        mCanvas.drawPath(mPath, mPaint);
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
        isHanging = false;
        if (!ProUtils.showPathAgain)
        {
            ProUtils.moveList = new ArrayList<Float>();
            ProUtils.moveList.add(Float.valueOf(x));
            ProUtils.moveList.add(Float.valueOf(y));
            System.out.println("add start");
        }
    }

    public static void HangDraw(float x, float y)
    {
        mCanvas.drawPath(mPath, mPaint);
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
        isHanging = true;
    }

    public static void moveDraw(float x, float y)
    {
        mPath.quadTo(mX, mY, (mX + x) / 2.0f, (mY + y) / 2.0f);
        mCanvas.drawPath(mPath, mPaint);
        mX = x;
        mY = y;
        if (!ProUtils.showPathAgain)
        {
            ProUtils.moveList.add(Float.valueOf(x));
            ProUtils.moveList.add(Float.valueOf(y));
        }
        ProUtils.HasNotes = true;
    }

    public static void upDraw(float x, float y)
    {
        mCanvas.drawPath(mPath, mPaint);
        mPath.reset();
        if (!ProUtils.showPathAgain)
        {
            ProUtils.moveList.add(Float.valueOf(mPaint.getStrokeWidth()));
            if (mPaint.getXfermode() != null)
            {
                ProUtils.moveList.add(Float.valueOf(-1.0f));
            } else
            {
                ProUtils.moveList.add(Float.valueOf(mPaint.getColor()));
            }
            ProUtils.penDataList.add(ProUtils.moveList);
            System.out.println("add end");
        }
        System.out.println("ending");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();
        if (MainActivity.scrollView.getScrollX() >= (MainActivity.maxWidth / 2) - 8)
        {
            ProUtils.ScrollOrNote = false;
            ProUtils.menuScrolled = false;
        }
        if (ProUtils.ScrollOrNote)
        {
            return super.onTouchEvent(event);
        }
        if (penOrtouch || isHanging)
        {
            penOrtouch = false;
            isHanging = false;
            return super.onTouchEvent(event);
        }
        switch (action)
        {
            case 0:
                startDraw(x, y);
                invalidate();
                break;
            case 1:
                upDraw(x, y);
                break;
            case 2:
                moveDraw(x, y);
                break;
        }
        return true;
    }
}