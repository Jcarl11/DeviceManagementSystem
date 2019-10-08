package com.example.devicemanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.Manifest;
import android.app.DownloadManager;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.devicemanagementsystem.Adapters.LogsAdapter;
import com.example.devicemanagementsystem.Models.Logs;
import com.example.devicemanagementsystem.Tasks.FetchLogsTask;
import com.example.devicemanagementsystem.Utilities.DateUtils;
import com.example.devicemanagementsystem.Utilities.GlobalConstants;
import com.example.devicemanagementsystem.Utilities.Loading;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LogsActivity extends AppCompatActivity {
    private static final String TAG = "LogsActivity";
    @BindView(R.id.logs_recyclerview) RecyclerView logs_recyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        ButterKnife.bind(this);
        logs_recyclerview.setHasFixedSize(true);
        logs_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        new FetchLogsTask(this, logs_recyclerview).execute((Void)null);
    }

    @OnClick(R.id.logs_export)
    void exportClicked() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        } else {
            exportLogs();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    exportLogs();
                } else {
                    Loading.getInstance()
                            .showAlertBox("You denied permission",
                                    "Permission to write to external storage " +
                                            "is needed for this operation", LogsActivity.this);
                }
                break;
        }
    }

    private void exportLogs() {
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Logs.csv");
            CSVWriter csvWriter;
            if (file.exists() && !file.isDirectory()) {
                Log.d(TAG, "exportClicked: File Exist");
                csvWriter = new CSVWriter(new FileWriter(file, false));
            } else {
                Log.d(TAG, "exportClicked: File doesn't exist");
                csvWriter = new CSVWriter(new FileWriter(file));
            }

            List<Logs> data = LogsAdapter.getLogsList();
            Log.d(TAG, "exportClicked: Data size: " + data.size());
            List<String[]> csvContent = new ArrayList<>();
            csvContent.add(0, new String[]{GlobalConstants.USER_USERNAME, GlobalConstants.USER_EMAIL,
                    GlobalConstants.COL_DEVICE_NAME, GlobalConstants.COL_DEVICE_BRAND,
                    GlobalConstants.COL_DEVICE_TYPE, GlobalConstants.COL_DEVICE_DEPARTMENT,
                    GlobalConstants.DEVICE_STATUS, GlobalConstants.TIMESTAMP});

            Log.d(TAG, "exportLogs: Timestamp sample: " + data.get(4).getTimestamp());

            for (Logs logs : data) {
                csvContent.add(new String[]{logs.getUserUsername(), logs.getUserEmail(),
                        logs.getDeviceName(), logs.getDeviceBrand(),
                        logs.getDeviceType(), logs.getDeviceDepartment(), logs.getDeviceStatus(),
                        logs.getTimestamp()});
            }
            csvWriter.writeAll(csvContent);
            csvWriter.close();
            Loading.getInstance().showAlertBox("Export finished",
                    "File is located at:\n" + file.getAbsolutePath(),
                    LogsActivity.this);
        } catch (Exception ex) {
            Loading.getInstance().showAlertBox("Error", ex.getMessage(), this);
            Log.d(TAG, "exportClicked: Exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
