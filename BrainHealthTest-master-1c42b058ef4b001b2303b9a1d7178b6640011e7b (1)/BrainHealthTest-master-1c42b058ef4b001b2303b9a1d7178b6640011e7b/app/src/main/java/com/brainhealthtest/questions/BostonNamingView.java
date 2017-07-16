package com.brainhealthtest.questions;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.brainhealthtest.osshelper.Helper;
import com.brainhealthtest.utility.Boston_namingQuestion;

import java.util.ArrayList;
import java.util.List;


public class BostonNamingView extends LinearLayout
{
    int totQuestions;
    public List<SingleBostonNamingView> bn;
    Context mContext;
    Button recordBtn, stopBtn;
    TextView view1, view2, view3;
    private EditText voicetotext;
    public Handler callbackHandler;

    public BostonNamingView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        mContext = context;
        //	instantiate(context, null);
    }

    public BostonNamingView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public BostonNamingView(final Context context, final Boston_namingQuestion boston_namingQuestion, Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        mContext = context;
        instantiate(context, boston_namingQuestion);
    }

    public void Init(Boston_namingQuestion boston_namingQuestion, Helper helper)
    {
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        mLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView textView = new TextView(mContext);
        textView = new TextView(mContext);
        textView.setText(boston_namingQuestion.guideWord1);
        mLayout.addView(textView);

        textView = new TextView(mContext);
        textView.setText(boston_namingQuestion.guideWord2);
        mLayout.addView(textView);
        textView = new TextView(mContext);
        textView.setText(boston_namingQuestion.guideWord3);
        mLayout.addView(textView);
        textView = new TextView(mContext);
        textView.setText(boston_namingQuestion.guideWord4);
        mLayout.addView(textView);

        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        marginLayout.setMargins(2, 2, 2, 2);//setMargins(left, top, right, bottom)
        LinearLayout viewLayout = new LinearLayout(mContext);
        viewLayout.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout.setBackgroundColor(Color.BLACK);
        viewLayout.setLayoutParams(marginLayout);

        view1 = new TextView(mContext);
        view1.setText("No Cue\nCircle 0 if incorrect\nCircle 1 if correct with no cues\nCircle 2 if correct immediately upon presentation; leave Time blank. ");
        view1.setWidth(450);
        view1.setHeight(180);
        view1.setTextSize(13);/////////////////////////
        view1.setBackgroundColor(Color.WHITE);
        view1.setLayoutParams(marginLayout);
        view1.setGravity(Gravity.CENTER);
        viewLayout.addView(view1);

        view2 = new TextView(mContext);
        view2.setText("Semantic Cue\nCircle 0 if incorrect\nCircle 1 if correct with Semantic cue\nLeave blank if Semantic cue not given");
        view2.setWidth(450);
        view2.setHeight(180);
        view2.setTextSize(13);
        view2.setBackgroundColor(Color.WHITE);
        view2.setLayoutParams(marginLayout);
        view2.setGravity(Gravity.CENTER);
        viewLayout.addView(view2);

        view3 = new TextView(mContext);
        view3.setText("Phonemic Cue\nCircle 0 if incorrect\nCircle 1 if correct with Phonemic cue\nLeave blank if Phonemic cue not given");
        view3.setWidth(450);
        view3.setHeight(180);
        view3.setTextSize(13);
        view3.setBackgroundColor(Color.WHITE);
        view3.setLayoutParams(marginLayout);
        view3.setGravity(Gravity.CENTER);
        viewLayout.addView(view3);
        mLayout.addView(viewLayout);

        LinearLayout mLayout2 = new LinearLayout(mContext);
        mLayout2.setOrientation(LinearLayout.VERTICAL);
        mLayout2.setGravity(Gravity.CENTER_HORIZONTAL);
        totQuestions = 0;
        bn = new ArrayList<SingleBostonNamingView>();
        for (int i = 0; i < boston_namingQuestion.ques1.size(); ++i)
        {
            SingleBostonNamingView singlebn = new SingleBostonNamingView(mContext);
            singlebn.Init(boston_namingQuestion.ques1.get(i), boston_namingQuestion.ques2.get(i), boston_namingQuestion.ques3.get(i), helper);
            mLayout2.addView(singlebn);
            bn.add(singlebn);
            totQuestions++;
        }
        Log.d("bn", String.valueOf("boston_namingQuestion.ques.size()" + boston_namingQuestion.ques1.size()));
        Log.d("bn", String.valueOf("totQuestions" + totQuestions)); /////////////test


        mLayout.addView(mLayout2);
        addView(mLayout);
        return;

    }

    private void instantiate(final Context context, final Boston_namingQuestion boston_naming)
    {
        Boston_namingQuestion boston_namingQuestion = new Boston_namingQuestion();
        boston_namingQuestion.ques1 = new ArrayList<String>();
        boston_namingQuestion.ques2 = new ArrayList<String>();
        boston_namingQuestion.ques3 = new ArrayList<String>();
        boston_namingQuestion.ques1.add("test");
        boston_namingQuestion.ques2.add("test");
        boston_namingQuestion.ques3.add("test");
        // next line: for preview
        //Init(trailerQuestion, null);
        return;
    }


}
