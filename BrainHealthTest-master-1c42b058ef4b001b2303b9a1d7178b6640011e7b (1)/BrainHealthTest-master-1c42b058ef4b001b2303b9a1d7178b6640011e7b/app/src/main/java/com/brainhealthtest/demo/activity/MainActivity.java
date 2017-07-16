package com.brainhealthtest.demo.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.brainhealthtest.R;
import com.brainhealthtest.demo.service.PenBleService;
import com.brainhealthtest.demo.service.PenDataService;
import com.brainhealthtest.demo.util.GlobalUtils;
import com.brainhealthtest.demo.util.ProUtils;
import com.brainhealthtest.demo.util.SaveTofile;
import com.brainhealthtest.demo.view.HorzScrollMain;
import com.brainhealthtest.demo.view.Showview;
import com.brainhealthtest.drawingtest.DrawClockActivity;
import com.brainhealthtest.drawingtest.ViewImgActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@SuppressLint({"InflateParams", "HandlerLeak", "SimpleDateFormat"})
public class MainActivity extends Activity
{
    private static Showview bleView;
    public static RelativeLayout bottomView;
    public static RelativeLayout camerafocus;
    public static boolean connected = false;
    public static Activity ctx;
    public static String currentFilepath = null;
    public static int currentSwitchPosition = -1;
    public static float dx = 0.0f;
    public static float dx_added = 0.0f;
    public static float dy = 0.0f;
    public static float dy_added = 0.0f;
    public static String filename = null;
    public static int height;
    public static List<String> listArray;
    public static List<String> listArray_guid;
    public static List<Float> mList;
    public static int maxHeight = 0;
    public static int maxWidth = 0;
    public static Handler mhandler;
    public static String note_name = null;
    public static boolean offStart = false;
    private static int reconnectTime = 0;
    public static byte[] resultBytes = null;
    public static RelativeLayout savefocus;
    public static boolean screenPower = true;
    public static HorzScrollMain scrollView;
    public static LinearLayout toolLinear;
    public static RelativeLayout topView;
    public static int width;
    public ImageButton saveButton;
    private PenBleService bleService;
    private int by_index = 0;
    private static SaveTofile record = new SaveTofile();
    OnClickListener ModifyNameListener = new OnClickListener()
    {
        public void onClick(View v)
        {
            Builder builder = new Builder(MainActivity.this);
            builder.setMessage(R.string.modify_title);
            AlertDialog dialog = builder.create();
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.devicename, null);
            dialog.setView(view);
            String ok = MainActivity.this.getResources().getString(R.string.ok);
            String cancel = MainActivity.this.getResources().getString(R.string.cancel);
            final EditText nameTxt = (EditText) view
                    .findViewById(R.id.devicename_edittext);
            dialog.setButton(-1, ok, new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                {
                    if (!nameTxt.getText().toString().trim().equals("")
                            && MainActivity.this.device != null
                            && MainActivity.this.sp.contains(MainActivity.this.device.getAddress()))
                    {
                        MainActivity.this.sp.edit().remove(MainActivity.this.device.getAddress()).commit();
                        MainActivity.this.sp.edit().putString(
                                MainActivity.this.device.getAddress(),
                                nameTxt.getText().toString()).commit();
                    }
                }
            });
            dialog.setButton(-2, cancel, new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                {
                }
            });
            dialog.show();
        }
    };

    Handler connectHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            if (MainActivity.reconnectTime > 20)
            {
                MainActivity.reconnectTime = 0;
                MainActivity.this.connectHandler.removeCallbacksAndMessages(null);
                MainActivity.this.runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        Toast.makeText(MainActivity.this, R.string.nullservice, Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (MainActivity.this.device != null)
            {
                MainActivity.reconnectTime = MainActivity.reconnectTime + 1;
                Message message;
                switch (msg.what)
                {
                    case 1/* 1 */:
                        try
                        {
                            Thread.sleep(500);
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        if (MainActivity.this.bleService.bleCharacteristic == null || MainActivity.this.bleService.bleCharacteristicTwo == null)
                        {
                            MainActivity.this.connect();
                            message = MainActivity.this.connectHandler.obtainMessage();
                            message.what = 1;
                            MainActivity.this.connectHandler.sendMessage(message);
                        } else
                        {
                            MainActivity.this.readCharacterister = true;
                            message = MainActivity.this.connectHandler.obtainMessage();
                            message.what = 2;
                            MainActivity.this.connectHandler.sendMessage(message);
                        }
                        System.out.println("handler 1");
                        return;
                    case 2 /* 2 */:
                        boolean notify = MainActivity.this.setNotify();
                        if (!MainActivity.connected)
                        {
                            message = MainActivity.this.connectHandler.obtainMessage();
                            message.what = 1;
                            MainActivity.this.connectHandler.sendMessage(message);
                            return;
                        } else if (notify)
                        {
                            message = MainActivity.this.connectHandler.obtainMessage();
                            message.what = 3;
                            MainActivity.this.connectHandler.sendMessage(message);
                            return;
                        } else
                        {
                            message = MainActivity.this.connectHandler.obtainMessage();
                            message.what = 2;
                            MainActivity.this.connectHandler.sendMessage(message);
                            return;
                        }
                    case 3 /* 3 */:
                        boolean read = MainActivity.this.ReadBytes();
                        if (!MainActivity.connected)
                        {
                            message = MainActivity.this.connectHandler.obtainMessage();
                            message.what = 1;
                            MainActivity.this.connectHandler.sendMessage(message);
                        } else if (read)
                        {
                            MainActivity.reconnectTime = 21;
                            MainActivity.this.connectHandler.removeCallbacksAndMessages(null);
                        } else
                        {
                            MainActivity.this.ReadBytes();
                            message = MainActivity.this.connectHandler
                                    .obtainMessage();
                            message.what = 3;
                            MainActivity.this.connectHandler.sendMessage(message);
                        }
                        System.out.println("handler 3");
                        return;
                    default:
                        return;
                }
            }
        }
    };
    OnClickListener connectListener = new OnClickListener()
    {
        public void onClick(View v)
        {
            Message msg;
            if (MainActivity.this.bleService != null)
            {
                MainActivity.this.connectHandler
                        .removeCallbacksAndMessages(null);
                msg = MainActivity.this.connectHandler.obtainMessage();
                msg.what = 1;
                MainActivity.this.connectHandler.sendMessage(msg);
                MainActivity.this.ReadBytes();
            } else
            {
                MainActivity.this.connectHandler
                        .removeCallbacksAndMessages(null);
                msg = MainActivity.this.connectHandler.obtainMessage();
                msg.what = 1;
                MainActivity.this.connectHandler.sendMessage(msg);
                MainActivity.this.ReadBytes();
            }
        }
    };
    private byte[] databytes = new byte[60];
    BluetoothDevice device;
    private ServiceConnection humitureConnection = new ServiceConnection()
    {
        public void onServiceDisconnected(ComponentName name)
        {
        }

        public void onServiceConnected(ComponentName name, IBinder service)
        {
            MainActivity.this.bleService = ((PenBleService.LocalBinder) service)
                    .getService();
            if (!MainActivity.this.bleService.initBle())
            {
                MainActivity.this.runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        Toast.makeText(MainActivity.this, R.string.turnOn,
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
            Message msg = MainActivity.this.connectHandler.obtainMessage();
            msg.what = 1;
            MainActivity.this.connectHandler.sendMessage(msg);
        }
    };
    private BroadcastReceiver humitureScanBroadCase = new BroadcastReceiver()
    {
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();
            if (!action.equals(GlobalUtils.DEVICE_CONNECTED))
            {
                if (action.equals(GlobalUtils.DEVICE_NOTIFICATION)
                        || action
                        .equals(GlobalUtils.READ_CHARACTERISTER_ACTION))
                {
                    MainActivity.resultBytes = intent
                            .getByteArrayExtra(GlobalUtils.EXTRA_VALUE);
                    ProUtils.LAST_WRITE_TIME = System.currentTimeMillis();
                    MainActivity.this.runOnUiThread(new Runnable()
                    {
                        public void run()
                        {
                            if (MainActivity.resultBytes != null
                                    && MainActivity.resultBytes.length > 0)
                            {
                                int length;
                                int i;
                                MainActivity access$0;
                                int index;
                                if (ProUtils.VERSION == 1)
                                {
                                    length = MainActivity.resultBytes.length;
                                    for (i = 0; i < length; i++)
                                    {
                                        MainActivity.this.databytes[MainActivity.this.by_index
                                                + i] = MainActivity.resultBytes[i];
                                    }
                                    System.out.println();
                                    access$0 = MainActivity.this;
                                    access$0.by_index = access$0.by_index
                                            + length;
                                    MainActivity.this.checkPenData();
                                    if (MainActivity.this.by_index >= 6)
                                    {
                                        index = MainActivity.this.by_index
                                                - (MainActivity.this.by_index % 6);
                                        MainActivity.this.penData(
                                                MainActivity.this.databytes,
                                                index);
                                        for (i = 0; i < MainActivity.this.by_index % 6; i++)
                                        {
                                            MainActivity.this.databytes[i] = MainActivity.this.databytes[index
                                                    + i];
                                        }
                                        access$0 = MainActivity.this;
                                        access$0.by_index = access$0.by_index % 6;
                                    }
                                } else if (ProUtils.VERSION == 0
                                        && (MainActivity.resultBytes[0] & 128) == 0)
                                {
                                    length = MainActivity.resultBytes[0];
                                    for (i = 0; i < length; i++)
                                    {
                                        MainActivity.this.databytes[MainActivity.this.by_index
                                                + i] = MainActivity.resultBytes[i + 1];
                                    }
                                    access$0 = MainActivity.this;
                                    access$0.by_index = access$0.by_index
                                            + length;
                                    MainActivity.this.checkPenData();
                                    if (MainActivity.this.by_index >= 6)
                                    {
                                        index = MainActivity.this.by_index
                                                - (MainActivity.this.by_index % 6);
                                        MainActivity.this.penData(
                                                MainActivity.this.databytes,
                                                index);
                                        for (i = 0; i < MainActivity.this.by_index % 6; i++)
                                        {
                                            MainActivity.this.databytes[i] = MainActivity.this.databytes[index
                                                    + i];
                                        }
                                        access$0 = MainActivity.this;
                                        access$0.by_index = access$0.by_index % 6;
                                    }
                                }
                            }
                        }
                    });
                } else if (action.equals(GlobalUtils.DEVICE_DISCONNECTED))
                {
                    MainActivity.connected = false;
                    MainActivity.this.readCharacterister = false;
                    MainActivity.this.runOnUiThread(new Runnable()
                    {
                        public void run()
                        {
                            Toast.makeText(MainActivity.this,
                                    R.string.disconnected, Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });
                }
            }
        }
    };
    private LayoutInflater inflater;
    private Intent intentOffscreenService;
    private boolean isMove = false;
    private boolean isStart = false;
    private float preX = 0.0f;
    private float preY = 0.0f;
    private boolean readCharacterister = false;
    public static String filenamed = "default";

    OnClickListener saveListener = new OnClickListener()
    {
        public void onClick(View v)
        {
            Showview.mPaint.setXfermode(null);
            Showview.mPaint.setStrokeWidth(2.0f);
            MainActivity.this.saveBitmapNotes();
            Intent intent0 = new Intent();
            if (SaveTofile.num <= 3)
            {
                intent0.setClass(MainActivity.this, ViewImgActivity.class);//pic 123 - return to draw1 2 3
            } else if (SaveTofile.num == 7)
            {
                intent0.setClass(MainActivity.this, DrawClockActivity.class);//clock1 - return to drawclock
            }
            startActivity(intent0);
        }
    };

    private BroadcastReceiver screenLockReceiver = new BroadcastReceiver()
    {
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();
            if ("android.intent.action.SCREEN_ON".equals(action))
            {
                if (!MainActivity.screenPower)
                {
                    MainActivity.this
                            .stopService(MainActivity.this.intentOffscreenService);
                    IntentFilter filter = new IntentFilter();
                    filter.addAction(GlobalUtils.DEVICE_CONNECTED);
                    filter.addAction(GlobalUtils.DEVICE_DISCONNECTED);
                    filter.addAction(GlobalUtils.DEVICE_NOTIFICATION);
                    filter.addAction(GlobalUtils.DEVICE_SCANED);
                    filter.addAction(GlobalUtils.DEVICE_DISCOVERED);
                    filter.addAction(GlobalUtils.READ_CHARACTERISTER_ACTION);
                    MainActivity.this.registerReceiver(
                            MainActivity.this.humitureScanBroadCase, filter);
                }
                MainActivity.screenPower = true;
            } else if ("android.intent.action.SCREEN_OFF".equals(action))
            {
                MainActivity.screenPower = false;
                MainActivity.this
                        .unregisterReceiver(MainActivity.this.humitureScanBroadCase);
                MainActivity.this
                        .startService(MainActivity.this.intentOffscreenService);
            }
        }
    };
    private SharedPreferences sp;
    public View viewChild2;

    class MonitorThread extends Thread
    {
        MonitorThread()
        {
        }

        public void run()
        {
            while (MainActivity.this.bleService != null
                    && MainActivity.this.device != null)
            {
                if (ProUtils.LAST_WRITE_TIME != 0)
                {
                    long time = System.currentTimeMillis();
                    if (time - ProUtils.LAST_WRITE_TIME > 1200)
                    {
                        if (MainActivity.this.device != null)
                        {
                            MainActivity.this.bleService.setRead();
                            ProUtils.LAST_WRITE_TIME = time;
                        }
                        System.out.println("read again");
                    }
                }
            }
        }
    }

    @SuppressLint("InflateParams")
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(128, 128);
        getWindow().setFlags(1024, 1024);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        maxWidth = dm.widthPixels;
        maxHeight = dm.heightPixels;
        this.inflater = LayoutInflater.from(this);
        scrollView = (HorzScrollMain) this.inflater.inflate(
                R.layout.yfsmartble, null);
        setContentView(scrollView);
        this.intentOffscreenService = new Intent(this, PenDataService.class);
        this.sp = getSharedPreferences("devicename", 0);
        initWidget();
        mhandler = new Handler();
        listArray = new ArrayList<String>();
        listArray_guid = new ArrayList<String>();
        scrollView.initView(new View[]{this.viewChild2}, 0);

        this.saveButton.setOnClickListener(this.saveListener);
        IntentFilter screenFilter = new IntentFilter();
        screenFilter.addAction("android.intent.action.SCREEN_ON");
        screenFilter.addAction("android.intent.action.SCREEN_OFF");
        registerReceiver(this.screenLockReceiver, screenFilter);
        dx = (((float) maxWidth) * 1.0f) / 11000.0f;
        dy = (((float) maxHeight) * 1.0f) / 15000.0f;
        if (dx < dy)
        {
            dy = dx;
            dy_added = (((float) maxHeight) - (dx * 15000.0f)) / 2.0f;
            System.out.println("maxHeight:" + maxHeight + ", add:" + dy_added);
        } else
        {
            dx = dy;
            dx_added = ((((float) maxWidth) - (dy * 11000.0f)) / 2.0f) * dy;
        }
        GlobalUtils.dx = dx;
        GlobalUtils.dy = dy;
        checkPenData();
        Intent deviceIntent = getIntent();
        this.device = (BluetoothDevice) deviceIntent
                .getParcelableExtra(GlobalUtils.DEVICE_SELECTED);
        ProUtils.VERSION = deviceIntent.getIntExtra(GlobalUtils.DEVICE_VERSION,
                1);
        bindService(new Intent(this, PenBleService.class),
                this.humitureConnection, 1);
    }

    private boolean connect()
    {
        if (this.bleService == null || this.device == null)
        {
            runOnUiThread(new Runnable()
            {
                public void run()
                {
                    Toast.makeText(MainActivity.this, R.string.nulldevice, Toast.LENGTH_SHORT).show();
                }
            });
        } else
        {
            if (!connected && this.bleService.Connect(this.device.getAddress()))
            {
                System.out.println("connecting");
            }
            if (connected && !this.readCharacterister)
            {
                this.readCharacterister = this.bleService.setNotification();
                if (this.readCharacterister)
                {
                    return true;
                }
                reconnectTime++;
                if (reconnectTime % 8 == 0)
                {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean setNotify()
    {
        if (this.readCharacterister && this.bleService != null)
        {
            if (this.bleService.SetNoficiationData())
            {
                return true;
            }
            System.out.println("setNotify");
        }
        return false;
    }

    private boolean ReadBytes()
    {
        if (!this.readCharacterister || this.bleService == null
                || !this.bleService.setRead())
        {
            return false;
        }
        runOnUiThread(new Runnable()
        {
            public void run()
            {
                Toast.makeText(MainActivity.this, R.string.connected, Toast.LENGTH_SHORT).show();
            }
        });
        new MonitorThread().start();
        return true;
    }

    private void checkPenData()
    {
        int length = this.by_index;
        int index_1 = 0;
        int[] indexArr = new int[40];
        int i = 0;
        while (i < length)
        {
            if (toHex(this.databytes[i]).equals("80")
                    && (toHex(this.databytes[i + 1]).equals("80")
                    || toHex(this.databytes[i + 1]).equals("81") || toHex(
                    this.databytes[i + 1]).equals("88")))
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
                    for (j = 0; j < this.by_index - index_len; j++)
                    {
                        this.databytes[(i * 6) + j] = this.databytes[((i * 6) + j)
                                + index_len];
                    }
                    this.by_index -= index_len;
                }
                index_len = indexArr[i + 1] - indexArr[i];
                if (index_len != 6)
                {
                    for (j = 0; j < this.by_index - index_len; j++)
                    {
                        this.databytes[(i * 6) + j] = this.databytes[((i * 6) + j)
                                + index_len];
                    }
                    this.by_index -= index_len;
                }
            }
        }
        if (index_1 == 0 && this.by_index >= 6)
        {
            this.databytes = null;
            this.databytes = new byte[60];
            this.by_index = 0;
        }
    }

    private void penData(byte[] usbByte, int length)
    {
        byte[] byX = new byte[2];
        byte[] byY = new byte[2];
        int i = 0;
        while (i < (length / 6) * 6)
        {
            if (toHex(usbByte[i]).equals("80")
                    && (toHex(usbByte[i + 1]).equals("80")
                    || toHex(usbByte[i + 1]).equals("81") || toHex(
                    usbByte[i + 1]).equals("88")))
            {
                byX[0] = usbByte[i + 2];
                byX[1] = usbByte[i + 3];
                byY[0] = usbByte[i + 4];
                byY[1] = usbByte[i + 5];
                float py = (((float) byteToshort(byY)) * dy) + dy_added;
                float px = (((float) (byteToshort(byX) + 5500)) * dx)
                        + dx_added;
                if (toHex(usbByte[i]).equals("80")
                        && toHex(usbByte[i + 1]).equals("81"))
                {
                    record.writeFilePoints("Points_" + SaveTofile.s1, String.valueOf(px) + " "
                            + String.valueOf(py) + " 1 \n");
                    Showview.penOrtouch = true;
                    if (!this.isStart)
                    {
                        Showview.penOrtouch = true;
                        Showview.startDraw(px, py);
                        this.preX = px;
                        this.preY = py;
                        this.isStart = true;
                    } else if (Math.sqrt(Math.pow((double) (px - this.preX),
                            2.0d) + Math.pow((double) (py - this.preY), 2.0d)) < 300.0d)
                    {
                        Showview.moveDraw(px, py);
                        this.preX = px;
                        this.preY = py;
                        this.isMove = true;
                    } else
                    {
                        Showview.moveDraw((this.preX + px) / 2.0f,
                                (this.preY + py) / 2.0f);
                        this.preX = (this.preX + px) / 2.0f;
                        this.preY = (this.preY + py) / 2.0f;
                        Showview.moveDraw(px, py);
                        this.preX = px;
                        this.preY = py;
                        this.isMove = true;
                    }
                } else if (toHex(usbByte[i]).equals("80")
                        && toHex(usbByte[i + 1]).equals("88"))
                {
                    record.writeFilePoints("Points_" + SaveTofile.s1, String.valueOf(px) + " "
                            + String.valueOf(py) + " 0 \n");
                    if (!this.isStart)
                    {
                        Showview.HangDraw(px, py);
                    }
                    if (this.isMove || this.isStart)
                    {
                        Showview.upDraw(px, py);
                        this.isStart = false;
                        this.isMove = false;
                    }
                }
            }
            i += 6;
        }
    }

    private void DrawOfflinePenlines(int mode)
    {
        List<List<Float>> pendataList = new ArrayList<List<Float>>();
        switch (mode)
        {
            case 0:
                pendataList = PenDataService.offPendatas;
                break;
            case 1 /* 1 */:
                pendataList = ProUtils.penDataList;
                ProUtils.showPathAgain = true;
                break;
        }
        if (pendataList.size() <= 0)
        {
            ProUtils.showPathAgain = true;
            return;
        }
        for (int i = 0; i < pendataList.size(); i++)
        {
            offStart = false;
            mList = (List<Float>) pendataList.get(i);
            float strokeWidth = ((Float) mList.get(mList.size() - 2))
                    .floatValue();
            float color = ((Float) mList.get(mList.size() - 1)).floatValue();
            if (color == -1.0f)
            {
                Showview.mPaint.setStrokeWidth(20.0f);
                Showview.mPaint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
            } else
            {
                Showview.mPaint.setStrokeWidth(strokeWidth);
                Showview.mPaint.setXfermode(null);
                Showview.mPaint.setColor((int) color);
            }
            float x = 0.0f;
            float y = 0.0f;
            for (int j = 0; j < mList.size() - 2; j += 2)
            {
                if (offStart)
                {
                    x = ((Float) mList.get(j)).floatValue();
                    y = ((Float) mList.get(j + 1)).floatValue();
                    Showview.moveDraw(x, y);
                } else
                {
                    x = ((Float) mList.get(j)).floatValue();
                    y = ((Float) mList.get(j + 1)).floatValue();
                    Showview.startDraw(x, y);
                    offStart = true;
                }
            }
            Showview.upDraw(x, y);
        }
        if (pendataList != null && mode == 0)
        {
            PenDataService.offPendatas.clear();
            PenDataService.offPendatas = null;
            PenDataService.offPendatas = new ArrayList<List<Float>>();
        }
        if (Showview.mPaint.getStrokeWidth() > 5.0f)
        {
            Showview.mPaint.setStrokeWidth(3.0f);
        }
        Showview.mPaint.setXfermode(null);
        ProUtils.showPathAgain = false;
    }

    private String toHex(byte by)
    {
        return String.valueOf("0123456789ABCDEF".charAt((by >> 4) & 15))
                + String.valueOf("0123456789ABCDEF".charAt(by & 15));
    }

    private short byteToshort(byte[] by)
    {
        return (short) (((by[1] & 255) << 8) | (by[0] & 255));
    }

    public void saveBitmapNotes()
    {
        if (ProUtils.HasNotes)
        {
            Bitmap mbitmap = MainActivity.overlayBitmap(
                    Showview.mBitmapBackground, Showview.mBitmap);
            filenamed = String.valueOf(new SimpleDateFormat("yyyyMMddHHmmss")
                    .format(Long.valueOf(System.currentTimeMillis())));
            if (SaveTofile.existFile(new StringBuilder(String
                    .valueOf(filenamed)).append(".jpg").toString()))
            {
                filenamed = new StringBuilder(String.valueOf(filenamed))
                        .append(String.valueOf(new SimpleDateFormat("HHmmss").format(Long
                                .valueOf(System.currentTimeMillis()))))
                        .toString();
            }
            SaveTofile.saveFileTocarmera(filenamed, mbitmap);
            final String notification = new StringBuilder(
                    String.valueOf(MainActivity.this.getResources().getString(
                            R.string.saveNotification))).append(":")
                    .append(filenamed).append(".jpg").toString();
            MainActivity.BackClear();
            try
            {
                MainActivity.this.sendBroadcast(new Intent(
                        "android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri
                        .fromFile(new File(SaveTofile.dirPath()
                                + filenamed + ".jpg"))));
            } catch (Exception e)
            {
            }
            new Handler().post(new Runnable()
            {
                public void run()
                {
                    Toast.makeText(MainActivity.ctx, notification,
                            Toast.LENGTH_SHORT).show();
                }
            });
            return;
        }
    }

    public static void BackClear()
    {
        if (!(Showview.mBitmap == null || Showview.mBitmap.isRecycled()))
        {
            Showview.mBitmap.recycle();
            Showview.mBitmap = null;
        }
        ProUtils.penDataList = new ArrayList<List<Float>>();
        Showview.mBitmap = Bitmap.createBitmap(maxWidth, maxHeight,
                Config.ARGB_4444);
        Showview.mCanvas = new Canvas(Showview.mBitmap);
        ProUtils.HasNotes = false;
    }

    protected void onResume()
    {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(GlobalUtils.DEVICE_CONNECTED);
        filter.addAction(GlobalUtils.DEVICE_DISCONNECTED);
        filter.addAction(GlobalUtils.DEVICE_NOTIFICATION);
        filter.addAction(GlobalUtils.DEVICE_SCANED);
        filter.addAction(GlobalUtils.DEVICE_DISCOVERED);
        filter.addAction(GlobalUtils.READ_CHARACTERISTER_ACTION);
        registerReceiver(this.humitureScanBroadCase, filter);
        System.out.println("bleview:" + bleView);
        runOnUiThread(new Runnable()
        {
            public void run()
            {
                MainActivity.this.DrawOfflinePenlines(0);
            }
        });
        if (ProUtils.PauseDestory)
        {
            DrawOfflinePenlines(1);
        }
        ProUtils.PauseDestory = false;
        ProUtils.showPathAgain = false;
        System.out.println("YFLenMain onResume");
    }

    protected void onRestart()
    {
        super.onRestart();
        if (!(connected || this.bleService == null || this.connectHandler == null))
        {
            this.connectHandler.removeCallbacksAndMessages(null);
            Message msg = this.connectHandler.obtainMessage();
            msg.what = 1;
            this.connectHandler.sendMessage(msg);
            ReadBytes();
        }
        System.out.println("onrestart");
    }

    protected void onStop()
    {
        super.onStop();
        System.out.println("onstop");
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    protected void onPause()
    {
        super.onPause();
        System.out.println("onpause");
    }

    protected void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(this.screenLockReceiver);
        reconnectTime = 0;
        if (this.bleService != null)
        {
            this.bleService.Disconnect();
            this.bleService.Close();
        }
        this.bleService = null;
        this.device = null;
        ProUtils.PauseDestory = true;
        unbindService(this.humitureConnection);
        unregisterReceiver(this.humitureScanBroadCase);
        this.connectHandler.removeCallbacksAndMessages(null);
        System.out.println("destroy");
    }

    private void initWidget()
    {
        this.viewChild2 = this.inflater.inflate(R.layout.horznoteview, null);
        bleView = (Showview) this.viewChild2.findViewById(R.id.showview);
        bottomView = (RelativeLayout) this.viewChild2
                .findViewById(R.id.bottomView);
        this.saveButton = (ImageButton) this.viewChild2
                .findViewById(R.id.saveButton);
        ctx = this;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == 4)
        {
            Builder builder = new Builder(this);
            builder.setTitle(R.string.exittitle).setMessage(R.string.quit)
                    .setCancelable(false);
            AlertDialog dialog = builder.create();
            dialog.setButton(-1, getResources().getString(R.string.ok),
                    new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            if (ProUtils.HasNotes)
                            {
                                MainActivity.filename = String
                                        .valueOf(new SimpleDateFormat(
                                                "yyyyMMddHHmmss").format(Long
                                                .valueOf(System
                                                        .currentTimeMillis())));
                                SaveTofile.saveFile(MainActivity.filename,
                                        MainActivity.overlayBitmap(
                                                Showview.mBitmapBackground,
                                                Showview.mBitmap));
                                MainActivity.currentFilepath = SaveTofile
                                        .dirPath() + MainActivity.filename;
                                MainActivity.BackClear();
                            }
                            MainActivity.reconnectTime = 0;
                            if (MainActivity.this.bleService != null)
                            {
                                MainActivity.this.bleService.Disconnect();
                            }
                            MainActivity.this.connectHandler
                                    .removeCallbacksAndMessages(null);
                            MainActivity.this.finish();
                        }
                    });
            dialog.setButton(-2, getResources().getString(R.string.cancel),
                    new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int which)
                        {
                        }
                    });
            dialog.show();
        }
        return true;
    }

    private static Bitmap overlayBitmap(Bitmap bitmap1, Bitmap bitmap2)
    {
        Bitmap bitmap = Bitmap.createBitmap(maxWidth, maxHeight,
                Config.ARGB_4444);
        Canvas mcanvs = new Canvas(bitmap);
        mcanvs.drawColor(-1);
        System.out.println("mx," + maxHeight + "," + maxHeight + ","
                + bitmap1.getWidth() + "," + bitmap1.getHeight());
        mcanvs.drawBitmap(bitmap1, 0.0f, 0.0f, null);
        mcanvs.drawBitmap(bitmap2, 0.0f, 0.0f, null);
        mcanvs.save(31);
        mcanvs.restore();
        return bitmap;
    }

    public static void nullFocus()
    {
        savefocus.setBackgroundResource(0);
    }
}
