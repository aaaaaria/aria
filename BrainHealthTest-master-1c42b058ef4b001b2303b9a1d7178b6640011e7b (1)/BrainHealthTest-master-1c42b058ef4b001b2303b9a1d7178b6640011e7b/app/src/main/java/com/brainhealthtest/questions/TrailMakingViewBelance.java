package com.brainhealthtest.questions;

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
import com.brainhealthtest.utility.TrailMakingQuestion;


public class TrailMakingViewBelance extends LinearLayout
{
    Context mContext;
    TextView view1_1, view1_2, view1_3, view1_4, view1_5, view1_6, view2_1, view2_2, view2_3, view2_4, view2_5, view2_6, view2_7, view2_8, view2_9, view3_1, view3_2, view3_3, view3_4, view2_3_1, view2_3_2;
    Button button1_0, button1_1, button2_0, button2_1, button3_0, button3_1;
    public EditText txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9, txt3_1, txt3_2;
    public Handler callbackHandler;
    public int score1 = -1, score2_1 = -1, score2_2 = -1;


    public TrailMakingViewBelance(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        mContext = context;
        //	instantiate(context, null);
    }

    public TrailMakingViewBelance(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public TrailMakingViewBelance(final Context context, final TrailMakingQuestion trailmakingQuestion, Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        mContext = context;
        instantiate(context, trailmakingQuestion);
    }

    public void Init(TrailMakingQuestion trailQuestion, Helper helper)
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
        textView.setText(trailQuestion.guideWord1);
        mLayout.addView(textView);
        textView = new TextView(mContext);
        textView.setTextSize(18);
        textView.setGravity(Gravity.CENTER);
        textView.setText(trailQuestion.title);
        mLayout.addView(textView);

        view1_1 = new TextView(mContext);
        view1_1.setText("Time to completion*");
        view1_1.setWidth(200);
        view1_1.setHeight(80);
        view1_1.setTextSize(13);
        view1_1.setBackgroundColor(Color.LTGRAY);
        view1_1.setLayoutParams(marginLayout);
        view1_1.setGravity(Gravity.CENTER);

        view1_2 = new TextView(mContext);
        view1_2.setText("# self-corrected errors");
        view1_2.setWidth(300);
        view1_2.setHeight(80);
        view1_2.setTextSize(13);
        view1_2.setBackgroundColor(Color.LTGRAY);
        view1_2.setLayoutParams(marginLayout);
        view1_2.setGravity(Gravity.CENTER);

        view1_3 = new TextView(mContext);
        view1_3.setText("# examiner corrected errors");
        view1_3.setWidth(300);
        view1_3.setHeight(80);
        view1_3.setTextSize(13);
        view1_3.setBackgroundColor(Color.LTGRAY);
        view1_3.setLayoutParams(marginLayout);
        view1_3.setGravity(Gravity.CENTER);

        view1_4 = new TextView(mContext);
        view1_4.setText("Obvious Tremor");
        view1_4.setWidth(300);
        view1_4.setHeight(80);
        view1_4.setTextSize(13);
        view1_4.setBackgroundColor(Color.LTGRAY);
        view1_4.setLayoutParams(marginLayout);
        view1_4.setGravity(Gravity.CENTER);

        view1_5 = new TextView(mContext);
        view1_5.setText("# pen lifts");
        view1_5.setWidth(100);
        view1_5.setHeight(80);
        view1_5.setTextSize(13);
        view1_5.setBackgroundColor(Color.LTGRAY);
        view1_5.setLayoutParams(marginLayout);
        view1_5.setGravity(Gravity.CENTER);

        view1_6 = new TextView(mContext);
        view1_6.setText("# cues given");
        view1_6.setWidth(100);
        view1_6.setHeight(80);
        view1_6.setTextSize(13);
        view1_6.setBackgroundColor(Color.LTGRAY);
        view1_6.setLayoutParams(marginLayout);
        view1_6.setGravity(Gravity.CENTER);
        viewLayout_1.addView(view1_1);
        viewLayout_1.addView(view1_2);
        viewLayout_1.addView(view1_3);
        viewLayout_1.addView(view1_4);
        viewLayout_1.addView(view1_5);
        viewLayout_1.addView(view1_6);
        mLayout.addView(viewLayout_1);

        view2_1 = new TextView(mContext);
        view2_1.setText("(min:sec)");
        view2_1.setWidth(200);
        view2_1.setHeight(50);
        view2_1.setTextSize(13);
        view2_1.setBackgroundColor(Color.LTGRAY);
        view2_1.setLayoutParams(marginLayout);
        view2_1.setGravity(Gravity.CENTER);
        view2_2 = new TextView(mContext);
        view2_2.setText("Perceptual");
        view2_2.setWidth(99);
        view2_2.setHeight(50);
        view2_2.setTextSize(13);
        view2_2.setBackgroundColor(Color.LTGRAY);
        view2_2.setLayoutParams(marginLayout);
        view2_2.setGravity(Gravity.CENTER);
        view2_3 = new TextView(mContext);
        view2_3.setText("LOS");
        view2_3.setWidth(99);
        view2_3.setHeight(50);
        view2_3.setTextSize(13);
        view2_3.setBackgroundColor(Color.LTGRAY);
        view2_3.setLayoutParams(marginLayout);
        view2_3.setGravity(Gravity.CENTER);
        view2_3_1 = new TextView(mContext);
        view2_3_1.setText("Sequencing");
        view2_3_1.setWidth(99);
        view2_3_1.setHeight(50);
        view2_3_1.setTextSize(13);
        view2_3_1.setBackgroundColor(Color.LTGRAY);
        view2_3_1.setLayoutParams(marginLayout);
        view2_3_1.setGravity(Gravity.CENTER);
        view2_4 = new TextView(mContext);
        view2_4.setText("Perceptual");
        view2_4.setWidth(99);
        view2_4.setHeight(50);
        view2_4.setTextSize(13);
        view2_4.setBackgroundColor(Color.LTGRAY);
        view2_4.setLayoutParams(marginLayout);
        view2_4.setGravity(Gravity.CENTER);
        view2_5 = new TextView(mContext);
        view2_5.setText("LOS");
        view2_5.setWidth(99);
        view2_5.setHeight(50);
        view2_5.setTextSize(13);
        view2_5.setBackgroundColor(Color.LTGRAY);
        view2_5.setLayoutParams(marginLayout);
        view2_5.setGravity(Gravity.CENTER);
        view2_3_2 = new TextView(mContext);
        view2_3_2.setText("Sequencing");
        view2_3_2.setWidth(99);
        view2_3_2.setHeight(50);
        view2_3_2.setTextSize(13);
        view2_3_2.setBackgroundColor(Color.LTGRAY);
        view2_3_2.setLayoutParams(marginLayout);
        view2_3_2.setGravity(Gravity.CENTER);
        view2_6 = new TextView(mContext);
        view2_6.setText("0=No");
        view2_6.setWidth(149);
        view2_6.setHeight(50);
        view2_6.setTextSize(13);
        view2_6.setBackgroundColor(Color.LTGRAY);
        view2_6.setLayoutParams(marginLayout);
        view2_6.setGravity(Gravity.CENTER);
        view2_7 = new TextView(mContext);
        view2_7.setText("1=Yes");
        view2_7.setWidth(149);
        view2_7.setHeight(50);
        view2_7.setTextSize(13);
        view2_7.setBackgroundColor(Color.LTGRAY);
        view2_7.setLayoutParams(marginLayout);
        view2_7.setGravity(Gravity.CENTER);
        view2_8 = new TextView(mContext);
        view2_8.setWidth(100);
        view2_8.setHeight(50);
        view2_8.setBackgroundColor(Color.LTGRAY);
        view2_8.setLayoutParams(marginLayout);
        view2_9 = new TextView(mContext);
        view2_9.setWidth(100);
        view2_9.setHeight(50);
        view2_9.setBackgroundColor(Color.LTGRAY);
        view2_9.setLayoutParams(marginLayout);
        viewLayout_2.addView(view2_1);
        viewLayout_2.addView(view2_2);
        viewLayout_2.addView(view2_3);
        viewLayout_2.addView(view2_3_1);
        viewLayout_2.addView(view2_4);
        viewLayout_2.addView(view2_5);
        viewLayout_2.addView(view2_3_2);
        viewLayout_2.addView(view2_6);
        viewLayout_2.addView(view2_7);
        viewLayout_2.addView(view2_8);
        viewLayout_2.addView(view2_9);
        mLayout.addView(viewLayout_2);

        txt1 = new EditText(mContext);
        txt1.setWidth(200);
        txt1.setHeight(150);
        txt1.setTextSize(13);
        txt1.setBackgroundColor(Color.WHITE);
        txt1.setLayoutParams(marginLayout);
        txt1.setGravity(Gravity.CENTER);
        txt2 = new EditText(mContext);
        txt2.setWidth(99);
        txt2.setHeight(150);
        txt2.setTextSize(13);
        txt2.setBackgroundColor(Color.WHITE);
        txt2.setLayoutParams(marginLayout);
        txt2.setGravity(Gravity.CENTER);
        txt3 = new EditText(mContext);
        txt3.setWidth(99);
        txt3.setHeight(150);
        txt3.setTextSize(13);
        txt3.setBackgroundColor(Color.WHITE);
        txt3.setLayoutParams(marginLayout);
        txt3.setGravity(Gravity.CENTER);
        txt3_1 = new EditText(mContext);
        txt3_1.setWidth(99);
        txt3_1.setHeight(150);
        txt3_1.setTextSize(13);
        txt3_1.setBackgroundColor(Color.WHITE);
        txt3_1.setLayoutParams(marginLayout);
        txt3_1.setGravity(Gravity.CENTER);
        txt4 = new EditText(mContext);
        txt4.setWidth(99);
        txt4.setHeight(150);
        txt4.setTextSize(13);
        txt4.setBackgroundColor(Color.WHITE);
        txt4.setLayoutParams(marginLayout);
        txt4.setGravity(Gravity.CENTER);
        txt5 = new EditText(mContext);
        txt5.setWidth(99);
        txt5.setHeight(150);
        txt5.setTextSize(13);
        txt5.setBackgroundColor(Color.WHITE);
        txt5.setLayoutParams(marginLayout);
        txt5.setGravity(Gravity.CENTER);
        txt3_2 = new EditText(mContext);
        txt3_2.setWidth(99);
        txt3_2.setHeight(150);
        txt3_2.setTextSize(13);
        txt3_2.setBackgroundColor(Color.WHITE);
        txt3_2.setLayoutParams(marginLayout);
        txt3_2.setGravity(Gravity.CENTER);
        txt6 = new EditText(mContext);
        txt6.setWidth(100);
        txt6.setHeight(150);
        txt6.setTextSize(13);/////////////////////////
        txt6.setBackgroundColor(Color.WHITE);
        txt6.setLayoutParams(marginLayout);
        txt6.setGravity(Gravity.CENTER);
        txt7 = new EditText(mContext);
        txt7.setWidth(100);
        txt7.setHeight(150);
        txt7.setTextSize(13);/////////////////////////
        txt7.setBackgroundColor(Color.WHITE);
        txt7.setLayoutParams(marginLayout);
        txt7.setGravity(Gravity.CENTER);
        button1_0 = new Button(mContext);
        button1_0.setWidth(149);
        button1_0.setHeight(150);
        button1_0.setBackgroundColor(Color.GRAY);
        button1_0.setLayoutParams(marginLayout);
        button1_0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score1 = 0;
                button1_0.setBackgroundColor(Color.GREEN);
                button1_1.setBackgroundColor(Color.GRAY);
            }
        });
        button1_1 = new Button(mContext);
        button1_1.setWidth(149);
        button1_1.setHeight(150);
        button1_1.setBackgroundColor(Color.GRAY);
        button1_1.setLayoutParams(marginLayout);
        button1_1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score1 = 1;
                button1_1.setBackgroundColor(Color.GREEN);
                button1_0.setBackgroundColor(Color.GRAY);
            }
        });


        viewLayout_3.addView(txt1);
        viewLayout_3.addView(txt2);
        viewLayout_3.addView(txt3);
        viewLayout_3.addView(txt3_1);
        viewLayout_3.addView(txt4);
        viewLayout_3.addView(txt5);
        viewLayout_3.addView(txt3_2);
        viewLayout_3.addView(button1_0);
        viewLayout_3.addView(button1_1);
        viewLayout_3.addView(txt6);
        viewLayout_3.addView(txt7);
        mLayout.addView(viewLayout_3);

        view3_1 = new TextView(mContext);
        view3_1.setText("");
        view3_1.setEnabled(false);
        view3_1.setWidth(200);
        view3_1.setHeight(100);
        view3_1.setBackgroundColor(Color.LTGRAY);
        view3_1.setLayoutParams(marginLayout);

        view3_2 = new TextView(mContext);
        view3_2.setText("Started to draw before told to begin Sample");
        view3_2.setWidth(300);
        view3_2.setHeight(100);
        view3_2.setTextSize(13);
        view3_2.setBackgroundColor(Color.LTGRAY);
        view3_2.setLayoutParams(marginLayout);
        view3_2.setGravity(Gravity.CENTER);

        view3_3 = new TextView(mContext);
        view3_3.setText("Started to draw before told to begin Test");
        view3_3.setWidth(300);
        view3_3.setHeight(100);
        view3_3.setTextSize(13);
        view3_3.setBackgroundColor(Color.LTGRAY);
        view3_3.setLayoutParams(marginLayout);
        view3_3.setGravity(Gravity.CENTER);

        view3_4 = new TextView(mContext);
        view3_4.setText("IF test was discontinued, record the number of circles that were completed.");
        view3_4.setWidth(500);
        view3_4.setHeight(100);
        view3_4.setTextSize(13);
        view3_4.setBackgroundColor(Color.LTGRAY);
        view3_4.setLayoutParams(marginLayout);
        view3_4.setGravity(Gravity.CENTER);
        viewLayout_4.addView(view3_1);
        viewLayout_4.addView(view3_2);
        viewLayout_4.addView(view3_3);
        viewLayout_4.addView(view3_4);
        mLayout.addView(viewLayout_4);

        txt8 = new EditText(mContext);
        txt8.setWidth(200);
        txt8.setHeight(150);
        txt8.setTextSize(13);
        txt8.setBackgroundColor(Color.WHITE);
        txt8.setLayoutParams(marginLayout);
        txt8.setGravity(Gravity.CENTER);
        txt9 = new EditText(mContext);
        txt9.setWidth(500);
        txt9.setHeight(150);
        txt9.setTextSize(13);
        txt9.setBackgroundColor(Color.WHITE);
        txt9.setLayoutParams(marginLayout);
        txt9.setGravity(Gravity.CENTER);

        button2_0 = new Button(mContext);
        button2_0.setWidth(149);
        button2_0.setHeight(150);
        button2_0.setText("0=No");
        button2_0.setBackgroundColor(Color.GRAY);
        button2_0.setLayoutParams(marginLayout);
        button2_0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score2_1 = 0;
                button2_0.setBackgroundColor(Color.GREEN);
                button2_1.setBackgroundColor(Color.GRAY);
            }
        });
        button2_1 = new Button(mContext);
        button2_1.setWidth(149);
        button2_1.setHeight(150);
        button2_1.setText("1=Yes");
        button2_1.setBackgroundColor(Color.GRAY);
        button2_1.setLayoutParams(marginLayout);
        button2_1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score2_1 = 1;
                button2_1.setBackgroundColor(Color.GREEN);
                button2_0.setBackgroundColor(Color.GRAY);
            }
        });
        button3_0 = new Button(mContext);
        button3_0.setWidth(149);
        button3_0.setHeight(150);
        button3_0.setText("0=No");
        button3_0.setBackgroundColor(Color.GRAY);
        button3_0.setLayoutParams(marginLayout);
        button3_0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score2_2 = 0;
                button3_0.setBackgroundColor(Color.GREEN);
                button3_1.setBackgroundColor(Color.GRAY);
            }
        });
        button3_1 = new Button(mContext);
        button3_1.setWidth(149);
        button3_1.setHeight(150);
        button3_1.setText("1=Yes");
        button3_1.setBackgroundColor(Color.GRAY);
        button3_1.setLayoutParams(marginLayout);
        button3_1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score2_2 = 1;
                button3_1.setBackgroundColor(Color.GREEN);
                button3_0.setBackgroundColor(Color.GRAY);
            }
        });

        viewLayout_5.addView(txt8);
        viewLayout_5.addView(button2_0);
        viewLayout_5.addView(button2_1);
        viewLayout_5.addView(button3_0);
        viewLayout_5.addView(button3_1);
        viewLayout_5.addView(txt9);
        mLayout.addView(viewLayout_5);

        addView(mLayout);
        return;
    }

    private void instantiate(final Context context, final TrailMakingQuestion drawing)
    {
        TrailMakingQuestion trailmakingQuestion = new TrailMakingQuestion();
        return;
    }
}
