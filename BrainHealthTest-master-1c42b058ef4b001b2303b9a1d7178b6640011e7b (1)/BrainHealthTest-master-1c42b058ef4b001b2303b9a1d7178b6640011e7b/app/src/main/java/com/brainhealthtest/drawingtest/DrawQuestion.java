package com.brainhealthtest.drawingtest;


import com.brainhealthtest.voicetest.VoiceQuestionItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DrawQuestion extends VoiceQuestionItem
{
    String picName;
    String picNameA, picNameB, picNameC;
    public boolean view;

    public JSONObject Save()
    {
        JSONObject saver = new JSONObject();
        try
        {
            if (skip)
                saver.put("skip", 1);
            else
                saver.put("draw", "A/B/C.jpg");
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
        type = "draw";
        name = "Visual Reproductions �C Immediate Recall (WMS)";
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
            guideWords = reader.getString("guide_words");
            if (reader.has("pic_name"))
            {
                view = true;
                picName = reader.getString("pic_name");
                picNameA = reader.getString("pic_nameA");
                picNameB = reader.getString("pic_nameB");
                picNameC = reader.getString("pic_nameC");
            } else
                view = false;
        } catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public String GetType()
    {
        return type;
    }

}
