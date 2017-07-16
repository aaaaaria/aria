package com.brainhealthtest.questions;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.brainhealthtest.osshelper.Helper;
import com.brainhealthtest.utility.DigitSymbolQuestion;

import java.util.ArrayList;
import java.util.List;


public class DigitSymbolView extends LinearLayout
{
    public EditText time, Observations;
    public List<SingleDigitSymbolView> ds1, ds2;
    public Handler callbackHandler;
    int totQuestions;
    Context mContext;
    TextView view1, view2, view3, view4, view5, view6;
    TextView view3_1, view3_2, view3_3, view3_4, view3_5, view3_6, view3_7, view3_8;
    TextView view7_1, view7_2, view7_3, view7_4;
    public EditText txt4_1, txt4_2, txt4_3, txt4_4, txt4_5, txt4_6, txt4_7, txt4_8;
    public EditText txt5_1, txt5_2, txt5_3, txt5_4, txt5_5, txt5_6, txt5_7, txt5_8;

    public DigitSymbolView(final Context context, final AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        //	instantiate(context, null);
        // TODO Auto-generated constructor stub
    }

    public void Init(DigitSymbolQuestion digitsymbolQuestion, Helper helper)
    {
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        marginLayout.setMargins(2, 2, 2, 2);//setMargins(left, top, right, bottom)

        TextView textView = new TextView(mContext);
        textView = new TextView(mContext);
        mLayout.addView(textView);

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
        LinearLayout viewLayout_6 = new LinearLayout(mContext);
        viewLayout_6.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout_6.setBackgroundColor(Color.BLACK);
        viewLayout_6.setLayoutParams(marginLayout);
        LinearLayout viewLayout_7 = new LinearLayout(mContext);
        viewLayout_7.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout_7.setBackgroundColor(Color.BLACK);
        viewLayout_7.setLayoutParams(marginLayout);

        //layout1
        view1 = new TextView(mContext);
        view1.setText("Total time to complete task(min:sec)");
        view1.setWidth(760);
        view1.setHeight(80);
        view1.setTextSize(10);/////////////////////////
        view1.setBackgroundColor(Color.WHITE);
        view1.setLayoutParams(marginLayout);
        view1.setGravity(Gravity.CENTER);
        viewLayout_1.addView(view1);

        time = new EditText(mContext);
        time.setWidth(660);
        time.setHeight(80);
        time.setTextSize(10);/////////////////////////
        time.setBackgroundColor(Color.WHITE);
        time.setLayoutParams(marginLayout);
        time.setGravity(Gravity.CENTER);
        viewLayout_1.addView(time);
        mLayout.addView(viewLayout_1);

        //layout2
        view2 = new TextView(mContext);
        view2.setText("Number of squares completed by the end of each time range(cumulative)");
        view2.setWidth(1420);
        view2.setHeight(80);
        view2.setTextSize(15);
        view2.setBackgroundColor(Color.GRAY);
        view2.setLayoutParams(marginLayout);
        view2.setGravity(Gravity.CENTER);
        viewLayout_2.addView(view2);
        mLayout.addView(viewLayout_2);

        //layout3
        view3 = new TextView(mContext);
        view3.setText(" ");
        view3.setWidth(440);
        view3.setHeight(100);
        view3.setTextSize(13);
        view3.setBackgroundColor(Color.LTGRAY);
        view3.setLayoutParams(marginLayout);
        view3.setGravity(Gravity.CENTER);
        viewLayout_3.addView(view3);

        view3_1 = new TextView(mContext);
        view3_1.setText("00:00-\n00:15");
        view3_1.setWidth(120);
        view3_1.setHeight(100);
        view3_1.setTextSize(12);
        view3_1.setBackgroundColor(Color.LTGRAY);
        view3_1.setLayoutParams(marginLayout);
        view3_1.setGravity(Gravity.CENTER);
        viewLayout_3.addView(view3_1);

        view3_2 = new TextView(mContext);
        view3_2.setText("00:16-\n00:30");
        view3_2.setWidth(120);
        view3_2.setHeight(100);
        view3_2.setTextSize(12);
        view3_2.setBackgroundColor(Color.LTGRAY);
        view3_2.setLayoutParams(marginLayout);
        view3_2.setGravity(Gravity.CENTER);
        viewLayout_3.addView(view3_2);

        view3_3 = new TextView(mContext);
        view3_3.setText("00:31-\n00:45");
        view3_3.setWidth(120);
        view3_3.setHeight(100);
        view3_3.setTextSize(12);
        view3_3.setBackgroundColor(Color.LTGRAY);
        view3_3.setLayoutParams(marginLayout);
        view3_3.setGravity(Gravity.CENTER);
        viewLayout_3.addView(view3_3);

        view3_4 = new TextView(mContext);
        view3_4.setText("00:46-\n1:00");
        view3_4.setWidth(120);
        view3_4.setHeight(100);
        view3_4.setTextSize(12);
        view3_4.setBackgroundColor(Color.LTGRAY);
        view3_4.setLayoutParams(marginLayout);
        view3_4.setGravity(Gravity.CENTER);
        viewLayout_3.addView(view3_4);

        view3_5 = new TextView(mContext);
        view3_5.setText("1:01-\n1:15");
        view3_5.setWidth(120);
        view3_5.setHeight(100);
        view3_5.setTextSize(12);
        view3_5.setBackgroundColor(Color.LTGRAY);
        view3_5.setLayoutParams(marginLayout);
        view3_5.setGravity(Gravity.CENTER);
        viewLayout_3.addView(view3_5);

        view3_6 = new TextView(mContext);
        view3_6.setText("1:16-\n1:30");
        view3_6.setWidth(120);
        view3_6.setHeight(100);
        view3_6.setTextSize(12);
        view3_6.setBackgroundColor(Color.LTGRAY);
        view3_6.setLayoutParams(marginLayout);
        view3_6.setGravity(Gravity.CENTER);
        viewLayout_3.addView(view3_6);

        view3_7 = new TextView(mContext);
        view3_7.setText("1:31-\n1:45");
        view3_7.setWidth(120);
        view3_7.setHeight(100);
        view3_7.setTextSize(12);
        view3_7.setBackgroundColor(Color.LTGRAY);
        view3_7.setLayoutParams(marginLayout);
        view3_7.setGravity(Gravity.CENTER);
        viewLayout_3.addView(view3_7);

        view3_8 = new TextView(mContext);
        view3_8.setText("1:46-\n2:00");
        view3_8.setWidth(120);
        view3_8.setHeight(100);
        view3_8.setTextSize(12);
        view3_8.setBackgroundColor(Color.LTGRAY);
        view3_8.setLayoutParams(marginLayout);
        view3_8.setGravity(Gravity.CENTER);
        viewLayout_3.addView(view3_8);

        mLayout.addView(viewLayout_3);
        //Log.d("cookie",String.valueOf("questions1="+cookietheftQuestion.ques1.size()));

        //Layout4
        view4 = new TextView(mContext);
        view4.setText("Total number CORRECT: ");
        view4.setWidth(440);
        view4.setHeight(100);
        view4.setTextSize(15);/////////////////////////
        view4.setBackgroundColor(Color.LTGRAY);
        view4.setLayoutParams(marginLayout);
        view4.setGravity(Gravity.CENTER);
        viewLayout_4.addView(view4);

        txt4_1 = new EditText(mContext);
        txt4_1.setWidth(120);
        txt4_1.setHeight(100);
        txt4_1.setTextSize(13);
        txt4_1.setBackgroundColor(Color.WHITE);
        txt4_1.setLayoutParams(marginLayout);
        txt4_2 = new EditText(mContext);
        txt4_2.setWidth(120);
        txt4_2.setHeight(100);
        txt4_2.setTextSize(13);
        txt4_2.setBackgroundColor(Color.WHITE);
        txt4_2.setLayoutParams(marginLayout);
        txt4_3 = new EditText(mContext);
        txt4_3.setWidth(120);
        txt4_3.setHeight(100);
        txt4_3.setTextSize(13);
        txt4_3.setBackgroundColor(Color.WHITE);
        txt4_3.setLayoutParams(marginLayout);
        txt4_4 = new EditText(mContext);
        txt4_4.setWidth(120);
        txt4_4.setHeight(100);
        txt4_4.setTextSize(13);
        txt4_4.setBackgroundColor(Color.WHITE);
        txt4_4.setLayoutParams(marginLayout);
        txt4_5 = new EditText(mContext);
        txt4_5.setWidth(120);
        txt4_5.setHeight(100);
        txt4_5.setTextSize(13);
        txt4_5.setBackgroundColor(Color.WHITE);
        txt4_5.setLayoutParams(marginLayout);
        txt4_6 = new EditText(mContext);
        txt4_6.setWidth(120);
        txt4_6.setHeight(100);
        txt4_6.setTextSize(13);
        txt4_6.setBackgroundColor(Color.WHITE);
        txt4_6.setLayoutParams(marginLayout);
        txt4_7 = new EditText(mContext);
        txt4_7.setWidth(120);
        txt4_7.setHeight(100);
        txt4_7.setTextSize(13);
        txt4_7.setBackgroundColor(Color.WHITE);
        txt4_7.setLayoutParams(marginLayout);
        txt4_8 = new EditText(mContext);
        txt4_8.setWidth(120);
        txt4_8.setHeight(100);
        txt4_8.setTextSize(13);
        txt4_8.setBackgroundColor(Color.WHITE);
        txt4_8.setLayoutParams(marginLayout);
        viewLayout_4.addView(txt4_1);
        viewLayout_4.addView(txt4_2);
        viewLayout_4.addView(txt4_3);
        viewLayout_4.addView(txt4_4);
        viewLayout_4.addView(txt4_5);
        viewLayout_4.addView(txt4_6);
        viewLayout_4.addView(txt4_7);
        viewLayout_4.addView(txt4_8);

        mLayout.addView(viewLayout_4);

        //Layout5
        view5 = new TextView(mContext);
        view5.setText("Total number INCORRECT: ");
        view5.setWidth(440);
        view5.setHeight(100);
        view5.setTextSize(15);/////////////////////////
        view5.setBackgroundColor(Color.LTGRAY);
        view5.setLayoutParams(marginLayout);
        view5.setGravity(Gravity.CENTER);
        viewLayout_5.addView(view5);

        txt5_1 = new EditText(mContext);
        txt5_1.setWidth(120);
        txt5_1.setHeight(100);
        txt5_1.setTextSize(13);
        txt5_1.setBackgroundColor(Color.WHITE);
        txt5_1.setLayoutParams(marginLayout);
        txt5_2 = new EditText(mContext);
        txt5_2.setWidth(120);
        txt5_2.setHeight(100);
        txt5_2.setTextSize(13);
        txt5_2.setBackgroundColor(Color.WHITE);
        txt5_2.setLayoutParams(marginLayout);
        txt5_3 = new EditText(mContext);
        txt5_3.setWidth(120);
        txt5_3.setHeight(100);
        txt5_3.setTextSize(13);
        txt5_3.setBackgroundColor(Color.WHITE);
        txt5_3.setLayoutParams(marginLayout);
        txt5_4 = new EditText(mContext);
        txt5_4.setWidth(120);
        txt5_4.setHeight(100);
        txt5_4.setTextSize(13);
        txt5_4.setBackgroundColor(Color.WHITE);
        txt5_4.setLayoutParams(marginLayout);
        txt5_5 = new EditText(mContext);
        txt5_5.setWidth(120);
        txt5_5.setHeight(100);
        txt5_5.setTextSize(13);
        txt5_5.setBackgroundColor(Color.WHITE);
        txt5_5.setLayoutParams(marginLayout);
        txt5_6 = new EditText(mContext);
        txt5_6.setWidth(120);
        txt5_6.setHeight(100);
        txt5_6.setTextSize(13);
        txt5_6.setBackgroundColor(Color.WHITE);
        txt5_6.setLayoutParams(marginLayout);
        txt5_7 = new EditText(mContext);
        txt5_7.setWidth(120);
        txt5_7.setHeight(100);
        txt5_7.setTextSize(13);
        txt5_7.setBackgroundColor(Color.WHITE);
        txt5_7.setLayoutParams(marginLayout);
        txt5_8 = new EditText(mContext);
        txt5_8.setWidth(120);
        txt5_8.setHeight(100);
        txt5_8.setTextSize(13);
        txt5_8.setBackgroundColor(Color.WHITE);
        txt5_8.setLayoutParams(marginLayout);
        viewLayout_5.addView(txt5_1);
        viewLayout_5.addView(txt5_2);
        viewLayout_5.addView(txt5_3);
        viewLayout_5.addView(txt5_4);
        viewLayout_5.addView(txt5_5);
        viewLayout_5.addView(txt5_6);
        viewLayout_5.addView(txt5_7);
        viewLayout_5.addView(txt5_8);

        mLayout.addView(viewLayout_5);

        //layout6
        view6 = new TextView(mContext);
        view6.setText("Qualitative Observation(frequency across first two minutes)");
        view6.setWidth(1420);
        view6.setHeight(80);
        view6.setTextSize(15);
        view6.setBackgroundColor(Color.GRAY);
        view6.setLayoutParams(marginLayout);
        view6.setGravity(Gravity.CENTER);
        viewLayout_6.addView(view6);
        mLayout.addView(viewLayout_6);

        //layout7
        view7_1 = new TextView(mContext);
        view7_1.setText("Error");
        view7_1.setWidth(445);
        view7_1.setHeight(80);
        view7_1.setTextSize(13);/////////////////////////
        view7_1.setBackgroundColor(Color.LTGRAY);
        view7_1.setLayoutParams(marginLayout);
        view7_1.setGravity(Gravity.CENTER);
        viewLayout_7.addView(view7_1);
        view7_2 = new TextView(mContext);
        view7_2.setText("Frequency");
        view7_2.setWidth(245);
        view7_2.setHeight(80);
        view7_2.setTextSize(13);/////////////////////////
        view7_2.setBackgroundColor(Color.LTGRAY);
        view7_2.setLayoutParams(marginLayout);
        view7_2.setGravity(Gravity.CENTER);
        viewLayout_7.addView(view7_2);
        view7_3 = new TextView(mContext);
        view7_3.setText("Self Corrections");
        view7_3.setWidth(485);
        view7_3.setHeight(80);
        view7_3.setTextSize(13);/////////////////////////
        view7_3.setBackgroundColor(Color.LTGRAY);
        view7_3.setLayoutParams(marginLayout);
        view7_3.setGravity(Gravity.CENTER);
        viewLayout_7.addView(view7_3);
        view7_4 = new TextView(mContext);
        view7_4.setText("Frequency");
        view7_4.setWidth(242);
        view7_4.setHeight(80);
        view7_4.setTextSize(15);/////////////////////////
        view7_4.setBackgroundColor(Color.LTGRAY);
        view7_4.setLayoutParams(marginLayout);
        view7_4.setGravity(Gravity.CENTER);
        viewLayout_7.addView(view7_4);

        mLayout.addView(viewLayout_7);

//        Log.d("cookie",String.valueOf("questions1="+digitsymbolQuestion.ques1.size()));
//        Log.d("cookie",String.valueOf("questions1="+digitsymbolQuestion.ques2.size()));

        LinearLayout mLayout2 = new LinearLayout(mContext);
        mLayout2.setOrientation(LinearLayout.VERTICAL);
        mLayout2.setGravity(Gravity.CENTER_HORIZONTAL);
        mLayout2.setLayoutParams(marginLayout);
        mLayout2.setBackgroundColor(Color.BLACK);
        totQuestions = 0;
        ds1 = new ArrayList<SingleDigitSymbolView>();
        for (int i = 0; i < 2; ++i)
        {
            SingleDigitSymbolView answertext1 = new SingleDigitSymbolView(mContext);
            answertext1.Init(digitsymbolQuestion.ques1.get(i), digitsymbolQuestion.ques2.get(i), 1, helper);
            mLayout2.addView(answertext1);
            ds1.add(answertext1);
            totQuestions++;
        }
        mLayout.addView(mLayout2);

        LinearLayout mLayout3 = new LinearLayout(mContext);
        mLayout3.setOrientation(LinearLayout.VERTICAL);
        mLayout3.setGravity(Gravity.CENTER_HORIZONTAL);
        mLayout3.setLayoutParams(marginLayout);
        mLayout3.setBackgroundColor(Color.BLACK);
        totQuestions = 0;
        ds2 = new ArrayList<SingleDigitSymbolView>();
        for (int i = 2; i < digitsymbolQuestion.ques1.size(); ++i)
        {
            SingleDigitSymbolView answertext2 = new SingleDigitSymbolView(mContext);
            answertext2.Init(digitsymbolQuestion.ques1.get(i), digitsymbolQuestion.ques2.get(i), 2, helper);
            mLayout3.addView(answertext2);
            ds2.add(answertext2);
            totQuestions++;
        }
        mLayout.addView(mLayout3);

        addView(mLayout);
        return;

    }

    public DigitSymbolView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public DigitSymbolView(final Context context, final DigitSymbolQuestion digitQuestion, Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        mContext = context;
        instantiate(context, digitQuestion);
    }

    private void instantiate(final Context context, final DigitSymbolQuestion digitQuestion)
    {
        DigitSymbolQuestion digitsymbolQuestion = new DigitSymbolQuestion();
        digitsymbolQuestion.ques1 = new ArrayList<String>();
        digitsymbolQuestion.ques2 = new ArrayList<String>();
        digitsymbolQuestion.ques1.add("test");
        digitsymbolQuestion.ques2.add("test");
        // next line: for preview

        return;
    }


}
