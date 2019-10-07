package com.example.devicemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;

import com.example.devicemanagementsystem.Tasks.FetchDevicesTask;

public class DeviceListActivity extends AppCompatActivity {

    @BindView(R.id.devices_recyclerview) RecyclerView devices_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);
        ButterKnife.bind(this);
        devices_recyclerview.setHasFixedSize(true);
        devices_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        new FetchDevicesTask(this, devices_recyclerview).execute((Void)null);
    }
}
