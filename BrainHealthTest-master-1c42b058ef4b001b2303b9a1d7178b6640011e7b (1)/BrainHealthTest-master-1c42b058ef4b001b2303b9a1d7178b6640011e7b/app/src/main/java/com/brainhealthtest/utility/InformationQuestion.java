package com.brainhealthtest.utility;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;


import com.brainhealthtest.questions.InformationView;
import com.brainhealthtest.questions.SingleInformationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class InformationQuestion extends QuestionItem
{
    public String guideWord1;
    public List<String> ques;
    public List<String> userAnswer = new ArrayList<String>();
    public List<Integer> userScore = new ArrayList<Integer>();
    public String record;
    public int fail;

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    public void GetTempAnswer(InformationView view)
    {
        fail = 0;
        for (int i = 0; i < view.in.size(); ++i)
        {
            SingleInformationView singleView = view.in.get(i);
            int btnScore = singleView.Score;
            if ((btnScore == 0 || btnScore == 6) && fail < 4)
                fail = fail + 1;
            else if ((btnScore == 0 || btnScore == 6) && fail == 4)
                view.in.get(i + 1).scoreN.callOnClick();
            else if (btnScore != 0 && btnScore != 6)
                fail = 0;
        }
    }

    public void GetAnswer(InformationView view)
    {
        userAnswer = new ArrayList<String>();
        userScore = new ArrayList<Integer>();
        for (int i = 0; i < view.in.size(); ++i)
        {
            SingleInformationView singleView = view.in.get(i);
            userAnswer.add(singleView.Answer.getText().toString());
            int s = singleView.Score;
            userScore.add(s);
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
                JSONArray array = new JSONArray();
                for (int i = 0; i < userAnswer.size(); ++i)
                {   ///size?
                    JSONObject userItem = new JSONObject();
                    userItem.put("answer", userAnswer.get(i));
                    userItem.put("score", userScore.get(i));
                    array.put(userItem);
                }
                saver.put("user_answers", array);
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
        type = "information";
        name = "Information (WAIS-R)";
        ques = new ArrayList<String>();
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
            if (reader.has("record"))
                record = reader.getString("record");
            if (reader.has("guide_word_1"))
                guideWord1 = reader.getString("guide_word_1");
            JSONArray questions = reader.getJSONArray("questions");
            for (int idx = 0; idx < questions.length(); ++idx)
            {
                JSONObject questionItem = questions.getJSONObject(idx);
                ques.add(questionItem.getString("question"));
            }
            Log.d("information", String.valueOf("questions.length()=" + questions.length())); /////////////test
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

}
