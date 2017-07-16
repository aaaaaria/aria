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
import com.brainhealthtest.utility.HooperVisualQuestion;
import com.brainhealthtest.utility.QuestionItemSet;

import java.io.File;
import java.io.FileInputStream;


public class HooperVisualActivity extends Activity implements OnClickListener
{
    public static String saved_local_path_dir = Environment.getExternalStorageDirectory().getPath() + "/";
    QuestionItemSet mQuestionItemSet;
    HooperVisualQuestion hoopervisualQuestion;
    MediaRecorder recorder = new MediaRecorder();
    MediaPlayer mediaPlayer = new MediaPlayer();
    private FileInputStream fileInputStream;
    private Button BtnRecord, BtnStop;
    String finalR;
    Helper helper = new Helper();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        helper.ossService = OSSServiceProvider.getService();
        helper.ossService.setApplicationContext(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hoopervisual);
        InitView();
    }

    private void InitView()
    {
        mQuestionItemSet = (QuestionItemSet) getApplicationContext();
        hoopervisualQuestion = (HooperVisualQuestion) ((mQuestionItemSet.GetQuestion()));
        HooperVisualView hoopervisual = (HooperVisualView) findViewById(R.id.hoopervisual);///////////
        hoopervisual.Init(hoopervisualQuestion, helper);
        Button btn = (Button) findViewById(R.id.hoopervisualGetNext);
        btn.setOnClickListener(this);
        Button BtnFinal = (Button) findViewById(R.id.btnFINISH);
        BtnFinal.setOnClickListener(this);
        Button BtnBack = (Button) findViewById(R.id.btnBACK);
        BtnBack.setOnClickListener(this);
        BtnStop = (Button) findViewById(R.id.StopBtn);
        BtnRecord = (Button) findViewById(R.id.RecordBtn);
        BtnRecord.setOnClickListener(this);
        BtnStop.setOnClickListener(this);
        BtnStop.setEnabled(false);
        finalR = hoopervisualQuestion.record;
    }

    public void deleteTempFile(String fileName)
    {
        try
        {
            File file = new File(fileName);
            file.delete();
        } catch (Exception e)
        {
            System.out.println("can't delete " + fileName);
        }
    }

    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
        if (checkBox.isChecked())
        {
            hoopervisualQuestion.skip = true;
            for (int i = 0; i < hoopervisualQuestion.skipQues.size(); ++i)
                mQuestionItemSet.questionItemSet.get(hoopervisualQuestion.skipQues.get(i)).skip = true;
        } else
            hoopervisualQuestion.skip = false;
        ((HooperVisualQuestion) (mQuestionItemSet.GetQuestion())).GetAnswer((HooperVisualView) findViewById(R.id.hoopervisual));

        switch (v.getId())
        {

            case R.id.RecordBtn:
                // TODO record and save
                BtnRecord.setEnabled(false);
                BtnStop.setEnabled(true);
                try
                {
                    recorder = new MediaRecorder();
                    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

                    recorder.setOutputFile(saved_local_path_dir + finalR);
                    recorder.prepare();
                    recorder.start();
                } catch (Exception e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            case R.id.StopBtn:
                try
                {
                    recorder.stop();
                    recorder.reset();
                    recorder.release();
                    Helper helper = new Helper();
                    helper.ossService = OSSServiceProvider.getService();
                    helper.ossService.setApplicationContext(getApplicationContext());
                    helper.Init();
                    int bufferSize;
                    byte[] buffer = null;
                    String fileName = saved_local_path_dir + finalR;
                    File sourceFile = new File(fileName);

                    try
                    {
                        fileInputStream = new FileInputStream(sourceFile);
                        bufferSize = fileInputStream.available();
                        buffer = new byte[bufferSize];
                        fileInputStream.read(buffer, 0, bufferSize);
                    } catch (Exception e)
                    {
//                        Log.e("Upload file to server Exception", "Exception : " + e.getMessage(), e);
                    }
                    helper.PutObject(mQuestionItemSet.folderName + finalR, buffer);
                    deleteTempFile(fileName);
                } catch (Exception e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                BtnStop.setEnabled(false);
                break;
            case R.id.hoopervisualGetNext:
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
                intent.setClass(HooperVisualActivity.this, mQuestionItemSet.GetActivity());
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
                intent2.setClass(HooperVisualActivity.this, EndActivity.class);
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
                intent3.setClass(HooperVisualActivity.this, Content2.class);
                startActivity(intent3);
                finish();
                break;
        }

    }

}
