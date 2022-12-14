package com.example.stocksportfoliomanagementsystem.supplier;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
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

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class UpdateSupplyDetailsActivity extends AppCompatActivity {

    String userEmail, userType;
    Spinner dropdown;
    String data[][];
    TableView tableView;
    Button updateSupplyDetailsButton,backToSupplireManagementButton;
    Connection connection;
    EditText brandName,quntity,pricePerItem;
    int supplier_id;

    private static final String URL = "jdbc:mysql://152.70.158.151:3306/spms";
    private static final String USER = "root";
    private static final String PASSWORD = "amres";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_supply_details);

        userEmail = getIntent().getStringExtra("userEmail");
        userType = getIntent().getStringExtra("userType");

        dropdown = findViewById(R.id.spinnerSelectProductSU);

        brandName = (EditText) findViewById(R.id.editTextBrandNameSU);
        quntity = (EditText) findViewById(R.id.editTextQuantitySU);
        pricePerItem = (EditText) findViewById(R.id.editTextCostPerItemSU);

        tableView = findViewById(R.id.tableViewUpdateSupplyDetailsActivity);
        tableView.setColumnCount(5);
        String headers[] = {"Product ID","Product Name", "Brand","Quantity","Price Per Item"};
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, headers));

        new InfoAsyncTask().execute();
        new InfoAsyncTask2().execute();

        updateSupplyDetailsButton = (Button) findViewById(R.id.btnusd);
        updateSupplyDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new UpdateSupplyDetailsTask().execute();
            }
        });

        backToSupplireManagementButton = (Button) findViewById(R.id.vbsm4);
        backToSupplireManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateSupplyDetailsActivity.this, SupplierManagementActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
            }
        });
    }
    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<Void, Void, List<List<String>>> {
        @Override
        protected List<List<String>> doInBackground(Void... voids) {
            List<List<String>> products = new ArrayList<>();
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                String query = "SELECT supplier_details.supplier_id  FROM user CROSS JOIN supplier_details WHERE user.user_email='" + userEmail + "' and supplier_details.user_id = user.user_id;";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet2 = preparedStatement.executeQuery();

                while (resultSet2.next()) {
                    supplier_id = resultSet2.getInt("supplier_id");
                    System.out.println(supplier_id);
                }
                String sql = "SELECT product.product_id, product.product_name, supply_details.brand, supply_details.quantity, supply_details.cost_of_item FROM product CROSS JOIN supply_details WHERE product.product_id=supply_details.product_id AND supply_details.supplier_id='" + supplier_id + "';";
                System.out.println(sql);
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    List<String> temp = new ArrayList<>();
                    temp.add(resultSet.getString("product_id"));
                    temp.add(resultSet.getString("product_name"));
                    temp.add(resultSet.getString("brand"));
                    temp.add(resultSet.getString("quantity"));
                    temp.add(resultSet.getString("cost_of_item"));
                    products.add(temp);
                }
            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading information", e);
            }

            return products;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(List<List<String>> result) {

            String[][] arr = result.stream()
                    .map(l -> l.stream().toArray(String[]::new))
                    .toArray(String[][]::new);


            if (!result.isEmpty()) {
                tableView.setDataAdapter(new SimpleTableDataAdapter(UpdateSupplyDetailsActivity.this, arr));
            }

        }


    }

    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask2 extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... voids) {
            List<String> products = new ArrayList<>();
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                String query = "SELECT supplier_details.supplier_id  FROM user CROSS JOIN supplier_details WHERE user.user_email='" + userEmail + "' and supplier_details.user_id = user.user_id;";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet2 = preparedStatement.executeQuery();

                while (resultSet2.next()) {
                    supplier_id = resultSet2.getInt("supplier_id");
                    System.out.println(supplier_id);
                }
                String sql = "SELECT product.product_id, product.product_name FROM product CROSS JOIN supply_details WHERE product.product_id=supply_details.product_id AND supply_details.supplier_id='"+supplier_id+"';";
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

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateSupplyDetailsActivity.this,
                        android.R.layout.simple_spinner_item, result);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dropdown.setAdapter(adapter);
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class UpdateSupplyDetailsTask extends AsyncTask<Void, Void, List<String>> {
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

                String sql = "UPDATE `spms`.`supply_details` SET `brand` = '"+brandName_+"', `quantity` = '"+quntity_+"', `cost_of_item` = '"+pricePerItem_+"' WHERE (`supplier_id` = '"+supplier_id+"') and (`product_id` = '"+productId+"');";

                System.out.println(sql);

                Statement st1 = connection.createStatement();

                if (!st1.execute(sql)) {
                    System.out.println("Data Updated successfully");

                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(UpdateSupplyDetailsActivity.this, "Data Updated Successfully.", Toast.LENGTH_SHORT);
                            toast.show();
                            new InfoAsyncTask().execute();
                            new InfoAsyncTask2().execute();
                        }
                    });

                    brandName.getText().clear();
                    quntity.getText().clear();
                    pricePerItem.getText().clear();

                } else {
                    System.out.println("Data upload failed.");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(UpdateSupplyDetailsActivity.this, "Data Updated Failed.", Toast.LENGTH_SHORT);
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