package com.brainhealthtest.drawingtest;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brainhealthtest.osshelper.Helper;


public class DrawClockView extends LinearLayout
{
    int totQuestions;
    Context mContext;
    TextView view1, view2, view3, view4;
    Button button1_0, button1_1, button1_8, button1_9,
            button2_0, button2_1, button2_2, button2_3, button2_9,
            button3_0, button3_1, button3_8, button3_9;
    public int score1 = -1, score2 = -1, score3 = -1;
    public EditText time, Observations;
    public Handler callbackHandler;

    public DrawClockView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        mContext = context;
        //	instantiate(context, null);
    }

    public DrawClockView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public DrawClockView(final Context context, final DrawClockQuestion drawclockQuestion, Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        mContext = context;
        instantiate(context, drawclockQuestion);
    }

    public void Init(DrawClockQuestion drawclockQuestion, Helper helper)
    {
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        //  mLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        marginLayout.setMargins(2, 2, 2, 2);//setMargins(left, top, right, bottom)

        LinearLayout viewLayout_1 = new LinearLayout(mContext);
        viewLayout_1.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout_1.setBackgroundColor(Color.BLACK);
        viewLayout_1.setLayoutParams(marginLayout);
        LinearLayout viewLayout_2 = new LinearLayout(mContext);
        viewLayout_2.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout_2.setBackgroundColor(Color.BLACK);
        viewLayout_2.setLayoutParams(marginLayout);
        LinearLayout viewLayout_3 = new LinearLayout(mContext);
        viewLayout_3.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout_3.setBackgroundColor(Color.BLACK);
        viewLayout_3.setLayoutParams(marginLayout);
        LinearLayout viewLayout_4 = new LinearLayout(mContext);
        viewLayout_4.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout_4.setBackgroundColor(Color.BLACK);
        viewLayout_4.setLayoutParams(marginLayout);
        LinearLayout viewLayout_5 = new LinearLayout(mContext);
        viewLayout_5.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout_5.setBackgroundColor(Color.BLACK);
        viewLayout_5.setLayoutParams(marginLayout);

        TextView textView = new TextView(mContext);
//        textView.setText(DrawClockQuestion.guideWord1);
//        mLayout.addView(textView);
//        textView = new TextView(mContext);
        textView.setTextSize(18);
        textView.setGravity(Gravity.CENTER);
        textView.setText(drawclockQuestion.title);
        mLayout.addView(textView);

        view1 = new TextView(mContext);
        view1.setText("Time to completion(min:sec): ");
        view1.setWidth(750);
        view1.setHeight(80);
        view1.setTextSize(13);/////////////////////////
        view1.setBackgroundColor(Color.WHITE);
        view1.setLayoutParams(marginLayout);
        view1.setGravity(Gravity.CENTER);
        viewLayout_1.addView(view1);

        time = new EditText(mContext);
        time.setWidth(660);
        time.setHeight(80);
        time.setTextSize(13);/////////////////////////
        time.setBackgroundColor(Color.WHITE);
        time.setLayoutParams(marginLayout);
        time.setGravity(Gravity.CENTER);
        viewLayout_1.addView(time);
        mLayout.addView(viewLayout_1);

        view2 = new TextView(mContext);
        view2.setText("Rotated paper while placing numerals/numeral substitutes ");
        view2.setWidth(750);
        view2.setHeight(100);
        view2.setTextSize(13);/////////////////////////
        view2.setBackgroundColor(Color.WHITE);
        view2.setLayoutParams(marginLayout);
        view2.setGravity(Gravity.CENTER);
        viewLayout_2.addView(view2);

        button1_0 = new Button(mContext);
        button1_0.setText("0\n No ");
        button1_0.setWidth(125);
        button1_0.setHeight(100);
        button1_0.setTextSize(13);/////////////////////////
        button1_0.setBackgroundColor(Color.GRAY);
        button1_0.setLayoutParams(marginLayout);
        button1_0.setGravity(Gravity.CENTER);
        button1_0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score1 = 0;
                button1_0.setBackgroundColor(Color.GREEN);
                button1_1.setBackgroundColor(Color.GRAY);
                button1_8.setBackgroundColor(Color.GRAY);
                button1_9.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_2.addView(button1_0);
        button1_1 = new Button(mContext);
        button1_1.setText("1\n Yes ");
        button1_1.setWidth(125);
        button1_1.setHeight(100);
        button1_1.setTextSize(13);/////////////////////////
        button1_1.setBackgroundColor(Color.GRAY);
        button1_1.setLayoutParams(marginLayout);
        button1_1.setGravity(Gravity.CENTER);
        button1_1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score1 = 1;
                button1_1.setBackgroundColor(Color.GREEN);
                button1_0.setBackgroundColor(Color.GRAY);
                button1_8.setBackgroundColor(Color.GRAY);
                button1_9.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_2.addView(button1_1);
        button1_8 = new Button(mContext);
        button1_8.setText("8\n N/A ");
        button1_8.setWidth(200);
        button1_8.setHeight(100);
        button1_8.setTextSize(13);/////////////////////////
        button1_8.setBackgroundColor(Color.GRAY);
        button1_8.setLayoutParams(marginLayout);
        button1_8.setGravity(Gravity.CENTER);
        button1_8.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score1 = 8;
                button1_8.setBackgroundColor(Color.GREEN);
                button1_1.setBackgroundColor(Color.GRAY);
                button1_0.setBackgroundColor(Color.GRAY);
                button1_9.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_2.addView(button1_8);
        button1_9 = new Button(mContext);
        button1_9.setText("9\n UnKnown ");
        button1_9.setWidth(200);
        button1_9.setHeight(100);
        button1_9.setTextSize(13);/////////////////////////
        button1_9.setBackgroundColor(Color.GRAY);
        button1_9.setLayoutParams(marginLayout);
        button1_9.setGravity(Gravity.CENTER);
        button1_9.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score1 = 9;
                button1_9.setBackgroundColor(Color.GREEN);
                button1_1.setBackgroundColor(Color.GRAY);
                button1_8.setBackgroundColor(Color.GRAY);
                button1_0.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_2.addView(button1_9);
        mLayout.addView(viewLayout_2);

        view3 = new TextView(mContext);
        view3.setText("Attempt to self-correct significant error ");
        view3.setWidth(500);
        view3.setHeight(150);
        view3.setTextSize(13);/////////////////////////
        view3.setBackgroundColor(Color.WHITE);
        view3.setLayoutParams(marginLayout);
        view3.setGravity(Gravity.CENTER);
        viewLayout_3.addView(view3);

        button2_0 = new Button(mContext);
        button2_0.setText("0\n No ");
        button2_0.setWidth(100);
        button2_0.setHeight(150);
        button2_0.setTextSize(13);/////////////////////////
        button2_0.setBackgroundColor(Color.GRAY);
        button2_0.setLayoutParams(marginLayout);
        button2_0.setGravity(Gravity.CENTER);
        button2_0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score2 = 0;
                button2_0.setBackgroundColor(Color.GREEN);
                button2_1.setBackgroundColor(Color.GRAY);
                button2_2.setBackgroundColor(Color.GRAY);
                button2_3.setBackgroundColor(Color.GRAY);
                button2_9.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_3.addView(button2_0);
        button2_1 = new Button(mContext);
        button2_1.setText("1\n Yes,result correct ");
        button2_1.setWidth(200);
        button2_1.setHeight(150);
        button2_1.setTextSize(13);/////////////////////////
        button2_1.setBackgroundColor(Color.GRAY);
        button2_1.setLayoutParams(marginLayout);
        button2_1.setGravity(Gravity.CENTER);
        button2_1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score2 = 1;
                button2_1.setBackgroundColor(Color.GREEN);
                button2_0.setBackgroundColor(Color.GRAY);
                button2_2.setBackgroundColor(Color.GRAY);
                button2_3.setBackgroundColor(Color.GRAY);
                button2_9.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_3.addView(button2_1);
        button2_2 = new Button(mContext);
        button2_2.setText("2\n Yes, result NOT correct ");
        button2_2.setWidth(200);
        button2_2.setHeight(150);
        button2_2.setTextSize(13);/////////////////////////
        button2_2.setBackgroundColor(Color.GRAY);
        button2_2.setLayoutParams(marginLayout);
        button2_2.setGravity(Gravity.CENTER);
        button2_2.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score2 = 2;
                button2_2.setBackgroundColor(Color.GREEN);
                button2_1.setBackgroundColor(Color.GRAY);
                button2_0.setBackgroundColor(Color.GRAY);
                button2_3.setBackgroundColor(Color.GRAY);
                button2_9.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_3.addView(button2_2);
        button2_3 = new Button(mContext);
        button2_3.setText("3\n No Errors ");
        button2_3.setWidth(200);
        button2_3.setHeight(150);
        button2_3.setTextSize(13);/////////////////////////
        button2_3.setBackgroundColor(Color.GRAY);
        button2_3.setLayoutParams(marginLayout);
        button2_3.setGravity(Gravity.CENTER);
        button2_3.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score2 = 3;
                button2_3.setBackgroundColor(Color.GREEN);
                button2_1.setBackgroundColor(Color.GRAY);
                button2_2.setBackgroundColor(Color.GRAY);
                button2_0.setBackgroundColor(Color.GRAY);
                button2_9.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_3.addView(button2_3);
        button2_9 = new Button(mContext);
        button2_9.setText("9\n Unknown ");
        button2_9.setWidth(200);
        button2_9.setHeight(150);
        button2_9.setTextSize(13);/////////////////////////
        button2_9.setBackgroundColor(Color.GRAY);
        button2_9.setLayoutParams(marginLayout);
        button2_9.setGravity(Gravity.CENTER);
        button2_9.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score2 = 9;
                button2_9.setBackgroundColor(Color.GREEN);
                button2_1.setBackgroundColor(Color.GRAY);
                button2_2.setBackgroundColor(Color.GRAY);
                button2_3.setBackgroundColor(Color.GRAY);
                button2_0.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_3.addView(button2_9);
        mLayout.addView(viewLayout_3);

        view4 = new TextView(mContext);
        view4.setText("Requested reminder of time for hand-setting ");
        view4.setWidth(750);
        view4.setHeight(100);
        view4.setTextSize(13);/////////////////////////
        view4.setBackgroundColor(Color.WHITE);
        view4.setLayoutParams(marginLayout);
        view4.setGravity(Gravity.CENTER);
        viewLayout_4.addView(view4);

        button3_0 = new Button(mContext);
        button3_0.setText("0\n No ");
        button3_0.setWidth(125);
        button3_0.setHeight(100);
        button3_0.setTextSize(13);/////////////////////////
        button3_0.setBackgroundColor(Color.GRAY);
        button3_0.setLayoutParams(marginLayout);
        button3_0.setGravity(Gravity.CENTER);
        button3_0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score3 = 0;
                button3_0.setBackgroundColor(Color.GREEN);
                button3_1.setBackgroundColor(Color.GRAY);
                button3_8.setBackgroundColor(Color.GRAY);
                button3_9.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_4.addView(button3_0);

        button3_1 = new Button(mContext);
        button3_1.setText("1\n Yes ");
        button3_1.setWidth(125);
        button3_1.setHeight(100);
        button3_1.setTextSize(13);/////////////////////////
        button3_1.setBackgroundColor(Color.GRAY);
        button3_1.setLayoutParams(marginLayout);
        button3_1.setGravity(Gravity.CENTER);
        button3_1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score3 = 1;
                button3_1.setBackgroundColor(Color.GREEN);
                button3_0.setBackgroundColor(Color.GRAY);
                button3_8.setBackgroundColor(Color.GRAY);
                button3_9.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_4.addView(button3_1);

        button3_8 = new Button(mContext);
        button3_8.setText("8\n N/A ");
        button3_8.setWidth(200);
        button3_8.setHeight(100);
        button3_8.setTextSize(13);/////////////////////////
        button3_8.setBackgroundColor(Color.GRAY);
        button3_8.setLayoutParams(marginLayout);
        button3_8.setGravity(Gravity.CENTER);
        button3_8.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score3 = 8;
                button3_8.setBackgroundColor(Color.GREEN);
                button3_1.setBackgroundColor(Color.GRAY);
                button3_0.setBackgroundColor(Color.GRAY);
                button3_9.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_4.addView(button3_8);
        button3_9 = new Button(mContext);
        button3_9.setText("9\n UnKnown ");
        button3_9.setWidth(200);
        button3_9.setHeight(100);
        button3_9.setTextSize(13);/////////////////////////
        button3_9.setBackgroundColor(Color.GRAY);
        button3_9.setLayoutParams(marginLayout);
        button3_9.setGravity(Gravity.CENTER);
        button3_9.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score3 = 9;
                button3_9.setBackgroundColor(Color.GREEN);
                button3_1.setBackgroundColor(Color.GRAY);
                button3_8.setBackgroundColor(Color.GRAY);
                button3_0.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_4.addView(button3_9);

        if (drawclockQuestion.num == 0)
        {
            mLayout.addView(viewLayout_4);
        }


        view4 = new TextView(mContext);
        view4.setText("Describe Other Observations:  ");
        view4.setWidth(750);
        view4.setHeight(100);
        view4.setTextSize(13);/////////////////////////
        view4.setBackgroundColor(Color.WHITE);
        view4.setLayoutParams(marginLayout);
        view4.setGravity(Gravity.CENTER);
        viewLayout_5.addView(view4);

        Observations = new EditText(mContext);
        Observations.setWidth(660);
        Observations.setHeight(100);
        Observations.setTextSize(13);/////////////////////////
        Observations.setBackgroundColor(Color.WHITE);
        Observations.setLayoutParams(marginLayout);
        Observations.setGravity(Gravity.CENTER);
        viewLayout_5.addView(Observations);
        mLayout.addView(viewLayout_5);

        addView(mLayout);
        return;


    }

    private void instantiate(final Context context, final DrawClockQuestion drawing)
    {
        DrawClockQuestion drawclockQuestion = new DrawClockQuestion();
        return;
    }

}
