package com.brainhealthtest.questions;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brainhealthtest.osshelper.Helper;


public class SingleInformationView extends LinearLayout
{
    private String pair1;
    private TextView word;
    public EditText Answer;
    public Button score0, score1, score6, scoreN;
    public Handler callbackHandler;
    public int Score;

    public void Init(String mpair1, String number, Helper helper)
    {
        pair1 = mpair1;
        word.setText(pair1);
        if (number == "10")
            scoreN.setText("8");
        else
        {
            scoreN.setEnabled(false);
            scoreN.setText("n/a");
        }
        Score = -1;
    }


    public SingleInformationView(Context context)
    {
        super(context);
        Context mContext = context;
        // TODO Auto-generated constructor stub
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.HORIZONTAL);
        mLayout.setBackgroundColor(Color.BLACK);
        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        marginLayout.setMargins(2, 2, 2, 2);

        word = new TextView(mContext);
        word.setWidth(450);
        word.setHeight(100);
        word.setTextSize(14);
        word.setLayoutParams(marginLayout);
        word.setBackgroundColor(Color.WHITE);
        mLayout.addView(word);

        Answer = new EditText(context);
        Answer.setWidth(350);
        Answer.setHeight(100);
        Answer.setTextSize(14);
        Answer.setLayoutParams(marginLayout);
        Answer.setBackgroundColor(Color.WHITE);
        mLayout.addView(Answer);

        score0 = new Button(mContext);
        score0.setWidth(125);
        score0.setHeight(90);
        score0.setText("0");
        score0.setLayoutParams(marginLayout);
        score0.setBackgroundColor(Color.GRAY);
        score0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Score = 0;
                // TODO Auto-generated method stub
                score0.setBackgroundColor(Color.GREEN);
                score1.setBackgroundColor(Color.GRAY);
                score6.setBackgroundColor(Color.GRAY);
                scoreN.setBackgroundColor(Color.GRAY);
            }
        });
        score1 = new Button(mContext);
        score1.setWidth(125);
        score1.setHeight(90);
        score1.setText("1");
        score1.setLayoutParams(marginLayout);
        score1.setBackgroundColor(Color.GRAY);
        score1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Score = 1;
                // TODO Auto-generated method stub
                score1.setBackgroundColor(Color.GREEN);
                score0.setBackgroundColor(Color.GRAY);
                score6.setBackgroundColor(Color.GRAY);
                scoreN.setBackgroundColor(Color.GRAY);
            }
        });

        score6 = new Button(mContext);
        score6.setWidth(125);
        score6.setHeight(90);
        score6.setText("6");
        score6.setLayoutParams(marginLayout);
        score6.setBackgroundColor(Color.GRAY);
        score6.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Score = 6;
                // TODO Auto-generated method stub
                score6.setBackgroundColor(Color.GREEN);
                score1.setBackgroundColor(Color.GRAY);
                score0.setBackgroundColor(Color.GRAY);
                scoreN.setBackgroundColor(Color.GRAY);
            }
        });

        scoreN = new Button(mContext);
        scoreN.setWidth(125);
        scoreN.setHeight(90);
        scoreN.setLayoutParams(marginLayout);
        scoreN.setBackgroundColor(Color.GRAY);
        scoreN.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Score = 8;
                // TODO Auto-generated method stub
                scoreN.setBackgroundColor(Color.RED);
                score1.setBackgroundColor(Color.GRAY);
                score6.setBackgroundColor(Color.GRAY);
                score0.setBackgroundColor(Color.GRAY);
            }
        });
        mLayout.addView(score0);
        mLayout.addView(score1);
        mLayout.addView(score6);
        mLayout.addView(scoreN);

        addView(mLayout);
        return;
    }

}
