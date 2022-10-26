package com.example.stocksportfoliomanagementsystem.startup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stocksportfoliomanagementsystem.AdministrationActivity;
import com.example.stocksportfoliomanagementsystem.FinancialManagementActivity;
import com.example.stocksportfoliomanagementsystem.R;
import com.example.stocksportfoliomanagementsystem.SupplierManagementActivity;
import com.example.stocksportfoliomanagementsystem.stocks.StockManagementActivity;

public class MenuActivity extends AppCompatActivity {

    Button financeManagementButton;
    Button stockManagementButton;
    Button supplierManagementButton;
    Button adminManagementButton;

    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        userEmail = getIntent().getStringExtra("userEmail");

        adminManagementButton = (Button) findViewById(R.id.adminMngButton);

        adminManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, AdministrationActivity.class));
                finish();
            }
        });

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

        supplierManagementButton = (Button) findViewById(R.id.supplierMngButton);

        supplierManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, SupplierManagementActivity.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
                finish();
            }
        });
    }
}