package com.brainhealthtest.utility;


import com.brainhealthtest.questions.IncidentalLearningView;
import com.brainhealthtest.questions.IncidentalLearningViewB;
import com.brainhealthtest.questions.SingleIncidentalLearningView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class IncidentalLearningQuestion extends QuestionItem
{

    public String title, guideWord1, guideWord2;
    public List<String> ques1;
    public List<String> ques2;
    public List<String> ques3;
    public List<Integer> Score1 = new ArrayList<Integer>();
    public List<Integer> Score2 = new ArrayList<Integer>();
    public List<Integer> Score3 = new ArrayList<Integer>();

    public static int Score1_1 = -1, Score1_2 = -1, Score1_3 = -1, Score2_1 = -1, Score2_2 = -1, Score2_3 = -1, Score3_1 = -1, Score3_2 = -1, Score3_3 = -1;
    public List<Integer> userCorrectNumber = new ArrayList<Integer>();
    public List<String> Totalnum = new ArrayList<String>();
    public static int userError = 0, userError2 = 0;

    public int num;


    public void GetAnswer(IncidentalLearningView view)
    {

        Score1 = new ArrayList<Integer>();
        Score2 = new ArrayList<Integer>();
        Score3 = new ArrayList<Integer>();

        for (int i = 0; i < view.il_1.size(); ++i)
        {
            SingleIncidentalLearningView singleil_1 = view.il_1.get(i);

            int S1 = -1;
            int S2 = -1;
            int S3 = -1;
            S1 = singleil_1.score1;
            S2 = singleil_1.score2;
            S3 = singleil_1.score3;
            Score1.add(S1);
            Score2.add(S2);
            Score3.add(S3);
        }
        Totalnum.add(view.txt2_4_1.getText().toString());
        Totalnum.add(view.txt2_4_2.getText().toString());
        Totalnum.add(view.txt2_4_3.getText().toString());
        Totalnum.add(view.txt2_4_4.getText().toString());
        Totalnum.add(view.txt2_4_5.getText().toString());

        userError = view.errortype;

    }

    public void GetAnswer2(IncidentalLearningViewB view2)
    {

        userCorrectNumber = new ArrayList<Integer>();
        userCorrectNumber.add(view2.score1_1);
        userCorrectNumber.add(view2.score2_1);
        userCorrectNumber.add(view2.score3_1);
        userCorrectNumber.add(view2.score1_2);
        userCorrectNumber.add(view2.score2_2);
        userCorrectNumber.add(view2.score3_2);
        userCorrectNumber.add(view2.score1_3);
        userCorrectNumber.add(view2.score2_3);
        userCorrectNumber.add(view2.score3_3);

        Totalnum.add(view2.txt2_1.getText().toString());
        Totalnum.add(view2.txt2_2.getText().toString());
        Totalnum.add(view2.txt2_3.getText().toString());
        Totalnum.add(view2.txt2_4.getText().toString());

        userError2 = view2.errortype;

    }

    @Override
    protected void Load(JSONObject reader)
    {
        skip = false;
        type = "incidental_learning";
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
            if (reader.has("Title"))
            {
                title = reader.getString("Title");
                name = "Digit Symbol: Incidental Learning" + " " + title;
            }
            if (reader.has("num"))
                num = 1;//partB
            else num = 0;
            if (reader.has("guide_word_1"))
                guideWord1 = reader.getString("guide_word_1");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        try
        {
            JSONArray questions = reader.getJSONArray("questions");
            for (int idx = 0; idx < questions.length(); ++idx)
            {
                JSONObject questionItem = questions.getJSONObject(idx);
                ques1.add(questionItem.getString("pair1"));
                ques2.add(questionItem.getString("pair2"));
                ques3.add(questionItem.getString("pair3"));
            }
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
                JSONArray array1 = new JSONArray();
                if (num == 0)
                {
                    saver.put("ErrorType", userError);

                    for (int i = 0; i < Score1.size(); ++i)
                    {   ///size?
                        JSONObject userItem = new JSONObject();
                        userItem.put("TotalNumberChoice1", Score1.get(i));
                        userItem.put("TotalNumberChoice2", Score2.get(i));
                        userItem.put("TotalNumberChoice3", Score3.get(i));
                        array1.put(userItem);
                    }
                    saver.put("user_choices", array1);
                } else if (num == 1)
                {
                    saver.put("num", 1);
                    saver.put("ErrorType2", userError2);
                    JSONObject userItem = new JSONObject();

                    userItem.put("TotalNumberChoice(H1.1)", userCorrectNumber.get(0));
                    userItem.put("TotalNumberChoice(H2.1)", userCorrectNumber.get(1));
                    userItem.put("TotalNumberChoice(H3.1)", userCorrectNumber.get(2));
                    userItem.put("TotalNumberChoice(H1.2)", userCorrectNumber.get(3));
                    userItem.put("TotalNumberChoice(H2.2)", userCorrectNumber.get(4));
                    userItem.put("TotalNumberChoice(H3.2)", userCorrectNumber.get(5));
                    userItem.put("TotalNumberChoice(H1.3)", userCorrectNumber.get(6));
                    userItem.put("TotalNumberChoice(H2.3)", userCorrectNumber.get(7));
                    userItem.put("TotalNumberChoice(H3.3)", userCorrectNumber.get(8));

                    array1.put(userItem);

                    saver.put("user_choices", array1);
                }

                JSONArray array2 = new JSONArray();
                for (int i = 0; i < Totalnum.size(); ++i)
                {
                    JSONObject userItem = new JSONObject();
                    userItem.put("TotalNumberCorrect", Totalnum.get(i));
                    array2.put(userItem);
                }

                saver.put("user_answers", array2);

            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return saver;
    }

}
