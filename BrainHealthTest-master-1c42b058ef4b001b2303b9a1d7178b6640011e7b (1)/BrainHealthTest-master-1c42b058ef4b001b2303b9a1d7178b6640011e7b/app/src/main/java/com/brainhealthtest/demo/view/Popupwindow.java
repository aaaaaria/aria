package com.brainhealthtest.demo.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


import com.brainhealthtest.R;
import com.brainhealthtest.demo.activity.MainActivity;
import com.brainhealthtest.demo.util.ProUtils;

import java.io.IOException;
import java.io.InputStream;


public class Popupwindow
{
    public static PopupWindow backpopup;
    public static PopupWindow cameraPopup;
    public static PopupWindow colorPopup;
    public static boolean onBackpop = false;
    public static boolean onCamerapop = false;
    public static boolean onPenerasepop = false;
    public static boolean onPenpop = false;
    public static boolean oncolorpop = false;
    public static PopupWindow penerasePopup;
    public static PopupWindow penpopup;

    static class AnonymousClass11 implements OnItemClickListener
    {
        private final /* synthetic */ Activity val$mcontext;

        AnonymousClass11(Activity activity)
        {
            this.val$mcontext = activity;
        }

        public void onItemClick(AdapterView<?> adapterView, View arg1, int arg2, long arg3)
        {
            Popupwindow.backpopup.dismiss();
            ProUtils.backBitmapPosition = arg2;
            InputStream is = this.val$mcontext.getResources().openRawResource(ImageAdapter.backId[arg2].intValue());
            Bitmap mbitmap = BitmapFactory.decodeStream(is);
            float scaleX = (float) ((((double) MainActivity.maxWidth) * 1.0d) / ((double) mbitmap.getWidth()));
            float scaleY = (float) ((((double) MainActivity.maxHeight) * 1.0d) / ((double) mbitmap.getHeight()));
            Matrix matrix = new Matrix();
            matrix.postScale(scaleX, scaleY);
            Showview.mBitmapBackground = Bitmap.createBitmap(mbitmap, 0, 0, mbitmap.getWidth(), mbitmap.getHeight(), matrix, true);
            mbitmap.recycle();
            try
            {
                is.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            Popupwindow.onBackpop = false;
        }
    }

    public static void backpopup(Activity mcontext)
    {
        LinearLayout linear = (LinearLayout) LayoutInflater.from(mcontext).inflate(R.layout.viewbackground, null);
        if (backpopup == null)
        {
            Gallery gallery = (Gallery) linear.findViewById(R.id.backgallery);
            gallery.setAdapter(new ImageAdapter(mcontext));
            gallery.setOnItemClickListener(new AnonymousClass11(mcontext));
            backpopup = new PopupWindow(linear, -2, -2);
        }
        //    backpopup.showAtLocation(mcontext.findViewById(R.id.back), 17, 0, 0);
        backpopup.update();
        onBackpop = true;
    }

    public static void closeBackpopup()
    {
        if (backpopup != null)
        {
            backpopup.dismiss();
            backpopup = null;
        }
        onBackpop = false;
    }

    public static void closePenpopup()
    {
        if (penpopup != null)
        {
            penpopup.dismiss();
            penpopup = null;
        }
        onPenpop = false;
    }

    public static void closeColorpop()
    {
        if (colorPopup != null)
        {
            colorPopup.dismiss();
            colorPopup = null;
        }
        oncolorpop = false;
    }

    public static void closePenerasepop()
    {
        if (penerasePopup != null)
        {
            penerasePopup.dismiss();
            penerasePopup = null;
        }
        onPenerasepop = false;
    }

    public static void closePencamerapop()
    {
        if (cameraPopup != null)
        {
            cameraPopup.dismiss();
            cameraPopup = null;
        }
        onCamerapop = false;
    }
}
