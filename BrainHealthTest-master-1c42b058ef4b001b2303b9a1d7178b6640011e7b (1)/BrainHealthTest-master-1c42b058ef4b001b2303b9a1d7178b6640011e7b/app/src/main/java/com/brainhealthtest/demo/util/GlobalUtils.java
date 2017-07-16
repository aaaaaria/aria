package com.brainhealthtest.demo.util;

import java.util.UUID;

public class GlobalUtils
{
    public static String DEVICE_CONNECTED = "ble.android.connected";
    public static String DEVICE_DISCONNECTED = "ble.android.disconnected";
    public static String DEVICE_DISCOVERED = "ble.android.discoverd";
    public static String DEVICE_NOTIFICATION = "ble.android.notification";
    public static String DEVICE_SCANED = "ble.android.scan";
    public static String DEVICE_SELECTED = "device";
    public static String DEVICE_VERSION = "version";
    public static String EXTRA_DEVICE = "device";
    public static String EXTRA_STATUS = "status";
    public static String EXTRA_VALUE = "value";
    public static String FIND_DEVICE_ACTION = "ble.android.find";
    public static UUID NOFITICATION_UUID = null;
    public static String READ_CHARACTERISTER_ACTION = "ble.android.read";
    public static UUID SERVICE_UUID = UUID.fromString("0000FE03-0000-1000-8000-00805f9b34fb");
    public static UUID SERVICE_UUID_ONE = null;
    public static UUID SUPPORT_ONE_UUID = UUID.fromString("0000ffc1-0000-1000-8000-00805f9b34fb");
    public static UUID SUPPORT_TWO_UUID = UUID.fromString("0000ffc2-0000-1000-8000-00805f9b34fb");
    public static float dx = 0.0f;
    public static float dy = 0.0f;
    public static int scanDelay = 2000;
}
