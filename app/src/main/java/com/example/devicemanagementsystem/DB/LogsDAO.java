package com.example.devicemanagementsystem.DB;

import android.util.Log;

import com.example.devicemanagementsystem.Models.Logs;
import com.example.devicemanagementsystem.Utilities.DateUtils;
import com.example.devicemanagementsystem.Utilities.GlobalConstants;
import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.List;

public class LogsDAO implements IOperations<Logs>{
    private static final String TAG = "LogsDAO";
    DateUtils dateUtils = new DateUtils();

    @Override
    public Logs insert(Logs object) {
        Logs logs = null;
        ParseObject parseObject = new ParseObject(GlobalConstants.DB_LOGS);
        parseObject.put(GlobalConstants.USER_EMAIL, object.getUserEmail());
        parseObject.put(GlobalConstants.USER_USERNAME, object.getUserUsername());
        parseObject.put(GlobalConstants.DEVICE_NAME, object.getDeviceName());
        parseObject.put(GlobalConstants.DEVICE_BRAND, object.getDeviceBrand());
        parseObject.put(GlobalConstants.DEVICE_DEPARTMENT, object.getDeviceDepartment());
        parseObject.put(GlobalConstants.DEVICE_TYPE, object.getDeviceType());
        parseObject.put(GlobalConstants.DEVICE_STATUS, object.getDeviceStatus());
        parseObject.put(GlobalConstants.TIMESTAMP, dateUtils.toISO8601Date(object.getTimestamp()));

        try {
            parseObject.save();
            logs = object;
        } catch (ParseException e) {
            Log.d(TAG, "insert: Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return logs;
    }

    @Override
    public int insertAll(List<Logs> objectList) {
        int successfulOperations = 0;
        for(Logs logs : objectList) {
            Logs data = insert(logs);
            if(data != null) {
                successfulOperations += 1;
            }
        }

        Log.d(TAG, "insertAll: Total Operations: " + objectList.size());
        Log.d(TAG, "insertAll: Failed operations: " + String.valueOf(objectList.size() - successfulOperations));
        return successfulOperations;
    }
}
