package com.brainhealthtest.utility;

import android.util.Log;


import com.brainhealthtest.questions.CookieTheftView;
import com.brainhealthtest.questions.SingleCookieTheftView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CookieTheftQuestion extends QuestionItem
{
    public String guideWord1;
    public List<String> ques1, ques2;
    public String userAnswerTime;
    public int TypeofWriting = -1, Syn = -1;
    public List<Integer> userScore1 = new ArrayList<Integer>();
    public List<Integer> userScore2 = new ArrayList<Integer>();

    public void GetAnswer(CookieTheftView view)
    {
        userAnswerTime = view.time.getText().toString();//get_time
        TypeofWriting = view.Type;
        Syn = view.Syn;

        userScore1 = new ArrayList<Integer>();
        for (int i = 0; i < view.ct1.size(); ++i)
        {
            SingleCookieTheftView singleView = view.ct1.get(i);
            int s = singleView.Score;
            userScore1.add(s);
        }
        userScore2 = new ArrayList<Integer>();
        for (int i = 0; i < view.ct2.size(); ++i)
        {
            SingleCookieTheftView singleView = view.ct2.get(i);
            int s = singleView.Score;
            userScore2.add(s);
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
                saver.put("Type_of_Writing", TypeofWriting);//save_type
                saver.put("Syn", Syn);
                JSONArray array1 = new JSONArray();
                for (int i = 0; i < userScore1.size(); ++i)
                {
                    array1.put(userScore1.get(i));
                }
                saver.put("Inclusion", array1);
                JSONArray array2 = new JSONArray();
                for (int i = 0; i < userScore2.size(); ++i)
                {
                    array2.put(userScore2.get(i));
                }
                saver.put("Other", array2);
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
        type = "cookietheft";
        name = "Cookie Theft Writing Sample";
        ques1 = new ArrayList<String>();
        ques2 = new ArrayList<String>();
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
            if (reader.has("guide_word_1"))
                guideWord1 = reader.getString("guide_word_1");
            JSONArray questions1 = reader.getJSONArray("question1");
            for (int idx = 0; idx < questions1.length(); ++idx)
            {
                JSONObject questionItem = questions1.getJSONObject(idx);
                ques1.add(questionItem.getString("question"));
            }
            JSONArray questions2 = reader.getJSONArray("question2");
            for (int idx = 0; idx < questions2.length(); ++idx)
            {
                JSONObject questionItem = questions2.getJSONObject(idx);
                ques2.add(questionItem.getString("question"));
            }
            Log.d("ct", String.valueOf("questions1.length()=" + questions1.length())); /////////////test
            Log.d("ct", String.valueOf("questions2.length()=" + questions2.length())); /////////////test
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

}
