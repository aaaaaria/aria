package com.brainhealthtest.utility;

import android.util.Log;


import com.brainhealthtest.questions.BostonNamingView;
import com.brainhealthtest.questions.SingleBostonNamingView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Boston_namingQuestion extends QuestionItem
{
    public List<String> ques1;
    public List<String> ques2;
    public List<String> ques3;
    public int score;
    public String guideWord1, guideWord2, guideWord3, guideWord4;
    public String record;
    public List<Integer> userError = new ArrayList<Integer>();
    public List<List<Integer>> userErr = new ArrayList<List<Integer>>();
    public List<Integer> userAnswerScore = new ArrayList<Integer>();
    public List<Integer> userAnswerType = new ArrayList<Integer>();
    public List<String> userAnswer = new ArrayList<String>();
    public List<String> userAnswerTime = new ArrayList<String>();

    public void GetAnswer(BostonNamingView view)
    {

        userAnswerScore = new ArrayList<Integer>();
        userAnswerType = new ArrayList<Integer>();
        userAnswerTime = new ArrayList<String>();
        for (int i = 0; i < view.bn.size(); ++i)
        {
            SingleBostonNamingView singleView = view.bn.get(i);
            if (singleView.type == 1)
            {
                userAnswerType.add(1);
                userAnswer.add(singleView.Answer1.getText().toString());
            } else if (singleView.type == 2)
            {
                userAnswerType.add(2);
                userAnswer.add(singleView.Answer2.getText().toString());
            } else if (singleView.type == 3)
            {
                userAnswerType.add(3);
                userAnswer.add(singleView.Answer3.getText().toString());
            }
            userError = new ArrayList<Integer>();
            userError.add(0, singleView.t1);
            userError.add(1, singleView.t2);
            userError.add(2, singleView.t3);
            userError.add(3, singleView.t4);
            userError.add(4, singleView.t5);
            userError.add(5, singleView.t6);
            userErr.add(i, userError);
            score = singleView.Score;
            userAnswerScore.add(score);
            userAnswerTime.add(singleView.time.getText().toString());
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
                for (int i = 0; i < userAnswerType.size(); ++i)
                {
                    JSONObject userItem = new JSONObject();
                    JSONArray arrayError = new JSONArray();
                    for (int j = 0; j < userErr.get(i).size(); ++j)
                    {
                        arrayError.put(userErr.get(i).get(j));
                    }
                    userItem.put("ErrorType", arrayError);
                    userItem.put("Score", userAnswerScore.get(i));
                    userItem.put("Answer", userAnswer.get(i));
                    userItem.put("Type", userAnswerType.get(i));
                    userItem.put("Time", userAnswerTime.get(i));
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
        skip = false;
        type = "boston_naming";
        name = "Boston Naming Test";
        ques1 = new ArrayList<String>();
        ques2 = new ArrayList<String>();
        ques3 = new ArrayList<String>();
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
            if (reader.has("guide_word_2"))
                guideWord2 = reader.getString("guide_word_2");
            if (reader.has("guide_word_3"))
                guideWord3 = reader.getString("guide_word_3");
            if (reader.has("guide_word_4"))
                guideWord4 = reader.getString("guide_word_4");
            if (reader.has("record"))
                record = reader.getString("record");
            JSONArray questions = reader.getJSONArray("questions");
            for (int idx = 0; idx < questions.length(); ++idx)
            {
                JSONObject questionItem = questions.getJSONObject(idx);
                ques1.add(questionItem.getString("pair1"));
                ques2.add(questionItem.getString("pair2"));
                ques3.add(questionItem.getString("pair3"));
            }
            Log.d("boston_naming", String.valueOf("questions.length()=" + questions.length())); /////////////test
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

}
