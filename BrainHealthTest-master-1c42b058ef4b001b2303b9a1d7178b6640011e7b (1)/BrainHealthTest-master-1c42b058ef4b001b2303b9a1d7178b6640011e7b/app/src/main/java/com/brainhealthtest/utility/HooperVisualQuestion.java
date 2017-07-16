package com.brainhealthtest.utility;


import com.brainhealthtest.questions.HooperVisualView;
import com.brainhealthtest.questions.SingleHooperVisualView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HooperVisualQuestion extends QuestionItem
{
    public List<String> ques, halfpoint;
    public List<String> ans;
    public int num;
    public String title, guideWord1, guideWord2;
    public String record;
    public List<Integer> userScore = new ArrayList<Integer>();
    public List<Integer> userType = new ArrayList<Integer>();

    public void GetAnswer(HooperVisualView view)
    {
        userScore = new ArrayList<Integer>();
        userType = new ArrayList<Integer>();
        for (int i = 0; i < view.hv.size(); ++i)
        {
            SingleHooperVisualView singleView = view.hv.get(i);
            int s = singleView.Score;
            int t = singleView.Type;
            userScore.add(s);
            userType.add(t);
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
                for (int i = 0; i < userScore.size(); ++i)
                {
                    JSONObject userItem = new JSONObject();
                    userItem.put("score", userScore.get(i));
                    userItem.put("error_type", userType.get(i));

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

    @Override
    protected void Load(JSONObject reader)
    {
        type = "hoopervisual";
        name = "Hooper Visual Organization Test";
        ques = new ArrayList<String>();
        halfpoint = new ArrayList<String>();
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
                halfpoint.add(questionItem.getString("halfpoint"));
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

}
