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
import com.brainhealthtest.utility.ReadingTestQuestion;

import java.util.ArrayList;
import java.util.List;


public class ReadingTestView extends LinearLayout
{
    int totQuestions;
    public List<SingleReadingTestView> rt;
    public String t = "0";
    Context mContext;
    Button recordBtn, stopBtn;
    TextView view1, view2, view3, word, correct, incorrect, error, noguess, dc;
    private EditText voicetotext;
    public Handler callbackHandler;

    public ReadingTestView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        mContext = context;
        //	instantiate(context, null);
    }

    public ReadingTestView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public ReadingTestView(final Context context, final ReadingTestQuestion readingQuestion, Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        mContext = context;
        instantiate(context, readingQuestion);
    }

    public void Init(ReadingTestQuestion readingtestQuestion, Helper helper)
    {
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        mLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView textView = new TextView(mContext);
        textView = new TextView(mContext);
        textView.setText(readingtestQuestion.guideWord1);
        mLayout.addView(textView);

        textView = new TextView(mContext);
        textView.setText(readingtestQuestion.guideWord2);
        mLayout.addView(textView);
        textView = new TextView(mContext);
        textView.setText(readingtestQuestion.guideWord3);
        mLayout.addView(textView);
        textView = new TextView(mContext);
        textView.setText(readingtestQuestion.guideWord4);
        mLayout.addView(textView);

        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        marginLayout.setMargins(2, 2, 2, 2);//setMargins(left, top, right, bottom)
        LinearLayout viewLayout = new LinearLayout(mContext);
        viewLayout.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout.setBackgroundColor(Color.BLACK);
        viewLayout.setLayoutParams(marginLayout);


        view1 = new TextView(mContext);
        view1.setText("Circle ��0�� for incorrect response and indicate error types \n(2=phonemic, 3=semantic, 4=wrong accent error); \n��1�� for correct responses; \n��8�� if test was discontinued. ");
        view1.setWidth(664);
        view1.setHeight(200);
        view1.setTextSize(13);/////////////////////////
        view1.setBackgroundColor(Color.WHITE);
        view1.setLayoutParams(marginLayout);
        view1.setGravity(Gravity.CENTER);
        viewLayout.addView(view1);

        view2 = new TextView(mContext);
        view2.setText("A B O S E   R T H U P   I V Z J Q   (15)\nIf letters are administered, put score to right; else leave blank.   ____ ____");
        view2.setWidth(650);
        view2.setHeight(200);
        view2.setTextSize(13);
        view2.setBackgroundColor(Color.WHITE);
        view2.setLayoutParams(marginLayout);
        view2.setGravity(Gravity.CENTER);
        viewLayout.addView(view2);
        mLayout.addView(viewLayout);

        LinearLayout mLayout1 = new LinearLayout(mContext);
        mLayout1.setOrientation(LinearLayout.HORIZONTAL);
        mLayout1.setGravity(Gravity.CENTER_HORIZONTAL);
        mLayout1.setBackgroundColor(Color.BLACK);
        word = new TextView(mContext);
        word.setText("");
        word.setWidth(500);
        word.setHeight(80);
        word.setTextSize(13);/////////////////////////
        word.setBackgroundColor(Color.WHITE);
        word.setLayoutParams(marginLayout);
        word.setGravity(Gravity.CENTER);
        mLayout1.addView(word);
        correct = new TextView(mContext);
        correct.setText("correct");
        correct.setWidth(150);
        correct.setHeight(80);
        correct.setTextSize(13);/////////////////////////
        correct.setBackgroundColor(Color.WHITE);
        correct.setLayoutParams(marginLayout);
        correct.setGravity(Gravity.CENTER);
        mLayout1.addView(correct);
        incorrect = new TextView(mContext);
        incorrect.setText("incorrect");
        incorrect.setWidth(150);
        incorrect.setHeight(80);
        incorrect.setTextSize(13);/////////////////////////
        incorrect.setBackgroundColor(Color.WHITE);
        incorrect.setLayoutParams(marginLayout);
        incorrect.setGravity(Gravity.CENTER);
        mLayout1.addView(incorrect);
        error = new TextView(mContext);
        error.setText("error type");
        error.setWidth(300);
        error.setHeight(80);
        error.setTextSize(13);/////////////////////////
        error.setBackgroundColor(Color.WHITE);
        error.setLayoutParams(marginLayout);
        error.setGravity(Gravity.CENTER);
        mLayout1.addView(error);
        noguess = new TextView(mContext);
        noguess.setText("no guess");
        noguess.setWidth(100);
        noguess.setHeight(80);
        noguess.setTextSize(13);/////////////////////////
        noguess.setBackgroundColor(Color.WHITE);
        noguess.setLayoutParams(marginLayout);
        noguess.setGravity(Gravity.CENTER);
        mLayout1.addView(noguess);
        dc = new TextView(mContext);
        dc.setText("d/c'd");
        dc.setWidth(100);
        dc.setHeight(80);
        dc.setTextSize(13);/////////////////////////
        dc.setBackgroundColor(Color.WHITE);
        dc.setLayoutParams(marginLayout);
        dc.setGravity(Gravity.CENTER);
        mLayout1.addView(dc);

        mLayout.addView(mLayout1);

        LinearLayout mLayout2 = new LinearLayout(mContext);
        mLayout2.setOrientation(LinearLayout.VERTICAL);
        mLayout2.setGravity(Gravity.CENTER_HORIZONTAL);
        mLayout2.setBackgroundColor(Color.BLACK);
        totQuestions = 0;
        rt = new ArrayList<SingleReadingTestView>();
        for (int i = 0; i < readingtestQuestion.ques1.size() & readingtestQuestion.flag2 != 8; ++i)//
        {
            SingleReadingTestView singlert = new SingleReadingTestView(mContext);
            if (totQuestions == 10)
                t = "10";
            singlert.Init(readingtestQuestion.ques1.get(i), readingtestQuestion.ques2.get(i), t, helper);
            mLayout2.addView(singlert);
            rt.add(singlert);
            totQuestions++;

        }
        Log.d("rt", String.valueOf("readingtestQuestion.ques.size()" + readingtestQuestion.ques1.size()));
        Log.d("rt", String.valueOf("totQuestions" + totQuestions)); /////////////test

        mLayout.addView(mLayout2);

        addView(mLayout);
        return;

    }

    private void instantiate(final Context context, final ReadingTestQuestion readingtest)
    {
        ReadingTestQuestion readingtestQuestion = new ReadingTestQuestion();
        readingtestQuestion.ques1 = new ArrayList<String>();
        readingtestQuestion.ques2 = new ArrayList<String>();
        t = "0";
        readingtestQuestion.ques1.add("test");
        readingtestQuestion.ques2.add("test");
        // next line: for preview
        //Init(trailerQuestion, null);
        return;
    }

}
