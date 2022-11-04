package com.example.stocksportfoliomanagementsystem.transactions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stocksportfoliomanagementsystem.R;
import com.example.stocksportfoliomanagementsystem.startup.MenuActivity;

public class FinancialManagementActivity extends AppCompatActivity {

    Button backToDashboardButton;
    Button viewTransactionsButton;
    Button updateTransactionButton;
    Button saveTransactionButton;
    Button deleteTransactionButton;
    String userEmail, userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_management);

        userEmail = getIntent().getStringExtra("userEmail");

        backToDashboardButton = (Button) findViewById(R.id.fmback);
        backToDashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinancialManagementActivity.this, MenuActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });
        viewTransactionsButton = (Button) findViewById(R.id.viewtransactions);
        viewTransactionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinancialManagementActivity.this, ViewTransactionsActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });
        updateTransactionButton = (Button) findViewById(R.id.updatetransactions);
        updateTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinancialManagementActivity.this, UpdateTransactionsActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });
        saveTransactionButton = (Button) findViewById(R.id.savetransactions);
        saveTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinancialManagementActivity.this, SaveTransactionsActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });
        deleteTransactionButton = (Button) findViewById(R.id.deletetransactions);
        deleteTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinancialManagementActivity.this, DeleteTransactionsActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });
    }
}