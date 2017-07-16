
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


public class ClockDrawingPreView extends LinearLayout
{
    int totQuestions;
    Context mContext;
    TextView view1, view2, view3, view4,
            view1_1, view1_2,
            view21_1, view21_2, view22_1, view22_2, view23_1, view23_2, view2_4,
            view3_1, view4_2;
    Button button11_0, button11_1, button11_8,
            button12_0, button12_1, button12_2, button12_3, button12_4, button12_8, button12_9,
            button24_0, button24_1, button24_2, button24_8, button24_9,
            button3_0, button3_1,
            button41_0, button41_1, button41_2, button41_3, button41_4;
    public int score11 = -1, score12 = -1, score24 = -1, score3 = -1, score41 = -1;
    public EditText txt21_1, txt21_2, txt22_1, txt22_2, txt23_1, txt23_2, Observations;
    public Handler callbackHandler;
    public List<SingleClockDrawingPreView> cdp;

    public ClockDrawingPreView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        mContext = context;
        //	instantiate(context, null);
    }

    public ClockDrawingPreView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public ClockDrawingPreView(final Context context, final ClockDrawingPreQuestion drawingpreQuestion, Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        mContext = context;
        instantiate(context, drawingpreQuestion);
    }

    public void Init(ClockDrawingPreQuestion drawingpreQuestion, Helper helper)
    {
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        //  mLayout.setGravity(Gravity.CENTER_HORIZONTAL);
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
        LinearLayout viewLayout_4 = new LinearLayout(mContext);
        viewLayout_4.setOrientation(LinearLayout.VERTICAL);
        viewLayout_4.setBackgroundColor(Color.BLACK);
        viewLayout_4.setLayoutParams(marginLayout);

        TextView textView = new TextView(mContext);
//        textView.setText(DrawClockQuestion.guideWord1);
//        mLayout.addView(textView);
//        textView = new TextView(mContext);
        textView.setTextSize(18);
        textView.setGravity(Gravity.CENTER);
        textView.setText(drawingpreQuestion.title);
        mLayout.addView(textView);

        view1 = new TextView(mContext);
        view1.setText("Real Time Observations");
        view1.setWidth(1400);
        view1.setHeight(110);
        view1.setTextSize(16);/////////////////////////
        view1.setBackgroundColor(Color.LTGRAY);
        view1.setLayoutParams(marginLayout);
        view1.setGravity(Gravity.CENTER);
        viewLayout_1.addView(view1);

        LinearLayout linearLayout1_1 = new LinearLayout(mContext);
        linearLayout1_1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout1_1.setBackgroundColor(Color.BLACK);
        LinearLayout linearLayout1_2 = new LinearLayout(mContext);
        linearLayout1_2.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout1_2.setBackgroundColor(Color.BLACK);

        view1_1 = new TextView(mContext);
        view1_1.setText("Participant rotated paper while inserting numerals/numeral substitutes ");
        view1_1.setWidth(860);
        view1_1.setHeight(100);
        view1_1.setTextSize(13);/////////////////////////
        view1_1.setBackgroundColor(Color.WHITE);
        view1_1.setLayoutParams(marginLayout);
        view1_1.setGravity(Gravity.CENTER);
        linearLayout1_1.addView(view1_1);
        button11_0 = new Button(mContext);
        button11_0.setText("0 = No ");
        button11_0.setWidth(180);
        button11_0.setHeight(100);
        button11_0.setTextSize(13);/////////////////////////
        button11_0.setBackgroundColor(Color.GRAY);
        button11_0.setLayoutParams(marginLayout);
        button11_0.setGravity(Gravity.CENTER);
        button11_0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score11 = 0;
                button11_0.setBackgroundColor(Color.GREEN);
                button11_1.setBackgroundColor(Color.GRAY);
                button11_8.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout1_1.addView(button11_0);
        button11_1 = new Button(mContext);
        button11_1.setText("1 = Yes ");
        button11_1.setWidth(180);
        button11_1.setHeight(100);
        button11_1.setTextSize(13);/////////////////////////
        button11_1.setBackgroundColor(Color.GRAY);
        button11_1.setLayoutParams(marginLayout);
        button11_1.setGravity(Gravity.CENTER);
        button11_1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score11 = 1;
                button11_1.setBackgroundColor(Color.GREEN);
                button11_0.setBackgroundColor(Color.GRAY);
                button11_8.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout1_1.addView(button11_1);
        button11_8 = new Button(mContext);
        button11_8.setText("8 = N/A ");
        button11_8.setWidth(180);
        button11_8.setHeight(100);
        button11_8.setTextSize(13);/////////////////////////
        button11_8.setBackgroundColor(Color.GRAY);
        button11_8.setLayoutParams(marginLayout);
        button11_8.setGravity(Gravity.CENTER);
        button11_8.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score11 = 8;
                button11_8.setBackgroundColor(Color.GREEN);
                button11_1.setBackgroundColor(Color.GRAY);
                button11_0.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout1_1.addView(button11_8);
        viewLayout_1.addView(linearLayout1_1);

        view1_2 = new TextView(mContext);
        view1_2.setText("Anchor numerals or substitutes placed before any others\n(circle “1” for all that apply; see scoring manual for details)");
        view1_2.setWidth(450);
        view1_2.setHeight(120);
        view1_2.setTextSize(13);/////////////////////////
        view1_2.setBackgroundColor(Color.WHITE);
        view1_2.setLayoutParams(marginLayout);
        view1_2.setGravity(Gravity.CENTER);
        linearLayout1_2.addView(view1_2);
        button12_0 = new Button(mContext);
        button12_0.setText("Nones");
        button12_0.setWidth(110);
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
                button12_2.setBackgroundColor(Color.GRAY);
                button12_3.setBackgroundColor(Color.GRAY);
                button12_4.setBackgroundColor(Color.GRAY);
                button12_9.setBackgroundColor(Color.GRAY);
                button12_8.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout1_2.addView(button12_0);
        button12_1 = new Button(mContext);
        button12_1.setText("12\nposition");
        button12_1.setWidth(135);
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
                button12_0.setBackgroundColor(Color.GRAY);
                button12_1.setBackgroundColor(Color.GREEN);
                button12_2.setBackgroundColor(Color.GRAY);
                button12_3.setBackgroundColor(Color.GRAY);
                button12_4.setBackgroundColor(Color.GRAY);
                button12_9.setBackgroundColor(Color.GRAY);
                button12_8.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout1_2.addView(button12_1);
        button12_2 = new Button(mContext);
        button12_2.setText("3\nposition");
        button12_2.setWidth(135);
        button12_2.setHeight(120);
        button12_2.setTextSize(13);/////////////////////////
        button12_2.setBackgroundColor(Color.GRAY);
        button12_2.setLayoutParams(marginLayout);
        button12_2.setGravity(Gravity.CENTER);
        button12_2.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score12 = 2;
                button12_0.setBackgroundColor(Color.GRAY);
                button12_1.setBackgroundColor(Color.GRAY);
                button12_2.setBackgroundColor(Color.GREEN);
                button12_3.setBackgroundColor(Color.GRAY);
                button12_4.setBackgroundColor(Color.GRAY);
                button12_9.setBackgroundColor(Color.GRAY);
                button12_8.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout1_2.addView(button12_2);
        button12_3 = new Button(mContext);
        button12_3.setText("6\nposition");
        button12_3.setWidth(150);
        button12_3.setHeight(120);
        button12_3.setTextSize(13);/////////////////////////
        button12_3.setBackgroundColor(Color.GRAY);
        button12_3.setLayoutParams(marginLayout);
        button12_3.setGravity(Gravity.CENTER);
        button12_3.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score12 = 3;
                button12_0.setBackgroundColor(Color.GRAY);
                button12_1.setBackgroundColor(Color.GRAY);
                button12_2.setBackgroundColor(Color.GRAY);
                button12_3.setBackgroundColor(Color.GREEN);
                button12_4.setBackgroundColor(Color.GRAY);
                button12_9.setBackgroundColor(Color.GRAY);
                button12_8.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout1_2.addView(button12_3);
        button12_4 = new Button(mContext);
        button12_4.setText("9\nposition");
        button12_4.setWidth(130);
        button12_4.setHeight(120);
        button12_4.setTextSize(13);/////////////////////////
        button12_4.setBackgroundColor(Color.GRAY);
        button12_4.setLayoutParams(marginLayout);
        button12_4.setGravity(Gravity.CENTER);
        button12_4.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score12 = 4;
                button12_0.setBackgroundColor(Color.GRAY);
                button12_1.setBackgroundColor(Color.GRAY);
                button12_2.setBackgroundColor(Color.GRAY);
                button12_3.setBackgroundColor(Color.GRAY);
                button12_4.setBackgroundColor(Color.GREEN);
                button12_9.setBackgroundColor(Color.GRAY);
                button12_8.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout1_2.addView(button12_4);
        button12_8 = new Button(mContext);
        button12_8.setText("8=N/A");
        button12_8.setWidth(130);
        button12_8.setHeight(120);
        button12_8.setTextSize(13);/////////////////////////
        button12_8.setBackgroundColor(Color.GRAY);
        button12_8.setLayoutParams(marginLayout);
        button12_8.setGravity(Gravity.CENTER);
        button12_8.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score12 = 8;
                button12_0.setBackgroundColor(Color.GRAY);
                button12_1.setBackgroundColor(Color.GRAY);
                button12_2.setBackgroundColor(Color.GRAY);
                button12_3.setBackgroundColor(Color.GRAY);
                button12_4.setBackgroundColor(Color.GRAY);
                button12_9.setBackgroundColor(Color.GRAY);
                button12_8.setBackgroundColor(Color.GREEN);
            }
        });
        linearLayout1_2.addView(button12_8);
        button12_9 = new Button(mContext);
        button12_9.setText("9=unknown");
        button12_9.setWidth(130);
        button12_9.setHeight(120);
        button12_9.setTextSize(13);/////////////////////////
        button12_9.setBackgroundColor(Color.GRAY);
        button12_9.setLayoutParams(marginLayout);
        button12_9.setGravity(Gravity.CENTER);
        button12_9.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score12 = 9;
                button12_0.setBackgroundColor(Color.GRAY);
                button12_1.setBackgroundColor(Color.GRAY);
                button12_2.setBackgroundColor(Color.GRAY);
                button12_3.setBackgroundColor(Color.GRAY);
                button12_4.setBackgroundColor(Color.GRAY);
                button12_9.setBackgroundColor(Color.GREEN);
                button12_8.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout1_2.addView(button12_9);
        viewLayout_1.addView(linearLayout1_2);
        mLayout.addView(viewLayout_1);

        view2 = new TextView(mContext);
        view2.setText("Numerals (If there are no numerals or numeral substitutes present, leave this section blank and skip ahead to “Time Setting”)");
        view2.setWidth(1400);
        view2.setHeight(110);
        view2.setTextSize(16);/////////////////////////
        view2.setBackgroundColor(Color.LTGRAY);
        view2.setLayoutParams(marginLayout);
        view2.setGravity(Gravity.CENTER);
        viewLayout_2.addView(view2);
        LinearLayout linearLayout2_1 = new LinearLayout(mContext);
        linearLayout2_1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout2_1.setBackgroundColor(Color.BLACK);
        LinearLayout linearLayout2_2 = new LinearLayout(mContext);
        linearLayout2_2.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout2_2.setBackgroundColor(Color.BLACK);
        LinearLayout linearLayout2_3 = new LinearLayout(mContext);
        linearLayout2_3.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout2_3.setBackgroundColor(Color.BLACK);
        LinearLayout linearLayout2_4 = new LinearLayout(mContext);
        linearLayout2_4.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout2_4.setBackgroundColor(Color.BLACK);
        LinearLayout linearLayout2_5 = new LinearLayout(mContext);
        linearLayout2_5.setOrientation(LinearLayout.VERTICAL);
        linearLayout2_5.setBackgroundColor(Color.BLACK);
        linearLayout2_5.setGravity(Gravity.CENTER_HORIZONTAL);
        linearLayout2_5.setLayoutParams(marginLayout);

        view21_1 = new TextView(mContext);
        view21_1.setText("Measurement of the most medially deviated number(**use templates)\n(if no deviation enter 0) NUMBER MEASURED, OR CLOCK LOCATION IF DUPLICATE NUMBER");
        view21_1.setWidth(900);
        view21_1.setHeight(120);
        view21_1.setTextSize(13);/////////////////////////
        view21_1.setBackgroundColor(Color.WHITE);
        view21_1.setLayoutParams(marginLayout);
        view21_1.setGravity(Gravity.CENTER);
        linearLayout2_1.addView(view21_1);
        txt21_1 = new EditText(mContext);
        txt21_1.setWidth(100);
        txt21_1.setHeight(120);
        txt21_1.setTextSize(13);/////////////////////////
        txt21_1.setBackgroundColor(Color.WHITE);
        txt21_1.setLayoutParams(marginLayout);
        txt21_1.setGravity(Gravity.CENTER);
        linearLayout2_1.addView(txt21_1);
        view21_2 = new TextView(mContext);
        view21_2.setText("mm:");
        view21_2.setWidth(300);
        view21_2.setHeight(120);
        view21_2.setTextSize(13);/////////////////////////
        view21_2.setBackgroundColor(Color.WHITE);
        view21_2.setLayoutParams(marginLayout);
        view21_2.setGravity(Gravity.CENTER);
        linearLayout2_1.addView(view21_2);
        txt21_2 = new EditText(mContext);
        txt21_2.setWidth(100);
        txt21_2.setHeight(120);
        txt21_2.setTextSize(13);/////////////////////////
        txt21_2.setBackgroundColor(Color.WHITE);
        txt21_2.setLayoutParams(marginLayout);
        txt21_2.setGravity(Gravity.CENTER);
        linearLayout2_1.addView(txt21_2);
        viewLayout_2.addView(linearLayout2_1);

        view22_1 = new TextView(mContext);
        view22_1.setText("Displacement of anchor (i.e., 12-3-6-9) numerals/numeral substitutes from correct hour position\n(**use templates)If no displacement write “0” and “0.0” ANCHOR MEASURED:");
        view22_1.setWidth(900);
        view22_1.setHeight(120);
        view22_1.setTextSize(13);/////////////////////////
        view22_1.setBackgroundColor(Color.WHITE);
        view22_1.setLayoutParams(marginLayout);
        view22_1.setGravity(Gravity.CENTER);
        linearLayout2_2.addView(view22_1);
        txt22_1 = new EditText(mContext);
        txt22_1.setWidth(100);
        txt22_1.setHeight(120);
        txt22_1.setTextSize(13);/////////////////////////
        txt22_1.setBackgroundColor(Color.WHITE);
        txt22_1.setLayoutParams(marginLayout);
        txt22_1.setGravity(Gravity.CENTER);
        linearLayout2_2.addView(txt22_1);
        view22_2 = new TextView(mContext);
        view22_2.setText("Number of 30 minute increments:");
        view22_2.setWidth(300);
        view22_2.setHeight(120);
        view22_2.setTextSize(13);/////////////////////////
        view22_2.setBackgroundColor(Color.WHITE);
        view22_2.setLayoutParams(marginLayout);
        view22_2.setGravity(Gravity.CENTER);
        linearLayout2_2.addView(view22_2);
        txt22_2 = new EditText(mContext);
        txt22_2.setWidth(100);
        txt22_2.setHeight(120);
        txt22_2.setTextSize(13);/////////////////////////
        txt22_2.setBackgroundColor(Color.WHITE);
        txt22_2.setLayoutParams(marginLayout);
        txt22_2.setGravity(Gravity.CENTER);
        linearLayout2_2.addView(txt22_2);
        viewLayout_2.addView(linearLayout2_2);

        view23_1 = new TextView(mContext);
        view23_1.setText("Displacement of non-anchor numerals/numeral substitutes from correct hour position\n(**use templates)If no displacement write “0” and “0.0” NON-ANCHOR MEASURED:");
        view23_1.setWidth(900);
        view23_1.setHeight(120);
        view23_1.setTextSize(13);/////////////////////////
        view23_1.setBackgroundColor(Color.WHITE);
        view23_1.setLayoutParams(marginLayout);
        view23_1.setGravity(Gravity.CENTER);
        linearLayout2_3.addView(view23_1);
        txt23_1 = new EditText(mContext);
        txt23_1.setWidth(100);
        txt23_1.setHeight(120);
        txt23_1.setTextSize(13);/////////////////////////
        txt23_1.setBackgroundColor(Color.WHITE);
        txt23_1.setLayoutParams(marginLayout);
        txt23_1.setGravity(Gravity.CENTER);
        linearLayout2_3.addView(txt23_1);
        view23_2 = new TextView(mContext);
        view23_2.setText("Number of 30 minute increments:");
        view23_2.setWidth(300);
        view23_2.setHeight(120);
        view23_2.setTextSize(13);/////////////////////////
        view23_2.setBackgroundColor(Color.WHITE);
        view23_2.setLayoutParams(marginLayout);
        view23_2.setGravity(Gravity.CENTER);
        linearLayout2_3.addView(view23_2);
        txt23_2 = new EditText(mContext);
        txt23_2.setWidth(100);
        txt23_2.setHeight(120);
        txt23_2.setTextSize(13);/////////////////////////
        txt23_2.setBackgroundColor(Color.WHITE);
        txt23_2.setLayoutParams(marginLayout);
        txt23_2.setGravity(Gravity.CENTER);
        linearLayout2_3.addView(txt23_2);
        viewLayout_2.addView(linearLayout2_3);
        view2_4 = new TextView(mContext);
        view2_4.setText("Numbers/substitutes are placed on or outside the outline");
        view2_4.setWidth(400);
        view2_4.setHeight(120);
        view2_4.setTextSize(13);/////////////////////////
        view2_4.setBackgroundColor(Color.WHITE);
        view2_4.setLayoutParams(marginLayout);
        view2_4.setGravity(Gravity.CENTER);
        linearLayout2_4.addView(view2_4);
        button24_0 = new Button(mContext);
        button24_0.setText("0\n no");
        button24_0.setWidth(100);
        button24_0.setHeight(120);
        button24_0.setTextSize(13);/////////////////////////
        button24_0.setBackgroundColor(Color.GRAY);
        button24_0.setLayoutParams(marginLayout);
        button24_0.setGravity(Gravity.CENTER);
        button24_0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score24 = 0;
                button24_0.setBackgroundColor(Color.GREEN);
                button24_1.setBackgroundColor(Color.GRAY);
                button24_2.setBackgroundColor(Color.GRAY);
                button24_9.setBackgroundColor(Color.GRAY);
                button24_8.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout2_4.addView(button24_0);
        button24_1 = new Button(mContext);
        button24_1.setText("1\nyes, at least one number/substitute, but not all");
        button24_1.setWidth(400);
        button24_1.setHeight(120);
        button24_1.setTextSize(13);/////////////////////////
        button24_1.setBackgroundColor(Color.GRAY);
        button24_1.setLayoutParams(marginLayout);
        button24_1.setGravity(Gravity.CENTER);
        button24_1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score24 = 1;
                button24_0.setBackgroundColor(Color.GRAY);
                button24_1.setBackgroundColor(Color.GREEN);
                button24_2.setBackgroundColor(Color.GRAY);
                button24_9.setBackgroundColor(Color.GRAY);
                button24_8.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout2_4.addView(button24_1);
        button24_2 = new Button(mContext);
        button24_2.setText("2\nyes, all numbers/substitutes");
        button24_2.setWidth(250);
        button24_2.setHeight(120);
        button24_2.setTextSize(13);/////////////////////////
        button24_2.setBackgroundColor(Color.GRAY);
        button24_2.setLayoutParams(marginLayout);
        button24_2.setGravity(Gravity.CENTER);
        button24_2.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score24 = 2;
                button24_0.setBackgroundColor(Color.GRAY);
                button24_1.setBackgroundColor(Color.GRAY);
                button24_2.setBackgroundColor(Color.GREEN);
                button24_9.setBackgroundColor(Color.GRAY);
                button24_8.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout2_4.addView(button24_2);
        button24_8 = new Button(mContext);
        button24_8.setText("8=N/A");
        button24_8.setWidth(120);
        button24_8.setHeight(120);
        button24_8.setTextSize(13);/////////////////////////
        button24_8.setBackgroundColor(Color.GRAY);
        button24_8.setLayoutParams(marginLayout);
        button24_8.setGravity(Gravity.CENTER);
        button24_8.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score24 = 8;
                button24_0.setBackgroundColor(Color.GRAY);
                button24_1.setBackgroundColor(Color.GRAY);
                button24_2.setBackgroundColor(Color.GRAY);
                button24_9.setBackgroundColor(Color.GRAY);
                button24_8.setBackgroundColor(Color.GREEN);
            }
        });
        linearLayout2_4.addView(button24_8);
        button24_9 = new Button(mContext);
        button24_9.setText("9\nunknown");
        button24_9.setWidth(120);
        button24_9.setHeight(120);
        button24_9.setTextSize(13);/////////////////////////
        button24_9.setBackgroundColor(Color.GRAY);
        button24_9.setLayoutParams(marginLayout);
        button24_9.setGravity(Gravity.CENTER);
        button24_9.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score24 = 9;
                button24_0.setBackgroundColor(Color.GRAY);
                button24_1.setBackgroundColor(Color.GRAY);
                button24_2.setBackgroundColor(Color.GRAY);
                button24_9.setBackgroundColor(Color.GREEN);
                button24_8.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout2_4.addView(button24_9);
        viewLayout_2.addView(linearLayout2_4);

        cdp = new ArrayList<SingleClockDrawingPreView>();
        totQuestions = 0;
        for (int i = 0; i < drawingpreQuestion.ques.size(); ++i)
        {
            SingleClockDrawingPreView singlect = new SingleClockDrawingPreView(mContext);
            singlect.Init(drawingpreQuestion.ques.get(i), helper);
            linearLayout2_5.addView(singlect);
            cdp.add(singlect);
            totQuestions++;
        }
        viewLayout_2.addView(linearLayout2_5);

        mLayout.addView(viewLayout_2);

        view3 = new TextView(mContext);
        view3.setText("Other Observations");
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
        view3_1 = new TextView(mContext);
        view3_1.setText("Other  observations: (please describe below)  (e.g., numbers in counterclockwise order <i.e., ”1” in 11 position,“2” in 10 position>; human hands drawn; smiley face; blatant difference in size of numerals; etc.) ");
        view3_1.setWidth(1040);
        view3_1.setHeight(120);
        view3_1.setTextSize(13);/////////////////////////
        view3_1.setBackgroundColor(Color.WHITE);
        view3_1.setLayoutParams(marginLayout);
        view3_1.setGravity(Gravity.CENTER);
        linearLayout3_1.addView(view3_1);
        button3_0 = new Button(mContext);
        button3_0.setText("No\n 0 ");
        button3_0.setWidth(180);
        button3_0.setHeight(120);
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
            }
        });
        linearLayout3_1.addView(button3_0);
        button3_1 = new Button(mContext);
        button3_1.setText("Yes\n 1");
        button3_1.setWidth(180);
        button3_1.setHeight(120);
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
            }
        });
        linearLayout3_1.addView(button3_1);
        viewLayout_3.addView(linearLayout3_1);
        mLayout.addView(viewLayout_3);

        view4 = new TextView(mContext);
        view4.setText("Tester’s clinical assessment of clock drawing");
        view4.setWidth(1400);
        view4.setHeight(110);
        view4.setTextSize(16);/////////////////////////
        view4.setBackgroundColor(Color.LTGRAY);
        view4.setLayoutParams(marginLayout);
        view4.setGravity(Gravity.CENTER);
        viewLayout_4.addView(view4);
        LinearLayout linearLayout4_1 = new LinearLayout(mContext);
        linearLayout4_1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout4_1.setBackgroundColor(Color.BLACK);
        LinearLayout linearLayout4_2 = new LinearLayout(mContext);
        linearLayout4_2.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout4_2.setBackgroundColor(Color.BLACK);
        button41_0 = new Button(mContext);
        button41_0.setText("0\n Normal");
        button41_0.setWidth(280);
        button41_0.setHeight(120);
        button41_0.setTextSize(13);/////////////////////////
        button41_0.setBackgroundColor(Color.GRAY);
        button41_0.setLayoutParams(marginLayout);
        button41_0.setGravity(Gravity.CENTER);
        button41_0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score41 = 0;
                button41_0.setBackgroundColor(Color.GREEN);
                button41_1.setBackgroundColor(Color.GRAY);
                button41_2.setBackgroundColor(Color.GRAY);
                button41_3.setBackgroundColor(Color.GRAY);
                button41_4.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout4_1.addView(button41_0);
        button41_1 = new Button(mContext);
        button41_1.setText("1\nMild impairment");
        button41_1.setWidth(280);
        button41_1.setHeight(120);
        button41_1.setTextSize(13);/////////////////////////
        button41_1.setBackgroundColor(Color.GRAY);
        button41_1.setLayoutParams(marginLayout);
        button41_1.setGravity(Gravity.CENTER);
        button41_1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score41 = 1;
                button41_0.setBackgroundColor(Color.GRAY);
                button41_1.setBackgroundColor(Color.GREEN);
                button41_2.setBackgroundColor(Color.GRAY);
                button41_3.setBackgroundColor(Color.GRAY);
                button41_4.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout4_1.addView(button41_1);
        button41_2 = new Button(mContext);
        button41_2.setText("2\nModerate impairment");
        button41_2.setWidth(280);
        button41_2.setHeight(120);
        button41_2.setTextSize(13);/////////////////////////
        button41_2.setBackgroundColor(Color.GRAY);
        button41_2.setLayoutParams(marginLayout);
        button41_2.setGravity(Gravity.CENTER);
        button41_2.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score41 = 2;
                button41_0.setBackgroundColor(Color.GRAY);
                button41_1.setBackgroundColor(Color.GRAY);
                button41_2.setBackgroundColor(Color.GREEN);
                button41_3.setBackgroundColor(Color.GRAY);
                button41_4.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout4_1.addView(button41_2);
        button41_3 = new Button(mContext);
        button41_3.setText("3\nSevere impairment");
        button41_3.setWidth(280);
        button41_3.setHeight(120);
        button41_3.setTextSize(13);/////////////////////////
        button41_3.setBackgroundColor(Color.GRAY);
        button41_3.setLayoutParams(marginLayout);
        button41_3.setGravity(Gravity.CENTER);
        button41_3.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score41 = 3;
                button41_0.setBackgroundColor(Color.GRAY);
                button41_1.setBackgroundColor(Color.GRAY);
                button41_2.setBackgroundColor(Color.GRAY);
                button41_3.setBackgroundColor(Color.GREEN);
                button41_4.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout4_1.addView(button41_3);
        button41_4 = new Button(mContext);
        button41_4.setText("4\nCannot Determine");
        button41_4.setWidth(280);
        button41_4.setHeight(120);
        button41_4.setTextSize(13);/////////////////////////
        button41_4.setBackgroundColor(Color.GRAY);
        button41_4.setLayoutParams(marginLayout);
        button41_4.setGravity(Gravity.CENTER);
        button41_4.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score41 = 4;
                button41_0.setBackgroundColor(Color.GRAY);
                button41_1.setBackgroundColor(Color.GRAY);
                button41_2.setBackgroundColor(Color.GRAY);
                button41_3.setBackgroundColor(Color.GRAY);
                button41_4.setBackgroundColor(Color.GREEN);
            }
        });
        linearLayout4_1.addView(button41_4);
        viewLayout_4.addView(linearLayout4_1);
        view4_2 = new TextView(mContext);
        view4_2.setText("Describe Other Observations:  ");
        view4_2.setWidth(350);
        view4_2.setHeight(130);
        view4_2.setTextSize(13);/////////////////////////
        view4_2.setBackgroundColor(Color.WHITE);
        view4_2.setLayoutParams(marginLayout);
        view4_2.setGravity(Gravity.CENTER);
        linearLayout4_2.addView(view4_2);
        Observations = new EditText(mContext);
        Observations.setWidth(1050);
        Observations.setHeight(130);
        Observations.setTextSize(13);/////////////////////////
        Observations.setBackgroundColor(Color.WHITE);
        Observations.setLayoutParams(marginLayout);
        Observations.setGravity(Gravity.CENTER);
        linearLayout4_2.addView(Observations);
        viewLayout_4.addView(linearLayout4_2);
        mLayout.addView(viewLayout_4);

        addView(mLayout);
        return;

    }

    private void instantiate(final Context context, final ClockDrawingPreQuestion drawing_preQuestion)
    {
        ClockDrawingPreQuestion drawingpreQuestion = new ClockDrawingPreQuestion();
        return;
    }


}
