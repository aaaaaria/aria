package com.brainhealthtest.questions;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.brainhealthtest.osshelper.Helper;


public class SingleVerbalFluencyView extends LinearLayout
{
    private String TimePeriod;//time period:0-15(sec) etc.
    private TextView timeperiod, Guideword, CorrectNum, WrongLetter, BrokenRule, Perseveration, OtherError, SelfCorrection;
    public EditText Answer, CorrectNumber;
    public Button WrongLetterBtn, BrokenRulebtn, PerseverationBtn, OtherErrorBtn, SelfcorrectionBtn;//Button in one row
    public int wb = 0, bb = 0, pb = 0, ob = 0, sb = 0;

    public Handler callbackHandler;

    public SingleVerbalFluencyView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        instantiate(context, null);
    }

    public void Init(String mtime, Helper helper)
    {
        TimePeriod = mtime;
        timeperiod.setText(TimePeriod);

    }

    public SingleVerbalFluencyView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
        Context mContext = context;
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.VERTICAL);

        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        marginLayout.setMargins(2, 2, 2, 2);

        LinearLayout viewLayout = new LinearLayout(mContext);
        viewLayout.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout.setBackgroundColor(Color.BLACK);
        //viewLayout.setLayoutParams(marginLayout);
        LinearLayout linearLayout_1 = new LinearLayout(mContext);
        linearLayout_1.setOrientation(LinearLayout.VERTICAL);
        linearLayout_1.setBackgroundColor(Color.BLACK);

        LinearLayout linearLayout_2 = new LinearLayout(mContext);
        linearLayout_2.setOrientation(LinearLayout.VERTICAL);
        linearLayout_2.setBackgroundColor(Color.BLACK);

        LinearLayout linearLayout_3 = new LinearLayout(mContext);
        linearLayout_3.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout_3.setBackgroundColor(Color.BLACK);

        LinearLayout linearLayout_4 = new LinearLayout(mContext);
        linearLayout_4.setOrientation(LinearLayout.VERTICAL);
        linearLayout_4.setBackgroundColor(Color.BLACK);

        LinearLayout linearLayout_5 = new LinearLayout(mContext);
        linearLayout_5.setOrientation(LinearLayout.VERTICAL);
        linearLayout_5.setBackgroundColor(Color.BLACK);

        timeperiod = new TextView(context);
        //timeperiod.setText("North\nSouth\n");
        timeperiod.setWidth(160);
        timeperiod.setHeight(420);
        timeperiod.setTextSize(13);
        timeperiod.setLayoutParams(marginLayout);
        timeperiod.setBackgroundColor(Color.WHITE);
        viewLayout.addView(timeperiod);


        Guideword = new TextView(context);
        Guideword.setText("Write all words produced by participant in order");
        Guideword.setWidth(180);
        Guideword.setHeight(150);
        Guideword.setTextSize(13);
        Guideword.setLayoutParams(marginLayout);
        Guideword.setBackgroundColor(Color.WHITE);
        linearLayout_1.addView(Guideword);

        Answer = new EditText(context);
        Answer.setWidth(300);
        Answer.setHeight(150);
        Answer.setTextSize(13);
        Answer.setLayoutParams(marginLayout);
        Answer.setBackgroundColor(Color.WHITE);
        linearLayout_2.addView(Answer);

        WrongLetter = new TextView(context);
        WrongLetter.setText("WrongLetter");
        WrongLetter.setWidth(180);
        WrongLetter.setHeight(50);
        WrongLetter.setTextSize(13);
        WrongLetter.setLayoutParams(marginLayout);
        WrongLetter.setBackgroundColor(Color.WHITE);
        linearLayout_1.addView(WrongLetter);
        BrokenRule = new TextView(context);
        BrokenRule.setText("BrokenRule");
        BrokenRule.setWidth(180);
        BrokenRule.setHeight(50);
        BrokenRule.setTextSize(13);
        BrokenRule.setLayoutParams(marginLayout);
        BrokenRule.setBackgroundColor(Color.WHITE);
        linearLayout_1.addView(BrokenRule);
        Perseveration = new TextView(context);
        Perseveration.setText("Perseveration");
        Perseveration.setWidth(180);
        Perseveration.setHeight(50);
        Perseveration.setTextSize(13);
        Perseveration.setLayoutParams(marginLayout);
        Perseveration.setBackgroundColor(Color.WHITE);
        linearLayout_1.addView(Perseveration);
        OtherError = new TextView(context);
        OtherError.setText("OtherError");
        OtherError.setWidth(180);
        OtherError.setHeight(50);
        OtherError.setTextSize(13);
        OtherError.setLayoutParams(marginLayout);
        OtherError.setBackgroundColor(Color.WHITE);
        linearLayout_1.addView(OtherError);
        SelfCorrection = new TextView(context);
        SelfCorrection.setText("SelfCorrection");
        SelfCorrection.setWidth(180);
        SelfCorrection.setHeight(50);
        SelfCorrection.setTextSize(13);
        SelfCorrection.setLayoutParams(marginLayout);
        SelfCorrection.setBackgroundColor(Color.WHITE);
        linearLayout_1.addView(SelfCorrection);

        WrongLetterBtn = new Button(mContext);
        WrongLetterBtn.setBackgroundColor(Color.LTGRAY);
        WrongLetterBtn.setLayoutParams(marginLayout);
        WrongLetterBtn.setWidth(100);
        WrongLetterBtn.setHeight(50);
        WrongLetterBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                if (wb == 0)
                {
                    WrongLetterBtn.setBackgroundColor(Color.GREEN);
                    wb = 1;
                } else
                {
                    WrongLetterBtn.setBackgroundColor(Color.LTGRAY);
                    wb = 0;
                }
            }
        });
        linearLayout_4.addView(WrongLetterBtn);
        BrokenRulebtn = new Button(mContext);
        BrokenRulebtn.setBackgroundColor(Color.LTGRAY);
        BrokenRulebtn.setLayoutParams(marginLayout);
        BrokenRulebtn.setWidth(100);
        BrokenRulebtn.setHeight(50);
        BrokenRulebtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                if (bb == 0)
                {
                    BrokenRulebtn.setBackgroundColor(Color.GREEN);
                    bb = 1;
                } else
                {
                    BrokenRulebtn.setBackgroundColor(Color.LTGRAY);
                    bb = 0;
                }
            }
        });
        linearLayout_4.addView(BrokenRulebtn);
        PerseverationBtn = new Button(mContext);
        PerseverationBtn.setBackgroundColor(Color.LTGRAY);
        PerseverationBtn.setLayoutParams(marginLayout);
        PerseverationBtn.setWidth(100);
        PerseverationBtn.setHeight(50);
        PerseverationBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                if (pb == 0)
                {
                    PerseverationBtn.setBackgroundColor(Color.GREEN);
                    pb = 1;
                } else
                {
                    PerseverationBtn.setBackgroundColor(Color.LTGRAY);
                    pb = 0;
                }
            }
        });
        linearLayout_4.addView(PerseverationBtn);
        OtherErrorBtn = new Button(mContext);
        OtherErrorBtn.setBackgroundColor(Color.LTGRAY);
        OtherErrorBtn.setLayoutParams(marginLayout);
        OtherErrorBtn.setWidth(100);
        OtherErrorBtn.setHeight(50);
        OtherErrorBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                if (ob == 0)
                {
                    OtherErrorBtn.setBackgroundColor(Color.GREEN);
                    ob = 1;
                } else
                {
                    OtherErrorBtn.setBackgroundColor(Color.LTGRAY);
                    ob = 0;
                }
            }
        });
        linearLayout_4.addView(OtherErrorBtn);
        SelfcorrectionBtn = new Button(mContext);
        SelfcorrectionBtn.setBackgroundColor(Color.LTGRAY);
        SelfcorrectionBtn.setLayoutParams(marginLayout);
        SelfcorrectionBtn.setWidth(100);
        SelfcorrectionBtn.setHeight(50);
        SelfcorrectionBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                if (sb == 0)
                {
                    SelfcorrectionBtn.setBackgroundColor(Color.GREEN);
                    sb = 1;
                } else
                {
                    SelfcorrectionBtn.setBackgroundColor(Color.LTGRAY);
                    sb = 0;
                }
            }
        });
        linearLayout_4.addView(SelfcorrectionBtn);

        CorrectNum = new TextView(context);
        CorrectNum.setText("Correct\nNumber");
        CorrectNum.setWidth(194);
        CorrectNum.setHeight(129);
        CorrectNum.setTextSize(13);
        CorrectNum.setLayoutParams(marginLayout);
        CorrectNum.setBackgroundColor(Color.WHITE);
        linearLayout_5.addView(CorrectNum);
        CorrectNumber = new EditText(context);
        CorrectNumber.setWidth(194);
        CorrectNumber.setHeight(129);
        CorrectNumber.setTextSize(13);
        CorrectNumber.setLayoutParams(marginLayout);
        CorrectNumber.setBackgroundColor(Color.WHITE);
        linearLayout_5.addView(CorrectNumber);

        linearLayout_3.addView(linearLayout_4);
        linearLayout_3.addView(linearLayout_5);
        linearLayout_2.addView(linearLayout_3);
        viewLayout.addView(linearLayout_1);
        viewLayout.addView(linearLayout_2);
        mLayout.addView(viewLayout);
        addView(mLayout);
        return;
    }

    public SingleVerbalFluencyView(final Context context, final String imageUrl, Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        instantiate(context, imageUrl);
    }

    private void instantiate(final Context context, final String imageUrl)
    {
        Context mContext = context;
        TableLayout mytable = new TableLayout(mContext);
        TableRow tableRow = new TableRow(context);
        tableRow.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        timeperiod = new EditText(context);
        tableRow.addView(timeperiod);
        mytable.addView(tableRow);
        addView(mytable);
        return;

    }

    public void Save(Helper helper)
    {

        helper.Init();
        Boolean hasWord = false;
        String typeWord = Answer.getText().toString();
        String output = "";


    }

}
