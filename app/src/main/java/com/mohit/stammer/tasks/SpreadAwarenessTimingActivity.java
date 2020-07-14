package com.mohit.stammer.tasks;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.button.MaterialButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.mohit.stammer.R;

import java.text.DecimalFormat;

public class SpreadAwarenessTimingActivity extends AppCompatActivity {

    boolean isRecording = false;
    long recordingTimeInSeconds = 0;
    MaterialButton recordingButton;
    Handler recordingThreadHandler;
    TextView recordingTimeView;
    View pauseButtonLayout;
    MaterialButton pauseButton;
    DecimalFormat form = new DecimalFormat("00");
    private boolean animateFlag = true;

    Runnable recordingUpdater = new Runnable() {
        @Override
        public void run() {
            if (isRecording) {

                recordingTimeInSeconds++;

                if (recordingTimeInSeconds >= 15 * 60) {
                    pauseRecording();
                } else {
                    recordingTimeView.setText(form.format(recordingTimeInSeconds / 60) + ":" + form.format(recordingTimeInSeconds % 60));
                    recordingThreadHandler.postDelayed(recordingUpdater, 1000);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spread_awareness_timing);

        recordingButton = findViewById(R.id.recordingButton);
        recordingTimeView = findViewById(R.id.time);
        pauseButtonLayout = findViewById(R.id.pauseButtonLayout);
        pauseButton = findViewById(R.id.pauseButton);

        recordingThreadHandler = new Handler();
    }

    public void recordingButtonClicked(View view) {
        if (isRecording) {
            pauseRecording();
        } else {
            Dexter.withContext(this)
                    .withPermission(Manifest.permission.RECORD_AUDIO)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {
                            startRecording();
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {/* Do Nothing */}

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
                    }).check();
        }
    }

    private void pauseRecording() {
        isRecording = false;
        recordingThreadHandler.removeCallbacks(recordingUpdater);
        pauseButton.setText("Resume Recording");
    }

    private void startRecording() {
        isRecording = true;

        if (animateFlag) {
            animateFlag = false;

            Animation fadeOut = new AlphaAnimation(1f, 0f);
            fadeOut.setDuration(100);
            recordingButton.startAnimation(fadeOut);
            recordingButton.setVisibility(View.GONE);

            pauseButtonLayout.setVisibility(View.VISIBLE);
        }

        pauseButton.setText("Pause Recording");
        recordingUpdater.run();
    }

    public void finishTask(View view) {
        pauseRecording();

        SharedPreferences sp = getSharedPreferences("stammer", MODE_PRIVATE);
        sp.edit().putBoolean("task5", true).apply();

        finish();
    }
}

