
package com.brainhealthtest.drawingtest;


import com.brainhealthtest.voicetest.VoiceQuestionItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ClockDrawingPreQuestion2 extends VoiceQuestionItem
{
    public String title;
    public String observation2;
    public List<String> ques1, ques2, ques3;
    public List<String> userAnswer = new ArrayList<String>();
    public List<Integer> userChoice = new ArrayList<Integer>();
    public List<Integer> userSingleChoice1 = new ArrayList<Integer>();
    public List<Integer> userSingleChoice2 = new ArrayList<Integer>();
    public List<Integer> userSingleChoice3 = new ArrayList<Integer>();
    public int choice = -1;


    public void GetAnswer(ClockDrawingPreView2 view)
    {


        userAnswer.add(view.txt12_1.getText().toString());
        userAnswer.add(view.txt12_2.getText().toString());
        userAnswer.add(view.txt16.getText().toString());
        userAnswer.add(view.txt17.getText().toString());

        userChoice.add(view.score12);
        userChoice.add(view.score13);
        userChoice.add(view.score14);
        userChoice.add(view.score16);
        userChoice.add(view.score17);
        userChoice.add(view.score31);

        userSingleChoice1 = new ArrayList<Integer>();
        for (int i = 0; i < view.cdp2_1.size(); ++i)
        {
            SingleClockDrawingPreView2 singleview1 = view.cdp2_1.get(i);
            choice = singleview1.Score;
            userSingleChoice1.add(choice);
        }
        userSingleChoice2 = new ArrayList<Integer>();
        for (int i = 0; i < view.cdp2_2.size(); ++i)
        {
            SingleClockDrawingPreView2 singleview2 = view.cdp2_2.get(i);
            choice = singleview2.Score;
            userSingleChoice2.add(choice);
        }
        userSingleChoice3 = new ArrayList<Integer>();
        for (int i = 0; i < view.cdp2_3.size(); ++i)
        {
            SingleClockDrawingPreView2 singleview3 = view.cdp2_3.get(i);
            choice = singleview3.Score;
            userSingleChoice3.add(choice);
        }

        observation2 = view.Observations.getText().toString();

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

                JSONArray arraySingle1 = new JSONArray();
                for (int i = 0; i < userSingleChoice1.size(); ++i)
                {
                    JSONObject userItem = new JSONObject();
                    userItem.put("SingleChoice1", userSingleChoice1.get(i));
                    arraySingle1.put(userItem);
                }
                saver.put("user_single_choices1", arraySingle1);
                JSONArray arraySingle2 = new JSONArray();
                for (int i = 0; i < userSingleChoice2.size(); ++i)
                {
                    JSONObject userItem = new JSONObject();
                    userItem.put("SingleChoice2", userSingleChoice2.get(i));
                    arraySingle2.put(userItem);
                }
                saver.put("user_single_choices2", arraySingle2);
                JSONArray arraySingle3 = new JSONArray();
                for (int i = 0; i < userSingleChoice3.size(); ++i)
                {
                    JSONObject userItem = new JSONObject();
                    userItem.put("SingleChoice3", userSingleChoice3.get(i));
                    arraySingle3.put(userItem);
                }
                saver.put("user_single_choices3", arraySingle3);

                saver.put("Observation", observation2);
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
        type = "clockdrawingpre2";
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
            JSONArray questions1 = reader.getJSONArray("questions1");
            for (int idx = 0; idx < questions1.length(); idx++)
            {
                JSONObject questionItem = questions1.getJSONObject(idx);
                ques1.add(questionItem.getString("pair1"));
            }
            JSONArray questions2 = reader.getJSONArray("questions2");
            for (int idx = 0; idx < questions2.length(); idx++)
            {
                JSONObject questionItem = questions2.getJSONObject(idx);
                ques2.add(questionItem.getString("pair1"));
            }
            JSONArray questions3 = reader.getJSONArray("questions3");
            for (int idx = 0; idx < questions3.length(); idx++)
            {
                JSONObject questionItem = questions3.getJSONObject(idx);
                ques3.add(questionItem.getString("pair1"));
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
