package com.brainhealthtest.voicetest;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DigitAllView extends LinearLayout
{
    int choice = -1;
    int answer = -1;
    TextView view;
    List<Integer> answers;
    public List<Integer> userAnswer = new ArrayList<Integer>();
    public Integer userCue = 0;
    public Integer userSco = -1;
    int cues = 0;

    public List<DigitView> digits;   //DigitView is a single question.
    public Handler callbackHandler;

    /**
     * This is used when creating the view in XML To have an image load in XML
     * use the tag
     * 'image="http://developer.android.com/images/dialog_buttons.png"'
     * Replacing the url with your desired image Once you have instantiated the
     * XML view you can call setImageDrawable(url) to change the image
     *
     * @param context
     * @param attrSet
     */
    public DigitAllView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        instantiate(context, null);
    }

    public void Init(List<Integer> numbers)
    {
        String text = "";
        answers = new ArrayList<Integer>();
        for (int i = 0; i < numbers.size(); ++i) //this question's length
        {
            text = text + Integer.toString(numbers.get(i)) + "-";
            answers.add(numbers.get(i)); //save the correct answer
        }
        //for (int i = 0; i < 10; ++i)
        for (int i = 0; i < 13; ++i)
            digits.get(i).Dis();//all questions do not show , questions' max length=9.
        view.setText(text); //show this question
    }

    public void Enable()
    {
        for (int i = 0; i < answers.size(); ++i)
        {
            digits.get(i).Init(answers.get(i));//������Ŀ�����ڵ�����
        }
        for (int i = answers.size(); i < answers.size() + 2; ++i)
        {
            digits.get(i).Init2(10);//�������������
        }
        for (int i = answers.size() + 2; i < answers.size() + 3; ++i)
        {
            digits.get(i).Init3();//����ȷ����
        }
        for (int i = answers.size() + 3; i < 13; ++i)
            digits.get(i).Dis();
    }

    public int Calculate()
    {
        userAnswer = new ArrayList<Integer>();
        Boolean allCounted = true;
        List<Boolean> checked = new ArrayList<Boolean>(); //result whether be checked
        int count = 0;
        for (int i = 0; i < digits.size(); ++i)
        {  //i= i column
            checked.add(true);
            if (digits.get(answers.size() + 2).GetFinish() < 0)/////////////////////////
                continue; // if the result is not done, break the for cycle
            count++;
            if (digits.get(i).GetChoice() >= 0)
                userAnswer.add(digits.get(i).GetChoice());
            if (digits.get(answers.size() + 2).GetCue() >= 0)
                userCue = digits.get(answers.size() + 2).GetCue();
        }

        if (digits.get(answers.size() + 2).GetFinish() < 0) /////// if the result is not done, return -1
        {
            return -1;
        } else
        {
            if (answers.size() != userAnswer.size())
            {
                Log.d("aliyun", answers.size() + "");
                Log.d("aliyun", userAnswer.size() + "");
                userSco = 0;
                return 0;

            } else
            {
                for (int i = 0; i < answers.size(); i++)
                {
                    int num = answers.get(i);
                    Boolean thisCounted = false;
                    for (int j = 0; j < answers.size(); ++j)
                        if ((checked.get(j) == true) && (digits.get(j).GetChoice() == num))
                        {
                            checked.set(j, false);
                            thisCounted = true;
                            break;
                        }
                    if (!thisCounted)
                    {
                        allCounted = false;
                        break;
                    }
                }
            }
        }

        if (!allCounted) //allCounted = false;
        {
            userSco = 0;
            return 0;//�������е����ֶ������ˣ�0��
        } else  //allCounted = true;
        {
            int startID, step, counted;
            startID = 0;
            counted = 0;
            step = 1;
            while (counted < answers.size() && startID >= 0 && startID < digits.size())
            {
                int num = answers.get(counted);
                if (digits.get(startID).GetChoice() == num)
                    counted++;
                startID += step;
            }

            if (counted == answers.size())//��������ֺ�˳�򶼶�
            {
                userSco = 2;
                return 2;
            } else//�������ֶ�
            {
                userSco = 1;
                return 1;
            }
        }
    }

    public DigitAllView(final Context context)
    {
        super(context);
    }

    /**
     * This is used when creating the view programatically Once you have
     * instantiated the view you can call setImageDrawable(url) to change the
     * image
     *
     * @param context  the Activity context
     * @param imageUrl the Image URL you wish to load
     */
    public DigitAllView(final Context context, final String imageUrl,
                        Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        instantiate(context, imageUrl);
    }

    /**
     * First time loading of the LoaderImageView Sets up the LayoutParams of the
     * view, you can change these to get the required effects you want
     */
    private void instantiate(final Context context, final String imageUrl)
    {
        Context mContext = context;
        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout lLayout = new LinearLayout(mContext);
        lLayout.setOrientation(LinearLayout.HORIZONTAL);

        TableLayout mytable = new TableLayout(mContext);
        view = new TextView(mContext);
        view.setText("1-2-3-4-5");
        lLayout.addView(view);

        mLayout.addView(lLayout);
        TableRow tableRow = new TableRow(context);
        tableRow.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        digits = new ArrayList<DigitView>();
        for (int i = 0; i < 13; ++i)////////////////////////////////////////////////////��������
        //for (int i = 0; i < 10; ++i)
        {
            DigitView digit = new DigitView(mContext);
            //digit.Dis();
            digits.add(digit);
            tableRow.addView(digit);
        }
        mLayout.addView(tableRow);
        mytable.addView(mLayout);
        addView(mytable);
        return;
    }

    /**
     * Set's the view's drawable, this uses the internet to retrieve the image
     * don't forget to add the correct permissions to your manifest
     *
     * @param url the url of the image you wish to load
     */
    public void setImageDrawable(String url)
    {
        return;
    }

    public void InitB(List<Integer> numbers)
    {//Backward

        String text = "";
        answers = new ArrayList<Integer>();
        for (int i = 0; i < numbers.size(); ++i)
        {
            text = text + Integer.toString(numbers.get(i)) + "-";
        }
        for (int i = numbers.size() - 1; i >= 0; --i)
        {
            //digits.get(numbers.size() - 1 - i).Init(numbers.get(i));
            answers.add(numbers.get(i));
        }
        //for (int i = 0; i < 10; ++i)
        for (int i = 0; i < 13; ++i)
            digits.get(i).Dis();
        view.setText(text);
    }

}
