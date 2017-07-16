package com.brainhealthtest.utility;


import com.brainhealthtest.questions.MathFluencyView;
import com.brainhealthtest.questions.SingleMathFluencyView;
import com.brainhealthtest.questions.SingleMathFluencyViewBeta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MathFluencyQuestion extends QuestionItem
{
    public String userAnswerTime;
    public List<String> ques1, ques2_1, ques2_2;
    public List<String> userAnswerR = new ArrayList<String>();
    public List<String> userAnswerL = new ArrayList<String>();
    public List<String> userAnswer1_1 = new ArrayList<String>();
    public List<String> userAnswer1_2 = new ArrayList<String>();
    public List<String> userAnswer1_3 = new ArrayList<String>();


    public void GetAnswer(MathFluencyView MathFview)
    {
        userAnswerTime = MathFview.time.getText().toString();//get_time
        userAnswer1_1 = new ArrayList<String>();
        userAnswer1_2 = new ArrayList<String>();
        userAnswer1_3 = new ArrayList<String>();
        userAnswerR = new ArrayList<String>();
        userAnswerL = new ArrayList<String>();
        for (int i = 0; i < MathFview.mf1.size(); ++i)
        {
            SingleMathFluencyView singleView1 = MathFview.mf1.get(i);
            userAnswer1_1.add(singleView1.answertxt1.getText().toString());
            userAnswer1_2.add(singleView1.answertxt2.getText().toString());
            userAnswer1_3.add(singleView1.answertxt3.getText().toString());
        }

        for (int i = 0; i < MathFview.mf2_1.size(); ++i)
        {
            SingleMathFluencyViewBeta singleView = MathFview.mf2_1.get(i);
            userAnswerL.add(singleView.answertxt1.getText().toString());
            userAnswerR.add(singleView.answertxt2.getText().toString());
        }
        for (int i = 0; i < MathFview.mf2_2.size(); ++i)
        {
            SingleMathFluencyViewBeta singleView = MathFview.mf2_2.get(i);
            userAnswerL.add(singleView.answertxt1.getText().toString());
//	            userAnswerR.add(singleView.answertxt2.getText().toString());
        }
    }

    public JSONObject Save()
    {
        JSONObject saver = new JSONObject();
        try
        {
            if (skip)
                saver.put("skip", 1);
            else
            {
                saver.put("Time", userAnswerTime);//save_time

                JSONArray array = new JSONArray();
                for (int i = 0; i < userAnswer1_1.size(); i++)
                {
                    JSONObject userItem = new JSONObject();
                    userItem.put("0:0-1:0", userAnswer1_1.get(i));
                    userItem.put("1:0-2:0", userAnswer1_2.get(i));
                    userItem.put("2:0-3:0", userAnswer1_3.get(i));
                    array.put(userItem);
                }
                saver.put("user_answers", array);

                JSONArray array1 = new JSONArray();
                for (int i = 0; i < userAnswerL.size(); i++)
                {
                    JSONObject userItem = new JSONObject();
                    userItem.put("Error(L)", userAnswerL.get(i));
                    array1.put(userItem);
                }
                saver.put("Error", array1);

                JSONArray array2 = new JSONArray();
                for (int i = 0; i < userAnswerR.size(); i++)
                {
                    JSONObject userItem = new JSONObject();
                    userItem.put("Self Corrections(R)", userAnswerR.get(i));
                    array2.put(userItem);
                }
                saver.put("Self Corrections", array2);
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return saver;
    }

    protected void Load(JSONObject reader)
    {
        skip = false;
        type = "math_fluency";
        name = "Math Fluency ";//?
        ques1 = new ArrayList<String>();
        ques2_1 = new ArrayList<String>();
        ques2_2 = new ArrayList<String>();
        try
        {
            if (reader.has("skip"))
            {
                JSONArray skips = reader.getJSONArray("skip");
                for (int i = 0; i < skips.length(); ++i)
                    skipQues.add(skips.getInt(i));
            }
        } catch (Exception e)
        {

        }
        try
        {
            JSONArray questions1 = reader.getJSONArray("questions1");
            for (int idx = 0; idx < questions1.length(); ++idx)
            {
                JSONObject questionItem = questions1.getJSONObject(idx);
                ques1.add(questionItem.getString("pair1"));
            }

            JSONArray questions2_1 = reader.getJSONArray("questions2_1");
            for (int idx = 0; idx < questions2_1.length(); ++idx)
            {
                JSONObject questionItem = questions2_1.getJSONObject(idx);
                ques2_1.add(questionItem.getString("pair1"));
                ques2_2.add(questionItem.getString("pair2"));
            }
            JSONArray questions2_2 = reader.getJSONArray("questions2_2");
            for (int idx = 0; idx < questions2_2.length(); ++idx)
            {
                JSONObject questionItem = questions2_2.getJSONObject(idx);
                ques2_1.add(questionItem.getString("pair1"));
                ques2_2.add(questionItem.getString("pair2"));
            }
//				Log.d("ds",String.valueOf("questions1.length()="+questions1.length())); /////////////test
            //Log.d("ds",String.valueOf("questions2.length()="+questions1.length()));

        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

}
