package com.example.stocksportfoliomanagementsystem.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stocksportfoliomanagementsystem.R;
import com.example.stocksportfoliomanagementsystem.startup.MenuActivity;

public class CustomerManagementActivity extends AppCompatActivity {

    Button createCustomerBtn, backToMenuBtn, resetPasswordBtn, updateCustomerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_management);

        updateCustomerBtn = (Button) findViewById(R.id.updateCustomer);

        updateCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerManagementActivity.this, UpdateCustomerActivity.class));
                finish();
            }
        });

        resetPasswordBtn = (Button) findViewById(R.id.updateAndResetPassword);

        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerManagementActivity.this, UpdateAndResetPasswordActivity.class));
                finish();
            }
        });

        backToMenuBtn = (Button) findViewById(R.id.backToMenu12);

        backToMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerManagementActivity.this, MenuActivity.class));
                finish();
            }
        });

        createCustomerBtn = (Button) findViewById(R.id.createCustomer);

        createCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerManagementActivity.this, CreateCustomerActivity.class));
                finish();
            }
        });
    }
}