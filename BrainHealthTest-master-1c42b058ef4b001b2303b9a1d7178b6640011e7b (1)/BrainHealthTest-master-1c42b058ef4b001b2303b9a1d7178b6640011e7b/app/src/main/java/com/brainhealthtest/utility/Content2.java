package com.brainhealthtest.utility;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.brainhealthtest.R;
import com.brainhealthtest.osshelper.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class Content2 extends Activity implements OnClickListener
{

    private JSONArray oldtemp;
    private JSONObject oj;
    private int length;
    QuestionItemSet mQuestionItemSet;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
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
        mQuestionItemSet.folderName = "01_Test/" + mQuestionItemSet.testerid + "_" + mQuestionItemSet.participanterid + "/";

        Helper helper = new Helper();
        helper.ossService = OSSServiceProvider.getService();
        helper.ossService.setApplicationContext(getApplicationContext());
        helper.Init();
        try
        {
            String answerJSON = helper.GetObject(mQuestionItemSet.folderName + "tester.json");

            try
            {
                JSONObject reader = new JSONObject(answerJSON);
                oldtemp = reader.getJSONArray("answers");
                length = oldtemp.length();

            } catch (JSONException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (OSSException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        //�Զ���layout���
        RelativeLayout layout = new RelativeLayout(this);
        ScrollView sv = new ScrollView(this);

        //���ﴴ��28����ť��ÿ�з���4����ť
        int num = length; ///
        Button Btn[] = new Button[num];
        for (int i = 0; i <= length - 1; i++)
        {
            Btn[i] = new Button(this);
            Btn[i].setId(2000 + i);
            Btn[i].setText(mQuestionItemSet.questionItemSet.get(i).name);
            RelativeLayout.LayoutParams btParams = new RelativeLayout.LayoutParams(width - 500, 100);  //���ð�ť�Ŀ�Ⱥ͸߶�
            btParams.leftMargin = 50;
            if (i >= 5 && i <= 14)
                btParams.topMargin = 50 + 100 * (i - 2);
            else if (i >= 17)
                btParams.topMargin = 50 + 100 * (i - 4);
            else
                btParams.topMargin = 50 + 100 * i;   //�����궨λ
            try
            {
                oj = oldtemp.getJSONObject(i);
            } catch (JSONException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Btn[i].setEnabled(true);
            if (oj.has("Answer"))
            {
                // Btn[i].setBackgroundColor(Color.GREEN);
                Btn[i].setEnabled(false);
            }
            if (i == 3 || i == 4 || i == 15 || i == 16)
                Btn[i].setVisibility(View.GONE);
            else
                layout.addView(Btn[i], btParams);
        }
        sv.addView(layout);
        this.setContentView(sv);

        for (int k = 0; k <= Btn.length - 1; k++)
        {
            Btn[k].setTag(k);

            Btn[k].setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int i = (Integer) v.getTag();
                    mQuestionItemSet.questionId = i;
                    Intent intent = new Intent();
                    intent.setClass(Content2.this, mQuestionItemSet.GetActivity());
                    startActivity(intent);
                    Content2.this.finish();
                }
            });
        }
    }

    @Override
    public void onClick(View arg0)
    {
        // TODO Auto-generated method stub

    }


}
