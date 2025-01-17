package com.example.nihongo;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class splash3 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash3);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_nihongo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(splash3.this, login.class));
                overridePendingTransition(0, 0);
                finish();
            }
        };
        Timer opening = new Timer();
        opening.schedule(task, 2000);
    }
}