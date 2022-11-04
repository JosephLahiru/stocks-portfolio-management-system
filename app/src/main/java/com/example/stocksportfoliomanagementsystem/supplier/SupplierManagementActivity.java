package com.example.stocksportfoliomanagementsystem.supplier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stocksportfoliomanagementsystem.R;
import com.example.stocksportfoliomanagementsystem.common.UpdateUserActivity;
import com.example.stocksportfoliomanagementsystem.startup.MenuActivity;

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
    String userEmail, userType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_management);
        userEmail = getIntent().getStringExtra("userEmail");
        userType = getIntent().getStringExtra("userType");

        viewRequiredStockDetailsButton = (Button) findViewById(R.id.checkrequiredstock);
        viewRequiredStockDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupplierManagementActivity.this, CheckRequiredStockActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });

        viewSupplyDetailsButton = (Button) findViewById(R.id.checksupplydetails);
        viewSupplyDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupplierManagementActivity.this, CheckSupplyDetailsActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });

        saveSupplyDetailsButton = (Button) findViewById(R.id.savesupplydetails);
        saveSupplyDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupplierManagementActivity.this, SaveSupplyDetailsActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });

        updateSupplyDetailsButton = (Button) findViewById(R.id.updatesupplydetails);
        updateSupplyDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupplierManagementActivity.this, UpdateSupplyDetailsActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });

        deleteSupplyDetailsButton = (Button) findViewById(R.id.deletesupplydetails);
        deleteSupplyDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupplierManagementActivity.this, DeleteSupplyDetailsActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });

        viewDiscountsDetailsButton = (Button) findViewById(R.id.viewdiscountsdetails);
        viewDiscountsDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupplierManagementActivity.this, ViewDiscountsActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });

        addDiscountsDetailsButton = (Button) findViewById(R.id.adddiscountsdetails);
        addDiscountsDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupplierManagementActivity.this, AddDiscountsDetailsActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });

        updateDiscountsDetailsButton = (Button) findViewById(R.id.updatediscountsdetails);
        updateDiscountsDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupplierManagementActivity.this, UpdateDiscountsDetailsActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });

        deleteDiscountsDetailsButton = (Button) findViewById(R.id.deletediscountsdetails);
        deleteDiscountsDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupplierManagementActivity.this, DeleteDiscountsDetailsActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });

        updateUserDetailsButton = (Button) findViewById(R.id.updateuserdetails);
        updateUserDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupplierManagementActivity.this, UpdateUserActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });

        backToDashboardButton = (Button) findViewById(R.id.smback);
        backToDashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupplierManagementActivity.this, MenuActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });
    }
}