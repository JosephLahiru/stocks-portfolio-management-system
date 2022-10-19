package com.example.stocksportfoliomanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinancialManagementActivity extends AppCompatActivity {

    Button backToDashboardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_management);

        backToDashboardButton = (Button) findViewById(R.id.fmback);
        backToDashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FinancialManagementActivity.this, MenuActivity.class));
                finish();
            }
        });
    }
}