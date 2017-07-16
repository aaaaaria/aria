package com.brainhealthtest.questions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.brainhealthtest.R;
import com.brainhealthtest.osshelper.Helper;
import com.brainhealthtest.utility.Content2;
import com.brainhealthtest.utility.DigitSymbolQuestion;
import com.brainhealthtest.utility.QuestionItemSet;

public class DigitSymbolActivity extends Activity implements OnClickListener
{
    QuestionItemSet mQuestionItemSet;
    DigitSymbolQuestion digitQuestion;

    Helper helper = new Helper();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        helper.ossService = OSSServiceProvider.getService();
        helper.ossService.setApplicationContext(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.digit_symbol);
        InitView();
    }

    private void InitView()
    {
        mQuestionItemSet = (QuestionItemSet) getApplicationContext();
        digitQuestion = (DigitSymbolQuestion) ((mQuestionItemSet.GetQuestion()));

        DigitSymbolView digits = (DigitSymbolView) findViewById(R.id.digitsymbol);///////////
        digits.Init(digitQuestion, helper);

        Button btn = (Button) findViewById(R.id.digit_symbolGetNext);
        btn.setOnClickListener((OnClickListener) this);//?
        Button BtnFinal = (Button) findViewById(R.id.btnFINISH);
        BtnFinal.setOnClickListener((OnClickListener) this);
        Button BtnBack = (Button) findViewById(R.id.btnBACK);
        BtnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
        if (checkBox.isChecked())
        {
            digitQuestion.skip = true;
            for (int i = 0; i < digitQuestion.skipQues.size(); ++i)
                mQuestionItemSet.questionItemSet.get(digitQuestion.skipQues.get(i)).skip = true;
        }
        ((DigitSymbolQuestion) (mQuestionItemSet.GetQuestion())).GetAnswer((DigitSymbolView) findViewById(R.id.digitsymbol));

        switch (v.getId())
        {
            case R.id.digit_symbolGetNext:

                Helper helper = new Helper();
                helper.ossService = OSSServiceProvider.getService();
                helper.ossService.setApplicationContext(getApplicationContext());
                helper.Init();
                try
                {
                    String json = mQuestionItemSet.Save1(helper.GetObject(mQuestionItemSet.folderName + "tester.json"), mQuestionItemSet.questionId);
                    byte[] data = json.getBytes();
                    helper.PutObject(mQuestionItemSet.folderName + "tester.json", data);
                } catch (OSSException e)
                {
                    e.printStackTrace();
                }

                mQuestionItemSet.questionId++;
                while (mQuestionItemSet.questionId < mQuestionItemSet.questionItemSet.size() && mQuestionItemSet.questionItemSet.get(mQuestionItemSet.questionId).skip)
                    mQuestionItemSet.questionId++;
                Intent intent = new Intent();
                intent.setClass(DigitSymbolActivity.this, mQuestionItemSet.GetActivity());
                startActivity(intent);
                finish();
                break;

            case R.id.btnFINISH:
                Helper helper2 = new Helper();
                helper2.ossService = OSSServiceProvider.getService();
                helper2.ossService.setApplicationContext(getApplicationContext());
                helper2.Init();
                try
                {
                    String json = mQuestionItemSet.Save1(helper2.GetObject(mQuestionItemSet.folderName + "tester.json"), mQuestionItemSet.questionId);
                    byte[] data = json.getBytes();
                    helper2.PutObject(mQuestionItemSet.folderName + "tester.json", data);
                } catch (OSSException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Intent intent2 = new Intent();
                intent2.setClass(DigitSymbolActivity.this, EndActivity.class);
                startActivity(intent2);
                finish();
                break;


            case R.id.btnBACK:

                Helper helper3 = new Helper();
                helper3.ossService = OSSServiceProvider.getService();
                helper3.ossService.setApplicationContext(getApplicationContext());
                helper3.Init();
                try
                {
                    String json2 = mQuestionItemSet.Save1(helper3.GetObject(mQuestionItemSet.folderName + "tester.json"), mQuestionItemSet.questionId);
                    byte[] data = json2.getBytes();
                    helper3.PutObject(mQuestionItemSet.folderName + "tester.json", data);
                } catch (OSSException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Intent intent3 = new Intent();
                intent3.setClass(DigitSymbolActivity.this, Content2.class);
                startActivity(intent3);
                finish();
                break;

        }

    }
}
