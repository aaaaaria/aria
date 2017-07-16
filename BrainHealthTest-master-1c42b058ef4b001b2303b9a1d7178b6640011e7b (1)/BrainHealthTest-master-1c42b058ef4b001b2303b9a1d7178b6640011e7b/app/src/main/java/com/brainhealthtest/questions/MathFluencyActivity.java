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
import com.brainhealthtest.utility.MathFluencyQuestion;
import com.brainhealthtest.utility.QuestionItemSet;


public class MathFluencyActivity extends Activity implements OnClickListener
{

    Helper helper = new Helper();
    QuestionItemSet mQuestionItemSet;
    MathFluencyQuestion mathfluencyQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        helper.ossService = OSSServiceProvider.getService();
        helper.ossService.setApplicationContext(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_fluency);
        InitView();
    }

    private void InitView()
    {
        mQuestionItemSet = (QuestionItemSet) getApplicationContext();
        mathfluencyQuestion = (MathFluencyQuestion) ((mQuestionItemSet.GetQuestion()));

        MathFluencyView mathf = (MathFluencyView) findViewById(R.id.mathfluency);///////////
        mathf.Init(mathfluencyQuestion, helper);

        Button btn = (Button) findViewById(R.id.math_fluencyGetNext);
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
            mathfluencyQuestion.skip = true;
            for (int i = 0; i < mathfluencyQuestion.skipQues.size(); ++i)
                mQuestionItemSet.questionItemSet.get(mathfluencyQuestion.skipQues.get(i)).skip = true;
        }
        ((MathFluencyQuestion) (mQuestionItemSet.GetQuestion())).GetAnswer((MathFluencyView) findViewById(R.id.mathfluency));


        switch (v.getId())
        {
            case R.id.math_fluencyGetNext:

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
                intent.setClass(MathFluencyActivity.this, mQuestionItemSet.GetActivity());
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
                    String json2 = mQuestionItemSet.Save1(helper2.GetObject(mQuestionItemSet.folderName + "tester.json"), mQuestionItemSet.questionId);
                    byte[] data2 = json2.getBytes();
                    helper2.PutObject(mQuestionItemSet.folderName + "tester.json", data2);
                } catch (OSSException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Intent intent2 = new Intent();
                intent2.setClass(MathFluencyActivity.this, EndActivity.class);
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
                intent3.setClass(MathFluencyActivity.this, Content2.class);
                startActivity(intent3);
                finish();
                break;
        }

    }


}
