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
import com.brainhealthtest.utility.TrailerQuestion;
import com.brainhealthtest.voicetest.DigitView;

import java.util.ArrayList;
import java.util.List;


public class TrailerView extends LinearLayout
{
    int totQuestions;
    public List<SingleTrailerView> trails;
    Context mContext;
    TextView view1, view2, desView;
    int mScore1, mScore2;
    Button playBtn, recordBtn, stopBtn;

    List<DigitView> digits;
    public Handler callbackHandler;

    /**
     * This is used when creating the view in XML To have an image load in XML
     * use the tag
     * 'image="http://developer.android.com/images/dialog_buttons.png"'
     * Replacing the url with your desired image Once you have instantiated the
     * XML view you can call setImageDrawable(url) to change the image
     *
     * @param context
     * @param attrSet
     */
    public TrailerView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        mContext = context;
        instantiate(context, null);
    }

    public void Save(Helper helper)
    {
        for (int i = 0; i < trails.size(); ++i)
            trails.get(i).Save(helper);
    }

    public void Init(TrailerQuestion trailerQuestion, Helper helper)
    {
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        mLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView textView = new TextView(mContext);
        textView.setText(trailerQuestion.title);
        textView.setTextSize(25);
        textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        mLayout.addView(textView);

        textView = new TextView(mContext);
        textView.setText(trailerQuestion.guideWord1);
        mLayout.addView(textView);

        playBtn = new Button(mContext);
        playBtn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        playBtn.setGravity(Gravity.CENTER);
        playBtn.setText("Play");
        playBtn.setWidth(150);
        mLayout.addView(playBtn);

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
        textView.setText(trailerQuestion.guideWord2);
        textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        mLayout.addView(textView);

        desView = new TextView(mContext);
        desView.setText("Which word went with ______?      \n" +
                "BE SURE TO GIVE FEEDBACK AFTER EACH RESPONSE, EITHER:\n" +
                "Right    OR    No, that one was _____________\n");
        mLayout.addView(desView);

        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        marginLayout.setMargins(2, 2, 2, 2);

        LinearLayout viewLayout = new LinearLayout(mContext);
        viewLayout.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout.setBackgroundColor(Color.BLACK);
        viewLayout.setLayoutParams(marginLayout);

        textView = new TextView(mContext);
        textView.setText("First\nRecall\n");
        textView.setWidth(160);
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        viewLayout.addView(textView);

        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(Color.BLACK);

        LinearLayout linearLayout1 = new LinearLayout(mContext);
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout1.setBackgroundColor(Color.BLACK);

        textView = new TextView(mContext);
        textView.setText("Score");
        textView.setWidth(220);
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        linearLayout.addView(textView);

        textView = new TextView(mContext);
        textView.setText("Easy\n");
        textView.setWidth(110);
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        linearLayout1.addView(textView);

        textView = new TextView(mContext);
        textView.setText("Hard\n");
        textView.setWidth(110);
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        linearLayout1.addView(textView);

        linearLayout.addView(linearLayout1);
        viewLayout.addView(linearLayout);

        linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(Color.BLACK);

        linearLayout1 = new LinearLayout(mContext);
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout1.setBackgroundColor(Color.BLACK);

        textView = new TextView(mContext);
        textView.setText("Incorrect Associates");
        textView.setWidth(580);
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        linearLayout.addView(textView);

        textView = new TextView(mContext);
        textView.setText("PAL\n");
        textView.setWidth(110);
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        linearLayout1.addView(textView);

        textView = new TextView(mContext);
        textView.setText("Related\n");
        textView.setWidth(110);
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        linearLayout1.addView(textView);

        textView = new TextView(mContext);
        textView.setWidth(110);
        textView.setText("Not\nRelated");
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        linearLayout1.addView(textView);

        textView = new TextView(mContext);
        textView.setText("Answers Verbatim\n");
        textView.setWidth(250);
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        linearLayout1.addView(textView);

        linearLayout.addView(linearLayout1);
        viewLayout.addView(linearLayout);

        textView = new TextView(mContext);
        textView.setText("No\nGuess\n");
        textView.setWidth(110);
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        viewLayout.addView(textView);

        textView = new TextView(mContext);
        textView.setText("\nPersey\n");
        textView.setWidth(110);
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        viewLayout.addView(textView);

        textView = new TextView(mContext);
        textView.setText("\nREPEAT\n");
        textView.setWidth(110);
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        viewLayout.addView(textView);

        mLayout.addView(viewLayout);

        totQuestions = 0;
        trails = new ArrayList<SingleTrailerView>();
        for (int i = 0; i < trailerQuestion.ques.size(); ++i)
        {
            SingleTrailerView trail = new SingleTrailerView(mContext);
            trail.Init(trailerQuestion.score.get(i), trailerQuestion.ques.get(i), trailerQuestion.ans.get(i), helper);
            mLayout.addView(trail);
            trails.add(trail);
            totQuestions++;
        }

        LinearLayout ly = new LinearLayout(mContext);
        ly.setOrientation(LinearLayout.HORIZONTAL);
        ly.setBackgroundColor(Color.GRAY);

        TextView view = new TextView(mContext);
        view.setBackgroundColor(Color.GRAY);
        view.setText("SCORE:");
        view.setWidth(160);
        ly.addView(view);

        view1 = new TextView(mContext);
        view1.setWidth(110);
        view1.setText("0");
        ly.addView(view1);

        view2 = new TextView(mContext);
        view2.setWidth(110);
        view2.setText("0");
        ly.addView(view2);

        LayoutParams marginTopLayout = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        marginTopLayout.setMargins(0, 0, 10, 10);
        ly.setLayoutParams(marginTopLayout);
        marginTopLayout.setMargins(0, 0, 0, 10);
        trails.get(trails.size() - 1).setLayoutParams(marginTopLayout);
        mLayout.addView(ly);

        //mLayout.addView(view);
        Button scoreBtn = new Button(mContext);
        scoreBtn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        scoreBtn.setGravity(Gravity.CENTER);
        scoreBtn.setText("Score");
        scoreBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mScore1 = 0;
                mScore2 = 0;
                for (int i = 0; i < totQuestions; ++i)
                {
                    if (trails.get(i).ss == 1)
                        mScore1 += trails.get(i).GetScore();
                    else
                        mScore2 += trails.get(i).GetScore();
                }
                view1.setText(Integer.toString(mScore1));
                view2.setText(Integer.toString(mScore2));
            }
        });
        scoreBtn.setWidth(150);
        mLayout.addView(scoreBtn);

        addView(mLayout);
        return;

    }

    public int GetScore1()
    {
        return mScore1;
    }

    public int GetScore2()
    {
        return mScore2;
    }


    public TrailerView(final Context context)
    {
        super(context);
    }

    public TrailerView(final Context context, final TrailerQuestion trailerQuestion,
                       Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        mContext = context;
        instantiate(context, trailerQuestion);
    }

    private void instantiate(final Context context, final TrailerQuestion trailer)
    {
        TrailerQuestion trailerQuestion = new TrailerQuestion();
        trailerQuestion.ques = new ArrayList<String>();
        trailerQuestion.ans = new ArrayList<String>();
        trailerQuestion.score = new ArrayList<Integer>();
        trailerQuestion.ques.add("test");
        trailerQuestion.ans.add("test");
        trailerQuestion.score.add(1);
        // next line: for preview
        //Init(trailerQuestion, null);
        return;
    }

}
