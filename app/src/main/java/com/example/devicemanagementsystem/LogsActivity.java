package com.example.devicemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;

import com.example.devicemanagementsystem.Tasks.FetchLogsTask;

public class LogsActivity extends AppCompatActivity {

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


}
