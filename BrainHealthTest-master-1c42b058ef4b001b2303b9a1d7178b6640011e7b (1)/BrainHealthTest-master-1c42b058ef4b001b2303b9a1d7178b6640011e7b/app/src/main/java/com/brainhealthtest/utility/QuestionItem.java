package com.brainhealthtest.utility;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QuestionItem
{

    protected String type;
    protected String name;
    protected String index;
    public String guideWords;
    public boolean skip = false;
    public List<Integer> skipQues = new ArrayList<Integer>();

    protected void Load(JSONObject reader)
    {

    }

    public String GetType()
    {
        return type;
    }

}
