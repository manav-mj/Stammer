package com.mohit.stammer.tasks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mohit.stammer.R;

public class SpeechTherapyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_therapy);
    }

    public void showPicture(View view) {
        startActivity(new Intent(this, PictureWatchActivity.class));
    }


}