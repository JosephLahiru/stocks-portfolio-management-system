package com.example.stocksportfoliomanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button financeManagementButton;
    Button stockManagementButton;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        userEmail = getIntent().getStringExtra("userEmail");

        financeManagementButton = (Button) findViewById(R.id.financialMngButton);

        financeManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, FinancialManagementActivity.class));
                finish();
            }
        });
              
        stockManagementButton = (Button) findViewById(R.id.stockMngButton);

        stockManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, StockManagementActivity.class));
                finish();
            }
        });
    }
}