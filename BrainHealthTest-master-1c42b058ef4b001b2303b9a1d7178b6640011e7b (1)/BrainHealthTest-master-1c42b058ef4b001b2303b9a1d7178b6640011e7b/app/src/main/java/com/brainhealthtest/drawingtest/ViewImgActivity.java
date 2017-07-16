package com.brainhealthtest.drawingtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableRow;

import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.brainhealthtest.R;
import com.brainhealthtest.demo.activity.MainActivity;
import com.brainhealthtest.demo.activity.SelectActivity;
import com.brainhealthtest.demo.util.SaveTofile;
import com.brainhealthtest.osshelper.Helper;
import com.brainhealthtest.questions.EndActivity;
import com.brainhealthtest.utility.Content2;
import com.brainhealthtest.utility.QuestionItemSet;

import java.io.File;
import java.io.FileInputStream;


public class ViewImgActivity extends Activity
{

    QuestionItemSet mQuestionItemSet;
    DrawQuestion drawQuestion;
    private FileInputStream fileInputStream;
    Handler imageLoadedHandler;
    LoaderImageView showImgA, showImgB, showImgC;
    Button btnA, btnB, btnC, btnN, BtnFinal, btnDrawA, btnDrawB, btnDrawC;
    boolean downloadedA, downloadedB, downloadedC;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_show);

        mQuestionItemSet = (QuestionItemSet) getApplicationContext();
        drawQuestion = (DrawQuestion) mQuestionItemSet.GetQuestion();

        downloadedA = true;
        downloadedB = true;
        downloadedC = true;
        showImgA = (LoaderImageView) findViewById(R.id.loaderImageViewA);
        showImgA.setVisibility(View.GONE);
        showImgB = (LoaderImageView) findViewById(R.id.loaderImageViewB);
        showImgB.setVisibility(View.GONE);
        showImgC = (LoaderImageView) findViewById(R.id.loaderImageViewC);
        showImgC.setVisibility(View.GONE);

        btnA = (Button) findViewById(R.id.tryaginbtn);
        btnB = (Button) findViewById(R.id.tryaginbtnB);
        btnC = (Button) findViewById(R.id.tryaginbtnC);
        btnN = (Button) findViewById(R.id.showImgNext);
        btnDrawA = (Button) findViewById(R.id.drawbtnA);
        btnDrawB = (Button) findViewById(R.id.drawbtnB);
        btnDrawC = (Button) findViewById(R.id.drawbtnC);

        Button BtnBack = (Button) findViewById(R.id.btnBACK);
        BtnBack.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
                if (checkBox.isChecked())
                {
                    drawQuestion.skip = true;
                    for (int i = 0; i < drawQuestion.skipQues.size(); ++i)
                        mQuestionItemSet.questionItemSet.get(drawQuestion.skipQues.get(i)).skip = true;
                }
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
                intent3.setClass(ViewImgActivity.this, Content2.class);
                startActivity(intent3);
                finish();
            }

        });

        BtnFinal = (Button) findViewById(R.id.btnFINISH);
        BtnFinal.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
                if (checkBox.isChecked())
                {
                    drawQuestion.skip = true;
                    for (int i = 0; i < drawQuestion.skipQues.size(); ++i)
                        mQuestionItemSet.questionItemSet.get(drawQuestion.skipQues.get(i)).skip = true;
                }

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
                intent2.setClass(ViewImgActivity.this, EndActivity.class);
                startActivity(intent2);
                finish();

            }

        });

        btnN.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
                if (checkBox.isChecked())
                {
                    drawQuestion.skip = true;
                    for (int i = 0; i < drawQuestion.skipQues.size(); ++i)
                        mQuestionItemSet.questionItemSet.get(drawQuestion.skipQues.get(i)).skip = true;
                }

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
                Intent intent = new Intent();
                mQuestionItemSet.questionId++;
                while (mQuestionItemSet.questionId < mQuestionItemSet.questionItemSet.size() && mQuestionItemSet.questionItemSet.get(mQuestionItemSet.questionId).skip)
                    mQuestionItemSet.questionId++;
                //System.out.println(mQuestionItemSet.GetActivity().getName());
                intent.setClass(ViewImgActivity.this, mQuestionItemSet.GetActivity());
                startActivity(intent);
                finish();
            }
        });

        btnA.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                handleClick(v);
                showImgA.setVisibility(View.VISIBLE);
                int rtn = getResources().getIdentifier(drawQuestion.picNameA, "raw", getPackageName());
                showImgA.setImageDrawable(rtn);
                btnDrawA.setVisibility(View.GONE);
                btnDrawB.setVisibility(View.GONE);
                btnDrawC.setVisibility(View.GONE);
                btnN.setVisibility(View.GONE);
                BtnFinal.setVisibility(View.GONE);
                downloadedB = true;
                TableRow row = (TableRow) findViewById(R.id.row1);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row2);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row3);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row4);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row5);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row6);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row7);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row8);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row9);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row10);
                row.setVisibility(View.GONE);
            }
        });
        btnB.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                handleClick(v);
                showImgB.setVisibility(View.VISIBLE);
                int rtn = getResources().getIdentifier(drawQuestion.picNameB, "raw", getPackageName());
                showImgB.setImageDrawable(rtn);
                btnDrawA.setVisibility(View.GONE);
                btnDrawB.setVisibility(View.GONE);
                btnDrawC.setVisibility(View.GONE);
                btnN.setVisibility(View.GONE);
                BtnFinal.setVisibility(View.GONE);
                downloadedC = true;
                TableRow row = (TableRow) findViewById(R.id.row1);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row2);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row3);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row4);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row5);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row6);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row7);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row8);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row9);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row10);
                row.setVisibility(View.GONE);
            }
        });
        btnC.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                handleClick(v);
                showImgC.setVisibility(View.VISIBLE);
                btnDrawA.setVisibility(View.GONE);
                btnDrawB.setVisibility(View.GONE);
                btnDrawC.setVisibility(View.GONE);
                btnN.setVisibility(View.GONE);
                BtnFinal.setVisibility(View.GONE);
                int rtn = getResources().getIdentifier(drawQuestion.picNameC, "raw", getPackageName());
                showImgC.setImageDrawable(rtn);
                TableRow row = (TableRow) findViewById(R.id.row1);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row2);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row3);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row4);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row5);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row6);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row7);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row8);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row9);
                row.setVisibility(View.GONE);
                row = (TableRow) findViewById(R.id.row10);
                row.setVisibility(View.GONE);
            }
        });
        btnDrawA.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                SaveTofile.s1 = "A";
                SaveTofile.num = 1;
                Intent intent0 = new Intent();
                intent0.setClass(ViewImgActivity.this, SelectActivity.class);//turn to bluetooth to 1
                startActivity(intent0);
            }
        });
        btnDrawB.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                SaveTofile.s1 = "B";
                SaveTofile.num = 2;
                Intent intent0 = new Intent();
                intent0.setClass(ViewImgActivity.this, MainActivity.class);//turn to draw view 2
                startActivity(intent0);
            }
        });
        btnDrawC.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                SaveTofile.s1 = "C";
                SaveTofile.num = 3;
                Intent intent0 = new Intent();
                intent0.setClass(ViewImgActivity.this, MainActivity.class);
                startActivity(intent0);
            }
        });
    }

    public void handleClick(View tager)
    {
        if (downloadedA)
        {
            showImgA.mImage.setVisibility(View.VISIBLE);
            new CountDownTimer(10000, 1000)
            {

                public void onTick(long millisUntilFinished)
                {
                    //countdownTextView.setText(desc + millisUntilFinished / 1000);
                }

                public void onFinish()
                {
                    showImgA.mImage.setVisibility(View.GONE);
                    TableRow row = (TableRow) findViewById(R.id.row1);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row2);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row3);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row4);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row5);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row6);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row7);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row8);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row9);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row10);
                    row.setVisibility(View.VISIBLE);
                    btnDrawA.setVisibility(View.VISIBLE);
                    btnDrawB.setVisibility(View.VISIBLE);
                    btnDrawC.setVisibility(View.VISIBLE);
                    btnN.setVisibility(View.VISIBLE);
                    BtnFinal.setVisibility(View.VISIBLE);
                }
            }.start();
        }
        if (downloadedB)
        {
            showImgB.mImage.setVisibility(View.VISIBLE);
            new CountDownTimer(10000, 1000)
            {

                public void onTick(long millisUntilFinished)
                {
                    //countdownTextView.setText(desc + millisUntilFinished / 1000);
                }

                public void onFinish()
                {
                    showImgB.mImage.setVisibility(View.GONE);
                    TableRow row = (TableRow) findViewById(R.id.row1);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row2);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row3);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row4);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row5);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row6);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row7);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row8);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row9);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row10);
                    row.setVisibility(View.VISIBLE);

                    btnDrawA.setVisibility(View.VISIBLE);
                    btnDrawB.setVisibility(View.VISIBLE);
                    btnDrawC.setVisibility(View.VISIBLE);
                    btnN.setVisibility(View.VISIBLE);
                    BtnFinal.setVisibility(View.VISIBLE);
                }
            }.start();
        }
        if (downloadedC)
        {
            showImgC.mImage.setVisibility(View.VISIBLE);
            new CountDownTimer(10000, 1000)
            {

                public void onTick(long millisUntilFinished)
                {
                    //countdownTextView.setText(desc + millisUntilFinished / 1000);
                }

                public void onFinish()
                {
                    showImgC.mImage.setVisibility(View.GONE);
                    TableRow row = (TableRow) findViewById(R.id.row1);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row2);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row3);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row4);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row5);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row6);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row7);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row8);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row9);
                    row.setVisibility(View.VISIBLE);
                    row = (TableRow) findViewById(R.id.row10);
                    row.setVisibility(View.VISIBLE);

                    btnDrawA.setVisibility(View.VISIBLE);
                    btnDrawB.setVisibility(View.VISIBLE);
                    btnDrawC.setVisibility(View.VISIBLE);
                    btnN.setVisibility(View.VISIBLE);
                    BtnFinal.setVisibility(View.VISIBLE);
                }
            }.start();
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
    {
        try
        {
            uploadFile("IMG_A.jpg");
            uploadFile("Points_A.txt");
            deleteTempFile(DrawClockActivity.saved_local_path_dir + "IMG_A.jpg");
            deleteTempFile(DrawClockActivity.saved_local_path_dir + "Points_A.txt");
        } catch (Exception e)
        {
        }
        try
        {
            uploadFile("IMG_B.jpg");
            uploadFile("Points_B.txt");
            deleteTempFile(DrawClockActivity.saved_local_path_dir + "IMG_B.jpg");
            deleteTempFile(DrawClockActivity.saved_local_path_dir + "Points_B.txt");
        } catch (Exception e)
        {
        }
        try
        {
            uploadFile("IMG_C.jpg");
            uploadFile("Points_C.txt");
            deleteTempFile(DrawClockActivity.saved_local_path_dir + "IMG_C.jpg");
            deleteTempFile(DrawClockActivity.saved_local_path_dir + "Points_C.txt");
        } catch (Exception e)
        {
        }
    }
}
