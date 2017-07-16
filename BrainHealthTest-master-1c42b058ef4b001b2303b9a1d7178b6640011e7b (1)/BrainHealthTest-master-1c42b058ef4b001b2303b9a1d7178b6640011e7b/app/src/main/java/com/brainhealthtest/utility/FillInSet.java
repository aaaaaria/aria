package com.brainhealthtest.utility;


import com.brainhealthtest.questions.ChoicerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FillInSet extends QuestionItem
{
    public List<FillinQuestion> questionItemSet = new ArrayList<FillinQuestion>();
    public int fillInQuestionId;
    public int qwidth, width;
    public int score;
    public String down;
    public String des;
    public List<String> tables;
    public boolean draw;
    public List<Integer> userAnswer = new ArrayList<Integer>();
    public List<Integer> tableWidth;

    public QuestionItem GetQuestion()
    {
        return questionItemSet.get(fillInQuestionId);
    }

    public void GetAnswer(List<ChoicerView> view)
    {
        userAnswer = new ArrayList<Integer>();
        for (int i = 0; i < view.size(); ++i)
            userAnswer.add(view.get(i).userAnswer);
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
                saver.put("score", score);
                JSONArray array = new JSONArray();
                for (int i = 0; i < userAnswer.size(); ++i)
                {
                    array.put(userAnswer.get(i));
                }
                saver.put("user_answers", array);
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return saver;
    }

    public void Load(JSONObject reader)
    {
        skip = false;
        fillInQuestionId = 0;
        type = "fill_in_set";
        try
        {
            name = reader.getString("des");
        } catch (JSONException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        tables = new ArrayList<String>();
        tableWidth = new ArrayList<Integer>();

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
            if (reader.has("draw"))
                draw = true;
            des = reader.getString("des");
            down = reader.getString("down");
            guideWords = reader.getString("guide_words");
        } catch (Exception e)
        {

        }

        try
        {
            JSONArray jsonTable = reader.getJSONArray("table");
            JSONArray jsonTableWidth = reader.getJSONArray("table_width");
            for (int idx = 0; idx < jsonTable.length(); ++idx)
            {
                tables.add(jsonTable.getString(idx));
                tableWidth.add(jsonTableWidth.getInt(idx));
            }

        } catch (Exception e)
        {

        }

        try
        {
            qwidth = reader.getInt("qwidth");
            width = reader.getInt("width");
            JSONArray questions = reader.getJSONArray("questions");
            for (int idx = 0; idx < questions.length(); ++idx)
            {
                JSONObject questionItem = questions.getJSONObject(idx);
                String type = questionItem.getString("type");
                FillinQuestion newQuestion = null;
                if (type.equals("choice"))
                {
                    newQuestion = new ChoiceQuestion();
                    newQuestion.Load(questionItem);
                } else if (type.equals("blank"))
                {
                    newQuestion = new BlankQuestion();
                    newQuestion.Load(questionItem);
                }
                questionItemSet.add(newQuestion);
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
