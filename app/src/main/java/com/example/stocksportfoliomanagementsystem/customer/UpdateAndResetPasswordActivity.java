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

public class UpdateAndResetPasswordActivity extends AppCompatActivity {

    Connection connection;
    Button backBtn, updateBtn;

    EditText etFName, etLName, etAddress, etTpNo, etEmail;

    private static final String URL = "jdbc:mysql://152.70.158.151:3306/spms";
    private static final String USER = "root";
    private static final String PASSWORD = "amres";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_reset_password);

        etFName = (EditText) findViewById(R.id.idFirstName);
        etLName = (EditText) findViewById(R.id.idLastName);
        etAddress = (EditText) findViewById(R.id.idAddress);
        etTpNo = (EditText) findViewById(R.id.idTelNo);
        etEmail = (EditText) findViewById(R.id.idEmail);

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
                startActivity(new Intent(UpdateAndResetPasswordActivity.this, CustomerManagementActivity.class));
                finish();
            }
        });

        new InfoAsyncTask().execute();
    }

    @SuppressLint("StaticFieldLeak")
    public class UpdateUserTask extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... voids) {
            List<String> products = new ArrayList<>();
            try {

                String custID = resetDataID.getText().toString();

                String sql = "UPDATE customer SET `password` = '1234' WHERE  `custID` = '" + custID + "'; ";

                System.out.println(sql);

                Statement st1 = connection.createStatement();

                if (!st1.execute(sql)) {
                    System.out.println("Data updated successfully");

                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(UpdateAndResetPasswordActivity.this, "Password Reset Successful.", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });

                } else {
                    System.out.println("Data upload failed.");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(UpdateAndResetPasswordActivity.this, "Password Reset Failed.", Toast.LENGTH_SHORT);
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
    public class InfoAsyncTask extends AsyncTask<Void, Void, List<List<String>>> {
        @Override
        protected List<List<String>> doInBackground(Void... voids) {
            List<List<String>> products = new ArrayList<>();
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);

                String sql = "SELECT * FROM customer";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    List<String> temp = new ArrayList<>();
                    temp.add(resultSet.getString("custID"));
                    temp.add(resultSet.getString("firstName"));
                    temp.add(resultSet.getString("email"));
                    temp.add(resultSet.getString("telNo"));
                    products.add(temp);
                }
            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading information", e);
            }

            return products;
        }
    }
}