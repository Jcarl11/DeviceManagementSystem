package com.example.devicemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.devicemanagementsystem.Tasks.FetchDevicesTask;
import com.example.devicemanagementsystem.Tasks.LogoutTask;
import com.example.devicemanagementsystem.Utilities.GlobalConstants;
import com.parse.Parse;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.pldt_logo) ImageView pldt_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeParse();
        ParseUser user = ParseUser.getCurrentUser();
        if(user == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        
    }

    @OnClick(R.id.cardview_scanner)
    void scannerCardClicked() {
        startActivity(new Intent(this, ScanActivity.class));
    }

    @OnClick(R.id.cardview_logout)
    void logoutClicked() {
        new LogoutTask(this).execute((Void)null);
    }

    @OnClick(R.id.cardview_generate)
    void generateClicked() {
        startActivity(new Intent(this, GenerateActivity.class));
    }

    @OnClick(R.id.cardview_devicelist)
    void deviceListClicked() {
        startActivity(new Intent(this, DeviceListActivity.class));
    }

    @OnClick(R.id.cardview_logs)
    void logsClicked() {
        startActivity(new Intent(this, LogsActivity.class));
    }

    @OnClick(R.id.cardview_aboutus)
    void aboutusClicked() {
        startActivity(new Intent(this, AboutActivity.class));
    }

    @OnClick(R.id.pldt_logo)
    void pldtlogoClicked() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(GlobalConstants.PLDT_HOME));
        startActivity(browserIntent);
    }
    private void initializeParse() {
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );
    }
}
