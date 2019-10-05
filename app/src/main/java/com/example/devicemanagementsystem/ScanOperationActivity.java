package com.example.devicemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devicemanagementsystem.DB.DevicesDAO;
import com.example.devicemanagementsystem.DB.IOperations;
import com.example.devicemanagementsystem.Models.Device;
import com.example.devicemanagementsystem.Utilities.GlobalConstants;
import com.example.devicemanagementsystem.Utilities.JSONUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ScanOperationActivity extends AppCompatActivity {

    private String SCAN_RESULT = null;
    private JSONUtils jsonUtils = new JSONUtils();
    private IOperations<Device> devicesDAO = new DevicesDAO();
    @BindView(R.id.scanoperation_deviceid) TextView scanoperation_deviceid;
    @BindView(R.id.scanoperation_devicename) TextView scanoperation_devicename;
    @BindView(R.id.scanoperation_devicebrand) TextView scanoperation_devicebrand;
    @BindView(R.id.scanoperation_devicedepartment) TextView scanoperation_devicedepartment;
    @BindView(R.id.scanoperation_devicetype) TextView scanoperation_devicetype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_operation);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        SCAN_RESULT = intent.getStringExtra(GlobalConstants.SCAN_RESULT);
        HashMap<String, String> result = new HashMap<>();
        try {
            result = jsonUtils.parseData(new JSONObject(SCAN_RESULT));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        scanoperation_deviceid.setText(result.get("DEVICE_ID"));
        scanoperation_devicename.setText(result.get("DEVICE_NAME"));
        scanoperation_devicebrand.setText(result.get("DEVICE_BRAND"));
        scanoperation_devicetype.setText(result.get("DEVICE_TYPE"));
        scanoperation_devicedepartment.setText(result.get("DEVICE_DEPARTMENT"));
    }

    @OnClick(R.id.scanoperation_add)
    void addClicked() {
        Device device = new Device();
        device.setDeviceName(scanoperation_devicename.getText().toString());
        device.setDeviceBrand(scanoperation_devicebrand.getText().toString());
        device.setDeviceType(scanoperation_devicename.getText().toString());
        device.setDeviceType(scanoperation_devicetype.getText().toString());
        device.setDepartment(scanoperation_devicedepartment.getText().toString());

        devicesDAO.insert(device);
        Toast.makeText(this, "Uploading...", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.scanoperation_itemin)
    void iteminClicked() {

    }
    @OnClick(R.id.scanoperation_itemout)
    void itemoutClicked() {

    }



}
