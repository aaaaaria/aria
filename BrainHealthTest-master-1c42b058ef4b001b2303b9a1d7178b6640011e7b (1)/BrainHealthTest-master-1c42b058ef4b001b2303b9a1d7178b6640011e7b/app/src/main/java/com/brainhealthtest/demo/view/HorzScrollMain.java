package com.brainhealthtest.demo.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.HorizontalScrollView;

import com.brainhealthtest.demo.activity.MainActivity;
import com.brainhealthtest.demo.util.ProUtils;


public class HorzScrollMain extends HorizontalScrollView
{
    private HorizontalScrollView me;

    class GLobalLayoutListener implements OnGlobalLayoutListener
    {
        private ViewGroup parent;
        private int scrollIndex;
        private View[] view;

        public GLobalLayoutListener(ViewGroup parent, View[] view, int scrollIndex)
        {
            this.parent = parent;
            this.view = view;
            this.scrollIndex = scrollIndex;
        }

        public void onGlobalLayout()
        {
            HorzScrollMain.this.me = HorzScrollMain.this;
            HorzScrollMain.this.me.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            int w = HorzScrollMain.this.me.getMeasuredWidth();
            int h = HorzScrollMain.this.me.getMeasuredHeight();
            this.parent.removeViews(0, this.view.length);
            for (int i = 0; i < this.view.length; i++)
            {
                this.view[i].setVisibility(0);
                if (i == this.scrollIndex)
                {
                    this.parent.addView(this.view[i], w, h);
                } else
                {
                    this.parent.addView(this.view[i], MainActivity.maxWidth / 2, h);
                }
            }
            new Handler().post(new Runnable()
            {
                public void run()
                {
                    HorzScrollMain.this.me.scrollTo(0, 0);
                }
            });
        }
    }

    public HorzScrollMain(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    public HorzScrollMain(Context context)
    {
        super(context);
        init(context);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        return false;
    }

    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY)
    {
        if (scrollX > MainActivity.maxWidth)
        {

            ProUtils.ScrollOrNote = false;
            super.smoothScrollTo(MainActivity.maxWidth / 2, 0);
            return;
        }
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    public boolean onTouchEvent(MotionEvent ev)
    {
        if (!ProUtils.scroll_left_Direction)
        {
            return false;
        }
        if (MainActivity.scrollView.getScrollX() < (MainActivity.maxWidth / 2) - 20)
        {
            return super.onTouchEvent(ev);
        }
        ProUtils.ScrollOrNote = false;
        MainActivity.scrollView.scrollTo(MainActivity.maxWidth / 2, 0);
        System.out.println("scroll:" + MainActivity.scrollView.getScrollX());
        ProUtils.menuScrolled = false;
        return false;
    }

    public void init(Context context)
    {
        setHorizontalFadingEdgeEnabled(false);
        setVerticalFadingEdgeEnabled(false);
    }

    public void initView(View[] viewgroup, int scrollIndex)
    {
        ViewGroup parent = (ViewGroup) getChildAt(0);
        for (int i = 0; i < viewgroup.length; i++)
        {
            viewgroup[i].setVisibility(0);
            parent.addView(viewgroup[i]);
        }
        getViewTreeObserver().addOnGlobalLayoutListener(new GLobalLayoutListener(parent, viewgroup, scrollIndex));
    }
}