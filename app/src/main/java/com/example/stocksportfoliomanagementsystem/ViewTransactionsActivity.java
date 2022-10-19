package com.example.stocksportfoliomanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewTransactionsActivity extends AppCompatActivity {
    Button backToFinancialManagementButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transactions);
        backToFinancialManagementButton = (Button) findViewById(R.id.vbfm);
        backToFinancialManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewTransactionsActivity.this, FinancialManagementActivity.class));
                finish();
            }
        });
    }
}