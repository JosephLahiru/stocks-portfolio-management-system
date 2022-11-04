package com.example.stocksportfoliomanagementsystem.customer;

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

import com.example.stocksportfoliomanagementsystem.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CreateCustomerActivity extends AppCompatActivity {

    Button registerBtn, backButton, clearBtn;
    Connection connection;
    EditText etFirstName, etLastName, etAddress, etTpNum, etEmail, etPassword;

    String userEmail, userType;

    private static final String URL = "jdbc:mysql://152.70.158.151:3306/spms";
    private static final String USER = "root";
    private static final String PASSWORD = "amres";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_customer);

        userEmail = getIntent().getStringExtra("userEmail");
        userType = getIntent().getStringExtra("userType");

        etFirstName = (EditText) findViewById(R.id.inputFirstName);
        etLastName = (EditText) findViewById(R.id.inputLastName);
        etAddress = (EditText) findViewById(R.id.inputAddress);
        etTpNum = (EditText) findViewById(R.id.inputTeleNo);
        etEmail = (EditText) findViewById(R.id.inputEmail);
        etPassword = (EditText) findViewById(R.id.inputPassword);

        registerBtn = (Button) findViewById(R.id.btnRegister);
        backButton = (Button) findViewById(R.id.btnBackRegis);
        clearBtn = (Button) findViewById(R.id.btnClear);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CusRegisterTask().execute();
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etFirstName.getText().clear();
                etLastName.getText().clear();
                etAddress.getText().clear();
                etTpNum.getText().clear();
                etEmail.getText().clear();
                etPassword.getText().clear();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateCustomerActivity.this, CustomerManagementActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    public class CusRegisterTask extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... voids) {
            List<String> products = new ArrayList<>();
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);

                String fName = etFirstName.getText().toString();
                String lName = etLastName.getText().toString();
                String address = etAddress.getText().toString();
                String tpNum = etTpNum.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                String sql = "INSERT INTO customer(`firstName`, `lastName`, `address`, `telNo`, `email`, `password`) " +
                        "VALUES ('" + fName + "', '" + lName + "', '" + address + "', '" + tpNum + "', '" + email + "', '" + password + "')";

                System.out.println(sql);

                Statement st1 = connection.createStatement();

                if (!st1.execute(sql)) {
                    System.out.println("Data uploaded successfully");

                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(CreateCustomerActivity.this, "Data Uploaded Successfully.", Toast.LENGTH_SHORT);
                            toast.show();

                            etFirstName.getText().clear();
                            etLastName.getText().clear();
                            etAddress.getText().clear();
                            etTpNum.getText().clear();
                            etEmail.getText().clear();
                            etPassword.getText().clear();
                        }
                    });

                } else {
                    System.out.println("Data upload failed.");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(CreateCustomerActivity.this, "Data Upload Failed.", Toast.LENGTH_SHORT);
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
