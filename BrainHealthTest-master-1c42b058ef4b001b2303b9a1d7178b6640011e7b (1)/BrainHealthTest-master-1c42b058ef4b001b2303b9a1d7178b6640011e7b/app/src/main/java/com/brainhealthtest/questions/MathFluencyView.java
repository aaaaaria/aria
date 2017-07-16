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
import com.brainhealthtest.utility.MathFluencyQuestion;

import java.util.ArrayList;
import java.util.List;


public class MathFluencyView extends LinearLayout
{

    Context mContext;
    TextView view1, view2, view3, view3_1, view3_2, view3_3, view5, view6_1, view6_2, view6_3, view6_4;
    int totQuestions;
    public EditText time;
    public List<SingleMathFluencyView> mf1;
    public List<SingleMathFluencyViewBeta> mf2_1, mf2_2;
    public Handler callbackHandler;


    public void Init(MathFluencyQuestion mathfluencyQuestion, Helper helper)
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

        //layout1
        view1 = new TextView(mContext);
        view1.setText("Total time to complete task(min:sec)\n (must be < 3:00) ");
        view1.setWidth(760);
        view1.setHeight(80);
        view1.setTextSize(12);/////////////////////////
        view1.setBackgroundColor(Color.WHITE);
        view1.setLayoutParams(marginLayout);
        view1.setGravity(Gravity.CENTER);
        viewLayout_1.addView(view1);

        time = new EditText(mContext);
        time.setWidth(660);
        time.setHeight(80);
        time.setTextSize(12);/////////////////////////
        time.setBackgroundColor(Color.WHITE);
        time.setLayoutParams(marginLayout);
        time.setGravity(Gravity.CENTER);
        viewLayout_1.addView(time);
        mLayout.addView(viewLayout_1);

        LinearLayout viewLayout_2 = new LinearLayout(mContext);
        viewLayout_2.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout_2.setBackgroundColor(Color.BLACK);
        viewLayout_2.setLayoutParams(marginLayout);

        //layout2
        view2 = new TextView(mContext);
        view2.setText("Cumulative frequencies by time interval");
        view2.setWidth(1420);
        view2.setHeight(80);
        view2.setTextSize(15);
        view2.setBackgroundColor(Color.GRAY);
        view2.setLayoutParams(marginLayout);
        view2.setGravity(Gravity.CENTER);
        viewLayout_2.addView(view2);
        mLayout.addView(viewLayout_2);

        LinearLayout viewLayout_3 = new LinearLayout(mContext);
        viewLayout_3.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout_3.setBackgroundColor(Color.BLACK);
        viewLayout_3.setLayoutParams(marginLayout);

        //layout3
        view3 = new TextView(mContext);
        view3.setText(" ");
        view3.setWidth(350);
        view3.setHeight(100);
        view3.setTextSize(13);
        view3.setBackgroundColor(Color.LTGRAY);
        view3.setLayoutParams(marginLayout);
        view3.setGravity(Gravity.CENTER);
        viewLayout_3.addView(view3);
        view3_1 = new TextView(mContext);
        view3_1.setText("00:00-01:00");
        view3_1.setWidth(350);
        view3_1.setHeight(100);
        view3_1.setTextSize(13);
        view3_1.setBackgroundColor(Color.LTGRAY);
        view3_1.setLayoutParams(marginLayout);
        view3_1.setGravity(Gravity.CENTER);
        viewLayout_3.addView(view3_1);
        view3_2 = new TextView(mContext);
        view3_2.setText("1:01-2:00");
        view3_2.setWidth(350);
        view3_2.setHeight(100);
        view3_2.setTextSize(13);
        view3_2.setBackgroundColor(Color.LTGRAY);
        view3_2.setLayoutParams(marginLayout);
        view3_2.setGravity(Gravity.CENTER);
        viewLayout_3.addView(view3_2);
        view3_3 = new TextView(mContext);
        view3_3.setText("2:01-3:00");
        view3_3.setWidth(350);
        view3_3.setHeight(100);
        view3_3.setTextSize(13);
        view3_3.setBackgroundColor(Color.LTGRAY);
        view3_3.setLayoutParams(marginLayout);
        view3_3.setGravity(Gravity.CENTER);
        viewLayout_3.addView(view3_3);
        mLayout.addView(viewLayout_3);

        LinearLayout viewLayout_4 = new LinearLayout(mContext);
        viewLayout_4.setOrientation(LinearLayout.VERTICAL);
        viewLayout_4.setBackgroundColor(Color.BLACK);
        viewLayout_4.setLayoutParams(marginLayout);

        totQuestions = 0;
        mf1 = new ArrayList<SingleMathFluencyView>();
        for (int i = 0; i < mathfluencyQuestion.ques1.size(); ++i)
        {
            SingleMathFluencyView answerview = new SingleMathFluencyView(mContext);
            answerview.Init(mathfluencyQuestion.ques1.get(i), helper);
            viewLayout_4.addView(answerview);
            mf1.add(answerview);
            totQuestions++;
        }
        mLayout.addView(viewLayout_4);

        LinearLayout viewLayout_5 = new LinearLayout(mContext);
        viewLayout_5.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout_5.setBackgroundColor(Color.BLACK);
        viewLayout_5.setLayoutParams(marginLayout);

        //layout5
        view5 = new TextView(mContext);
        view5.setText("Qualitative Observations (totals across full time range)");
        view5.setWidth(1420);
        view5.setHeight(80);
        view5.setTextSize(15);
        view5.setBackgroundColor(Color.GRAY);
        view5.setLayoutParams(marginLayout);
        view5.setGravity(Gravity.CENTER);
        viewLayout_5.addView(view5);
        mLayout.addView(viewLayout_5);

        LinearLayout viewLayout_6 = new LinearLayout(mContext);
        viewLayout_6.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout_6.setBackgroundColor(Color.BLACK);
        viewLayout_6.setLayoutParams(marginLayout);
        //layout6
        view6_1 = new TextView(mContext);
        view6_1.setText("Error");
        view6_1.setWidth(445);
        view6_1.setHeight(80);
        view6_1.setTextSize(13);/////////////////////////
        view6_1.setBackgroundColor(Color.LTGRAY);
        view6_1.setLayoutParams(marginLayout);
        view6_1.setGravity(Gravity.CENTER);
        viewLayout_6.addView(view6_1);
        view6_2 = new TextView(mContext);
        view6_2.setText("Frequency");
        view6_2.setWidth(245);
        view6_2.setHeight(80);
        view6_2.setTextSize(13);/////////////////////////
        view6_2.setBackgroundColor(Color.LTGRAY);
        view6_2.setLayoutParams(marginLayout);
        view6_2.setGravity(Gravity.CENTER);
        viewLayout_6.addView(view6_2);
        view6_3 = new TextView(mContext);
        view6_3.setText("Self Corrections");
        view6_3.setWidth(485);
        view6_3.setHeight(80);
        view6_3.setTextSize(13);/////////////////////////
        view6_3.setBackgroundColor(Color.LTGRAY);
        view6_3.setLayoutParams(marginLayout);
        view6_3.setGravity(Gravity.CENTER);
        viewLayout_6.addView(view6_3);
        view6_4 = new TextView(mContext);
        view6_4.setText("Frequency");
        view6_4.setWidth(242);
        view6_4.setHeight(80);
        view6_4.setTextSize(15);/////////////////////////
        view6_4.setBackgroundColor(Color.LTGRAY);
        view6_4.setLayoutParams(marginLayout);
        view6_4.setGravity(Gravity.CENTER);
        viewLayout_6.addView(view6_4);
        mLayout.addView(viewLayout_6);

        LinearLayout mLayout2 = new LinearLayout(mContext);
        mLayout2.setOrientation(LinearLayout.VERTICAL);
        mLayout2.setGravity(Gravity.CENTER_HORIZONTAL);
        mLayout2.setLayoutParams(marginLayout);
        mLayout2.setBackgroundColor(Color.BLACK);

        mf2_1 = new ArrayList<SingleMathFluencyViewBeta>();
        for (int i = 0; i < 2; ++i)
        {
            SingleMathFluencyViewBeta answerview1 = new SingleMathFluencyViewBeta(mContext);
            answerview1.Init(mathfluencyQuestion.ques2_1.get(i), mathfluencyQuestion.ques2_2.get(i), 1, helper);
            mLayout2.addView(answerview1);
            mf2_1.add(answerview1);
        }
        mLayout.addView(mLayout2);
//      Log.d("cookie",String.valueOf("questions2_1="+mathfluencyQuestion.ques2_1.size()));


        LinearLayout mLayout3 = new LinearLayout(mContext);
        mLayout3.setOrientation(LinearLayout.VERTICAL);
        mLayout3.setGravity(Gravity.CENTER_HORIZONTAL);
        mLayout3.setLayoutParams(marginLayout);
        mLayout3.setBackgroundColor(Color.BLACK);
        totQuestions = 0;
        mf2_2 = new ArrayList<SingleMathFluencyViewBeta>();
        for (int j = 2; j < mathfluencyQuestion.ques2_1.size(); ++j)
        {
            SingleMathFluencyViewBeta answerview2 = new SingleMathFluencyViewBeta(mContext);
            answerview2.Init(mathfluencyQuestion.ques2_1.get(j), mathfluencyQuestion.ques2_2.get(j), 2, helper);
            mLayout3.addView(answerview2);
            mf2_2.add(answerview2);
            totQuestions++;
        }
        mLayout.addView(mLayout3);


        addView(mLayout);
        return;


    }

    public MathFluencyView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public MathFluencyView(final Context context, final AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        //	instantiate(context, null);
        // TODO Auto-generated constructor stub
    }

    public MathFluencyView(final Context context, final MathFluencyQuestion mathQuestion, Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        mContext = context;
        instantiate(context, mathQuestion);
    }

    private void instantiate(final Context context, final MathFluencyQuestion mathQuestion)
    {
        MathFluencyQuestion mathfluencyQuestion = new MathFluencyQuestion();
        mathfluencyQuestion.ques1 = new ArrayList<String>();
        mathfluencyQuestion.ques2_1 = new ArrayList<String>();
        mathfluencyQuestion.ques2_2 = new ArrayList<String>();
        mathfluencyQuestion.ques1.add("test");
        mathfluencyQuestion.ques2_1.add("test");
        mathfluencyQuestion.ques2_2.add("test");
        // next line: for preview

        return;
    }

}
