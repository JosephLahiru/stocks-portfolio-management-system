package com.example.stocksportfoliomanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SupplierManagementActivity extends AppCompatActivity {

    Button backToDashboardButton;
    Button viewRequiredStockDetailsButton;
    Button viewSupplyDetailsButton;
    Button saveSupplyDetailsButton;
    Button updateSupplyDetailsButton;
    Button deleteSupplyDetailsButton;
    Button viewDiscountsDetailsButton;
    Button addDiscountsDetailsButton;
    Button updateDiscountsDetailsButton;
    Button deleteDiscountsDetailsButton;
    Button updateUserDetailsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_management);

        viewRequiredStockDetailsButton = (Button) findViewById(R.id.checkrequiredstock);
        viewRequiredStockDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SupplierManagementActivity.this, CheckRequiredStockActivity.class));
                finish();
            }
        });

        viewSupplyDetailsButton = (Button) findViewById(R.id.checksupplydetails);
        viewSupplyDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SupplierManagementActivity.this, CheckSupplyDetailsActivity.class));
                finish();
            }
        });

        saveSupplyDetailsButton = (Button) findViewById(R.id.savesupplydetails);
        saveSupplyDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SupplierManagementActivity.this, SaveSupplyDetailsActivity.class));
                finish();
            }
        });

        updateSupplyDetailsButton = (Button) findViewById(R.id.updatesupplydetails);
        updateSupplyDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SupplierManagementActivity.this, UpdateSupplyDetailsActivity.class));
                finish();
            }
        });

        deleteSupplyDetailsButton = (Button) findViewById(R.id.deletesupplydetails);
        deleteSupplyDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SupplierManagementActivity.this, DeleteSupplyDetailsActivity.class));
                finish();
            }
        });

        viewDiscountsDetailsButton = (Button) findViewById(R.id.viewdiscountsdetails);
        viewDiscountsDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SupplierManagementActivity.this, ViewDiscountsActivity.class));
                finish();
            }
        });

        addDiscountsDetailsButton = (Button) findViewById(R.id.adddiscountsdetails);
        addDiscountsDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SupplierManagementActivity.this, AddDiscountsDetailsActivity.class));
                finish();
            }
        });

        updateDiscountsDetailsButton = (Button) findViewById(R.id.updatediscountsdetails);
        updateDiscountsDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SupplierManagementActivity.this, UpdateDiscountsDetailsActivity.class));
                finish();
            }
        });

        deleteDiscountsDetailsButton = (Button) findViewById(R.id.deletediscountsdetails);
        deleteDiscountsDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SupplierManagementActivity.this, DeleteDiscountsDetailsActivity.class));
                finish();
            }
        });

        updateUserDetailsButton = (Button) findViewById(R.id.updateuserdetails);
        updateUserDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SupplierManagementActivity.this, UpdateUserActivity.class));
                finish();
            }
        });

        backToDashboardButton = (Button) findViewById(R.id.smback);
        backToDashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SupplierManagementActivity.this, MenuActivity.class));
                finish();
            }
        });
    }
}