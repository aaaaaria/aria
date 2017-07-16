package com.brainhealthtest.questions;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brainhealthtest.osshelper.Helper;


public class SingleBalanceView extends LinearLayout
{
    String ques;
    public TextView question, div, div2, txt;
    public Button time1_0, time1_1, time1_2, time1_3, time2_0, time2_1, time2_2, time2_3, time2_4, time2_5, time2_6, time2_7, time2_8, time2_9;
    public int Time1 = -1, Time2 = -1;

    public void Init(String mpair1, Helper helper)
    {
        ques = mpair1;
        question.setText(ques);
    }

    public SingleBalanceView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
        Context mContext = context;
        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        marginLayout.setMargins(2, 2, 2, 2);
        LayoutParams marginLayout2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        marginLayout2.setMargins(2, 2, 2, 2);
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.HORIZONTAL);
        mLayout.setBackgroundColor(Color.BLACK);

        LinearLayout viewLayout_1 = new LinearLayout(mContext);
        viewLayout_1.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout_1.setBackgroundColor(Color.BLACK);
        viewLayout_1.setLayoutParams(marginLayout);
        LinearLayout viewLayout_2 = new LinearLayout(mContext);
        viewLayout_2.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout_2.setBackgroundColor(Color.BLACK);
        viewLayout_2.setLayoutParams(marginLayout);
        LinearLayout viewLayout_3 = new LinearLayout(mContext);
        viewLayout_3.setOrientation(LinearLayout.VERTICAL);
        viewLayout_3.setBackgroundColor(Color.BLACK);
        viewLayout_3.setLayoutParams(marginLayout);

        question = new TextView(mContext);
        question.setWidth(500);
        question.setHeight(204);
        question.setTextSize(16);
        question.setLayoutParams(marginLayout2);
        question.setBackgroundColor(Color.WHITE);

        time1_0 = new Button(mContext);
        time1_0.setWidth(100);
        time1_0.setHeight(100);
        time1_0.setText("0");
        time1_0.setLayoutParams(marginLayout);
        time1_0.setBackgroundColor(Color.GRAY);
        time1_0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Time1 = 0;
                // TODO Auto-generated method stub
                time1_0.setBackgroundColor(Color.GREEN);
                time1_1.setBackgroundColor(Color.GRAY);
                time1_2.setBackgroundColor(Color.GRAY);
                time1_3.setBackgroundColor(Color.GRAY);
            }
        });
        time1_1 = new Button(mContext);
        time1_1.setWidth(100);
        time1_1.setHeight(100);
        time1_1.setText("1");
        time1_1.setLayoutParams(marginLayout);
        time1_1.setBackgroundColor(Color.GRAY);
        time1_1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Time1 = 1;
                // TODO Auto-generated method stub
                time1_1.setBackgroundColor(Color.GREEN);
                time1_0.setBackgroundColor(Color.GRAY);
                time1_2.setBackgroundColor(Color.GRAY);
                time1_3.setBackgroundColor(Color.GRAY);
            }
        });
        time1_2 = new Button(mContext);
        time1_2.setWidth(100);
        time1_2.setHeight(100);
        time1_2.setText("2");
        time1_2.setLayoutParams(marginLayout);
        time1_2.setBackgroundColor(Color.GRAY);
        time1_2.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Time1 = 2;
                // TODO Auto-generated method stub
                time1_2.setBackgroundColor(Color.GREEN);
                time1_0.setBackgroundColor(Color.GRAY);
                time1_1.setBackgroundColor(Color.GRAY);
                time1_3.setBackgroundColor(Color.GRAY);
            }
        });
        time1_3 = new Button(mContext);
        time1_3.setWidth(100);
        time1_3.setHeight(100);
        time1_3.setText("3");
        time1_3.setLayoutParams(marginLayout);
        time1_3.setBackgroundColor(Color.GRAY);
        time1_3.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Time1 = 3;
                // TODO Auto-generated method stub
                time1_3.setBackgroundColor(Color.GREEN);
                time1_0.setBackgroundColor(Color.GRAY);
                time1_2.setBackgroundColor(Color.GRAY);
                time1_1.setBackgroundColor(Color.GRAY);
            }
        });
        time2_0 = new Button(mContext);
        time2_0.setWidth(100);
        time2_0.setHeight(100);
        time2_0.setText("0");
        time2_0.setLayoutParams(marginLayout);
        time2_0.setBackgroundColor(Color.GRAY);
        time2_0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Time2 = 0;
                // TODO Auto-generated method stub
                time2_0.setBackgroundColor(Color.GREEN);
                time2_3.setBackgroundColor(Color.GRAY);
                time2_1.setBackgroundColor(Color.GRAY);
                time2_2.setBackgroundColor(Color.GRAY);
                time2_4.setBackgroundColor(Color.GRAY);
                time2_5.setBackgroundColor(Color.GRAY);
                time2_6.setBackgroundColor(Color.GRAY);
                time2_7.setBackgroundColor(Color.GRAY);
                time2_8.setBackgroundColor(Color.GRAY);
                time2_9.setBackgroundColor(Color.GRAY);

            }
        });
        time2_1 = new Button(mContext);
        time2_1.setWidth(100);
        time2_1.setHeight(100);
        time2_1.setText("1");
        time2_1.setLayoutParams(marginLayout);
        time2_1.setBackgroundColor(Color.GRAY);
        time2_1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Time2 = 1;
                // TODO Auto-generated method stub
                time2_1.setBackgroundColor(Color.GREEN);
                time2_0.setBackgroundColor(Color.GRAY);
                time2_3.setBackgroundColor(Color.GRAY);
                time2_2.setBackgroundColor(Color.GRAY);
                time2_4.setBackgroundColor(Color.GRAY);
                time2_5.setBackgroundColor(Color.GRAY);
                time2_6.setBackgroundColor(Color.GRAY);
                time2_7.setBackgroundColor(Color.GRAY);
                time2_8.setBackgroundColor(Color.GRAY);
                time2_9.setBackgroundColor(Color.GRAY);

            }
        });
        time2_2 = new Button(mContext);
        time2_2.setWidth(100);
        time2_2.setHeight(100);
        time2_2.setText("2");
        time2_2.setLayoutParams(marginLayout);
        time2_2.setBackgroundColor(Color.GRAY);
        time2_2.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Time2 = 2;
                // TODO Auto-generated method stub
                time2_2.setBackgroundColor(Color.GREEN);
                time2_0.setBackgroundColor(Color.GRAY);
                time2_1.setBackgroundColor(Color.GRAY);
                time2_3.setBackgroundColor(Color.GRAY);
                time2_4.setBackgroundColor(Color.GRAY);
                time2_5.setBackgroundColor(Color.GRAY);
                time2_6.setBackgroundColor(Color.GRAY);
                time2_7.setBackgroundColor(Color.GRAY);
                time2_8.setBackgroundColor(Color.GRAY);
                time2_9.setBackgroundColor(Color.GRAY);

            }
        });
        time2_3 = new Button(mContext);
        time2_3.setWidth(100);
        time2_3.setHeight(100);
        time2_3.setText("3");
        time2_3.setLayoutParams(marginLayout);
        time2_3.setBackgroundColor(Color.GRAY);
        time2_3.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Time2 = 3;
                // TODO Auto-generated method stub
                time2_3.setBackgroundColor(Color.GREEN);
                time2_0.setBackgroundColor(Color.GRAY);
                time2_1.setBackgroundColor(Color.GRAY);
                time2_2.setBackgroundColor(Color.GRAY);
                time2_4.setBackgroundColor(Color.GRAY);
                time2_5.setBackgroundColor(Color.GRAY);
                time2_6.setBackgroundColor(Color.GRAY);
                time2_7.setBackgroundColor(Color.GRAY);
                time2_8.setBackgroundColor(Color.GRAY);
                time2_9.setBackgroundColor(Color.GRAY);

            }
        });
        time2_4 = new Button(mContext);
        time2_4.setWidth(100);
        time2_4.setHeight(100);
        time2_4.setText("4");
        time2_4.setLayoutParams(marginLayout);
        time2_4.setBackgroundColor(Color.GRAY);
        time2_4.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Time2 = 4;
                // TODO Auto-generated method stub
                time2_4.setBackgroundColor(Color.GREEN);
                time2_0.setBackgroundColor(Color.GRAY);
                time2_1.setBackgroundColor(Color.GRAY);
                time2_2.setBackgroundColor(Color.GRAY);
                time2_3.setBackgroundColor(Color.GRAY);
                time2_5.setBackgroundColor(Color.GRAY);
                time2_6.setBackgroundColor(Color.GRAY);
                time2_7.setBackgroundColor(Color.GRAY);
                time2_8.setBackgroundColor(Color.GRAY);
                time2_9.setBackgroundColor(Color.GRAY);

            }
        });
        time2_5 = new Button(mContext);
        time2_5.setWidth(100);
        time2_5.setHeight(100);
        time2_5.setText("5");
        time2_5.setLayoutParams(marginLayout);
        time2_5.setBackgroundColor(Color.GRAY);
        time2_5.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Time2 = 5;
                // TODO Auto-generated method stub
                time2_5.setBackgroundColor(Color.GREEN);
                time2_0.setBackgroundColor(Color.GRAY);
                time2_1.setBackgroundColor(Color.GRAY);
                time2_2.setBackgroundColor(Color.GRAY);
                time2_4.setBackgroundColor(Color.GRAY);
                time2_3.setBackgroundColor(Color.GRAY);
                time2_6.setBackgroundColor(Color.GRAY);
                time2_7.setBackgroundColor(Color.GRAY);
                time2_8.setBackgroundColor(Color.GRAY);
                time2_9.setBackgroundColor(Color.GRAY);

            }
        });
        time2_6 = new Button(mContext);
        time2_6.setWidth(100);
        time2_6.setHeight(100);
        time2_6.setText("6");
        time2_6.setLayoutParams(marginLayout);
        time2_6.setBackgroundColor(Color.GRAY);
        time2_6.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Time2 = 6;
                // TODO Auto-generated method stub
                time2_6.setBackgroundColor(Color.GREEN);
                time2_0.setBackgroundColor(Color.GRAY);
                time2_1.setBackgroundColor(Color.GRAY);
                time2_2.setBackgroundColor(Color.GRAY);
                time2_4.setBackgroundColor(Color.GRAY);
                time2_5.setBackgroundColor(Color.GRAY);
                time2_3.setBackgroundColor(Color.GRAY);
                time2_7.setBackgroundColor(Color.GRAY);
                time2_8.setBackgroundColor(Color.GRAY);
                time2_9.setBackgroundColor(Color.GRAY);

            }
        });
        time2_7 = new Button(mContext);
        time2_7.setWidth(100);
        time2_7.setHeight(100);
        time2_7.setText("7");
        time2_7.setLayoutParams(marginLayout);
        time2_7.setBackgroundColor(Color.GRAY);
        time2_7.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Time2 = 7;
                // TODO Auto-generated method stub
                time2_7.setBackgroundColor(Color.GREEN);
                time2_0.setBackgroundColor(Color.GRAY);
                time2_1.setBackgroundColor(Color.GRAY);
                time2_2.setBackgroundColor(Color.GRAY);
                time2_4.setBackgroundColor(Color.GRAY);
                time2_5.setBackgroundColor(Color.GRAY);
                time2_6.setBackgroundColor(Color.GRAY);
                time2_3.setBackgroundColor(Color.GRAY);
                time2_8.setBackgroundColor(Color.GRAY);
                time2_9.setBackgroundColor(Color.GRAY);

            }
        });
        time2_8 = new Button(mContext);
        time2_8.setWidth(100);
        time2_8.setHeight(100);
        time2_8.setText("8");
        time2_8.setLayoutParams(marginLayout);
        time2_8.setBackgroundColor(Color.GRAY);
        time2_8.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Time2 = 8;
                // TODO Auto-generated method stub
                time2_8.setBackgroundColor(Color.GREEN);
                time2_0.setBackgroundColor(Color.GRAY);
                time2_1.setBackgroundColor(Color.GRAY);
                time2_2.setBackgroundColor(Color.GRAY);
                time2_4.setBackgroundColor(Color.GRAY);
                time2_5.setBackgroundColor(Color.GRAY);
                time2_6.setBackgroundColor(Color.GRAY);
                time2_7.setBackgroundColor(Color.GRAY);
                time2_3.setBackgroundColor(Color.GRAY);
                time2_9.setBackgroundColor(Color.GRAY);

            }
        });
        time2_9 = new Button(mContext);
        time2_9.setWidth(100);
        time2_9.setHeight(100);
        time2_9.setText("9");
        time2_9.setLayoutParams(marginLayout);
        time2_9.setBackgroundColor(Color.GRAY);
        time2_9.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Time2 = 9;
                // TODO Auto-generated method stub
                time2_9.setBackgroundColor(Color.GREEN);
                time2_0.setBackgroundColor(Color.GRAY);
                time2_1.setBackgroundColor(Color.GRAY);
                time2_2.setBackgroundColor(Color.GRAY);
                time2_4.setBackgroundColor(Color.GRAY);
                time2_5.setBackgroundColor(Color.GRAY);
                time2_6.setBackgroundColor(Color.GRAY);
                time2_7.setBackgroundColor(Color.GRAY);
                time2_8.setBackgroundColor(Color.GRAY);
                time2_3.setBackgroundColor(Color.GRAY);

            }
        });

        div = new TextView(mContext);
        div.setWidth(10);
        div.setHeight(100);
        div.setTextSize(16);
        div.setLayoutParams(marginLayout);
        div2 = new TextView(mContext);
        div2.setWidth(10);
        div2.setHeight(100);
        div2.setTextSize(16);
        div2.setLayoutParams(marginLayout);

        txt = new TextView(mContext);
        txt.setWidth(200);
        txt.setText("seconds");
        txt.setHeight(202);
        txt.setTextSize(16);
        txt.setLayoutParams(marginLayout2);
        txt.setGravity(Gravity.CENTER);
        txt.setBackgroundColor(Color.GRAY);


        viewLayout_1.addView(time1_0);
        viewLayout_1.addView(time1_1);
        viewLayout_1.addView(div);
        viewLayout_1.addView(time2_0);
        viewLayout_1.addView(time2_1);
        viewLayout_1.addView(time2_2);
        viewLayout_1.addView(time2_3);
        viewLayout_1.addView(time2_4);

        viewLayout_2.addView(time1_2);
        viewLayout_2.addView(time1_3);
        viewLayout_2.addView(div2);
        viewLayout_2.addView(time2_5);
        viewLayout_2.addView(time2_6);
        viewLayout_2.addView(time2_7);
        viewLayout_2.addView(time2_8);
        viewLayout_2.addView(time2_9);

        viewLayout_3.addView(viewLayout_1);
        viewLayout_3.addView(viewLayout_2);
        mLayout.addView(question);
        mLayout.addView(viewLayout_3);
        mLayout.addView(txt);
        addView(mLayout);
        return;
    }

}
