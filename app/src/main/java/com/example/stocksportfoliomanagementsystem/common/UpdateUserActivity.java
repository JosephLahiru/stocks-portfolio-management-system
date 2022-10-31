package com.example.stocksportfoliomanagementsystem.common;

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
import com.example.stocksportfoliomanagementsystem.supplier.DeleteDiscountsDetailsActivity;
import com.example.stocksportfoliomanagementsystem.supplier.SupplierManagementActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;

public class UpdateUserActivity extends AppCompatActivity {

    String userEmail;
    Button updateProfileButton,backToSupplireManagementButton;
    Connection connection;
    EditText username,firstname,lastname,email,phone,address,password;
    String userid_;
    int supplier_id;

    private static final String URL = "jdbc:mysql://152.70.158.151:3306/spms";
    private static final String USER = "root";
    private static final String PASSWORD = "amres";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        userEmail = getIntent().getStringExtra("userEmail");

        username = (EditText) findViewById(R.id.editTextUserNameUUP);
        firstname = (EditText) findViewById(R.id.editTextUserFristNameUUP);
        lastname = (EditText) findViewById(R.id.editTextUserLastNameUUP);
        address = (EditText) findViewById(R.id.editTextUserAddressUUP);
        phone = (EditText) findViewById(R.id.editTextUserContactNumberUUP);
        email = (EditText) findViewById(R.id.editTextUserEmailUUP);
        password = (EditText) findViewById(R.id.editTextUserPasswordUUP);

        new InfoAsyncTask().execute();

        updateProfileButton = (Button) findViewById(R.id.btnUpdateUserProfileUUP);
        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new UpdateUserTask().execute();
            }
        });

        backToSupplireManagementButton = (Button) findViewById(R.id.btnBTSM);
        backToSupplireManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateUserActivity.this, SupplierManagementActivity.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
            }
        });

    }

    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                String query = "SELECT * FROM user WHERE user_email = '" + userEmail + "'";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    userid_ = resultSet.getString("user_id");
                    String username_ = resultSet.getString("user_name");
                    String userfristname_ = resultSet.getString("user_first_name");
                    String userlastname_ = resultSet.getString("user_last_name");
                    String useraddress_ = resultSet.getString("user_address");
                    String usernumber_ = resultSet.getString("user_contact_number");
                    String useremail_ = resultSet.getString("user_email");
                    String userpassword_ = resultSet.getString("user_password");

                    username.setText(username_);
                    firstname.setText(userfristname_);
                    lastname.setText(userlastname_);
                    address.setText(useraddress_);
                    phone.setText(usernumber_);
                    email.setText(useremail_);
                    password.setText(userpassword_);

                }

            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading information", e);
            }

            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class UpdateUserTask extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... voids) {
            List<String> products = new ArrayList<>();
            try {

                String user_name = username.getText().toString();
                String user_first_name = firstname.getText().toString();
                String user_last_name = lastname.getText().toString();
                String user_address = address.getText().toString();
                String user_contact_number = phone.getText().toString();
                String user_email = email.getText().toString();
                String user_password = password.getText().toString();

                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                String sql = "UPDATE user SET user_name = '"+user_name+"', user_first_name ='"+user_first_name+"', user_last_name = '"+user_last_name+"', user_address = '"+user_address+"', user_contact_number = '"+user_contact_number+"', user_email = '"+user_email+"', user_password = '"+user_password+"' WHERE user_id = '"+userid_+"'";

                System.out.println(sql);

                Statement st1 = connection.createStatement();

                if (!st1.execute(sql)) {
                    System.out.println("Data updated successfully");

                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(UpdateUserActivity.this, "Data Updated Successfully.", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });

                } else {
                    System.out.println("Data Update failed.");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(UpdateUserActivity.this, "Data Update Failed.", Toast.LENGTH_SHORT);
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