package com.example.stocksportfoliomanagementsystem.startup;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stocksportfoliomanagementsystem.R;
import com.example.stocksportfoliomanagementsystem.delivery.DeliveryManagementActivity;
import com.example.stocksportfoliomanagementsystem.supplier.SupplierManagementActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private static final String URL = "jdbc:mysql://152.70.158.151:3306/spms";
    private static final String USER = "root";
    private static final String PASSWORD = "amres";

    Button loginButton;
    EditText emailEditText;
    EditText passwordEditText;
    TextView redirectSignInTextView;
    ProgressBar progressBar;

    String email, password, userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.loginButton);
        emailEditText = (EditText) findViewById(R.id.editTextEmail);
        passwordEditText = (EditText) findViewById(R.id.editTextTextPassword);
        redirectSignInTextView = (TextView) findViewById(R.id.redirectSignup);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        redirectSignInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignInActivity.class));
                finish();
            }
        });
    }

    public void loginUser(){
        email = emailEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            emailEditText.setError("Email cannot be empty.");
            emailEditText.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            passwordEditText.setError("Password cannot be empty.");
            passwordEditText.requestFocus();
        }else{
            progressBar.setVisibility(View.VISIBLE);

            new InfoAsyncTask().execute();
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... voids) {
            List<String> districts = new ArrayList<>();

            int user_found = 0;

            try {
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

                String sql = "SELECT * FROM user";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {

                    if(Objects.equals(resultSet.getString("user_email"), email) && Objects.equals(resultSet.getString("user_password"), password)){
                        user_found = 1;
                        userType = resultSet.getString("user_type").toLowerCase(Locale.ROOT);
                        break;
                    }
                }

                if(user_found == 1){
                    progressBar.setVisibility(View.INVISIBLE);
                    if(Objects.equals(userType, "supplier")){
                        Intent intent = new Intent(LoginActivity.this, SupplierManagementActivity.class);
                        intent.putExtra("userEmail", email);
                        intent.putExtra("userType", userType);
                        startActivity(intent);
                        finish();

                        //TODO : remove back to dashboard from supplier add logout button
                    }else if(Objects.equals(userType, "delivery")){
                        Intent intent = new Intent(LoginActivity.this, DeliveryManagementActivity.class);
                        intent.putExtra("userEmail", email);
                        intent.putExtra("userType", userType);
                        startActivity(intent);
                        finish();

                        //TODO : do the same on delivery
                    }else {
                        //Toast.makeText(LoginActivity.this, "User logged in successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                        intent.putExtra("userEmail", email);
                        intent.putExtra("userType", userType);
                        startActivity(intent);
                        finish();
                    }
                }else{
                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(LoginActivity.this, "User login failed.", Toast.LENGTH_SHORT);
                            toast.show();

                            emailEditText.getText().clear();
                            passwordEditText.getText().clear();

                            progressBar.setVisibility(View.INVISIBLE);
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