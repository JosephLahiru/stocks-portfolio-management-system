package com.example.stocksportfoliomanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class SupplierManagementActivity extends AppCompatActivity {

    Button backToDashboardButton;
    Button viewRequiredSupplyDetailsButton;
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
    }
}