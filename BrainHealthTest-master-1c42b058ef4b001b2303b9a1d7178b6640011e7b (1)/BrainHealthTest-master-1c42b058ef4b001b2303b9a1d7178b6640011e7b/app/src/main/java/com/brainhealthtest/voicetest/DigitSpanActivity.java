package com.brainhealthtest.voicetest;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.brainhealthtest.R;
import com.brainhealthtest.osshelper.Helper;
import com.brainhealthtest.questions.EndActivity;
import com.brainhealthtest.utility.Content2;
import com.brainhealthtest.utility.QuestionItemSet;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


public class DigitSpanActivity extends Activity implements OnClickListener
{
    QuestionItemSet mQuestionItemSet;
    DigitSpanQuestion question;
    Boolean flag;
    List<DigitAllView> digs;//several DigitAllViews are combined to it
    int toCheck; //present the testing's situation 
    private int back;
    private boolean skip, skip2, skip3, skip4;
    private Button BtnRecord, BtnStop;
    private Handler mHandler;
    MediaRecorder recorder = new MediaRecorder();
    MediaPlayer mediaPlayer = new MediaPlayer();
    private FileInputStream fileInputStream;
    public static String saved_local_path_dir = Environment.getExternalStorageDirectory().getPath() + "/";
    String finalR;

    List<String> words = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.digit_span);
        InitView();//Initial
        mHandler = new Handler();
        mHandler.post(new Runnable()
        {
            @Override
            public void run()
            {
                int num = digs.get(toCheck).Calculate();
                if (num > 0)
                {
                    if ((back == -1) && (toCheck < 2))
                    {//倒叙且是例子
                        if (toCheck == 0)
                        {
                            if (num == 2)
                            {
                                toCheck += 3;
                            }
                        }
                        if (toCheck == 1)
                        {
                            if (num == 2)
                            {
                                toCheck += 2;
                            }
                        }
                    } else if (num == 2)
                    {
                        if (back == -1 && skip3 == true && (toCheck == 2 || toCheck == 3) && skip4 == false)
                            toCheck = 5;
                        else if (back == -1 && skip3 == true && (toCheck == 2 || toCheck == 3) && skip4 == true)
                            return;
                        else if (toCheck % 2 == 0)
                            toCheck += 1;
                    } else if (toCheck > 1 && num == 1 && toCheck % 2 == 0)
                    {
                        if (back == -1 && (toCheck == 2 || toCheck == 4))
                        {
                        } else if ((digs.get(toCheck - 1).Calculate() == 2) || (digs.get(toCheck - 2).Calculate() == 2))
                        {
                            if (skip)
                                toCheck += 1;
                        } else
                            toCheck += 1;
                    } else if ((back == -1) && (toCheck == 5) && (num == 1))
                    {
                        toCheck = 1;
                        skip3 = true; //例子对了但是3span只有1分}
                    } else if ((back == -1) && (toCheck == 3) && (num == 1) && (skip4 == true))
                        return;
                    else if ((back == -1) && (toCheck == 3) && (num == 1) && (skip3 == true) && (skip4 == false))
                        toCheck = 5;

                    else if (num == 1 && toCheck % 2 == 1 && digs.get(toCheck - 1).Calculate() == 0)
                        skip = true;
                    toCheck++;
                    if (toCheck >= digs.size())
                        return;
                    digs.get(toCheck).Enable();
                } else if ((num == 0 && back == 0) || (num == 0 && back == -1 && toCheck >= 3))
                {
                    if (toCheck > 0 && num == 0 && toCheck % 2 == 1)
                    {
                        if (toCheck == 5 && back == -1)
                        {
                            if (digs.get(toCheck - 1).Calculate() == 0)
                            {
                                skip3 = true;
                                skip4 = true;
                            }
                            skip3 = true;
                            toCheck = 1;
                        } else if (toCheck == 3 && back == -1 && skip3 == true)
                        {
                            if (skip4 == true)
                                return;
                            else if (digs.get(toCheck - 1).Calculate() == 0)
                                return;
                            else
                                toCheck = 5;
                        } else
                        {
                            if (digs.get(toCheck - 1).Calculate() == 0)
                                return;
                            if (digs.get(toCheck - 1).Calculate() == 1)
                                skip = true;
                        }
                        toCheck++;
                        if (toCheck >= digs.size())
                            return;
                        digs.get(toCheck).Enable();
                    } else if (num == 0 && toCheck % 2 == 0)
                    {
                        toCheck++;
                        if (toCheck >= digs.size())
                            return;
                        digs.get(toCheck).Enable();
                    }
                } else if (num == 0 && back == -1 && toCheck < 3)
                {

                    toCheck += 1;
                    if (toCheck >= digs.size())
                        return;
                    digs.get(toCheck).Enable();
                }
                if (toCheck < digs.size())
                    mHandler.postDelayed(this, 200);
            }
        });
    }

    /* 
     * 鐣岄潰鍒濆鍖� 
     */
    private void InitView()
    {
        mQuestionItemSet = (QuestionItemSet) getApplicationContext();
        question = (DigitSpanQuestion) ((mQuestionItemSet.GetQuestion()));
        digs = new ArrayList<DigitAllView>();
        toCheck = 0;
        back = 0;
        skip = false;
        skip2 = false;
        skip3 = false;
        skip4 = false;
        if (question.spanType.equals("backward"))   //Backward question
        {
            back = -1;
            TextView view = (TextView) findViewById(R.id.description);
            view.setText("Digit Span(Backward) (WAIS)");
            view = (TextView) findViewById(R.id.guideWord);
            view.setText("Now I am going to say some more numbers, but this time when I stop I want you to say them backwards.\n  For example, if I say 鈥�7-1-9鈥� what would you say?\n");
            view = (TextView) findViewById(R.id.down);
            view.setText("NP008");
            DigitAllView digits = (DigitAllView) findViewById(R.id.digit_1);
            digits.InitB(question.digits.get(0));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit_2);
            digits.InitB(question.digits.get(1));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit1);
            digits.setVisibility(View.GONE);
            digits = (DigitAllView) findViewById(R.id.digit2);
            digits.setVisibility(View.GONE);
            digits = (DigitAllView) findViewById(R.id.digit3);
            digits.InitB(question.digits.get(2));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit4);
            digits.InitB(question.digits.get(3));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit5);
            digits.InitB(question.digits.get(4));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit6);
            digits.InitB(question.digits.get(5));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit7);
            digits.InitB(question.digits.get(6));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit8);
            digits.InitB(question.digits.get(7));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit9);
            digits.InitB(question.digits.get(8));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit10);
            digits.InitB(question.digits.get(9));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit11);
            digits.InitB(question.digits.get(10));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit12);
            digits.InitB(question.digits.get(11));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit13);
            digits.InitB(question.digits.get(12));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit14);
            digits.InitB(question.digits.get(13));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit15);
            digits.InitB(question.digits.get(14));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit16);
            digits.InitB(question.digits.get(15));
            digs.add(digits);
        } else                                      //Forward question
        {
            DigitAllView digits = (DigitAllView) findViewById(R.id.digit1);
            digits.Init(question.digits.get(0));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit2);
            digits.Init(question.digits.get(1));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit3);
            digits.Init(question.digits.get(2));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit4);
            digits.Init(question.digits.get(3));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit5);
            digits.Init(question.digits.get(4));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit6);
            digits.Init(question.digits.get(5));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit7);
            digits.Init(question.digits.get(6));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit8);
            digits.Init(question.digits.get(7));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit9);
            digits.Init(question.digits.get(8));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit10);
            digits.Init(question.digits.get(9));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit11);
            digits.Init(question.digits.get(10));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit12);
            digits.Init(question.digits.get(11));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit13);
            digits.Init(question.digits.get(12));
            digs.add(digits);
            digits = (DigitAllView) findViewById(R.id.digit14);
            digits.Init(question.digits.get(13));
            digs.add(digits);

            digits = (DigitAllView) findViewById(R.id.digit_1);
            digits.setVisibility(View.GONE);
            digits = (DigitAllView) findViewById(R.id.digit_2);
            digits.setVisibility(View.GONE);
            digits = (DigitAllView) findViewById(R.id.digit15);
            digits.setVisibility(View.GONE);
            digits = (DigitAllView) findViewById(R.id.digit16);
            digits.setVisibility(View.GONE);
            TextView view = (TextView) findViewById(R.id.digit_1view);
            view.setVisibility(View.GONE);
            view = (TextView) findViewById(R.id.digit_2view);
            view.setVisibility(View.GONE);
        }
        BtnStop = (Button) findViewById(R.id.StopBtn);
        BtnRecord = (Button) findViewById(R.id.RecordBtn);
        BtnRecord.setOnClickListener(this);
        BtnStop.setOnClickListener(this);
        BtnStop.setEnabled(false);
        Button btn = (Button) findViewById(R.id.getScore);
        btn.setOnClickListener(this);
        btn = (Button) findViewById(R.id.getNext);
        btn.setOnClickListener(this);
        Button BtnFinal = (Button) findViewById(R.id.btnFINISH);
        BtnFinal.setOnClickListener(this);
        Button BtnBack = (Button) findViewById(R.id.btnBACK);
        BtnBack.setOnClickListener(this);
        digs.get(0).Enable();
        finalR = question.record;

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

    /*next question & score*/
    public void onClick(View v)
    {
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
            case R.id.getNext:
                CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
                if (checkBox.isChecked())
                {
                    question.skip = true;
                    for (int i = 0; i < question.skipQues.size(); ++i)
                        mQuestionItemSet.questionItemSet.get(question.skipQues.get(i)).skip = true;
                } else
                {
                    question.skip = false;
                    question.GetAnswer(digs);
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

                mQuestionItemSet.questionId++;
                while (mQuestionItemSet.questionId < mQuestionItemSet.questionItemSet.size() && mQuestionItemSet.questionItemSet.get(mQuestionItemSet.questionId).skip)
                    mQuestionItemSet.questionId++;
                Intent intent = new Intent();
                intent.setClass(DigitSpanActivity.this, mQuestionItemSet.GetActivity());
                startActivity(intent);
                finish();
                break;
            case R.id.getScore:  //计算最长的长度
                int score1 = 0;//number right& sequency wrong
                int score2 = 0;//number right & sequency right
                int num = 0;
                for (int i = 0; i < 16; ++i)
                {
                    if (back == 0 && (i == 14 || i == 15))
                        break;
                    else
                        num = digs.get(i).Calculate();//to calculate each question's score
                    if (back == -1 && i == 2)
                    {
                        if (num == 2)
                        {
                            score1 = 2;
                            score2 = 2;
                            i += 1;
                        } else if (num == 1)
                        {
                            score1 = 2;
                            score2 = 0;
                        } else
                        {
                            score1 = 0;
                            score2 = 0;
                        }


                    } else if (back == -1 && i == 3)
                    {
                        if (num == 2)
                        {
                            score1 = 2;
                            score2 = 2;
                        } else if (num == 1)
                        {
                            score1 = 2;
                            score2 = 0;
                        } else
                        {
                            score1 = 0;
                            score2 = 0;
                        }

                    } else
                    {
                        if (back == 0)
                        {
                            if (num == 2 && i % 2 == 0)
                            {
                                if (!skip2)
                                    score2 = i / 2 + 3 + back;
                                score1 = i / 2 + 3 + back;
                                i += 1;
                            }
                            if (num == 2 && i % 2 == 1)
                            {
                                if (!skip2)
                                    score2 = i / 2 + 3 + back;
                                score1 = i / 2 + 3 + back;
                            }
                            if (num == 1)
                            {
                                score1 = i / 2 + 3 + back;
                                if (i % 2 == 0 && skip2 == true)
                                    i += 1;
                                if (i % 2 == 1)
                                    skip2 = true;//无需改动longest correct span
                            }
                            if (num == 0 && i % 2 == 1)
                                skip2 = true;
                            if (num == 0 && i % 2 == 1 && digs.get(i - 1).Calculate() == 0)
                                break;
                        } else
                        {
                            if (num == 2 && i % 2 == 0)
                            {
                                if (!skip2)
                                    score2 = i / 2 + 2 + back;
                                score1 = i / 2 + 2 + back;
                                i += 1;
                            }
                            if (num == 2 && i % 2 == 1)
                            {
                                if (!skip2)
                                    score2 = i / 2 + 2 + back;
                                score1 = i / 2 + 2 + back;
                            }
                            if (num == 1)
                            {
                                score1 = i / 2 + 2 + back;
                                if (i % 2 == 0 && skip2 == true)
                                    i += 1;
                                if (i % 2 == 1 && i >= 2)
                                    skip2 = true;
                            }
                            if (num == 0 && i % 2 == 1 && i >= 2)
                                skip2 = true;
                            if (num == 0 && i % 2 == 1 && digs.get(i - 1).Calculate() == 0 && i >= 2)
                                break;
                        }
                    }

                }
                question.score1 = score1;
                question.score2 = score2;
                TextView t1 = (TextView) findViewById(R.id.score1);
                t1.setText("The longest correct span = " + Integer.toString(score2));
                t1 = (TextView) findViewById(R.id.score2);
                t1.setText("The longest span = " + Integer.toString(score1));
                break;
            case R.id.btnFINISH:
                CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
                if (checkBox1.isChecked())
                {
                    question.skip = true;
                    for (int i = 0; i < question.skipQues.size(); ++i)
                        mQuestionItemSet.questionItemSet.get(question.skipQues.get(i)).skip = true;
                } else
                {
                    question.skip = false;
                    question.GetAnswer(digs);
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
                Intent intent2 = new Intent();
                intent2.setClass(DigitSpanActivity.this, EndActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.btnBACK:

                CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox1);
                if (checkBox2.isChecked())
                {
                    question.skip = true;
                    for (int i = 0; i < question.skipQues.size(); ++i)
                        mQuestionItemSet.questionItemSet.get(question.skipQues.get(i)).skip = true;
                } else
                {
                    question.skip = false;
                    question.GetAnswer(digs);
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
                Intent intent3 = new Intent();
                intent3.setClass(DigitSpanActivity.this, Content2.class);
                startActivity(intent3);
                finish();
                break;
        }
    }


}  