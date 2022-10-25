package com.example.stocksportfoliomanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdministrationActivity extends AppCompatActivity {

    Button backBtn, userRegistrationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);

        userRegistrationBtn = (Button) findViewById(R.id.userRegistration);

        userRegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdministrationActivity.this, UserRegistrationActivity.class));
                finish();
            }
        });

        backBtn = (Button) findViewById(R.id.backToMenuAdmin);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdministrationActivity.this, MenuActivity.class));
                finish();
            }
        });
    }
}