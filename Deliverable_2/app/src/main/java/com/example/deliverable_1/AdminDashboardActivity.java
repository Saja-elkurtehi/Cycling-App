package com.example.deliverable_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AdminDashboardActivity extends AppCompatActivity {

    private Button eventManagementButton, accountManagementButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // Initialize UI components
        eventManagementButton = findViewById(R.id.btnEventManagement);

        // Set click listeners for the buttons
        eventManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the EventManagementActivity
                Intent intent = new Intent(AdminDashboardActivity.this, EventManagementActivity.class);
                startActivity(intent);
            }
        });

        accountManagementButton = findViewById(R.id.btnAccountManagement);
        accountManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the Account Management part of the admin panel
                Intent intent = new Intent(AdminDashboardActivity.this, AccountManagementActivity.class);
                startActivity(intent);
            }
        });

    }
}
