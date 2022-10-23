package com.example.stocksportfoliomanagementsystem;

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

public class DeleteTransactionsActivity extends AppCompatActivity {

    Button backToFinancialManagementButton, deleteTransactionButton;
    TableView tableView;
    Connection connection;
    String data[][];
    EditText deleteDataID;

    private static final String URL = "jdbc:mysql://152.70.158.151:3306/spms";
    private static final String USER = "root";
    private static final String PASSWORD = "amres";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_transactions);
        tableView = findViewById(R.id.tableViewD);
        tableView.setColumnCount(8);
        String headers[] = {"Invoice ID", "Date", "Company", "Product", "Product Discription", "Product Quantity", "Total Price", "Type Of Trascation"};
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, headers));
        new InfoAsyncTask().execute();
        backToFinancialManagementButton = (Button) findViewById(R.id.dbfm);
        backToFinancialManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeleteTransactionsActivity.this, FinancialManagementActivity.class));
                finish();
            }
        });
        deleteTransactionButton = (Button) findViewById(R.id.btnrmt);
        deleteTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DeleteStockTask().execute();

                deleteDataID.getText().clear();
            }
        });
        deleteDataID = (EditText) findViewById(R.id.editTextInvoiceIDD);

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
                tableView.setDataAdapter(new SimpleTableDataAdapter(DeleteTransactionsActivity.this, arr));
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class DeleteStockTask extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... voids) {
            List<String> products = new ArrayList<>();
            try {

                String invoiceID = deleteDataID.getText().toString();

                String sql = "DELETE FROM `transactions` WHERE `invoiceid` = '" + invoiceID + "'; ";

                System.out.println(sql);

                Statement st1 = connection.createStatement();

                if (!st1.execute(sql)) {
                    System.out.println("Data deleted successfully");

                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(DeleteTransactionsActivity.this, "Data Deleted Successfully.", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });

                } else {
                    System.out.println("Data deletion failed.");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(DeleteTransactionsActivity.this, "Data Deletion Failed.", Toast.LENGTH_SHORT);
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