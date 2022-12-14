package com.example.stocksportfoliomanagementsystem.delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stocksportfoliomanagementsystem.R;
import com.example.stocksportfoliomanagementsystem.startup.LoginActivity;
import com.example.stocksportfoliomanagementsystem.startup.MenuActivity;

import java.util.Objects;

public class DeliveryManagementActivity extends AppCompatActivity {

    Button viewOderListBtn, confirmDeliveryBtn, getUserFeedbackBtn, backBtn;

    String userEmail, userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_management);

        viewOderListBtn = (Button) findViewById(R.id.viewOrderList);

        userEmail = getIntent().getStringExtra("userEmail");
        userType = getIntent().getStringExtra("userType");

        viewOderListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeliveryManagementActivity.this, ViewOrderListActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });

        confirmDeliveryBtn = (Button) findViewById(R.id.confirmDelivery);

        confirmDeliveryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeliveryManagementActivity.this, ConfirmDeliveryActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });

        getUserFeedbackBtn = (Button) findViewById(R.id.getUserFeedback);

        getUserFeedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeliveryManagementActivity.this, GetUserDeliveryFeedbackActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });

        backBtn = (Button) findViewById(R.id.backToMenuDelivery);
        if(Objects.equals(userType, "admin")){
            backBtn.setText("BACK TO MENU");
        }else if(Objects.equals(userType, "delivery")){
            backBtn.setText("LOG OUT");
        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Objects.equals(userType, "admin")) {
                    Intent intent = new Intent(DeliveryManagementActivity.this, MenuActivity.class);
                    intent.putExtra("userEmail", userEmail);
                    intent.putExtra("userType", userType);
                    startActivity(intent);
                    finish();
                }else if(Objects.equals(userType, "delivery")){
                    Intent intent = new Intent(DeliveryManagementActivity.this, LoginActivity.class);
                    intent.putExtra("userEmail", userEmail);
                    intent.putExtra("userType", userType);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}