package com.example.stocksportfoliomanagementsystem.startup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stocksportfoliomanagementsystem.admin.AdministrationActivity;
import com.example.stocksportfoliomanagementsystem.customer.CustomerManagementActivity;
import com.example.stocksportfoliomanagementsystem.reports.ReportsActivity;
import com.example.stocksportfoliomanagementsystem.transactions.FinancialManagementActivity;
import com.example.stocksportfoliomanagementsystem.delivery.DeliveryManagementActivity;
import com.example.stocksportfoliomanagementsystem.R;
import com.example.stocksportfoliomanagementsystem.supplier.SupplierManagementActivity;
import com.example.stocksportfoliomanagementsystem.stocks.StockManagementActivity;

import java.util.Locale;
import java.util.Objects;

public class MenuActivity extends AppCompatActivity {

    Button financeManagementButton;
    Button stockManagementButton;
    Button supplierManagementButton;
    Button adminManagementButton;
    Button deliveryManagementButton;
    Button customerManagement;
    Button reportsButton;

    String userEmail, userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        userEmail = getIntent().getStringExtra("userEmail");
        userType = getIntent().getStringExtra("userType").toLowerCase(Locale.ROOT);

        System.out.println(userType);

        customerManagement = (Button) findViewById(R.id.cusMngButton);
        reportsButton = (Button) findViewById(R.id.reportMngButton);
        deliveryManagementButton = (Button) findViewById(R.id.deliveryMngButton);
        adminManagementButton = (Button) findViewById(R.id.adminMngButton);
        financeManagementButton = (Button) findViewById(R.id.financialMngButton);
        stockManagementButton = (Button) findViewById(R.id.stockMngButton);
        supplierManagementButton = (Button) findViewById(R.id.supplierMngButton);


        if(Objects.equals(userType, "admin")){
            customerManagement.setVisibility(View.VISIBLE);
            reportsButton.setVisibility(View.VISIBLE);
            deliveryManagementButton.setVisibility(View.VISIBLE);
            adminManagementButton.setVisibility(View.VISIBLE);
            financeManagementButton.setVisibility(View.VISIBLE);
            stockManagementButton.setVisibility(View.VISIBLE);
            supplierManagementButton.setVisibility(View.VISIBLE);
        }else if(Objects.equals(userType, "supplier")){
            supplierManagementButton.setVisibility(View.VISIBLE);
        }else if(Objects.equals(userType, "management")){
            customerManagement.setVisibility(View.VISIBLE);
            reportsButton.setVisibility(View.VISIBLE);
            deliveryManagementButton.setVisibility(View.VISIBLE);
            financeManagementButton.setVisibility(View.VISIBLE);
            stockManagementButton.setVisibility(View.VISIBLE);
            supplierManagementButton.setVisibility(View.VISIBLE);
        }else if(Objects.equals(userType, "delivery")){
            deliveryManagementButton.setVisibility(View.VISIBLE);
        }


        customerManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, CustomerManagementActivity.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
                finish();
            }
        });

        reportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, ReportsActivity.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
                finish();
            }
        });

        deliveryManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, DeliveryManagementActivity.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
                finish();
            }
        });

        adminManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, AdministrationActivity.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
                finish();
            }
        });

        financeManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, FinancialManagementActivity.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
                finish();
            }
        });

        stockManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, StockManagementActivity.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
                finish();
            }
        });

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