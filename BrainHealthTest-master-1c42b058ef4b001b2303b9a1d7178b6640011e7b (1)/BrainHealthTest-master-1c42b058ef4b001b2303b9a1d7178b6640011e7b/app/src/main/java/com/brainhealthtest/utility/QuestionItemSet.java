package com.brainhealthtest.utility;

import android.app.Application;
import android.os.Environment;
import android.util.Log;


import com.brainhealthtest.drawingtest.ClockDrawingPreActivity;
import com.brainhealthtest.drawingtest.ClockDrawingPreActivity2;
import com.brainhealthtest.drawingtest.ClockDrawingPreQuestion;
import com.brainhealthtest.drawingtest.ClockDrawingPreQuestion2;
import com.brainhealthtest.drawingtest.DrawClockActivity;
import com.brainhealthtest.drawingtest.DrawClockQuestion;
import com.brainhealthtest.drawingtest.DrawQuestion;
import com.brainhealthtest.drawingtest.ViewImgActivity;
import com.brainhealthtest.questions.BalanceActivity;
import com.brainhealthtest.questions.BlockDesignActivity;
import com.brainhealthtest.questions.BostonNamingActivity;
import com.brainhealthtest.questions.ChoiceTesterActivity;
import com.brainhealthtest.questions.CookieTheftActivity;
import com.brainhealthtest.questions.DigitSymbolActivity;
import com.brainhealthtest.questions.EndActivity;
import com.brainhealthtest.questions.FingerActivity;
import com.brainhealthtest.questions.HooperVisualActivity;
import com.brainhealthtest.questions.IncidentalLearningActivity;
import com.brainhealthtest.questions.InformationActivity;
import com.brainhealthtest.questions.MathFluencyActivity;
import com.brainhealthtest.questions.ReadingTestActivity;
import com.brainhealthtest.questions.SimilarActivity;
import com.brainhealthtest.questions.TrailMakingActivity;
import com.brainhealthtest.questions.TrailerActivity;
import com.brainhealthtest.questions.VerbalFluencyActivity;
import com.brainhealthtest.voicetest.DigitSpanActivity;
import com.brainhealthtest.voicetest.DigitSpanQuestion;
import com.brainhealthtest.voicetest.StoryRecallActivity;
import com.brainhealthtest.voicetest.StoryRecallQuestion;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class QuestionItemSet extends Application
{
    public String folderName;
    public List<QuestionItem> questionItemSet = new ArrayList<QuestionItem>();
    public int questionId;
    private JSONObject oj, reader;
    private JSONArray answer;
    public String testerid, participanterid;

    public void onCreate()
    {
        super.onCreate();
        Fresco.initialize(this);
    }

    public String Save()
    {

        JSONArray saver = new JSONArray();
        for (int idx = 0; idx < questionItemSet.size(); ++idx)
        {
            JSONObject tmpJson = new JSONObject();
            try
            {
                tmpJson.put("index", idx);
            } catch (JSONException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            saver.put(tmpJson);

        }
        JSONObject obj = new JSONObject();
        try
        {
            obj.put("answers", saver);
        } catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.d("temp", obj.toString());////////////////////////
        return obj.toString();

    }

    public String Save1(String answerJSON, int idx)    //save answer
    {
        try
        {

            reader = new JSONObject(answerJSON);
            answer = reader.getJSONArray("answers");
            int length = answer.length();
            for (int i = 0; i < length; i++)
            {
                oj = answer.getJSONObject(i);
                String type = oj.getString("index");
                if (idx == Integer.parseInt(type))
                    break;
            }
            Log.d("temp", oj.toString());
        } catch (JSONException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        JSONArray saver = new JSONArray();
        String typeName = questionItemSet.get(idx).type;
        JSONObject tmpJson = new JSONObject();
        if (typeName.equals("fill_in_set"))
        {
            tmpJson = ((FillInSet) (questionItemSet.get(idx))).Save();
        } else if (typeName.equals("story_recall"))
        {
            tmpJson = ((StoryRecallQuestion) (questionItemSet.get(idx))).Save();
        } else if (typeName.equals("trailer"))
        {
            tmpJson = ((TrailerQuestion) (questionItemSet.get(idx))).Save();
        } else if (typeName.equals("draw"))
        {
            tmpJson = ((DrawQuestion) (questionItemSet.get(idx))).Save();
        } else if (typeName.equals("digit_span"))
        {
            tmpJson = ((DigitSpanQuestion) (questionItemSet.get(idx))).Save();
        } else if (typeName.equals("similar"))
        {
            tmpJson = ((SimilarQuestion) (questionItemSet.get(idx))).Save();
        } else if (typeName.equals("draw_clock"))
        {
            tmpJson = ((DrawClockQuestion) (questionItemSet.get(idx))).Save();
        } else if (typeName.equals("verbal_fluency"))
        {
            tmpJson = ((Verbal_fluencyQuestion) (questionItemSet.get(idx))).Save();
        } else if (typeName.equals("boston_naming"))
        {
            tmpJson = ((Boston_namingQuestion) (questionItemSet.get(idx))).Save();
        } else if (typeName.equals("trail_making"))
        {
            tmpJson = ((TrailMakingQuestion) (questionItemSet.get(idx))).Save();
        } else if (typeName.equals("reading_test"))
        {
            tmpJson = ((ReadingTestQuestion) (questionItemSet.get(idx))).Save();
        } else if (typeName.equals("finger"))
        {
            tmpJson = ((FingerQuestion) (questionItemSet.get(idx))).Save();
        } else if (typeName.equals("information"))
        {
            tmpJson = ((InformationQuestion) (questionItemSet.get(idx))).Save();
        } else if (typeName.equals("cookietheft"))
        {
            tmpJson = ((CookieTheftQuestion) (questionItemSet.get(idx))).Save();
        } else if (typeName.equals("hoopervisual"))
        {
            tmpJson = ((HooperVisualQuestion) (questionItemSet.get(idx))).Save();
        } else if (typeName.equals("balance"))
        {
            tmpJson = ((BalanceQuestion) (questionItemSet.get(idx))).Save();
        } else if (typeName.equals("block"))
        {
            //tmpJson = ((BlockQuestion)(questionItemSet.get(idx))).Save();
        } else if (typeName.equals("digit_symbol"))
        {
            tmpJson = ((DigitSymbolQuestion) (questionItemSet.get(idx))).Save();
        } else if (typeName.equals("incidental_learning"))
        {
            tmpJson = ((IncidentalLearningQuestion) (questionItemSet.get(idx))).Save();
        } else if (typeName.equals("math_fluency"))
        {
            tmpJson = ((MathFluencyQuestion) (questionItemSet.get(idx))).Save();
        } else if (typeName.equals("clockdrawingpre"))
        {
            tmpJson = ((ClockDrawingPreQuestion) (questionItemSet.get(idx))).Save();
        } else if (typeName.equals("clockdrawingpre2"))
        {
            tmpJson = ((ClockDrawingPreQuestion2) (questionItemSet.get(idx))).Save();
        }
        try
        {
            if (tmpJson.has("skip"))
            {
            } else
                answer.getJSONObject(idx).put("Answer", tmpJson);
            reader.put("answers", answer);
        } catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return reader.toString();
    }


    public void Load(String buffer)
    {
        questionId = 0;
        try
        {
            JSONObject reader = new JSONObject(buffer);
            JSONArray questions = reader.getJSONArray("question_items");
            Log.d("aliyun", String.valueOf("question_items.length" + questions.length())); ///////////////////////////////////////////////////test
            for (int idx = 0; idx < questions.length(); ++idx)
            {
                JSONObject questionItem = questions.getJSONObject(idx);
                String type = questionItem.getString("type");
                QuestionItem newQuestion = null;

                if (type.equals("digit_span"))
                {
                    newQuestion = new DigitSpanQuestion();
                    newQuestion.Load(questionItem);
                } else if (type.equals("fill_in_set"))
                {
                    newQuestion = new FillInSet();
                    newQuestion.Load(questionItem);
                } else if (type.equals("story_recall"))
                {
                    newQuestion = new StoryRecallQuestion();
                    newQuestion.Load(questionItem);
                } else if (type.equals("draw"))
                {
                    newQuestion = new DrawQuestion();
                    newQuestion.Load(questionItem);
                } else if (type.equals("trailer"))
                {
                    newQuestion = new TrailerQuestion();
                    newQuestion.Load(questionItem);
                } else if (type.equals("similar"))
                {
                    newQuestion = new SimilarQuestion();
                    newQuestion.Load(questionItem);
                } else if (type.equals("draw_clock"))
                {
                    newQuestion = new DrawClockQuestion();
                    newQuestion.Load(questionItem);
                } else if (type.equals("verbal_fluency"))
                {
                    newQuestion = new Verbal_fluencyQuestion();
                    newQuestion.Load(questionItem);
                } else if (type.equals("boston_naming"))
                {
                    newQuestion = new Boston_namingQuestion();
                    newQuestion.Load(questionItem);
                } else if (type.equals("trail_making"))
                {
                    newQuestion = new TrailMakingQuestion();
                    newQuestion.Load(questionItem);
                } else if (type.equals("reading_test"))
                {
                    newQuestion = new ReadingTestQuestion();
                    newQuestion.Load(questionItem);
                } else if (type.equals("finger"))
                {
                    newQuestion = new FingerQuestion();
                    newQuestion.Load(questionItem);
                } else if (type.equals("information"))
                {
                    newQuestion = new InformationQuestion();
                    newQuestion.Load(questionItem);
                } else if (type.equals("cookietheft"))
                {
                    newQuestion = new CookieTheftQuestion();
                    newQuestion.Load(questionItem);
                } else if (type.equals("hoopervisual"))
                {
                    newQuestion = new HooperVisualQuestion();
                    newQuestion.Load(questionItem);
                } else if (type.equals("balance"))
                {
                    newQuestion = new BalanceQuestion();
                    newQuestion.Load(questionItem);
                } else if (type.equals("block"))
                {
                    Log.d("load aliyun", "enter type:block");
                    newQuestion = new BlockQuestion();
                    newQuestion.Load(questionItem);
                    Log.d("load aliyun2", "end type:block");

                } else if (type.equals("digit_symbol"))
                {
                    newQuestion = new DigitSymbolQuestion();
                    newQuestion.Load(questionItem);
                } else if (type.equals("incidental_learning"))
                {
                    newQuestion = new IncidentalLearningQuestion();
                    newQuestion.Load(questionItem);
                } else if (type.equals("math_fluency"))
                {
                    newQuestion = new MathFluencyQuestion();
                    newQuestion.Load(questionItem);
                } else if (type.equals("clockdrawingpre"))
                {
                    newQuestion = new ClockDrawingPreQuestion();
                    newQuestion.Load(questionItem);
                } else if (type.equals("clockdrawingpre2"))
                {
                    newQuestion = new ClockDrawingPreQuestion2();
                    newQuestion.Load(questionItem);
                }
                questionItemSet.add(newQuestion);
                System.out.println(newQuestion.type);
                Log.d("aliyun", "idx= " + String.valueOf(idx)); ////////////////////////////////////////////////////test
                Log.d("aliyun", "content " + String.valueOf(questionItemSet.size()));
            }
        } catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Class<?> GetActivity()
    {
        Log.d("aliyun", "questionID=" + String.valueOf(questionId)); //////////////////////////////////////////////////////////////test
        Log.d("aliyun", "questionItemSet.size=" + String.valueOf(questionItemSet.size())); ///////////////////////////////////////////////////test

        if (questionId >= questionItemSet.size())
            return EndActivity.class;
        String typeName = questionItemSet.get(questionId).type;
        Log.d("aliyun", "questionItemSet.name=" + typeName); ////////////////////////////////////////////////////////test
        if (typeName.equals("digit_span"))
        {
            return DigitSpanActivity.class;
        } else if (typeName.equals("fill_in_set"))
        {
            return ChoiceTesterActivity.class;
        } else if (typeName.equals("story_recall"))
        {
            return StoryRecallActivity.class;
        } else if (typeName.equals("draw"))
        {
            DrawQuestion question = (DrawQuestion) questionItemSet.get(questionId);
            if (question.view)
            {
                return ViewImgActivity.class;
            } else
                return EndActivity.class;
        } else if (typeName.equals("trailer"))
        {
            return TrailerActivity.class;
        } else if (typeName.equals("similar"))
        {
            return SimilarActivity.class;
        } else if (typeName.equals("draw_clock"))
        {
            return DrawClockActivity.class;
        } else if (typeName.equals("verbal_fluency"))
        {
            return VerbalFluencyActivity.class;
        } else if (typeName.equals("trail_making"))
        {
            return TrailMakingActivity.class;
        } else if (typeName.equals("boston_naming"))
        {
            return BostonNamingActivity.class;
        } else if (typeName.equals("reading_test"))
        {
            return ReadingTestActivity.class;
        } else if (typeName.equals("finger"))
        {
            return FingerActivity.class;
        } else if (typeName.equals("information"))
        {
            return InformationActivity.class;
        } else if (typeName.equals("cookietheft"))
        {
            return CookieTheftActivity.class;
        } else if (typeName.equals("hoopervisual"))
        {
            return HooperVisualActivity.class;
        } else if (typeName.equals("balance"))
        {
            return BalanceActivity.class;
        } else if (typeName.equals("block"))
        {
            return BlockDesignActivity.class;

        } else if (typeName.equals("digit_symbol"))
        {
            return DigitSymbolActivity.class;
        } else if (typeName.equals("incidental_learning"))
        {
            return IncidentalLearningActivity.class;
        } else if (typeName.equals("math_fluency"))
        {
            return MathFluencyActivity.class;
        } else if (typeName.equals("clockdrawingpre"))
        {
            return ClockDrawingPreActivity.class;
        } else if (typeName.equals("clockdrawingpre2"))
        {
            return ClockDrawingPreActivity2.class;
        }
        return EndActivity.class;
    }

    public String GetGuideWord()
    {
        return questionItemSet.get(questionId).guideWords;
    }

    public String GetType()
    {
        return questionItemSet.get(questionId).type;
    }

    public QuestionItem GetQuestion()
    {
        return questionItemSet.get(questionId);
    }

    public QuestionItem GetQuestion(int index)
    {
        return questionItemSet.get(index);
    }

    public static String txt2String(File file)
    {
        String result = "";
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));//����һ��BufferedReader������ȡ�ļ�
            String s = null;
            while ((s = br.readLine()) != null)
            {//ʹ��readLine������һ�ζ�һ��
                result = result + s;
            }
            br.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    /*	public String readFile(String remoteFile) {
               String log = "";
               try {
                   File file = new File(remoteFile);
                   FileInputStream is = new FileInputStream(remoteFile);
                   int size = (int) file.length();
                   byte[] bytes = getBytes(is,size);
                   String content = new String(bytes, "GBK");
                   log = content;
                   is.close();
               } catch (IOException ex) {
                }
               return log;
            }


            private byte[] getBytes(InputStream inputStream,int size) {
               byte[] bytes = new byte[size];
               try {
                   int readBytes = inputStream.read(bytes);
                   return bytes;
               } catch (Exception e) {
                   e.printStackTrace();
               }
               return null;
            }
            */
    public static void put(String s, String name)
    {
        try
        {
            FileOutputStream outStream = new FileOutputStream(Environment.getExternalStorageDirectory() + "/annazheng/" + name + ".txt", true);
            OutputStreamWriter writer = new OutputStreamWriter(outStream, "gb2312");
            writer.write(s);
            writer.flush();
            writer.close();
            outStream.close();
        } catch (Exception e)
        {
            Log.e("m", "file write error");
        }
    }

    public static void put2(String s, String name)
    {
        try
        {
            FileOutputStream outStream = new FileOutputStream(Environment.getExternalStorageDirectory() + "/annazheng/" + name + ".txt", true);
            OutputStreamWriter writer = new OutputStreamWriter(outStream, "gb2312");
            writer.write(",");
            writer.write(s);
            writer.flush();
            writer.close();
            outStream.close();
        } catch (Exception e)
        {
            Log.e("m", "file write error");
        }
    }

}
