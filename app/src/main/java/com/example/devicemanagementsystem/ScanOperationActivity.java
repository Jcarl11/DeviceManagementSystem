package com.example.devicemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.devicemanagementsystem.Utilities.GlobalConstants;

public class ScanOperationActivity extends AppCompatActivity {

    private String SCAN_RESULT = null;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_operation);
        result = (TextView) findViewById(R.id.textview_result);

        Intent intent = getIntent();
        SCAN_RESULT = intent.getStringExtra(GlobalConstants.SCAN_RESULT);
        result.setText(SCAN_RESULT);
    }


}
