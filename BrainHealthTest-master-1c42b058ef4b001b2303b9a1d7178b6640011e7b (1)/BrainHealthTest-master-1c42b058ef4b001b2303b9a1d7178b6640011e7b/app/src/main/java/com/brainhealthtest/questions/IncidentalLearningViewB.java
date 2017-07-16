package com.brainhealthtest.questions;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.brainhealthtest.R;
import com.brainhealthtest.osshelper.Helper;
import com.brainhealthtest.utility.IncidentalLearningQuestion;

import java.util.List;


public class IncidentalLearningViewB extends LinearLayout
{

    Context mContext;
    Button score1_1_0, score1_1_1, score1_2_0, score1_2_1, score1_3_0, score1_3_1,
            score2_1_0, score2_1_1, score2_2_0, score2_2_1, score2_3_0, score2_3_1,
            score3_1_0, score3_1_1, score3_2_0, score3_2_1, score3_3_0, score3_3_1,
            btn4_1, btn4_2, btn4_3, btn4_4, btn4_5, btn4_6, btn4_7;
    TextView view1, view1_1, view1_2, view1_3, view2_1, view2_2, view2_3, view3_1, view3_2, view3_3,
            view2_01, view2_02, view2_03, view2_04,
            view3, view4;
    public EditText txt2_1, txt2_2, txt2_3, txt2_4;
    public int score1_1 = -1, score1_2 = -1, score1_3 = -1, score2_1 = -1, score2_2 = -1, score2_3 = -1, score3_1 = -1, score3_2 = -1, score3_3 = -1;
    int type;
    int totQuestions;
    public int errortype;
    public Bitmap imgBloodBg;
    public Handler callbackHandler;
    public List<SingleIncidentalLearningView> il_1;


    public IncidentalLearningViewB(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        mContext = context;
        //	instantiate(context, null);
    }


    public IncidentalLearningViewB(final Context context, final IncidentalLearningQuestion incidentallearningQuestion, Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        mContext = context;
        instantiate(context, incidentallearningQuestion);
    }

    public void Init(IncidentalLearningQuestion incidental_2Question, Helper helper)
    {


        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        //  mLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        marginLayout.setMargins(2, 2, 2, 2);//setMargins(left, top, right, bottom) 

        TextView textView = new TextView(mContext);
        textView.setTextSize(18);
        textView.setGravity(Gravity.CENTER);
        textView.setText(incidental_2Question.title);
        mLayout.addView(textView);//title

        LinearLayout viewLayout1 = new LinearLayout(mContext);
        viewLayout1.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout1.setBackgroundColor(Color.BLACK);
        viewLayout1.setLayoutParams(marginLayout);
        //layout1
        view1 = new TextView(mContext);
        view1.setText("Presence/Absence of symbols associated with each numeral (0 = missing; 1 = present)");
        view1.setWidth(1420);
        view1.setHeight(100);
        view1.setTextSize(15);/////////////////////////
        view1.setBackgroundColor(Color.LTGRAY);
        view1.setLayoutParams(marginLayout);
        view1.setGravity(Gravity.CENTER);
        viewLayout1.addView(view1);
        mLayout.addView(viewLayout1);

        LinearLayout mLayout1 = new LinearLayout(mContext);
        mLayout1.setOrientation(LinearLayout.VERTICAL);
        mLayout1.setGravity(Gravity.CENTER_HORIZONTAL);

        LinearLayout viewLayout_1 = new LinearLayout(mContext);
        viewLayout_1.setOrientation(LinearLayout.HORIZONTAL);//
        viewLayout_1.setBackgroundColor(Color.BLACK);
        //viewLayout_1.setLayoutParams(marginLayout);

        view1_1 = new TextView(mContext);
        view1_1.setText(" ");
        view1_1.setWidth(170);
        view1_1.setHeight(100);
        view1_1.setTextSize(15);
        view1_1.setBackgroundResource(R.drawable.il1_1);
//       view1_1.setBackgroundColor(Color.LTGRAY);
        view1_1.setLayoutParams(marginLayout);
        view1_1.setGravity(Gravity.CENTER);
        viewLayout_1.addView(view1_1);

        score1_1_0 = new Button(mContext);
        score1_1_0.setWidth(150);
        score1_1_0.setHeight(100);
        score1_1_0.setText("0\n Absent");
        score1_1_0.setTextSize(13);
        score1_1_0.setLayoutParams(marginLayout);
        score1_1_0.setBackgroundColor(Color.GRAY);
        score1_1_0.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 1;
                score1_1 = 0;
                score1_1_0.setBackgroundColor(Color.GREEN);
                score1_1_1.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_1.addView(score1_1_0);

        score1_1_1 = new Button(mContext);
        score1_1_1.setWidth(150);
        score1_1_1.setHeight(100);
        score1_1_1.setText("1\n Present");
        score1_1_1.setTextSize(13);
        score1_1_1.setLayoutParams(marginLayout);
        score1_1_1.setBackgroundColor(Color.GRAY);
        score1_1_1.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 1;
                score1_1 = 1;
                score1_1_0.setBackgroundColor(Color.GRAY);
                score1_1_1.setBackgroundColor(Color.GREEN);
            }
        });
        viewLayout_1.addView(score1_1_1);

        view1_2 = new TextView(mContext);
        view1_2.setText(" ");
        view1_2.setWidth(170);
        view1_2.setHeight(100);
        view1_2.setTextSize(13);/////////////////////////
        view1_2.setBackgroundResource(R.drawable.il2_1);
        //view1_2.setBackgroundColor(Color.LTGRAY);
        view1_2.setLayoutParams(marginLayout);
        view1_2.setGravity(Gravity.CENTER);
        viewLayout_1.addView(view1_2);

        score1_2_0 = new Button(mContext);
        score1_2_0.setWidth(150);
        score1_2_0.setHeight(100);
        score1_2_0.setText("0\n Absent");
        score1_2_0.setTextSize(13);
        score1_2_0.setLayoutParams(marginLayout);
        score1_2_0.setBackgroundColor(Color.GRAY);
        score1_2_0.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 2;
                score1_2 = 0;
                score1_2_0.setBackgroundColor(Color.GREEN);
                score1_2_1.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_1.addView(score1_2_0);

        score1_2_1 = new Button(mContext);
        score1_2_1.setWidth(150);
        score1_2_1.setHeight(100);
        score1_2_1.setText("1\n Present");
        score1_2_1.setTextSize(13);
        score1_2_1.setLayoutParams(marginLayout);
        score1_2_1.setBackgroundColor(Color.GRAY);
        score1_2_1.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 2;
                score1_2 = 1;
                score1_2_0.setBackgroundColor(Color.GRAY);
                score1_2_1.setBackgroundColor(Color.GREEN);
            }
        });
        viewLayout_1.addView(score1_2_1);

        view1_3 = new TextView(mContext);
        view1_3.setText(" ");
        view1_3.setWidth(170);
        view1_3.setHeight(100);
        view1_3.setTextSize(15);/////////////////////////
        //view1_3.setBackground(getResources().getDrawable(R.drawable.t1_1));
        view1_3.setBackgroundResource(R.drawable.il3_1);
//       view1_3.setBackgroundColor(Color.LTGRAY);
        view1_3.setLayoutParams(marginLayout);
        view1_3.setGravity(Gravity.CENTER);
        viewLayout_1.addView(view1_3);

        score1_3_0 = new Button(mContext);
        score1_3_0.setWidth(150);
        score1_3_0.setHeight(100);
        score1_3_0.setText("0\n Absent");
        score1_3_0.setTextSize(13);
        score1_3_0.setLayoutParams(marginLayout);
        score1_3_0.setBackgroundColor(Color.GRAY);
        score1_3_0.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 3;
                score1_3 = 0;
                score1_3_0.setBackgroundColor(Color.GREEN);
                score1_3_1.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_1.addView(score1_3_0);

        score1_3_1 = new Button(mContext);
        score1_3_1.setWidth(150);
        score1_3_1.setHeight(100);
        score1_3_1.setText("1\n Present");
        score1_3_1.setTextSize(13);
        score1_3_1.setLayoutParams(marginLayout);
        score1_3_1.setBackgroundColor(Color.GRAY);
        score1_3_1.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 3;
                score1_3 = 1;
                score1_3_0.setBackgroundColor(Color.GRAY);
                score1_3_1.setBackgroundColor(Color.GREEN);
            }
        });
        viewLayout_1.addView(score1_3_1);

        ///viewLayout_2
        LinearLayout viewLayout_2 = new LinearLayout(mContext);
        viewLayout_2.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout_2.setBackgroundColor(Color.BLACK);
        //viewLayout_2.setLayoutParams(marginLayout);

        view2_1 = new TextView(mContext);
        view2_1.setText(" ");
        view2_1.setWidth(170);
        view2_1.setHeight(100);
        view2_1.setTextSize(15);/////////////////////////
        //view2_1.setBackground(getResources().getDrawable(R.drawable.t1_1));
        view2_1.setBackgroundResource(R.drawable.il1_2);
//       view2_1.setBackgroundColor(Color.LTGRAY);
        view2_1.setLayoutParams(marginLayout);
        view2_1.setGravity(Gravity.CENTER);
        viewLayout_2.addView(view2_1);

        score2_1_0 = new Button(mContext);
        score2_1_0.setWidth(150);
        score2_1_0.setHeight(100);
        score2_1_0.setText("0\n Absent");
        score2_1_0.setTextSize(13);
        score2_1_0.setLayoutParams(marginLayout);
        score2_1_0.setBackgroundColor(Color.GRAY);
        score2_1_0.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 1;
                score2_1 = 0;
                score2_1_0.setBackgroundColor(Color.GREEN);
                score2_1_1.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_2.addView(score2_1_0);

        score2_1_1 = new Button(mContext);
        score2_1_1.setWidth(150);
        score2_1_1.setHeight(100);
        score2_1_1.setText("1\n Present");
        score2_1_1.setTextSize(13);
        score2_1_1.setLayoutParams(marginLayout);
        score2_1_1.setBackgroundColor(Color.GRAY);
        score2_1_1.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 1;
                score2_1 = 1;
                score2_1_0.setBackgroundColor(Color.GRAY);
                score2_1_1.setBackgroundColor(Color.GREEN);
            }
        });
        viewLayout_2.addView(score2_1_1);

        view2_2 = new TextView(mContext);
        view2_2.setText(" ");
        view2_2.setWidth(170);
        view2_2.setHeight(100);
        view2_2.setTextSize(13);/////////////////////////
        //view2_2.setBackground(getResources().getDrawable(R.drawable.t1_1));
        view2_2.setBackgroundResource(R.drawable.il2_2);
//       view2_2.setBackgroundColor(Color.LTGRAY);
        view2_2.setLayoutParams(marginLayout);
        view2_2.setGravity(Gravity.CENTER);
        viewLayout_2.addView(view2_2);

        score2_2_0 = new Button(mContext);
        score2_2_0.setWidth(150);
        score2_2_0.setHeight(100);
        score2_2_0.setText("0\n Absent");
        score2_2_0.setTextSize(13);
        score2_2_0.setLayoutParams(marginLayout);
        score2_2_0.setBackgroundColor(Color.GRAY);
        score2_2_0.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 2;
                score2_2 = 0;
                score2_2_0.setBackgroundColor(Color.GREEN);
                score2_2_1.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_2.addView(score2_2_0);

        score2_2_1 = new Button(mContext);
        score2_2_1.setWidth(150);
        score2_2_1.setHeight(100);
        score2_2_1.setText("1\n Present");
        score2_2_1.setTextSize(13);
        score2_2_1.setLayoutParams(marginLayout);
        score2_2_1.setBackgroundColor(Color.GRAY);
        score2_2_1.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 2;
                score2_2 = 1;
                score2_2_0.setBackgroundColor(Color.GRAY);
                score2_2_1.setBackgroundColor(Color.GREEN);
            }
        });
        viewLayout_2.addView(score2_2_1);

        view2_3 = new TextView(mContext);
        view2_3.setText(" ");
        view2_3.setWidth(170);
        view2_3.setHeight(100);
        view2_3.setTextSize(15);/////////////////////////
        //view2_3.setBackground(getResources().getDrawable(R.drawable.t1_1));
        view2_3.setBackgroundResource(R.drawable.il3_2);
//       view2_3.setBackgroundColor(Color.LTGRAY);
        view2_3.setLayoutParams(marginLayout);
        view2_3.setGravity(Gravity.CENTER);
        viewLayout_2.addView(view2_3);

        score2_3_0 = new Button(mContext);
        score2_3_0.setWidth(150);
        score2_3_0.setHeight(100);
        score2_3_0.setText("0\n Absent");
        score2_3_0.setTextSize(13);
        score2_3_0.setLayoutParams(marginLayout);
        score2_3_0.setBackgroundColor(Color.GRAY);
        score2_3_0.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 3;
                score2_3 = 0;
                score2_3_0.setBackgroundColor(Color.GREEN);
                score2_3_1.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_2.addView(score2_3_0);

        score2_3_1 = new Button(mContext);
        score2_3_1.setWidth(150);
        score2_3_1.setHeight(100);
        score2_3_1.setText("1\n Present");
        score2_3_1.setTextSize(13);
        score2_3_1.setLayoutParams(marginLayout);
        score2_3_1.setBackgroundColor(Color.GRAY);
        score2_3_1.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 3;
                score2_3 = 1;
                score2_3_0.setBackgroundColor(Color.GRAY);
                score2_3_1.setBackgroundColor(Color.GREEN);
            }
        });
        viewLayout_2.addView(score2_3_1);

        ///viewLayout_3
        LinearLayout viewLayout_3 = new LinearLayout(mContext);
        viewLayout_3.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout_3.setBackgroundColor(Color.BLACK);
        //viewLayout_3.setLayoutParams(marginLayout);

        view3_1 = new TextView(mContext);
        view3_1.setText(" ");
        view3_1.setWidth(170);
        view3_1.setHeight(100);
        view3_1.setTextSize(15);/////////////////////////
        view3_1.setBackgroundResource(R.drawable.il1_3);
//       view3_1.setBackgroundColor(Color.LTGRAY);
        view3_1.setLayoutParams(marginLayout);
        view3_1.setGravity(Gravity.CENTER);
        viewLayout_3.addView(view3_1);

        score3_1_0 = new Button(mContext);
        score3_1_0.setWidth(150);
        score3_1_0.setHeight(100);
        score3_1_0.setText("0\n Absent");
        score3_1_0.setTextSize(13);
        score3_1_0.setLayoutParams(marginLayout);
        score3_1_0.setBackgroundColor(Color.GRAY);
        score3_1_0.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 1;
                score3_1 = 0;
                score3_1_0.setBackgroundColor(Color.GREEN);
                score3_1_1.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_3.addView(score3_1_0);

        score3_1_1 = new Button(mContext);
        score3_1_1.setWidth(150);
        score3_1_1.setHeight(100);
        score3_1_1.setText("1\n Present");
        score3_1_1.setTextSize(13);
        score3_1_1.setLayoutParams(marginLayout);
        score3_1_1.setBackgroundColor(Color.GRAY);
        score3_1_1.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 1;
                score3_1 = 1;
                score3_1_0.setBackgroundColor(Color.GRAY);
                score3_1_1.setBackgroundColor(Color.GREEN);
            }
        });
        viewLayout_3.addView(score3_1_1);

        view3_2 = new TextView(mContext);
        view3_2.setText(" ");
        view3_2.setWidth(170);
        view3_2.setHeight(100);
        view3_2.setTextSize(13);/////////////////////////
        //view3_2.setBackground(getResources().getDrawable(R.drawable.t1_1));
        view3_2.setBackgroundResource(R.drawable.il2_3);
//       view3_2.setBackgroundColor(Color.LTGRAY);
        view3_2.setLayoutParams(marginLayout);
        view3_2.setGravity(Gravity.CENTER);
        viewLayout_3.addView(view3_2);

        score3_2_0 = new Button(mContext);
        score3_2_0.setWidth(150);
        score3_2_0.setHeight(100);
        score3_2_0.setText("0\n Absent");
        score3_2_0.setTextSize(13);
        score3_2_0.setLayoutParams(marginLayout);
        score3_2_0.setBackgroundColor(Color.GRAY);
        score3_2_0.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 2;
                score3_2 = 0;
                score3_2_0.setBackgroundColor(Color.GREEN);
                score3_2_1.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_3.addView(score3_2_0);

        score3_2_1 = new Button(mContext);
        score3_2_1.setWidth(150);
        score3_2_1.setHeight(100);
        score3_2_1.setText("1\n Present");
        score3_2_1.setTextSize(13);
        score3_2_1.setLayoutParams(marginLayout);
        score3_2_1.setBackgroundColor(Color.GRAY);
        score3_2_1.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 2;
                score3_2 = 1;
                score3_2_0.setBackgroundColor(Color.GRAY);
                score3_2_1.setBackgroundColor(Color.GREEN);
            }
        });
        viewLayout_3.addView(score3_2_1);

        view3_3 = new TextView(mContext);
        view3_3.setText(" ");
        view3_3.setWidth(170);
        view3_3.setHeight(100);
        view3_3.setTextSize(15);/////////////////////////
        //view3_3.setBackground(getResources().getDrawable(R.drawable.t1_1));
        view3_3.setBackgroundResource(R.drawable.il3_3);
        view3_3.setLayoutParams(marginLayout);
        view3_3.setGravity(Gravity.CENTER);
        viewLayout_3.addView(view3_3);

        score3_3_0 = new Button(mContext);
        score3_3_0.setWidth(150);
        score3_3_0.setHeight(100);
        score3_3_0.setText("0\n Absent");
        score3_3_0.setTextSize(13);
        score3_3_0.setLayoutParams(marginLayout);
        score3_3_0.setBackgroundColor(Color.GRAY);
        score3_3_0.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 3;
                score3_3 = 0;
                score3_3_0.setBackgroundColor(Color.GREEN);
                score3_3_1.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout_3.addView(score3_3_0);

        score3_3_1 = new Button(mContext);
        score3_3_1.setWidth(150);
        score3_3_1.setHeight(100);
        score3_3_1.setText("1\n Present");
        score3_3_1.setTextSize(13);
        score3_3_1.setLayoutParams(marginLayout);
        score3_3_1.setBackgroundColor(Color.GRAY);
        score3_3_1.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                type = 3;
                score3_3 = 1;
                score3_3_0.setBackgroundColor(Color.GRAY);
                score3_3_1.setBackgroundColor(Color.GREEN);
            }
        });
        viewLayout_3.addView(score3_3_1);

        mLayout1.addView(viewLayout_1);
        mLayout1.addView(viewLayout_2);
        mLayout1.addView(viewLayout_3);

        mLayout.addView(mLayout1);


        LinearLayout viewLayout2_1 = new LinearLayout(mContext);
        viewLayout2_1.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout2_1.setBackgroundColor(Color.BLACK);
        viewLayout2_1.setLayoutParams(marginLayout);
        view2_01 = new TextView(mContext);
        view2_01.setText("Total Number of confabulated marks");
        view2_01.setWidth(1200);
        view2_01.setHeight(90);
        view2_01.setTextSize(15);/////////////////////////
        view2_01.setBackgroundColor(Color.LTGRAY);
        view2_01.setLayoutParams(marginLayout);
        view2_01.setGravity(Gravity.CENTER);
        viewLayout2_1.addView(view2_01);

        txt2_1 = new EditText(mContext);
        txt2_1.setWidth(220);
        txt2_1.setHeight(90);
        txt2_1.setTextSize(15);
        txt2_1.setBackgroundColor(Color.WHITE);
        txt2_1.setLayoutParams(marginLayout);
        viewLayout2_1.addView(txt2_1);

        mLayout.addView(viewLayout2_1);

        LinearLayout viewLayout2_2 = new LinearLayout(mContext);
        viewLayout2_2.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout2_2.setBackgroundColor(Color.BLACK);
        viewLayout2_2.setLayoutParams(marginLayout);
        view2_02 = new TextView(mContext);
        view2_02.setText("Total Number of rotated marks");
        view2_02.setWidth(1200);
        view2_02.setHeight(90);
        view2_02.setTextSize(15);/////////////////////////
        view2_02.setBackgroundColor(Color.LTGRAY);
        view2_02.setLayoutParams(marginLayout);
        view2_02.setGravity(Gravity.CENTER);
        viewLayout2_2.addView(view2_02);

        txt2_2 = new EditText(mContext);
        txt2_2.setWidth(220);
        txt2_2.setHeight(90);
        txt2_2.setTextSize(15);
        txt2_2.setBackgroundColor(Color.WHITE);
        txt2_2.setLayoutParams(marginLayout);
        viewLayout2_2.addView(txt2_2);

        mLayout.addView(viewLayout2_2);

        LinearLayout viewLayout2_3 = new LinearLayout(mContext);
        viewLayout2_3.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout2_3.setBackgroundColor(Color.BLACK);
        viewLayout2_3.setLayoutParams(marginLayout);
        view2_03 = new TextView(mContext);
        view2_03.setText("Total Number of repeated marks (within Free Recall Condition only)");
        view2_03.setWidth(1200);
        view2_03.setHeight(90);
        view2_03.setTextSize(15);/////////////////////////
        view2_03.setBackgroundColor(Color.LTGRAY);
        view2_03.setLayoutParams(marginLayout);
        view2_03.setGravity(Gravity.CENTER);
        viewLayout2_3.addView(view2_03);

        txt2_3 = new EditText(mContext);
        txt2_3.setWidth(220);
        txt2_3.setHeight(90);
        txt2_3.setTextSize(15);
        txt2_3.setBackgroundColor(Color.WHITE);
        txt2_3.setLayoutParams(marginLayout);
        viewLayout2_3.addView(txt2_3);

        mLayout.addView(viewLayout2_3);

        LinearLayout viewLayout2_4 = new LinearLayout(mContext);
        viewLayout2_4.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout2_4.setBackgroundColor(Color.BLACK);
        viewLayout2_4.setLayoutParams(marginLayout);
        view2_04 = new TextView(mContext);
        view2_04.setText("Total Number of marks drawn by participant");
        view2_04.setWidth(1200);
        view2_04.setHeight(90);
        view2_04.setTextSize(15);/////////////////////////
        view2_04.setBackgroundColor(Color.LTGRAY);
        view2_04.setLayoutParams(marginLayout);
        view2_04.setGravity(Gravity.CENTER);
        viewLayout2_4.addView(view2_04);

        txt2_4 = new EditText(mContext);
        txt2_4.setWidth(220);
        txt2_4.setHeight(90);
        txt2_4.setTextSize(15);
        txt2_4.setBackgroundColor(Color.WHITE);
        txt2_4.setLayoutParams(marginLayout);
        viewLayout2_4.addView(txt2_4);

        mLayout.addView(viewLayout2_4);

        LinearLayout viewLayout3 = new LinearLayout(mContext);
        viewLayout3.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout3.setBackgroundColor(Color.BLACK);
        viewLayout3.setLayoutParams(marginLayout);

        view3 = new TextView(mContext);
        view3.setText("Present symbols + Confabulated marks + Rotated marks + Repeated marks must equal number of marks drawn by participant.");
        view3.setWidth(1420);
        view3.setHeight(80);
        view3.setTextSize(12);/////////////////////////
        view3.setBackgroundColor(Color.GRAY);
        view3.setLayoutParams(marginLayout);
        view3.setGravity(Gravity.CENTER);
        viewLayout3.addView(view3);
        mLayout.addView(viewLayout3);

        LinearLayout viewLayout4 = new LinearLayout(mContext);
        viewLayout4.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout4.setBackgroundColor(Color.BLACK);
        viewLayout4.setLayoutParams(marginLayout);

        view4 = new TextView(mContext);
        view4.setText("Which of these errors were made on IL-Free Recall? (circle)");
        view4.setWidth(700);
        view4.setHeight(100);
        view4.setTextSize(15);/////////////////////////
        view4.setBackgroundColor(Color.LTGRAY);
        view4.setLayoutParams(marginLayout);
        view4.setGravity(Gravity.CENTER);
        viewLayout4.addView(view4);

        btn4_1 = new Button(mContext);
        btn4_1.setWidth(100);
        btn4_1.setHeight(100);
        btn4_1.setText("none");
        btn4_1.setTextSize(11);
        btn4_1.setLayoutParams(marginLayout);
        btn4_1.setBackgroundColor(Color.GRAY);
        btn4_1.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                errortype = 1;
                btn4_1.setBackgroundColor(Color.GREEN);
                btn4_2.setBackgroundColor(Color.GRAY);
                btn4_3.setBackgroundColor(Color.GRAY);
                btn4_4.setBackgroundColor(Color.GRAY);
                btn4_5.setBackgroundColor(Color.GRAY);
                btn4_6.setBackgroundColor(Color.GRAY);
                btn4_7.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout4.addView(btn4_1);

        btn4_2 = new Button(mContext);
        btn4_2.setWidth(100);
        btn4_2.setHeight(100);
        btn4_2.setText("“U”");
        btn4_2.setTextSize(13);
        btn4_2.setLayoutParams(marginLayout);
        btn4_2.setBackgroundColor(Color.GRAY);
        btn4_2.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                errortype = 2;
                btn4_1.setBackgroundColor(Color.GRAY);
                btn4_2.setBackgroundColor(Color.GREEN);
                btn4_3.setBackgroundColor(Color.GRAY);
                btn4_4.setBackgroundColor(Color.GRAY);
                btn4_5.setBackgroundColor(Color.GRAY);
                btn4_6.setBackgroundColor(Color.GRAY);
                btn4_7.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout4.addView(btn4_2);

        btn4_3 = new Button(mContext);
        btn4_3.setWidth(100);
        btn4_3.setHeight(100);
        btn4_3.setText("“L”");
        btn4_3.setTextSize(13);
        btn4_3.setLayoutParams(marginLayout);
        btn4_3.setBackgroundColor(Color.GRAY);
        btn4_3.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                errortype = 3;
                btn4_1.setBackgroundColor(Color.GRAY);
                btn4_2.setBackgroundColor(Color.GRAY);
                btn4_3.setBackgroundColor(Color.GREEN);
                btn4_4.setBackgroundColor(Color.GRAY);
                btn4_5.setBackgroundColor(Color.GRAY);
                btn4_6.setBackgroundColor(Color.GRAY);
                btn4_7.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout4.addView(btn4_3);

        btn4_4 = new Button(mContext);
        btn4_4.setWidth(100);
        btn4_4.setHeight(100);
        btn4_4.setText("“V”");
        btn4_4.setTextSize(13);
        btn4_4.setLayoutParams(marginLayout);
        btn4_4.setBackgroundColor(Color.GRAY);
        btn4_4.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                errortype = 4;
                btn4_1.setBackgroundColor(Color.GRAY);
                btn4_2.setBackgroundColor(Color.GRAY);
                btn4_3.setBackgroundColor(Color.GRAY);
                btn4_4.setBackgroundColor(Color.GREEN);
                btn4_5.setBackgroundColor(Color.GRAY);
                btn4_6.setBackgroundColor(Color.GRAY);
                btn4_7.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout4.addView(btn4_4);

        btn4_5 = new Button(mContext);
        btn4_5.setWidth(100);
        btn4_5.setHeight(100);
        btn4_5.setText("“A”");
        btn4_5.setTextSize(13);
        btn4_5.setLayoutParams(marginLayout);
        btn4_5.setBackgroundColor(Color.GRAY);
        btn4_5.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                errortype = 5;
                btn4_1.setBackgroundColor(Color.GRAY);
                btn4_2.setBackgroundColor(Color.GRAY);
                btn4_3.setBackgroundColor(Color.GRAY);
                btn4_4.setBackgroundColor(Color.GRAY);
                btn4_5.setBackgroundColor(Color.GREEN);
                btn4_6.setBackgroundColor(Color.GRAY);
                btn4_7.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout4.addView(btn4_5);

        btn4_6 = new Button(mContext);
        btn4_6.setWidth(100);
        btn4_6.setHeight(100);
        btn4_6.setText("“=”");
        btn4_6.setTextSize(13);
        btn4_6.setLayoutParams(marginLayout);
        btn4_6.setBackgroundColor(Color.GRAY);
        btn4_6.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                errortype = 6;
                btn4_1.setBackgroundColor(Color.GRAY);
                btn4_2.setBackgroundColor(Color.GRAY);
                btn4_3.setBackgroundColor(Color.GRAY);
                btn4_4.setBackgroundColor(Color.GRAY);
                btn4_5.setBackgroundColor(Color.GRAY);
                btn4_6.setBackgroundColor(Color.GREEN);
                btn4_7.setBackgroundColor(Color.GRAY);
            }
        });
        viewLayout4.addView(btn4_6);

        btn4_7 = new Button(mContext);
        btn4_7.setWidth(100);
        btn4_7.setHeight(100);
        btn4_7.setText("“T”");
        btn4_7.setTextSize(13);
        btn4_7.setLayoutParams(marginLayout);
        btn4_7.setBackgroundColor(Color.GRAY);
        btn4_7.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                errortype = 7;
                btn4_1.setBackgroundColor(Color.GRAY);
                btn4_2.setBackgroundColor(Color.GRAY);
                btn4_3.setBackgroundColor(Color.GRAY);
                btn4_4.setBackgroundColor(Color.GRAY);
                btn4_5.setBackgroundColor(Color.GRAY);
                btn4_6.setBackgroundColor(Color.GRAY);
                btn4_7.setBackgroundColor(Color.GREEN);
            }
        });
        viewLayout4.addView(btn4_7);

        mLayout.addView(viewLayout4);

        addView(mLayout);
        return;
    }

    public IncidentalLearningViewB(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }

    private void instantiate(final Context context, final IncidentalLearningQuestion incidentallearningQuestion)
    {
        // TODO Auto-generated method stub
        IncidentalLearningQuestion incidentallQuestion = new IncidentalLearningQuestion();
        return;
    }

}
