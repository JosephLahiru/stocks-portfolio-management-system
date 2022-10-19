package com.example.stocksportfoliomanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ProfileActivity extends AppCompatActivity {

    Button btnLogout, btnMenu;
    String userEmail;
    TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnLogout = findViewById(R.id.logoutButton);
        btnMenu = findViewById(R.id.menuButton);
        welcomeTextView = findViewById(R.id.welcomeText);

        userEmail = getIntent().getStringExtra("userEmail");

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}