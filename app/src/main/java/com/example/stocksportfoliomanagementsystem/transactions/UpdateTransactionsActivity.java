package com.example.stocksportfoliomanagementsystem.transactions;

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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.stocksportfoliomanagementsystem.R;
import com.example.stocksportfoliomanagementsystem.supplier.CheckSupplyDetailsActivity;
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
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class UpdateTransactionsActivity extends AppCompatActivity {
    Button backToFinancialManagementButton, updateTransactionButton, searchInvoiceButton;
    Spinner dropdown;
    TableView tableView;
    Connection connection;
    String data[][];
    EditText invoiceID,companyName,productName,productDiscription,productQuantity,totalPrice;
    String userEmail;

    private static final String URL = "jdbc:mysql://152.70.158.151:3306/spms";
    private static final String USER = "root";
    private static final String PASSWORD = "amres";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_transactions);
        userEmail = getIntent().getStringExtra("userEmail");
        new InfoAsyncTask().execute();
        backToFinancialManagementButton = (Button) findViewById(R.id.ubfm2);
        backToFinancialManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateTransactionsActivity.this, FinancialManagementActivity.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
                finish();
            }
        });
        updateTransactionButton = (Button) findViewById(R.id.btnut);
        updateTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new UpdateTransactionsTask().execute();
                invoiceID.getText().clear();
                companyName.getText().clear();
                productName.getText().clear();
                productDiscription.getText().clear();
                productQuantity.getText().clear();
                totalPrice.getText().clear();
            }
        });
        searchInvoiceButton = (Button) findViewById(R.id.btnsfi);
        searchInvoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SearchInvoiceTask().execute();
            }
        });

        dropdown = findViewById(R.id.spinneru);
        invoiceID = (EditText) findViewById(R.id.editTextInvoiceID);
        companyName = (EditText) findViewById(R.id.editTextCompanyNameu);
        productName = (EditText) findViewById(R.id.editTextProcuctNameu);
        productDiscription = (EditText) findViewById(R.id.editTextpdiscriptionu);
        productQuantity = (EditText) findViewById(R.id.editTextProductQuantityu);
        totalPrice = (EditText) findViewById(R.id.editTextTotalPriceu);
        tableView = findViewById(R.id.tableViewU);
        tableView.setColumnCount(8);
        String headers[] = {"Invoice ID", "Date", "Company", "Product", "Product Discription", "Product Quantity", "Total Price", "Type Of Trascation"};
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, headers));

    }

    @SuppressLint("StaticFieldLeak")
    public class SearchInvoiceTask extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... voids) {
            List<String> invoices = new ArrayList<>();
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                String invoiceID_ = invoiceID.getText().toString();
                String sql = "SELECT * FROM spms.transactions WHERE invoiceid ="+invoiceID_+";";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                System.out.println(sql);

                while (resultSet.next()) {
                    String invoiceIDU_ = resultSet.getString("invoiceid");
                    String companyName_ = resultSet.getString("cname");
                    String productName_ = resultSet.getString("pname");
                    String productDiscription_ = resultSet.getString("discription");
                    String productQuantity_ = resultSet.getString("pquantity");
                    String totalPrice_ = resultSet.getString("total");
                    String transactionType = resultSet.getString("ttype");
                    System.out.println(companyName_);

                    companyName.setText(companyName_);
                    productName.setText(productName_);
                    productDiscription.setText(productDiscription_);
                    productQuantity.setText(productQuantity_);
                    totalPrice.setText(totalPrice_);

                }

            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading information", e);
            }
            return invoices;
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class UpdateTransactionsTask extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... voids) {
            List<String> invoices = new ArrayList<>();
            try {

                String invoiceID_ = invoiceID.getText().toString();
                String companyName_ = companyName.getText().toString();
                String productName_ = productName.getText().toString();
                String productDiscription_ = productDiscription.getText().toString();
                String productQuantity_ = productQuantity.getText().toString();
                String totalPrice_ = totalPrice.getText().toString();
                String transactionType = dropdown.getSelectedItem().toString();

                String sql = "UPDATE `spms`.`transactions` SET `cname` = '"+companyName_+"', `pname` = '"+productName_+"', `discription` = '"+productDiscription_+"', `pquantity` = '"+productQuantity_+"', `total` = '"+totalPrice_+"', `ttype` = '"+transactionType+"' WHERE (`invoiceid` = '"+invoiceID_+"');";

                System.out.println(sql);

                Statement st1 = connection.createStatement();

                if (!st1.execute(sql)) {
                    System.out.println("Data updated successfully");

                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(UpdateTransactionsActivity.this, "Data updated Successfully.", Toast.LENGTH_SHORT);
                            toast.show();
                            new InfoAsyncTask().execute();
                        }
                    });

                } else {
                    System.out.println("Data upload failed.");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(UpdateTransactionsActivity.this, "Data Upload Failed.", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });
                }
            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading information", e);
            }
            return invoices;
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<Void, Void, List<List<String>>> {
        @Override
        protected List<List<String>> doInBackground(Void... voids) {
            List<List<String>> invoices = new ArrayList<>();
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);

                String sql = "SELECT * FROM spms.transactions";
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
                    invoices.add(temp);
                }
            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading information", e);
            }

            return invoices;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(List<List<String>> result) {

            String[][] arr = result.stream()
                    .map(l -> l.stream().toArray(String[]::new))
                    .toArray(String[][]::new);


            if (!result.isEmpty()) {
                tableView.setDataAdapter(new SimpleTableDataAdapter(UpdateTransactionsActivity.this, arr));
            }
        }
    }
}