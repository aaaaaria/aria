package com.brainhealthtest.utility;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;


import com.brainhealthtest.questions.ReadingTestView;
import com.brainhealthtest.questions.SingleReadingTestView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ReadingTestQuestion extends QuestionItem
{


    public List<String> ques1;
    public List<String> ques2;
    public List<Integer> score;///////////
    public String guideWord1, guideWord2, guideWord3, guideWord4;
    public String record;
    public int flag = 0, flag2 = 0;
    public int fail;
    public List<Integer> answer = new ArrayList<Integer>();
    public List<Integer> errortype = new ArrayList<Integer>();

    public void GetAnswer(ReadingTestView view)
    {

        for (int i = 0; i < view.rt.size(); ++i)
        {
            SingleReadingTestView singleView = view.rt.get(i);

            flag = singleView.Score;
            flag2 = singleView.Type;
            answer.add(flag);
            errortype.add(flag2);
        }
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    public void GetTempAnswer(ReadingTestView view)
    {
        fail = 0;
        for (int i = 0; i < view.rt.size(); ++i)
        {
            SingleReadingTestView singleView = view.rt.get(i);
            int btnScore = singleView.Score;
            if ((btnScore == 0 || btnScore == 6) && fail < 9)
                fail = fail + 1;
            else if ((btnScore == 0 || btnScore == 6) && fail == 9)
                view.rt.get(i + 1).scoreN.callOnClick();
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
                for (int i = 0; i < answer.size(); ++i)
                {
                    JSONObject userItem = new JSONObject();
                    if (answer.get(i) == 0)
                        userItem.put("errortype", errortype.get(i));
                    userItem.put("score", answer.get(i));
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
        type = "reading_test";
        name = "Reading Test (WRAT 3rd Edition)";
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
            }
            Log.d("reading_test", String.valueOf("questions.length()=" + questions.length())); /////////////test
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }


}
