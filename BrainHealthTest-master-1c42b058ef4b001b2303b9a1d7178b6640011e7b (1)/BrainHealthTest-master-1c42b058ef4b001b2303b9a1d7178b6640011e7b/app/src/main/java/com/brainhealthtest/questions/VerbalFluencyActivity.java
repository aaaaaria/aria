package com.brainhealthtest.questions;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.brainhealthtest.R;
import com.brainhealthtest.osshelper.Helper;
import com.brainhealthtest.utility.Content2;
import com.brainhealthtest.utility.JsonParser;
import com.brainhealthtest.utility.QuestionItemSet;
import com.brainhealthtest.utility.Verbal_fluencyQuestion;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;


public class VerbalFluencyActivity extends Activity implements OnClickListener
{

    private String voiceResult = "";
    private TextView text_result;
    private SpeechRecognizer mIat;
    private RecognizerDialog mIatDialog;
    private Toast mToast;
    private String fileName = "";
    private String fileName2 = "";
    private String save = "";
    private String save2 = "";
    private String name = "";
    private String save_path = "";
    private FileInputStream fileInputStream;
    private FileInputStream fileInputStream2;

    public static String saved_local_path_dir = Environment.getExternalStorageDirectory().getPath() + "/";
    QuestionItemSet mQuestionItemSet;
    Verbal_fluencyQuestion verbal_fluencyQuestion;/////////////test
    MediaRecorder recorder = new MediaRecorder();
    MediaPlayer mediaPlayer = new MediaPlayer();
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
        setContentView(R.layout.verbal_fluency);

        StringBuffer param = new StringBuffer();
        param.append("appid=55a47632");
        param.append(",");
        param.append(SpeechConstant.ENGINE_MODE + "=" +
                SpeechConstant.MODE_AUTO);
        SpeechUtility.createUtility(this, param.toString());
        mIat = SpeechRecognizer.createRecognizer(this, null);
        mIatDialog = new RecognizerDialog(this, null);
        // iatDialog = new RecognizerDialog(this,mInitListener);
        mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        InitView();
    }

    private void InitView()
    {
        mQuestionItemSet = (QuestionItemSet) getApplicationContext();
        verbal_fluencyQuestion = (Verbal_fluencyQuestion) ((mQuestionItemSet.GetQuestion()));
        VerbalFluencyView verbal_fluency = (VerbalFluencyView) findViewById(R.id.verbal_fluency1);///////////
        verbal_fluency.Init(verbal_fluencyQuestion, helper);
        Button btn = (Button) findViewById(R.id.verbal_fluencyGetNext);
        btn.setOnClickListener(this);
        Button BtnFinal = (Button) findViewById(R.id.btnFINISH);
        BtnFinal.setOnClickListener(this);
        Button BtnBack = (Button) findViewById(R.id.btnBACK);
        BtnBack.setOnClickListener(this);
        text_result = (TextView) this.findViewById(R.id.voicetxt);


        if (verbal_fluencyQuestion.num == 1)
        {
            fileName = Environment.getExternalStorageDirectory() + "/annazheng/verbal_fluencyQuestion_animal.pcm";
            fileName2 = Environment.getExternalStorageDirectory() + "/annazheng/verbal_fluencyQuestion_animal.txt";
            save = "verbal_fluencyQuestion_animal.pcm";
            save2 = "verbal_fluencyQuestion_animal.txt";
            name = "verbal_fluencyQuestion_animal";
            save_path = "/annazheng/verbal_fluencyQuestion_animal.pcm";

            final String finalR = verbal_fluencyQuestion.record;

            verbal_fluency.recordBtn.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    v.setEnabled(false);
                    try
                    {
                        getVoice();
                    } catch (Exception e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            });

            verbal_fluency.stopBtn.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    v.setEnabled(false);
                    try
                    {
                        Helper helper = new Helper();
                        helper.ossService = OSSServiceProvider.getService();
                        helper.ossService.setApplicationContext(getApplicationContext());
                        helper.Init();
                        int bufferSize;
                        int bufferSize2;
                        byte[] buffer = null;
                        byte[] buffer2 = null;
                        File sourceFile = new File(fileName);
                        File sourceFile2 = new File(fileName2);

                        try
                        {
                            fileInputStream = new FileInputStream(sourceFile);
                            bufferSize = fileInputStream.available();
                            buffer = new byte[bufferSize];
                            fileInputStream.read(buffer, 0, bufferSize);
                            helper.PutObject(mQuestionItemSet.folderName + save, buffer);

                            fileInputStream2 = new FileInputStream(sourceFile2);
                            bufferSize2 = fileInputStream2.available();
                            buffer2 = new byte[bufferSize2];
                            fileInputStream2.read(buffer2, 0, bufferSize2);
                            helper.PutObject(mQuestionItemSet.folderName + save2, buffer2);
                        } catch (Exception e)
                        {
//                            Log.e("Upload file to server Exception", "Exception : " + e.getMessage(), e);
                        }
                        deleteTempFile(fileName);
                        deleteTempFile(fileName2);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }


                }
            });

            return;
        }

        final String finalR = verbal_fluencyQuestion.record;

        verbal_fluency.recordBtn.setOnClickListener(new OnClickListener()
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

        verbal_fluency.stopBtn.setOnClickListener(new OnClickListener()
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
                    ((VerbalFluencyView) findViewById(R.id.verbal_fluency1)).Save(helper);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });


        mQuestionItemSet.questionId++;
        verbal_fluencyQuestion = (Verbal_fluencyQuestion) ((mQuestionItemSet.GetQuestion()));
        verbal_fluency = (VerbalFluencyView) findViewById(R.id.verbal_fluency2);
        verbal_fluency.Init(verbal_fluencyQuestion, helper);

        final String finalR1 = verbal_fluencyQuestion.record;

        verbal_fluency.recordBtn.setOnClickListener(new OnClickListener()
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

        verbal_fluency.stopBtn.setOnClickListener(new OnClickListener()
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
                    ((VerbalFluencyView) findViewById(R.id.verbal_fluency2)).Save(helper);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
        mQuestionItemSet.questionId++;

        verbal_fluencyQuestion = (Verbal_fluencyQuestion) ((mQuestionItemSet.GetQuestion()));
        verbal_fluency = (VerbalFluencyView) findViewById(R.id.verbal_fluency3);
        verbal_fluency.Init(verbal_fluencyQuestion, helper);

        final String finalR2 = verbal_fluencyQuestion.record;

        verbal_fluency.recordBtn.setOnClickListener(new OnClickListener()
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

                    recorder.setOutputFile(saved_local_path_dir + finalR2);
                    recorder.prepare();
                    recorder.start();
                } catch (Exception e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });

        verbal_fluency.stopBtn.setOnClickListener(new OnClickListener()
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
                    String fileName = saved_local_path_dir + finalR2;

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
                    helper.PutObject(mQuestionItemSet.folderName + finalR2, buffer);
                    deleteTempFile(fileName);
                    ((VerbalFluencyView) findViewById(R.id.verbal_fluency3)).Save(helper);
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
        // TODO Auto-generated method stub
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
        if (checkBox.isChecked())
        {
            verbal_fluencyQuestion.skip = true;
            for (int i = 0; i < verbal_fluencyQuestion.skipQues.size(); ++i)
                mQuestionItemSet.questionItemSet.get(verbal_fluencyQuestion.skipQues.get(i)).skip = true;
        } else
            verbal_fluencyQuestion.skip = false;
        if (verbal_fluencyQuestion.num == 1)
        {
            ((Verbal_fluencyQuestion) (mQuestionItemSet.GetQuestion())).GetAnswer((VerbalFluencyView) findViewById(R.id.verbal_fluency1));
        } else
        {
            int index = mQuestionItemSet.questionId;
            ((Verbal_fluencyQuestion) (mQuestionItemSet.GetQuestion(index - 2))).GetAnswer((VerbalFluencyView) findViewById(R.id.verbal_fluency1));
            ((Verbal_fluencyQuestion) (mQuestionItemSet.GetQuestion(index - 1))).GetAnswer((VerbalFluencyView) findViewById(R.id.verbal_fluency2));
            ((Verbal_fluencyQuestion) (mQuestionItemSet.GetQuestion(index - 0))).GetAnswer((VerbalFluencyView) findViewById(R.id.verbal_fluency3));
            ((VerbalFluencyView) findViewById(R.id.verbal_fluency1)).Save(helper);
            ((VerbalFluencyView) findViewById(R.id.verbal_fluency2)).Save(helper);
            ((VerbalFluencyView) findViewById(R.id.verbal_fluency3)).Save(helper);
        }
        switch (v.getId())
        {
            case R.id.verbal_fluencyGetNext:


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
                intent.setClass(VerbalFluencyActivity.this, mQuestionItemSet.GetActivity());
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
                intent2.setClass(VerbalFluencyActivity.this, EndActivity.class);
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
                intent3.setClass(VerbalFluencyActivity.this, Content2.class);
                startActivity(intent3);
                finish();
                break;
        }
    }

    /**
     */
    private InitListener mInitListener = new InitListener()
    {
        public void onInit(int code)
        {
            Log.d("aliyun", "anna_0");
            if (code == ErrorCode.SUCCESS)
            {
                Toast.makeText(VerbalFluencyActivity.this, "init success", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
// Inflate the menu; this adds items to the action bar if it is present. 
        getMenuInflater().inflate(R.menu.reco, menu);
        return true;
    }


    public void getVoice()
    {

        setParam();

        mIat.startListening(recognizerListener);
    }

    public void setParam()
    {
        mIat.setParameter(SpeechConstant.LANGUAGE, "en_us"); /////////////////
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);///

        mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory() + save_path);
        mIat.setParameter(SpeechConstant.VAD_EOS, "5000");
    }


    private RecognizerListener recognizerListener = new RecognizerListener()
    {
        @Override
        public void onVolumeChanged(int i, byte[] bytes)
        {

        }

        @Override
        public void onBeginOfSpeech()
        {
            showTip("Start Speaking");
        }

        @Override
        public void onEndOfSpeech()
        {
            showTip("Stop Speaking");
        }

        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b)
        {
            String text = JsonParser.parseIatResult(recognizerResult.getResultString());
            voiceResult = voiceResult + text;
            if (b)
            {
                text_result.setText(voiceResult);
                put(voiceResult, name);
            }

        }

        @Override
        public void onError(SpeechError speechError)
        {
            showTip(speechError.getPlainDescription(true));
        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle)
        {

        }

    };

    public static void put(String s, String name)
    {
        try
        {
            FileOutputStream outStream = new FileOutputStream(Environment.getExternalStorageDirectory() + "/annazheng/" + name + ".txt", true);
            OutputStreamWriter writer = new OutputStreamWriter(outStream, "gb2312");
            writer.write(s);
            writer.write("/n");
            writer.flush();
            writer.close();

            outStream.close();
        } catch (Exception e)
        {
            Log.e("m", "file write error");
        }
    }

    private void showTip(final String str)
    {
        mToast.setText(str);
        mToast.show();
    }


}
