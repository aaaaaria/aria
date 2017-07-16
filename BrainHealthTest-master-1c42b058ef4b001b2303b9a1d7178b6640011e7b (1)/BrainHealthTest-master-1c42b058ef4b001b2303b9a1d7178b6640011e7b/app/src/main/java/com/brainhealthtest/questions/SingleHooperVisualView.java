package com.brainhealthtest.questions;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brainhealthtest.osshelper.Helper;


public class SingleHooperVisualView extends LinearLayout
{
    String ques, point;
    public TextView question;
    public Button score0, score1, score5, score6, type1, type2, type3, type4, type5;
    public int Score = -1, Type = -1;


    public void Init(String mpair1, String mpair2, Helper helper)
    {
        ques = mpair1;
        point = mpair2;
        question.setText(ques);
        score5.setText(point);
        if (point == "n/a")
            score5.setEnabled(false);
    }

    public SingleHooperVisualView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
        Context mContext = context;
        // TODO Auto-generated constructor stub
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.HORIZONTAL);
        mLayout.setBackgroundColor(Color.BLACK);
        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        marginLayout.setMargins(2, 2, 2, 2);

        question = new TextView(mContext);
        question.setWidth(300);
        question.setHeight(100);
        question.setTextSize(16);
        question.setLayoutParams(marginLayout);
        question.setBackgroundColor(Color.WHITE);
        mLayout.addView(question);

        score1 = new Button(mContext);
        score1.setWidth(110);
        score1.setHeight(100);
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
                score1.setBackgroundColor(Color.GREEN);
                score0.setBackgroundColor(Color.GRAY);
                score5.setBackgroundColor(Color.GRAY);
                score6.setBackgroundColor(Color.GRAY);
            }
        });
        score0 = new Button(mContext);
        score0.setWidth(110);
        score0.setHeight(100);
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
                score5.setBackgroundColor(Color.GRAY);
                score6.setBackgroundColor(Color.GRAY);
            }
        });
        score5 = new Button(mContext);
        score5.setWidth(110);
        score5.setHeight(100);
        score5.setTextSize(12);
        score5.setLayoutParams(marginLayout);
        score5.setBackgroundColor(Color.GRAY);
        score5.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Score = 5;
                // TODO Auto-generated method stub
                score5.setBackgroundColor(Color.GREEN);
                score1.setBackgroundColor(Color.GRAY);
                score0.setBackgroundColor(Color.GRAY);
                score6.setBackgroundColor(Color.GRAY);
            }
        });
        score6 = new Button(mContext);
        score6.setWidth(110);
        score6.setHeight(90);
        score6.setText("6");
        score6.setLayoutParams(marginLayout);
        score6.setBackgroundColor(Color.GRAY);
        score6.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Score = 6;
                // TODO Auto-generated method stub
                score6.setBackgroundColor(Color.GREEN);
                score1.setBackgroundColor(Color.GRAY);
                score0.setBackgroundColor(Color.GRAY);
                score5.setBackgroundColor(Color.GRAY);
            }
        });
        mLayout.addView(score0);
        mLayout.addView(score5);
        mLayout.addView(score1);
        mLayout.addView(score6);

        type1 = new Button(mContext);
        type1.setWidth(110);
        type1.setHeight(90);
        type1.setLayoutParams(marginLayout);
        type1.setBackgroundColor(Color.GRAY);
        type1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Type = 1;
                // TODO Auto-generated method stub
                type1.setBackgroundColor(Color.YELLOW);
                type2.setBackgroundColor(Color.GRAY);
                type3.setBackgroundColor(Color.GRAY);
                type4.setBackgroundColor(Color.GRAY);
                type5.setBackgroundColor(Color.GRAY);
            }
        });
        type2 = new Button(mContext);
        type2.setWidth(110);
        type2.setHeight(90);
        type2.setLayoutParams(marginLayout);
        type2.setBackgroundColor(Color.GRAY);
        type2.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Type = 2;
                // TODO Auto-generated method stub
                type2.setBackgroundColor(Color.YELLOW);
                type1.setBackgroundColor(Color.GRAY);
                type3.setBackgroundColor(Color.GRAY);
                type4.setBackgroundColor(Color.GRAY);
                type5.setBackgroundColor(Color.GRAY);
            }
        });
        type3 = new Button(mContext);
        type3.setWidth(110);
        type3.setHeight(90);
        type3.setLayoutParams(marginLayout);
        type3.setBackgroundColor(Color.GRAY);
        type3.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Type = 3;
                // TODO Auto-generated method stub
                type3.setBackgroundColor(Color.YELLOW);
                type2.setBackgroundColor(Color.GRAY);
                type1.setBackgroundColor(Color.GRAY);
                type4.setBackgroundColor(Color.GRAY);
                type5.setBackgroundColor(Color.GRAY);
            }
        });
        type4 = new Button(mContext);
        type4.setWidth(110);
        type4.setHeight(90);
        type4.setLayoutParams(marginLayout);
        type4.setBackgroundColor(Color.GRAY);
        type4.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Type = 4;
                // TODO Auto-generated method stub
                type4.setBackgroundColor(Color.YELLOW);
                type2.setBackgroundColor(Color.GRAY);
                type3.setBackgroundColor(Color.GRAY);
                type1.setBackgroundColor(Color.GRAY);
                type5.setBackgroundColor(Color.GRAY);
            }
        });
        type5 = new Button(mContext);
        type5.setWidth(110);
        type5.setHeight(90);
        type5.setLayoutParams(marginLayout);
        type5.setBackgroundColor(Color.GRAY);
        type5.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Type = 5;
                // TODO Auto-generated method stub
                type5.setBackgroundColor(Color.YELLOW);
                type2.setBackgroundColor(Color.GRAY);
                type3.setBackgroundColor(Color.GRAY);
                type4.setBackgroundColor(Color.GRAY);
                type1.setBackgroundColor(Color.GRAY);
            }
        });
        mLayout.addView(type1);
        mLayout.addView(type2);
        mLayout.addView(type3);
        mLayout.addView(type4);
        mLayout.addView(type5);
        addView(mLayout);
        return;
    }

}
