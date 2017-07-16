package com.brainhealthtest.demo.activity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.Process;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.brainhealthtest.R;
import com.brainhealthtest.demo.util.GlobalUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SelectActivity extends Activity
{
    public static boolean HasDevice = false;
    deviceAdapter adapter = new deviceAdapter();
    LeScanCallback bleScancallBack = new LeScanCallback()
    {
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord)
        {
            boolean hasDevice = false;
            MainActivity.resultBytes = scanRecord;
            for (BluetoothDevice mDevice : SelectActivity.this.deviceList)
            {
                if (mDevice != null
                        && mDevice.getAddress().toString()
                        .equals(device.getAddress().toString()))
                {
                    hasDevice = true;
                }
            }
            if (!hasDevice)
            {
                Intent intent = new Intent();
                intent.setAction(GlobalUtils.DEVICE_SCANED);
                intent.putExtra(GlobalUtils.EXTRA_DEVICE, device);
                SelectActivity.this.sendBroadcast(intent);
                SelectActivity.this.deviceList.add(device);
                if (scanRecord[29] == (byte) 0 && scanRecord[30] == (byte) 2)
                {
                    SelectActivity.this.versionList.add(Integer.valueOf(0));
                } else
                {
                    SelectActivity.this.versionList.add(Integer.valueOf(1));
                }
            }
        }
    };
    private BluetoothAdapter bluetoothAdapter;
    OnItemClickListener deviceItemClickListener = new OnItemClickListener()
    {
        public void onItemClick(AdapterView<?> adapterView, View arg1,
                                int arg2, long arg3)
        {
            Intent intent = new Intent(SelectActivity.this, MainActivity.class);
            intent.putExtra(GlobalUtils.DEVICE_SELECTED,
                    (Parcelable) SelectActivity.this.deviceList.get(arg2));
            intent.putExtra(GlobalUtils.DEVICE_VERSION,
                    (Serializable) SelectActivity.this.versionList.get(arg2));
            SelectActivity.this.startActivity(intent);
        }
    };
    private List<BluetoothDevice> deviceList = new ArrayList<BluetoothDevice>();
    private ListView deviceListView;
    private BroadcastReceiver humitureScanBroadCase = new BroadcastReceiver()
    {
        public void onReceive(Context context, Intent intent)
        {
            if (intent.getAction().equals(GlobalUtils.DEVICE_SCANED))
            {
                final BluetoothDevice mdevice = (BluetoothDevice) intent
                        .getParcelableExtra(GlobalUtils.EXTRA_DEVICE);
                SelectActivity.this.runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        if (!SelectActivity.this.sp.contains(mdevice
                                .getAddress()))
                        {
                            SelectActivity.this.sp
                                    .edit()
                                    .putString(mdevice.getAddress(),
                                            mdevice.getName()).commit();
                        }
                        SelectActivity.this.adapter.notifyDataSetChanged();
                        System.out.println("devicename:" + mdevice.getName());
                    }
                });
            }
        }
    };
    private ImageButton refresh;
    SharedPreferences sp;
    private List<Integer> versionList = new ArrayList<Integer>();

    class deviceAdapter extends BaseAdapter
    {

        class ViewHolder
        {
            TextView addressText = null;
            TextView nameText = null;

            ViewHolder()
            {
            }
        }

        deviceAdapter()
        {
        }

        public int getCount()
        {
            return SelectActivity.this.deviceList.size();
        }

        public Object getItem(int position)
        {
            return SelectActivity.this.deviceList.get(position);
        }

        public long getItemId(int position)
        {
            return (long) position;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewHolder holder = new ViewHolder();
            if (convertView == null)
            {
                convertView = LayoutInflater.from(SelectActivity.this).inflate(
                        R.layout.devicelist_item, null);
                holder.nameText = (TextView) convertView
                        .findViewById(R.id.textname);
                holder.addressText = (TextView) convertView
                        .findViewById(R.id.textaddress);
                convertView.setTag(holder);
            } else
            {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.nameText.setText(SelectActivity.this.sp.getString(
                    ((BluetoothDevice) SelectActivity.this.deviceList
                            .get(position)).getAddress(), "YFPen"));
            holder.addressText.setText("");
            return convertView;
        }
    }

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(128, 128);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.devicelayout);
        this.sp = getSharedPreferences("devicename", 0);
        this.deviceListView = (ListView) findViewById(R.id.device_list);
        this.refresh = (ImageButton) findViewById(R.id.refresh);
        this.deviceListView.setAdapter(this.adapter);
        this.deviceListView.setOnItemClickListener(this.deviceItemClickListener);
        this.bluetoothAdapter = ((BluetoothManager) getSystemService(BLUETOOTH_SERVICE)).getAdapter();
        IntentFilter filter = new IntentFilter();
        filter.addAction(GlobalUtils.DEVICE_CONNECTED);
        filter.addAction(GlobalUtils.DEVICE_DISCONNECTED);
        filter.addAction(GlobalUtils.DEVICE_NOTIFICATION);
        filter.addAction(GlobalUtils.DEVICE_SCANED);
        filter.addAction(GlobalUtils.DEVICE_DISCOVERED);
        filter.addAction(GlobalUtils.READ_CHARACTERISTER_ACTION);
        registerReceiver(this.humitureScanBroadCase, filter);
        if (this.bluetoothAdapter != null)
        {
            startScan(true);
        }
        this.refresh.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                SelectActivity.this.deviceList = new ArrayList<BluetoothDevice>();
                SelectActivity.this.versionList = new ArrayList<Integer>();
                if (SelectActivity.this.adapter != null)
                {
                    SelectActivity.this.adapter.notifyDataSetChanged();
                }
                if (SelectActivity.this.bluetoothAdapter.isEnabled())
                {
                    SelectActivity.this.startScan(true);
                } else
                {
                    SelectActivity.this.runOnUiThread(new Runnable()
                    {
                        public void run()
                        {
                            Toast.makeText(SelectActivity.this,
                                    R.string.turnOn, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    protected void onPause()
    {
        super.onPause();
        System.out.println("onpause");
    }

    public void startScan(boolean enable)
    {
        if (!this.bluetoothAdapter.isEnabled())
        {
            runOnUiThread(new Runnable()
            {
                public void run()
                {
                    Toast.makeText(SelectActivity.this, R.string.turnOn,
                            Toast.LENGTH_SHORT).show();
                }
            });
        } else if (this.bluetoothAdapter == null)
        {
        } else
        {
            if (enable)
            {
                new Handler().postDelayed(new Runnable()
                {
                    public void run()
                    {
                        SelectActivity.this.bluetoothAdapter.stopLeScan(SelectActivity.this.bleScancallBack);
                        System.out.println("stop scan");
                    }
                }, (long) GlobalUtils.scanDelay);
                this.bluetoothAdapter.startLeScan(this.bleScancallBack);
                return;
            }
            this.bluetoothAdapter.stopLeScan(this.bleScancallBack);
        }
    }

    protected void onResume()
    {
        super.onResume();
        this.adapter.notifyDataSetChanged();
        System.out.println("onresume");
    }

    protected void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(this.humitureScanBroadCase);
        finish();
        Process.killProcess(Process.myPid());
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == 4)
        {
            finish();
        }
        return true;
    }
}
