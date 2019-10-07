package com.example.devicemanagementsystem.DB;

import android.util.Log;

import com.example.devicemanagementsystem.Models.Logs;
import com.example.devicemanagementsystem.Utilities.DateUtils;
import com.example.devicemanagementsystem.Utilities.GlobalConstants;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
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

    @Override
    public List<Logs> getAll() {
        Log.d(TAG, "getAll: Started..");
        List<Logs> logsList = new ArrayList<>();
        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery(GlobalConstants.DB_LOGS);
        try {
            List<ParseObject> parseObjectList =  parseQuery.find();
            Log.d(TAG, "getAll: Retrieved finished");
            for(ParseObject parseObject : parseObjectList) {
                Logs logs = new Logs();
                logs.setObjectId(parseObject.getObjectId());
                logs.setUserUsername(parseObject.getString(GlobalConstants.USER_USERNAME));
                logs.setUserEmail(parseObject.getString(GlobalConstants.USER_EMAIL));
                logs.setDeviceName(parseObject.getString(GlobalConstants.COL_DEVICE_NAME));
                logs.setDeviceBrand(parseObject.getString(GlobalConstants.COL_DEVICE_BRAND));
                logs.setDeviceType(parseObject.getString(GlobalConstants.COL_DEVICE_TYPE));
                logs.setDeviceDepartment(parseObject.getString(GlobalConstants.COL_DEVICE_DEPARTMENT));
                logs.setDeviceStatus(parseObject.getString(GlobalConstants.DEVICE_STATUS));
                logs.setTimestamp(dateUtils.toISO8601String(parseObject.getDate(GlobalConstants.TIMESTAMP)));
                logsList.add(logs);
            }
        } catch (ParseException e) {
            Log.d(TAG, "getAll: Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return logsList;
    }
}
