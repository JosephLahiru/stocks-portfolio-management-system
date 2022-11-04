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

public class DeleteDiscountsDetailsActivity extends AppCompatActivity {

    String userEmail, userType;
    Button deleteDiscountsButton,backToSupplireManagementButton;
    Connection connection;
    EditText discountid;
    int supplier_id;
    TableView tableView;
    String data[][];

    private static final String URL = "jdbc:mysql://152.70.158.151:3306/spms";
    private static final String USER = "root";
    private static final String PASSWORD = "amres";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_discounts_details);

        userEmail = getIntent().getStringExtra("userEmail");
        userType = getIntent().getStringExtra("userType");

        discountid = (EditText) findViewById(R.id.editTextDiscountIDDSD);

        tableView = findViewById(R.id.tableViewDeleteDiscountsDetailsActivity);
        tableView.setColumnCount(4);
        String headers[] = {"Discount ID","Product Name", "Margin","Discount"};
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, headers));

        new InfoAsyncTask().execute();

        deleteDiscountsButton = (Button) findViewById(R.id.btnDeleteDiscountsDSD);
        deleteDiscountsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DeleteDiscountsTask().execute();
            }
        });

        backToSupplireManagementButton = (Button) findViewById(R.id.vbsm9);
        backToSupplireManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeleteDiscountsDetailsActivity.this, SupplierManagementActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userType", userType);
                startActivity(intent);
                finish();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    public class DeleteDiscountsTask extends AsyncTask<Void, Void, List<String>> {
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

                String discountid_ = discountid.getText().toString();

                String sql = "DELETE FROM `spms`.`supplier_discounts` WHERE (`discount_id` = '"+discountid_+"');";

                System.out.println(sql);

                Statement st1 = connection.createStatement();

                if (!st1.execute(sql)) {
                    System.out.println("Data Deleted successfully");

                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(DeleteDiscountsDetailsActivity.this, "Data Deleted Successfully.", Toast.LENGTH_SHORT);
                            toast.show();
                            new InfoAsyncTask().execute();
                        }
                    });

                    discountid.getText().clear();

                } else {
                    System.out.println("Data Delete failed.");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            final Toast toast = Toast.makeText(DeleteDiscountsDetailsActivity.this, "Data Delete Failed.", Toast.LENGTH_SHORT);
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
                String query = "SELECT supplier_details.supplier_id  FROM user CROSS JOIN supplier_details WHERE user.user_email='"+userEmail+"' and supplier_details.user_id = user.user_id;";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet2 = preparedStatement.executeQuery();

                while (resultSet2.next()){
                    supplier_id = resultSet2.getInt("supplier_id");
                    System.out.println(supplier_id);
                }
                String sql = "SELECT supplier_discounts.discount_id, product.product_name, supplier_discounts.margin, supplier_discounts.discount FROM supplier_discounts CROSS JOIN product WHERE supplier_discounts.product_id=product.product_id AND supplier_discounts.supplier_id="+supplier_id+";";
                System.out.println(sql);
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    List<String> temp = new ArrayList<>();
                    temp.add(resultSet.getString("discount_id"));
                    temp.add(resultSet.getString("product_name"));
                    temp.add(resultSet.getString("margin"));
                    temp.add(resultSet.getString("discount"));
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
                tableView.setDataAdapter(new SimpleTableDataAdapter(DeleteDiscountsDetailsActivity.this, arr));
            }

        }
    }
}