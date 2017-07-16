package com.brainhealthtest.drawingtest;


import com.brainhealthtest.voicetest.VoiceQuestionItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DrawClockQuestion extends VoiceQuestionItem
{
    public String title;
    public int num;
    public String userTime, observation, userTime2, observation2;
    public int RotatedScore = -1, RotatedScore2 = -1;
    public int SelfCorrectError = -1, SelfCorrectError2 = -1;
    public int ReminderTimeScore = -1, ReminderTimeScore2 = -1;


    public void GetAnswer(DrawClockView view)
    {

        userTime = view.time.getText().toString();
        RotatedScore = view.score1;
        SelfCorrectError = view.score2;
        ReminderTimeScore = view.score3;
        observation = view.Observations.getText().toString();

    }

    public void GetAnswer2(DrawClockView view2)
    {

        userTime2 = view2.time.getText().toString();
        RotatedScore2 = view2.score1;
        SelfCorrectError2 = view2.score2;
        observation2 = view2.Observations.getText().toString();

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
                    saver.put("Time2", userTime2);
                    saver.put("Rotated Paper2", RotatedScore2);
                    saver.put("Self Correct Error2", SelfCorrectError2);
                    saver.put("Describe Observations2", observation2);

                }
                if (num == 0)
                {
                    saver.put("Time", userTime);
                    saver.put("Rotated Paper", RotatedScore);
                    saver.put("Reminder Time", ReminderTimeScore);
                    saver.put("Self Correct Error", SelfCorrectError);
                    saver.put("Describe Observations", observation);

                }
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
        type = "draw_clock";

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
                name = "Clock Drawing Test" + " " + title;
            }

            if (reader.has("num"))
                num = 1;//partB
            else num = 0;
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
