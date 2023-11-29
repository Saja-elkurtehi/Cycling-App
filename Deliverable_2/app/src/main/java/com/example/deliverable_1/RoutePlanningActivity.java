package com.example.deliverable_3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RoutePlanningActivity extends AppCompatActivity {

    // Initialize views
    private EditText destinationEditText, elevationEditText, landmarksEditText;
    private Button submitRouteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_planning);

        // Initialize views
        destinationEditText = findViewById(R.id.editTextDestination);
        elevationEditText = findViewById(R.id.editTextElevation);
        landmarksEditText = findViewById(R.id.editTextLandmarks);
        submitRouteButton = findViewById(R.id.buttonPlanRoute);

        // Set click listener for the submit button
        submitRouteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitRoute();
            }
        });
    }

    private void submitRoute() {
        // Get values from EditText fields
        String destination = destinationEditText.getText().toString().trim();
        String elevation = elevationEditText.getText().toString().trim();
        String landmarks = landmarksEditText.getText().toString().trim();

        // Check if fields are not empty
        if (!destination.isEmpty() && !elevation.isEmpty() && !landmarks.isEmpty()) {
            // Process the route data as needed (e.g., display in a Toast)
            String routeDetails = "Destination: " + destination +
                    "\nElevation: " + elevation +
                    "\nLandmarks: " + landmarks;

            // Display a success message or perform any other actions
            Toast.makeText(this, "Route submitted successfully:\n" + routeDetails, Toast.LENGTH_SHORT).show();

            // Clear EditText fields after submission
            destinationEditText.setText("");
            elevationEditText.setText("");
            landmarksEditText.setText("");
        } else {
            // Display an error message if any field is empty
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
        }
    }
}
