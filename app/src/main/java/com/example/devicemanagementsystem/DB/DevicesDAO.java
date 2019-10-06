package com.example.devicemanagementsystem.DB;

import android.util.Log;

import com.example.devicemanagementsystem.Models.Device;
import com.example.devicemanagementsystem.Utilities.GlobalConstants;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class DevicesDAO implements IOperations<Device> {

    private static final String TAG = "DevicesDAO";


    @Override
    public Device insert(Device object) {
        Device myDevice = null;
        ParseObject parseObject = new ParseObject(GlobalConstants.DB_DEVICES);
        parseObject.put(GlobalConstants.COL_DEVICE_NAME, object.getDeviceName());
        parseObject.put(GlobalConstants.COL_DEVICE_BRAND, object.getDeviceBrand());
        parseObject.put(GlobalConstants.COL_DEVICE_TYPE, object.getDeviceType());
        parseObject.put(GlobalConstants.COL_DEVICE_DEPARTMENT, object.getDepartment());

        try {
            parseObject.save();
            myDevice = object;
        } catch (ParseException e) {
            Log.d(TAG, "insert: Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return myDevice;
    }

    @Override
    public int insertAll(List<Device> objectList) {
        int successfulOperations = 0;
        for(Device device : objectList) {
            Device myDev = insert(device);
            if(myDev != null) {
                successfulOperations += 1;
            }
        }

        Log.d(TAG, "insertAll: Total Operations: " + objectList.size());
        Log.d(TAG, "insertAll: Failed operations: " + String.valueOf(objectList.size() - successfulOperations));
        return successfulOperations;
    }
}
