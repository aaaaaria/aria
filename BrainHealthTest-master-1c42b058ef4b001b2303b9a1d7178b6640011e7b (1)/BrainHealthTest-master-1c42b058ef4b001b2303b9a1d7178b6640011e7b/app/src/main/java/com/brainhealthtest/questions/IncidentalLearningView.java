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
import com.brainhealthtest.utility.IncidentalLearningQuestion;

import java.util.ArrayList;
import java.util.List;


public class IncidentalLearningView extends LinearLayout
{

    Context mContext;
    TextView view1, view2_1, view2_2_1, view2_2_2, view2_3_1, view2_3_2, view2_3_3, view2_3_4, view2_3_5,
            view3, view4;
    public EditText txt2_4_1, txt2_4_2, txt2_4_3, txt2_4_4, txt2_4_5;
    Button btn4_1, btn4_2, btn4_3, btn4_4, btn4_5, btn4_6, btn4_7;
    int totQuestions;
    public int errortype;//String
    public Handler callbackHandler;
    public List<SingleIncidentalLearningView> il_1;


    public IncidentalLearningView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        mContext = context;
        //	instantiate(context, null);
    }


    public IncidentalLearningView(final Context context, final IncidentalLearningQuestion incidentallearningQuestion, Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        mContext = context;
        instantiate(context, incidentallearningQuestion);
    }

    public void Init(IncidentalLearningQuestion incidental_1Question, Helper helper)
    {
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        //  mLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        marginLayout.setMargins(2, 2, 2, 2);//setMargins(left, top, right, bottom)


        TextView textView = new TextView(mContext);
//        textView = new TextView(mContext);
        textView.setTextSize(18);
        textView.setGravity(Gravity.CENTER);
        textView.setText(incidental_1Question.title);
        mLayout.addView(textView);//title

        LinearLayout viewLayout1 = new LinearLayout(mContext);
        viewLayout1.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout1.setBackgroundColor(Color.BLACK);
        viewLayout1.setLayoutParams(marginLayout);
        //layout1
        view1 = new TextView(mContext);
        view1.setText("Total Number of correct pairs for each Code Numeral (range = 0 to 2)");
        view1.setWidth(1400);
        view1.setHeight(100);
        view1.setTextSize(15);/////////////////////////
        view1.setBackgroundColor(Color.LTGRAY);
        view1.setLayoutParams(marginLayout);
        view1.setGravity(Gravity.CENTER);
        viewLayout1.addView(view1);
        mLayout.addView(viewLayout1);

        LinearLayout mLayout1 = new LinearLayout(mContext);
        mLayout1.setOrientation(LinearLayout.HORIZONTAL);//Vertical
        mLayout1.setGravity(Gravity.CENTER_VERTICAL);//CENTER_HORIZONTAL
        totQuestions = 0;
        il_1 = new ArrayList<SingleIncidentalLearningView>();
        for (int i = 0; i < incidental_1Question.ques1.size(); ++i)
        {
            SingleIncidentalLearningView singleil_1 = new SingleIncidentalLearningView(mContext);
            singleil_1.Init(incidental_1Question.ques1.get(i), incidental_1Question.ques2.get(i), incidental_1Question.ques3.get(i), helper);
            mLayout1.addView(singleil_1);
            il_1.add(singleil_1);
            totQuestions++;
        }
        mLayout.addView(mLayout1);
//		Log.d("il_1",String.valueOf("boston_namingQuestion.ques.size()"+boston_namingQuestion.ques1.size())); 
        Log.d("il_1", String.valueOf("totQuestions" + totQuestions)); /////////////test

        LinearLayout viewLayout2 = new LinearLayout(mContext);
        viewLayout2.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout2.setBackgroundColor(Color.BLACK);
        viewLayout2.setLayoutParams(marginLayout);
        //layout2
        view2_1 = new TextView(mContext);
        view2_1.setText("Total Number of incorrect marks\n (range = 0 to 18):");
        view2_1.setWidth(450);
        view2_1.setHeight(400);
        view2_1.setTextSize(15);/////////////////////////
        view2_1.setBackgroundColor(Color.LTGRAY);
        view2_1.setLayoutParams(marginLayout);
        view2_1.setGravity(Gravity.CENTER);

        LinearLayout viewLayout2_2 = new LinearLayout(mContext);
        viewLayout2_2.setOrientation(LinearLayout.VERTICAL);
        viewLayout2_2.setBackgroundColor(Color.BLACK);
        //viewLayout2_2.setLayoutParams(marginLayout);

        view2_2_1 = new TextView(mContext);
        view2_2_1.setText("another mark in code");
        view2_2_1.setWidth(350);
        view2_2_1.setHeight(205);
        view2_2_1.setTextSize(15);/////////////////////////
        view2_2_1.setBackgroundColor(Color.LTGRAY);
        view2_2_1.setLayoutParams(marginLayout);
        view2_2_1.setGravity(Gravity.CENTER);

        view2_2_2 = new TextView(mContext);
        view2_2_2.setText("confabulated \n (NOT another mark in code)");
        view2_2_2.setWidth(350);
        view2_2_2.setHeight(205);
        view2_2_2.setTextSize(15);/////////////////////////
        view2_2_2.setBackgroundColor(Color.LTGRAY);
        view2_2_2.setLayoutParams(marginLayout);
        view2_2_2.setGravity(Gravity.CENTER);

        viewLayout2_2.addView(view2_2_1);
        viewLayout2_2.addView(view2_2_2);

        LinearLayout viewLayout2_3 = new LinearLayout(mContext);
        viewLayout2_3.setOrientation(LinearLayout.VERTICAL);
        viewLayout2_3.setBackgroundColor(Color.BLACK);
        //viewLayout2_3.setLayoutParams(marginLayout);

        view2_3_1 = new TextView(mContext);
        view2_3_1.setText("rotation of correct mark");
        view2_3_1.setWidth(400);
        view2_3_1.setHeight(100);
        view2_3_1.setTextSize(15);/////////////////////////
        view2_3_1.setBackgroundColor(Color.LTGRAY);
        view2_3_1.setLayoutParams(marginLayout);
        view2_3_1.setGravity(Gravity.CENTER);

        view2_3_2 = new TextView(mContext);
        view2_3_2.setText("not rotation of correct mark");
        view2_3_2.setWidth(400);
        view2_3_2.setHeight(100);
        view2_3_2.setTextSize(15);/////////////////////////
        view2_3_2.setBackgroundColor(Color.LTGRAY);
        view2_3_2.setLayoutParams(marginLayout);
        view2_3_2.setGravity(Gravity.CENTER);

        view2_3_3 = new TextView(mContext);
        view2_3_3.setText("rotation of mark in code");
        view2_3_3.setWidth(400);
        view2_3_3.setHeight(100);
        view2_3_3.setTextSize(15);/////////////////////////
        view2_3_3.setBackgroundColor(Color.LTGRAY);
        view2_3_3.setLayoutParams(marginLayout);
        view2_3_3.setGravity(Gravity.CENTER);

        view2_3_4 = new TextView(mContext);
        view2_3_4.setText("novel mark");
        view2_3_4.setWidth(400);
        view2_3_4.setHeight(100);
        view2_3_4.setTextSize(15);/////////////////////////
        view2_3_4.setBackgroundColor(Color.LTGRAY);
        view2_3_4.setLayoutParams(marginLayout);
        view2_3_4.setGravity(Gravity.CENTER);

        viewLayout2_3.addView(view2_3_1);
        viewLayout2_3.addView(view2_3_2);
        viewLayout2_3.addView(view2_3_3);
        viewLayout2_3.addView(view2_3_4);

        LinearLayout viewLayout2_4 = new LinearLayout(mContext);
        viewLayout2_4.setOrientation(LinearLayout.VERTICAL);
        viewLayout2_4.setBackgroundColor(Color.BLACK);
        //viewLayout2_4.setLayoutParams(marginLayout);

        txt2_4_1 = new EditText(mContext);
        txt2_4_1.setWidth(200);
        txt2_4_1.setHeight(100);
        txt2_4_1.setTextSize(15);
        txt2_4_1.setBackgroundColor(Color.WHITE);
        txt2_4_1.setLayoutParams(marginLayout);

        txt2_4_2 = new EditText(mContext);
        txt2_4_2.setWidth(200);
        txt2_4_2.setHeight(100);
        txt2_4_2.setTextSize(15);
        txt2_4_2.setBackgroundColor(Color.WHITE);
        txt2_4_2.setLayoutParams(marginLayout);

        txt2_4_3 = new EditText(mContext);
        txt2_4_3.setWidth(200);
        txt2_4_3.setHeight(100);
        txt2_4_3.setTextSize(15);
        txt2_4_3.setBackgroundColor(Color.WHITE);
        txt2_4_3.setLayoutParams(marginLayout);

        txt2_4_4 = new EditText(mContext);
        txt2_4_4.setWidth(200);
        txt2_4_4.setHeight(100);
        txt2_4_4.setTextSize(15);
        txt2_4_4.setBackgroundColor(Color.WHITE);
        txt2_4_4.setLayoutParams(marginLayout);

        viewLayout2_4.addView(txt2_4_1);
        viewLayout2_4.addView(txt2_4_2);
        viewLayout2_4.addView(txt2_4_3);
        viewLayout2_4.addView(txt2_4_4);

        viewLayout2.addView(view2_1);
        viewLayout2.addView(viewLayout2_2);
        viewLayout2.addView(viewLayout2_3);
        viewLayout2.addView(viewLayout2_4);

        mLayout.addView(viewLayout2);

        LinearLayout viewLayout2_5 = new LinearLayout(mContext);
        viewLayout2_5.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout2_5.setBackgroundColor(Color.BLACK);
        viewLayout2_5.setLayoutParams(marginLayout);
        view2_3_5 = new TextView(mContext);
        view2_3_5.setText("Total Number left blank (range = 0 to 18):");
        view2_3_5.setWidth(1200);
        view2_3_5.setHeight(90);
        view2_3_5.setTextSize(15);/////////////////////////
        view2_3_5.setBackgroundColor(Color.LTGRAY);
        view2_3_5.setLayoutParams(marginLayout);
        view2_3_5.setGravity(Gravity.CENTER);
        viewLayout2_5.addView(view2_3_5);

        txt2_4_5 = new EditText(mContext);
        txt2_4_5.setWidth(200);
        txt2_4_5.setHeight(90);
        txt2_4_5.setTextSize(15);
        txt2_4_5.setBackgroundColor(Color.WHITE);
        txt2_4_5.setLayoutParams(marginLayout);
        viewLayout2_5.addView(txt2_4_5);

        mLayout.addView(viewLayout2_5);


        LinearLayout viewLayout3 = new LinearLayout(mContext);
        viewLayout3.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout3.setBackgroundColor(Color.BLACK);
        viewLayout3.setLayoutParams(marginLayout);

        view3 = new TextView(mContext);
        view3.setText("Correct pairs + Incorrect marks + Left blank must equal 18.");
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
        view4.setText("Which of these errors were made on IL-Paired? (circle)  ");
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
// 				errortype="none";
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
// 				errortype="U";
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
// 				errortype="L";
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
// 				errortype="V";
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
// 				errortype="A";
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
// 				errortype="=";
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
// 				errortype="T";
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

    public IncidentalLearningView(Context context)
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
