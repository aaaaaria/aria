package com.brainhealthtest.drawingtest;


import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.brainhealthtest.R;

public class LoaderImageView extends LinearLayout
{
    static final int COMPLETE = 0;
    static final int FAILED = 1;

    private Context mContext;
    private ProgressBar mSpinner;
    public ImageView mImage;

    public Handler callbackHandler;

    /**
     * This is used when creating the view in XML To have an image load in XML
     * use the tag
     * 'image="http://developer.android.com/images/dialog_buttons.png"'
     * Replacing the url with your desired image Once you have instantiated the
     * XML view you can call setImageDrawable(url) to change the image
     *
     * @param context
     * @param attrSet
     */
    public LoaderImageView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        final String url = attrSet.getAttributeValue(null, "image");
        if (url != null)
        {
            instantiate(context, url);
        } else
        {
            instantiate(context, null);
        }
    }

    /**
     * This is used when creating the view programatically Once you have
     * instantiated the view you can call setImageDrawable(url) to change the
     * image
     *
     * @param context  the Activity context
     * @param imageUrl the Image URL you wish to load
     */
    public LoaderImageView(final Context context, final String imageUrl)
    {
        super(context);
        instantiate(context, imageUrl);
    }

    /**
     * This is used when creating the view programatically Once you have
     * instantiated the view you can call setImageDrawable(url) to change the
     * image
     *
     * @param context the Activity context
     */
    public LoaderImageView(final Context context)
    {
        super(context);
        mContext = context;

        mImage = new ImageView(mContext);
        mImage.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));

        mSpinner = new ProgressBar(mContext);
        mSpinner.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));

        mSpinner.setIndeterminate(true);

        //addView(mSpinner);
        addView(mImage);
    }

    /**
     * This is used when creating the view programatically Once you have
     * instantiated the view you can call setImageDrawable(url) to change the
     * image
     *
     * @param context  the Activity context
     * @param imageUrl the Image URL you wish to load
     */
    public LoaderImageView(final Context context, final String imageUrl,
                           Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        instantiate(context, imageUrl);
    }

    /**
     * First time loading of the LoaderImageView Sets up the LayoutParams of the
     * view, you can change these to get the required effects you want
     */
    private void instantiate(final Context context, final String imageUrl)
    {
        mContext = context;

        mImage = new ImageView(mContext);
        mImage.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));

        mSpinner = new ProgressBar(mContext);
        mSpinner.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));

        mSpinner.setIndeterminate(true);

        //addView(mSpinner);
        addView(mImage);

        if (imageUrl != null)
        {
            setImageDrawable(imageUrl);
        }
    }

    /**
     * Set's the view's drawable, this uses the internet to retrieve the image
     * don't forget to add the correct permissions to your manifest
     *
     * @param url the url of the image you wish to load
     */
    public void setImageDrawable(String url)
    {
        /*mDrawable = null;
        mSpinner.setVisibility(View.VISIBLE);
		mImage.setVisibility(View.GONE);
		new Thread() {
			public void run() {
				try {
					mDrawable = getDrawableFromUrl(imageUrl);
					imageLoadedHandler.sendEmptyMessage(COMPLETE);
				} catch (MalformedURLException e) {
					imageLoadedHandler.sendEmptyMessage(FAILED);
				} catch (IOException e) {
					imageLoadedHandler.sendEmptyMessage(FAILED);
				}
			};
		}.start();*/
        mImage.setImageResource(R.drawable.t1_1);
        mImage.setVisibility(View.VISIBLE);
    }


    public void setmImage()
    {
        Log.d("STAGE", "image");
    }

    /**
     * Set's the view's drawable, this uses the internet to retrieve the image
     * don't forget to add the correct permissions to your manifest
     */
    public void setImageDrawable(int rtn)
    {
        /*mDrawable = null;
        mSpinner.setVisibility(View.VISIBLE);
		mImage.setVisibility(View.GONE);
		new Thread() {
			public void run() {
				try {
					mDrawable = getDrawableFromUrl(imageUrl);
					imageLoadedHandler.sendEmptyMessage(COMPLETE);
				} catch (MalformedURLException e) {
					imageLoadedHandler.sendEmptyMessage(FAILED);
				} catch (IOException e) {
					imageLoadedHandler.sendEmptyMessage(FAILED);
				}
			};
		}.start();*/
        mImage.setImageResource(rtn);
        mImage.setVisibility(View.VISIBLE);
    }

}
