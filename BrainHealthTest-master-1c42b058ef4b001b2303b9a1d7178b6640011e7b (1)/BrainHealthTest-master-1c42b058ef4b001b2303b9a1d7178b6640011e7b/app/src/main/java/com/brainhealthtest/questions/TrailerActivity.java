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
import android.widget.TableRow;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.brainhealthtest.R;
import com.brainhealthtest.osshelper.Helper;
import com.brainhealthtest.utility.Content2;
import com.brainhealthtest.utility.QuestionItemSet;
import com.brainhealthtest.utility.TrailerQuestion;

import java.io.File;
import java.io.FileInputStream;


public class TrailerActivity extends Activity implements OnClickListener
{
    public static String saved_local_path_dir = Environment.getExternalStorageDirectory().getPath() + "/";
    QuestionItemSet mQuestionItemSet;
    TrailerQuestion trailerQuestion;
    MediaRecorder recorder = new MediaRecorder();
    MediaPlayer mediaPlayer = new MediaPlayer();
    private FileInputStream fileInputStream;
    Button nextButton;
    Helper helper = new Helper();


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
        setContentView(R.layout.trailer);
        InitView();
    }

    private void InitView()
    {
        mQuestionItemSet = (QuestionItemSet) getApplicationContext();
        trailerQuestion = (TrailerQuestion) ((mQuestionItemSet.GetQuestion()));
        TrailerView trailers = (TrailerView) findViewById(R.id.trailer1);
        trailers.Init(trailerQuestion, helper);
        Button btn = (Button) findViewById(R.id.trailGetNext);
        btn.setOnClickListener(this);
        Button BtnFinal = (Button) findViewById(R.id.btnFINISH);
        BtnFinal.setOnClickListener(this);
        Button BtnBack = (Button) findViewById(R.id.btnBACK);
        BtnBack.setOnClickListener(this);


        if (trailerQuestion.num == 1)
        {
            trailers.desView.setVisibility(View.GONE);
            //trailers.playBtn.setText("record");
            trailers.playBtn.setVisibility(View.GONE);
            TextView tmpView = (TextView) findViewById(R.id.down);
            tmpView.setText("NP012");

            tmpView = (TextView) findViewById(R.id.description);
            tmpView.setText("VERBAL PAIRED ASSOCIATES (WMS), Delayed Recall");

            TableRow row = (TableRow) findViewById(R.id.row3);
            row.setVisibility(View.GONE);

            final String finalR = trailerQuestion.record;

            trailers.recordBtn.setOnClickListener(new OnClickListener()
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

            trailers.stopBtn.setOnClickListener(new OnClickListener()
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
                            //helper.PutObject(mQuestionItemSet.folderName + finalR, buffer);
                        } catch (Exception e)
                        {
                            //Log.e("Upload file to server Exception", "Exception : " + e.getMessage(), e);
                        }
                        helper.PutObject(mQuestionItemSet.folderName + finalR, buffer);
                        deleteTempFile(fileName);
                        ((TrailerView) findViewById(R.id.trailer1)).Save(helper);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            });

            return;
        }

        final String t1 = trailerQuestion.voiceFile;

        trailers.playBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mediaPlayer = new MediaPlayer();
                int rtn = getResources().getIdentifier(t1, "raw", getPackageName());

                mediaPlayer = MediaPlayer.create(getBaseContext(), rtn);
                mediaPlayer.start();

            }
        });

        final String finalR = trailerQuestion.record;

        trailers.recordBtn.setOnClickListener(new OnClickListener()
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

        trailers.stopBtn.setOnClickListener(new OnClickListener()
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
                        //helper.PutObject(mQuestionItemSet.folderName + finalR, buffer);
                    } catch (Exception e)
                    {
                        //Log.e("Upload file to server Exception", "Exception : " + e.getMessage(), e);
                    }
                    helper.PutObject(mQuestionItemSet.folderName + finalR, buffer);
                    deleteTempFile(fileName);
                    ((TrailerView) findViewById(R.id.trailer1)).Save(helper);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });


        mQuestionItemSet.questionId++;
        trailerQuestion = (TrailerQuestion) ((mQuestionItemSet.GetQuestion()));
        final String t2 = trailerQuestion.voiceFile;
        trailers = (TrailerView) findViewById(R.id.trailer2);
        trailers.Init(trailerQuestion, helper);
        trailers.playBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mediaPlayer = new MediaPlayer();
                int rtn = getResources().getIdentifier(t2, "raw", getPackageName());

                mediaPlayer = MediaPlayer.create(getBaseContext(), rtn);
                mediaPlayer.start();

            }
        });

        final String finalR1 = trailerQuestion.record;

        trailers.recordBtn.setOnClickListener(new OnClickListener()
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

                    recorder.setOutputFile(saved_local_path_dir + finalR1);
                    recorder.prepare();
                    recorder.start();
                } catch (Exception e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });

        trailers.stopBtn.setOnClickListener(new OnClickListener()
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
                    String fileName = saved_local_path_dir + finalR1;

                    int bufferSize;
                    byte[] buffer = null;
                    File sourceFile = new File(fileName);

                    try
                    {
                        fileInputStream = new FileInputStream(sourceFile);
                        bufferSize = fileInputStream.available();
                        buffer = new byte[bufferSize];
                        fileInputStream.read(buffer, 0, bufferSize);
                        //helper.PutObject(mQuestionItemSet.folderName + finalR1, buffer);
                    } catch (Exception e)
                    {
                        //Log.e("Upload file to server Exception", "Exception : " + e.getMessage(), e);
                    }
                    helper.PutObject(mQuestionItemSet.folderName + finalR1, buffer);
                    deleteTempFile(fileName);
                    ((TrailerView) findViewById(R.id.trailer1)).Save(helper);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
        mQuestionItemSet.questionId++;

        trailerQuestion = (TrailerQuestion) ((mQuestionItemSet.GetQuestion()));
        final String t3 = trailerQuestion.voiceFile;
        trailers = (TrailerView) findViewById(R.id.trailer3);
        trailers.Init(trailerQuestion, helper);
        trailers.playBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mediaPlayer = new MediaPlayer();
                int rtn = getResources().getIdentifier(t3, "raw", getPackageName());

                mediaPlayer = MediaPlayer.create(getBaseContext(), rtn);
                mediaPlayer.start();

            }
        });

        final String finalR2 = trailerQuestion.record;

        trailers.recordBtn.setOnClickListener(new OnClickListener()
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

                    recorder.setOutputFile(finalR2);
                    recorder.prepare();
                    recorder.start();
                } catch (Exception e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });

        trailers.stopBtn.setOnClickListener(new OnClickListener()
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
                    String fileName = finalR2;

                    int bufferSize;
                    byte[] buffer = null;
                    File sourceFile = new File(fileName);

                    try
                    {
                        fileInputStream = new FileInputStream(sourceFile);
                        bufferSize = fileInputStream.available();
                        buffer = new byte[bufferSize];
                        fileInputStream.read(buffer, 0, bufferSize);
                        //helper.PutObject(mQuestionItemSet.folderName + finalR2, buffer);
                    } catch (Exception e)
                    {
                        //Log.e("Upload file to server Exception", "Exception : " + e.getMessage(), e);
                    }
                    helper.PutObject(mQuestionItemSet.folderName + finalR2, buffer);
                    deleteTempFile(fileName);
                    ((TrailerView) findViewById(R.id.trailer1)).Save(helper);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onClick(View v)
    {
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
        if (checkBox.isChecked())
        {
            trailerQuestion.skip = true;
            for (int i = 0; i < trailerQuestion.skipQues.size(); ++i)
                mQuestionItemSet.questionItemSet.get(trailerQuestion.skipQues.get(i)).skip = true;
        } else
            trailerQuestion.skip = false;
        if (trailerQuestion.num == 1)
        {
            ((TrailerQuestion) (mQuestionItemSet.GetQuestion())).GetAnswer((TrailerView) findViewById(R.id.trailer1));

        } else
        {
            int index = mQuestionItemSet.questionId;
            ((TrailerQuestion) (mQuestionItemSet.GetQuestion(index - 2))).GetAnswer((TrailerView) findViewById(R.id.trailer1));
            ((TrailerQuestion) (mQuestionItemSet.GetQuestion(index - 1))).GetAnswer((TrailerView) findViewById(R.id.trailer2));
            ((TrailerQuestion) (mQuestionItemSet.GetQuestion(index - 0))).GetAnswer((TrailerView) findViewById(R.id.trailer3));
            ((TrailerView) findViewById(R.id.trailer1)).Save(helper);
            ((TrailerView) findViewById(R.id.trailer2)).Save(helper);
            ((TrailerView) findViewById(R.id.trailer3)).Save(helper);
        }
        switch (v.getId())
        {
            case R.id.trailGetNext:

                Helper helper = new Helper();
                helper.ossService = OSSServiceProvider.getService();
                helper.ossService.setApplicationContext(getApplicationContext());
                helper.Init();
                try
                {
                    String json1 = mQuestionItemSet.Save1(helper.GetObject(mQuestionItemSet.folderName + "tester.json"), mQuestionItemSet.questionId - 2);
                    byte[] data1 = json1.getBytes();
                    helper.PutObject(mQuestionItemSet.folderName + "tester.json", data1);
                    String json2 = mQuestionItemSet.Save1(helper.GetObject(mQuestionItemSet.folderName + "tester.json"), mQuestionItemSet.questionId - 1);
                    byte[] data2 = json2.getBytes();
                    helper.PutObject(mQuestionItemSet.folderName + "tester.json", data2);
                    String json3 = mQuestionItemSet.Save1(helper.GetObject(mQuestionItemSet.folderName + "tester.json"), mQuestionItemSet.questionId);
                    byte[] data3 = json3.getBytes();
                    helper.PutObject(mQuestionItemSet.folderName + "tester.json", data3);
                } catch (OSSException e)
                {
                    e.printStackTrace();
                }

                mQuestionItemSet.questionId++;
                while (mQuestionItemSet.questionId < mQuestionItemSet.questionItemSet.size() && mQuestionItemSet.questionItemSet.get(mQuestionItemSet.questionId).skip)
                    mQuestionItemSet.questionId++;
                Intent intent = new Intent();
                intent.setClass(TrailerActivity.this, mQuestionItemSet.GetActivity());
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
                    String json1 = mQuestionItemSet.Save1(helper2.GetObject(mQuestionItemSet.folderName + "tester.json"), mQuestionItemSet.questionId - 2);
                    byte[] data1 = json1.getBytes();
                    helper2.PutObject(mQuestionItemSet.folderName + "tester.json", data1);
                    String json2 = mQuestionItemSet.Save1(helper2.GetObject(mQuestionItemSet.folderName + "tester.json"), mQuestionItemSet.questionId - 1);
                    byte[] data2 = json2.getBytes();
                    helper2.PutObject(mQuestionItemSet.folderName + "tester.json", data2);
                    String json3 = mQuestionItemSet.Save1(helper2.GetObject(mQuestionItemSet.folderName + "tester.json"), mQuestionItemSet.questionId);
                    byte[] data3 = json3.getBytes();
                    helper2.PutObject(mQuestionItemSet.folderName + "tester.json", data3);
                } catch (OSSException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Intent intent2 = new Intent();
                intent2.setClass(TrailerActivity.this, EndActivity.class);
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
                    String json1 = mQuestionItemSet.Save1(helper3.GetObject(mQuestionItemSet.folderName + "tester.json"), mQuestionItemSet.questionId - 2);
                    byte[] data1 = json1.getBytes();
                    helper3.PutObject(mQuestionItemSet.folderName + "tester.json", data1);
                    String json2 = mQuestionItemSet.Save1(helper3.GetObject(mQuestionItemSet.folderName + "tester.json"), mQuestionItemSet.questionId - 1);
                    byte[] data2 = json2.getBytes();
                    helper3.PutObject(mQuestionItemSet.folderName + "tester.json", data2);
                    String json3 = mQuestionItemSet.Save1(helper3.GetObject(mQuestionItemSet.folderName + "tester.json"), mQuestionItemSet.questionId);
                    byte[] data3 = json3.getBytes();
                    helper3.PutObject(mQuestionItemSet.folderName + "tester.json", data3);
                } catch (OSSException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Intent intent3 = new Intent();
                intent3.setClass(TrailerActivity.this, Content2.class);
                startActivity(intent3);
                finish();
                break;
        }
    }

}  