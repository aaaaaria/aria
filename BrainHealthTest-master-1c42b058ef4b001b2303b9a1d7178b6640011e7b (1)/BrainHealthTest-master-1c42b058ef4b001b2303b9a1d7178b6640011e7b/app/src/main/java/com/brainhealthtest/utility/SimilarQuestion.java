package com.brainhealthtest.utility;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;


import com.brainhealthtest.questions.SimilarView;
import com.brainhealthtest.questions.SingleSimilarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SimilarQuestion extends QuestionItem
{

    public List<String> ques1;
    public List<String> ques2;
    public List<Integer> score;
    public int num;
    public int fail;
    public String voiceFile;
    public String guideWord1, guideWord2;
    public String record;
    public List<Integer> userAnswer = new ArrayList<Integer>();
    public List<Integer> userType = new ArrayList<Integer>();
    public List<String> userWords = new ArrayList<String>();

    public void GetAnswer(SimilarView view)
    {
        userAnswer = new ArrayList<Integer>();
        userType = new ArrayList<Integer>();
        userWords = new ArrayList<String>();
        for (int i = 0; i < view.similars.size(); ++i)
        {
            SingleSimilarView singleView = view.similars.get(i);
            userWords.add(singleView.text.getText().toString());
            int btnType = singleView.wordEditType;
            userType.add(btnType);
            int btnScore = singleView.score;
            userAnswer.add(btnScore);
        }
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    public void GetTempAnswer(SimilarView view)
    {
        fail = 0;
        for (int i = 0; i < view.similars.size(); ++i)
        {
            SingleSimilarView singleView = view.similars.get(i);
            int btnScore = singleView.score;
            if ((btnScore == 0 || btnScore == 6) && fail < 3)
                fail = fail + 1;
            else if ((btnScore == 0 || btnScore == 6) && fail == 3)
                view.similars.get(i + 1).NoScore.callOnClick();
            else if (btnScore != 0 && btnScore != 6)
                fail = 0;

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
                    userItem.put("score", userAnswer.get(i));
                    userItem.put("answer", userWords.get(i));
                    userItem.put("type", userType.get(i));
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
        type = "similar";
        name = "Similarities (WAIS)";
        ques1 = new ArrayList<String>();
        ques2 = new ArrayList<String>();
        score = new ArrayList<Integer>();
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
            if (reader.has("record"))
                record = reader.getString("record");
            JSONArray questions = reader.getJSONArray("questions");
            for (int idx = 0; idx < questions.length(); ++idx)
            {
                JSONObject questionItem = questions.getJSONObject(idx);
                ques1.add(questionItem.getString("pair1"));
                ques2.add(questionItem.getString("pair2"));
            }
            Log.d("similar", String.valueOf("questions.length()=" + questions.length())); /////////////test
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

}
