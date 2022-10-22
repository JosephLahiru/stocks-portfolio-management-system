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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddStockActivity extends AppCompatActivity {

    Spinner dropdown;
    Button addStockBtn, backButton;
    Connection connection;
    EditText productQty;

    private static final String URL = "jdbc:mysql://152.70.158.151:3306/spms";
    private static final String USER = "root";
    private static final String PASSWORD = "amres";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);

        dropdown = findViewById(R.id.spinner1);
        addStockBtn = (Button) findViewById(R.id.addStock);
        productQty = (EditText) findViewById(R.id.etProductQty);
        backButton = (Button) findViewById(R.id.backButton);

        new InfoAsyncTask().execute();

        addStockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddStockTask().execute();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddStockActivity.this, StockManagementActivity.class));
                finish();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... voids) {
            List<String> products = new ArrayList<>();
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);

                //System.out.println("CONNECTED SUCCESSFULLY");

                String sql = "SELECT * FROM product";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    //System.out.println("DATA : " + resultSet.getString("product_name"));
                    products.add(resultSet.getString("product_id") + " - " + resultSet.getString("product_name"));
                }
            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading information", e);
            }

            return products;
        }

        @Override
        protected void onPostExecute(List<String> result) {
            if (!result.isEmpty()) {

                ArrayAdapter<String>adapter = new ArrayAdapter<String>(AddStockActivity.this,
                        android.R.layout.simple_spinner_item, result);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dropdown.setAdapter(adapter);
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class AddStockTask extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... voids) {
            List<String> products = new ArrayList<>();
            try {

                String productId = dropdown.getSelectedItem().toString().split("-")[0];
                System.out.println(productId);

                String productQty_ = productQty.getText().toString();

                String sql = "INSERT INTO product_stock(stock_qty, product_id) VALUES('" + productQty_ + "', '" + productId + "');";
                //String sql2 = "CREATE TABLE `user`(user_id INT PRIMARY KEY AUTO_INCREMENT, user_email VARCHAR(60), user_password VARCHAR(100));";

                System.out.println(sql);

                Statement st1 = connection.createStatement();

                if (!st1.execute(sql)) {
                    System.out.println("Data uploaded successfully");

                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(AddStockActivity.this, "Data Uploaded Successfully.", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });

                    productQty.getText().clear();

                } else {
                    System.out.println("Data upload failed.");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(AddStockActivity.this, "Data Upload Failed.", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });
                }
            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading information", e);
            }
            return products;
        }
    }
}
