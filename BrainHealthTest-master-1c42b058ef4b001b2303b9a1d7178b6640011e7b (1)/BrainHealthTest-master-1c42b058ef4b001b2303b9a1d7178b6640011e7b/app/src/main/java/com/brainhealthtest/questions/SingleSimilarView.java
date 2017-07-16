package com.brainhealthtest.questions;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;


import com.brainhealthtest.osshelper.Helper;

import java.util.ArrayList;
import java.util.List;


public class SingleSimilarView extends LinearLayout
{
    public int score = -1;
    int ss = 0;
    private String pair1, pair2;//words pair
    public int wordEditType;
    public int fail = 5;

    private EditText pair;
    public EditText text;
    //public Button easyScore, hardScore, palBtn, relatedBtn, notRelBtn, noGuessBtn, perseyBtn, repeatBtn;
    public Button zeroScore, oneScore, twoScore, sixScore, NoScore, LOSBtn, ConBtn, PsvBtn, OtherBtn;//Button in one row

    private List<String> words;
    private List<Integer> wordType;

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
    public SingleSimilarView(final Context context, final AttributeSet attrSet)
    {
        super(context, attrSet);
        instantiate(context, null);
    }

    public int GetScore()
    {
        return score;
    }

    public void InitWordList(Helper helper)
    {
        helper.Init();
        words = new ArrayList<String>();
        wordType = new ArrayList<Integer>();
        try
        {
            String maps = helper.GetObject("00_Config/" + pair1 + ".txt");//build the dictionary
            String[] IDmaps = maps.split("\n");
            for (int i = 0; i < IDmaps.length; ++i)
            {
                if (IDmaps.length == 0) continue;
                String[] IDs = IDmaps[i].split(" ");
                wordType.add(Integer.parseInt(IDs[1]));
                words.add(IDs[0]);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void Init(String mpair1, String mpair2, Helper helper)
    {
        pair1 = mpair1;
        pair2 = mpair2;
        pair.setText(pair1 + "\n" + pair2);

        InitWordList(helper);

        text.setOnFocusChangeListener(new OnFocusChangeListener()
        {//according to the dictionary to set the answer type
            @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                {
                    String typeWord = text.getText().toString();
                    for (int i = 0; i < words.size(); ++i)
                        if (words.get(i).equals(typeWord))
                        {
                            int type = wordType.get(i);
                            if (type == 1)
                                LOSBtn.callOnClick();
                            else if (type == 2)
                                ConBtn.callOnClick();
                            else if (type == 3)
                                PsvBtn.callOnClick();
                            else if (type == 4)
                                OtherBtn.callOnClick();
                        }
                }
            }
        });//according to the dictionary to set the answer type
    }

    public void Save(Helper helper)
    {
        InitWordList(helper);

        helper.Init();
        Boolean hasWord = false;
        String typeWord = text.getText().toString();
        for (int i = 0; i < words.size(); ++i)
            if (words.get(i).equals(typeWord))
            {
                hasWord = true;
                break;
            }
        if (!hasWord && wordEditType > 0 && typeWord.length() > 0)
        {
            words.add(typeWord);
            wordType.add(wordEditType);
        } else
            return;
        String output = "";
        for (int i = 0; i < words.size(); ++i)
        {
            output = output + words.get(i) + " " + Integer.toString(wordType.get(i));
            if (i < words.size() - 1)
                output = output + "\n";
        }
        byte[] data = output.getBytes();
        try
        {
            helper.PutObject("00_Config/" + pair1 + ".txt", data);
        } catch (Exception e)
        {

        }
    }

    public SingleSimilarView(final Context context)
    {
        super(context);
        Context mContext = context;
        wordEditType = 0;

        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        mLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        marginLayout.setMargins(2, 2, 2, 2);

        LinearLayout viewLayout = new LinearLayout(mContext);
        viewLayout.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout.setBackgroundColor(Color.BLACK);
        viewLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        viewLayout.setLayoutParams(marginLayout);

        pair = new EditText(context);
        //pair.setText("North\nSouth\n");
        pair.setWidth(236);
        pair.setHeight(100);
        pair.setGravity(Gravity.CENTER);
        pair.setEnabled(false);
        pair.setTextSize(13);
        pair.setLayoutParams(marginLayout);
        pair.setTextColor(Color.BLACK);
        pair.setBackgroundColor(Color.WHITE);

        zeroScore = new Button(mContext);
        zeroScore.setBackgroundColor(Color.LTGRAY);
        zeroScore.setWidth(91);
        zeroScore.setHeight(100);
        zeroScore.setGravity(Gravity.CENTER);
        zeroScore.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score = 0;
                fail = 1;
                zeroScore.setBackgroundColor(Color.GREEN);
                oneScore.setBackgroundColor(Color.LTGRAY);
                twoScore.setBackgroundColor(Color.LTGRAY);
                LOSBtn.setBackgroundColor(Color.LTGRAY);
                ConBtn.setBackgroundColor(Color.LTGRAY);
                PsvBtn.setBackgroundColor(Color.LTGRAY);
                OtherBtn.setBackgroundColor(Color.LTGRAY);
                NoScore.setBackgroundColor(Color.LTGRAY);
                sixScore.setBackgroundColor(Color.LTGRAY);
            }
        });
        zeroScore.setLayoutParams(marginLayout);

        oneScore = new Button(mContext);
        oneScore.setBackgroundColor(Color.LTGRAY);
        oneScore.setWidth(91);
        oneScore.setHeight(100);
        oneScore.setGravity(Gravity.CENTER);
        oneScore.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score = 1;
                fail = 0;
                wordEditType = 2;
                zeroScore.setBackgroundColor(Color.LTGRAY);
                oneScore.setBackgroundColor(Color.GREEN);
                twoScore.setBackgroundColor(Color.LTGRAY);
                LOSBtn.setBackgroundColor(Color.LTGRAY);
                ConBtn.setBackgroundColor(Color.YELLOW);
                PsvBtn.setBackgroundColor(Color.LTGRAY);
                OtherBtn.setBackgroundColor(Color.LTGRAY);
                NoScore.setBackgroundColor(Color.LTGRAY);
                sixScore.setBackgroundColor(Color.LTGRAY);
            }
        });
        oneScore.setLayoutParams(marginLayout);

        twoScore = new Button(mContext);
        twoScore.setBackgroundColor(Color.LTGRAY);
        twoScore.setWidth(91);
        twoScore.setHeight(100);
        twoScore.setGravity(Gravity.CENTER);
        twoScore.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score = 2;
                fail = 0;
                zeroScore.setBackgroundColor(Color.LTGRAY);
                oneScore.setBackgroundColor(Color.LTGRAY);
                twoScore.setBackgroundColor(Color.GREEN);
                LOSBtn.setBackgroundColor(Color.LTGRAY);
                ConBtn.setBackgroundColor(Color.LTGRAY);
                PsvBtn.setBackgroundColor(Color.LTGRAY);
                OtherBtn.setBackgroundColor(Color.LTGRAY);
                NoScore.setBackgroundColor(Color.LTGRAY);
                sixScore.setBackgroundColor(Color.LTGRAY);
            }
        });
        twoScore.setLayoutParams(marginLayout);

        sixScore = new Button(mContext);
        sixScore.setBackgroundColor(Color.LTGRAY);
        sixScore.setWidth(91);
        sixScore.setHeight(100);
        sixScore.setGravity(Gravity.CENTER);
        sixScore.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score = 6;
                fail = 1;
                zeroScore.setBackgroundColor(Color.LTGRAY);
                oneScore.setBackgroundColor(Color.LTGRAY);
                sixScore.setBackgroundColor(Color.GREEN);
                twoScore.setBackgroundColor(Color.LTGRAY);
                LOSBtn.setBackgroundColor(Color.LTGRAY);
                ConBtn.setBackgroundColor(Color.LTGRAY);
                PsvBtn.setBackgroundColor(Color.LTGRAY);
                OtherBtn.setBackgroundColor(Color.LTGRAY);
                NoScore.setBackgroundColor(Color.LTGRAY);
            }
        });
        sixScore.setLayoutParams(marginLayout);

        NoScore = new Button(mContext);
        NoScore.setBackgroundColor(Color.LTGRAY);
        NoScore.setWidth(91);
        NoScore.setHeight(100);
        NoScore.setGravity(Gravity.CENTER);
        NoScore.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score = 8;
                fail = 1;
                zeroScore.setBackgroundColor(Color.LTGRAY);
                oneScore.setBackgroundColor(Color.LTGRAY);
                NoScore.setBackgroundColor(Color.RED);
                sixScore.setBackgroundColor(Color.LTGRAY);
                twoScore.setBackgroundColor(Color.LTGRAY);
                LOSBtn.setBackgroundColor(Color.LTGRAY);
                ConBtn.setBackgroundColor(Color.LTGRAY);
                PsvBtn.setBackgroundColor(Color.LTGRAY);
                OtherBtn.setBackgroundColor(Color.LTGRAY);
            }
        });
        NoScore.setLayoutParams(marginLayout);

        LOSBtn = new Button(mContext);
        LOSBtn.setBackgroundColor(Color.LTGRAY);
        LOSBtn.setWidth(91);
        LOSBtn.setHeight(100);
        LOSBtn.setGravity(Gravity.CENTER);
        LOSBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                wordEditType = 1;
                score = 0;//when belong to error, the score =0?
                zeroScore.setBackgroundColor(Color.GREEN);
                oneScore.setBackgroundColor(Color.LTGRAY);
                twoScore.setBackgroundColor(Color.LTGRAY);
                LOSBtn.setBackgroundColor(Color.YELLOW);
                ConBtn.setBackgroundColor(Color.LTGRAY);
                PsvBtn.setBackgroundColor(Color.LTGRAY);
                OtherBtn.setBackgroundColor(Color.LTGRAY);
                NoScore.setBackgroundColor(Color.LTGRAY);
                sixScore.setBackgroundColor(Color.LTGRAY);
            }
        });
        LOSBtn.setLayoutParams(marginLayout);

        ConBtn = new Button(mContext);
        ConBtn.setBackgroundColor(Color.LTGRAY);
        ConBtn.setWidth(91);
        ConBtn.setHeight(100);
        ConBtn.setGravity(Gravity.CENTER);
        ConBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                wordEditType = 2;
                score = 1;
                zeroScore.setBackgroundColor(Color.LTGRAY);
                oneScore.setBackgroundColor(Color.GREEN);
                twoScore.setBackgroundColor(Color.LTGRAY);
                LOSBtn.setBackgroundColor(Color.LTGRAY);
                ConBtn.setBackgroundColor(Color.YELLOW);
                PsvBtn.setBackgroundColor(Color.LTGRAY);
                OtherBtn.setBackgroundColor(Color.LTGRAY);
                NoScore.setBackgroundColor(Color.LTGRAY);
                sixScore.setBackgroundColor(Color.LTGRAY);
            }
        });
        ConBtn.setLayoutParams(marginLayout);

        PsvBtn = new Button(mContext);
        PsvBtn.setBackgroundColor(Color.LTGRAY);
        PsvBtn.setWidth(91);
        PsvBtn.setHeight(100);
        PsvBtn.setGravity(Gravity.CENTER);
        PsvBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                wordEditType = 3;
                score = 0;
                zeroScore.setBackgroundColor(Color.GREEN);
                oneScore.setBackgroundColor(Color.LTGRAY);
                twoScore.setBackgroundColor(Color.LTGRAY);
                LOSBtn.setBackgroundColor(Color.LTGRAY);
                ConBtn.setBackgroundColor(Color.LTGRAY);
                PsvBtn.setBackgroundColor(Color.YELLOW);
                OtherBtn.setBackgroundColor(Color.LTGRAY);
                NoScore.setBackgroundColor(Color.LTGRAY);
                sixScore.setBackgroundColor(Color.LTGRAY);
            }
        });
        PsvBtn.setLayoutParams(marginLayout);

        OtherBtn = new Button(mContext);
        OtherBtn.setBackgroundColor(Color.LTGRAY);
        OtherBtn.setWidth(91);
        OtherBtn.setHeight(100);
        OtherBtn.setGravity(Gravity.CENTER);
        OtherBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                wordEditType = 4;
                score = 0;
                zeroScore.setBackgroundColor(Color.GREEN);
                oneScore.setBackgroundColor(Color.LTGRAY);
                twoScore.setBackgroundColor(Color.LTGRAY);
                LOSBtn.setBackgroundColor(Color.LTGRAY);
                ConBtn.setBackgroundColor(Color.LTGRAY);
                PsvBtn.setBackgroundColor(Color.LTGRAY);
                OtherBtn.setBackgroundColor(Color.YELLOW);
                NoScore.setBackgroundColor(Color.LTGRAY);
                sixScore.setBackgroundColor(Color.LTGRAY);
            }
        });
        OtherBtn.setLayoutParams(marginLayout);


        text = new EditText(mContext);
        text.setWidth(250);
        text.setHeight(100);
        text.setGravity(Gravity.CENTER);
        text.setLayoutParams(marginLayout);
        text.setBackgroundColor(Color.WHITE);

        viewLayout.addView(pair);
        viewLayout.addView(text);
        viewLayout.addView(zeroScore);
        viewLayout.addView(oneScore);
        viewLayout.addView(twoScore);
        viewLayout.addView(sixScore);
        viewLayout.addView(NoScore);
        viewLayout.addView(LOSBtn);
        viewLayout.addView(ConBtn);
        viewLayout.addView(PsvBtn);
        viewLayout.addView(OtherBtn);
        mLayout.addView(viewLayout);
        addView(mLayout);
        return;
    }

    /**
     * This is used when creating the view programatically Once you have
     * instantiated the view you can call setImageDrawable(url) to change the
     * image
     *
     * @param context  the Activity context
     * @param imageUrl the Image URL you wish to load
     */
    public SingleSimilarView(final Context context, final String imageUrl, Handler handler)
    {
        super(context);
        this.callbackHandler = handler;
        instantiate(context, imageUrl);
    }

    private void instantiate(final Context context, final String imageUrl)
    {
        Context mContext = context;

        TableLayout mytable = new TableLayout(mContext);
        TableRow tableRow = new TableRow(context);
        tableRow.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        pair = new EditText(context);
        //pair.setText("North\nSouth");//Should it be changed to new question : Orange Banana
        zeroScore = new Button(mContext);
        zeroScore.setText("    ");
        zeroScore.setBackgroundColor(Color.LTGRAY);
        zeroScore.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                score = 0;
                zeroScore.setBackgroundColor(Color.GREEN);
                oneScore.setBackgroundColor(Color.LTGRAY);
                twoScore.setBackgroundColor(Color.LTGRAY);
                LOSBtn.setBackgroundColor(Color.LTGRAY);
                ConBtn.setBackgroundColor(Color.LTGRAY);
                PsvBtn.setBackgroundColor(Color.LTGRAY);
                OtherBtn.setBackgroundColor(Color.LTGRAY);
            }
        });

        oneScore = new Button(mContext);
        oneScore.setBackgroundColor(Color.LTGRAY);
        oneScore.setText("    ");
        oneScore.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                score = 1;
                zeroScore.setBackgroundColor(Color.LTGRAY);
                oneScore.setBackgroundColor(Color.GREEN);
                twoScore.setBackgroundColor(Color.LTGRAY);
                LOSBtn.setBackgroundColor(Color.LTGRAY);
                ConBtn.setBackgroundColor(Color.LTGRAY);
                PsvBtn.setBackgroundColor(Color.LTGRAY);
                OtherBtn.setBackgroundColor(Color.LTGRAY);
            }
        });

        twoScore = new Button(mContext);
        twoScore.setBackgroundColor(Color.LTGRAY);
        twoScore.setText("    ");
        twoScore.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                score = 1;
                zeroScore.setBackgroundColor(Color.LTGRAY);
                oneScore.setBackgroundColor(Color.LTGRAY);
                twoScore.setBackgroundColor(Color.GREEN);
                LOSBtn.setBackgroundColor(Color.LTGRAY);
                ConBtn.setBackgroundColor(Color.LTGRAY);
                PsvBtn.setBackgroundColor(Color.LTGRAY);
                OtherBtn.setBackgroundColor(Color.LTGRAY);
            }
        });

        LOSBtn = new Button(mContext);
        LOSBtn.setBackgroundColor(Color.LTGRAY);
        LOSBtn.setText("    ");
        LOSBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                wordEditType = 1;
                score = 0;
                zeroScore.setBackgroundColor(Color.LTGRAY);
                oneScore.setBackgroundColor(Color.LTGRAY);
                twoScore.setBackgroundColor(Color.LTGRAY);
                LOSBtn.setBackgroundColor(Color.GREEN);
                ConBtn.setBackgroundColor(Color.LTGRAY);
                PsvBtn.setBackgroundColor(Color.LTGRAY);
                OtherBtn.setBackgroundColor(Color.LTGRAY);
            }
        });

        ConBtn = new Button(mContext);
        ConBtn.setBackgroundColor(Color.LTGRAY);
        ConBtn.setText("    ");
        ConBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                wordEditType = 2;
                score = 0;
                zeroScore.setBackgroundColor(Color.LTGRAY);
                oneScore.setBackgroundColor(Color.LTGRAY);
                twoScore.setBackgroundColor(Color.LTGRAY);
                LOSBtn.setBackgroundColor(Color.LTGRAY);
                ConBtn.setBackgroundColor(Color.GREEN);
                PsvBtn.setBackgroundColor(Color.LTGRAY);
                OtherBtn.setBackgroundColor(Color.LTGRAY);
            }
        });

        PsvBtn = new Button(mContext);
        PsvBtn.setBackgroundColor(Color.LTGRAY);
        PsvBtn.setText("    ");
        PsvBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                wordEditType = 3;
                score = 0;
                zeroScore.setBackgroundColor(Color.LTGRAY);
                oneScore.setBackgroundColor(Color.LTGRAY);
                twoScore.setBackgroundColor(Color.LTGRAY);
                LOSBtn.setBackgroundColor(Color.LTGRAY);
                ConBtn.setBackgroundColor(Color.LTGRAY);
                PsvBtn.setBackgroundColor(Color.GREEN);
                OtherBtn.setBackgroundColor(Color.LTGRAY);
            }
        });

        OtherBtn = new Button(mContext);
        OtherBtn.setBackgroundColor(Color.LTGRAY);
        OtherBtn.setText("    ");
        OtherBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                wordEditType = 3;
                score = 0;
                zeroScore.setBackgroundColor(Color.LTGRAY);
                oneScore.setBackgroundColor(Color.LTGRAY);
                twoScore.setBackgroundColor(Color.LTGRAY);
                LOSBtn.setBackgroundColor(Color.LTGRAY);
                ConBtn.setBackgroundColor(Color.LTGRAY);
                PsvBtn.setBackgroundColor(Color.LTGRAY);
                OtherBtn.setBackgroundColor(Color.GREEN);
            }
        });
        text = new EditText(mContext);
        text.setText("Add text    ");
        tableRow.addView(pair);
        tableRow.addView(text);
        tableRow.addView(zeroScore);
        tableRow.addView(oneScore);
        tableRow.addView(twoScore);
        tableRow.addView(LOSBtn);
        tableRow.addView(ConBtn);
        tableRow.addView(PsvBtn);
        tableRow.addView(OtherBtn);
        mytable.addView(tableRow);
        addView(mytable);
        return;
    }

}
