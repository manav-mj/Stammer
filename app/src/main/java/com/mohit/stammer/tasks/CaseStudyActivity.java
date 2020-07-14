package com.mohit.stammer.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mohit.stammer.R;

public class CaseStudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_study);
    }

    public void startTask(View view) {
        Intent intent = new Intent(this, ReadCaseStudyActivity.class);
        startActivity(intent);
        finish();
    }
}

