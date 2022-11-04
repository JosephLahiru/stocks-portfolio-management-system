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
import com.example.stocksportfoliomanagementsystem.transactions.ViewTransactionsActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class FinalMonthlyReportActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_final_monthly_report);

        userEmail = getIntent().getStringExtra("userEmail");
        userType = getIntent().getStringExtra("userType");

        backToReportsButton = (Button) findViewById(R.id.fmrbackbtn);
        backToReportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinalMonthlyReportActivity.this, ReportsActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });

        tableView = findViewById(R.id.tableViewFinalMonthlyReport);
        tableView.setColumnCount(5);
        String headers[] = {"Product ID", "Product Name", "Product Price", "Current Stock", "Stock Required"};
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

                String sql = "SELECT product.product_id, product.product_name, product.product_price, product_stock.stock_qty, stock_required.stock_needed FROM product CROSS JOIN product_stock ON product.product_id=product_stock.product_id CROSS JOIN stock_required ON product.product_id=stock_required.product_id;";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    List<String> temp = new ArrayList<>();
                    temp.add(resultSet.getString("product_id"));
                    temp.add(resultSet.getString("product_name"));
                    temp.add(resultSet.getString("product_price"));
                    temp.add(resultSet.getString("stock_qty"));
                    temp.add(resultSet.getString("stock_needed"));
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
                tableView.setDataAdapter(new SimpleTableDataAdapter(FinalMonthlyReportActivity.this, arr));
            }
        }
    }
}