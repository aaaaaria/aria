package com.brainhealthtest.questions;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.brainhealthtest.R;
import com.brainhealthtest.osshelper.Helper;
import com.brainhealthtest.utility.Content2;
import com.brainhealthtest.utility.CookieTheftQuestion;
import com.brainhealthtest.utility.QuestionItemSet;

import java.io.FileInputStream;


public class CookieTheftActivity extends Activity implements OnClickListener
{
    public static String saved_local_path_dir = Environment.getExternalStorageDirectory().getPath() + "/";
    QuestionItemSet mQuestionItemSet;
    CookieTheftQuestion cookieTheftQuestion;
    MediaRecorder recorder = new MediaRecorder();
    MediaPlayer mediaPlayer = new MediaPlayer();
    private FileInputStream fileInputStream;
    Helper helper = new Helper();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        helper.ossService = OSSServiceProvider.getService();
        helper.ossService.setApplicationContext(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cookietheft);
        InitView();
    }

    private void InitView()
    {
        mQuestionItemSet = (QuestionItemSet) getApplicationContext();
        cookieTheftQuestion = (CookieTheftQuestion) ((mQuestionItemSet.GetQuestion()));
        CookieTheftView cookietheft = (CookieTheftView) findViewById(R.id.cookietheft);///////////
        cookietheft.Init(cookieTheftQuestion, helper);
        Button btn = (Button) findViewById(R.id.cookietheftGetNext);
        btn.setOnClickListener(this);
        Button BtnFinal = (Button) findViewById(R.id.btnFINISH);
        BtnFinal.setOnClickListener(this);
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
            cookieTheftQuestion.skip = true;
            for (int i = 0; i < cookieTheftQuestion.skipQues.size(); ++i)
                mQuestionItemSet.questionItemSet.get(cookieTheftQuestion.skipQues.get(i)).skip = true;
        } else
            cookieTheftQuestion.skip = false;
        ((CookieTheftQuestion) (mQuestionItemSet.GetQuestion())).GetAnswer((CookieTheftView) findViewById(R.id.cookietheft));

        switch (v.getId())
        {
            case R.id.cookietheftGetNext:
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
                intent.setClass(CookieTheftActivity.this, mQuestionItemSet.GetActivity());
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
                intent2.setClass(CookieTheftActivity.this, EndActivity.class);
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
                intent3.setClass(CookieTheftActivity.this, Content2.class);
                startActivity(intent3);
                finish();
                break;

        }
    }


}
