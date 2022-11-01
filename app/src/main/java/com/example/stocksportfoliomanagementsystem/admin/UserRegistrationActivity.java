package com.example.stocksportfoliomanagementsystem.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.stocksportfoliomanagementsystem.R;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRegistrationActivity extends AppCompatActivity {

    private static final String URL = "jdbc:mysql://152.70.158.151:3306/spms";
    private static final String USER = "root";
    private static final String PASSWORD = "amres";

    EditText etUserName, etEmail, etPassword, etPasswordConfirm;
    Button signInButton, backBtn;
    ProgressBar progressBar;

    String userEmail;

    String username, email, password, comPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        userEmail = getIntent().getStringExtra("userEmail");

        backBtn = (Button) findViewById(R.id.backButtonAdmin);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);

        etUserName = findViewById(R.id.editTextUserNameAdmin);
        etEmail = findViewById(R.id.etEmailAdmin);
        etPassword = findViewById(R.id.editTextPasswordAdmin);
        etPasswordConfirm = findViewById(R.id.editTextPasswordConfirmAdmin);
        signInButton = (Button) findViewById(R.id.signInButtonAdmin);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserRegistrationActivity.this, AdministrationActivity.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
                finish();
            }
        });
    }

    private void createUser(){
        username = etUserName.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        comPassword = etPasswordConfirm.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            etEmail.setError("Email cannot be empty.");
            etEmail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            etPassword.setError("Password cannot be empty.");
            etPassword.requestFocus();
        }else if(TextUtils.isEmpty(username)){
            etUserName.setError("Username cannot be empty.");
            etUserName.requestFocus();
        }else if(TextUtils.isEmpty(comPassword)){
            etPasswordConfirm.setError("Password confirm cannot be empty.");
            etPasswordConfirm.requestFocus();
        }else if(!password.equals(comPassword)){
            Toast.makeText(this, "Password and Confirm Password not matching.!!!", Toast.LENGTH_SHORT).show();
        }else{
            new InfoAsyncTask().execute();
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... voids) {
            List<String> districts = new ArrayList<>();
            try {
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

                String sql = "INSERT INTO user(user_name, user_email, user_password) VALUES('" + username + "', '" + email + "', '" + password + "');";
                Statement st1 = connection.createStatement();

                if (!st1.execute(sql)) {
                    System.out.println("User registered successfully");

                    runOnUiThread(new Runnable() {
                        public void run() {
                            etUserName.getText().clear();
                            etEmail.getText().clear();
                            etUserName.getText().clear();
                            etPassword.getText().clear();
                            etPasswordConfirm.getText().clear();

                            progressBar.setVisibility(View.INVISIBLE);

                            final Toast toast = Toast.makeText(UserRegistrationActivity.this, "User Registered Successfully.", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });

                } else {
                    System.out.println("User registration failed.");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(UserRegistrationActivity.this, "User Registration Failed.", Toast.LENGTH_SHORT);
                            toast.show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading information", e);
            }

            return districts;
        }

    }
}