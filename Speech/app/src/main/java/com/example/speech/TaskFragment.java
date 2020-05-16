package com.example.speech;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TaskFragment extends Fragment {
    private Button startbtn, stopbtn, playbtn, stopplay;
    private TextView speechTherpy,caseStudy,speechPractice,speechPractice1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tasks, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
         speechPractice = (TextView) getView().findViewById(R.id.speech_practice);
        speechPractice1 = (TextView) getView().findViewById(R.id.speech_practice1);
         speechPractice.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Intent intent = new Intent(getActivity(), DailySpeechActivity.class);
                 startActivity(intent);
             }
         });

        speechPractice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), DailySpeechActivity.class);
                startActivity(intent);
            }
        });
    }

}
