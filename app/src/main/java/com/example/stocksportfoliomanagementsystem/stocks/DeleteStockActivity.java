package com.example.stocksportfoliomanagementsystem.stocks;

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

public class DeleteStockActivity extends AppCompatActivity {

    TableView tableView;
    Connection connection;
    String data[][];
    Button backBtn, deleteBtn;

    EditText deleteDataID;

    private static final String URL = "jdbc:mysql://152.70.158.151:3306/spms";
    private static final String USER = "root";
    private static final String PASSWORD = "amres";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_stock);

        deleteDataID = (EditText) findViewById(R.id.etStockID);

        backBtn = (Button) findViewById(R.id.backButton3);
        deleteBtn = (Button) findViewById(R.id.deleteButton);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DeleteStockTask().execute();

                deleteDataID.getText().clear();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeleteStockActivity.this, StockManagementActivity.class));
                finish();
            }
        });

        tableView = findViewById(R.id.table_data_view_delete);
        String headers[] = {"PS ID", "Arrived Date", "Stock Qty", "Product ID"};

        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, headers));

        new InfoAsyncTask().execute();
    }

    @SuppressLint("StaticFieldLeak")
    public class DeleteStockTask extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... voids) {
            List<String> products = new ArrayList<>();
            try {

                String stockID = deleteDataID.getText().toString();

                String sql = "DELETE FROM `product_stock` WHERE `product_stock_id` = '" + stockID + "'; ";

                System.out.println(sql);

                Statement st1 = connection.createStatement();

                if (!st1.execute(sql)) {
                    System.out.println("Data deleted successfully");

                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(DeleteStockActivity.this, "Data Deleted Successfully.", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });

                } else {
                    System.out.println("Data deletion failed.");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(DeleteStockActivity.this, "Data Deletion Failed.", Toast.LENGTH_SHORT);
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

    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<Void, Void, List<List<String>>> {
        @Override
        protected List<List<String>> doInBackground(Void... voids) {
            List<List<String>> products = new ArrayList<>();
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);

                String sql = "SELECT * FROM product_stock";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    List<String> temp = new ArrayList<>();
                    temp.add(resultSet.getString("product_stock_id"));
                    temp.add(resultSet.getString("arrived_date"));
                    temp.add(resultSet.getString("stock_qty"));
                    temp.add(resultSet.getString("product_id"));
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
                tableView.setDataAdapter(new SimpleTableDataAdapter(DeleteStockActivity.this, arr));
            }
        }
    }
}