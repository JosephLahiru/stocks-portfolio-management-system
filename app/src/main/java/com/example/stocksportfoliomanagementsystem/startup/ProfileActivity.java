package com.example.stocksportfoliomanagementsystem.startup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.stocksportfoliomanagementsystem.R;


public class ProfileActivity extends AppCompatActivity {

    Button btnLogout, btnMenu;
    String userEmail, userType;
    TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnLogout = findViewById(R.id.logoutButton);
        btnMenu = findViewById(R.id.menuButton);
        welcomeTextView = findViewById(R.id.welcomeText);

        userEmail = getIntent().getStringExtra("userEmail");
        userType = getIntent().getStringExtra("userType");

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}