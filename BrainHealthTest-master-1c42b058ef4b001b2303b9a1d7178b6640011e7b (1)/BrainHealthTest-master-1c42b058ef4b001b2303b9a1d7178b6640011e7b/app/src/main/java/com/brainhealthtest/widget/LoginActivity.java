package com.brainhealthtest.widget;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.callback.GetBytesCallback;
import com.alibaba.sdk.android.oss.callback.SaveCallback;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.alibaba.sdk.android.oss.storage.OSSAsyncTask;
import com.aliyun.oss.model.PutObjectRequest;
import com.brainhealthtest.R;
import com.brainhealthtest.osshelper.Helper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity
{
    Helper helper = new Helper();

    @ViewById(R.id.username)
    EditText etUsername;
    @ViewById(R.id.password)
    EditText etPassword;

    static final int LOGIN_SUCCESS = 10023;
    static final int LOGIN_FAIL = 10025;

    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);

            switch (msg.what)
            {
                case LOGIN_SUCCESS:
                {
                    Toast toast = Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                    Intent intent = new Intent(LoginActivity.this, DoctorActivity_.class);
                    startActivity(intent);

                    break;
                }
                case LOGIN_FAIL:
                {
                    Toast toast = Toast.makeText(LoginActivity.this, "Username has been registered", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    break;
                }
            }
        }
    };

    @Click(R.id.sign_up_button)
    void signUpClicked(Button button)
    {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity_.class);
        startActivity(intent);
    }

    @AfterViews
    void init()
    {
        helper.ossService = OSSServiceProvider.getService();
        helper.ossService.setApplicationContext(getApplicationContext());
        helper.Init();
    }

    @Click(R.id.login_button)
    void loginClicked(Button button)
    {
//        Intent intent = new Intent(LoginActivity.this, DoctorActivity_.class);
//        startActivity(intent);

        Toast toast = Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        Intent intent = new Intent(LoginActivity.this, DoctorActivity_.class);
        startActivity(intent);

//        final String username = etUsername.getText().toString();
//        String password = etPassword.getText().toString();
//
//        if (username.equals(""))
//        {
//            Toast.makeText(this, "Please input user name", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (password.length() < 6)
//        {
//            Toast.makeText(this, "Password's length must >=6", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        helper.getObjectAsync("00_Config/Doctors.txt", new GetBytesCallback()
//        {
//            @Override
//            public void onSuccess(String s, byte[] bytes)
//            {
//                String maps = new String(bytes);
//                String[] usernames = maps.split("\n");
//
//                for (int i = 0; i < usernames.length; ++i)
//                {
//                    String[] IDs = usernames[i].split("\t");
//                    if (username.equals(IDs[0]) && username.equals(IDs[1]))
//                    {
//                        Message message = new Message();
//                        message.what = LOGIN_SUCCESS;
//                        handler.sendMessage(message);
//                        return;
//                    }
//                }
//                Message message = new Message();
//                message.what = LOGIN_FAIL;
//                handler.sendMessage(message);
//            }
//
//            @Override
//            public void onProgress(String s, int i, int i1)
//            {
//
//            }
//
//            @Override
//            public void onFailure(String s, OSSException e)
//            {
//
//            }
//        });

    }


}
