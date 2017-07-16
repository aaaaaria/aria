package com.brainhealthtest.utility;

import android.util.Log;


import com.brainhealthtest.questions.SingleVerbalFluencyView;
import com.brainhealthtest.questions.VerbalFluencyView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Verbal_fluencyQuestion extends QuestionItem
{
    public List<String> ques;
    public List<String> ans;
    public int num;
    public String title, guideWord1, guideWord2;
    public String record;
    public List<String> userAnswer = new ArrayList<String>();
    public List<String> userNum = new ArrayList<String>();
    public List<Integer> userError = new ArrayList<Integer>();
    public List<List<Integer>> userErr = new ArrayList<List<Integer>>();

    public void GetAnswer(VerbalFluencyView view)
    {
        userAnswer = new ArrayList<String>();
        userNum = new ArrayList<String>();
        for (int i = 0; i < view.vf.size(); ++i)
        {
            userError = new ArrayList<Integer>();
            SingleVerbalFluencyView singleView = view.vf.get(i);
            userAnswer.add(singleView.Answer.getText().toString());
            userNum.add(singleView.CorrectNumber.getText().toString());
            userError.add(0, singleView.wb);
            userError.add(1, singleView.bb);
            userError.add(2, singleView.pb);
            userError.add(3, singleView.ob);
            userError.add(4, singleView.sb);
            userErr.add(i, userError);
            Log.d("span", "i" + String.valueOf(i));
            Log.d("span", "userError" + String.valueOf(userError));
            Log.d("span", "userErr" + String.valueOf(userErr));
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
                {
                    JSONObject userItem = new JSONObject();
                    userItem.put("Answer", userAnswer.get(i));
                    userItem.put("CorrectNum", userNum.get(i));
                    JSONArray arrayError = new JSONArray();
                    for (int j = 0; j < userErr.get(i).size(); ++j)
                    {
                        arrayError.put(userErr.get(i).get(j));
                    }
                    userItem.put("ErrorType", arrayError);
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
        type = "verbal_fluency";
        ques = new ArrayList<String>();
        ans = new ArrayList<String>();
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
            if (reader.has("Title"))
            {
                title = reader.getString("Title");
                name = "VERBAL FLUENCY (FAS)" + " " + title;
            }
            if (reader.has("guide_word_1"))
                guideWord1 = reader.getString("guide_word_1");
            if (reader.has("guide_word_2"))
                guideWord2 = reader.getString("guide_word_2");
            if (reader.has("num"))
                num = 1;
            else num = 0;
            JSONArray questions = reader.getJSONArray("questions");
            for (int idx = 0; idx < questions.length(); ++idx)
            {
                JSONObject questionItem = questions.getJSONObject(idx);
                ques.add(questionItem.getString("time"));
            }
            Log.d("vf", String.valueOf("questions.length()=" + questions.length())); /////////////test
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

}
