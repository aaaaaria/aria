package com.brainhealthtest.questions;


import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.brainhealthtest.R;
import com.brainhealthtest.osshelper.Helper;
import com.brainhealthtest.utility.BlockQuestion;
import com.brainhealthtest.utility.QuestionItemSet;

public class BlockDesignActivity extends Activity implements OnClickListener
{
    public static String saved_local_path_dir = Environment.getExternalStorageDirectory().getPath() + "/";
    private int STATE;
    private int CHOISE;
    private int BUTTON_PUSHED;
    private LinearLayout a1;
    private int[] result = new int[40];

    private int count = 0;
    private long FirstClick = 0;
    private Button LastButton;

    private Button start;
    private boolean isStart = false;
    private int totalSec = 0;
    private TextView minute;
    private TextView second;
    private long accurateTime1 = 0;
    private long accurateTime2 = 0;

    Helper helper = new Helper();
    QuestionItemSet mQuestionItemSet;
    BlockQuestion blockQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        helper.ossService = OSSServiceProvider.getService();
        helper.ossService.setApplicationContext(getApplicationContext());
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.blockdesign);
//		InitView();

        CHOISE = R.id.cube1;// initialize result area(right)

        final LayoutInflater inflater = LayoutInflater.from(this);
        final LinearLayout box1 = (LinearLayout) findViewById(R.id.InnerBox1);
        final LinearLayout box2 = (LinearLayout) findViewById(R.id.InnerBox2);

        for (int i = 1; i <= 4; i++)
        {
            LinearLayout ButtonUnit = (LinearLayout) inflater.inflate(
                    R.layout.button_unit, null).findViewById(R.id.area);
            setButtonUnit(ButtonUnit);

            if (i <= 2)
            {
                box1.addView(ButtonUnit);
                ButtonUnit.setTag(i);
            } else
            {
                box2.addView(ButtonUnit);
                ButtonUnit.setTag(i);
            }
        }

        findViewById(R.id.cube1).setBackgroundResource(R.drawable.select_fram);
        setChart();

        start = (Button) findViewById(R.id.StartButton);
        final Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                switch (msg.what)
                {
                    case 2:
                        this.sendEmptyMessageDelayed(1, 1000 - accurateTime2);
                        accurateTime1 = System.currentTimeMillis();
                        break;
                    case 1:
                        if (isStart)
                        {
                            updateTimer();
                            accurateTime2 = System.currentTimeMillis() - accurateTime1;
                            accurateTime1 = System.currentTimeMillis();
                            this.sendEmptyMessageDelayed(1, 1000);
                        }
                        break;

                    default:
                        accurateTime2 = System.currentTimeMillis() - accurateTime1;
                        break;
                }
                super.handleMessage(msg);
            }
        };

        minute = (TextView) findViewById(R.id.Min);
        second = (TextView) findViewById(R.id.Sec);

        start.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                handler.removeMessages(1);
                if (false == isStart)
                {
                    handler.sendEmptyMessage(2);
                    isStart = true;
                    start.setText("STOP");
                } else
                {
                    handler.sendEmptyMessage(0);
                    isStart = false;
                    start.setText("START");
                }
            }
        });
        Log.d("aliyun activity1", "enter activity onCreate");


    }

    private void InitView()
    {

        mQuestionItemSet = (QuestionItemSet) getApplicationContext();
        blockQuestion = (BlockQuestion) ((mQuestionItemSet.GetQuestion()));

        BlockView blockview = (BlockView) findViewById(R.id.block);///////////
        blockview.Init(blockQuestion, helper);

        Button btn = (Button) findViewById(R.id.blockGetNext);
        btn.setOnClickListener((OnClickListener) this);//?
        Button BtnFinal = (Button) findViewById(R.id.btnFINISH);
        BtnFinal.setOnClickListener((OnClickListener) this);
        Button BtnBack = (Button) findViewById(R.id.btnBACK);
        BtnBack.setOnClickListener(this);

        Log.d("aliyun activity1", "enter activity InitView");


    }

    public void updateTimer()
    {
        totalSec += 1;
        int min = (int) (totalSec / 60) % 60;
        int sec = (int) (totalSec % 60);
        minute.setText("" + min + ":");
        if (sec < 10)
        {
            second.setText("0" + sec);
        } else
        {
            second.setText("" + sec);
        }
    }

    public void setButtonUnit(View v)
    {
        Button ButtonClicked1 = (Button) v.findViewById(R.id.button_1);
        Button ButtonClicked2 = (Button) v.findViewById(R.id.button_2);
        Button ButtonClicked3 = (Button) v.findViewById(R.id.button_3);
        Button ButtonClicked4 = (Button) v.findViewById(R.id.button_4);
        Button ButtonClicked5 = (Button) v.findViewById(R.id.button_5);
        Button ButtonClicked6 = (Button) v.findViewById(R.id.button_6);
        Button ButtonClickedRes = (Button) v.findViewById(R.id.res);
        Log.d("aliyun activity1", "enter activity setButtonUnit");

        ButtonClicked1.setOnClickListener(this);
        ButtonClicked2.setOnClickListener(this);
        ButtonClicked3.setOnClickListener(this);
        ButtonClicked4.setOnClickListener(this);
        ButtonClicked5.setOnClickListener(this);
        ButtonClicked6.setOnClickListener(this);
        ButtonClickedRes.setOnClickListener(this);

    }

    public void setChart()
    {
        View cube1 = findViewById(R.id.cube1);
        View cube2 = findViewById(R.id.cube2);
        View cube3 = findViewById(R.id.cube3);
        View cube4 = findViewById(R.id.cube4);
        View cube5 = findViewById(R.id.cube5);
        View cube6 = findViewById(R.id.cube6);
        View cube7 = findViewById(R.id.cube7);
        View cube8 = findViewById(R.id.cube8);
        //CHOISE = cube1.getId();
        cube1.setOnClickListener(this);
        cube2.setOnClickListener(this);
        cube3.setOnClickListener(this);
        cube4.setOnClickListener(this);
        cube5.setOnClickListener(this);
        cube6.setOnClickListener(this);
        cube7.setOnClickListener(this);
        cube8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {

//		CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox1);
//        if (checkBox.isChecked()) {
//        	blockQuestion.skip = true;
//            for (int i = 0; i < blockQuestion.skipQues.size(); ++i)
//                mQuestionItemSet.questionItemSet.get(blockQuestion.skipQues.get(i)).skip = true;
//        }
//        ((MathFluencyQuestion)(mQuestionItemSet.GetQuestion())).GetAnswer((MathFluencyView)findViewById(R.id.mathfluency));

//	    switch(v.getId())  
//        {  
//    	case R.id.blockGetNext:
//           
//            Helper helper = new Helper();
//            helper.ossService = OSSServiceProvider.getService();
//            helper.ossService.setApplicationContext(getApplicationContext());
//            helper.Init();
//            try {
//            	String json = mQuestionItemSet.Save1(helper.GetObject(mQuestionItemSet.folderName + "tester.json"),mQuestionItemSet.questionId);
//                byte[] data = json.getBytes();
//                helper.PutObject(mQuestionItemSet.folderName + "tester.json", data);
//            } catch (OSSException e) {
//                e.printStackTrace();
//           }
//
//    		mQuestionItemSet.questionId ++;
//            while (mQuestionItemSet.questionId < mQuestionItemSet.questionItemSet.size() && mQuestionItemSet.questionItemSet.get(mQuestionItemSet.questionId).skip)
//                mQuestionItemSet.questionId ++;
//     	    Intent intent = new Intent();
//            intent.setClass(BlockDesignActivity.this, mQuestionItemSet.GetActivity());
//            startActivity(intent);
//            finish();
//    		break;
//    	
//    	 case R.id.btnFINISH:
//	      	   Helper helper2 = new Helper();
//	      	   helper2.ossService = OSSServiceProvider.getService();
//	     		   helper2.ossService.setApplicationContext(getApplicationContext());
//	     		   helper2.Init();
//	     		   try {
//	     		   String json2 = mQuestionItemSet.Save1(helper2.GetObject(mQuestionItemSet.folderName + "tester.json"),mQuestionItemSet.questionId);
//	   	      	   byte[] data2 = json2.getBytes();
//	     		   helper2.PutObject(mQuestionItemSet.folderName + "tester.json", data2);
//	  		   } catch (OSSException e) {
//	  			   // TODO Auto-generated catch block
//	  			   e.printStackTrace();
//	  		   }
//	     		   
//	     		Intent intent2 = new Intent();
//	          intent2.setClass(BlockDesignActivity.this, EndActivity.class);
//	          startActivity(intent2);
//	     		   finish();
//	     		   break;
//	    
//    	 case R.id.btnBACK:
//         	   
//         	  Helper helper3 = new Helper();
//         	   helper3.ossService = OSSServiceProvider.getService();
//        		   helper3.ossService.setApplicationContext(getApplicationContext());
//        		   helper3.Init();
//        		   try {
//        			 String json2 = mQuestionItemSet.Save1(helper3.GetObject(mQuestionItemSet.folderName + "tester.json"),mQuestionItemSet.questionId);
//      			   byte[] data = json2.getBytes();
//        			   helper3.PutObject(mQuestionItemSet.folderName + "tester.json", data);
//     		   } catch (OSSException e) {
//     			   // TODO Auto-generated catch block
//     			   e.printStackTrace();
//     		   }
//        		Intent intent3 = new Intent();
//        		intent3.setClass(BlockDesignActivity.this, Content2.class);
//             startActivity(intent3);
//        		   finish();
//        		   break;
//        		   
//        } 

        try
        {// click on the result area(right) to select
            if (((RelativeLayout) v.getParent()).getId() == R.id.Chart)
            {
                findViewById(CHOISE).setBackgroundColor(0);
                //hide former choice frame
                v.setBackgroundResource(R.drawable.select_fram);
                //set up new frame
                CHOISE = v.getId();
                //save present choice

                for (int target = R.id.c1, i = 1; i <= 4; target++, i++)
                {
                    try
                    {
                        findViewById(R.id.box).findViewWithTag(i).findViewById(R.id.res).setVisibility(View.VISIBLE);
                        findViewById(R.id.box).findViewWithTag(i).findViewById(R.id.line1).setVisibility(View.GONE);
                        findViewById(R.id.box).findViewWithTag(i).findViewById(R.id.line2).setVisibility(View.GONE);
                        findViewById(R.id.box).findViewWithTag(i).findViewById(R.id.res)
                                .setBackgroundResource(Integer.parseInt(((ImageView) v.findViewById(target)).getTag().toString()));
                    } catch (Exception e)
                    {
                        findViewById(R.id.box).findViewWithTag(i).findViewById(R.id.res).setVisibility(View.GONE);
                        findViewById(R.id.box).findViewWithTag(i).findViewById(R.id.line1).setVisibility(View.VISIBLE);
                        findViewById(R.id.box).findViewWithTag(i).findViewById(R.id.line2).setVisibility(View.VISIBLE);
                    }
                }

            }
        } catch (Exception e)
        {
            // click on the select area(control box) to record
            STATE = 0;
            if (((LinearLayout) v.getParent().getParent()).getId() == R.id.area)
            {
                a1 = (LinearLayout) v.getParent().getParent();
            } else
            {
                a1 = (LinearLayout) v.getParent();
            }

            Button res = (Button) a1.findViewById(R.id.res);

            switch (v.getId())
            {
                case R.id.button_1:
                    res.setBackgroundResource(R.drawable.q1);
                    BUTTON_PUSHED = R.drawable.q1;
                    break;
                case R.id.button_2:
                    res.setBackgroundResource(R.drawable.q2);
                    BUTTON_PUSHED = R.drawable.q2;
                    break;
                case R.id.button_3:
                    res.setBackgroundResource(R.drawable.q3);
                    BUTTON_PUSHED = R.drawable.q3;
                    break;
                case R.id.button_4:
                    res.setBackgroundResource(R.drawable.q4);
                    BUTTON_PUSHED = R.drawable.q4;
                    break;
                case R.id.button_5:
                    res.setBackgroundResource(R.drawable.q5);
                    BUTTON_PUSHED = R.drawable.q5;
                    break;
                case R.id.button_6:
                    res.setBackgroundResource(R.drawable.q6);
                    BUTTON_PUSHED = R.drawable.q6;
                    break;

                default:
                    STATE = 1;
                    break;
            }

            int totalId = 0;
            if (CHOISE == R.id.cube1)
            {
                totalId = 1;
            } else
            {
                totalId = (CHOISE - R.id.cube1 - 4) * 5 + 1;
            }

            if (0 == STATE)
            {// single click
                a1.findViewById(R.id.line1).setVisibility(View.GONE);
                a1.findViewById(R.id.line2).setVisibility(View.GONE);
                res.setVisibility(View.VISIBLE);


                switch (Integer.parseInt(a1.getTag().toString()))
                {
                    case 1:
                        ((ImageView) findViewById(CHOISE).findViewById(R.id.c1))
                                .setImageResource(BUTTON_PUSHED);
                        ((ImageView) findViewById(CHOISE).findViewById(R.id.c1))
                                .setTag(BUTTON_PUSHED);
                        result[totalId] = BUTTON_PUSHED;
                        break;
                    case 2:
                        ((ImageView) findViewById(CHOISE).findViewById(R.id.c2))
                                .setImageResource(BUTTON_PUSHED);
                        ((ImageView) findViewById(CHOISE).findViewById(R.id.c2))
                                .setTag(BUTTON_PUSHED);
                        result[totalId + 1] = BUTTON_PUSHED;
                        break;
                    case 3:
                        ((ImageView) findViewById(CHOISE).findViewById(R.id.c3))
                                .setImageResource(BUTTON_PUSHED);
                        ((ImageView) findViewById(CHOISE).findViewById(R.id.c3))
                                .setTag(BUTTON_PUSHED);
                        result[totalId + 2] = BUTTON_PUSHED;
                        break;
                    case 4:
                        ((ImageView) findViewById(CHOISE).findViewById(R.id.c4))
                                .setImageResource(BUTTON_PUSHED);
                        ((ImageView) findViewById(CHOISE).findViewById(R.id.c4))
                                .setTag(BUTTON_PUSHED);
                        result[totalId + 3] = BUTTON_PUSHED;
                        break;

                    default:
                        break;
                }

            } else
            {// double click, set to default
                count++;
                if (System.currentTimeMillis() - FirstClick >= 500)
                {
                    FirstClick = System.currentTimeMillis();
                    if (2 == count)
                    {
                        count--;
                    }
                } else if (2 == count
                        && System.currentTimeMillis() - FirstClick < 500)
                {
                    if ((Button) v != LastButton)
                    {
                        count--;
                    } else
                    {
                        a1.findViewById(R.id.line1).setVisibility(View.VISIBLE);
                        a1.findViewById(R.id.line2).setVisibility(View.VISIBLE);
                        res.setVisibility(View.GONE);

                        switch (Integer.parseInt(a1.getTag().toString()))
                        {
                            case 1:
                                ((ImageView) findViewById(CHOISE).findViewById(
                                        R.id.c1)).setImageResource(R.drawable.grey);
                                ((ImageView) findViewById(CHOISE).findViewById(R.id.c1))
                                        .setTag(null);
                                result[totalId] = 0;
                                break;
                            case 2:
                                ((ImageView) findViewById(CHOISE).findViewById(
                                        R.id.c2)).setImageResource(R.drawable.grey);
                                ((ImageView) findViewById(CHOISE).findViewById(R.id.c2))
                                        .setTag(null);
                                result[totalId + 1] = 0;
                                break;
                            case 3:
                                ((ImageView) findViewById(CHOISE).findViewById(
                                        R.id.c3)).setImageResource(R.drawable.grey);
                                ((ImageView) findViewById(CHOISE).findViewById(R.id.c3))
                                        .setTag(null);
                                result[totalId + 2] = 0;
                                break;
                            case 4:
                                ((ImageView) findViewById(CHOISE).findViewById(
                                        R.id.c4)).setImageResource(R.drawable.grey);
                                ((ImageView) findViewById(CHOISE).findViewById(R.id.c4))
                                        .setTag(null);
                                result[totalId + 3] = 0;
                                break;

                            default:
                                break;
                        }

                        count = 0;
                        FirstClick = 0;
                    }
                }
                LastButton = (Button) v;
            }

        }

    }

    public class data
    {
        private int[] result = new int[40];
        private int Min;
        private int Sec;

        public int[] getResult()
        {
            return result;
        }

        public int getMin()
        {
            return Min;
        }

        public int getSec()
        {
            return Sec;
        }

    }

    public void recover()
    {
        TextView min = (TextView) findViewById(R.id.Min);
        TextView sec = (TextView) findViewById(R.id.Sec);
        data data = new data();
        int[] res = data.getResult();
        int seq = 0;
        //recover time
        min.setText(data.getMin());
        sec.setText(data.getSec());
        //recover result
        for (int target = R.id.c1; target <= R.id.c4; target++)
        {
            findViewById(R.id.cube1).findViewById(target)
                    .setBackgroundResource(res[seq++]);
        }
        for (int target = R.id.cube2; target <= R.id.cube8; target++)
        {
            for (int i = R.id.c1; i <= R.id.c4; i++)
            {
                findViewById(target).findViewById(i).setBackgroundResource(
                        res[seq++]);
            }

        }

    }
}
