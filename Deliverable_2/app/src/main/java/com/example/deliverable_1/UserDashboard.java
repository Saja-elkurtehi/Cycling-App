package com.example.deliverable_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class UserDashboard extends AppCompatActivity {
    
    private Button eventDiscovery;
    private Button clubDiscovery;

    private Button currentCandE; //current clubs and events

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        eventDiscovery = findViewById(R.id.eventDiscovery);
        clubDiscovery = findViewById(R.id.clubDiscovery);
        currentCandE = findViewById(R.id.cevents);

        // Set a click listener for the Event Discovery
        eventDiscovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDashboard.this, EventDiscovery.class));
            }
        });

        // Set a click listener for the Event Discovery
        clubDiscovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDashboard.this, ClubDiscovery.class));
            }
        });

        currentCandE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDashboard.this, CurrentEventsAndClubs.class));
            }
        });
    }

    
}