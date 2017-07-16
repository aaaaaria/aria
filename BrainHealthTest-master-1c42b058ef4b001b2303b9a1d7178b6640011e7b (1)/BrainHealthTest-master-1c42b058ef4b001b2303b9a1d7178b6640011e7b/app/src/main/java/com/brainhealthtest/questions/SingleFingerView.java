package com.brainhealthtest.questions;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brainhealthtest.osshelper.Helper;


public class SingleFingerView extends LinearLayout
{
    private String pair1;
    private TextView word1, word2;
    public EditText Answer1, Answer2;
    public Handler callbackHandler;

    public void Init(String mpair1, Helper helper)
    {
        pair1 = mpair1;
        word1.setText(pair1);
        word2.setText(pair1);
    }

    public SingleFingerView(Context context)
    {
        super(context);
        Context mContext = context;
        // TODO Auto-generated constructor stub

        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.HORIZONTAL);
        mLayout.setBackgroundColor(Color.BLACK);
        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        marginLayout.setMargins(2, 2, 2, 2);

        word1 = new TextView(mContext);
        word1.setWidth(324);
        word1.setHeight(70);
        word1.setTextSize(15);
        word1.setGravity(Gravity.CENTER);
        word1.setLayoutParams(marginLayout);
        word1.setBackgroundColor(Color.WHITE);
        mLayout.addView(word1);

        Answer1 = new EditText(context);
        Answer1.setWidth(324);
        Answer1.setHeight(70);
        Answer1.setTextSize(13);
        Answer1.setLayoutParams(marginLayout);
        Answer1.setBackgroundColor(Color.WHITE);
        mLayout.addView(Answer1);


        word2 = new TextView(mContext);
        word2.setWidth(324);
        word2.setHeight(70);
        word2.setTextSize(15);
        word2.setGravity(Gravity.CENTER);
        word2.setLayoutParams(marginLayout);
        word2.setBackgroundColor(Color.WHITE);
        mLayout.addView(word2);

        Answer2 = new EditText(context);
        Answer2.setWidth(324);
        Answer2.setHeight(70);
        Answer2.setTextSize(13);
        Answer2.setLayoutParams(marginLayout);
        Answer2.setBackgroundColor(Color.WHITE);
        mLayout.addView(Answer2);

        addView(mLayout);
        return;
    }

}
