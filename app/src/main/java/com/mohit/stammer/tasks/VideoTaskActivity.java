package com.mohit.stammer.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mohit.stammer.R;

public class VideoTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_task);
    }

    public void watch_video(View view) {
        Intent intent = new Intent(this, VideoWatchActivity.class);
        startActivity(intent);
        finish();
    }
}

