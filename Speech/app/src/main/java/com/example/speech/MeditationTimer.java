package com.example.speech;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MeditationTimer extends AppCompatActivity {


    private Chronometer chronometer;
    private long pauseOffSet;
    private boolean running;
    private int timeLimit = 1800;  // in seconds
    private int timeCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        //getSupportActionBar().setTitle("Task Description");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        chronometer = (Chronometer) findViewById(R.id.chronometer);

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (running) {
                    timeCounter++;
                    if(timeCounter == timeLimit){
                        TextView btn = (TextView)findViewById(R.id.startTimer);
                        btn.setText("Finish Task");
                        stopChronometer();
                    }
                }
            }
        });
    }
    public void startChronometer(View v){
        if(!running && timeCounter != timeLimit){
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffSet);
            chronometer.start();
            running = true;
        }
        if(timeCounter == timeLimit){
            Intent intent = new Intent(this, MainActivity.class); // change the name of previous page here.
            startActivity(intent);
        }
    }
    public void stopChronometer(){
        if (running){
            chronometer.stop();
            pauseOffSet = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

}
