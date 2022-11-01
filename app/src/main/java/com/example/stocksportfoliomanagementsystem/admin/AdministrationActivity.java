package com.example.stocksportfoliomanagementsystem.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stocksportfoliomanagementsystem.R;
import com.example.stocksportfoliomanagementsystem.customer.CustomerManagementActivity;
import com.example.stocksportfoliomanagementsystem.startup.MenuActivity;

public class AdministrationActivity extends AppCompatActivity {

    Button backBtn, userRegistrationBtn, supplierDataBtn, bugReviewBtn, devFeedbackBtn, usrPassResetBtn;

    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);

        userEmail = getIntent().getStringExtra("userEmail");

        usrPassResetBtn = (Button) findViewById(R.id.resetPassword);

        usrPassResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdministrationActivity.this, UserPasswordResetActivity.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
                finish();
            }
        });

        devFeedbackBtn = (Button) findViewById(R.id.developerFeedbackReview);

        devFeedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdministrationActivity.this, DeveloperFeedbackActivity.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
                finish();
            }
        });

        bugReviewBtn = (Button) findViewById(R.id.bugReview);

        bugReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdministrationActivity.this, BugReviewActivity.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
                finish();
            }
        });

        supplierDataBtn = (Button) findViewById(R.id.supplierDataReview);

        supplierDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdministrationActivity.this, ViewSupplierDataActivity.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
                finish();
            }
        });

        userRegistrationBtn = (Button) findViewById(R.id.userRegistration);

        userRegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdministrationActivity.this, UserRegistrationActivity.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
                finish();
            }
        });

        backBtn = (Button) findViewById(R.id.backToMenuAdmin);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdministrationActivity.this, MenuActivity.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
                finish();
            }
        });
    }
}