package com.brainhealthtest.utility;


import com.brainhealthtest.questions.DigitSymbolView;
import com.brainhealthtest.questions.SingleDigitSymbolView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DigitSymbolQuestion extends QuestionItem
{
    public List<String> ques1, ques2;
    public String record;
    public String userAnswerTime;
    public List<String> userAnswerR = new ArrayList<String>();
    public List<String> userAnswerL = new ArrayList<String>();
    public List<String> TotalnumCorrect = new ArrayList<String>();
    public List<String> TotalnumIncorrect = new ArrayList<String>();

    public void GetAnswer(DigitSymbolView digitSview)
    {
        userAnswerTime = digitSview.time.getText().toString(); //get_time

        TotalnumCorrect.add(digitSview.txt4_1.getText().toString());
        TotalnumCorrect.add(digitSview.txt4_2.getText().toString());
        TotalnumCorrect.add(digitSview.txt4_3.getText().toString());
        TotalnumCorrect.add(digitSview.txt4_4.getText().toString());
        TotalnumCorrect.add(digitSview.txt4_5.getText().toString());
        TotalnumCorrect.add(digitSview.txt4_6.getText().toString());
        TotalnumCorrect.add(digitSview.txt4_7.getText().toString());
        TotalnumCorrect.add(digitSview.txt4_8.getText().toString());

        TotalnumIncorrect.add(digitSview.txt5_1.getText().toString());
        TotalnumIncorrect.add(digitSview.txt5_2.getText().toString());
        TotalnumIncorrect.add(digitSview.txt5_3.getText().toString());
        TotalnumIncorrect.add(digitSview.txt5_4.getText().toString());
        TotalnumIncorrect.add(digitSview.txt5_5.getText().toString());
        TotalnumIncorrect.add(digitSview.txt5_6.getText().toString());
        TotalnumIncorrect.add(digitSview.txt5_7.getText().toString());
        TotalnumIncorrect.add(digitSview.txt5_8.getText().toString());

        userAnswerR = new ArrayList<String>();
        userAnswerL = new ArrayList<String>();
        for (int i = 0; i < digitSview.ds1.size(); ++i)
        {
            SingleDigitSymbolView singleView = digitSview.ds1.get(i);
            userAnswerL.add(singleView.answertxt1.getText().toString());
            userAnswerR.add(singleView.answertxt2.getText().toString());
        }
        for (int i = 0; i < digitSview.ds2.size(); ++i)
        {
            SingleDigitSymbolView singleView = digitSview.ds2.get(i);
            userAnswerL.add(singleView.answertxt1.getText().toString());
            userAnswerR.add(singleView.answertxt2.getText().toString());
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

                JSONArray array1 = new JSONArray();
                for (int i = 0; i < TotalnumCorrect.size(); ++i)
                {
                    JSONObject userItem = new JSONObject();
                    userItem.put("TotalnumberCorrect1", TotalnumCorrect.get(i));
                    array1.put(userItem);
                }
                saver.put("Totalnumber Correct", array1);

                JSONArray array2 = new JSONArray();
                for (int i = 0; i < TotalnumIncorrect.size(); ++i)
                {   ///size?
                    JSONObject userItem = new JSONObject();
                    userItem.put("TotalnumberIncorrect1", TotalnumIncorrect.get(i));
                    array2.put(userItem);
                }
                saver.put("Totalnumber Incorrect", array2);

                JSONArray array3 = new JSONArray();
                for (int i = 0; i < userAnswerL.size(); ++i)
                {   ///size?
                    JSONObject userItem = new JSONObject();
                    userItem.put("Error_Frequency(L)", userAnswerL.get(i));
                    array3.put(userItem);
                }
                saver.put("Error_Frequency", array3);

                JSONArray array4 = new JSONArray();
                for (int i = 0; i < 2; ++i)
                {   ///size?
                    JSONObject userItem = new JSONObject();
                    userItem.put("Self_Corrections_Frequency(R)", userAnswerR.get(i));
                    array4.put(userItem);
                }
                saver.put("Self_Corrections_Frequency", array4);

                saver.put("Time", userAnswerTime);//save_time

            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return saver;

    }

    protected void Load(JSONObject reader)
    {
        skip = false;
        type = "digit_symbol";
        name = "Digit Symbol Test";//?
        ques1 = new ArrayList<String>();
        ques2 = new ArrayList<String>();
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
            if (reader.has("record"))
                record = reader.getString("record");
            JSONArray questions1 = reader.getJSONArray("question1");
            for (int idx = 0; idx < questions1.length(); ++idx)
            {
                JSONObject questionItem = questions1.getJSONObject(idx);
                ques1.add(questionItem.getString("pair1"));
                ques2.add(questionItem.getString("pair2"));
            }

            JSONArray questions2 = reader.getJSONArray("question2");
            for (int idx = 0; idx < questions2.length(); ++idx)
            {
                JSONObject questionItem = questions2.getJSONObject(idx);
                ques1.add(questionItem.getString("pair1"));
                ques2.add(questionItem.getString("pair2"));
            }

        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

}
