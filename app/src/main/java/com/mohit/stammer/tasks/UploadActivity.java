package com.mohit.stammer.tasks;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.mohit.stammer.R;

public class UploadActivity extends AppCompatActivity {

    private MaterialButton finishTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        finishTaskButton = findViewById(R.id.finishTaskButton);
    }

    public void uploadClicked(View view) {
        finishTaskButton.setEnabled(true);
    }

    public void finishTask(View view) {
        SharedPreferences sp = getSharedPreferences("stammer", MODE_PRIVATE);
        sp.edit().putBoolean("task8", true).apply();

        finish();
    }

}