package com.brainhealthtest.osshelper;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tester
{
    static List<String> testerID;
    static List<String> paticipaterID;

    public static void Init() throws IOException
    {
        Random random = new Random();
        testerID = new ArrayList<String>();
        paticipaterID = new ArrayList<String>();
        testerID.add("839100-001001-20150518");
        testerID.add("845100-001001-20150518");
        testerID.add("840100-001001-20150518");
        testerID.add("782100-001001-20150518");
        testerID.add("847100-001001-20150518");
        testerID.add("016100-001001-20150518");
        testerID.add("725100-001001-20150518");
        for (int i = 0; i < 70; ++i)
        {
            int num = random.nextInt(999) % (999 - 100 + 1) + 100;
            paticipaterID.add(Integer.toString(num));
            //System.out.println(paticipaterID.get(paticipaterID.size() - 1));
        }

        File file = new File("/Users/zrfan/Documents/brainhealthapp/IDmap.txt");
        FileWriter fw = null;
        BufferedWriter writer = null;
        try
        {
            fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            for (int i = 0; i < testerID.size(); ++i)
            {
                for (int j = 0; j < 10; ++j)
                {
                    int index = random.nextInt(paticipaterID.size() - 1);
                    writer.write(testerID.get(i) + "\t000-" + paticipaterID.get(index) + "001-001001-20150520");
                    writer.newLine();
                    writer.write(testerID.get(i) + "\t000-" + paticipaterID.get(index) + "002-001001-20150520");
                    writer.newLine();
                    writer.write(testerID.get(i) + "\t000-" + paticipaterID.get(index) + "003-001001-20150520");
                    writer.newLine();
                }
            }
            writer.flush();
        } finally
        {
            try
            {
                writer.close();
                fw.close();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException
    {
        Helper helper = new Helper();
        helper.Init();

        //System.out.println(helper.CheckID("839100-001001-20150518", "000-326002-001001-20150520"));

        //Init();
        //helper.putObject("00_Config/IDmap.txt", "/Users/zrfan/Documents/brainhealthapp/IDmap.txt");
    }
}
