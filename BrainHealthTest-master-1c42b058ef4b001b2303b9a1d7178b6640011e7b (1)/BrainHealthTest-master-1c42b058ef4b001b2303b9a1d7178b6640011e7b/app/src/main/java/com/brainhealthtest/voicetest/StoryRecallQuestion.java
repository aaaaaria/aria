package com.brainhealthtest.voicetest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class StoryRecallQuestion extends VoiceQuestionItem
{
    String storyFile;
    String recordName;
    public int delayed;

    @Override
    protected void Load(JSONObject reader)
    {
        skip = false;
        type = "story_recall";
        questionId = 0;
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
        if (reader.has("delayed"))
        {
            delayed = 1;
            name = "LOGICAL MEMORY (WMS), Delayed Recall";
        } else
        {
            delayed = 0;
            name = "LOGICAL MEMORY (WMS), Immediate Recall";
        }
        try
        {
            recordName = reader.getString("record");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        try
        {
            guideWords = reader.getString("guide_words");
            storyFile = reader.getString("story");
        } catch (JSONException e)
        {
            e.printStackTrace();
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
                saver.put("record", "recorded");
            }
        } catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return saver;
    }

    @Override
    public String GetType()
    {
        return type;
    }

    @Override
    public int Calculate(List<String> words)
    {
        return 0;
    }

}
