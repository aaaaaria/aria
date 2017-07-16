package com.brainhealthtest.utility;


import com.brainhealthtest.questions.BalanceView;
import com.brainhealthtest.questions.SingleBalanceView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class BalanceQuestion extends QuestionItem
{
    public List<String> ques;
    public int Type;
    public String title, guideWord1, guideWord2;
    public String record;
    public List<Integer> time1 = new ArrayList<Integer>();
    public List<Integer> time2 = new ArrayList<Integer>();

    public void GetAnswer(BalanceView view)
    {
        time1 = new ArrayList<Integer>();
        time2 = new ArrayList<Integer>();
        for (int i = 0; i < view.b.size(); ++i)
        {
            SingleBalanceView singleView = view.b.get(i);
            int s = singleView.Time1;
            int t = singleView.Time2;
            time1.add(s);
            time2.add(t);
        }
        Type = view.Type;
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
                for (int i = 0; i < time1.size(); ++i)
                {   ///size?
                    JSONObject userItem = new JSONObject();
                    userItem.put("Time1", time1.get(i));
                    userItem.put("Time2", time2.get(i));

                    array.put(userItem);
                }
                saver.put("Type_first", Type);
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
        type = "balance";
        name = "Balance Tests ";
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
                ques.add(questionItem.getString("question"));
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }


}
