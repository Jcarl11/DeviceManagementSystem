package com.example.devicemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.devicemanagementsystem.Utilities.GlobalConstants;
import com.example.devicemanagementsystem.Utilities.JSONUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ScanOperationActivity extends AppCompatActivity {

    private String SCAN_RESULT = null;
    private JSONUtils jsonUtils = new JSONUtils();
    @BindView(R.id.scanoperation_deviceid) TextView scanoperation_deviceid;
    @BindView(R.id.scanoperation_devicename) TextView scanoperation_devicename;
    @BindView(R.id.scanoperation_devicebrand) TextView scanoperation_devicebrand;
    @BindView(R.id.scanoperation_devicedepartment) TextView scanoperation_devicedepartment;

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
        scanoperation_devicedepartment.setText(result.get("DEVICE_DEPARTMENT"));
    }




}
