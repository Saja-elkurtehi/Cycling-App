package com.example.deliverable_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ClubOwnerDashboardActivity extends AppCompatActivity {
    private Button signOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubowner_dashboard);

        //TextView welcomeText = findViewById(R.id.welcomeText);
        //welcomeText.setText("Welcome (Club Owner)!");

        Button routePlanningButton = findViewById(R.id.btnRoutePlanning);
        Button eventCreationButton = findViewById(R.id.btnEventCreation);
        Button registrationManagementButton = findViewById(R.id.btnRegistrationManagement);
        Button resultsAndAwardsButton = findViewById(R.id.btnResultsAndAwards);
        Button editClubProfile = findViewById(R.id.btnEditClubProfile);
        signOut = findViewById(R.id.signOut);
        // / Set click listeners for the buttons
        eventCreationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the EventCreationActivity
                startActivity(new Intent(ClubOwnerDashboardActivity.this, EventCreationActivity.class));
            }
        });


        routePlanningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the RoutePlanningActivity
                startActivity(new Intent(ClubOwnerDashboardActivity.this, RoutePlanningActivity.class));
            }
        });


        registrationManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the RegistrationManagementActivity
                startActivity(new Intent(ClubOwnerDashboardActivity.this, RegistrationManagementActivity.class));
            }
        });



        resultsAndAwardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the ResultsAndAwardsActivity
                startActivity(new Intent(ClubOwnerDashboardActivity.this, ResultsAndAwardsActivity.class));
            }
        });

        editClubProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the EditClubProfileActivity
                startActivity(new Intent(ClubOwnerDashboardActivity.this, EditClubProfileActivity.class));
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
        Intent intent = new Intent(ClubOwnerDashboardActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Finish the current activity to prevent going back to the dashboard using the back button
    }
}

