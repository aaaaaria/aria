package com.brainhealthtest.questions;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brainhealthtest.osshelper.Helper;


public class SingleMathFluencyView extends LinearLayout
{
    private String pair1;
    private TextView word1;
    public EditText answertxt1, answertxt2, answertxt3;
    public Handler callbackHandler;
    Context mContext;


    public void Init(String mpair1, Helper helper)
    {
        pair1 = mpair1;
        word1.setText(pair1);

    }

    public SingleMathFluencyView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
        Context mContext = context;
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.HORIZONTAL);
        mLayout.setBackgroundColor(Color.BLACK);
        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        marginLayout.setMargins(2, 2, 2, 2);

        word1 = new TextView(mContext);
        word1.setWidth(350);
        word1.setHeight(100);
        word1.setTextSize(15);
        word1.setLayoutParams(marginLayout);
        word1.setBackgroundColor(Color.LTGRAY);
        mLayout.addView(word1);

        answertxt1 = new EditText(mContext);
        answertxt1.setWidth(350);
        answertxt1.setHeight(100);
        answertxt1.setTextSize(15);
        answertxt1.setBackgroundColor(Color.WHITE);
        answertxt1.setLayoutParams(marginLayout);
        mLayout.addView(answertxt1);

        answertxt2 = new EditText(mContext);
        answertxt2.setWidth(350);
        answertxt2.setHeight(100);
        answertxt2.setTextSize(15);
        answertxt2.setBackgroundColor(Color.WHITE);
        answertxt2.setLayoutParams(marginLayout);
        mLayout.addView(answertxt2);

        answertxt3 = new EditText(mContext);
        answertxt3.setWidth(350);
        answertxt3.setHeight(100);
        answertxt3.setTextSize(15);
        answertxt3.setBackgroundColor(Color.WHITE);
        answertxt3.setLayoutParams(marginLayout);
        mLayout.addView(answertxt3);

        addView(mLayout);
        return;
    }

    public SingleMathFluencyView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        instantiate(context, null);
    }

    public SingleMathFluencyView(final Context context, final String imageUrl, Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        instantiate(context, imageUrl);
    }

    private void instantiate(final Context context, final String imageUrl)
    {
        Context mContext = context;
    }

}
