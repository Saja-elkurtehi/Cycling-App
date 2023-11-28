package com.example.deliverable_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ClubOwnerDashboardActivity {
    public class AdminDashboardActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_clubowner_dashboard);

            Button eventCreationButton = findViewById(R.id.btnEventCreation);

            // / Set click listeners for the buttons
            eventCreationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Navigate to the EventManagementActivity
                    startActivity(new Intent(com.example.deliverable_1.ClubOwnerDashboardActivity.this, EventCreationActivity.class));
                }
            });

            Button routePlanningButton = findViewById(R.id.btnRoutePlanning);
            routePlanningButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Navigate to the Account Management part of the admin panel
                    startActivity(new Intent(com.example.deliverable_1.ClubOwnerDashboardActivity.this, RoutePlanningActivity.class));
                }
            });

            Button registrationManagementButton = findViewById(R.id.btnRegistrationManagement);
            routePlanningButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Navigate to the Account Management part of the admin panel
                    startActivity(new Intent(com.example.deliverable_1.ClubOwnerDashboardActivity.this, RegistrationManagementActivity.class));
                }
            });

            Button resultsAndAwardsButton = findViewById(R.id.btnRoutePlanning);
            routePlanningButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Navigate to the Account Management part of the admin panel
                    startActivity(new Intent(com.example.deliverable_1.ClubOwnerDashboardActivity.this, ResultsAndAwardsActivity.class));
                }
            });
        }
    }
}
