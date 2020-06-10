package com.mohit.stammer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class StartActivity extends AppCompatActivity {

    EditText name, age, city, email, password, conpassword;
    Button sign_up;
    FirebaseAuth Auth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);


        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        city = findViewById(R.id.city);
        sign_up = findViewById(R.id.sign_up);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        conpassword = findViewById(R.id.conpassword);


        Auth = FirebaseAuth.getInstance();


//        if (Auth.getCurrentUser() !=  null)    {
//           // startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            Toast.makeText(this, "You are already logged in", Toast.LENGTH_SHORT).show();
//
//            finish();
//
//        }


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = email.getText().toString().trim();
                String pass = password.getText().toString().trim();


                if (TextUtils.isEmpty(Email)) {

                    email.setError("Mobile number is required");
                    return;

                }

                if (TextUtils.isEmpty(pass)) {

                    password.setError("Password is required");
                    return;

                }

                if (pass.length() < 6) {
                    password.setError("The password is weak");

                    return;
                }


                Auth.createUserWithEmailAndPassword(Email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(StartActivity.this, "User Created Successfully ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                        } else {
                            Toast.makeText(StartActivity.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });


            }
        });


    }

    public void login(View view) {

        Intent intent = new Intent(this, LoginActivity.class);

        startActivity(intent);
    }


}
