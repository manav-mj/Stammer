package com.mohit.stammer.tasks;

import android.Manifest;
import android.content.Intent;
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

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.mohit.stammer.R;

import java.text.DecimalFormat;

public class ReadContentActivity extends AppCompatActivity {

    boolean isRecording = false;
    long recordingTimeInSeconds = 0;
    CardView recordingButton;
    ImageView recordingIcon;
    Handler recordingThreadHandler;
    TextView recordingTimeView;
    View bottomContainer;
    View recordingUIContainer;
    ProgressBar progressBar;
    DecimalFormat form = new DecimalFormat("00");

    Runnable recordingUpdater = new Runnable() {
        @Override
        public void run() {
            if (isRecording) {

                recordingTimeInSeconds++;

                if (recordingTimeInSeconds >= 15 * 60) {
                    stopRecording();
                } else {
                    recordingTimeView.setText(form.format(recordingTimeInSeconds / 60) + ":" + form.format(recordingTimeInSeconds % 60));
                    progressBar.setProgress((int) (((float) recordingTimeInSeconds / (float) (15 * 60)) * 100));
                    recordingThreadHandler.postDelayed(recordingUpdater, 1000);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_content);

        recordingButton = findViewById(R.id.recordingButton);
        recordingIcon = findViewById(R.id.recordingIcon);
        recordingTimeView = findViewById(R.id.time);
        bottomContainer = findViewById(R.id.bottom_container);
        recordingUIContainer = findViewById(R.id.recording_ui_container);
        progressBar = findViewById(R.id.progress);

        recordingThreadHandler = new Handler();

        progressBar.setMax(15 * 60);
    }

    public void recordingButtonClicked(View view) {
        if (isRecording) {
            stopRecording();
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

    private void stopRecording() {
        isRecording = false;
        recordingThreadHandler.removeCallbacks(recordingUpdater);
        Animation fadeOut = new AlphaAnimation(1f, 0f);
        fadeOut.setDuration(150);
        recordingUIContainer.startAnimation(fadeOut);
        recordingUIContainer.setVisibility(View.INVISIBLE);
    }

    private void startRecording() {
        isRecording = true;
        recordingButton.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
        recordingIcon.setImageResource(R.drawable.pause);
        recordingUpdater.run();
    }

    public void analyzeTask(View view) {
        Intent intent = new Intent(this, PictureAnalyzeActivity.class);
        intent.putExtra("task", "task3");
        startActivity(intent);
        finish();
    }
}

