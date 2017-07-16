package com.brainhealthtest.utility;

import org.json.JSONException;
import org.json.JSONObject;

public class BlankQuestion extends FillinQuestion
{
    public String answer;

    @Override
    protected void Load(JSONObject reader)
    {
        type = "blank";
        try
        {
            question = reader.getString("question");
        } catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
