package com.brainhealthtest.widget;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.callback.GetBytesCallback;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.brainhealthtest.R;
import com.brainhealthtest.osshelper.Helper;
import com.brainhealthtest.questions.StartActivity;
import com.brainhealthtest.utility.Content;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import co.dift.ui.SwipeToAction;

@EActivity(R.layout.activity_doctor)
public class DoctorActivity extends AppCompatActivity
{
    @ViewById(R.id.recycler)
    RecyclerView recyclerView;
    DoctorAdapter adapter;
    SwipeToAction swipeToAction;

    List<TestCase> testCases = new ArrayList<>();

    Helper helper = new Helper();


    @Click(R.id.add_case_button)
    void addCaseClicked(Button button)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(DoctorActivity.this);
        builder.setTitle("ADD A NEW CASE");

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View addFriendView = layoutInflater.inflate(R.layout.dialog_doctor_add_case, null);
        builder.setView(addFriendView);

        final EditText etParID = (EditText) addFriendView.findViewById(R.id.par_id);

        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        String parid = etParID.getText().toString();

                        TestCase testCase = new TestCase(parid, "2016-12-9", "http://static.TestCasestck.com/TestCases/einstein-his-life-and-universe-400.jpg");

                        testCases.add(0, testCase);
                        adapter.notifyItemInserted(0);

//                        helper.getObjectAsync("00_Config/Doctors.txt/" + username, new GetBytesCallback()
//                        {
//                            @Override
//                            public void onSuccess(String s, byte[] bytes)
//                            {
//                                String maps = new String(bytes);
//                                String[] usernames = maps.split("\n");
//
//                                for (int i = 0; i < usernames.length; ++i)
//                                {
//                                    String[] IDs = usernames[i].split("\t");
//                                    if (username.equals(IDs[0]) && username.equals(IDs[1]))
//                                    {
//                                        Message message = new Message();
//                                        message.what = LOGIN_SUCCESS;
//                                        handler.sendMessage(message);
//                                        return;
//                                    }
//                                }
//                                Message message = new Message();
//                                message.what = LOGIN_FAIL;
//                                handler.sendMessage(message);
//                            }
//
//                            @Override
//                            public void onProgress(String s, int i, int i1)
//                            {
//
//                            }
//
//                            @Override
//                            public void onFailure(String s, OSSException e)
//                            {
//
//                            }
//                        });

                    }
                });

        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });

        builder.show();
    }


    @AfterViews
    void init()
    {
        helper.ossService = OSSServiceProvider.getService();
        helper.ossService.setApplicationContext(getApplicationContext());
        helper.Init();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new DoctorAdapter(this.testCases);
        recyclerView.setAdapter(adapter);

        swipeToAction = new SwipeToAction(recyclerView, new SwipeToAction.SwipeListener<TestCase>()
        {
            @Override
            public boolean swipeLeft(final TestCase itemData)
            {
                final int pos = removeTestCase(itemData);
                displaySnackbar(itemData.getTitle() + " removed", "Undo", new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        addTestCase(pos, itemData);
                    }
                });
                return true;
            }

            @Override
            public boolean swipeRight(TestCase itemData)
            {
                displaySnackbar(itemData.getTitle() + " loved", null, null);
                return true;
            }

            @Override
            public void onClick(TestCase itemData)
            {
                Intent intent = new Intent();
                intent.putExtra("testernumber", "1");
                intent.putExtra("participanternumber", "1");
                intent.setClass(DoctorActivity.this, Content.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(TestCase itemData)
            {
                displaySnackbar(itemData.getTitle() + " long clicked", null, null);
            }
        });

        populate();

        // use swipeLeft or swipeRight and the elem position to swipe by code
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                swipeToAction.swipeRight(2);
            }
        }, 3000);
    }

    private void populate()
    {
        this.testCases.add(new TestCase("TEST2012-15", "2016-10-12", "http://static.TestCasestck.com/TestCases/einstein-his-life-and-universe-400.jpg"));
        this.testCases.add(new TestCase("TEST2012-17", "2016-10-12", "http://static.TestCasestck.com/TestCases/einstein-his-life-and-universe-400.jpg"));
        this.testCases.add(new TestCase("TEST2012-19", "2016-10-12", "http://static.TestCasestck.com/TestCases/einstein-his-life-and-universe-400.jpg"));
        this.testCases.add(new TestCase("TEST2012-12", "2016-10-12", "http://static.TestCasestck.com/TestCases/einstein-his-life-and-universe-400.jpg"));
        this.testCases.add(new TestCase("TEST2012-20", "2016-10-12", "http://static.TestCasestck.com/TestCases/einstein-his-life-and-universe-400.jpg"));
        this.testCases.add(new TestCase("TEST2012-21", "2016-10-12", "http://static.TestCasestck.com/TestCases/einstein-his-life-and-universe-400.jpg"));
        this.testCases.add(new TestCase("TEST2012-22", "2016-10-12", "http://static.TestCasestck.com/TestCases/einstein-his-life-and-universe-400.jpg"));
        this.testCases.add(new TestCase("TEST2012-23", "2016-10-12", "http://static.TestCasestck.com/TestCases/einstein-his-life-and-universe-400.jpg"));
    }

    private void displaySnackbar(String text, String actionName, View.OnClickListener action)
    {
        Snackbar snack = Snackbar.make(findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG)
                .setAction(actionName, action);

        View v = snack.getView();
        v.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        ((TextView) v.findViewById(android.support.design.R.id.snackbar_text)).setTextColor(Color.WHITE);
        ((TextView) v.findViewById(android.support.design.R.id.snackbar_action)).setTextColor(Color.BLACK);

        snack.show();
    }

    private int removeTestCase(TestCase TestCase)
    {
        int pos = testCases.indexOf(TestCase);
        testCases.remove(TestCase);
        adapter.notifyItemRemoved(pos);
        return pos;
    }

    private void addTestCase(int pos, TestCase TestCase)
    {
        testCases.add(pos, TestCase);
        adapter.notifyItemInserted(pos);
    }

}
