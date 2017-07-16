package com.brainhealthtest.questions;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.brainhealthtest.osshelper.Helper;
import com.brainhealthtest.utility.BalanceQuestion;

import java.util.ArrayList;
import java.util.List;


public class BalanceView extends LinearLayout
{
    int totQuestions;
    public List<SingleBalanceView> b;
    Context mContext;
    public Handler callbackHandler;
    TextView view1;
    Button right, left;
    public int Type;

    public BalanceView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        mContext = context;
        //	instantiate(context, null);
    }

    public BalanceView(final Context context, final BalanceQuestion balanceQuestion, Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        mContext = context;
        instantiate(context, balanceQuestion);
    }

    public BalanceView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public void Init(BalanceQuestion balanceQuestion, Helper helper)
    {
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        mLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        marginLayout.setMargins(2, 2, 2, 2);//setMargins(left, top, right, bottom)


        TextView textView = new TextView(mContext);
        textView.setText(balanceQuestion.guideWord1);
        mLayout.addView(textView);

        totQuestions = 0;
        b = new ArrayList<SingleBalanceView>();
        for (int i = 0; i < balanceQuestion.ques.size(); ++i)//
        {
            SingleBalanceView singleb = new SingleBalanceView(mContext);
            singleb.Init(balanceQuestion.ques.get(i), helper);
            mLayout.addView(singleb);
            b.add(singleb);
            totQuestions++;
        }

        view1 = new TextView(mContext);
        view1.setText("Circle to indicate which leg was balanced on (foot on floor) first:");
        view1.setWidth(1400);
        view1.setHeight(80);
        view1.setTextSize(16);/////////////////////////
        view1.setBackgroundColor(Color.GRAY);
        view1.setLayoutParams(marginLayout);
        view1.setGravity(Gravity.CENTER);
        mLayout.addView(view1);

        LinearLayout viewLayout_1 = new LinearLayout(mContext);
        viewLayout_1.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout_1.setBackgroundColor(Color.BLACK);
        viewLayout_1.setLayoutParams(marginLayout);

        right = new Button(mContext);
        right.setWidth(700);
        right.setHeight(80);
        right.setText("Right 1");
        right.setLayoutParams(marginLayout);
        right.setBackgroundColor(Color.GRAY);
        right.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Type = 1;
                // TODO Auto-generated method stub
                right.setBackgroundColor(Color.GREEN);
                left.setBackgroundColor(Color.GRAY);
            }
        });
        left = new Button(mContext);
        left.setWidth(700);
        left.setHeight(80);
        left.setText("Left 2");
        left.setLayoutParams(marginLayout);
        left.setBackgroundColor(Color.GRAY);
        left.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Type = 2;
                // TODO Auto-generated method stub
                right.setBackgroundColor(Color.GRAY);
                left.setBackgroundColor(Color.GREEN);
            }
        });
        viewLayout_1.addView(right);
        viewLayout_1.addView(left);
        mLayout.addView(viewLayout_1);

        addView(mLayout);
        return;
    }

    private void instantiate(final Context context, final BalanceQuestion info)
    {
        BalanceQuestion balanceQuestion = new BalanceQuestion();
        balanceQuestion.ques = new ArrayList<String>();
        balanceQuestion.ques.add("test");
        return;
    }

}
