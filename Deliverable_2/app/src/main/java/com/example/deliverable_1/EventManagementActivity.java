package com.example.deliverable_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class EventManagementActivity extends AppCompatActivity {

    private EditText eventTypeEditText, eventDetailsEditText;
    private CheckBox under18CheckBox, eighteenToTwentyFiveCheckBox, twentySixToSixtyFiveCheckBox, over65CheckBox;
    private CheckBox beginnerCheckBox, intermediateCheckBox, advancedCheckBox, expertCheckBox;
    private CheckBox slowCheckBox, moderateCheckBox, fastCheckBox, extremeCheckBox;
    private Button addEventButton, editEventButton, deleteEventButton, backToAdminDashboardButton;
    private ListView eventListView;
    private List<String> events;
    private ArrayAdapter<String> eventAdapter;

    private Integer selectedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_management);

        // Initialize UI components
        // EditText for event type and details
        eventTypeEditText = findViewById(R.id.eventType);
        eventDetailsEditText = findViewById(R.id.eventDetails);

        // Checkboxes for participant requirements
        under18CheckBox = findViewById(R.id.under18);
        eighteenToTwentyFiveCheckBox = findViewById(R.id.eighteenToTwentyFive);
        twentySixToSixtyFiveCheckBox = findViewById(R.id.twentySixToSixtyFive);
        over65CheckBox = findViewById(R.id.over65);

        beginnerCheckBox = findViewById(R.id.beginner);
        intermediateCheckBox = findViewById(R.id.intermediate);
        advancedCheckBox = findViewById(R.id.advanced);
        expertCheckBox = findViewById(R.id.expert);

        slowCheckBox = findViewById(R.id.slow);
        moderateCheckBox = findViewById(R.id.moderate);
        fastCheckBox = findViewById(R.id.fast);
        extremeCheckBox = findViewById(R.id.extreme);

        // Buttons for adding, editing, and deleting events
        addEventButton = findViewById(R.id.btnAddEvent);
        editEventButton = findViewById(R.id.btnEditEvent);
        deleteEventButton = findViewById(R.id.btnDeleteEvent);
        backToAdminDashboardButton = findViewById(R.id.btnBackToAdminDashboard);

        // Initialize the list of events
        events = new ArrayList<>();

        // Initialize the adapter for the event list view
        eventAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, events);

        // Set the adapter for the eventListView
        eventListView = findViewById(R.id.eventListView);
        eventListView.setAdapter(eventAdapter);

        // Set click listener for the "Add Event" button
        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eventType = eventTypeEditText.getText().toString();
                String eventDetails = eventDetailsEditText.getText().toString();

                if (!eventType.isEmpty() && !eventDetails.isEmpty()) {
                    // Build the participant requirements string

                    //The setEvent method is accessed externally
                    String eventDescription = setEvent(eventType, eventDetails);

                    // Add the event to the list and update the list view
                    events.add(eventDescription);
                    eventAdapter.notifyDataSetChanged();

                    // Clear input fields and checkboxes
                    clearInputFields();
                } else {
                    Toast.makeText(EventManagementActivity.this, "Please enter event type and details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set click listener for the "Edit Event" button
        editEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatedEventType = eventTypeEditText.getText().toString();
                String updatedEventDetails = eventDetailsEditText.getText().toString();

                if (!updatedEventType.isEmpty() && !updatedEventDetails.isEmpty()) {
                    // Build the participant requirements string


                    //The setEvent method is accessed externally
                    String eventDescription = setEvent(updatedEventType, updatedEventDetails);

                    // Add the event to the list and update the list view
                    events.set(selectedIndex, eventDescription);
                    // Notify the adapter about the data change
                    eventAdapter.notifyDataSetChanged();
                    // Clear input fields and checkboxes
                    clearInputFields();
                } else {
                    Toast.makeText(EventManagementActivity.this, "Please enter event type and details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set click listener for the "Delete Event" button
        deleteEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String eventType = eventTypeEditText.getText().toString();
                String eventDetails = eventDetailsEditText.getText().toString();
                if (eventType.isEmpty() || eventDetails.isEmpty()){
                    Toast.makeText(EventManagementActivity.this, "No event has been selected to delete", Toast.LENGTH_SHORT).show();
                }else{
                    String eventDescription = setEvent(eventType, eventDetails);
                    events.remove(selectedIndex);
                    eventAdapter.remove(eventDescription);
                }
            }
        });

        backToAdminDashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to the AdminDashboardActivity
                Intent intent = new Intent(EventManagementActivity.this, AdminDashboardActivity.class);
                startActivity(intent);
            }
        });

        // Edit Event: This is the first step in repopulating the EventManagement Page
        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle item click, e.g., repopulate UI components
                selectedIndex = position;
                clearInputFields();
                repopulateUI(position);
            }
        });



    }

    //This is a method that will be used for both the add and edit methods
    private String setEvent(String eventType, String eventDetails) {
        StringBuilder participantRequirements = new StringBuilder("Participant Requirements: ");
        if (under18CheckBox.isChecked()) {
            participantRequirements.append("Under 18, ");
        }
        if (eighteenToTwentyFiveCheckBox.isChecked()) {
            participantRequirements.append("18-25, ");
        }
        if (twentySixToSixtyFiveCheckBox.isChecked()) {
            participantRequirements.append("26-65, ");
        }
        if (over65CheckBox.isChecked()) {
            participantRequirements.append("66+, ");
        }

        // Build the skill level string
        StringBuilder skillLevel = new StringBuilder("Skill Level: ");
        if (beginnerCheckBox.isChecked()) {
            skillLevel.append("Beginner, ");
        }
        if (intermediateCheckBox.isChecked()) {
            skillLevel.append("Intermediate, ");
        }
        if (advancedCheckBox.isChecked()) {
            skillLevel.append("Advanced, ");
        }
        if (expertCheckBox.isChecked()) {
            skillLevel.append("Expert, ");
        }

        // Build the pace string
        StringBuilder pace = new StringBuilder("Pace: ");
        if (slowCheckBox.isChecked()) {
            pace.append("Slow (10-15 mph), ");
        }
        if (moderateCheckBox.isChecked()) {
            pace.append("Moderate (15-20 mph), ");
        }
        if (fastCheckBox.isChecked()) {
            pace.append("Fast (20+ mph), ");
        }
        if (extremeCheckBox.isChecked()) {
            pace.append("Extreme (25+ mph), ");
        }

        // Remove trailing comma and space from the strings
        participantRequirements.setLength(participantRequirements.length() - 2);
        skillLevel.setLength(skillLevel.length() - 2);
        pace.setLength(pace.length() - 2);

        // Create the event description
        String eventDescription = eventType + "\n" + eventDetails + "\n" +
                participantRequirements.toString() + "\n" +
                skillLevel.toString() + "\n" +
                pace.toString();
        return eventDescription;
    }

    // Edit Event: This is the method used to UI components (so the text fields and boxes)
    private void repopulateUI(int position) {
        // Get the selected event from the list
        String selectedEvent = events.get(position);

        String[] parts = selectedEvent.split("\n");


        // Parse the selected event to extract information
        String parsedEventType = parts[0].trim();
        String parsedEventDetails = parts[1].trim();
        String ageOptions = parts[2].trim();
        String levelOptions = parts[3].trim();
        String paceOptions = parts[4].trim();

        //Now separating ageOptions, levelOptions, and paceOptions
        //ageOptions
        String [] selectAge = ageOptions.substring(ageOptions.indexOf(":") + 1).split(",");
        String [] selectLevel = levelOptions.substring(levelOptions.indexOf(":") + 1).split(",");
        String [] selectPace = paceOptions.substring(paceOptions.indexOf(":") + 1).split(",");

        // Update UI components
        eventTypeEditText.setText(parsedEventType);
        eventDetailsEditText.setText(parsedEventDetails);

        // Checkboxes should be checked based on parsed information
        for (String s: selectAge){
            s = s.trim();
            CheckBox checkBox;
            if (s.equals("Under 18")){
                under18CheckBox.setChecked(true);
            }else if (s.equals("18-25")){
                eighteenToTwentyFiveCheckBox.setChecked(true);
            }else if (s.equals("26-65")){
                twentySixToSixtyFiveCheckBox.setChecked(true);
            }else if (s.equals("66+")){
                over65CheckBox.setChecked(true);
            }
        }

        for (String s: selectLevel){
            s = s.trim();
            CheckBox checkBox;
            if (s.equals("Beginner")){
                beginnerCheckBox.setChecked(true);
            }else if (s.equals("Intermediate")){
                intermediateCheckBox.setChecked(true);
            }else if (s.equals("Advanced")){
                advancedCheckBox.setChecked(true);
            }else if (s.equals("Expert")){
                expertCheckBox.setChecked(true);
            }
        }

        for (String s: selectPace){
            s = s.trim();
            CheckBox checkBox;
            if (s.equals("Slow (10-15 mph)")){
                slowCheckBox.setChecked(true);
            }else if (s.equals("Moderate (15-20 mph)")){
                moderateCheckBox.setChecked(true);
            }else if (s.equals("Fast (20+ mph)")){
                fastCheckBox.setChecked(true);
            }else if (s.equals("Extreme (25+ mph)")){
                extremeCheckBox.setChecked(true);
            }
        }

    }

    // Helper method to clear input fields and checkboxes
    private void clearInputFields() {
        eventTypeEditText.getText().clear();
        eventDetailsEditText.getText().clear();
        under18CheckBox.setChecked(false);
        eighteenToTwentyFiveCheckBox.setChecked(false);
        twentySixToSixtyFiveCheckBox.setChecked(false);
        over65CheckBox.setChecked(false);
        beginnerCheckBox.setChecked(false);
        intermediateCheckBox.setChecked(false);
        advancedCheckBox.setChecked(false);
        expertCheckBox.setChecked(false);
        slowCheckBox.setChecked(false);
        moderateCheckBox.setChecked(false);
        fastCheckBox.setChecked(false);
        extremeCheckBox.setChecked(false);
    }
}
