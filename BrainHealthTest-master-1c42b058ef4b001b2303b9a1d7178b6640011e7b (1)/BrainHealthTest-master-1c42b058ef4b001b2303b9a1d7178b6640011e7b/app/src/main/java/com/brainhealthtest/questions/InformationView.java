package com.brainhealthtest.questions;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.brainhealthtest.osshelper.Helper;
import com.brainhealthtest.utility.InformationQuestion;

import java.util.ArrayList;
import java.util.List;


public class InformationView extends LinearLayout
{
    int totQuestions;
    public List<SingleInformationView> in;
    Context mContext;
    public String t = "0";
    TextView view1, view2, view3, view4, view5, view6;
    public Handler callbackHandler;

    public InformationView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public InformationView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        mContext = context;
        //	instantiate(context, null);
    }

    public InformationView(final Context context, final InformationQuestion informationQuestion, Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        mContext = context;
        instantiate(context, informationQuestion);
    }

    public void Init(InformationQuestion informationQuestion, Helper helper)
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


        TextView textView = new TextView(mContext);
        textView = new TextView(mContext);
        textView.setText(informationQuestion.guideWord1);
        mLayout.addView(textView);

        view1 = new TextView(mContext);
        view1.setText("Question");
        view1.setWidth(450);
        view1.setHeight(80);
        view1.setTextSize(16);/////////////////////////
        view1.setBackgroundColor(Color.WHITE);
        view1.setLayoutParams(marginLayout);
        view1.setGravity(Gravity.CENTER);
        viewLayout_1.addView(view1);

        view2 = new TextView(mContext);
        view2.setText("Response");
        view2.setWidth(350);
        view2.setHeight(80);
        view2.setTextSize(16);
        view2.setBackgroundColor(Color.WHITE);
        view2.setLayoutParams(marginLayout);
        view2.setGravity(Gravity.CENTER);
        viewLayout_1.addView(view2);

        view3 = new TextView(mContext);
        view3.setText("Incorrect");
        view3.setWidth(125);
        view3.setHeight(80);
        view3.setTextSize(16);
        view3.setBackgroundColor(Color.WHITE);
        view3.setLayoutParams(marginLayout);
        view3.setGravity(Gravity.CENTER);
        viewLayout_1.addView(view3);

        view4 = new TextView(mContext);
        view4.setText("Correct");
        view4.setWidth(125);
        view4.setHeight(80);
        view4.setTextSize(16);
        view4.setBackgroundColor(Color.WHITE);
        view4.setLayoutParams(marginLayout);
        view4.setGravity(Gravity.CENTER);
        viewLayout_1.addView(view4);

        view5 = new TextView(mContext);
        view5.setText("No guess");
        view5.setWidth(125);
        view5.setHeight(80);
        view5.setTextSize(16);
        view5.setBackgroundColor(Color.WHITE);
        view5.setLayoutParams(marginLayout);
        view5.setGravity(Gravity.CENTER);
        viewLayout_1.addView(view5);

        view6 = new TextView(mContext);
        view6.setText("d/cd");
        view6.setWidth(125);
        view6.setHeight(80);
        view6.setTextSize(16);
        view6.setBackgroundColor(Color.WHITE);
        view6.setLayoutParams(marginLayout);
        view6.setGravity(Gravity.CENTER);
        viewLayout_1.addView(view6);
        mLayout.addView(viewLayout_1);

        LinearLayout mLayout2 = new LinearLayout(mContext);
        mLayout2.setOrientation(LinearLayout.VERTICAL);
        mLayout2.setGravity(Gravity.CENTER_HORIZONTAL);
        totQuestions = 0;
        in = new ArrayList<SingleInformationView>();
        for (int i = 0; i < informationQuestion.ques.size(); ++i)
        {
            SingleInformationView singlein = new SingleInformationView(mContext);
            if (totQuestions == 10)
                t = "10";
            singlein.Init(informationQuestion.ques.get(i), t, helper);
            mLayout2.addView(singlein);
            in.add(singlein);
            totQuestions++;
        }


        mLayout.addView(mLayout2);

        addView(mLayout);
        return;

    }

    private void instantiate(final Context context, final InformationQuestion info)
    {
        InformationQuestion informationQuestion = new InformationQuestion();
        informationQuestion.ques = new ArrayList<String>();
        informationQuestion.ques.add("test");
        return;
    }

}
