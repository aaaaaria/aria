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
import com.brainhealthtest.utility.SimilarQuestion;
import com.brainhealthtest.voicetest.DigitView;

import java.util.ArrayList;
import java.util.List;


public class SimilarView extends LinearLayout
{
    int totQuestions;
    public List<SingleSimilarView> similars;
    Context mContext;
    TextView view1, view2;
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
    public SimilarView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        mContext = context;
        instantiate(context, null);
    }

    public void Save(Helper helper)
    {
        for (int i = 0; i < similars.size(); ++i)
            similars.get(i).Save(helper);
    }

    public void Init(SimilarQuestion similarQuestion, Helper helper)
    {
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        mLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView textView = new TextView(mContext);
        textView = new TextView(mContext);
        textView.setText(similarQuestion.guideWord1);
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
        textView.setText(similarQuestion.guideWord2);
        textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        mLayout.addView(textView);


        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        marginLayout.setMargins(2, 2, 2, 2);//
        /*set the column*/
        LinearLayout viewLayout = new LinearLayout(mContext);
        viewLayout.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout.setBackgroundColor(Color.BLACK);
        viewLayout.setLayoutParams(marginLayout);

        textView = new TextView(mContext);
        textView.setText("Recall\n");
        textView.setWidth(236);
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
        textView.setText("Response\n");
        textView.setWidth(250);
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        viewLayout.addView(textView);

        textView = new TextView(mContext);
        textView.setText("Score");
        textView.setWidth(463);
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        linearLayout.addView(textView);

        textView = new TextView(mContext);
        textView.setText("Zero");
        textView.setWidth(91);
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        linearLayout1.addView(textView);

        textView = new TextView(mContext);
        textView.setText("One");
        textView.setWidth(91);
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        linearLayout1.addView(textView);

        textView = new TextView(mContext);
        textView.setText("Two");
        textView.setWidth(91);
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        linearLayout1.addView(textView);

        textView = new TextView(mContext);
        textView.setText("NoG");
        textView.setWidth(91);
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        linearLayout1.addView(textView);

        textView = new TextView(mContext);
        textView.setText("d/c");
        textView.setWidth(91);
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
        textView.setWidth(370);
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        linearLayout.addView(textView);

        textView = new TextView(mContext);
        textView.setText("LOS");
        textView.setWidth(91);
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        linearLayout1.addView(textView);

        textView = new TextView(mContext);
        textView.setText("Con");
        textView.setWidth(91);
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        linearLayout1.addView(textView);

        textView = new TextView(mContext);
        textView.setWidth(91);
        textView.setText("Psv");
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        linearLayout1.addView(textView);

        textView = new TextView(mContext);
        textView.setWidth(91);
        textView.setText("Other");
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(marginLayout);
        textView.setGravity(Gravity.CENTER);
        linearLayout1.addView(textView);

        linearLayout.addView(linearLayout1);
        viewLayout.addView(linearLayout);

        mLayout.addView(viewLayout);

        totQuestions = 0;
        similars = new ArrayList<SingleSimilarView>();
        for (int i = 0; i < similarQuestion.ques1.size(); ++i)
        {
            SingleSimilarView similar = new SingleSimilarView(mContext);
            similar.Init(similarQuestion.ques1.get(i), similarQuestion.ques2.get(i), helper);
            mLayout.addView(similar);
            similars.add(similar);
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
        similars.get(similars.size() - 1).setLayoutParams(marginTopLayout);

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
                    if (similars.get(i).ss == 1)
                        mScore1 += similars.get(i).GetScore();
                    else
                        mScore2 += similars.get(i).GetScore();
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


    public SimilarView(final Context context)
    {
        super(context);
    }

    public SimilarView(final Context context, final SimilarQuestion similarQuestion, Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        mContext = context;
        instantiate(context, similarQuestion);
    }

    private void instantiate(final Context context, final SimilarQuestion similar)
    {
        SimilarQuestion similarQuestion = new SimilarQuestion();
        similarQuestion.ques1 = new ArrayList<String>();
        similarQuestion.ques2 = new ArrayList<String>();
        similarQuestion.score = new ArrayList<Integer>();
        similarQuestion.ques1.add("test");
        similarQuestion.ques2.add("test");
        similarQuestion.score.add(1);
        // next line: for preview
        //Init(trailerQuestion, null);
        return;
    }

}
