package com.brainhealthtest.demo.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;


import com.brainhealthtest.demo.activity.MainActivity;
import com.brainhealthtest.demo.util.GlobalUtils;
import com.brainhealthtest.demo.util.ProUtils;
import com.brainhealthtest.demo.view.Showview;

import java.util.ArrayList;
import java.util.List;


public class PenDataService extends Service
{
    public static int by_index = 0;
    public static List<Float> curList = new ArrayList<Float>();
    public static byte[] databytes = new byte[60];
    public static boolean isMove = false;
    public static boolean isStart = false;
    public static List<List<Float>> offPendatas = new ArrayList<List<Float>>();
    public static float preX = 0.0f;
    public static float preY = 0.0f;
    public static byte[] resultBytes = null;
    public static int up_times = 0;
    private BroadcastReceiver penDataBroadCase = new BroadcastReceiver()
    {
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();
            if (action.equals(GlobalUtils.DEVICE_NOTIFICATION) || action.equals(GlobalUtils.READ_CHARACTERISTER_ACTION))
            {
                PenDataService.resultBytes = intent.getByteArrayExtra(GlobalUtils.EXTRA_VALUE);
                new Handler().post(new Runnable()
                {
                    public void run()
                    {
                        if (PenDataService.resultBytes != null && PenDataService.resultBytes.length > 0)
                        {
                            int length;
                            int i;
                            int index;
                            if (ProUtils.VERSION == 1)
                            {
                                length = PenDataService.resultBytes.length;
                                for (i = 0; i < length; i++)
                                {
                                    PenDataService.databytes[PenDataService.by_index + i] = PenDataService.resultBytes[i];
                                }
                                System.out.println();
                                PenDataService.by_index += length;
                                PenDataService.this.checkPenData();
                                if (PenDataService.by_index >= 6)
                                {
                                    index = PenDataService.by_index - (PenDataService.by_index % 6);
                                    PenDataService.this.penData(PenDataService.databytes, index);
                                    for (i = 0; i < PenDataService.by_index % 6; i++)
                                    {
                                        PenDataService.databytes[i] = PenDataService.databytes[index + i];
                                    }
                                    PenDataService.by_index %= 6;
                                }
                            } else if (ProUtils.VERSION == 0 && (PenDataService.resultBytes[0] & 128) == 0)
                            {
                                length = PenDataService.resultBytes[0];
                                for (i = 0; i < length; i++)
                                {
                                    PenDataService.databytes[PenDataService.by_index + i] = PenDataService.resultBytes[i + 1];
                                }
                                System.out.println();
                                PenDataService.by_index += length;
                                PenDataService.this.checkPenData();
                                if (PenDataService.by_index >= 6)
                                {
                                    index = PenDataService.by_index - (PenDataService.by_index % 6);
                                    PenDataService.this.penData(PenDataService.databytes, index);
                                    for (i = 0; i < PenDataService.by_index % 6; i++)
                                    {
                                        PenDataService.databytes[i] = PenDataService.databytes[index + i];
                                    }
                                    PenDataService.by_index %= 6;
                                }
                            }
                        }
                    }
                });
            }
        }
    };

    public IBinder onBind(Intent intent)
    {
        return null;
    }

    public void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(this.penDataBroadCase);
        System.out.println("service ondestroy");
    }

    public int onStartCommand(Intent intent, int flags, int startId)
    {
        IntentFilter filter = new IntentFilter();
        filter.addAction(GlobalUtils.DEVICE_NOTIFICATION);
        filter.addAction(GlobalUtils.READ_CHARACTERISTER_ACTION);
        registerReceiver(this.penDataBroadCase, filter);
        curList.clear();
        curList = null;
        curList = new ArrayList<Float>();
        up_times = 0;
        System.out.println("service onstartcommand");
        return super.onStartCommand(intent, flags, startId);
    }

    private void penData(byte[] usbByte, int length)
    {
        byte[] byX = new byte[2];
        byte[] byY = new byte[2];
        int i = 0;
        while (i < (length / 6) * 6)
        {
            if (toHex(usbByte[i]).equals("80") && (toHex(usbByte[i + 1]).equals("80") || toHex(usbByte[i + 1]).equals("81") || toHex(usbByte[i + 1]).equals("88")))
            {
                byX[0] = usbByte[i + 2];
                byX[1] = usbByte[i + 3];
                byY[0] = usbByte[i + 4];
                byY[1] = usbByte[i + 5];
                float py = (((float) byteToshort(byY)) * MainActivity.dy) + MainActivity.dy_added;
                float px = (((float) (byteToshort(byX) + 5500)) * MainActivity.dx) + MainActivity.dx_added;
                if (toHex(usbByte[i]).equals("80") && toHex(usbByte[i + 1]).equals("81"))
                {
                    if (!isStart)
                    {
                        curList = new ArrayList<Float>();
                        curList.add(Float.valueOf(px));
                        curList.add(Float.valueOf(py));
                        preX = px;
                        preY = py;
                        isStart = true;
                    } else if (Math.sqrt(Math.pow((double) (px - preX), 2.0d) + Math.pow((double) (py - preY), 2.0d)) < 100.0d)
                    {
                        curList.add(Float.valueOf(px));
                        curList.add(Float.valueOf(py));
                        preX = px;
                        preY = py;
                        isMove = true;
                    } else
                    {
                        curList.add(Float.valueOf(2.0f));
                        curList.add(Float.valueOf((float) Showview.mPaint.getColor()));
                        offPendatas.add(up_times, curList);
                        up_times++;
                        isStart = false;
                        isMove = false;
                    }
                } else if (toHex(usbByte[i]).equals("80") && ((toHex(usbByte[i + 1]).equals("88") || toHex(usbByte[i + 1]).equals("80")) && isMove))
                {
                    curList.add(Float.valueOf(2.0f));
                    curList.add(Float.valueOf((float) Showview.mPaint.getColor()));
                    offPendatas.add(up_times, curList);
                    up_times++;
                    isStart = false;
                    isMove = false;
                }
            }
            i += 6;
        }
    }

    private void checkPenData()
    {
        int length = by_index;
        int index_1 = 0;
        int[] indexArr = new int[40];
        int i = 0;
        while (i < length)
        {
            if (toHex(databytes[i]).equals("80") && (toHex(databytes[i + 1]).equals("80") || toHex(databytes[i + 1]).equals("81") || toHex(databytes[i + 1]).equals("88")))
            {
                indexArr[index_1] = i;
                index_1++;
            }
            i++;
        }
        if (index_1 >= 2)
        {
            for (i = 0; i < index_1 - 1; i++)
            {
                int index_len;
                int j;
                if (i == 0 && indexArr[0] != 0)
                {
                    index_len = indexArr[0];
                    for (j = 0; j < by_index - index_len; j++)
                    {
                        databytes[(i * 6) + j] = databytes[((i * 6) + j) + index_len];
                    }
                    by_index -= index_len;
                }
                index_len = indexArr[i + 1] - indexArr[i];
                if (index_len != 6)
                {
                    for (j = 0; j < by_index - index_len; j++)
                    {
                        databytes[(i * 6) + j] = databytes[((i * 6) + j) + index_len];
                    }
                    by_index -= index_len;
                }
            }
        }
        if (index_1 == 0 && by_index >= 6)
        {
            databytes = null;
            databytes = new byte[60];
            by_index = 0;
        }
    }

    private short byteToshort(byte[] by)
    {
        return (short) (((by[1] & 255) << 8) | (by[0] & 255));
    }

    private String toHex(byte by)
    {
        return String.valueOf("0123456789ABCDEF".charAt((by >> 4) & 15)) + String.valueOf("0123456789ABCDEF".charAt(by & 15));
    }
}
