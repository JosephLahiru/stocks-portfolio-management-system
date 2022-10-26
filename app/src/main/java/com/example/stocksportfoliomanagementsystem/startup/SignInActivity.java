package com.example.stocksportfoliomanagementsystem.startup;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.stocksportfoliomanagementsystem.R;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SignInActivity extends AppCompatActivity {

    private static final String URL = "jdbc:mysql://152.70.158.151:3306/spms";
    private static final String USER = "root";
    private static final String PASSWORD = "amres";

    EditText etUserName, etEmail, etPassword, etPasswordConfirm;
    Button signInButton;
    TextView loginText;

    String username, email, password, comPassword;

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
                //String sql2 = "CREATE TABLE `user`(user_id INT PRIMARY KEY AUTO_INCREMENT, user_email VARCHAR(60), user_password VARCHAR(100));";
                Statement st1 = connection.createStatement();

                if (!st1.execute(sql)) {
                    System.out.println("Data uploaded successfully");

                    startActivity(new Intent(SignInActivity.this, LoginActivity.class));
                    finish();

                } else {
                    System.out.println("Data upload failed.");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(SignInActivity.this, "Signing Failed.", Toast.LENGTH_SHORT);
                            toast.show();
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