package com.example.stocksportfoliomanagementsystem.reports;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stocksportfoliomanagementsystem.R;
import com.example.stocksportfoliomanagementsystem.startup.MenuActivity;

public class ReportsActivity extends AppCompatActivity {

    String userEmail, userType;

    Button finalMonthlyReportButton;
    Button supplierReportButton;
    Button backToDashboardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        userEmail = getIntent().getStringExtra("userEmail");
        userType = getIntent().getStringExtra("userType");

        backToDashboardButton = findViewById(R.id.rback);
        backToDashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportsActivity.this, MenuActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });

        finalMonthlyReportButton = findViewById(R.id.finalmonthlyreportbtn);
        finalMonthlyReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportsActivity.this, FinalMonthlyReportActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });

        supplierReportButton = findViewById(R.id.suppliersreportbtn);
        supplierReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportsActivity.this, SupplierReportActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });
    }
}