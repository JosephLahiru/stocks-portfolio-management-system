package com.example.stocksportfoliomanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StockManagementActivity extends AppCompatActivity {

    Button addStockBtn, viewStockHistoryBtn, backToMenuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_management);

        addStockBtn = (Button) findViewById(R.id.addStocks);
        viewStockHistoryBtn = (Button) findViewById(R.id.viewStockHistory);
        backToMenuBtn = (Button) findViewById(R.id.backtoMenu);

        backToMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StockManagementActivity.this, MenuActivity.class));
                finish();
            }
        });

        addStockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StockManagementActivity.this, AddStockActivity.class));
                finish();
            }
        });

        viewStockHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StockManagementActivity.this, ViewStockHistoryActivity.class));
                finish();
            }
        });

    }
}