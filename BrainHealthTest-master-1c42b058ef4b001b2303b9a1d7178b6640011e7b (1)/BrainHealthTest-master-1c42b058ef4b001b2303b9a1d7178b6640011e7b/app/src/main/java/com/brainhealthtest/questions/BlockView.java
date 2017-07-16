package com.brainhealthtest.questions;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.brainhealthtest.osshelper.Helper;
import com.brainhealthtest.utility.BlockQuestion;

import java.util.List;


public class BlockView extends LinearLayout
{
    TextView view1, view2, view3, view4, view4_1, view4_2, view5, view5_1, view5_2, view5_3;
    public EditText time, Observations;
    Button button1_0, button1_1, button1_2, button1_3, button2_0, button2_1, button2_2;
    int totQuestions;
    public List<SingleVerbalFluencyView> vf;
    Context mContext;
    Button playBtn, recordBtn, stopBtn;
    private EditText voicetotext;
    public Handler callbackHandler;

    public BlockView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        mContext = context;
        //	instantiate(context, null);
    }

    public BlockView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public BlockView(final Context context, final BlockQuestion blockQuestion, Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        mContext = context;
        instantiate(context, blockQuestion);
    }

    public void Init(BlockQuestion blockQuestion, Helper helper)
    {
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        marginLayout.setMargins(2, 2, 2, 2);//setMargins(left, top, right, bottom)

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


        addView(mLayout);
        return;

    }

    private void instantiate(final Context context, final BlockQuestion boston_naming)
    {
        BlockQuestion cookietheftQuestion = new BlockQuestion();
        // next line: for preview
        //Init(trailerQuestion, null);
        return;
    }
}
