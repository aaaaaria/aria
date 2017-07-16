package com.brainhealthtest.questions;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.TextView;

import com.brainhealthtest.R;


public class EndActivity extends Activity
{
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endpagelayout);
        tv = (TextView) findViewById(R.id.endstring);
        tv.setText("Finish the Test!");
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(60);
        AssetManager mgr = getAssets();
//        Typeface tf = Typeface.createFromAsset(mgr, "fonts/Jackey_Egypt.ttf");
//        tv.setTypeface(tf);
        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {

                finish();
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);
                System.exit(0);
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable, 1500);
    }

}
