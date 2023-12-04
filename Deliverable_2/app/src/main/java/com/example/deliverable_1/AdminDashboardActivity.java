package com.example.deliverable_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class AdminDashboardActivity extends AppCompatActivity {
    private Button signOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        Button eventManagementButton = findViewById(R.id.btnEventManagement);
        signOut = findViewById(R.id.signOut);
        // / Set click listeners for the buttons
        eventManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the EventManagementActivity
                startActivity(new Intent(AdminDashboardActivity.this, EventManagementActivity.class));
            }
        });

        Button accountManagementButton = findViewById(R.id.btnAccountManagement);
        accountManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Account Management part of the admin panel
                startActivity(new Intent(AdminDashboardActivity.this, AccountManagementActivity.class));
            }
        });
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOutUser();
            }
        });
    }

    private void signOutUser() {
        // Sign out the user using Firebase Authentication
        FirebaseAuth.getInstance().signOut();

        // Redirect the user to the login page
        Intent intent = new Intent(AdminDashboardActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Finish the current activity to prevent going back to the dashboard using the back button
    }
}

