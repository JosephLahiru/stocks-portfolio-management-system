package com.example.stocksportfoliomanagementsystem.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stocksportfoliomanagementsystem.R;

public class CustomerManagementActivity extends AppCompatActivity {

    Button createCustomerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_management);

        createCustomerBtn = (Button) findViewById(R.id.createCustomer);

        createCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerManagementActivity.this, CreateCustomerActivity.class));
            }
        });
    }
}