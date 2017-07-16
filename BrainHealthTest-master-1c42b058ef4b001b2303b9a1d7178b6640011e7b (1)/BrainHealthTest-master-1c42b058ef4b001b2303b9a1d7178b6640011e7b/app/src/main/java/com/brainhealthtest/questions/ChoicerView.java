package com.brainhealthtest.questions;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.brainhealthtest.utility.ChoiceQuestion;

import java.util.ArrayList;
import java.util.List;


public class ChoicerView extends LinearLayout
{
    int score = 0;
    int answer;
    public int userAnswer = -1;
    private TextView question;
    public List<Button> btns;
    Context mContext;

    public Handler callbackHandler;

    public ChoicerView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        instantiate(context, null);
    }

    public int GetScore()
    {
        return score;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void Init(final ChoiceQuestion choice, int Qwidth, int width)
    {
        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        marginLayout.setMargins(2, 2, 2, 2);

        LinearLayout viewLayout = new LinearLayout(mContext);
        viewLayout.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout.setBackgroundColor(Color.BLACK);
        viewLayout.setLayoutParams(marginLayout);

        question = new TextView(mContext);
        question.setWidth(Qwidth);
        question.setText(choice.question + "\n");
        question.setBackgroundColor(Color.WHITE);
        question.setLayoutParams(marginLayout);

        answer = choice.answer;
        addView(question);
        btns = new ArrayList<Button>();
        for (int i = 0; i < 10; ++i)
        {
            Button btn = new Button(mContext);
            btn.setText("");
            btn.setVisibility(View.GONE);
            btn.setLayoutParams(marginLayout);
            btns.add(btn);
        }
        for (int i = 0; i < choice.choices.size(); ++i)
        {
            Button btn = btns.get(i);
            btn.setVisibility(View.VISIBLE);
            btn.setText(choice.choices.get(i));
            btn.setWidth(width);
            if (choice.answer == i)
            {
                btn.setBackgroundColor(Color.GREEN);
                final int finalI = i;
                btn.setOnClickListener(new OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        for (int i = 0; i < 10; ++i)
                            btns.get(i).setBackgroundColor(Color.LTGRAY);
                        v.setBackgroundColor(Color.YELLOW);
                        score = 1;
                        userAnswer = finalI;

                    }
                });
            } else
            {
                btn.setBackgroundColor(Color.LTGRAY);
                final int finalI1 = i;
                btn.setOnClickListener(new OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        for (int i = 0; i < 10; ++i)
                            btns.get(i).setBackgroundColor(Color.LTGRAY);
                        v.setBackgroundColor(Color.YELLOW);
                        btns.get(choice.answer).setBackgroundColor(Color.GREEN);
                        score = 0;
                        userAnswer = finalI1;
                    }
                });
            }
            viewLayout.addView(btn);
        }
        addView(viewLayout);
    }

    public ChoicerView(final Context context)
    {
        super(context);
        mContext = context;
        return;
    }

    public ChoicerView(final Context context, final ChoiceQuestion choice,
                       Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        instantiate(context, choice);
    }

    private void instantiate(final Context context, final ChoiceQuestion choice)
    {
        mContext = context;
        return;
    }

}
