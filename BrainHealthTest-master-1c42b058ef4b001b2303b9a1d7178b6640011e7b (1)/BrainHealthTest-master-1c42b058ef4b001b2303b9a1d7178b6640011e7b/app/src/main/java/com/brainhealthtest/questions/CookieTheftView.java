package com.brainhealthtest.questions;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.brainhealthtest.osshelper.Helper;
import com.brainhealthtest.utility.CookieTheftQuestion;

import java.util.ArrayList;
import java.util.List;


public class CookieTheftView extends LinearLayout
{
    int totQuestions;
    public List<SingleCookieTheftView> ct1, ct2;
    Context mContext;
    Button recordBtn, stopBtn;
    TextView view1, view2, view3, view4, view4_1, view4_2, view5, view5_1, view5_2, view5_3;
    public EditText time, Observations;
    Button button1_0, button1_1, button1_2, button1_3, button2_0, button2_1, button2_2;
    public Handler callbackHandler;
    public int Type = -1, Syn = -1;

    public CookieTheftView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        mContext = context;
        //	instantiate(context, null);
    }

    public void Init(CookieTheftQuestion cookietheftQuestion, Helper helper)
    {
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        marginLayout.setMargins(2, 2, 2, 2);//setMargins(left, top, right, bottom)


        TextView textView = new TextView(mContext);
        textView = new TextView(mContext);
        textView.setText(cookietheftQuestion.guideWord1);
        mLayout.addView(textView);

        LinearLayout viewLayout_1 = new LinearLayout(mContext);
        viewLayout_1.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout_1.setBackgroundColor(Color.BLACK);
        viewLayout_1.setLayoutParams(marginLayout);
        LinearLayout viewLayout_2 = new LinearLayout(mContext);
        viewLayout_2.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout_2.setBackgroundColor(Color.BLACK);
        viewLayout_2.setLayoutParams(marginLayout);
        LinearLayout viewLayout_3 = new LinearLayout(mContext);
        viewLayout_3.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout_3.setBackgroundColor(Color.BLACK);
        viewLayout_3.setLayoutParams(marginLayout);
        LinearLayout viewLayout_4 = new LinearLayout(mContext);
        viewLayout_4.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout_4.setBackgroundColor(Color.BLACK);
        viewLayout_4.setLayoutParams(marginLayout);
        LinearLayout viewLayout_5 = new LinearLayout(mContext);
        viewLayout_5.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout_5.setBackgroundColor(Color.BLACK);
        viewLayout_5.setLayoutParams(marginLayout);

        view1 = new TextView(mContext);
        view1.setText("Time to completion:\n(from go ahead to finish) in minutes and seconds");
        view1.setWidth(750);
        view1.setHeight(80);
        view1.setTextSize(13);/////////////////////////
        view1.setBackgroundColor(Color.WHITE);
        view1.setLayoutParams(marginLayout);
        view1.setGravity(Gravity.CENTER);
        viewLayout_1.addView(view1);

        time = new EditText(mContext);
        time.setWidth(660);
        time.setHeight(80);
        time.setTextSize(13);/////////////////////////
        time.setBackgroundColor(Color.WHITE);
        time.setLayoutParams(marginLayout);
        time.setGravity(Gravity.CENTER);
        viewLayout_1.addView(time);
        mLayout.addView(viewLayout_1);

        view2 = new TextView(mContext);
        view2.setText("Type of writing: ");
        view2.setWidth(200);
        view2.setHeight(150);
        view2.setTextSize(13);/////////////////////////
        view2.setBackgroundColor(Color.WHITE);
        view2.setLayoutParams(marginLayout);
        view2.setGravity(Gravity.CENTER);
        viewLayout_2.addView(view2);

        button1_0 = new Button(mContext);
        button1_0.setText("0\n No relevant writing(If no relevant writing, leave rest of page blank) ");
        button1_0.setWidth(300);
        button1_0.setHeight(150);
        button1_0.setTextSize(11);/////////////////////////
        button1_0.setBackgroundColor(Color.GRAY);
        button1_0.setLayoutParams(marginLayout);
        button1_0.setGravity(Gravity.CENTER);
        button1_0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                Type = 0;
                button1_0.setBackgroundColor(Color.GREEN);
                button1_1.setBackgroundColor(Color.GRAY);
                button1_2.setBackgroundColor(Color.GRAY);
                button1_3.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_2.addView(button1_0);
        button1_1 = new Button(mContext);
        button1_1.setText("1\n Print ");
        button1_1.setWidth(300);
        button1_1.setHeight(150);
        button1_1.setTextSize(13);/////////////////////////
        button1_1.setBackgroundColor(Color.GRAY);
        button1_1.setLayoutParams(marginLayout);
        button1_1.setGravity(Gravity.CENTER);
        button1_1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                Type = 1;
                button1_1.setBackgroundColor(Color.GREEN);
                button1_0.setBackgroundColor(Color.GRAY);
                button1_3.setBackgroundColor(Color.GRAY);
                button1_2.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_2.addView(button1_1);
        button1_2 = new Button(mContext);
        button1_2.setText("2\n Cursive ");
        button1_2.setWidth(300);
        button1_2.setHeight(150);
        button1_2.setTextSize(13);/////////////////////////
        button1_2.setBackgroundColor(Color.GRAY);
        button1_2.setLayoutParams(marginLayout);
        button1_2.setGravity(Gravity.CENTER);
        button1_2.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                Type = 2;
                button1_2.setBackgroundColor(Color.GREEN);
                button1_1.setBackgroundColor(Color.GRAY);
                button1_0.setBackgroundColor(Color.GRAY);
                button1_3.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_2.addView(button1_2);
        button1_3 = new Button(mContext);
        button1_3.setText("3\n Combined print and cursive ");
        button1_3.setWidth(300);
        button1_3.setHeight(150);
        button1_3.setTextSize(13);/////////////////////////
        button1_3.setBackgroundColor(Color.GRAY);
        button1_3.setLayoutParams(marginLayout);
        button1_3.setGravity(Gravity.CENTER);
        button1_3.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                Type = 3;
                button1_3.setBackgroundColor(Color.GREEN);
                button1_0.setBackgroundColor(Color.GRAY);
                button1_1.setBackgroundColor(Color.GRAY);
                button1_2.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_2.addView(button1_3);
        mLayout.addView(viewLayout_2);

        view3 = new TextView(mContext);
        view3.setText("Syntactic complexity of writing:\nNOTE: Do NOT consider the omission of ��the�� in reference to a character as an incomplete sentence; e.g. ��Boy is reaching���� ");
        view3.setWidth(500);
        view3.setHeight(150);
        view3.setTextSize(13);/////////////////////////
        view3.setBackgroundColor(Color.WHITE);
        view3.setLayoutParams(marginLayout);
        view3.setGravity(Gravity.CENTER);
        viewLayout_3.addView(view3);

        button2_0 = new Button(mContext);
        button2_0.setText("0\nIncomplete sentences");
        button2_0.setWidth(300);
        button2_0.setHeight(150);
        button2_0.setTextSize(13);/////////////////////////
        button2_0.setBackgroundColor(Color.GRAY);
        button2_0.setLayoutParams(marginLayout);
        button2_0.setGravity(Gravity.CENTER);
        button2_0.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                Syn = 0;
                button2_0.setBackgroundColor(Color.GREEN);
                button2_1.setBackgroundColor(Color.GRAY);
                button2_2.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_3.addView(button2_0);
        button2_1 = new Button(mContext);
        button2_1.setText("1\n Syntactically simple sentences ");
        button2_1.setWidth(300);
        button2_1.setHeight(150);
        button2_1.setTextSize(13);/////////////////////////
        button2_1.setBackgroundColor(Color.GRAY);
        button2_1.setLayoutParams(marginLayout);
        button2_1.setGravity(Gravity.CENTER);
        button2_1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                Syn = 1;
                button2_1.setBackgroundColor(Color.GREEN);
                button2_0.setBackgroundColor(Color.GRAY);
                button2_2.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_3.addView(button2_1);
        button2_2 = new Button(mContext);
        button2_2.setText("2\n Syntactically complex sentences ");
        button2_2.setWidth(300);
        button2_2.setHeight(150);
        button2_2.setTextSize(13);/////////////////////////
        button2_2.setBackgroundColor(Color.GRAY);
        button2_2.setLayoutParams(marginLayout);
        button2_2.setGravity(Gravity.CENTER);
        button2_2.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                Syn = 2;
                button2_2.setBackgroundColor(Color.GREEN);
                button2_1.setBackgroundColor(Color.GRAY);
                button2_0.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_3.addView(button2_2);
        mLayout.addView(viewLayout_3);

        view4 = new TextView(mContext);
        view4.setText("Inclusion of major events");
        view4.setWidth(500);
        view4.setHeight(80);
        view4.setTextSize(13);/////////////////////////
        view4.setBackgroundColor(Color.WHITE);
        view4.setLayoutParams(marginLayout);
        view4.setGravity(Gravity.CENTER);
        viewLayout_4.addView(view4);
        view4_1 = new TextView(mContext);
        view4_1.setText("Absent");
        view4_1.setWidth(300);
        view4_1.setHeight(80);
        view4_1.setTextSize(13);/////////////////////////
        view4_1.setBackgroundColor(Color.WHITE);
        view4_1.setLayoutParams(marginLayout);
        view4_1.setGravity(Gravity.CENTER);
        viewLayout_4.addView(view4_1);
        view4_2 = new TextView(mContext);
        view4_2.setText("Present");
        view4_2.setWidth(300);
        view4_2.setHeight(80);
        view4_2.setTextSize(13);/////////////////////////
        view4_2.setBackgroundColor(Color.WHITE);
        view4_2.setLayoutParams(marginLayout);
        view4_2.setGravity(Gravity.CENTER);
        viewLayout_4.addView(view4_2);
        mLayout.addView(viewLayout_4);
        Log.d("cookie", String.valueOf("questions1=" + cookietheftQuestion.ques1.size()));
        Log.d("cookie", String.valueOf("questions1=" + cookietheftQuestion.ques2.size()));

        LinearLayout mLayout2 = new LinearLayout(mContext);
        mLayout2.setOrientation(LinearLayout.VERTICAL);
        mLayout2.setGravity(Gravity.CENTER_HORIZONTAL);
        mLayout2.setLayoutParams(marginLayout);
        mLayout2.setBackgroundColor(Color.BLACK);
        totQuestions = 0;
        ct1 = new ArrayList<SingleCookieTheftView>();
        for (int i = 0; i < cookietheftQuestion.ques1.size(); ++i)
        {
            SingleCookieTheftView singlect1 = new SingleCookieTheftView(mContext);
            singlect1.Init(cookietheftQuestion.ques1.get(i), 1, helper);
            mLayout2.addView(singlect1);
            ct1.add(singlect1);
            totQuestions++;
        }
        mLayout.addView(mLayout2);

        view5 = new TextView(mContext);
        view5.setText("Other notable findings");
        view5.setWidth(500);
        view5.setHeight(80);
        view5.setTextSize(13);/////////////////////////
        view5.setBackgroundColor(Color.WHITE);
        view5.setLayoutParams(marginLayout);
        view5.setGravity(Gravity.CENTER);
        viewLayout_5.addView(view5);
        view5_1 = new TextView(mContext);
        view5_1.setText("Absent");
        view5_1.setWidth(300);
        view5_1.setHeight(80);
        view5_1.setTextSize(13);/////////////////////////
        view5_1.setBackgroundColor(Color.WHITE);
        view5_1.setLayoutParams(marginLayout);
        view5_1.setGravity(Gravity.CENTER);
        viewLayout_5.addView(view5_1);
        view5_2 = new TextView(mContext);
        view5_2.setText("Present");
        view5_2.setWidth(300);
        view5_2.setHeight(80);
        view5_2.setTextSize(13);/////////////////////////
        view5_2.setBackgroundColor(Color.WHITE);
        view5_2.setLayoutParams(marginLayout);
        view5_2.setGravity(Gravity.CENTER);
        viewLayout_5.addView(view5_2);
        view5_3 = new TextView(mContext);
        view5_3.setText("N/A");
        view5_3.setWidth(300);
        view5_3.setHeight(80);
        view5_3.setTextSize(13);/////////////////////////
        view5_3.setBackgroundColor(Color.WHITE);
        view5_3.setLayoutParams(marginLayout);
        view5_3.setGravity(Gravity.CENTER);
        viewLayout_5.addView(view5_3);
        mLayout.addView(viewLayout_5);

        LinearLayout mLayout3 = new LinearLayout(mContext);
        mLayout3.setOrientation(LinearLayout.VERTICAL);
        mLayout3.setGravity(Gravity.CENTER_HORIZONTAL);
        mLayout3.setLayoutParams(marginLayout);
        mLayout3.setBackgroundColor(Color.BLACK);
        totQuestions = 0;
        ct2 = new ArrayList<SingleCookieTheftView>();
        for (int i = 0; i < cookietheftQuestion.ques2.size(); ++i)
        {
            SingleCookieTheftView singlect2 = new SingleCookieTheftView(mContext);
            singlect2.Init(cookietheftQuestion.ques2.get(i), 2, helper);
            mLayout3.addView(singlect2);
            ct2.add(singlect2);
            totQuestions++;
        }
        mLayout.addView(mLayout3);


        addView(mLayout);
        return;

    }

    public CookieTheftView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public CookieTheftView(final Context context, final CookieTheftQuestion cookietheftQuestion, Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        mContext = context;
        instantiate(context, cookietheftQuestion);
    }

    private void instantiate(final Context context, final CookieTheftQuestion boston_naming)
    {
        CookieTheftQuestion cookietheftQuestion = new CookieTheftQuestion();
        cookietheftQuestion.ques1 = new ArrayList<String>();
        cookietheftQuestion.ques1.add("test");
        // next line: for preview
        //Init(trailerQuestion, null);
        return;
    }


}
