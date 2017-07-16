package com.brainhealthtest.questions;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brainhealthtest.osshelper.Helper;


public class SingleBostonNamingView extends LinearLayout
{
    private String pair1, pair2, pair3;
    private TextView word1, word2, word3, error_type, timetxt;
    public Button score1_0, score1_1, score1_2, score2_0, score2_1, score3_0, score3_1;
    public boolean type1_1;
    public Button type1, type2, type3, type4, type5, type6;
    public int t1 = 0, t2 = 0, t3 = 0, t4 = 0, t5 = 0, t6 = 0;
    public EditText Answer1, Answer2, Answer3, time;
    public Handler callbackHandler;
    public int type, Score;

    public void Init(String mpair1, String mpair2, String mpair3, Helper helper)
    {
        pair1 = mpair1;
        pair2 = mpair2;
        pair3 = mpair3;
        word1.setText(pair1);
        word2.setText(pair2);
        word3.setText(pair3);
    }

    public SingleBostonNamingView(Context context)
    {
        super(context);
        Context mContext = context;
        // TODO Auto-generated constructor stub
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        marginLayout.setMargins(2, 2, 2, 2);


        LinearLayout linearLayout_1 = new LinearLayout(mContext);
        linearLayout_1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout_1.setBackgroundColor(Color.BLACK);

        LinearLayout linearLayout_2 = new LinearLayout(mContext);
        linearLayout_2.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout_2.setBackgroundColor(Color.BLACK);

        LinearLayout linearLayout_3 = new LinearLayout(mContext);
        linearLayout_3.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout_3.setBackgroundColor(Color.BLACK);

        word1 = new TextView(mContext);
        word1.setWidth(260);
        word1.setHeight(60);
        word1.setTextSize(15);
        word1.setLayoutParams(marginLayout);
        word1.setBackgroundColor(Color.WHITE);
        linearLayout_1.addView(word1);

        score1_0 = new Button(mContext);
        score1_0.setWidth(60);
        score1_0.setHeight(60);
        score1_0.setText("0");
        score1_0.setLayoutParams(marginLayout);
        score1_0.setBackgroundColor(Color.GRAY);
        score1_0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                type = 1;
                Score = 0;
                // TODO Auto-generated method stub
                score1_0.setBackgroundColor(Color.GREEN);
                score1_1.setBackgroundColor(Color.GRAY);
                score1_2.setBackgroundColor(Color.GRAY);
                score2_1.setBackgroundColor(Color.GRAY);
                score2_0.setBackgroundColor(Color.GRAY);
                score3_1.setBackgroundColor(Color.GRAY);
                score3_0.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout_1.addView(score1_0);

        score1_1 = new Button(mContext);
        score1_1.setWidth(60);
        score1_1.setHeight(60);
        score1_1.setText("1");
        score1_1.setLayoutParams(marginLayout);
        score1_1.setBackgroundColor(Color.GRAY);
        score1_1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                type = 1;
                Score = 1;
                // TODO Auto-generated method stub
                score1_0.setBackgroundColor(Color.GRAY);
                score1_1.setBackgroundColor(Color.GREEN);
                score1_2.setBackgroundColor(Color.GRAY);
                score2_1.setBackgroundColor(Color.GRAY);
                score2_0.setBackgroundColor(Color.GRAY);
                score3_1.setBackgroundColor(Color.GRAY);
                score3_0.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout_1.addView(score1_1);

        score1_2 = new Button(mContext);
        score1_2.setWidth(60);
        score1_2.setHeight(60);
        score1_2.setText("2");
        score1_2.setLayoutParams(marginLayout);
        score1_2.setBackgroundColor(Color.GRAY);
        score1_2.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                type = 1;
                Score = 2;
                // TODO Auto-generated method stub
                score1_0.setBackgroundColor(Color.GRAY);
                score1_1.setBackgroundColor(Color.GRAY);
                score1_2.setBackgroundColor(Color.GREEN);
                score2_1.setBackgroundColor(Color.GRAY);
                score2_0.setBackgroundColor(Color.GRAY);
                score3_1.setBackgroundColor(Color.GRAY);
                score3_0.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout_1.addView(score1_2);

        word2 = new TextView(mContext);
        word2.setWidth(323);
        word2.setHeight(60);
        word2.setTextSize(15);
        word2.setLayoutParams(marginLayout);
        word2.setBackgroundColor(Color.WHITE);
        linearLayout_1.addView(word2);

        score2_0 = new Button(mContext);
        score2_0.setWidth(60);
        score2_0.setHeight(60);
        score2_0.setText("0");
        score2_0.setLayoutParams(marginLayout);
        score2_0.setBackgroundColor(Color.GRAY);
        score2_0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                type = 2;
                Score = 0;
                // TODO Auto-generated method stub
                score2_0.setBackgroundColor(Color.GREEN);
                score2_1.setBackgroundColor(Color.GRAY);
                score1_0.setBackgroundColor(Color.GRAY);
                score1_1.setBackgroundColor(Color.GRAY);
                score1_2.setBackgroundColor(Color.GRAY);
                score3_0.setBackgroundColor(Color.GRAY);
                score3_1.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout_1.addView(score2_0);
        score2_1 = new Button(mContext);
        score2_1.setWidth(60);
        score2_1.setHeight(60);
        score2_1.setText("1");
        score2_1.setLayoutParams(marginLayout);
        score2_1.setBackgroundColor(Color.GRAY);
        score2_1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                type = 2;
                Score = 1;
                // TODO Auto-generated method stub
                score2_0.setBackgroundColor(Color.GRAY);
                score2_1.setBackgroundColor(Color.GREEN);
                score1_0.setBackgroundColor(Color.GRAY);
                score1_1.setBackgroundColor(Color.GRAY);
                score1_2.setBackgroundColor(Color.GRAY);
                score3_0.setBackgroundColor(Color.GRAY);
                score3_1.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout_1.addView(score2_1);

        word3 = new TextView(mContext);
        word3.setWidth(327);
        word3.setHeight(60);
        word3.setTextSize(15);
        word3.setLayoutParams(marginLayout);
        word3.setBackgroundColor(Color.WHITE);
        linearLayout_1.addView(word3);
        score3_0 = new Button(mContext);
        score3_0.setWidth(60);
        score3_0.setHeight(60);
        score3_0.setText("0");
        score3_0.setLayoutParams(marginLayout);
        score3_0.setBackgroundColor(Color.GRAY);
        score3_0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                type = 3;
                Score = 0;
                // TODO Auto-generated method stub
                score3_0.setBackgroundColor(Color.GREEN);
                score3_1.setBackgroundColor(Color.GRAY);
                score1_0.setBackgroundColor(Color.GRAY);
                score1_1.setBackgroundColor(Color.GRAY);
                score1_2.setBackgroundColor(Color.GRAY);
                score2_0.setBackgroundColor(Color.GRAY);
                score2_1.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout_1.addView(score3_0);
        score3_1 = new Button(mContext);
        score3_1.setWidth(60);
        score3_1.setHeight(60);
        score3_1.setText("1");
        score3_1.setLayoutParams(marginLayout);
        score3_1.setBackgroundColor(Color.GRAY);
        score3_1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                type = 3;
                Score = 1;
                // TODO Auto-generated method stub
                score3_0.setBackgroundColor(Color.GRAY);
                score3_1.setBackgroundColor(Color.GREEN);
                score1_0.setBackgroundColor(Color.GRAY);
                score1_1.setBackgroundColor(Color.GRAY);
                score1_2.setBackgroundColor(Color.GRAY);
                score2_0.setBackgroundColor(Color.GRAY);
                score2_1.setBackgroundColor(Color.GRAY);
            }
        });
        linearLayout_1.addView(score3_1);
        mLayout.addView(linearLayout_1);

        Answer1 = new EditText(context);
        Answer1.setWidth(450);
        Answer1.setHeight(200);
        Answer1.setTextSize(13);
        Answer1.setLayoutParams(marginLayout);
        Answer1.setBackgroundColor(Color.WHITE);
        linearLayout_2.addView(Answer1);
        Answer2 = new EditText(context);
        Answer2.setWidth(450);
        Answer2.setHeight(200);
        Answer2.setTextSize(13);
        Answer2.setLayoutParams(marginLayout);
        Answer2.setBackgroundColor(Color.WHITE);
        linearLayout_2.addView(Answer2);
        Answer3 = new EditText(context);
        Answer3.setWidth(450);
        Answer3.setHeight(200);
        Answer3.setTextSize(13);
        Answer3.setLayoutParams(marginLayout);
        Answer3.setBackgroundColor(Color.WHITE);
        linearLayout_2.addView(Answer3);
        mLayout.addView(linearLayout_2);

        error_type = new TextView(mContext);
        error_type.setText("Errors: \nCircle all that apply");
        error_type.setWidth(122);
        error_type.setHeight(80);
        error_type.setTextSize(12);
        error_type.setLayoutParams(marginLayout);
        error_type.setBackgroundColor(Color.WHITE);
        linearLayout_3.addView(error_type);
        type1 = new Button(mContext);
        type1.setWidth(100);
        type1.setHeight(80);
        type1.setTextSize(11);
        type1.setText("No error");
        type1.setLayoutParams(marginLayout);
        type1.setBackgroundColor(Color.GRAY);
        type1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                if (t1 == 0)
                {
                    type1.setBackgroundColor(Color.GREEN);
                    t1 = 1;
                } else
                {
                    type1.setBackgroundColor(Color.GRAY);
                    t1 = 0;
                }
                int type_1 = 1;
            }
        });
        linearLayout_3.addView(type1);
        type2 = new Button(mContext);
        type2.setWidth(150);
        type2.setHeight(80);
        type2.setTextSize(11);
        type2.setText("Circumloc");
        type2.setLayoutParams(marginLayout);
        type2.setBackgroundColor(Color.GRAY);
        type2.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                if (t2 == 0)
                {
                    type2.setBackgroundColor(Color.GREEN);
                    t2 = 1;
                } else
                {
                    type2.setBackgroundColor(Color.GRAY);
                    t2 = 0;
                }
            }
        });
        linearLayout_3.addView(type2);
        type3 = new Button(mContext);
        type3.setWidth(180);
        type3.setHeight(80);
        type3.setTextSize(11);
        type3.setText("Perseveration");
        type3.setLayoutParams(marginLayout);
        type3.setBackgroundColor(Color.GRAY);
        type3.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                if (t3 == 0)
                {
                    type3.setBackgroundColor(Color.GREEN);
                    t3 = 1;
                } else
                {
                    type3.setBackgroundColor(Color.GRAY);
                    t3 = 0;
                }
            }
        });
        linearLayout_3.addView(type3);
        type4 = new Button(mContext);
        type4.setWidth(170);
        type4.setHeight(80);
        type4.setTextSize(11);
        type4.setText("Semantic Par.");
        type4.setLayoutParams(marginLayout);
        type4.setBackgroundColor(Color.GRAY);
        type4.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                if (t4 == 0)
                {
                    type4.setBackgroundColor(Color.GREEN);
                    t4 = 1;
                } else
                {
                    type4.setBackgroundColor(Color.GRAY);
                    t4 = 0;
                }
            }
        });
        linearLayout_3.addView(type4);
        type5 = new Button(mContext);
        type5.setWidth(170);
        type5.setHeight(80);
        type5.setTextSize(11);
        type5.setText("Phonemic Par.");
        type5.setLayoutParams(marginLayout);
        type5.setBackgroundColor(Color.GRAY);
        type5.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                if (t5 == 0)
                {
                    type5.setBackgroundColor(Color.GREEN);
                    t5 = 1;
                } else
                {
                    type5.setBackgroundColor(Color.GRAY);
                    t5 = 0;
                }
            }
        });
        linearLayout_3.addView(type5);
        type6 = new Button(mContext);
        type6.setWidth(170);
        type6.setHeight(80);
        type6.setTextSize(11);
        type6.setText("Perceptual");
        type6.setLayoutParams(marginLayout);
        type6.setBackgroundColor(Color.GRAY);
        type6.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                if (t6 == 0)
                {
                    type6.setBackgroundColor(Color.GREEN);
                    t6 = 1;
                } else
                {
                    type6.setBackgroundColor(Color.GRAY);
                    t6 = 0;
                }
            }
        });
        linearLayout_3.addView(type6);
        timetxt = new TextView(mContext);
        timetxt.setText("TIME\n(seconds)");
        timetxt.setWidth(113);
        timetxt.setHeight(90);
        timetxt.setTextSize(12);
        timetxt.setGravity(Gravity.CENTER);
        timetxt.setLayoutParams(marginLayout);
        timetxt.setBackgroundColor(Color.WHITE);
        linearLayout_3.addView(timetxt);
        time = new EditText(mContext);
        time.setWidth(150);
        time.setHeight(80);
        time.setTextSize(16);
        time.setLayoutParams(marginLayout);
        time.setBackgroundColor(Color.WHITE);
        linearLayout_3.addView(time);

        mLayout.addView(linearLayout_3);
        addView(mLayout);
        return;
    }

    public SingleBostonNamingView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        instantiate(context, null);
    }

    public SingleBostonNamingView(final Context context, final String imageUrl, Handler handler)
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
