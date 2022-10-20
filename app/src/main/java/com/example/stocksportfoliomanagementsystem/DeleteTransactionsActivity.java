package com.example.stocksportfoliomanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeleteTransactionsActivity extends AppCompatActivity {

    Button backToFinancialManagementButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_transactions);
        backToFinancialManagementButton = (Button) findViewById(R.id.dbfm);
        backToFinancialManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeleteTransactionsActivity.this, FinancialManagementActivity.class));
                finish();
            }
        });
    }

}