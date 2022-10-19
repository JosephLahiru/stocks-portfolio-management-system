package com.example.stocksportfoliomanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StockManagementActivity extends AppCompatActivity {

    Button addStockBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_management);

        addStockBtn = (Button) findViewById(R.id.addStocks);

        addStockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StockManagementActivity.this, AddStockActivity.class));
                finish();
            }
        });
    }
}