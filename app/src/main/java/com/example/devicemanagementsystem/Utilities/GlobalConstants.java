package com.example.devicemanagementsystem.Utilities;

import com.parse.ParseUser;

public class GlobalConstants {
    public static final String SCAN_RESULT = "SCAN_RESULT";
    public static final int SCANACTIVITY_REQUESTCODE = 1;
    public static final String USER_EMAIL = "USER_EMAIL";
    public static final String USER_USERNAME = "USER_USERNAME";
    public static final String DEVICE_NAME = "DEVICE_NAME";

    public static final String DEVICE_BRAND = "DEVICE_BRAND";
    public static final String DEVICE_TYPE = "DEVICE_TYPE";
    public static final String TIMESTAMP = "TIMESTAMP";
    public static final String DEVICE_STATUS = "DEVICE_STATUS";
    public static final String DEVICE_DEPARTMENT = "DEVICE_DEPARTMENT";

    //CLOUD DATABASE TABLE NAMES
    public static final String DB_DEVICES = "Devices";
    public static final String DB_DEVICE_TYPE = "DeviceType";
    public static final String DB_DEPARTMENT = "Department";
    public static final String DB_LOGS = "Logs";

    //COLUMN NAMES (DEVICES)
    public static final String COL_DEVICE_NAME = "DEVICE_NAME";
    public static final String COL_DEVICE_BRAND = "DEVICE_BRAND";
    public static final String COL_DEVICE_TYPE = "DEVICE_TYPE";
    public static final String COL_DEVICE_DEPARTMENT = "DEVICE_DEPARTMENT";
    public static final String[] departmentsList = {"IT", "HR", "ACCOUNTING", "MANAGEMENT"};
    public static final String[] deviceTypes = {"COMPUTER", "LAPTOP", "MOUSE", "KEYBOARD", "MONITOR"};

    public static final String LOGIN_SUCCESS = "LOGIN_SUCCESS";
    public static final String LOGIN_FAILED = "LOGIN_FAILED";


    public static final String DEVICE_IN = "IN";
    public static final String DEVICE_OUT = "OUT";

    public static final String PLDT_HOME = "https://m.pldthome.com";
    public static final String PLDT_ABOUTUS = "http://pldt.com/about-us";


    public static final String LOGGEDUSER_USERNAME = ParseUser.getCurrentUser().getUsername();
    public static final String LOGGEDUSER_EMAIL = ParseUser.getCurrentUser().getEmail();

}
