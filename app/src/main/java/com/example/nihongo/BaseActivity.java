package com.example.nihongo;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Automatically set up the back icon click listener if it exists
    protected void setupBackBtn(){
        ImageView backIcon = findViewById(R.id.btnBack);
        if (backIcon != null) {
            backIcon.setOnClickListener(v -> onBackPressed());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
