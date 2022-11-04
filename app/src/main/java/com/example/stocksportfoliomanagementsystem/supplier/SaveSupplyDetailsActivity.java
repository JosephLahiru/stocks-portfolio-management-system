package com.example.stocksportfoliomanagementsystem.supplier;

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
import com.example.stocksportfoliomanagementsystem.stocks.AddStockActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SaveSupplyDetailsActivity extends AppCompatActivity {

    String userEmail, userType;
    Spinner dropdown;
    Button saveSupplyDetailsButton,backToSupplireManagementButton;
    Connection connection;
    EditText brandName,quntity,pricePerItem;
    int supplier_id;

    private static final String URL = "jdbc:mysql://152.70.158.151:3306/spms";
    private static final String USER = "root";
    private static final String PASSWORD = "amres";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_supply_details);
        userEmail = getIntent().getStringExtra("userEmail");
        userType = getIntent().getStringExtra("userType");

        dropdown = findViewById(R.id.spinnerSelectProductS);

        brandName = (EditText) findViewById(R.id.editTextBrandNameS);
        quntity = (EditText) findViewById(R.id.editTextQuantityS);
        pricePerItem = (EditText) findViewById(R.id.editTextCostPerItemS);

        new InfoAsyncTask().execute();

        saveSupplyDetailsButton = (Button) findViewById(R.id.btnssd);
        saveSupplyDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddSupplyDetailsTask().execute();
            }
        });

        backToSupplireManagementButton = (Button) findViewById(R.id.vbsm3);
        backToSupplireManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaveSupplyDetailsActivity.this, SupplierManagementActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
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
                String sql = "SELECT * FROM product";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
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

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SaveSupplyDetailsActivity.this,
                        android.R.layout.simple_spinner_item, result);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dropdown.setAdapter(adapter);
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class AddSupplyDetailsTask extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... voids) {
            List<String> products = new ArrayList<>();
            try {

                String query = "SELECT supplier_details.supplier_id  FROM user CROSS JOIN supplier_details WHERE user.user_email='"+userEmail+"' and supplier_details.user_id = user.user_id;";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet2 = preparedStatement.executeQuery();

                while (resultSet2.next()){
                    supplier_id = resultSet2.getInt("supplier_id");
                    System.out.println(supplier_id);
                }

                String productId = dropdown.getSelectedItem().toString().split("-")[0];
                System.out.println(productId);

                String brandName_ = brandName.getText().toString();
                String quntity_ = quntity.getText().toString();
                String pricePerItem_ = pricePerItem.getText().toString();

                String sql = "INSERT INTO `spms`.`supply_details` (`supplier_id`, `product_id`, `brand`, `quantity`, `cost_of_item`) VALUES ('"+supplier_id+"', '"+productId+"', '"+brandName_+"', '"+quntity_+"', '"+pricePerItem_+"');";

                System.out.println(sql);

                Statement st1 = connection.createStatement();

                if (!st1.execute(sql)) {
                    System.out.println("Data uploaded successfully");

                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(SaveSupplyDetailsActivity.this, "Data Uploaded Successfully.", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });

                    brandName.getText().clear();
                    quntity.getText().clear();
                    pricePerItem.getText().clear();

                } else {
                    System.out.println("Data upload failed.");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(SaveSupplyDetailsActivity.this, "Data Upload Failed.", Toast.LENGTH_SHORT);
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