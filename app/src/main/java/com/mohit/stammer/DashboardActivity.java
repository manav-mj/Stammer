package com.mohit.stammer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.logout_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void logout(View view){

        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this ,MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Logged out successfully" , Toast.LENGTH_SHORT).show();
    }

    public void speech_therapy(View view){

        Intent intent = new Intent(this , speech_therapy.class);
        startActivity(intent);

    }

    public void caseStudy(View view){
        Intent intent = new Intent(this ,CaseStudy.class);
        startActivity(intent);
    }

    public void spreadAwareness(View view){
        Intent intent = new Intent(this ,SpreadAwareness.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }
}

