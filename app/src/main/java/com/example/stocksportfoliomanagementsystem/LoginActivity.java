package com.example.stocksportfoliomanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    EditText userNameEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.loginButton);
        userNameEditText = (EditText) findViewById(R.id.editTextTextUserName);
        passwordEditText = (EditText) findViewById(R.id.editTextTextPassword);

        DAOUser doa = new DAOUser();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(LoginActivity.this, "User Name : " + userNameEditText.getText().toString() + "\nPassword : " + passwordEditText.getText().toString(), Toast.LENGTH_SHORT).show();
                User user = new User(userNameEditText.getText().toString(), passwordEditText.getText().toString());
                doa.add(user).addOnSuccessListener(suc->{
                    Toast.makeText(LoginActivity.this, "Data Inserted Successfully.", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er->{
                    Toast.makeText(LoginActivity.this, er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
    }
}