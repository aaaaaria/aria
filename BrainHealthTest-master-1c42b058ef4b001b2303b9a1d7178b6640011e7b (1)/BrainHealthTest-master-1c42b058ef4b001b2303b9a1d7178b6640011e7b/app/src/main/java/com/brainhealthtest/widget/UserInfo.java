package com.brainhealthtest.widget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whaodar on 2016/12/9.
 */

public class UserInfo
{
    static UserInfo userInfo = new UserInfo();

    public String username;
    public List<String> cases = new ArrayList<>();
    public String currentCase;

    private UserInfo()
    {
    }

    public static UserInfo getInstance()
    {
        return userInfo;
    }

}
