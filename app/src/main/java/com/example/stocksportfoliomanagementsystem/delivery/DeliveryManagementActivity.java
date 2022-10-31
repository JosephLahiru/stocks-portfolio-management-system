package com.example.stocksportfoliomanagementsystem.delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stocksportfoliomanagementsystem.R;
import com.example.stocksportfoliomanagementsystem.startup.MenuActivity;

public class DeliveryManagementActivity extends AppCompatActivity {

    Button viewOderListBtn, confirmDeliveryBtn, getUserFeedbackBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_management);

        viewOderListBtn = (Button) findViewById(R.id.viewOrderList);

        viewOderListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeliveryManagementActivity.this, ViewOrderListActivity.class));
                finish();
            }
        });

        confirmDeliveryBtn = (Button) findViewById(R.id.confirmDelivery);

        confirmDeliveryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeliveryManagementActivity.this, ConfirmDeliveryActivity.class));
                finish();
            }
        });

        getUserFeedbackBtn = (Button) findViewById(R.id.getUserFeedback);

        getUserFeedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeliveryManagementActivity.this, GetUserDeliveryFeedbackActivity.class));
                finish();
            }
        });

        backBtn = (Button) findViewById(R.id.backToMenuDelivery);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeliveryManagementActivity.this, MenuActivity.class));
                finish();
            }
        });
    }
}