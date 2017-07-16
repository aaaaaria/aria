package com.brainhealthtest.questions;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.brainhealthtest.osshelper.Helper;
import com.brainhealthtest.utility.HooperVisualQuestion;

import java.util.ArrayList;
import java.util.List;


public class HooperVisualView extends LinearLayout
{
    int totQuestions;
    public List<SingleHooperVisualView> hv;
    Context mContext;
    public Handler callbackHandler;
    TextView view1, view2, view3, txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9, txt10;

    public HooperVisualView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public HooperVisualView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        mContext = context;
        //	instantiate(context, null);
    }

    public HooperVisualView(final Context context, final HooperVisualQuestion hoopervisualQuestion, Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        mContext = context;
        instantiate(context, hoopervisualQuestion);
    }

    public void Init(HooperVisualQuestion hoopervisualQuestion, Helper helper)
    {
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        mLayout.setGravity(Gravity.CENTER_HORIZONTAL);

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

        view1 = new TextView(mContext);
        view1.setText("RESPONSE");
        view1.setWidth(300);
        view1.setHeight(80);
        view1.setTextSize(16);/////////////////////////
        view1.setBackgroundColor(Color.WHITE);
        view1.setLayoutParams(marginLayout);
        view1.setGravity(Gravity.CENTER);
        viewLayout_1.addView(view1);

        view2 = new TextView(mContext);
        view2.setText("Score");
        view2.setWidth(446);
        view2.setHeight(80);
        view2.setTextSize(16);
        view2.setBackgroundColor(Color.WHITE);
        view2.setLayoutParams(marginLayout);
        view2.setGravity(Gravity.CENTER);
        viewLayout_1.addView(view2);

        view3 = new TextView(mContext);
        view3.setText("ERROR TYPE");
        view3.setWidth(558);
        view3.setHeight(80);
        view3.setTextSize(16);
        view3.setBackgroundColor(Color.WHITE);
        view3.setLayoutParams(marginLayout);
        view3.setGravity(Gravity.CENTER);
        viewLayout_1.addView(view3);
        mLayout.addView(viewLayout_1);

        txt1 = new TextView(mContext);
        txt1.setText("");
        txt1.setWidth(300);
        txt1.setHeight(80);
        txt1.setTextSize(14);/////////////////////////
        txt1.setBackgroundColor(Color.WHITE);
        txt1.setLayoutParams(marginLayout);
        txt1.setGravity(Gravity.CENTER);
        viewLayout_2.addView(txt1);
        txt2 = new TextView(mContext);
        txt2.setText("Incorrect");
        txt2.setWidth(110);
        txt2.setHeight(80);
        txt2.setTextSize(13);/////////////////////////
        txt2.setBackgroundColor(Color.WHITE);
        txt2.setLayoutParams(marginLayout);
        txt2.setGravity(Gravity.CENTER);
        viewLayout_2.addView(txt2);
        txt3 = new TextView(mContext);
        txt3.setText(".5pt");
        txt3.setWidth(110);
        txt3.setHeight(80);
        txt3.setTextSize(14);/////////////////////////
        txt3.setBackgroundColor(Color.WHITE);
        txt3.setLayoutParams(marginLayout);
        txt3.setGravity(Gravity.CENTER);
        viewLayout_2.addView(txt3);
        txt4 = new TextView(mContext);
        txt4.setText("Correct");
        txt4.setWidth(110);
        txt4.setHeight(80);
        txt4.setTextSize(14);/////////////////////////
        txt4.setBackgroundColor(Color.WHITE);
        txt4.setLayoutParams(marginLayout);
        txt4.setGravity(Gravity.CENTER);
        viewLayout_2.addView(txt4);
        txt5 = new TextView(mContext);
        txt5.setText("No Guess");
        txt5.setWidth(110);
        txt5.setHeight(80);
        txt5.setTextSize(14);/////////////////////////
        txt5.setBackgroundColor(Color.WHITE);
        txt5.setLayoutParams(marginLayout);
        txt5.setGravity(Gravity.CENTER);
        viewLayout_2.addView(txt5);
        txt6 = new TextView(mContext);
        txt6.setText("Isolate");
        txt6.setWidth(110);
        txt6.setHeight(80);
        txt6.setTextSize(14);/////////////////////////
        txt6.setBackgroundColor(Color.WHITE);
        txt6.setLayoutParams(marginLayout);
        txt6.setGravity(Gravity.CENTER);
        viewLayout_2.addView(txt6);
        txt7 = new TextView(mContext);
        txt7.setText("Percept.");
        txt7.setWidth(110);
        txt7.setHeight(80);
        txt7.setTextSize(14);/////////////////////////
        txt7.setBackgroundColor(Color.WHITE);
        txt7.setLayoutParams(marginLayout);
        txt7.setGravity(Gravity.CENTER);
        viewLayout_2.addView(txt7);
        txt8 = new TextView(mContext);
        txt8.setText("Psv.");
        txt8.setWidth(110);
        txt8.setHeight(80);
        txt8.setTextSize(14);/////////////////////////
        txt8.setBackgroundColor(Color.WHITE);
        txt8.setLayoutParams(marginLayout);
        txt8.setGravity(Gravity.CENTER);
        viewLayout_2.addView(txt8);
        txt9 = new TextView(mContext);
        txt9.setText("LOS");
        txt9.setWidth(110);
        txt9.setHeight(80);
        txt9.setTextSize(14);/////////////////////////
        txt9.setBackgroundColor(Color.WHITE);
        txt9.setLayoutParams(marginLayout);
        txt9.setGravity(Gravity.CENTER);
        viewLayout_2.addView(txt9);
        txt10 = new TextView(mContext);
        txt10.setText("Other");
        txt10.setWidth(110);
        txt10.setHeight(80);
        txt10.setTextSize(14);/////////////////////////
        txt10.setBackgroundColor(Color.WHITE);
        txt10.setLayoutParams(marginLayout);
        txt10.setGravity(Gravity.CENTER);
        viewLayout_2.addView(txt10);
        mLayout.addView(viewLayout_2);

        LinearLayout mLayout2 = new LinearLayout(mContext);
        mLayout2.setOrientation(LinearLayout.VERTICAL);
        mLayout2.setGravity(Gravity.CENTER_HORIZONTAL);
        mLayout2.setBackgroundColor(Color.BLACK);
        totQuestions = 0;
        hv = new ArrayList<SingleHooperVisualView>();
        for (int i = 0; i < hoopervisualQuestion.ques.size(); ++i)//
        {
            SingleHooperVisualView singlehv = new SingleHooperVisualView(mContext);
            singlehv.Init(hoopervisualQuestion.ques.get(i), hoopervisualQuestion.halfpoint.get(i), helper);
            mLayout2.addView(singlehv);
            hv.add(singlehv);
            totQuestions++;
        }
        mLayout.addView(mLayout2);


        addView(mLayout);
        return;
    }

    private void instantiate(final Context context, final HooperVisualQuestion info)
    {
        HooperVisualQuestion hoopervisualQuestion = new HooperVisualQuestion();
        hoopervisualQuestion.ques = new ArrayList<String>();
        hoopervisualQuestion.ques.add("test");
        return;
    }

}
