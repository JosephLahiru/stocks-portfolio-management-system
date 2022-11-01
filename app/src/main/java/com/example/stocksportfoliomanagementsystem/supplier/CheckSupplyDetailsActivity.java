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

public class CheckSupplyDetailsActivity extends AppCompatActivity {

    TableView tableView;
    Button backToSupplireManagementButton;
    Connection connection;
    String data[][];
    String userEmail;
    int userId;

    private static final String URL = "jdbc:mysql://152.70.158.151:3306/spms";
    private static final String USER = "root";
    private static final String PASSWORD = "amres";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_supply_details);
        userEmail = getIntent().getStringExtra("userEmail");

        backToSupplireManagementButton = (Button) findViewById(R.id.vbsm2);
        backToSupplireManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckSupplyDetailsActivity.this, SupplierManagementActivity.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
            }
        });

        tableView = findViewById(R.id.tableViewCheckSupplyDetailsActivity);
        tableView.setColumnCount(5);
        String headers[] = {"Product ID","Product Name", "Brand","Quantity","Price Per Item"};
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
                String query = "SELECT supplier_details.supplier_id  FROM user CROSS JOIN supplier_details WHERE user.user_email='"+userEmail+"' and supplier_details.user_id = user.user_id;";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet2 = preparedStatement.executeQuery();

                while (resultSet2.next()){
                    userId = resultSet2.getInt("supplier_id");
                    System.out.println(userId);
                }
                String sql = "SELECT product.product_id, product.product_name, supply_details.brand, supply_details.quantity, supply_details.cost_of_item FROM product CROSS JOIN supply_details WHERE product.product_id=supply_details.product_id AND supply_details.supplier_id='"+userId+"';";
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

            //String[][] data = {{"1", "2", "3", "4"},{"5", "6", "7", "8"}};

            String[][] arr = result.stream()
                    .map(l -> l.stream().toArray(String[]::new))
                    .toArray(String[][]::new);


            if (!result.isEmpty()) {
                tableView.setDataAdapter(new SimpleTableDataAdapter(CheckSupplyDetailsActivity.this, arr));
            }

        }
    }
}