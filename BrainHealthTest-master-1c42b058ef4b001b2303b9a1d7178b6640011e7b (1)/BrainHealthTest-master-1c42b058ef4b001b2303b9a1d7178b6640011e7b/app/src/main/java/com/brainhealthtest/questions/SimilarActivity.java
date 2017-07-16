package com.brainhealthtest.questions;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.brainhealthtest.R;
import com.brainhealthtest.osshelper.Helper;
import com.brainhealthtest.utility.Content2;
import com.brainhealthtest.utility.QuestionItemSet;
import com.brainhealthtest.utility.SimilarQuestion;

import java.io.File;
import java.io.FileInputStream;
import java.util.Timer;
import java.util.TimerTask;


public class SimilarActivity extends Activity implements OnClickListener
{
    public static String saved_local_path_dir = Environment.getExternalStorageDirectory().getPath() + "/";
    QuestionItemSet mQuestionItemSet;
    SimilarQuestion similarQuestion;
    SimilarView similars;
    MediaRecorder recorder = new MediaRecorder();
    MediaPlayer mediaPlayer = new MediaPlayer();
    private FileInputStream fileInputStream;
    Helper helper = new Helper();
    private String answerJSON;

    private final Timer timer = new Timer();
    private TimerTask task;
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            // TODO Auto-generated method stub
            ((SimilarQuestion) (mQuestionItemSet.GetQuestion())).GetTempAnswer((SimilarView) findViewById(R.id.similar1));

            super.handleMessage(msg);
        }
    };

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
    protected void onCreate(Bundle savedInstanceState)
    {
        helper.ossService = OSSServiceProvider.getService();
        helper.ossService.setApplicationContext(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.similarities);
        InitView();
        timer.schedule(task, 2000, 1000);

    }

    private void InitView()
    {
        mQuestionItemSet = (QuestionItemSet) getApplicationContext();
        similarQuestion = (SimilarQuestion) ((mQuestionItemSet.GetQuestion()));
        similars = (SimilarView) findViewById(R.id.similar1);///////////
        similars.Init(similarQuestion, helper);
        Button btn = (Button) findViewById(R.id.similarGetNext);
        btn.setOnClickListener(this);
        Button BtnFinal = (Button) findViewById(R.id.btnFINISH);
        BtnFinal.setOnClickListener(this);
        Button BtnBack = (Button) findViewById(R.id.btnBACK);
        BtnBack.setOnClickListener(this);


        final String finalR = similarQuestion.record;

        similars.recordBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                v.setEnabled(false);
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

            }
        });

        similars.stopBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                v.setEnabled(false);
                try
                {
                    recorder.stop();
                    recorder.reset();
                    recorder.release();

                    helper.Init();
                    String fileName = saved_local_path_dir + finalR;

                    int bufferSize;
                    byte[] buffer = null;
                    File sourceFile = new File(fileName);

                    try
                    {
                        fileInputStream = new FileInputStream(sourceFile);
                        bufferSize = fileInputStream.available();
                        buffer = new byte[bufferSize];
                        fileInputStream.read(buffer, 0, bufferSize);
                    } catch (Exception e)
                    {
                        //Log.e("Upload file to server Exception", "Exception : " + e.getMessage(), e);
                    }
                    helper.PutObject(mQuestionItemSet.folderName + finalR, buffer);
                    deleteTempFile(fileName);
                    ((SimilarView) findViewById(R.id.similar1)).Save(helper);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
        task = new TimerTask()
        {
            @Override
            public void run()
            {
                // TODO Auto-generated method stub  
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };

    }


    @Override
    public void onClick(View v)
    {
        timer.cancel();
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
        if (checkBox.isChecked())
        {
            similarQuestion.skip = true;
            for (int i = 0; i < similarQuestion.skipQues.size(); ++i)
                mQuestionItemSet.questionItemSet.get(similarQuestion.skipQues.get(i)).skip = true;
        } else
            similarQuestion.skip = false;
        ((SimilarQuestion) (mQuestionItemSet.GetQuestion())).GetAnswer((SimilarView) findViewById(R.id.similar1));

        switch (v.getId())
        {

            case R.id.similarGetNext:
                ((SimilarView) findViewById(R.id.similar1)).Save(helper);

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
                intent.setClass(SimilarActivity.this, mQuestionItemSet.GetActivity());
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
                intent2.setClass(SimilarActivity.this, EndActivity.class);
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
                intent3.setClass(SimilarActivity.this, Content2.class);
                startActivity(intent3);
                finish();
                break;
        }
    }

}  