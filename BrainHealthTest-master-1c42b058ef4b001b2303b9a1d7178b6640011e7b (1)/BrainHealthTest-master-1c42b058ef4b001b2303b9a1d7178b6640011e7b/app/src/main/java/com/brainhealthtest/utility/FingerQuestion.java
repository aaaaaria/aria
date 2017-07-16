package com.brainhealthtest.utility;

import android.util.Log;


import com.brainhealthtest.questions.FingerView;
import com.brainhealthtest.questions.SingleFingerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FingerQuestion extends QuestionItem
{
    public String guideWord1;
    public List<String> ques;
    public List<String> userAnswerR = new ArrayList<String>();
    public List<String> userAnswerL = new ArrayList<String>();

    public void GetAnswer(FingerView view)
    {
        userAnswerR = new ArrayList<String>();
        userAnswerL = new ArrayList<String>();
        for (int i = 0; i < view.ft.size(); ++i)
        {
            SingleFingerView singleView = view.ft.get(i);
            userAnswerR.add(singleView.Answer1.getText().toString());
            userAnswerL.add(singleView.Answer2.getText().toString());
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
                for (int i = 0; i < userAnswerR.size(); ++i)
                {   ///size?
                    JSONObject userItem = new JSONObject();
                    userItem.put("answer_left", userAnswerL.get(i));
                    userItem.put("answer_right", userAnswerR.get(i));
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
        type = "finger";
        name = "Finger Tapping Test";
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
            if (reader.has("guide_word_1"))
                guideWord1 = reader.getString("guide_word_1");
            JSONArray questions = reader.getJSONArray("questions");
            for (int idx = 0; idx < questions.length(); ++idx)
            {
                JSONObject questionItem = questions.getJSONObject(idx);
                ques.add(questionItem.getString("number"));
            }
            Log.d("finger", String.valueOf("questions.length()=" + questions.length())); /////////////test
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

}
