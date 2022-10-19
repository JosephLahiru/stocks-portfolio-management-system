package com.example.stocksportfoliomanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinancialManagementActivity extends AppCompatActivity {

    Button backToDashboardButton;
    Button viewTransactionsButton;
    Button updateTransactionButton;
    Button saveTransactionButton;
    Button deleteTransactionButton;

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
        viewTransactionsButton = (Button) findViewById(R.id.viewtransactions);
        viewTransactionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FinancialManagementActivity.this, ViewTransactionsActivity.class));
                finish();
            }
        });
        updateTransactionButton = (Button) findViewById(R.id.updatetransactions);
        updateTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FinancialManagementActivity.this, UpdateTransactionsActivity.class));
                finish();
            }
        });
        saveTransactionButton = (Button) findViewById(R.id.savetransactions);
        saveTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FinancialManagementActivity.this, SaveTransactionsActivity.class));
                finish();
            }
        });
        deleteTransactionButton = (Button) findViewById(R.id.deletetransactions);
        deleteTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FinancialManagementActivity.this, DeleteTransactionsActivity.class));
                finish();
            }
        });
    }
}