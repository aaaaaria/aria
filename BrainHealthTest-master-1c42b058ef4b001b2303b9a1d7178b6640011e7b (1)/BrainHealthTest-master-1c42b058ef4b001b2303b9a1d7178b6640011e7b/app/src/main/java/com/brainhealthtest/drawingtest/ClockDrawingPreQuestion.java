
package com.brainhealthtest.drawingtest;


import com.brainhealthtest.voicetest.VoiceQuestionItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ClockDrawingPreQuestion extends VoiceQuestionItem
{
    public String title;

    public int choice = -1;
    public String observation;
    public List<String> ques;
    public List<String> userAnswer = new ArrayList<String>();
    public List<Integer> userChoice = new ArrayList<Integer>();
    public List<Integer> userSingleChoice = new ArrayList<Integer>();


    public void GetAnswer(ClockDrawingPreView view)
    {

        userAnswer.add(view.txt21_1.getText().toString());
        userAnswer.add(view.txt21_2.getText().toString());
        userAnswer.add(view.txt22_1.getText().toString());
        userAnswer.add(view.txt22_2.getText().toString());
        userAnswer.add(view.txt23_1.getText().toString());
        userAnswer.add(view.txt23_2.getText().toString());

        userChoice.add(view.score11);
        userChoice.add(view.score12);
        userChoice.add(view.score24);
        userChoice.add(view.score3);
        userChoice.add(view.score41);

        userSingleChoice = new ArrayList<Integer>();
        for (int i = 0; i < view.cdp.size(); ++i)
        {
            SingleClockDrawingPreView singleview = view.cdp.get(i);
            choice = singleview.Score;
            userSingleChoice.add(choice);
        }
        observation = view.Observations.getText().toString();

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

                JSONArray arraySingle = new JSONArray();
                for (int i = 0; i < userSingleChoice.size(); ++i)
                {
                    JSONObject userItem = new JSONObject();
                    userItem.put("SingleChoice", userSingleChoice.get(i));
                    arraySingle.put(userItem);
                }
                saver.put("user_single_choices", arraySingle);

                saver.put("Observation", observation);


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
        type = "clockdrawingpre";
        name = "Clock Drawing Test:Pre-drawn(Number Placement)";
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
            JSONArray questions = reader.getJSONArray("questions");
            for (int idx = 0; idx < questions.length(); idx++)
            {
                JSONObject questionItem = questions.getJSONObject(idx);
                ques.add(questionItem.getString("pair1"));
            }


        } catch (Exception e)
        {
            // TODO: handle exception
        }
    }


    @Override
    public String GetType()
    {
        return type;
    }


}
