package com.brainhealthtest.widget;

/**
 * Created by whaodar on 2016/12/9.
 */

public class TestCase
{
    private String title;
    private String author;
    private String imageUrl;
    private String time;

    public TestCase(String time, String title)
    {
        this.time = time;
        this.title = title;
    }

    public TestCase(String title, String author, String imageUrl)
    {
        this.title = title;
        this.author = author;
        this.imageUrl = imageUrl;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getTitle()
    {
        return title;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }
}
