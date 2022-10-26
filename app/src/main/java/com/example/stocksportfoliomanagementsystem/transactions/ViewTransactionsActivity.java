package com.example.stocksportfoliomanagementsystem.transactions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.RequiresApi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.AsyncTask;
import android.util.Log;
import android.annotation.SuppressLint;
import android.os.Build;

import com.example.stocksportfoliomanagementsystem.supplier.FinancialManagementActivity;
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

public class ViewTransactionsActivity extends AppCompatActivity {
    TableView tableView;
    Button backToFinancialManagementButton;
    Connection connection;
    String data[][];

    private static final String URL = "jdbc:mysql://152.70.158.151:3306/spms";
    private static final String USER = "root";
    private static final String PASSWORD = "amres";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transactions);
        backToFinancialManagementButton = (Button) findViewById(R.id.vbfm);
        backToFinancialManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewTransactionsActivity.this, FinancialManagementActivity.class));
                finish();
            }
        });
        tableView = findViewById(R.id.tableView);
        tableView.setColumnCount(8);
        String headers[] = {"Invoice ID", "Date", "Company", "Product", "Product Discription", "Product Quantity", "Total Price", "Type Of Trascation"};
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

                String sql = "SELECT * FROM transactions";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    List<String> temp = new ArrayList<>();
                    temp.add(resultSet.getString("invoiceid"));
                    temp.add(resultSet.getString("date"));
                    temp.add(resultSet.getString("cname"));
                    temp.add(resultSet.getString("pname"));
                    temp.add(resultSet.getString("discription"));
                    temp.add(resultSet.getString("pquantity"));
                    temp.add(resultSet.getString("total"));
                    temp.add(resultSet.getString("ttype"));
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
                tableView.setDataAdapter(new SimpleTableDataAdapter(ViewTransactionsActivity.this, arr));
            }
        }
    }
}