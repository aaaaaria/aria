package com.brainhealthtest.questions;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;


import com.brainhealthtest.osshelper.Helper;

import java.util.ArrayList;
import java.util.List;


public class SingleTrailerView extends LinearLayout
{
    public int score = 0;
    int ss = 0;
    private String pair1, pair2;
    public int wordEditType;

    private EditText pair;
    public EditText text;
    public Button easyScore, hardScore, palBtn, relatedBtn, notRelBtn, noGuessBtn, perseyBtn, repeatBtn;

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
    public SingleTrailerView(final Context context, final AttributeSet attrSet)
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
            String maps = helper.GetObject("00_Config/" + pair1 + ".txt");
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

    public void Init(int score, String mpair1, String mpair2, Helper helper)
    {
        pair1 = mpair1;
        pair2 = mpair2;
        pair.setText(pair1 + "\n" + pair2);
        if (score == 1)
        {
            ss = 1;
            hardScore.setBackgroundColor(Color.BLACK);
            hardScore.setEnabled(false);
        } else
        {
            ss = 2;
            easyScore.setBackgroundColor(Color.BLACK);
            easyScore.setEnabled(false);
        }

        InitWordList(helper);

        text.setOnFocusChangeListener(new OnFocusChangeListener()
        {
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
                                palBtn.callOnClick();
                            else if (type == 2)
                                relatedBtn.callOnClick();
                            else if (type == 3)
                                notRelBtn.callOnClick();
                        }
                }
            }
        });
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

    public SingleTrailerView(final Context context)
    {
        super(context);
        Context mContext = context;
        wordEditType = 0;

        LinearLayout mLayout = new LinearLayout(mContext);
        mLayout.setOrientation(LinearLayout.VERTICAL);

        LayoutParams marginLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        marginLayout.setMargins(2, 2, 2, 2);

        LinearLayout viewLayout = new LinearLayout(mContext);
        viewLayout.setOrientation(LinearLayout.HORIZONTAL);
        viewLayout.setBackgroundColor(Color.BLACK);
        //viewLayout.setLayoutParams(marginLayout);

        pair = new EditText(context);
        //pair.setText("North\nSouth\n");
        pair.setWidth(160);
        pair.setHeight(100);
        pair.setEnabled(false);
        pair.setTextSize(13);
        pair.setLayoutParams(marginLayout);
        //pair.setTextColor(Color.WHITE);
        pair.setBackgroundColor(Color.WHITE);

        easyScore = new Button(mContext);

        easyScore.setWidth(110);
        easyScore.setHeight(100);
        easyScore.setBackgroundColor(Color.LTGRAY);
        easyScore.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score = 1;
                easyScore.setBackgroundColor(Color.GREEN);
                palBtn.setBackgroundColor(Color.LTGRAY);
                relatedBtn.setBackgroundColor(Color.LTGRAY);
                notRelBtn.setBackgroundColor(Color.LTGRAY);
                noGuessBtn.setBackgroundColor(Color.LTGRAY);
                perseyBtn.setBackgroundColor(Color.LTGRAY);
                repeatBtn.setBackgroundColor(Color.LTGRAY);
            }
        });
        //easyScore.setLayoutParams(marginLayout);

        hardScore = new Button(mContext);
        hardScore.setBackgroundColor(Color.LTGRAY);

        hardScore.setWidth(110);
        hardScore.setHeight(100);
        hardScore.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score = 2;
                hardScore.setBackgroundColor(Color.GREEN);
                palBtn.setBackgroundColor(Color.LTGRAY);
                relatedBtn.setBackgroundColor(Color.LTGRAY);
                notRelBtn.setBackgroundColor(Color.LTGRAY);
                noGuessBtn.setBackgroundColor(Color.LTGRAY);
                perseyBtn.setBackgroundColor(Color.LTGRAY);
                repeatBtn.setBackgroundColor(Color.LTGRAY);
            }
        });
        hardScore.setLayoutParams(marginLayout);

        palBtn = new Button(mContext);
        palBtn.setBackgroundColor(Color.LTGRAY);

        palBtn.setWidth(110);
        palBtn.setHeight(100);
        palBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                wordEditType = 1;
                score = 0;
                if (ss == 1)
                    easyScore.setBackgroundColor(Color.LTGRAY);
                else
                    hardScore.setBackgroundColor(Color.LTGRAY);
                palBtn.setBackgroundColor(Color.GREEN);
                relatedBtn.setBackgroundColor(Color.LTGRAY);
                notRelBtn.setBackgroundColor(Color.LTGRAY);
                noGuessBtn.setBackgroundColor(Color.LTGRAY);
                perseyBtn.setBackgroundColor(Color.LTGRAY);
                repeatBtn.setBackgroundColor(Color.LTGRAY);
            }
        });
        palBtn.setLayoutParams(marginLayout);

        relatedBtn = new Button(mContext);
        relatedBtn.setWidth(110);
        relatedBtn.setBackgroundColor(Color.LTGRAY);

        relatedBtn.setHeight(100);
        relatedBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                wordEditType = 2;
                score = 0;
                if (ss == 1)
                    easyScore.setBackgroundColor(Color.LTGRAY);
                else
                    hardScore.setBackgroundColor(Color.LTGRAY);
                palBtn.setBackgroundColor(Color.LTGRAY);
                relatedBtn.setBackgroundColor(Color.GREEN);
                notRelBtn.setBackgroundColor(Color.LTGRAY);
                noGuessBtn.setBackgroundColor(Color.LTGRAY);
                perseyBtn.setBackgroundColor(Color.LTGRAY);
                repeatBtn.setBackgroundColor(Color.LTGRAY);
            }
        });
        relatedBtn.setLayoutParams(marginLayout);

        notRelBtn = new Button(mContext);
        notRelBtn.setBackgroundColor(Color.LTGRAY);

        notRelBtn.setWidth(110);
        notRelBtn.setHeight(100);
        notRelBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                wordEditType = 3;
                score = 0;
                if (ss == 1)
                    easyScore.setBackgroundColor(Color.LTGRAY);
                else
                    hardScore.setBackgroundColor(Color.LTGRAY);
                palBtn.setBackgroundColor(Color.LTGRAY);
                relatedBtn.setBackgroundColor(Color.LTGRAY);
                notRelBtn.setBackgroundColor(Color.GREEN);
                noGuessBtn.setBackgroundColor(Color.LTGRAY);
                perseyBtn.setBackgroundColor(Color.LTGRAY);
                repeatBtn.setBackgroundColor(Color.LTGRAY);
            }
        });
        notRelBtn.setLayoutParams(marginLayout);

        noGuessBtn = new Button(mContext);
        noGuessBtn.setBackgroundColor(Color.LTGRAY);

        noGuessBtn.setWidth(110);
        noGuessBtn.setHeight(100);
        noGuessBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                score = 0;
                if (ss == 1)
                    easyScore.setBackgroundColor(Color.LTGRAY);
                else
                    hardScore.setBackgroundColor(Color.LTGRAY);
                palBtn.setBackgroundColor(Color.LTGRAY);
                relatedBtn.setBackgroundColor(Color.LTGRAY);
                notRelBtn.setBackgroundColor(Color.LTGRAY);
                noGuessBtn.setBackgroundColor(Color.GREEN);
                perseyBtn.setBackgroundColor(Color.LTGRAY);
                repeatBtn.setBackgroundColor(Color.LTGRAY);
            }
        });
        noGuessBtn.setLayoutParams(marginLayout);

        perseyBtn = new Button(mContext);
        perseyBtn.setBackgroundColor(Color.LTGRAY);

        perseyBtn.setWidth(110);
        perseyBtn.setHeight(100);
        perseyBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                score = 0;
                if (ss == 1)
                    easyScore.setBackgroundColor(Color.LTGRAY);
                else
                    hardScore.setBackgroundColor(Color.LTGRAY);
                palBtn.setBackgroundColor(Color.LTGRAY);
                relatedBtn.setBackgroundColor(Color.LTGRAY);
                notRelBtn.setBackgroundColor(Color.LTGRAY);
                noGuessBtn.setBackgroundColor(Color.LTGRAY);
                perseyBtn.setBackgroundColor(Color.GREEN);
                repeatBtn.setBackgroundColor(Color.LTGRAY);
            }
        });
        perseyBtn.setLayoutParams(marginLayout);

        repeatBtn = new Button(mContext);
        repeatBtn.setWidth(110);
        repeatBtn.setHeight(100);
        repeatBtn.setBackgroundColor(Color.LTGRAY);
        repeatBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                score = 0;
                if (ss == 1)
                    easyScore.setBackgroundColor(Color.LTGRAY);
                else
                    hardScore.setBackgroundColor(Color.LTGRAY);
                palBtn.setBackgroundColor(Color.LTGRAY);
                relatedBtn.setBackgroundColor(Color.LTGRAY);
                notRelBtn.setBackgroundColor(Color.LTGRAY);
                noGuessBtn.setBackgroundColor(Color.LTGRAY);
                perseyBtn.setBackgroundColor(Color.LTGRAY);
                repeatBtn.setBackgroundColor(Color.GREEN);
            }
        });
        repeatBtn.setLayoutParams(marginLayout);

        text = new EditText(mContext);
        text.setWidth(240);
        text.setHeight(100);
        text.setLayoutParams(marginLayout);
        text.setBackgroundColor(Color.WHITE);

        viewLayout.addView(pair);
        viewLayout.addView(easyScore);
        viewLayout.addView(hardScore);
        viewLayout.addView(palBtn);
        viewLayout.addView(relatedBtn);
        viewLayout.addView(notRelBtn);
        viewLayout.addView(text);
        viewLayout.addView(noGuessBtn);
        viewLayout.addView(perseyBtn);
        viewLayout.addView(repeatBtn);

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
    public SingleTrailerView(final Context context, final String imageUrl,
                             Handler handler)
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
        pair.setText("North\nSouth");
        easyScore = new Button(mContext);
        easyScore.setText("    ");
        easyScore.setBackgroundColor(Color.LTGRAY);
        easyScore.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                score = 1;
                easyScore.setBackgroundColor(Color.GREEN);
                palBtn.setBackgroundColor(Color.LTGRAY);
                relatedBtn.setBackgroundColor(Color.LTGRAY);
                notRelBtn.setBackgroundColor(Color.LTGRAY);
                noGuessBtn.setBackgroundColor(Color.LTGRAY);
                perseyBtn.setBackgroundColor(Color.LTGRAY);
            }
        });

        hardScore = new Button(mContext);
        hardScore.setBackgroundColor(Color.LTGRAY);
        hardScore.setText("    ");
        hardScore.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                score = 2;
                hardScore.setBackgroundColor(Color.GREEN);
                palBtn.setBackgroundColor(Color.LTGRAY);
                relatedBtn.setBackgroundColor(Color.LTGRAY);
                notRelBtn.setBackgroundColor(Color.LTGRAY);
                noGuessBtn.setBackgroundColor(Color.LTGRAY);
                perseyBtn.setBackgroundColor(Color.LTGRAY);
            }
        });
        palBtn = new Button(mContext);
        palBtn.setBackgroundColor(Color.LTGRAY);
        palBtn.setText("    ");
        palBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                wordEditType = 1;
                score = 0;
                if (ss == 1)
                    easyScore.setBackgroundColor(Color.LTGRAY);
                else
                    hardScore.setBackgroundColor(Color.LTGRAY);
                palBtn.setBackgroundColor(Color.GREEN);
                relatedBtn.setBackgroundColor(Color.LTGRAY);
                notRelBtn.setBackgroundColor(Color.LTGRAY);
                noGuessBtn.setBackgroundColor(Color.LTGRAY);
                perseyBtn.setBackgroundColor(Color.LTGRAY);
            }
        });
        relatedBtn = new Button(mContext);
        relatedBtn.setBackgroundColor(Color.LTGRAY);
        relatedBtn.setText("    ");
        relatedBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                wordEditType = 2;
                score = 0;
                if (ss == 1)
                    easyScore.setBackgroundColor(Color.LTGRAY);
                else
                    hardScore.setBackgroundColor(Color.LTGRAY);
                palBtn.setBackgroundColor(Color.LTGRAY);
                relatedBtn.setBackgroundColor(Color.GREEN);
                notRelBtn.setBackgroundColor(Color.LTGRAY);
                noGuessBtn.setBackgroundColor(Color.LTGRAY);
                perseyBtn.setBackgroundColor(Color.LTGRAY);
            }
        });
        notRelBtn = new Button(mContext);
        notRelBtn.setBackgroundColor(Color.LTGRAY);
        notRelBtn.setText("    ");
        notRelBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                wordEditType = 3;
                score = 0;
                if (ss == 1)
                    easyScore.setBackgroundColor(Color.LTGRAY);
                else
                    hardScore.setBackgroundColor(Color.LTGRAY);
                palBtn.setBackgroundColor(Color.LTGRAY);
                relatedBtn.setBackgroundColor(Color.LTGRAY);
                notRelBtn.setBackgroundColor(Color.GREEN);
                noGuessBtn.setBackgroundColor(Color.LTGRAY);
                perseyBtn.setBackgroundColor(Color.LTGRAY);
            }
        });
        noGuessBtn = new Button(mContext);
        noGuessBtn.setBackgroundColor(Color.LTGRAY);
        noGuessBtn.setText("    ");
        noGuessBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                score = 0;
                if (ss == 1)
                    easyScore.setBackgroundColor(Color.LTGRAY);
                else
                    hardScore.setBackgroundColor(Color.LTGRAY);
                palBtn.setBackgroundColor(Color.LTGRAY);
                relatedBtn.setBackgroundColor(Color.LTGRAY);
                notRelBtn.setBackgroundColor(Color.LTGRAY);
                noGuessBtn.setBackgroundColor(Color.GREEN);
                perseyBtn.setBackgroundColor(Color.LTGRAY);
            }
        });
        perseyBtn = new Button(mContext);
        perseyBtn.setBackgroundColor(Color.LTGRAY);
        perseyBtn.setText("    ");
        perseyBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                score = 0;
                if (ss == 1)
                    easyScore.setBackgroundColor(Color.LTGRAY);
                else
                    hardScore.setBackgroundColor(Color.LTGRAY);
                palBtn.setBackgroundColor(Color.LTGRAY);
                relatedBtn.setBackgroundColor(Color.LTGRAY);
                notRelBtn.setBackgroundColor(Color.LTGRAY);
                noGuessBtn.setBackgroundColor(Color.LTGRAY);
                perseyBtn.setBackgroundColor(Color.GREEN);
            }
        });
        text = new EditText(mContext);
        text.setText("Add text    ");
        tableRow.addView(pair);
        tableRow.addView(easyScore);
        tableRow.addView(hardScore);
        tableRow.addView(palBtn);
        tableRow.addView(relatedBtn);
        tableRow.addView(notRelBtn);
        tableRow.addView(text);
        tableRow.addView(noGuessBtn);
        tableRow.addView(perseyBtn);
        mytable.addView(tableRow);
        addView(mytable);
        return;
    }

}
