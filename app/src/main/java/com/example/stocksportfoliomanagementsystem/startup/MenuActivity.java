package com.example.stocksportfoliomanagementsystem.startup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stocksportfoliomanagementsystem.admin.AdministrationActivity;
import com.example.stocksportfoliomanagementsystem.customer.CustomerManagementActivity;
import com.example.stocksportfoliomanagementsystem.transactions.FinancialManagementActivity;
import com.example.stocksportfoliomanagementsystem.delivery.DeliveryManagementActivity;
import com.example.stocksportfoliomanagementsystem.R;
import com.example.stocksportfoliomanagementsystem.supplier.SupplierManagementActivity;
import com.example.stocksportfoliomanagementsystem.stocks.StockManagementActivity;

public class MenuActivity extends AppCompatActivity {

    Button financeManagementButton;
    Button stockManagementButton;
    Button supplierManagementButton;
    Button adminManagementButton;
    Button deliveryManagementButton;
    Button customerManagement;

    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        userEmail = getIntent().getStringExtra("userEmail");

        customerManagement = (Button) findViewById(R.id.cusMngButton);

        customerManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, CustomerManagementActivity.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
                finish();
            }
        });

        deliveryManagementButton = (Button) findViewById(R.id.deliveryMngButton);

        deliveryManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, DeliveryManagementActivity.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
                finish();
            }
        });

        adminManagementButton = (Button) findViewById(R.id.adminMngButton);

        adminManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, AdministrationActivity.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
                finish();
            }
        });

        financeManagementButton = (Button) findViewById(R.id.financialMngButton);

        financeManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, FinancialManagementActivity.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
                finish();
            }
        });
              
        stockManagementButton = (Button) findViewById(R.id.stockMngButton);

        stockManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, StockManagementActivity.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
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