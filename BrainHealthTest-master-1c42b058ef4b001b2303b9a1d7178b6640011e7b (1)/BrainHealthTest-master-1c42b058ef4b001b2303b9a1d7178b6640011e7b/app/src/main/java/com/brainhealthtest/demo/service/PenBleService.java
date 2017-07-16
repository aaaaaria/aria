package com.brainhealthtest.demo.service;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.brainhealthtest.demo.activity.MainActivity;
import com.brainhealthtest.demo.util.GlobalUtils;
import com.brainhealthtest.demo.util.SaveTofile;
import com.brainhealthtest.demo.util.ProUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class PenBleService extends Service
{
    public static List<BluetoothDevice> blueDeviceList;
    private UUID NOTIFICATION_DESCRIPTOR_UUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    private boolean appDisconnect = false;
    private BluetoothAdapter bleAdapter;
    private Binder bleBinder = new LocalBinder();
    private BluetoothGatt bleBluegatt;
    public BluetoothGattCharacteristic bleCharacteristic = null;
    public BluetoothGattCharacteristic bleCharacteristicTwo = null;
    private String bleDeviceAddress = null;
    private SaveTofile recordTime = null;
    private long currentTime = 0;
    BluetoothGattCallback bleGattcallback = new BluetoothGattCallback()
    {
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic)
        {
            Intent intent = new Intent();
            intent.setAction(GlobalUtils.DEVICE_NOTIFICATION);
            intent.putExtra(GlobalUtils.EXTRA_VALUE, characteristic.getValue());
            PenBleService.this.sendBroadcast(intent);
        }

        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status)
        {
            recordTime.writeFilePoints("Time", String.valueOf(System.currentTimeMillis() - currentTime) + " \n");
            currentTime = System.currentTimeMillis();
            if (PenBleService.this.bleCharacteristic != null && ProUtils.VERSION == 0)
            {
                try
                {
                    Thread.sleep(200);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                if (PenBleService.this.readTimes < 9)
                {
                    gatt.readCharacteristic(PenBleService.this.bleCharacteristic);
                    PenBleService penBleService = PenBleService.this;
                    penBleService.readTimes = penBleService.readTimes + 1;
                }
            }
            if (status == 0 && PenBleService.this.readTimes > 8 && ProUtils.VERSION == 0)
            {
                Intent intent = new Intent();
                intent.setAction(GlobalUtils.READ_CHARACTERISTER_ACTION);
                intent.putExtra(GlobalUtils.EXTRA_STATUS, status);
                intent.putExtra(GlobalUtils.EXTRA_VALUE, characteristic.getValue());
                PenBleService.this.sendBroadcast(intent);
            }
            if (status == 0 && ProUtils.VERSION == 1)
            {
                Intent intent = new Intent();
                intent.setAction(GlobalUtils.READ_CHARACTERISTER_ACTION);
                intent.putExtra(GlobalUtils.EXTRA_STATUS, status);
                intent.putExtra(GlobalUtils.EXTRA_VALUE, characteristic.getValue());
                PenBleService.this.sendBroadcast(intent);
            }
        }

        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState)
        {
            Intent intent = new Intent();
            if (status != 0)
            {
                return;
            }
            if (newState == 2)
            {
                intent.setAction(GlobalUtils.DEVICE_CONNECTED);
                PenBleService.this.sendBroadcast(intent);
                System.out.println("------------>connect state ");
                PenBleService.this.appDisconnect = false;
                MainActivity.connected = true;
            } else if (newState == 0)
            {
                MainActivity.connected = false;
                intent.setAction(GlobalUtils.DEVICE_DISCONNECTED);
                PenBleService.this.sendBroadcast(intent);
                System.out.println("------------>disconnect state ");
                synchronized (PenBleService.this.bleBluegatt)
                {
                    if (PenBleService.this.appDisconnect)
                    {
                        if (PenBleService.this.bleBluegatt != null)
                        {
                            PenBleService.this.bleBluegatt.close();
                            PenBleService.this.bleBluegatt = null;
                        } else
                        {
                            gatt.close();
                        }
                        //JUST FOR FUN AND I DONT GET IT
                    }
                }
            }
        }


        public void onServicesDiscovered(BluetoothGatt gatt, int status)
        {
            if (status != 0)
            {
                return;
            }
            if (PenBleService.this.bleCharacteristic == null || PenBleService.this.bleCharacteristicTwo == null)
            {
                synchronized (PenBleService.this.bleBluegatt)
                {
                    PenBleService.this.bleCharacteristic = PenBleService.this.bleBluegatt.getService(GlobalUtils.SERVICE_UUID).getCharacteristic(GlobalUtils.SUPPORT_ONE_UUID);
                    PenBleService.this.bleCharacteristicTwo = PenBleService.this.bleBluegatt.getService(GlobalUtils.SERVICE_UUID).getCharacteristic(GlobalUtils.SUPPORT_TWO_UUID);
                }
                Intent intent = new Intent();
                intent.setAction(GlobalUtils.DEVICE_DISCOVERED);
                PenBleService.this.sendBroadcast(intent);
            }
        }

        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status)
        {
            System.out.println("des read");
        }

        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status)
        {
            if (status == 0 && PenBleService.this.bleCharacteristic != null)
            {
                gatt.readCharacteristic(PenBleService.this.bleCharacteristic);
            }
            System.out.println("des write");
        }

        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status)
        {
            if (status == 0 && PenBleService.this.bleCharacteristic != null)
            {
                gatt.readCharacteristic(PenBleService.this.bleCharacteristic);
            }
            System.out.println("char write");
        }

        public void onReliableWriteCompleted(BluetoothGatt gatt, int status)
        {
            if (status == 0 && PenBleService.this.bleCharacteristic != null)
            {
                gatt.readCharacteristic(PenBleService.this.bleCharacteristic);
            }
            System.out.println("reli write");
        }
    };
    private BluetoothManager bleManager;
    public BluetoothGattService blegattService;
    private int readTimes = 0;

    public class LocalBinder extends Binder
    {
        public PenBleService getService()
        {
            return PenBleService.this;
        }
    }

    public IBinder onBind(Intent intent)
    {
        return this.bleBinder;
    }

    public boolean initBle()
    {
        this.bleManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        if (this.bleManager == null)
        {
            return false;
        }
        this.bleAdapter = this.bleManager.getAdapter();
        if (this.bleAdapter == null)
        {
            return false;
        }
        blueDeviceList = new ArrayList<BluetoothDevice>();
        if (this.bleAdapter.isEnabled())
        {
            return true;
        }
        return false;
    }

    private byte toByte(String str)
    {
        byte[] bys = str.getBytes();
        if (bys.length != 2)
        {
            return (byte) -1;
        }
        System.out.println(bys[1] + "," + bys[0]);
        for (int i = 0; i < 2; i++)
        {
            int mode = 0;
            if (bys[i] >= (byte) 65)
            {
                mode = 1;
            }
            switch (mode)
            {
                case 0:
                    bys[i] = (byte) (bys[i] - 48);
                    break;
                case 1 /*1*/:
                    bys[i] = (byte) (bys[i] - 55);
                    break;
                default:
                    break;
            }
        }
        System.out.println(bys[1] + "," + bys[0]);
        return (byte) (((bys[1] << 4) & 255) | (bys[0] & 255));
    }

    public void onDestroy()
    {
        if (blueDeviceList != null)
        {
            blueDeviceList.clear();
        }
        super.onDestroy();
    }

    public List<BluetoothDevice> getScanDevice()
    {
        if (blueDeviceList == null)
        {
            return null;
        }
        return blueDeviceList;
    }

    public boolean SetNoficiationData()
    {
        this.readTimes = 0;
        if (this.bleBluegatt == null || this.bleCharacteristic == null || this.bleCharacteristicTwo == null)
        {
            return false;
        }
        synchronized (this.bleBluegatt)
        {
            if (this.bleBluegatt.setCharacteristicNotification(this.bleCharacteristic, true))
            {
                BluetoothGattDescriptor des = this.bleCharacteristic.getDescriptor(this.NOTIFICATION_DESCRIPTOR_UUID);
                if (des == null)
                {
                    return false;
                } else if (des.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE))
                {
                    boolean boowrite = this.bleBluegatt.writeDescriptor(des);
                    try
                    {
                        Thread.sleep(50);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    if (boowrite)
                    {
                        return true;
                    }
                    return false;
                } else
                {
                    return false;
                }
            }
            return false;
        }
    }

    public boolean disableNotify()
    {
        boolean z = false;
        if (!(this.bleBluegatt == null || this.bleCharacteristic == null))
        {
            synchronized (this.bleBluegatt)
            {
                z = this.bleBluegatt.setCharacteristicNotification(this.bleCharacteristic, false);
            }
        }
        return z;
    }

    public boolean setRead()
    {
        if (this.bleBluegatt == null || this.bleCharacteristic == null || this.bleCharacteristicTwo == null)
        {
            return false;
        }
        boolean boo;
        synchronized (this.bleBluegatt)
        {
            boo = this.bleBluegatt.readCharacteristic(this.bleCharacteristic);
        }
        return boo;
    }

    public boolean setNotification()
    {
        if ((this.bleAdapter == null) || (this.bleBluegatt == null))
            return false;
        synchronized (this.bleBluegatt)
        {
            this.bleBluegatt.discoverServices();
            if (this.blegattService == null)
                this.blegattService = this.bleBluegatt.getService(GlobalUtils.SERVICE_UUID);
            if (this.blegattService != null)
            {
                return true;
            }
        }
        return false;
    }

    public boolean writeChar()
    {
        boolean z = false;
        if (!(this.bleCharacteristic == null || this.bleCharacteristicTwo == null || this.bleBluegatt == null))
        {
            this.bleCharacteristicTwo.setValue(new byte[]{(byte) 1});
            synchronized (this.bleBluegatt)
            {
                z = this.bleBluegatt.writeCharacteristic(this.bleCharacteristicTwo);
            }
        }
        return z;
    }

    public boolean readCharacterister(UUID supportUUID)
    {
        if (this.bleBluegatt == null)
        {
            return false;
        }
        synchronized (this.bleBluegatt)
        {
            if (this.blegattService == null)
            {
                this.blegattService = this.bleBluegatt.getService(GlobalUtils.SERVICE_UUID);
            }
            if (this.blegattService == null)
            {
                return false;
            }
            if (this.bleCharacteristic == null)
            {
                this.bleCharacteristic = this.blegattService.getCharacteristic(supportUUID);
            }
            if (this.bleCharacteristic == null)
            {
                return false;
            }
            this.bleBluegatt.readCharacteristic(this.bleCharacteristic);
            return true;
        }
    }

    public List<BluetoothGattService> getSupportServices()
    {
        if (this.bleBluegatt == null)
        {
            return null;
        }
        return this.bleBluegatt.getServices();
    }

    public boolean Connect(String address)
    {
        if (address == null || this.bleAdapter == null)
        {
            return false;
        }
        if (this.bleAdapter != null)
        {
            int i;
            if (this.bleBluegatt != null)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 1 && address.equals(this.bleDeviceAddress))
            {
                this.bleBluegatt.connect();
                return true;
            }
        }
        BluetoothDevice device = this.bleAdapter.getRemoteDevice(address);
        if (device == null)
        {
            return false;
        }
        this.bleBluegatt = device.connectGatt(this, false, this.bleGattcallback);
        if (this.bleBluegatt == null)
        {
            return false;
        }
        this.bleBluegatt.discoverServices();
        List<BluetoothGattService> list = this.bleBluegatt.getServices();
        for (BluetoothGattService service : list)
        {
            System.out.println("service:" + service.getUuid().toString());
        }
        if (list.size() <= 0)
        {
            System.out.println("<------->null service");
        }
        this.bleDeviceAddress = address;
        return true;
    }

    public void Disconnect()
    {
        if (this.bleBluegatt != null && this.bleAdapter != null)
        {
            synchronized (this.bleBluegatt)
            {
                this.appDisconnect = true;
                this.bleDeviceAddress = null;
                this.bleBluegatt.disconnect();
            }
        }
    }

    public void Close()
    {
        if (this.bleBluegatt != null)
        {
            synchronized (this.bleBluegatt)
            {
                this.bleBluegatt.close();
                this.bleBluegatt = null;
            }
        }
    }
}
