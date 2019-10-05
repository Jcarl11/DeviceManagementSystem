package com.example.devicemanagementsystem.DB;

import com.example.devicemanagementsystem.Models.Device;
import com.example.devicemanagementsystem.Utilities.GlobalConstants;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class DevicesDAO implements IOperations<Device> {

    private static final String TAG = "DevicesDAO";


    @Override
    public Device insert(Device object) {
        ParseObject parseObject = new ParseObject(GlobalConstants.DB_DEVICES);
        parseObject.put(GlobalConstants.COL_DEVICE_NAME, object.getDeviceName());
        parseObject.put(GlobalConstants.COL_DEVICE_BRAND, object.getDeviceBrand());
        parseObject.put(GlobalConstants.COL_DEVICE_TYPE, object.getDeviceType());
        parseObject.put(GlobalConstants.COL_DEVICE_DEPARTMENT, object.getDepartment());

        parseObject.saveInBackground();

        return object;
    }

    @Override
    public int insertAll(List<Device> objectList) {
        for(Device device : objectList) {
            insert(device);
        }
        return objectList.size();
    }
}
