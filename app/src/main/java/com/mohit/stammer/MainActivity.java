package com.mohit.stammer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    public void login(View view){

        Intent intent = new Intent(this, LoginActivity.class);

        startActivity(intent);
    }

    public void signIn(View view){
        Intent intent = new Intent(this, StartActivity.class);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
