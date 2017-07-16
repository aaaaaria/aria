package com.brainhealthtest.utility;

import android.util.Log;


import com.brainhealthtest.questions.BlockView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class BlockQuestion extends QuestionItem
{
    public List<String> ques;
    public List<String> ans;
    public int num;
    public String title, guideWord1, guideWord2;
    public String picture;
    public List<String> userAnswer = new ArrayList<String>();
    public List<String> userNum = new ArrayList<String>();

    public void GetAnswer(BlockView view)
    {
        userAnswer = new ArrayList<String>();
        userNum = new ArrayList<String>();

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
                {
                    JSONObject userItem = new JSONObject();
                    userItem.put("Answer", userAnswer.get(i));
                    userItem.put("CorrectNum", userNum.get(i));
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
        type = "block";
        name = "Block Design (WAIS)";
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
            if (reader.has("picture"))
                picture = reader.getString("picture");
            if (reader.has("title"))
                title = reader.getString("title");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        Log.d("load blockQuestion", "end blockQuestion");
    }

}
