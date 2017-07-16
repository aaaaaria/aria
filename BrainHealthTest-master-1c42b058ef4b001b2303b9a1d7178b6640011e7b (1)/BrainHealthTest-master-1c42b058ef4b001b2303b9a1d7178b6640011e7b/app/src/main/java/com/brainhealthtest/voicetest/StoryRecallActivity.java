package com.brainhealthtest.voicetest;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.brainhealthtest.R;
import com.brainhealthtest.osshelper.Helper;
import com.brainhealthtest.questions.EndActivity;
import com.brainhealthtest.utility.Content2;
import com.brainhealthtest.utility.JsonParser;
import com.brainhealthtest.utility.QuestionItemSet;
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


public class StoryRecallActivity extends Activity implements OnClickListener
{

    // 语音听写对象
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

    private VideoView videoView;

    public static String saved_local_path_dir = Environment.getExternalStorageDirectory().getPath() + "/";
    QuestionItemSet mQuestionItemSet;
    StoryRecallQuestion question;
    Boolean flag;
    MediaPlayer mediaPlayer = new MediaPlayer();

    /**
     * 鐣岄潰甯冨眬鍏冪礌
     **/
    private Button BtnStart, BtnRecord, BtnStop, BtnNext, BtnFinal, BtnBack;
    private FileInputStream fileInputStream;
    private FileInputStream fileInputStream2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mQuestionItemSet = (QuestionItemSet) getApplicationContext();
        question = (StoryRecallQuestion) mQuestionItemSet.GetQuestion();

        StringBuffer param = new StringBuffer();
        //初始化
        param.append("appid=55a47632");
        param.append(",");
        param.append(SpeechConstant.ENGINE_MODE + "=" + SpeechConstant.MODE_AUTO);
        SpeechUtility.createUtility(this, param.toString());
        mIat = SpeechRecognizer.createRecognizer(this, null);
        mIatDialog = new RecognizerDialog(this, null);
        // iatDialog = new RecognizerDialog(this,mInitListener);
        mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);


        if (question.delayed == 1)
        {
            setContentView(R.layout.story_later);
            BtnStop = (Button) findViewById(R.id.storyStopBtn);
            BtnRecord = (Button) findViewById(R.id.storyRecordBtn);
            BtnRecord.setOnClickListener(this);
            BtnStop.setOnClickListener(this);
            BtnNext = (Button) findViewById(R.id.btnStoryNext);
            BtnNext.setOnClickListener(this);
            BtnFinal = (Button) findViewById(R.id.btnFINISH);
            BtnFinal.setOnClickListener(this);
            BtnBack = (Button) findViewById(R.id.btnBACK);
            BtnBack.setOnClickListener(this);
            BtnStop.setEnabled(false);
            BtnRecord.setEnabled(true);
            text_result = (TextView) this.findViewById(R.id.voicetxt);
            fileName = Environment.getExternalStorageDirectory() + "/annazheng/story_delayed_recall.pcm";
            fileName2 = Environment.getExternalStorageDirectory() + "/annazheng/reco_delayed_result.txt";
            save = "story_delayed_recall.pcm";
            save2 = "reco_delayed_result.txt";
            name = "reco_delayed_result";
            save_path = "/annazheng/story_delayed_recall.pcm";
        } else   /*若不延迟*/
        {
            setContentView(R.layout.story_tester);
            InitView();
            fileName = Environment.getExternalStorageDirectory() + "/annazheng/story_immediate_recall.pcm";
            fileName2 = Environment.getExternalStorageDirectory() + "/annazheng/reco_immediate_result.txt";
            save = "story_immediate_recall.pcm";
            save2 = "reco_immediate_result.txt";
            name = "reco_immediate_result";
            save_path = "/annazheng/story_immediate_recall.pcm";

        }
    }

    private void InitView()
    {
        BtnStart = (Button) findViewById(R.id.storyStartBtn);
        BtnStop = (Button) findViewById(R.id.storyStopBtn);
        BtnRecord = (Button) findViewById(R.id.storyRecordBtn);
        BtnRecord.setOnClickListener(this);
        BtnStop.setOnClickListener(this);
        BtnStart.setOnClickListener(this);
        BtnNext = (Button) findViewById(R.id.btnStoryNext);
        BtnNext.setOnClickListener(this);
        BtnFinal = (Button) findViewById(R.id.btnFINISH);
        BtnFinal.setOnClickListener(this);
        BtnBack = (Button) findViewById(R.id.btnBACK);
        BtnBack.setOnClickListener(this);
        BtnStart.setEnabled(true);
        BtnStop.setEnabled(false);
        BtnRecord.setEnabled(false);
        text_result = (TextView) this.findViewById(R.id.voicetxt);


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
      
    /* 
     * 澶勭悊Click浜嬩欢 
     */

    public void onClick(View v)
    {
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
        if (checkBox.isChecked())
        {
            question.skip = true;
            for (int i = 0; i < question.skipQues.size(); ++i)
                mQuestionItemSet.questionItemSet.get(question.skipQues.get(i)).skip = true;
        } else
            question.skip = false;
        switch (v.getId())
        {
            case R.id.storyStartBtn:

                videoView = (VideoView) findViewById(R.id.video_view);
                videoView.setVideoURI(Uri.parse("android.resource://com.brainhealthtest/" + R.raw.movie));
                videoView.setMediaController(new MediaController(this));
                videoView.start();

                BtnStart.setEnabled(false);
                BtnRecord.setEnabled(true);
                break;
            case R.id.storyRecordBtn:
                // TODO record and save
                BtnRecord.setEnabled(false);
                BtnStop.setEnabled(true);
                try
                {
                    getVoice();
                } catch (Exception e)
                {
                }
                break;
            case R.id.storyStopBtn:
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
//                        Log.e("Upload file to server Exception", "Exception : " + e.getMessage(), e);
                    }
                    deleteTempFile(fileName);
                    deleteTempFile(fileName2);
                } catch (Exception e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                BtnStop.setEnabled(false);
                break;
            case R.id.btnStoryNext:
                Helper helper = new Helper();
                helper.ossService = OSSServiceProvider.getService();
                helper.ossService.setApplicationContext(getApplicationContext());
                helper.Init();
                try
                {
                    String json2 = mQuestionItemSet.Save1(helper.GetObject(mQuestionItemSet.folderName + "tester.json"), mQuestionItemSet.questionId);
                    byte[] data = json2.getBytes();
                    helper.PutObject(mQuestionItemSet.folderName + "tester.json", data);
                } catch (OSSException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                mQuestionItemSet.questionId++;
                while (mQuestionItemSet.questionId < mQuestionItemSet.questionItemSet.size() && mQuestionItemSet.questionItemSet.get(mQuestionItemSet.questionId).skip)
                    mQuestionItemSet.questionId++;
                Intent intent = new Intent();
                intent.setClass(StoryRecallActivity.this, mQuestionItemSet.GetActivity());
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
                    byte[] data = json2.getBytes();
                    helper2.PutObject(mQuestionItemSet.folderName + "tester.json", data);
                } catch (OSSException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Intent intent2 = new Intent();
                intent2.setClass(StoryRecallActivity.this, EndActivity.class);
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
                intent3.setClass(StoryRecallActivity.this, Content2.class);
                startActivity(intent3);
                finish();
                break;
        }
    }
///////////////////////////////////////////////////////////////////////讯飞选项
    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener()
    {
        @Override
        public void onInit(int code)
        {
            Log.d("aliyun", "anna_0");
            if (code == ErrorCode.SUCCESS)
            {
                Toast.makeText(StoryRecallActivity.this, "init success", Toast.LENGTH_SHORT).show();
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
        // 设置参数
        setParam();
        // 不显示听写对话框
        mIat.startListening(recognizerListener);
    }

    public void setParam()
    {
        mIat.setParameter(SpeechConstant.LANGUAGE, "en_us");
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory() + save_path);
        mIat.setParameter(SpeechConstant.VAD_EOS, "5000");
    }

    /**
     * 听写监听器
     */

    private RecognizerListener recognizerListener = new RecognizerListener()
    {
        @Override
        public void onVolumeChanged(int i, byte[] bytes)
        {

        }

        @Override
        public void onBeginOfSpeech()
        {
            // 此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入
            showTip("开始说话");
        }

        @Override
        public void onError(SpeechError error)
        {
            // Tips：
            // 错误码：10118(您没有说话)，可能是录音机权限被禁，需要提示用户打开应用的录音权限。
            // 如果使用本地功能（语记）需要提示用户开启语记的录音权限。
            showTip(error.getPlainDescription(true));
        }

        @Override
        public void onEndOfSpeech()
        {
            // 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
            showTip("结束说话");
        }

        @Override
        public void onResult(RecognizerResult results, boolean isLast)
        {
            String text = JsonParser.parseIatResult(results.getResultString());
            voiceResult = voiceResult + text;
            if (isLast)
            {
                text_result.setText(voiceResult);
                put(voiceResult, name);
            }
        }

        @Override
        public void onEvent(int arg0, int arg1, int arg2, Bundle arg3)
        {
            // TODO Auto-generated method stub
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
            writer.close();//记得关闭

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