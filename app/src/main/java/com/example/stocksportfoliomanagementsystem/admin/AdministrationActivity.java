package com.example.stocksportfoliomanagementsystem.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stocksportfoliomanagementsystem.R;
import com.example.stocksportfoliomanagementsystem.startup.MenuActivity;

public class AdministrationActivity extends AppCompatActivity {

    Button backBtn, userRegistrationBtn, supplierDataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);

        supplierDataBtn = (Button) findViewById(R.id.supplierDataReview);

        supplierDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdministrationActivity.this, ViewSupplierDataActivity.class));
                finish();
            }
        });

        userRegistrationBtn = (Button) findViewById(R.id.userRegistration);

        userRegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdministrationActivity.this, UserRegistrationActivity.class));
                finish();
            }
        });

        backBtn = (Button) findViewById(R.id.backToMenuAdmin);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdministrationActivity.this, MenuActivity.class));
                finish();
            }
        });
    }
}