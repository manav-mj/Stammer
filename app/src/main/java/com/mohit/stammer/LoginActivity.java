package com.mohit.stammer;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {



    EditText Email, Password;
    Button login;
    ProgressBar progressBar;
    FirebaseAuth Auth = FirebaseAuth.getInstance();

    public void signIn (View view){
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        login = findViewById(R.id.login);
        progressBar = findViewById(R.id.progressBar);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = Email.getText().toString().trim();
                final String pass = Password.getText().toString().trim();


                if (TextUtils.isEmpty(email)) {

                    Email.setError("Email is required");
                    return;

                }

                if (TextUtils.isEmpty(pass)) {

                    Password.setError("Password is required");
                    return;

                }


                   progressBar.setVisibility(View.VISIBLE);

                Auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {



                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        try {if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
                            progressBar.setVisibility(View.GONE);

                            Email.setText(null);
                            Password.setText(null);

                        }else {
                            Toast.makeText(LoginActivity.this, "Error "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }

                        }
                        catch(NullPointerException e) {
                            Toast.makeText(LoginActivity.this, "Null pointer Exception", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });




    }
}
