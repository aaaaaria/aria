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
import com.brainhealthtest.utility.Verbal_fluencyQuestion;

import java.util.ArrayList;
import java.util.List;


public class VerbalFluencyView extends LinearLayout
{
    int totQuestions;
    public List<SingleVerbalFluencyView> vf;
    Context mContext;
    Button recordBtn, stopBtn;
    private EditText voicetotext;
    public Handler callbackHandler;

    public VerbalFluencyView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        mContext = context;
        //	instantiate(context, null);
    }

    public VerbalFluencyView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public VerbalFluencyView(final Context context, final Verbal_fluencyQuestion verbal_fluencyQuestion, Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        mContext = context;
        instantiate(context, verbal_fluencyQuestion);
    }

    public void Init(Verbal_fluencyQuestion verbal_fluencyQuestion, Helper helper)
    {
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        mLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        LinearLayout mLayout2 = new LinearLayout(mContext);
        mLayout2.setOrientation(LinearLayout.VERTICAL);
        mLayout2.setGravity(Gravity.CENTER_HORIZONTAL);

        LinearLayout mLayout3 = new LinearLayout(mContext);
        mLayout3.setOrientation(LinearLayout.HORIZONTAL);
        mLayout3.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView textView = new TextView(mContext);
        textView.setText(verbal_fluencyQuestion.title);
        textView.setTextSize(25);
        textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        mLayout.addView(textView);

        textView = new TextView(mContext);
        textView.setText(verbal_fluencyQuestion.guideWord1);
        mLayout.addView(textView);


        recordBtn = new Button(mContext);
        recordBtn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        recordBtn.setGravity(Gravity.CENTER);
        recordBtn.setText("Record");
        recordBtn.setWidth(150);
        mLayout.addView(recordBtn);

        stopBtn = new Button(mContext);
        stopBtn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        stopBtn.setGravity(Gravity.CENTER);
        stopBtn.setText("Stop");
        stopBtn.setWidth(150);
        mLayout.addView(stopBtn);

        textView = new TextView(mContext);
        textView.setText(verbal_fluencyQuestion.guideWord2);
        textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        mLayout.addView(textView);

        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        marginLayout.setMargins(2, 2, 2, 2);//setMargins(left, top, right, bottom)
        LinearLayout viewLayout = new LinearLayout(mContext);
        viewLayout.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout.setBackgroundColor(Color.BLACK);
        viewLayout.setLayoutParams(marginLayout);


        textView = new TextView(mContext);
        textView.setText(verbal_fluencyQuestion.title);
        textView.setWidth(644);
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        viewLayout.addView(textView);

        textView = new TextView(mContext);
        textView.setText("Voice to text");
        textView.setWidth(644);
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        viewLayout.addView(textView);
        mLayout.addView(viewLayout);


        totQuestions = 0;
        vf = new ArrayList<SingleVerbalFluencyView>();
        for (int i = 0; i < verbal_fluencyQuestion.ques.size(); ++i)
        {
            SingleVerbalFluencyView singlevf = new SingleVerbalFluencyView(mContext);
            singlevf.Init(verbal_fluencyQuestion.ques.get(i), helper);
            mLayout2.addView(singlevf);
            vf.add(singlevf);
            totQuestions++;
        }

        mLayout3.addView(mLayout2);
        voicetotext = new EditText(mContext);
        voicetotext.setWidth(644);
        voicetotext.setHeight(700);
        voicetotext.setTextSize(13);
        voicetotext.setLayoutParams(marginLayout);
        voicetotext.setBackgroundColor(Color.WHITE);
        mLayout3.addView(voicetotext);

        mLayout.addView(mLayout3);

        Log.d("vf", String.valueOf("verbal_fluencyQuestion.ques.size()" + verbal_fluencyQuestion.ques.size()));
        Log.d("vf", String.valueOf("totQuestions" + totQuestions)); /////////////test

        addView(mLayout);//add all layout to the screen
        return;


    }

    private void instantiate(final Context context, final Verbal_fluencyQuestion verbal_fluency)
    {
        Verbal_fluencyQuestion verbal_fluencyQuestion = new Verbal_fluencyQuestion();
        verbal_fluencyQuestion.ques = new ArrayList<String>();
        verbal_fluencyQuestion.ques.add("test");
        // next line: for preview
        //Init(trailerQuestion, null);
        return;
    }

    public void Save(Helper helper)
    {
        for (int i = 0; i < vf.size(); ++i)
            vf.get(i).Save(helper);
    }

}
