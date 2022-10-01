package com.example.stocksportfoliomanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    Button btnLogout, btnMenu;
    FirebaseAuth mAuth;
    String userEmail;
    TextView welcomeTextView;

    DatabaseReference reference;
    Query getUserName;

    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnLogout = findViewById(R.id.logoutButton);
        btnMenu = findViewById(R.id.menuButton);
        mAuth = FirebaseAuth.getInstance();
        welcomeTextView = findViewById(R.id.welcomeText);

        userEmail = getIntent().getStringExtra("userEmail");

        reference = FirebaseDatabase.getInstance().getReference("users");
        getUserName = reference.orderByChild("email").equalTo(userEmail);

        getUserName.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot child : snapshot.getChildren()){
                        userName = child.child("username").getValue().toString();
                        welcomeTextView.setText("Welcome\n" + userName);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MenuActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
        }
    }
}