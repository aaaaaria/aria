package com.brainhealthtest.questions;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brainhealthtest.osshelper.Helper;


public class SingleIncidentalLearningView extends LinearLayout
{

    private String pair1, pair2, pair3;
    private TextView word1, word2, word3;
    public Button score1_0, score1_1, score1_2, score2_0, score2_1, score2_2, score3_0, score3_1, score3_2;
    public int score1 = -1, score2 = -1, score3 = -1, type;
    public Handler callbackHandler;

    public void Init(String mpair1, String mpair2, String mpair3, Helper helper)
    {
        // TODO Auto-generated method stub
        pair1 = mpair1;
        pair2 = mpair2;
        pair3 = mpair3;
        word1.setText(pair1);
        word2.setText(pair2);
        word3.setText(pair3);
    }

    public SingleIncidentalLearningView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
        Context mContext = context;
        LinearLayout mlayout = new LinearLayout(mContext);
        mlayout.setOrientation(LinearLayout.VERTICAL);//VERTICAL
        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        marginLayout.setMargins(2, 2, 2, 2);

        LinearLayout linearlayout1 = new LinearLayout(mContext);
        linearlayout1.setOrientation(LinearLayout.HORIZONTAL);//HORIZONTAL
        linearlayout1.setBackgroundColor(Color.BLACK);

        word1 = new TextView(mContext);
        word1.setWidth(130);
        word1.setHeight(70);
        word1.setTextSize(12);
        word1.setLayoutParams(marginLayout);
        word1.setBackgroundColor(Color.WHITE);
        linearlayout1.addView(word1);

        score1_0 = new Button(mContext);
        score1_0.setWidth(110);
        score1_0.setHeight(70);
        score1_0.setText("0");
        score1_0.setTextSize(15);
        score1_0.setLayoutParams(marginLayout);
        score1_0.setBackgroundColor(Color.GRAY);
        score1_0.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 1;
                score1 = 0;
                score1_0.setBackgroundColor(Color.GREEN);
                score1_1.setBackgroundColor(Color.GRAY);
                score1_2.setBackgroundColor(Color.GRAY);
            }
        });
        linearlayout1.addView(score1_0);

        score1_1 = new Button(mContext);
        score1_1.setWidth(110);
        score1_1.setHeight(70);
        score1_1.setText("1");
        score1_1.setTextSize(15);
        score1_1.setLayoutParams(marginLayout);
        score1_1.setBackgroundColor(Color.GRAY);
        score1_1.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 1;
                score1 = 1;
                score1_0.setBackgroundColor(Color.GRAY);
                score1_1.setBackgroundColor(Color.GREEN);
                score1_2.setBackgroundColor(Color.GRAY);
            }
        });
        linearlayout1.addView(score1_1);

        score1_2 = new Button(mContext);
        score1_2.setWidth(110);
        score1_2.setHeight(70);
        score1_2.setText("2");
        score1_2.setTextSize(15);
        score1_2.setLayoutParams(marginLayout);
        score1_2.setBackgroundColor(Color.GRAY);
        score1_2.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 1;
                score1 = 2;
                score1_0.setBackgroundColor(Color.GRAY);
                score1_1.setBackgroundColor(Color.GRAY);
                score1_2.setBackgroundColor(Color.GREEN);
            }
        });
        linearlayout1.addView(score1_2);
        mlayout.addView(linearlayout1);


        LinearLayout linearlayout2 = new LinearLayout(mContext);
        linearlayout2.setOrientation(LinearLayout.HORIZONTAL);//HORIZONTAL
        linearlayout2.setBackgroundColor(Color.BLACK);

        word2 = new TextView(mContext);
        word2.setWidth(130);
        word2.setHeight(70);
        word2.setTextSize(12);
        word2.setLayoutParams(marginLayout);
        word2.setBackgroundColor(Color.WHITE);
        linearlayout2.addView(word2);

        score2_0 = new Button(mContext);
        score2_0.setWidth(110);
        score2_0.setHeight(70);
        score2_0.setText("0");
        score2_0.setTextSize(15);
        score2_0.setLayoutParams(marginLayout);
        score2_0.setBackgroundColor(Color.GRAY);
        score2_0.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 2;
                score2 = 0;
                score2_0.setBackgroundColor(Color.GREEN);
                score2_1.setBackgroundColor(Color.GRAY);
                score2_2.setBackgroundColor(Color.GRAY);
            }
        });
        linearlayout2.addView(score2_0);

        score2_1 = new Button(mContext);
        score2_1.setWidth(110);
        score2_1.setHeight(70);
        score2_1.setText("1");
        score2_1.setTextSize(15);
        score2_1.setLayoutParams(marginLayout);
        score2_1.setBackgroundColor(Color.GRAY);
        score2_1.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 2;
                score2 = 1;
                score2_0.setBackgroundColor(Color.GRAY);
                score2_1.setBackgroundColor(Color.GREEN);
                score2_2.setBackgroundColor(Color.GRAY);
            }
        });
        linearlayout2.addView(score2_1);

        score2_2 = new Button(mContext);
        score2_2.setWidth(110);
        score2_2.setHeight(70);
        score2_2.setText("2");
        score2_2.setTextSize(15);
        score2_2.setLayoutParams(marginLayout);
        score2_2.setBackgroundColor(Color.GRAY);
        score2_2.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 2;
                score2 = 2;
                score2_0.setBackgroundColor(Color.GRAY);
                score2_1.setBackgroundColor(Color.GRAY);
                score2_2.setBackgroundColor(Color.GREEN);
            }
        });
        linearlayout2.addView(score2_2);
        mlayout.addView(linearlayout2);


        LinearLayout linearlayout3 = new LinearLayout(mContext);
        linearlayout3.setOrientation(LinearLayout.HORIZONTAL);//HORIZONTAL
        linearlayout3.setBackgroundColor(Color.BLACK);

        word3 = new TextView(mContext);
        word3.setWidth(130);
        word3.setHeight(70);
        word3.setTextSize(12);
        word3.setLayoutParams(marginLayout);
        word3.setBackgroundColor(Color.WHITE);
        linearlayout3.addView(word3);


        score3_0 = new Button(mContext);
        score3_0.setWidth(110);
        score3_0.setHeight(70);
        score3_0.setText("0");
        score3_0.setTextSize(15);
        score3_0.setLayoutParams(marginLayout);
        score3_0.setBackgroundColor(Color.GRAY);
        score3_0.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 3;
                score3 = 0;
                score3_0.setBackgroundColor(Color.GREEN);
                score3_1.setBackgroundColor(Color.GRAY);
                score3_2.setBackgroundColor(Color.GRAY);
            }
        });
        linearlayout3.addView(score3_0);

        score3_1 = new Button(mContext);
        score3_1.setWidth(110);
        score3_1.setHeight(70);
        score3_1.setText("1");
        score3_1.setTextSize(15);
        score3_1.setLayoutParams(marginLayout);
        score3_1.setBackgroundColor(Color.GRAY);
        score3_1.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 3;
                score3 = 1;
                score3_0.setBackgroundColor(Color.GRAY);
                score3_1.setBackgroundColor(Color.GREEN);
                score3_2.setBackgroundColor(Color.GRAY);
            }
        });
        linearlayout3.addView(score3_1);

        score3_2 = new Button(mContext);
        score3_2.setWidth(110);
        score3_2.setHeight(70);
        score3_2.setText("2");
        score3_2.setTextSize(15);
        score3_2.setLayoutParams(marginLayout);
        score3_2.setBackgroundColor(Color.GRAY);
        score3_2.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 3;
                score3 = 2;
                score3_0.setBackgroundColor(Color.GRAY);
                score3_1.setBackgroundColor(Color.GRAY);
                score3_2.setBackgroundColor(Color.GREEN);
            }
        });
        linearlayout3.addView(score3_2);

        mlayout.addView(linearlayout3);
        addView(mlayout);
        return;

    }

    public SingleIncidentalLearningView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        instantiate(context, null);
    }

    public SingleIncidentalLearningView(final Context context, final String imageUrl, Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        instantiate(context, imageUrl);
    }

    private void instantiate(final Context context, final String imageUrl)
    {
        Context mContext = context;
    }
}
