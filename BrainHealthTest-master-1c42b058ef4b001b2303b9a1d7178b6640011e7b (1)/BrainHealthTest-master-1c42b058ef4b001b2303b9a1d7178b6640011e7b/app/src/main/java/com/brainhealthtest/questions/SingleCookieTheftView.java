package com.brainhealthtest.questions;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brainhealthtest.osshelper.Helper;


public class SingleCookieTheftView extends LinearLayout
{
    private String pair;
    private TextView word;
    public Button score0, score1, score8;
    public boolean type1_1;
    public int Score = -1;
    public Button type1, type2, type3, type4, type5, type6, type7;
    public EditText Answer1, Answer2, Answer3, time;
    public Handler callbackHandler;

    public void Init(String mpair1, int num, Helper helper)
    {
        pair = mpair1;
        word.setText(pair);
        if (num == 1)
        {
            score8.setText("");
            score8.setVisibility(View.INVISIBLE);
        } else
            score8.setText("8");
    }

    public SingleCookieTheftView(Context context)
    {
        super(context);
        Context mContext = context;
        // TODO Auto-generated constructor stub
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        marginLayout.setMargins(2, 2, 2, 2);

        word = new TextView(mContext);
        word.setWidth(500);
        word.setHeight(60);
        word.setTextSize(15);
        word.setLayoutParams(marginLayout);
        word.setBackgroundColor(Color.WHITE);
        mLayout.addView(word);

        score0 = new Button(mContext);
        score0.setWidth(300);
        score0.setHeight(60);
        score0.setText("0");
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
            }
        });
        mLayout.addView(score0);

        score1 = new Button(mContext);
        score1.setWidth(300);
        score1.setHeight(60);
        score1.setText("1");
        score1.setLayoutParams(marginLayout);
        score1.setBackgroundColor(Color.GRAY);
        score1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Score = 1;
                // TODO Auto-generated method stub
                score0.setBackgroundColor(Color.GRAY);
                score1.setBackgroundColor(Color.GREEN);
                score8.setBackgroundColor(Color.GRAY);
            }
        });
        mLayout.addView(score1);

        score8 = new Button(mContext);
        score8.setWidth(300);
        score8.setHeight(60);
        score8.setLayoutParams(marginLayout);
        score8.setBackgroundColor(Color.GRAY);
        score8.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Score = 8;
                // TODO Auto-generated method stub
                score0.setBackgroundColor(Color.GRAY);
                score8.setBackgroundColor(Color.GREEN);
                score1.setBackgroundColor(Color.GRAY);
            }
        });
        mLayout.addView(score8);

        addView(mLayout);
        return;
    }

    public SingleCookieTheftView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        instantiate(context, null);
    }

    public SingleCookieTheftView(final Context context, final String imageUrl, Handler handler)
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
