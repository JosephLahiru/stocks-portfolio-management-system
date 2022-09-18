package com.example.stocksportfoliomanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    EditText etUserName, etEmail, etPassword, etPasswordConfirm;
    FirebaseAuth mAuth;
    Button signInButton;
    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        etUserName = findViewById(R.id.editTextUserName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.editTextPassword);
        etPasswordConfirm = findViewById(R.id.editTextPasswordConfirm);
        signInButton = (Button) findViewById(R.id.signInButton);
        loginText = (TextView) findViewById(R.id.loginText);

        mAuth = FirebaseAuth.getInstance();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, LoginActivity.class));
            }
        });
    }

    private void createUser(){
        String username = etUserName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String comPassword = etPasswordConfirm.getText().toString().trim();
        
        if(TextUtils.isEmpty(email)){
            etEmail.setError("Email cannot be empty.");
            etEmail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            etPassword.setError("Password cannot be empty.");
            etPassword.requestFocus();
        }else if(TextUtils.isEmpty(comPassword)){
            etPasswordConfirm.setError("Password confirm cannot be empty.");
            etPasswordConfirm.requestFocus();
        }else if(!password.equals(comPassword)){
            Toast.makeText(this, "Password and Confirm Password not matching.!!!", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(SignInActivity.this, "User Created Successfully.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignInActivity.this, LoginActivity.class));
                    }else{
                        Toast.makeText(SignInActivity.this, "User Creation Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}