
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

import java.util.ArrayList;
import java.util.List;


public class ClockDrawingPreView2 extends LinearLayout
{
    int totQuestions, totQuestions2, totQuestions3;
    Context mContext;
    TextView view1, view2, view3,
            view1_2, view1_3, view1_4, view1_6, view1_7,
            view21_1, view21_2, view22_1, view22_2, view23_1, view23_2, view2_4,
            view3_1, view3_2;
    Button button12_0, button12_1, button13_0, button13_1, button13_2, button13_3, button13_4, button14_0, button14_1, button14_2, button14_8, button16_0, button16_1, button16_2, button17_0, button17_1, button17_2,
            button31_0, button31_1, button31_2, button31_3, button31_4;
    public int score12 = -1, score13 = -1, score14 = -1, score16 = -1, score17 = -1, score31 = -1;
    public EditText txt12_1, txt12_2, txt16, txt17, Observations;
    public Handler callbackHandler;
    public List<SingleClockDrawingPreView2> cdp2_1, cdp2_2, cdp2_3;

    public ClockDrawingPreView2(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        mContext = context;
        //	instantiate(context, null);
    }

    public ClockDrawingPreView2(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public ClockDrawingPreView2(final Context context, final ClockDrawingPreQuestion2 drawingpreQuestion2, Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        mContext = context;
        instantiate(context, drawingpreQuestion2);
    }

    public void Init(ClockDrawingPreQuestion2 clockdrawingpreQusetion2, Helper helper)
    {
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        marginLayout.setMargins(2, 2, 2, 2);//setMargins(left, top, right, bottom)

        LinearLayout viewLayout_1 = new LinearLayout(mContext);
        viewLayout_1.setOrientation(LinearLayout.VERTICAL);
        viewLayout_1.setBackgroundColor(Color.BLACK);
        viewLayout_1.setLayoutParams(marginLayout);
        LinearLayout viewLayout_2 = new LinearLayout(mContext);
        viewLayout_2.setOrientation(LinearLayout.VERTICAL);
        viewLayout_2.setBackgroundColor(Color.BLACK);
        viewLayout_2.setLayoutParams(marginLayout);
        LinearLayout viewLayout_3 = new LinearLayout(mContext);
        viewLayout_3.setOrientation(LinearLayout.VERTICAL);
        viewLayout_3.setBackgroundColor(Color.BLACK);
        viewLayout_3.setLayoutParams(marginLayout);

        TextView textView = new TextView(mContext);
        textView.setTextSize(18);
        textView.setGravity(Gravity.CENTER);
        textView.setText(clockdrawingpreQusetion2.title);
        mLayout.addView(textView);

        view1 = new TextView(mContext);
        view1.setText("Time Setting");
        view1.setWidth(1400);
        view1.setHeight(110);
        view1.setTextSize(16);/////////////////////////
        view1.setBackgroundColor(Color.LTGRAY);
        view1.setLayoutParams(marginLayout);
        view1.setGravity(Gravity.CENTER);
        viewLayout_1.addView(view1);

        LinearLayout linearLayout1_1 = new LinearLayout(mContext);
        linearLayout1_1.setOrientation(LinearLayout.VERTICAL);
        linearLayout1_1.setBackgroundColor(Color.BLACK);
        linearLayout1_1.setBackgroundColor(Color.BLACK);
        linearLayout1_1.setGravity(Gravity.CENTER_HORIZONTAL);
        LinearLayout linearLayout1_2 = new LinearLayout(mContext);
        linearLayout1_2.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout1_2.setBackgroundColor(Color.BLACK);
        LinearLayout linearLayout1_3 = new LinearLayout(mContext);
        linearLayout1_3.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout1_3.setBackgroundColor(Color.BLACK);
        LinearLayout linearLayout1_4 = new LinearLayout(mContext);
        linearLayout1_4.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout1_4.setBackgroundColor(Color.BLACK);
        LinearLayout linearLayout1_5 = new LinearLayout(mContext);
        linearLayout1_5.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout1_5.setBackgroundColor(Color.BLACK);
        LinearLayout linearLayout1_6 = new LinearLayout(mContext);
        linearLayout1_6.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout1_6.setBackgroundColor(Color.BLACK);
        LinearLayout linearLayout1_7 = new LinearLayout(mContext);
        linearLayout1_7.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout1_7.setBackgroundColor(Color.BLACK);

        cdp2_1 = new ArrayList<SingleClockDrawingPreView2>();
        totQuestions = 0;
        for (int i = 0; i < clockdrawingpreQusetion2.ques1.size(); ++i)
        {
            SingleClockDrawingPreView2 singlect2_1 = new SingleClockDrawingPreView2(mContext);
            singlect2_1.Init(clockdrawingpreQusetion2.ques1.get(i), helper);
            linearLayout1_1.addView(singlect2_1);
            cdp2_1.add(singlect2_1);
            totQuestions++;
        }
        viewLayout_1.addView(linearLayout1_1);

        view1_2 = new TextView(mContext);
        view1_2.setText("One straight line is drawn connecting two numerals(If yes, record the target numbers.)");
        view1_2.setWidth(860);
        view1_2.setHeight(120);
        view1_2.setTextSize(13);/////////////////////////
        view1_2.setBackgroundColor(Color.WHITE);
        view1_2.setLayoutParams(marginLayout);
        view1_2.setGravity(Gravity.CENTER);
        linearLayout1_2.addView(view1_2);
        txt12_1 = new EditText(mContext);
        txt12_1.setWidth(100);
        txt12_1.setHeight(120);
        txt12_1.setTextSize(13);/////////////////////////
        txt12_1.setBackgroundColor(Color.WHITE);
        txt12_1.setLayoutParams(marginLayout);
        txt12_1.setGravity(Gravity.CENTER);
        linearLayout1_2.addView(txt12_1);
        txt12_2 = new EditText(mContext);
        txt12_2.setWidth(100);
        txt12_2.setHeight(120);
        txt12_2.setTextSize(13);/////////////////////////
        txt12_2.setBackgroundColor(Color.WHITE);
        txt12_2.setLayoutParams(marginLayout);
        txt12_2.setGravity(Gravity.CENTER);
        linearLayout1_2.addView(txt12_2);
        button12_0 = new Button(mContext);
        button12_0.setText("0 \n No ");
        button12_0.setWidth(170);
        button12_0.setHeight(120);
        button12_0.setTextSize(13);/////////////////////////
        button12_0.setBackgroundColor(Color.GRAY);
        button12_0.setLayoutParams(marginLayout);
        button12_0.setGravity(Gravity.CENTER);
        button12_0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score12 = 0;
                button12_0.setBackgroundColor(Color.GREEN);
                button12_1.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout1_2.addView(button12_0);
        button12_1 = new Button(mContext);
        button12_1.setText("1 \n Yes ");
        button12_1.setWidth(170);
        button12_1.setHeight(120);
        button12_1.setTextSize(13);/////////////////////////
        button12_1.setBackgroundColor(Color.GRAY);
        button12_1.setLayoutParams(marginLayout);
        button12_1.setGravity(Gravity.CENTER);
        button12_1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score12 = 1;
                button12_1.setBackgroundColor(Color.GREEN);
                button12_0.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout1_2.addView(button12_1);
        viewLayout_1.addView(linearLayout1_2);

        view1_3 = new TextView(mContext);
        view1_3.setText("Number of hands(If NONE, leave rest of “Time Setting” blank; go to “Other Observations”)");
        view1_3.setWidth(550);
        view1_3.setHeight(120);
        view1_3.setTextSize(13);/////////////////////////
        view1_3.setBackgroundColor(Color.WHITE);
        view1_3.setLayoutParams(marginLayout);
        view1_3.setGravity(Gravity.CENTER);
        linearLayout1_3.addView(view1_3);
        button13_0 = new Button(mContext);
        button13_0.setText("0\nNone");
        button13_0.setWidth(170);
        button13_0.setHeight(120);
        button13_0.setTextSize(13);/////////////////////////
        button13_0.setBackgroundColor(Color.GRAY);
        button13_0.setLayoutParams(marginLayout);
        button13_0.setGravity(Gravity.CENTER);
        button13_0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score13 = 0;
                button13_0.setBackgroundColor(Color.GREEN);
                button13_1.setBackgroundColor(Color.GRAY);
                button13_2.setBackgroundColor(Color.GRAY);
                button13_3.setBackgroundColor(Color.GRAY);
                button13_4.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout1_3.addView(button13_0);
        button13_1 = new Button(mContext);
        button13_1.setText("1\nOne");
        button13_1.setWidth(170);
        button13_1.setHeight(120);
        button13_1.setTextSize(13);/////////////////////////
        button13_1.setBackgroundColor(Color.GRAY);
        button13_1.setLayoutParams(marginLayout);
        button13_1.setGravity(Gravity.CENTER);
        button13_1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score13 = 1;
                button13_0.setBackgroundColor(Color.GRAY);
                button13_1.setBackgroundColor(Color.GREEN);
                button13_2.setBackgroundColor(Color.GRAY);
                button13_3.setBackgroundColor(Color.GRAY);
                button13_4.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout1_3.addView(button13_1);
        button13_2 = new Button(mContext);
        button13_2.setText("2\nTwo");
        button13_2.setWidth(170);
        button13_2.setHeight(120);
        button13_2.setTextSize(13);/////////////////////////
        button13_2.setBackgroundColor(Color.GRAY);
        button13_2.setLayoutParams(marginLayout);
        button13_2.setGravity(Gravity.CENTER);
        button13_2.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score13 = 2;
                button13_0.setBackgroundColor(Color.GRAY);
                button13_1.setBackgroundColor(Color.GRAY);
                button13_2.setBackgroundColor(Color.GREEN);
                button13_3.setBackgroundColor(Color.GRAY);
                button13_4.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout1_3.addView(button13_2);
        button13_3 = new Button(mContext);
        button13_3.setText("3\nThree");
        button13_3.setWidth(170);
        button13_3.setHeight(120);
        button13_3.setTextSize(13);/////////////////////////
        button13_3.setBackgroundColor(Color.GRAY);
        button13_3.setLayoutParams(marginLayout);
        button13_3.setGravity(Gravity.CENTER);
        button13_3.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub         
                score13 = 3;
                button13_0.setBackgroundColor(Color.GRAY);
                button13_1.setBackgroundColor(Color.GRAY);
                button13_2.setBackgroundColor(Color.GRAY);
                button13_3.setBackgroundColor(Color.GREEN);
                button13_4.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout1_3.addView(button13_3);
        button13_4 = new Button(mContext);
        button13_4.setText("4\nFour or more");
        button13_4.setWidth(170);
        button13_4.setHeight(120);
        button13_4.setTextSize(13);/////////////////////////
        button13_4.setBackgroundColor(Color.GRAY);
        button13_4.setLayoutParams(marginLayout);
        button13_4.setGravity(Gravity.CENTER);
        button13_4.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score13 = 4;
                button13_0.setBackgroundColor(Color.GRAY);
                button13_1.setBackgroundColor(Color.GRAY);
                button13_2.setBackgroundColor(Color.GRAY);
                button13_3.setBackgroundColor(Color.GRAY);
                button13_4.setBackgroundColor(Color.GREEN);
            }
        });
        linearLayout1_3.addView(button13_4);
        viewLayout_1.addView(linearLayout1_3);

        view1_4 = new TextView(mContext);
        view1_4.setText("Length of hour versus minute hands");
        view1_4.setWidth(720);
        view1_4.setHeight(120);
        view1_4.setTextSize(13);/////////////////////////
        view1_4.setBackgroundColor(Color.WHITE);
        view1_4.setLayoutParams(marginLayout);
        view1_4.setGravity(Gravity.CENTER);
        linearLayout1_4.addView(view1_4);
        button14_0 = new Button(mContext);
        button14_0.setText("0\nMinute hand shorter");
        button14_0.setWidth(170);
        button14_0.setHeight(120);
        button14_0.setTextSize(13);/////////////////////////
        button14_0.setBackgroundColor(Color.GRAY);
        button14_0.setLayoutParams(marginLayout);
        button14_0.setGravity(Gravity.CENTER);
        button14_0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score14 = 0;
                button14_0.setBackgroundColor(Color.GREEN);
                button14_1.setBackgroundColor(Color.GRAY);
                button14_2.setBackgroundColor(Color.GRAY);
                button14_8.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout1_4.addView(button14_0);
        button14_1 = new Button(mContext);
        button14_1.setText("1\nEqual");
        button14_1.setWidth(170);
        button14_1.setHeight(120);
        button14_1.setTextSize(13);/////////////////////////
        button14_1.setBackgroundColor(Color.GRAY);
        button14_1.setLayoutParams(marginLayout);
        button14_1.setGravity(Gravity.CENTER);
        button14_1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score14 = 1;
                button14_0.setBackgroundColor(Color.GRAY);
                button14_1.setBackgroundColor(Color.GREEN);
                button14_2.setBackgroundColor(Color.GRAY);
                button14_8.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout1_4.addView(button14_1);
        button14_2 = new Button(mContext);
        button14_2.setText("2\nHour hand shorter");
        button14_2.setWidth(170);
        button14_2.setHeight(120);
        button14_2.setTextSize(13);/////////////////////////
        button14_2.setBackgroundColor(Color.GRAY);
        button14_2.setLayoutParams(marginLayout);
        button14_2.setGravity(Gravity.CENTER);
        button14_2.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score14 = 2;
                button14_0.setBackgroundColor(Color.GRAY);
                button14_1.setBackgroundColor(Color.GRAY);
                button14_2.setBackgroundColor(Color.GREEN);
                button14_8.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout1_4.addView(button14_2);
        button14_8 = new Button(mContext);
        button14_8.setText("8\nN/A");
        button14_8.setWidth(170);
        button14_8.setHeight(120);
        button14_8.setTextSize(13);/////////////////////////
        button14_8.setBackgroundColor(Color.GRAY);
        button14_8.setLayoutParams(marginLayout);
        button14_8.setGravity(Gravity.CENTER);
        button14_8.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub         
                score14 = 8;
                button14_0.setBackgroundColor(Color.GRAY);
                button14_1.setBackgroundColor(Color.GRAY);
                button14_2.setBackgroundColor(Color.GRAY);
                button14_8.setBackgroundColor(Color.GREEN);
            }
        });
        linearLayout1_4.addView(button14_8);
        viewLayout_1.addView(linearLayout1_4);

        cdp2_2 = new ArrayList<SingleClockDrawingPreView2>();
        totQuestions2 = 0;
        for (int i = 0; i < clockdrawingpreQusetion2.ques2.size(); ++i)
        {
            SingleClockDrawingPreView2 singlect2_2 = new SingleClockDrawingPreView2(mContext);
            singlect2_2.Init(clockdrawingpreQusetion2.ques2.get(i), helper);
            linearLayout1_5.addView(singlect2_2);
            cdp2_2.add(singlect2_2);
            totQuestions2++;
        }
        viewLayout_1.addView(linearLayout1_5);

        view1_6 = new TextView(mContext);
        view1_6.setText("On the horizontal axis, the point of intersection of the hands is:\ndeviation of the origin of hands from center, template 3, if not deviated enter 0 mm");
        view1_6.setWidth(800);
        view1_6.setHeight(120);
        view1_6.setTextSize(13);/////////////////////////
        view1_6.setBackgroundColor(Color.WHITE);
        view1_6.setLayoutParams(marginLayout);
        view1_6.setGravity(Gravity.CENTER);
        linearLayout1_6.addView(view1_6);
        txt16 = new EditText(mContext);
        txt16.setWidth(100);
        txt16.setHeight(120);
        txt16.setTextSize(13);/////////////////////////
        txt16.setBackgroundColor(Color.WHITE);
        txt16.setLayoutParams(marginLayout);
        txt16.setGravity(Gravity.CENTER);
        linearLayout1_6.addView(txt16);
        button16_0 = new Button(mContext);
        button16_0.setText("0\nLeft");
        button16_0.setWidth(170);
        button16_0.setHeight(120);
        button16_0.setTextSize(13);/////////////////////////
        button16_0.setBackgroundColor(Color.GRAY);
        button16_0.setLayoutParams(marginLayout);
        button16_0.setGravity(Gravity.CENTER);
        button16_0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score16 = 0;
                button16_0.setBackgroundColor(Color.GREEN);
                button16_1.setBackgroundColor(Color.GRAY);
                button16_2.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout1_6.addView(button16_0);
        button16_1 = new Button(mContext);
        button16_1.setText("1\nCenter");
        button16_1.setWidth(170);
        button16_1.setHeight(120);
        button16_1.setTextSize(13);/////////////////////////
        button16_1.setBackgroundColor(Color.GRAY);
        button16_1.setLayoutParams(marginLayout);
        button16_1.setGravity(Gravity.CENTER);
        button16_1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score16 = 1;
                button16_0.setBackgroundColor(Color.GRAY);
                button16_1.setBackgroundColor(Color.GREEN);
                button16_2.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout1_6.addView(button16_1);
        button16_2 = new Button(mContext);
        button16_2.setText("2\nRight");
        button16_2.setWidth(170);
        button16_2.setHeight(120);
        button16_2.setTextSize(13);/////////////////////////
        button16_2.setBackgroundColor(Color.GRAY);
        button16_2.setLayoutParams(marginLayout);
        button16_2.setGravity(Gravity.CENTER);
        button16_2.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score16 = 2;
                button16_0.setBackgroundColor(Color.GRAY);
                button16_1.setBackgroundColor(Color.GRAY);
                button16_2.setBackgroundColor(Color.GREEN);
            }
        });
        linearLayout1_6.addView(button16_2);
        viewLayout_1.addView(linearLayout1_6);

        view1_7 = new TextView(mContext);
        view1_7.setText("On the vertical axis, the point of intersection of the hands is:\ndeviation of the origin of hands from center, template 3, if not deviated enter 0 mm");
        view1_7.setWidth(800);
        view1_7.setHeight(120);
        view1_7.setTextSize(13);/////////////////////////
        view1_7.setBackgroundColor(Color.WHITE);
        view1_7.setLayoutParams(marginLayout);
        view1_7.setGravity(Gravity.CENTER);
        linearLayout1_7.addView(view1_7);
        txt17 = new EditText(mContext);
        txt17.setWidth(100);
        txt17.setHeight(120);
        txt17.setTextSize(13);/////////////////////////
        txt17.setBackgroundColor(Color.WHITE);
        txt17.setLayoutParams(marginLayout);
        txt17.setGravity(Gravity.CENTER);
        linearLayout1_7.addView(txt17);
        button17_0 = new Button(mContext);
        button17_0.setText("0\nDown");
        button17_0.setWidth(170);
        button17_0.setHeight(120);
        button17_0.setTextSize(13);/////////////////////////
        button17_0.setBackgroundColor(Color.GRAY);
        button17_0.setLayoutParams(marginLayout);
        button17_0.setGravity(Gravity.CENTER);
        button17_0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score17 = 0;
                button17_0.setBackgroundColor(Color.GREEN);
                button17_1.setBackgroundColor(Color.GRAY);
                button17_2.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout1_7.addView(button17_0);
        button17_1 = new Button(mContext);
        button17_1.setText("1\nCenter");
        button17_1.setWidth(170);
        button17_1.setHeight(120);
        button17_1.setTextSize(13);/////////////////////////
        button17_1.setBackgroundColor(Color.GRAY);
        button17_1.setLayoutParams(marginLayout);
        button17_1.setGravity(Gravity.CENTER);
        button17_1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score17 = 1;
                button17_0.setBackgroundColor(Color.GRAY);
                button17_1.setBackgroundColor(Color.GREEN);
                button17_2.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout1_7.addView(button17_1);
        button17_2 = new Button(mContext);
        button17_2.setText("2\nUp");
        button17_2.setWidth(170);
        button17_2.setHeight(120);
        button17_2.setTextSize(13);/////////////////////////
        button17_2.setBackgroundColor(Color.GRAY);
        button17_2.setLayoutParams(marginLayout);
        button17_2.setGravity(Gravity.CENTER);
        button17_2.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score17 = 2;
                button17_0.setBackgroundColor(Color.GRAY);
                button17_1.setBackgroundColor(Color.GRAY);
                button17_2.setBackgroundColor(Color.GREEN);
            }
        });
        linearLayout1_7.addView(button17_2);
        viewLayout_1.addView(linearLayout1_7);

        mLayout.addView(viewLayout_1);

        view2 = new TextView(mContext);
        view2.setText("Other Observations");
        view2.setWidth(1400);
        view2.setHeight(110);
        view2.setTextSize(16);/////////////////////////
        view2.setBackgroundColor(Color.LTGRAY);
        view2.setLayoutParams(marginLayout);
        view2.setGravity(Gravity.CENTER);
        viewLayout_2.addView(view2);

        LinearLayout linearLayout2_1 = new LinearLayout(mContext);
        linearLayout2_1.setOrientation(LinearLayout.VERTICAL);
        linearLayout2_1.setBackgroundColor(Color.BLACK);

        cdp2_3 = new ArrayList<SingleClockDrawingPreView2>();
        totQuestions3 = 0;
        for (int i = 0; i < clockdrawingpreQusetion2.ques3.size(); ++i)
        {
            SingleClockDrawingPreView2 singlect2_3 = new SingleClockDrawingPreView2(mContext);
            singlect2_3.Init(clockdrawingpreQusetion2.ques3.get(i), helper);
            linearLayout2_1.addView(singlect2_3);
            cdp2_3.add(singlect2_3);
            totQuestions3++;
        }
        viewLayout_2.addView(linearLayout2_1);
        mLayout.addView(viewLayout_2);

        view3 = new TextView(mContext);
        view3.setText("Tester’s clinical assessment of clock drawing");
        view3.setWidth(1400);
        view3.setHeight(110);
        view3.setTextSize(16);/////////////////////////
        view3.setBackgroundColor(Color.LTGRAY);
        view3.setLayoutParams(marginLayout);
        view3.setGravity(Gravity.CENTER);
        viewLayout_3.addView(view3);

        LinearLayout linearLayout3_1 = new LinearLayout(mContext);
        linearLayout3_1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout3_1.setBackgroundColor(Color.BLACK);
        LinearLayout linearLayout3_2 = new LinearLayout(mContext);
        linearLayout3_2.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout3_2.setBackgroundColor(Color.BLACK);
        button31_0 = new Button(mContext);
        button31_0.setText("0\n Normal");
        button31_0.setWidth(280);
        button31_0.setHeight(120);
        button31_0.setTextSize(13);/////////////////////////
        button31_0.setBackgroundColor(Color.GRAY);
        button31_0.setLayoutParams(marginLayout);
        button31_0.setGravity(Gravity.CENTER);
        button31_0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score31 = 0;
                button31_0.setBackgroundColor(Color.GREEN);
                button31_1.setBackgroundColor(Color.GRAY);
                button31_2.setBackgroundColor(Color.GRAY);
                button31_3.setBackgroundColor(Color.GRAY);
                button31_4.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout3_1.addView(button31_0);
        button31_1 = new Button(mContext);
        button31_1.setText("1\nMild impairment");
        button31_1.setWidth(280);
        button31_1.setHeight(120);
        button31_1.setTextSize(13);/////////////////////////
        button31_1.setBackgroundColor(Color.GRAY);
        button31_1.setLayoutParams(marginLayout);
        button31_1.setGravity(Gravity.CENTER);
        button31_1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score31 = 1;
                button31_0.setBackgroundColor(Color.GRAY);
                button31_1.setBackgroundColor(Color.GREEN);
                button31_2.setBackgroundColor(Color.GRAY);
                button31_3.setBackgroundColor(Color.GRAY);
                button31_4.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout3_1.addView(button31_1);
        button31_2 = new Button(mContext);
        button31_2.setText("2\nModerate impairment");
        button31_2.setWidth(280);
        button31_2.setHeight(120);
        button31_2.setTextSize(13);/////////////////////////
        button31_2.setBackgroundColor(Color.GRAY);
        button31_2.setLayoutParams(marginLayout);
        button31_2.setGravity(Gravity.CENTER);
        button31_2.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score31 = 2;
                button31_0.setBackgroundColor(Color.GRAY);
                button31_1.setBackgroundColor(Color.GRAY);
                button31_2.setBackgroundColor(Color.GREEN);
                button31_3.setBackgroundColor(Color.GRAY);
                button31_4.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout3_1.addView(button31_2);
        button31_3 = new Button(mContext);
        button31_3.setText("3\nSevere impairment");
        button31_3.setWidth(280);
        button31_3.setHeight(120);
        button31_3.setTextSize(13);/////////////////////////
        button31_3.setBackgroundColor(Color.GRAY);
        button31_3.setLayoutParams(marginLayout);
        button31_3.setGravity(Gravity.CENTER);
        button31_3.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score31 = 3;
                button31_0.setBackgroundColor(Color.GRAY);
                button31_1.setBackgroundColor(Color.GRAY);
                button31_2.setBackgroundColor(Color.GRAY);
                button31_3.setBackgroundColor(Color.GREEN);
                button31_4.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout3_1.addView(button31_3);
        button31_4 = new Button(mContext);
        button31_4.setText("4\nCannot Determine");
        button31_4.setWidth(280);
        button31_4.setHeight(120);
        button31_4.setTextSize(13);/////////////////////////
        button31_4.setBackgroundColor(Color.GRAY);
        button31_4.setLayoutParams(marginLayout);
        button31_4.setGravity(Gravity.CENTER);
        button31_4.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score31 = 4;
                button31_0.setBackgroundColor(Color.GRAY);
                button31_1.setBackgroundColor(Color.GRAY);
                button31_2.setBackgroundColor(Color.GRAY);
                button31_3.setBackgroundColor(Color.GRAY);
                button31_4.setBackgroundColor(Color.GREEN);
            }
        });
        linearLayout3_1.addView(button31_4);
        viewLayout_3.addView(linearLayout3_1);
        view3_2 = new TextView(mContext);
        view3_2.setText("Describe Other Observations:  ");
        view3_2.setWidth(350);
        view3_2.setHeight(130);
        view3_2.setTextSize(13);/////////////////////////
        view3_2.setBackgroundColor(Color.WHITE);
        view3_2.setLayoutParams(marginLayout);
        view3_2.setGravity(Gravity.CENTER);
        linearLayout3_2.addView(view3_2);
        Observations = new EditText(mContext);
        Observations.setWidth(1050);
        Observations.setHeight(130);
        Observations.setTextSize(13);/////////////////////////
        Observations.setBackgroundColor(Color.WHITE);
        Observations.setLayoutParams(marginLayout);
        Observations.setGravity(Gravity.CENTER);
        linearLayout3_2.addView(Observations);
        viewLayout_3.addView(linearLayout3_2);
        mLayout.addView(viewLayout_3);

        addView(mLayout);
        return;

    }

    private void instantiate(final Context context, final ClockDrawingPreQuestion2 drawing_preQuestion2)
    {
        ClockDrawingPreQuestion2 drawingpreQuestion2 = new ClockDrawingPreQuestion2();
        return;
    }

}
