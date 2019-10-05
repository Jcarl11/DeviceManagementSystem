package com.example.devicemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.EditText;

import com.example.devicemanagementsystem.Utilities.GlobalConstants;
import com.example.devicemanagementsystem.Utilities.JSONUtils;

import java.util.HashMap;

public class GenerateActivity extends AppCompatActivity {

    @BindView(R.id.generate_text) EditText generate_text;
    JSONUtils jsonUtils = new JSONUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);
        ButterKnife.bind(this);
        HashMap<String, String> mockData = new HashMap<>();
        mockData.put(GlobalConstants.DEVICE_ID, "CWSC535");
        mockData.put(GlobalConstants.DEVICE_NAME, "ASUS XL2411");
        mockData.put(GlobalConstants.DEVICE_BRAND, "ASUS");
        mockData.put(GlobalConstants.DEVICE_TYPE, "OPBNKG21");
        mockData.put(GlobalConstants.DEVICE_IN, "12:23:45 05-12-2019");
        mockData.put(GlobalConstants.DEVICE_OUT, "09:15:44 05-12-2019");
        mockData.put(GlobalConstants.DEVICE_DEPARTMENT, "2V5TEEE");

        generate_text.setText(jsonUtils.generateJSON(mockData));

    }
}
