package com.brainhealthtest.questions;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.brainhealthtest.osshelper.Helper;


public class SingleReadingTestView extends LinearLayout
{
    private String pair1, pair2;
    public int Score = -1, Type = -1;
    private EditText pair;
    public Button score0, score1, score2, score3, score4, score6, scoreN;
    public Handler callbackHandler;

    public void Init(String mpair1, String mpair2, String number, Helper helper)
    {
        pair1 = mpair1;
        pair2 = mpair2;
        if (number == "10")
            scoreN.setText("8");
        else
        {
            scoreN.setEnabled(false);
            scoreN.setText("n/a");
            scoreN.setBackgroundColor(Color.GRAY);
        }
        pair.setText(pair1 + "\n" + pair2);
    }

    public SingleReadingTestView(Context context)
    {
        super(context);
        Context mContext = context;
        // TODO Auto-generated constructor stub
//		 Type = -1;
//		 Score =-1;
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        marginLayout.setMargins(2, 2, 2, 2);

        pair = new EditText(context);
        //pair.setText("North\nSouth\n");
        pair.setWidth(500);
        pair.setHeight(90);
        pair.setEnabled(false);
        pair.setTextSize(13);
        pair.setTextColor(Color.BLACK);
        pair.setLayoutParams(marginLayout);
        //pair.setTextColor(Color.WHITE);
        pair.setBackgroundColor(Color.WHITE);

        score0 = new Button(mContext);
        score0.setWidth(150);
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
        score1.setWidth(150);
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
                score2.setBackgroundColor(Color.GRAY);
                score3.setBackgroundColor(Color.GRAY);
                score4.setBackgroundColor(Color.GRAY);
                score6.setBackgroundColor(Color.GRAY);
                scoreN.setBackgroundColor(Color.GRAY);
            }
        });
        score2 = new Button(mContext);
        score2.setWidth(99);
        score2.setHeight(90);
        score2.setText("2");
        score2.setLayoutParams(marginLayout);
        score2.setBackgroundColor(Color.GRAY);
        score2.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Type = 2;
                Score = 0;
                // TODO Auto-generated method stub
                score1.setBackgroundColor(Color.GRAY);
                score2.setBackgroundColor(Color.YELLOW);
                score0.setBackgroundColor(Color.GREEN);
                score3.setBackgroundColor(Color.GRAY);
                score4.setBackgroundColor(Color.GRAY);
                score6.setBackgroundColor(Color.GRAY);
                scoreN.setBackgroundColor(Color.GRAY);
            }
        });
        score3 = new Button(mContext);
        score3.setWidth(99);
        score3.setHeight(90);
        score3.setText("3");
        score3.setLayoutParams(marginLayout);
        score3.setBackgroundColor(Color.GRAY);
        score3.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Type = 3;
                Score = 0;
                // TODO Auto-generated method stub
                score3.setBackgroundColor(Color.YELLOW);
                score1.setBackgroundColor(Color.GRAY);
                score2.setBackgroundColor(Color.GRAY);
                score0.setBackgroundColor(Color.GREEN);
                score4.setBackgroundColor(Color.GRAY);
                score6.setBackgroundColor(Color.GRAY);
                scoreN.setBackgroundColor(Color.GRAY);
            }
        });
        score4 = new Button(mContext);
        score4.setWidth(98);
        score4.setHeight(90);
        score4.setText("4");
        score4.setLayoutParams(marginLayout);
        score4.setBackgroundColor(Color.GRAY);
        score4.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Type = 4;
                Score = 0;
                // TODO Auto-generated method stub
                score4.setBackgroundColor(Color.YELLOW);
                score1.setBackgroundColor(Color.GRAY);
                score2.setBackgroundColor(Color.GRAY);
                score3.setBackgroundColor(Color.GRAY);
                score0.setBackgroundColor(Color.GREEN);
                score6.setBackgroundColor(Color.GRAY);
                scoreN.setBackgroundColor(Color.GRAY);
            }
        });

        score6 = new Button(mContext);
        score6.setWidth(100);
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
                score2.setBackgroundColor(Color.GRAY);
                score3.setBackgroundColor(Color.GRAY);
                score4.setBackgroundColor(Color.GRAY);
                scoreN.setBackgroundColor(Color.GRAY);
            }
        });

        scoreN = new Button(mContext);
        scoreN.setWidth(100);
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
                score2.setBackgroundColor(Color.GRAY);
                score3.setBackgroundColor(Color.GRAY);
                score4.setBackgroundColor(Color.GRAY);
                score6.setBackgroundColor(Color.GRAY);
                score0.setBackgroundColor(Color.GRAY);
            }
        });
        mLayout.addView(pair);
        mLayout.addView(score1);
        mLayout.addView(score0);
        mLayout.addView(score2);
        mLayout.addView(score3);
        mLayout.addView(score4);
        mLayout.addView(score6);
        mLayout.addView(scoreN);
        addView(mLayout);
        return;


    }

    public SingleReadingTestView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        instantiate(context, null);
    }

    public SingleReadingTestView(final Context context, final String imageUrl, Handler handler)
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
