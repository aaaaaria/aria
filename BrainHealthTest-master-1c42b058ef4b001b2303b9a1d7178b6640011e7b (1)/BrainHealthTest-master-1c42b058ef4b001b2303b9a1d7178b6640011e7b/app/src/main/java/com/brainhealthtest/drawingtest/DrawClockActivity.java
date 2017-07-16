package com.brainhealthtest.drawingtest;

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
import com.brainhealthtest.demo.activity.SelectActivity;
import com.brainhealthtest.demo.util.SaveTofile;
import com.brainhealthtest.osshelper.Helper;
import com.brainhealthtest.questions.EndActivity;
import com.brainhealthtest.utility.Content2;
import com.brainhealthtest.utility.QuestionItemSet;

import java.io.File;
import java.io.FileInputStream;


public class DrawClockActivity extends Activity implements OnClickListener
{

    public static String saved_local_path_dir = Environment.getExternalStorageDirectory().getPath() + "/annazheng/";
    QuestionItemSet mQuestionItemSet;
    DrawClockQuestion drawclockQusetion;
    MediaRecorder recorder = new MediaRecorder();
    MediaPlayer mediaPlayer = new MediaPlayer();
    private FileInputStream fileInputStream;
    Button nextButton;
    Button drawButton;
    Helper helper = new Helper();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        helper.ossService = OSSServiceProvider.getService();
        helper.ossService.setApplicationContext(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawclock2);//drawclock
        InitView();
    }

    private void InitView()
    {
        mQuestionItemSet = (QuestionItemSet) getApplicationContext();
        drawclockQusetion = (DrawClockQuestion) ((mQuestionItemSet.GetQuestion()));
        DrawClockView clock = (DrawClockView) findViewById(R.id.drawingview);///////////
        clock.Init(drawclockQusetion, helper);
        Button btn = (Button) findViewById(R.id.drawclockNext);
        btn.setOnClickListener(this);
        Button BtnFinal = (Button) findViewById(R.id.btnFINISH);
        BtnFinal.setOnClickListener(this);
        Button BtnBack = (Button) findViewById(R.id.btnBACK);
        BtnBack.setOnClickListener(this);
        Button BtnDraw = (Button) findViewById(R.id.drawbutton);
        BtnDraw.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
        if (checkBox.isChecked())
        {
            drawclockQusetion.skip = true;
            for (int i = 0; i < drawclockQusetion.skipQues.size(); ++i)
                mQuestionItemSet.questionItemSet.get(drawclockQusetion.skipQues.get(i)).skip = true;
        }
        if (drawclockQusetion.num == 0)
        {
            ((DrawClockQuestion) (mQuestionItemSet.GetQuestion())).GetAnswer((DrawClockView) findViewById(R.id.drawingview));
        }
        if (drawclockQusetion.num == 1)
        {
            ((DrawClockQuestion) (mQuestionItemSet.GetQuestion())).GetAnswer2((DrawClockView) findViewById(R.id.drawingview));
        }
//        else
//        	drawclockQusetion.skip = false;
        switch (v.getId())
        {
            case R.id.drawbutton:
                SaveTofile.s1 = "drawclock1";
                SaveTofile.num = 7;
                Intent intent0 = new Intent();
                intent0.setClass(DrawClockActivity.this, SelectActivity.class);
                startActivity(intent0);
                break;
            case R.id.drawclockNext:
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
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                savepic_path();
                mQuestionItemSet.questionId++;
                while (mQuestionItemSet.questionId < mQuestionItemSet.questionItemSet.size() && mQuestionItemSet.questionItemSet.get(mQuestionItemSet.questionId).skip)
                    mQuestionItemSet.questionId++;
                Intent intent = new Intent();
                intent.setClass(DrawClockActivity.this, mQuestionItemSet.GetActivity());
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
                savepic_path();
                Intent intent2 = new Intent();
                intent2.setClass(DrawClockActivity.this, EndActivity.class);
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
                savepic_path();
                Intent intent3 = new Intent();
                intent3.setClass(DrawClockActivity.this, Content2.class);
                startActivity(intent3);
                finish();
                break;
        }
    }

    public int uploadFile(String picName)
    {
        String fileName = DrawClockActivity.saved_local_path_dir + picName;

        Helper helper = new Helper();
        helper.ossService = OSSServiceProvider.getService();
        helper.ossService.setApplicationContext(getApplicationContext());
        helper.Init();

        int bufferSize;
        byte[] buffer;
        File sourceFile = new File(fileName);

        try
        {
            fileInputStream = new FileInputStream(sourceFile);
            bufferSize = fileInputStream.available();
            buffer = new byte[bufferSize];
            fileInputStream.read(buffer, 0, bufferSize);
            helper.PutObject(mQuestionItemSet.folderName + picName, buffer);
        } catch (Exception e)
        {
            // Log.e("Upload file to server Exception", "Exception : " + e.getMessage(), e);
            return -1;
        }
        return 0;
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

    public void savepic_path()
    { //save picture
        uploadFile("IMG_drawclock1.jpg");
        uploadFile("Points_drawclock1.txt");
        deleteTempFile(DrawClockActivity.saved_local_path_dir + "IMG_drawclock1.jpg");
        deleteTempFile(DrawClockActivity.saved_local_path_dir + "Points_drawclock1.txt");
    }

}
