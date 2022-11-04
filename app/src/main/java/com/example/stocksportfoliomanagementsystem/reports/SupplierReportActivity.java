package com.example.stocksportfoliomanagementsystem.reports;

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

import com.example.stocksportfoliomanagementsystem.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class SupplierReportActivity extends AppCompatActivity {

    String userEmail, userType;
    TableView tableView;
    Button backToReportsButton;
    Connection connection;
    String data[][];

    private static final String URL = "jdbc:mysql://152.70.158.151:3306/spms";
    private static final String USER = "root";
    private static final String PASSWORD = "amres";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_report);

        userEmail = getIntent().getStringExtra("userEmail");
        userType = getIntent().getStringExtra("userType");

        backToReportsButton = (Button) findViewById(R.id.srbackbtn);
        backToReportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupplierReportActivity.this, ReportsActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });

        tableView = findViewById(R.id.tableViewSuppliersReport);
        tableView.setColumnCount(7);
        String headers[] = {"Supplier ID", "Supplier User Name", "Product ID", "Product Name", "Product brand", "Product Quantity", "Product Price"};
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, headers));
        new InfoAsyncTask().execute();

    }

    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<Void, Void, List<List<String>>> {
        @Override
        protected List<List<String>> doInBackground(Void... voids) {
            List<List<String>> products = new ArrayList<>();
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);

                String sql = "SELECT supply_details.supplier_id, user.user_name, supply_details.product_id, product.product_name, supply_details.brand, supply_details.quantity, supply_details.cost_of_item FROM supply_details CROSS JOIN product ON supply_details.product_id=product.product_id CROSS JOIN supplier_details ON supply_details.supplier_id=supplier_details.supplier_id CROSS JOIN user ON supplier_details.user_id=user.user_id;";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    List<String> temp = new ArrayList<>();
                    temp.add(resultSet.getString("supplier_id"));
                    temp.add(resultSet.getString("user_name"));
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
                tableView.setDataAdapter(new SimpleTableDataAdapter(SupplierReportActivity.this, arr));
            }
        }
    }
}