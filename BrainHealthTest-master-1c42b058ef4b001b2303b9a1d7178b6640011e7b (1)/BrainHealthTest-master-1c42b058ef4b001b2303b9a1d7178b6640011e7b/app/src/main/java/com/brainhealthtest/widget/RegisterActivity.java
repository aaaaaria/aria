package com.brainhealthtest.widget;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.callback.GetBytesCallback;
import com.alibaba.sdk.android.oss.callback.SaveCallback;
import com.brainhealthtest.R;
import com.brainhealthtest.osshelper.Helper;
import com.alibaba.sdk.android.oss.model.OSSException;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_register)
public class RegisterActivity extends Activity
{

    @ViewById(R.id.register_username)
    EditText etUsername;
    @ViewById(R.id.register_pwd)
    EditText etPassword;
    @ViewById(R.id.register_pwd_confirm)
    EditText etPasswordConfirm;

    static final int ADD_ACCOUNT = 10023;
    static final int ADD_ACCOUNT_FAIL = 10024;

    Helper helper = new Helper();

    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);

            switch (msg.what)
            {
                case ADD_ACCOUNT:
                {
                    Toast toast = Toast.makeText(RegisterActivity.this, "SUCCESS", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    RegisterActivity.this.finish();
                    break;
                }
                case ADD_ACCOUNT_FAIL:
                {
                    Toast toast = Toast.makeText(RegisterActivity.this, "Username has been registered", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    break;
                }
            }

        }
    };

    @AfterViews
    void init()
    {
        helper.ossService = OSSServiceProvider.getService();
        helper.ossService.setApplicationContext(getApplicationContext());
        helper.Init();
    }

    @Click(R.id.sign_up_button)
    void signUpClicked(Button button)
    {
        final String username = etUsername.getText().toString();
        final String pwd = etPassword.getText().toString();
        String pwdConfirm = etPasswordConfirm.getText().toString();

        if (username.equals(""))
        {
            Toast.makeText(this, "Please input user name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pwd.length() < 6 || pwdConfirm.length() < 6)
        {
            Toast.makeText(this, "Password's length must >=6", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pwd.equals(pwdConfirm))
        {
            Toast.makeText(this, "Password must be the same", Toast.LENGTH_SHORT).show();
            return;
        }


        helper.getObjectAsync("00_Config/Doctors.txt",
                new GetBytesCallback()
                {
                    @Override
                    public void onSuccess(String s, byte[] bytes)
                    {
                        String maps = new String(bytes);
                        String[] usernames = maps.split("\n");
                        for (int i = 0; i < usernames.length; ++i)
                        {
                            String[] IDs = usernames[i].split("\t");
                            if (username.equals(IDs[0]))
                            {
                                Message message = new Message();
                                message.what = ADD_ACCOUNT_FAIL;
                                handler.sendMessage(message);
                                return;
                            }
                        }

                        String string = maps + username + "\t" + pwd + "\n";
                        byte[] data = string.getBytes();

                        helper.putObjectAsync("00_Config/Doctors.txt", data, new SaveCallback()
                        {
                            @Override
                            public void onSuccess(String s)
                            {
                                Message message = new Message();
                                message.what = ADD_ACCOUNT;
                                handler.sendMessage(message);

                                helper.putObjectAsync("00_Config/DocCases/" + username, "".getBytes(), new SaveCallback()
                                {
                                    @Override
                                    public void onSuccess(String s)
                                    {
                                        Message message = new Message();
                                        message.what = ADD_ACCOUNT;
                                        handler.sendMessage(message);
                                    }

                                    @Override
                                    public void onProgress(String s, int i, int i1)
                                    {

                                    }

                                    @Override
                                    public void onFailure(String s, OSSException e)
                                    {

                                    }
                                });

                            }

                            @Override
                            public void onProgress(String s, int i, int i1)
                            {

                            }

                            @Override
                            public void onFailure(String s, OSSException e)
                            {

                            }
                        });
                    }

                    @Override
                    public void onProgress(String s, int i, int i1)
                    {

                    }

                    @Override
                    public void onFailure(String s, OSSException e)
                    {

                    }
                });


    }

}
