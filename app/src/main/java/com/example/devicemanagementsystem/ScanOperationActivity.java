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
import com.example.devicemanagementsystem.Models.Logs;
import com.example.devicemanagementsystem.Tasks.LogsTask;
import com.example.devicemanagementsystem.Tasks.UploadTask;
import com.example.devicemanagementsystem.Utilities.DateUtils;
import com.example.devicemanagementsystem.Utilities.GlobalConstants;
import com.example.devicemanagementsystem.Utilities.JSONUtils;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;

public class ScanOperationActivity extends AppCompatActivity {

    private String SCAN_RESULT = null;
    private JSONUtils jsonUtils = new JSONUtils();
    private IOperations<Device> devicesDAO = new DevicesDAO();
    private Logs logs = new Logs();
    private DateUtils dateUtils = new DateUtils();
    @BindView(R.id.scanoperation_devicename) TextInputLayout scanoperation_devicename;
    @BindView(R.id.scanoperation_devicebrand) TextInputLayout scanoperation_devicebrand;
    @BindView(R.id.scanoperation_devicedepartment) TextInputLayout scanoperation_devicedepartment;
    @BindView(R.id.scanoperation_devicetype) TextInputLayout scanoperation_devicetype;

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

        scanoperation_devicename.getEditText().setText(result.get(GlobalConstants.DEVICE_NAME));
        scanoperation_devicebrand.getEditText().setText(result.get(GlobalConstants.DEVICE_BRAND));
        scanoperation_devicetype.getEditText().setText(result.get(GlobalConstants.DEVICE_TYPE));
        scanoperation_devicedepartment.getEditText().setText(result.get(GlobalConstants.DEVICE_DEPARTMENT));

        logs.setUserUsername(GlobalConstants.LOGGEDUSER_USERNAME);
        logs.setUserEmail(GlobalConstants.LOGGEDUSER_EMAIL);
        logs.setDeviceName(result.get(GlobalConstants.DEVICE_NAME));
        logs.setDeviceBrand(result.get(GlobalConstants.DEVICE_BRAND));
        logs.setDeviceType(result.get(GlobalConstants.DEVICE_TYPE));
        logs.setDeviceDepartment(result.get(GlobalConstants.DEVICE_DEPARTMENT));

    }

    @OnClick(R.id.scanoperation_add)
    void addClicked() {
        Device device = new Device();
        device.setDeviceName(scanoperation_devicename.getEditText().getText().toString());
        device.setDeviceBrand(scanoperation_devicebrand.getEditText().getText().toString());
        device.setDeviceType(scanoperation_devicename.getEditText().getText().toString());
        device.setDeviceType(scanoperation_devicetype.getEditText().getText().toString());
        device.setDepartment(scanoperation_devicedepartment.getEditText().getText().toString());
        new UploadTask(this, device).execute((Void) null);
    }

    @OnClick(R.id.scanoperation_itemin)
    void iteminClicked() {
        logs.setDeviceStatus(GlobalConstants.DEVICE_IN);
        logs.setTimestamp(dateUtils.toISO8601String(new Date()));
        new LogsTask(this, logs).execute((Void) null);
    }
    @OnClick(R.id.scanoperation_itemout)
    void itemoutClicked() {
        logs.setDeviceStatus(GlobalConstants.DEVICE_OUT);
        logs.setTimestamp(dateUtils.toISO8601String(new Date()));
        new LogsTask(this, logs).execute((Void) null);
    }
}
