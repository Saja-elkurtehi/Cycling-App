package com.example.deliverable_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EventCreationActivity extends AppCompatActivity {

    Spinner eventSelectionSpinner;
    TextView route, difficultyLevel, participantLimit;

    Button createEventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_creation);

        // Initialize variables
        eventSelectionSpinner = findViewById(R.id.eventSelectionSpinner);
        route = findViewById(R.id.route);
        difficultyLevel = findViewById(R.id.difficultyLevel);
        participantLimit = findViewById(R.id.participantLimit);

        createEventButton = findViewById(R.id.btnCreateEvent);

        // Set click listener for the event creation button
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create the event (NOT IMPLEMENTED)
            }
        });
    }
}
