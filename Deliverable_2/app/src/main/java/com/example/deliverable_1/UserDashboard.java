package com.example.deliverable_1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;

public class UserDashboard extends AppCompatActivity {

    private Button eventDiscovery;
    private Button clubDiscovery;
    private Button currentCandE; //current clubs and events
    private Button signOut, rateEventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        eventDiscovery = findViewById(R.id.eventDiscovery);
        clubDiscovery = findViewById(R.id.clubDiscovery);
        currentCandE = findViewById(R.id.cevents);
        signOut = findViewById(R.id.signOut);
        rateEventButton = findViewById(R.id.rateEventButton);

        // Set a click listener for the Event Discovery
        eventDiscovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDashboard.this, EventDiscovery.class));
            }
        });

        // Set a click listener for the Club Discovery
        clubDiscovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDashboard.this, ClubDiscovery.class));
            }
        });

        // Set a click listener for the Current Clubs and Events
        currentCandE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDashboard.this, CurrentEventsAndClubs.class));
            }
        });

        rateEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDashboard.this, RateActivity.class));
            }
        });

        // Set a click listener for the Sign Out button
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
        Intent intent = new Intent(UserDashboard.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Finish the current activity to prevent going back to the dashboard using the back button
    }
}
