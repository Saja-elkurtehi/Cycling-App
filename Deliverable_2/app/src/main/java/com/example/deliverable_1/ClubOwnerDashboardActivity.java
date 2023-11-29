package com.example.deliverable_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ClubOwnerDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubowner_dashboard);

        TextView welcomeText = findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome (Club Owner)!");

        Button eventCreationButton = findViewById(R.id.btnEventCreation);
        eventCreationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the EventCreationActivity
                startActivity(new Intent(ClubOwnerDashboardActivity.this, EventCreationActivity.class));
            }
        });

        Button routePlanningButton = findViewById(R.id.btnRoutePlanning);
        routePlanningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the RoutePlanningActivity
                startActivity(new Intent(ClubOwnerDashboardActivity.this, RoutePlanningActivity.class));
            }
        });

        Button registrationManagementButton = findViewById(R.id.btnRegistrationManagement);
        registrationManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the RegistrationManagementActivity
                startActivity(new Intent(ClubOwnerDashboardActivity.this, RegistrationManagementActivity.class));
            }
        });

        Button resultsAndAwardsButton = findViewById(R.id.btnResultsAndAwards);
        resultsAndAwardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the ResultsAndAwardsActivity
                startActivity(new Intent(ClubOwnerDashboardActivity.this, ResultsAndAwardsActivity.class));
            }
        });
    }
}
