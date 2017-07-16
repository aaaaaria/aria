package com.brainhealthtest.drawingtest;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.brainhealthtest.osshelper.Helper;


public class SingleClockDrawingPreView extends LinearLayout
{
    public Button score0, score1, score8, score9;
    public int Score = -1;
    private String pair1;
    private EditText pair;
    private Handler callbackHandler;

    public void Init(String mpair1, Helper helper)
    {
        pair1 = mpair1;
        pair.setText(pair1);

    }

    public SingleClockDrawingPreView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub

        Context mContext = context;
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        marginLayout.setMargins(2, 2, 2, 2);


        pair = new EditText(context);
        //pair.setText("North\nSouth\n");
        pair.setWidth(900);
        pair.setHeight(120);
        pair.setEnabled(false);
        pair.setTextSize(13);
        pair.setTextColor(Color.BLACK);
        pair.setLayoutParams(marginLayout);
        //pair.setTextColor(Color.WHITE);
        pair.setBackgroundColor(Color.WHITE);

        score0 = new Button(mContext);
        score0.setWidth(120);
        score0.setHeight(120);
        score0.setText("0=No");
        score0.setLayoutParams(marginLayout);
        score0.setBackgroundColor(Color.GRAY);
        score0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Score = 0;
                // TODO Auto-generated method stub
                score0.setBackgroundColor(Color.GREEN);
                score1.setBackgroundColor(Color.GRAY);
                score8.setBackgroundColor(Color.GRAY);
                score9.setBackgroundColor(Color.GRAY);
            }
        });
        score1 = new Button(mContext);
        score1.setWidth(120);
        score1.setHeight(120);
        score1.setText("1=Yes");
        score1.setLayoutParams(marginLayout);
        score1.setBackgroundColor(Color.GRAY);
        score1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Score = 1;
                // TODO Auto-generated method stub
                score1.setBackgroundColor(Color.GREEN);
                score0.setBackgroundColor(Color.GRAY);
                score8.setBackgroundColor(Color.GRAY);
                score9.setBackgroundColor(Color.GRAY);
            }
        });
        score8 = new Button(mContext);
        score8.setWidth(120);
        score8.setHeight(120);
        score8.setText("8=n/a");
        score8.setLayoutParams(marginLayout);
        score8.setBackgroundColor(Color.GRAY);
        score8.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Score = 8;
                // TODO Auto-generated method stub
                score1.setBackgroundColor(Color.GRAY);
                score0.setBackgroundColor(Color.GRAY);
                score8.setBackgroundColor(Color.GREEN);
                score9.setBackgroundColor(Color.GRAY);
            }
        });
        score9 = new Button(mContext);
        score9.setWidth(120);
        score9.setHeight(120);
        score9.setText("9=unknown");
        score9.setLayoutParams(marginLayout);
        score9.setBackgroundColor(Color.GRAY);
        score9.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Score = 9;
                // TODO Auto-generated method stub
                score0.setBackgroundColor(Color.GRAY);
                score1.setBackgroundColor(Color.GRAY);
                score9.setBackgroundColor(Color.GREEN);
                score8.setBackgroundColor(Color.GRAY);
            }
        });
        mLayout.addView(pair);
        mLayout.addView(score0);
        mLayout.addView(score1);
        mLayout.addView(score8);
        mLayout.addView(score9);
        addView(mLayout);
        return;

    }

    public SingleClockDrawingPreView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        instantiate(context, null);
    }

    public SingleClockDrawingPreView(final Context context, final String imageUrl, Handler handler)
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
