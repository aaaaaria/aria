package com.brainhealthtest.utility;


import com.brainhealthtest.questions.SingleTrailerView;
import com.brainhealthtest.questions.TrailerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class TrailerQuestion extends QuestionItem
{

    public List<String> ques;
    public List<String> ans;
    public List<Integer> score;
    public int num;
    public int score1, score2;
    public String voiceFile;
    public String title, guideWord1, guideWord2;
    public String record;
    public List<Integer> userAnswer = new ArrayList<Integer>();
    public List<String> userWords = new ArrayList<String>();

    public void GetAnswer(TrailerView view)
    {
        score1 = view.GetScore1();
        score2 = view.GetScore1();
        userAnswer = new ArrayList<Integer>();
        userWords = new ArrayList<String>();
        for (int i = 0; i < view.trails.size(); ++i)
        {
            SingleTrailerView singleView = view.trails.get(i);
            userWords.add(singleView.text.getText().toString());
            int btnType = 0;
            if (singleView.score > 0)
                btnType = 1;
            else if (singleView.wordEditType > 0)
                btnType = singleView.wordEditType + 1;
            userAnswer.add(btnType);
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
                saver.put("score1", score1);
                saver.put("score2", score2);

                JSONArray array = new JSONArray();
                for (int i = 0; i < userAnswer.size(); ++i)
                {
                    JSONObject userItem = new JSONObject();
                    userItem.put("answer", userAnswer.get(i));
                    userItem.put("word", userWords.get(i));
                    array.put(userItem);
                }
                saver.put("user_answers", array);
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
        skip = false;
        score1 = 0;
        score2 = 0;
        type = "trailer";
        try
        {
            name = "VERBAL PAIRED ASSOCIATES (WMS)" + " " + reader.getString("title");
        } catch (JSONException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        ques = new ArrayList<String>();
        ans = new ArrayList<String>();
        score = new ArrayList<Integer>();
        try
        {
            if (reader.has("voice"))
                voiceFile = reader.getString("voice");
            if (reader.has("title"))
            {
                title = reader.getString("title");
                name = "VERBAL PAIRED ASSOCIATES (WMS)" + " " + title;
            }
            if (reader.has("guide_word_1"))
                guideWord1 = reader.getString("guide_word_1");
            if (reader.has("guide_word_2"))
                guideWord2 = reader.getString("guide_word_2");
            if (reader.has("record"))
                record = reader.getString("record");
            if (reader.has("num"))
            {
                num = 1;
                name = "VERBAL PAIRED ASSOCIATES (WMS),Delayed Recall";
            } else num = 0;
            JSONArray questions = reader.getJSONArray("questions");
            for (int idx = 0; idx < questions.length(); ++idx)
            {
                JSONObject questionItem = questions.getJSONObject(idx);
                score.add(questionItem.getInt("score"));
                ques.add(questionItem.getString("pair1"));
                ans.add(questionItem.getString("pair2"));
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

}
