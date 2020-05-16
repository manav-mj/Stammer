package com.example.speech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DailySpeechActivity extends AppCompatActivity {

    private Button start;
    private AppCompatImageView backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_speech);

        start = findViewById(R.id.button);
        backbtn = findViewById(R.id.back);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DailySpeechActivity.this, AudioRecord.class));
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DailySpeechActivity.this, MainActivity.class));
            }
        });

    }
}
