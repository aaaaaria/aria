package com.brainhealthtest.utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChoiceQuestion extends FillinQuestion
{
    public List<String> choices;
    public List<Integer> score;
    public List<Integer> jump;
    public int answer;
    public int totScore;
    public boolean draw;

    public JSONObject Save()
    {
        JSONObject saver = new JSONObject();
        try
        {
            if (skip)
                saver.put("skip", 1);
            else
                saver.put("score", totScore);
        } catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return saver;
    }

    @Override
    protected void Load(JSONObject reader)
    {
        skip = false;
        choices = new ArrayList<String>();
        score = new ArrayList<Integer>();
        jump = new ArrayList<Integer>();
        type = "choice";
        draw = false;
        try
        {
            if (reader.has("draw"))
                draw = true;
            question = reader.getString("question");
            JSONArray questionChoices = reader.getJSONArray("choices");
            for (int i = 0; i < questionChoices.length(); i++)
            {
                choices.add(questionChoices.getJSONObject(i).getString("choice"));
                score.add(questionChoices.getJSONObject(i).getInt("score"));
                if (score.get(i) > 0)
                    answer = i;
                if (questionChoices.getJSONObject(i).has("jump"))
                    jump.add(questionChoices.getJSONObject(i).getInt("jump"));
                else
                    jump.add(1);
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    void SetAnswer(int t)
    {
        answer = t;
    }
}
