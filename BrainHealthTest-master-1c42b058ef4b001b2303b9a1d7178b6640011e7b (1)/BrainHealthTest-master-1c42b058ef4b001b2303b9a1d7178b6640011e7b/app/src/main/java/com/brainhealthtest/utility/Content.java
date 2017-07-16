package com.brainhealthtest.utility;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.brainhealthtest.R;
import com.brainhealthtest.osshelper.Helper;

import java.io.IOException;
import java.io.InputStream;


public class Content extends Activity implements OnClickListener
{
    QuestionItemSet mQuestionItemSet;
    private Button NewQuestion, OldQuestion, SelectQuestion;
    private Bundle bundle;

    void LoadQuestion()
    {
        mQuestionItemSet = (QuestionItemSet) getApplicationContext();
        mQuestionItemSet.testerid = bundle.getString("testernumber");
        mQuestionItemSet.participanterid = bundle.getString("participanternumber");
        InputStream questionFile = getResources().openRawResource(R.raw.tester_1);//tester
        int count;
        try
        {
            count = questionFile.available();
            byte[] rebyte = new byte[count];
            questionFile.read(rebyte);
            String questionString = new String(rebyte);
            mQuestionItemSet.Load(questionString);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    void Save()
    {
        String json = mQuestionItemSet.Save();
        byte[] data = json.getBytes();
        Helper helper = new Helper();
        helper.ossService = OSSServiceProvider.getService();
        helper.ossService.setApplicationContext(getApplicationContext());
        helper.Init();
        try
        {
            helper.PutObject(mQuestionItemSet.folderName + "tester.json", data);
        } catch (OSSException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
        NewQuestion = (Button) findViewById(R.id.btnNew);
        NewQuestion.setOnClickListener(this);
        OldQuestion = (Button) findViewById(R.id.btnOld);
        OldQuestion.setOnClickListener(this);

        Intent intent = getIntent();
        bundle = intent.getExtras();
    }

    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        switch (v.getId())
        {
            case R.id.btnNew:
                LoadQuestion();
                mQuestionItemSet.folderName = "01_Test/" + mQuestionItemSet.testerid + "_" + mQuestionItemSet.participanterid + "/";
                Save();
                Intent intent = new Intent();
                intent.setClass(Content.this, mQuestionItemSet.GetActivity());
                startActivity(intent);
                finish();
                break;
            case R.id.btnOld:
                LoadQuestion();
                Intent intent2 = new Intent();
                intent2.setClass(Content.this, Content2.class);
                startActivity(intent2);
                finish();
                break;

        }

    }

}
