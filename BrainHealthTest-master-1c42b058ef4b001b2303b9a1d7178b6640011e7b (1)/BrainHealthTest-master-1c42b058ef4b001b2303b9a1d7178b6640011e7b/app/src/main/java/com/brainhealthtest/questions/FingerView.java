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
import com.brainhealthtest.utility.FingerQuestion;

import java.util.ArrayList;
import java.util.List;


public class FingerView extends LinearLayout
{
    int totQuestions;
    public List<SingleFingerView> ft;
    Context mContext;
    TextView view1, view2, view1_1, view1_2, view2_1, view2_2, comments;
    public Handler callbackHandler;


    public FingerView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        mContext = context;
        //	instantiate(context, null);
    }

    public FingerView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public FingerView(final Context context, final FingerQuestion fingerQuestion, Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        mContext = context;
        instantiate(context, fingerQuestion);
    }

    public void Init(FingerQuestion fingerQuestion, Helper helper)
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

        TextView textView = new TextView(mContext);
        textView = new TextView(mContext);
        textView.setText(fingerQuestion.guideWord1);
        mLayout.addView(textView);

        view1 = new TextView(mContext);
        view1.setText("Right Hand");
        view1.setWidth(650);
        view1.setHeight(80);
        view1.setTextSize(16);/////////////////////////
        view1.setBackgroundColor(Color.WHITE);
        view1.setLayoutParams(marginLayout);
        view1.setGravity(Gravity.CENTER);
        viewLayout_1.addView(view1);

        view2 = new TextView(mContext);
        view2.setText("Left Hand");
        view2.setWidth(650);
        view2.setHeight(80);
        view2.setTextSize(16);
        view2.setBackgroundColor(Color.WHITE);
        view2.setLayoutParams(marginLayout);
        view2.setGravity(Gravity.CENTER);
        viewLayout_1.addView(view2);
        mLayout.addView(viewLayout_1);

        view1_1 = new TextView(mContext);
        view1_1.setText("Trial");
        view1_1.setWidth(324);
        view1_1.setHeight(80);
        view1_1.setTextSize(15);
        view1_1.setBackgroundColor(Color.WHITE);
        view1_1.setLayoutParams(marginLayout);
        view1_1.setGravity(Gravity.CENTER);
        viewLayout_2.addView(view1_1);
        view1_2 = new TextView(mContext);
        view1_2.setText("# of Tap");
        view1_2.setWidth(324);
        view1_2.setHeight(80);
        view1_2.setTextSize(15);
        view1_2.setBackgroundColor(Color.WHITE);
        view1_2.setLayoutParams(marginLayout);
        view1_2.setGravity(Gravity.CENTER);
        viewLayout_2.addView(view1_2);
        view2_1 = new TextView(mContext);
        view2_1.setText("Trial");
        view2_1.setWidth(324);
        view2_1.setHeight(80);
        view2_1.setTextSize(15);
        view2_1.setBackgroundColor(Color.WHITE);
        view2_1.setLayoutParams(marginLayout);
        view2_1.setGravity(Gravity.CENTER);
        viewLayout_2.addView(view2_1);
        view2_2 = new TextView(mContext);
        view2_2.setText("# of Tap");
        view2_2.setWidth(324);
        view2_2.setHeight(80);
        view2_2.setTextSize(15);
        view2_2.setBackgroundColor(Color.WHITE);
        view2_2.setLayoutParams(marginLayout);
        view2_2.setGravity(Gravity.CENTER);
        viewLayout_2.addView(view2_2);
        mLayout.addView(viewLayout_2);

        LinearLayout mLayout2 = new LinearLayout(mContext);
        mLayout2.setOrientation(LinearLayout.VERTICAL);
        mLayout2.setGravity(Gravity.CENTER_HORIZONTAL);
        totQuestions = 0;
        ft = new ArrayList<SingleFingerView>();
        for (int i = 0; i < fingerQuestion.ques.size(); ++i)
        {
            SingleFingerView singleft = new SingleFingerView(mContext);
            singleft.Init(fingerQuestion.ques.get(i), helper);
            mLayout2.addView(singleft);
            ft.add(singleft);
            totQuestions++;
        }

        mLayout.addView(mLayout2);

        comments = new TextView(mContext);
        comments.setText("Comments: (Will NOT be keyed; transfer comments that should be keyed to Factors Affecting Testing page in the back of the battery.)");
        comments.setWidth(1300);
        comments.setHeight(100);
        comments.setTextSize(13);/////////////////////////
        comments.setBackgroundColor(Color.WHITE);
        comments.setLayoutParams(marginLayout);
        comments.setGravity(Gravity.CENTER);
        mLayout.addView(comments);

        EditText com = new EditText(mContext);
        com.setWidth(1300);
        com.setHeight(100);
        com.setTextSize(13);/////////////////////////
        com.setBackgroundColor(Color.WHITE);
        com.setLayoutParams(marginLayout);
        com.setGravity(Gravity.CENTER);
        mLayout.addView(com);

        addView(mLayout);
        return;
    }

    private void instantiate(final Context context, final FingerQuestion finger)
    {
        FingerQuestion fingerQuestion = new FingerQuestion();
        fingerQuestion.ques = new ArrayList<String>();
        fingerQuestion.ques.add("test");
        // next line: for preview
        //Init(trailerQuestion, null);
        return;
    }


}
