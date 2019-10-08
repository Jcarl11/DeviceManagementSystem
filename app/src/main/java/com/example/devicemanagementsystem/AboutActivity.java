package com.example.devicemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.devicemanagementsystem.Utilities.GlobalConstants;

public class AboutActivity extends AppCompatActivity {
    @BindView(R.id.aboutus_logo) ImageView aboutus_logo;

    @BindView(R.id.aboutus_link) TextView aboutus_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        aboutus_link.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @OnClick(R.id.aboutus_logo)
    void logoClicked() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(GlobalConstants.PLDT_HOME));
        startActivity(browserIntent);
    }

    @OnClick(R.id.aboutus_link)
    void linkClicked() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(GlobalConstants.PLDT_ABOUTUS));
        startActivity(browserIntent);
    }
}
