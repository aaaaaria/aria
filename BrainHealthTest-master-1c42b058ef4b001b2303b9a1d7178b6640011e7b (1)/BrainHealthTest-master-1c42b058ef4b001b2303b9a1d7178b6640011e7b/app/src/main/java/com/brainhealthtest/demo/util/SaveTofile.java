package com.brainhealthtest.demo.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveTofile
{
    private static String CarmeraFile = "annazheng";
    private static String dirFile = "evernote";
    private static String direc = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static String savePath;
    public static String s1;
    public static int num;

    public static boolean saveFile(String filename, Bitmap mbitmap)
    {
        String dirpath = direc + File.separator + dirFile;
        File file = new File(dirpath);
        System.out.println("dirpath:" + dirpath);
        if (!file.isDirectory())
        {
            file.mkdir();
            System.out.println("make dir");
        }
        String savePath = new StringBuilder(String.valueOf(dirpath)).append(File.separator).append(filename).append(".jpg").toString();
        //save to home computer
        System.out.println("filepath:" + savePath);
        File save = new File(savePath);
        try
        {
            save.createNewFile();
            FileOutputStream out = new FileOutputStream(save);
            mbitmap.compress(CompressFormat.JPEG, 80, out);
            out.flush();
            out.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean saveFileTocarmera(String filename, Bitmap mbitmap)
    {
        String dirpath = direc + File.separator + CarmeraFile;
        File file = new File(dirpath);
        System.out.println("dirpath:" + dirpath);
        if (!file.isDirectory())
        {
            file.mkdir();
            System.out.println("make dir");
        }
        String savePath = new StringBuilder(String.valueOf(dirpath)).append(File.separator).append("IMG_").append(s1).append(".jpg").toString();
        System.out.println("filepath:" + savePath);
        File save = new File(savePath);
        try
        {
            save.createNewFile();
            FileOutputStream out = new FileOutputStream(save);
            mbitmap.compress(CompressFormat.JPEG, 80, out);
            out.flush();
            out.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return true;
    }

    public void writeFilePoints(String fileName, String message)
    {

        String dirpath = direc + File.separator + CarmeraFile;
        File file = new File(dirpath);
        if (!file.isDirectory())
        {
            file.mkdir();
            System.out.println("make dir");
        }

        savePath = new StringBuilder(String.valueOf(dirpath)).append(File.separator).append(fileName).append(".txt").toString();
        File save = new File(savePath);
        try
        {
            if (!save.exists())
            {
                save.createNewFile();
            }
            FileOutputStream fout = new FileOutputStream(save, true);
            byte[] bytes = message.getBytes();
            fout.write(bytes);
            fout.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String dirPath()
    {
        return direc + File.separator + dirFile + File.separator;
    }

    public static boolean existFile(String filename)
    {
        if (new File(dirPath() + filename).exists())
        {
            return true;
        }
        return false;
    }
}
