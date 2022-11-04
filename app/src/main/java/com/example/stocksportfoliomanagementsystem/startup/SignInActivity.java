package com.example.stocksportfoliomanagementsystem.startup;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stocksportfoliomanagementsystem.R;
import com.example.stocksportfoliomanagementsystem.stocks.AddStockActivity;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SignInActivity extends AppCompatActivity {

    private static final String URL = "jdbc:mysql://152.70.158.151:3306/spms";
    private static final String USER = "root";
    private static final String PASSWORD = "amres";

    EditText etUserName, etEmail, etPassword, etPasswordConfirm, usrFName, usrLName, usrAddress, usrCtcNum;
    Button signInButton;
    TextView loginText;
    Spinner dropdown;

    String username, email, password, comPassword, firstName, lastName, Address, ctcNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        etUserName = findViewById(R.id.editTextUserName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.editTextPassword);
        etPasswordConfirm = findViewById(R.id.editTextPasswordConfirm);
        signInButton = (Button) findViewById(R.id.signInButton);
        loginText = (TextView) findViewById(R.id.loginText);

        usrFName = (EditText) findViewById(R.id.editTextFirstName);
        usrLName = (EditText) findViewById(R.id.editTextLastName);
        usrAddress = (EditText) findViewById(R.id.editTextAddress);
        usrCtcNum = (EditText) findViewById(R.id.editTextContactNumber);

        dropdown = (Spinner) findViewById(R.id.spinner2);

        List<String> userTypes = new ArrayList<>();

        userTypes.add("Admin");
        userTypes.add("Supplier");
        userTypes.add("Manager");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SignInActivity.this,
                android.R.layout.simple_spinner_item, userTypes);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private void createUser(){
        username = etUserName.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        comPassword = etPasswordConfirm.getText().toString().trim();

        firstName = usrFName.getText().toString().trim();
        lastName = usrLName.getText().toString().trim();
        Address = usrAddress.getText().toString().trim();
        ctcNum = usrCtcNum.getText().toString().trim();
        
        if(TextUtils.isEmpty(email)){
            etEmail.setError("Email cannot be empty.");
            etEmail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            etPassword.setError("Password cannot be empty.");
            etPassword.requestFocus();
        }else if(TextUtils.isEmpty(firstName)){
            usrFName.setError("First Name cannot be empty.");
            usrFName.requestFocus();
        }else if(TextUtils.isEmpty(lastName)){
            usrLName.setError("Last Name cannot be empty.");
            usrLName.requestFocus();
        }else if(TextUtils.isEmpty(Address)){
            usrAddress.setError("Address cannot be empty.");
            usrAddress.requestFocus();
        }else if(TextUtils.isEmpty(ctcNum)){
            usrCtcNum.setError("Contact Number cannot be empty.");
            usrCtcNum.requestFocus();
        }else if(TextUtils.isEmpty(username)){
            etUserName.setError("Username cannot be empty.");
            etUserName.requestFocus();
        }else if(TextUtils.isEmpty(comPassword)){
            etPasswordConfirm.setError("Password confirm cannot be empty.");
            etPasswordConfirm.requestFocus();
        }else if(!password.equals(comPassword)){
            Toast.makeText(this, "Password and Confirm Password not matching.!!!", Toast.LENGTH_SHORT).show();
        }else{
            new InfoAsyncTask().execute();
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... voids) {
            List<String> districts = new ArrayList<>();
            try {
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

                String userType = dropdown.getSelectedItem().toString().toLowerCase(Locale.ROOT);
                System.out.println(userType);

                String sql = "INSERT INTO user(user_name, user_first_name, user_last_name, user_address, user_contact_number, user_email, user_password, user_type) VALUES('" + username + "', '" + firstName + "', '" + lastName + "', '" + Address + "', '" + ctcNum + "', '" + email + "', '" + password + "','" + userType + "');";
                //String sql2 = "CREATE TABLE `user`(user_id INT PRIMARY KEY AUTO_INCREMENT, user_email VARCHAR(60), user_password VARCHAR(100));";
                Statement st1 = connection.createStatement();

                if (!st1.execute(sql)) {
                    System.out.println("Data uploaded successfully");

                    startActivity(new Intent(SignInActivity.this, LoginActivity.class));
                    finish();

                } else {
                    System.out.println("Data upload failed.");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(SignInActivity.this, "Signing Failed.", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });
                }
            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading information", e);
            }

            return districts;
        }

    }
}