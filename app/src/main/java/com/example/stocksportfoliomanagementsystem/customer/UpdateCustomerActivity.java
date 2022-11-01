package com.example.stocksportfoliomanagementsystem.customer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stocksportfoliomanagementsystem.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class UpdateCustomerActivity extends AppCompatActivity {

    Connection connection;
    Button backBtn, updateBtn, loadDataBtn;

    EditText etFName, etLName, etAddress, etTpNo, etEmail, etCustID;

    private static final String URL = "jdbc:mysql://152.70.158.151:3306/spms";
    private static final String USER = "root";
    private static final String PASSWORD = "amres";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customer);

        etFName = (EditText) findViewById(R.id.idFirstName);
        etLName = (EditText) findViewById(R.id.idLastName);
        etAddress = (EditText) findViewById(R.id.idAddress);
        etTpNo = (EditText) findViewById(R.id.idTelNo);
        etEmail = (EditText) findViewById(R.id.idEmail);
        etCustID = (EditText) findViewById(R.id.idCus);

        loadDataBtn = (Button) findViewById(R.id.btnLoadData);

        loadDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoadDataTask().execute();
            }
        });

        updateBtn = (Button) findViewById(R.id.btnUpdateDetails);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new UpdateUserTask().execute();
            }
        });

        backBtn = (Button) findViewById(R.id.btnBackUpdateCus);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateCustomerActivity.this, CustomerManagementActivity.class));
                finish();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    public class UpdateUserTask extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... voids) {
            List<String> products = new ArrayList<>();
            try {

                String fName = etFName.getText().toString();
                String lName = etLName.getText().toString();
                String address = etAddress.getText().toString();
                String tpNum = etTpNo.getText().toString();
                String email = etEmail.getText().toString();

                String custId = etCustID.getText().toString();

                String sql = "UPDATE customer SET `firstName` = '" + fName + "', `lastName` = '" + lName + "' " +
                        ", `address` = '" + address + "', `telNo` = '" + tpNum + "', `email` = '" + email + "' WHERE  `custID` = '" + custId + "'; ";

                System.out.println(sql);

                Statement st1 = connection.createStatement();

                if (!st1.execute(sql)) {
                    System.out.println("Data updated successfully");

                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(UpdateCustomerActivity.this, "Password Reset Successful.", Toast.LENGTH_SHORT);
                            toast.show();

                            etFName.getText().clear();
                            etLName.getText().clear();
                            etAddress.getText().clear();
                            etTpNo.getText().clear();
                            etEmail.getText().clear();
                            etCustID.getText().clear();
                        }
                    });

                } else {
                    System.out.println("Data upload failed.");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(UpdateCustomerActivity.this, "Password Reset Failed.", Toast.LENGTH_SHORT);
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

    @SuppressLint("StaticFieldLeak")
    public class LoadDataTask extends AsyncTask<Void, Void, List<List<String>>> {
        @Override
        protected List<List<String>> doInBackground(Void... voids) {
            List<List<String>> products = new ArrayList<>();
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);

                String custId = etCustID.getText().toString();

                String sql = "SELECT * FROM customer WHERE `custID` = '" + custId + "' ";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    etFName.setText(resultSet.getString("firstName"));
                    etLName.setText(resultSet.getString("lastName"));
                    etAddress.setText(resultSet.getString("address"));
                    etTpNo.setText(resultSet.getString("telNo"));
                    etEmail.setText(resultSet.getString("email"));
                }
            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading information", e);
            }

            return products;
        }
    }
}