package com.brainhealthtest.questions;


import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brainhealthtest.osshelper.Helper;


public class SingleMathFluencyViewBeta extends LinearLayout
{
    private String pair1, pair2;
    private TextView word1, word2;
    public EditText answertxt1, answertxt2;
    public boolean type1_1;
    public Handler callbackHandler;

    public void Init(String mpair1, String mpair2, int num, Helper helper)
    {
        pair1 = mpair1;
        word1.setText(pair1);
        if (num == 1)
        {
            pair2 = mpair2;
            word2.setText(pair2);
        } else
        {
            //word2.setText("");
            pair2 = mpair2;
            word2.setVisibility(View.INVISIBLE); //
            answertxt2.setVisibility(View.INVISIBLE);
        }

    }

    public SingleMathFluencyViewBeta(Context context)
    {
        super(context);
        Context mContext = context;
        // TODO Auto-generated constructor stub
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.HORIZONTAL);
        mLayout.setBackgroundColor(Color.BLACK);
        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        marginLayout.setMargins(2, 2, 2, 2);

        word1 = new TextView(mContext);
        word1.setWidth(450);
        word1.setHeight(100);
        word1.setTextSize(15);
        word1.setLayoutParams(marginLayout);
        word1.setBackgroundColor(Color.LTGRAY);
        mLayout.addView(word1);

        answertxt1 = new EditText(mContext);
        answertxt1.setWidth(240);
        answertxt1.setHeight(100);
        answertxt1.setTextSize(15);
        answertxt1.setBackgroundColor(Color.WHITE);
        answertxt1.setLayoutParams(marginLayout);
        mLayout.addView(answertxt1);

        word2 = new TextView(mContext);
        word2.setWidth(480);
        word2.setHeight(100);
        word2.setTextSize(15);
        word2.setLayoutParams(marginLayout);
        word2.setBackgroundColor(Color.LTGRAY);
        mLayout.addView(word2);

        answertxt2 = new EditText(mContext);
        answertxt2.setWidth(240);
        answertxt2.setHeight(100);
        answertxt2.setTextSize(15);
        answertxt2.setBackgroundColor(Color.WHITE);
        answertxt2.setLayoutParams(marginLayout);
        mLayout.addView(answertxt2);

        addView(mLayout);
        return;

    }

    public SingleMathFluencyViewBeta(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        instantiate(context, null);
    }

    public SingleMathFluencyViewBeta(final Context context, final String imageUrl, Handler handler)
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

