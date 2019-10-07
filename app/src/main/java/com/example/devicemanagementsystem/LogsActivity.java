package com.example.devicemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.siegmar.fastcsv.writer.CsvAppender;
import de.siegmar.fastcsv.writer.CsvWriter;

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
import com.example.devicemanagementsystem.Utilities.GlobalConstants;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class LogsActivity extends AppCompatActivity {
    private static final String TAG = "LogsActivity";
    @BindView(R.id.logs_recyclerview) RecyclerView logs_recyclerview;
    private int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
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
            // Permission is not granted
            // Request for permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

        } else {
            try {
                File file = new File("Logs.csv");
                CsvWriter csvWriter = new CsvWriter();
                CsvAppender csvAppender = csvWriter.append(file, StandardCharsets.UTF_8);
                // header
                csvAppender.appendLine(GlobalConstants.USER_USERNAME, GlobalConstants.USER_EMAIL, GlobalConstants.COL_DEVICE_NAME
                        , GlobalConstants.COL_DEVICE_BRAND, GlobalConstants.COL_DEVICE_TYPE, GlobalConstants.COL_DEVICE_DEPARTMENT,
                        GlobalConstants.DEVICE_STATUS, GlobalConstants.TIMESTAMP);

                List<Logs> data = LogsAdapter.getLogsList();

                for (Logs logs : data) {
                    csvAppender.appendLine(logs.getUserUsername(), logs.getUserEmail(),
                            logs.getDeviceName(), logs.getDeviceBrand(), logs.getDeviceType(),
                            logs.getDeviceDepartment(), logs.getDeviceStatus(), logs.getTimestamp());
                }
                csvAppender.endLine();

                Uri uri = Uri.parse(file.getAbsolutePath());
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "MyLogs");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); // to notify when download is complete
                request.allowScanningByMediaScanner();// if you want to be available from media players
                DownloadManager manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                manager.enqueue(request);
            } catch (IOException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                Log.d(TAG, "exportClicked: Exception: " + e.getMessage());
                e.printStackTrace();
            }

            Toast.makeText(this, "File saved to downloads", Toast.LENGTH_LONG).show();
        }


    }

}
