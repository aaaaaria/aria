package com.brainhealthtest.demo.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;

import com.brainhealthtest.R;


public class ImageAdapter extends BaseAdapter
{
    public static Integer[] backId = new Integer[]{Integer.valueOf(R.drawable.back)};
    private Context mcontext;

    public ImageAdapter(Context mcontext)
    {
        this.mcontext = mcontext;
    }

    public int getCount()
    {
        return backId.length;
    }

    public Object getItem(int position)
    {
        return backId[position];
    }

    public long getItemId(int position)
    {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ImageView i = new ImageView(this.mcontext);
        i.setAdjustViewBounds(true);
        i.setLayoutParams(new LayoutParams(-2, -2));
        return i;
    }
}
