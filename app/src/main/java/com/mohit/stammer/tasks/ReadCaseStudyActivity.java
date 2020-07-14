package com.mohit.stammer.tasks;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mohit.stammer.R;

public class ReadCaseStudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_case_study);
    }

    public void finish(View view) {
        SharedPreferences sp = getSharedPreferences("stammer", MODE_PRIVATE);
        sp.edit().putBoolean("task4", true).apply();

        finish();
    }
}

