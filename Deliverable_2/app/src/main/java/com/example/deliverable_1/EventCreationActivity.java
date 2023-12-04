package com.example.deliverable_1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventCreationActivity extends AppCompatActivity {

    AutoCompleteTextView eventSelectionAutoComplete;
    EditText route, difficultyLevel, participantLimit;

    Button createEventButton;

    DatabaseReference eventsReference; // Reference to the "Events" node in the database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_creation);

        // Initialize Firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        // Check if the user is authenticated
        if (user != null) {
            // Use the UID of the authenticated user
            String uid = user.getUid();

            // Set the reference to "Events" node under the UID of the authenticated user
            eventsReference = firebaseDatabase.getReference("Events").child(uid);

            eventSelectionAutoComplete = findViewById(R.id.eventSelectionAutoComplete);
            route = findViewById(R.id.route);
            difficultyLevel = findViewById(R.id.difficultyLevel);
            participantLimit = findViewById(R.id.participantLimit);

            createEventButton = findViewById(R.id.btnCreateEvent);

            // Set up the event types AutoCompleteTextView
            setUpEventTypesAutoComplete();

            // Set click listener for the event creation button
            createEventButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createEvent();
                }
            });
        }
    }

    private void setUpEventTypesAutoComplete() {
        // Read events from the database and populate the AutoCompleteTextView with event types
        eventsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> eventTypes = new ArrayList<>();

                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                    String eventType = eventSnapshot.child("eventType").getValue(String.class);
                    if (eventType != null && !eventTypes.contains(eventType)) {
                        eventTypes.add(eventType);
                    }
                }

                // Create an ArrayAdapter using the eventTypes list
                ArrayAdapter<String> eventTypesAdapter = new ArrayAdapter<>(
                        EventCreationActivity.this,
                        android.R.layout.simple_dropdown_item_1line,
                        eventTypes
                );

                // Apply the adapter to the AutoCompleteTextView
                eventSelectionAutoComplete.setAdapter(eventTypesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                Toast.makeText(EventCreationActivity.this, "Failed to retrieve event types", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createEvent() {
        // Retrieve data from UI elements
        String selectedEvent = eventSelectionAutoComplete.getText().toString();
        String eventRouteText = route.getText().toString();
        String eventDifficultyText = difficultyLevel.getText().toString();
        String participantLimitText = participantLimit.getText().toString();

        // Check if any of the text fields is empty
        if (eventRouteText.isEmpty() || eventDifficultyText.isEmpty() || participantLimitText.isEmpty()) {
            Toast.makeText(EventCreationActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create an Event object with the provided data
        Event event = setEvent(selectedEvent, eventRouteText, eventDifficultyText, participantLimitText);

        // Save the event data to the database under the UID of the authenticated user
        saveEventToDatabase(event);
    }

    private Event setEvent(String eventType, String eventRoute, String eventDifficulty, String participantLimit) {
        Event event = new Event();
        event.setEventName(eventType);
        event.setEventRoute(eventRoute);
        event.setEventDifficulty(eventDifficulty);
        event.setParticipantLimit(participantLimit);
        return event;
    }

    private void saveEventToDatabase(Event event) {
        // Save the event data to the database under the UID of the authenticated user
        eventsReference.push().setValue(event).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Optionally, show a confirmation message
                    Toast.makeText(EventCreationActivity.this, "Event created successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle the error
                    Toast.makeText(EventCreationActivity.this, "Failed to create event", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
