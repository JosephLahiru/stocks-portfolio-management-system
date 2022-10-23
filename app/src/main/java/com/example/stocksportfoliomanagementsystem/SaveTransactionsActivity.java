package com.example.stocksportfoliomanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SaveTransactionsActivity extends AppCompatActivity {
    Button backToFinancialManagementButton;
    Spinner dropdown;
    EditText companyName,productName,productDiscription,productQuantity,totalPrice;
    Connection connection;
    Button savetransactonBtn;

    private static final String URL = "jdbc:mysql://152.70.158.151:3306/spms";
    private static final String USER = "root";
    private static final String PASSWORD = "amres";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_transactions);
        backToFinancialManagementButton = (Button) findViewById(R.id.sbfm);
        backToFinancialManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SaveTransactionsActivity.this, FinancialManagementActivity.class));
                finish();
            }
        });
        dropdown = findViewById(R.id.spinner);
        savetransactonBtn = (Button) findViewById(R.id.btnst);
        savetransactonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SaveTransactions().execute();
            }
        });
        companyName = (EditText) findViewById(R.id.editTextCompanyName);
        productName = (EditText) findViewById(R.id.editTextProcuctName);
        productDiscription = (EditText) findViewById(R.id.editTextpdiscription);
        productQuantity = (EditText) findViewById(R.id.editTextProductQuantity);
        totalPrice = (EditText) findViewById(R.id.editTextTotalPrice);


    }

    @SuppressLint("StaticFieldLeak")
    public class SaveTransactions extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... voids) {
            List<String> transactions = new ArrayList<>();
            try {

                String companyName_ = companyName.getText().toString();
                String productName_ = productName.getText().toString();
                String productDiscription_ = productDiscription.getText().toString();
                String productQuantity_ = productQuantity.getText().toString();
                String totalPrice_ = totalPrice.getText().toString();
                String transactionType = dropdown.getSelectedItem().toString();

                String sql = "INSERT INTO `spms`.`transactions` (`cname`, `pname`, `discription`, `pquantity`, `total`, `ttype`) VALUES('" + companyName_ + "', '" + productName_ + "','" +productDiscription_+ "','" +productQuantity_+ "','" +totalPrice_+ "','" +transactionType+ "');";
                
                System.out.println(sql);

                connection = DriverManager.getConnection(URL, USER, PASSWORD);

                Statement st1 = connection.createStatement();

                if (!st1.execute(sql)) {
                    System.out.println("Data uploaded successfully");

                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(SaveTransactionsActivity.this, "Data Uploaded Successfully.", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });

                    companyName.getText().clear();
                    productName.getText().clear();
                    productDiscription.getText().clear();
                    productQuantity.getText().clear();
                    totalPrice.getText().clear();
                    
                } else {
                    System.out.println("Data upload failed.");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(SaveTransactionsActivity.this, "Data Upload Failed.", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });
                }
            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading information", e);
            }
            return transactions;
        }
    }
}