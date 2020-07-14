package com.mohit.stammer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.mohit.stammer.tasks.CaseStudyActivity;
import com.mohit.stammer.tasks.MeditationTaskActivity;
import com.mohit.stammer.tasks.ReadTaskActivity;
import com.mohit.stammer.tasks.SpeechTherapyActivity;
import com.mohit.stammer.tasks.SpreadAwarenessActivity;
import com.mohit.stammer.tasks.StrangerTaskActivity;
import com.mohit.stammer.tasks.VideoTaskActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    final static String[] tasks = {"task1", "task2", "task3", "task4", "task5", "task6", "task7", "task8"};
    final static int[] tickIds = {R.id.imageView3, R.id.imageView13, R.id.imageView31, R.id.imageView113, R.id.imageView1113, R.id.imageView311, R.id.imageView11113, R.id.imageView111113};
    ArrayList<ImageView> tickViews;

    TextView taskProgressView;
    TextView totalPointsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tickViews = new ArrayList<>();
        for (int id : tickIds) {
            tickViews.add((ImageView) findViewById(id));
        }

        taskProgressView = findViewById(R.id.task_completed);
        totalPointsView = findViewById(R.id.task_points);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences("stammer", MODE_PRIVATE);
        int progress = 0;
        for (int i = 0; i < tasks.length; i++) {
            if (sp.getBoolean(tasks[i], false)) {
                tickViews.get(i).setImageResource(R.drawable.ic_tick);
                progress++;
            } else {
                tickViews.get(i).setImageResource(R.drawable.dashboard_ring);
            }
        }

        taskProgressView.setText((int) (((float) progress / (float) tasks.length) * 100) + "% Tasks Completed");
        DecimalFormat form = new DecimalFormat("00");
        totalPointsView.setText(form.format(progress * 3));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        getSharedPreferences("stammer", MODE_PRIVATE).edit().remove("user_id").apply();
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
    }

    public void watch_video(View view) {
        Intent intent = new Intent(this, VideoTaskActivity.class);
        startActivity(intent);
    }

    public void speech_therapy(View view) {
        Intent intent = new Intent(this, SpeechTherapyActivity.class);
        startActivity(intent);
    }

    public void readingTask(View view) {
        Intent intent = new Intent(this, ReadTaskActivity.class);
        startActivity(intent);
    }

    public void caseStudy(View view) {
        Intent intent = new Intent(this, CaseStudyActivity.class);
        startActivity(intent);
    }

    public void spreadAwareness(View view) {
        Intent intent = new Intent(this, SpreadAwarenessActivity.class);
        startActivity(intent);
    }

    public void meditationTask(View view) {
        Intent intent = new Intent(this, MeditationTaskActivity.class);
        startActivity(intent);
    }

    public void strangerTask(View view) {
        Intent intent = new Intent(this, StrangerTaskActivity.class);
        startActivity(intent);
    }
}

