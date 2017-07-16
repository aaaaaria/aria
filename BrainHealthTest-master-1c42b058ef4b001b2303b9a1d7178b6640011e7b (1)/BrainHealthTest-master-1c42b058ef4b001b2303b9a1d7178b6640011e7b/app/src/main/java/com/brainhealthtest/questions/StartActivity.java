package com.brainhealthtest.questions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.brainhealthtest.R;
import com.brainhealthtest.osshelper.Helper;
import com.brainhealthtest.utility.Content;
import com.brainhealthtest.utility.QuestionItemSet;

import java.io.IOException;
import java.io.InputStream;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class StartActivity extends Activity implements OnClickListener
{
    QuestionItemSet mQuestionItemSet;
    private Button BtnStart;

    void LoadQuestion()
    {
        mQuestionItemSet = (QuestionItemSet) getApplicationContext();
        InputStream questionFile = getResources().openRawResource(R.raw.tester);
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

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);
        BtnStart = (Button) findViewById(R.id.btnStart);
        BtnStart.setOnClickListener(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onClick(View v)
    {
        Helper helper = new Helper();
        helper.ossService = OSSServiceProvider.getService();
        helper.ossService.setApplicationContext(getApplicationContext());
        helper.Init();

        EditText tester = (EditText) findViewById(R.id.testerIDinput);
        EditText participanter = (EditText) findViewById(R.id.participantIDinput);
        try
        {
            if (helper.CheckID(tester.getText().toString(), participanter.getText().toString()))
            {
                Intent intent = new Intent();
                intent.putExtra("testernumber", tester.getText().toString());
                intent.putExtra("participanternumber", participanter.getText().toString());
                intent.setClass(StartActivity.this, Content.class);
                startActivity(intent);
                finish();
            } else
            {
                new AlertDialog.Builder(StartActivity.this).setTitle("wrong")
                        .setMessage("No matching testerID and participantID in database!")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.dismiss();
                            }
                        }).show();
            }
        } catch (OSSException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
