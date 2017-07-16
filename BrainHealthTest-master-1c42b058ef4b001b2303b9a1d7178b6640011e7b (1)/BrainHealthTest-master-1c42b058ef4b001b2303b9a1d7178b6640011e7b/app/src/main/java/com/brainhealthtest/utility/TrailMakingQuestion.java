package com.brainhealthtest.utility;


import com.brainhealthtest.questions.TrailMakingView;
import com.brainhealthtest.questions.TrailMakingViewBelance;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class TrailMakingQuestion extends QuestionItem
{

    public List<String> ques;
    public List<String> ans;
    public int num;
    public String title, guideWord1, guideWord2;
    public String record;
    public List<String> userAnswer = new ArrayList<String>();
    public List<Integer> userChoice = new ArrayList<Integer>();

    public void GetAnswer(TrailMakingView view)
    {

        userAnswer.add(view.txt1.getText().toString());
        userAnswer.add(view.txt2.getText().toString());
        userAnswer.add(view.txt3.getText().toString());
        userAnswer.add(view.txt4.getText().toString());
        userAnswer.add(view.txt5.getText().toString());
        userAnswer.add(view.txt6.getText().toString());
        userAnswer.add(view.txt7.getText().toString());

        userAnswer.add(view.txt8.getText().toString());
        userAnswer.add(view.txt9.getText().toString());

        userChoice.add(view.score1);
        userChoice.add(view.score2_1);
        userChoice.add(view.score2_2);

    }

    public void GetAnswer2(TrailMakingViewBelance view2)
    {

        userAnswer.add(view2.txt1.getText().toString());
        userAnswer.add(view2.txt2.getText().toString());
        userAnswer.add(view2.txt3.getText().toString());
        userAnswer.add(view2.txt3_1.getText().toString());
        userAnswer.add(view2.txt4.getText().toString());
        userAnswer.add(view2.txt5.getText().toString());
        userAnswer.add(view2.txt3_2.getText().toString());
        userAnswer.add(view2.txt6.getText().toString());
        userAnswer.add(view2.txt7.getText().toString());

        userAnswer.add(view2.txt8.getText().toString());
        userAnswer.add(view2.txt9.getText().toString());

        userChoice.add(view2.score1);
        userChoice.add(view2.score2_1);
        userChoice.add(view2.score2_2);

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
                if (num == 1)
                {
                    saver.put("num", 1);
                }
                JSONArray array = new JSONArray();
                for (int i = 0; i < userAnswer.size(); ++i)
                {
                    JSONObject userItem = new JSONObject();
                    userItem.put("AnswerTxt", userAnswer.get(i));
                    array.put(userItem);
                }
                saver.put("user_answers", array);

                JSONArray array2 = new JSONArray();
                for (int i = 0; i < userChoice.size(); ++i)
                {
                    JSONObject userItem = new JSONObject();
                    userItem.put("AnswerChoice", userChoice.get(i));
                    array2.put(userItem);
                }
                saver.put("user_choices", array2);

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
        type = "trail_making";
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
            if (reader.has("Title"))
            {
                title = reader.getString("Title");
                name = "Trail Making Test" + " " + title;
            }
            if (reader.has("num"))
                num = 1;//partB
            else num = 0;
            if (reader.has("guide_word_1"))
                guideWord1 = reader.getString("guide_word_1");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }


}
