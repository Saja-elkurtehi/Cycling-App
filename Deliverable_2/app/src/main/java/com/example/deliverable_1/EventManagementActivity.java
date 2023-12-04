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
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

    // Firebase database reference
    private DatabaseReference eventsReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_management);

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

            // Initialize UI components
            eventTypeEditText = findViewById(R.id.eventType);
            eventDetailsEditText = findViewById(R.id.eventDetails);
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
            addEventButton = findViewById(R.id.btnAddEvent);
            editEventButton = findViewById(R.id.btnEditEvent);
            deleteEventButton = findViewById(R.id.btnDeleteEvent);
            backToAdminDashboardButton = findViewById(R.id.btnBackToAdminDashboard);

            events = new ArrayList<>();
            eventAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, events);
            eventListView = findViewById(R.id.eventListView);
            eventListView.setAdapter(eventAdapter);

            addEventButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String eventType = eventTypeEditText.getText().toString();
                    String eventDetails = eventDetailsEditText.getText().toString();

                    if (!eventType.isEmpty() && !eventDetails.isEmpty()) {
                        String eventDescription = setEvent(eventType, eventDetails);
                        saveEventToDatabase(eventDescription);

                        events.add(eventDescription);
                        eventAdapter.notifyDataSetChanged();
                        clearInputFields();
                    } else {
                        Toast.makeText(EventManagementActivity.this, "Please enter event type and details", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            editEventButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String updatedEventType = eventTypeEditText.getText().toString();
                    String updatedEventDetails = eventDetailsEditText.getText().toString();

                    if (!updatedEventType.isEmpty() && !updatedEventDetails.isEmpty()) {
                        String eventDescription = setEvent(updatedEventType, updatedEventDetails);
                        updateEventInDatabase(selectedIndex, eventDescription);

                        events.set(selectedIndex, eventDescription);
                        eventAdapter.notifyDataSetChanged();
                        clearInputFields();
                    } else {
                        Toast.makeText(EventManagementActivity.this, "Please enter event type and details", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            deleteEventButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String eventType = eventTypeEditText.getText().toString();
                    String eventDetails = eventDetailsEditText.getText().toString();
                    if (eventType.isEmpty() || eventDetails.isEmpty()) {
                        Toast.makeText(EventManagementActivity.this, "No event has been selected to delete", Toast.LENGTH_SHORT).show();
                    } else {
                        String eventDescription = setEvent(eventType, eventDetails);
                        deleteEventFromDatabase(selectedIndex);

                        events.remove(selectedIndex);
                        eventAdapter.remove(eventDescription);
                    }
                }
            });

            backToAdminDashboardButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(EventManagementActivity.this, AdminDashboardActivity.class);
                    startActivity(intent);
                }
            });

            eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    selectedIndex = position;
                    clearInputFields();
                    repopulateUI(position);
                }
            });
        }
    }

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

    private void saveEventToDatabase(String eventDescription) {
        // Extract information from the eventDescription
        String[] parts = eventDescription.split("\n");
        String eventType = parts[0].trim();
        String eventDetails = parts[1].trim();
        String participantRequirements = parts[2].trim();
        String skillLevel = parts[3].trim();
        String pace = parts[4].trim();

        // Save each category separately in the database
        saveEventToDatabase(eventType, eventDetails, participantRequirements, skillLevel, pace);
    }

    private void updateEventInDatabase(int selectedIndex, String eventDescription) {
        // Extract information from the eventDescription
        String[] parts = eventDescription.split("\n");
        String eventType = parts[0].trim();
        String eventDetails = parts[1].trim();
        String participantRequirements = parts[2].trim();
        String skillLevel = parts[3].trim();
        String pace = parts[4].trim();

        // Update each category separately in the database
        updateEventInDatabase(selectedIndex, eventType, eventDetails, participantRequirements, skillLevel, pace);
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

    private void saveEventToDatabase(String eventType, String eventDetails,
                                     String participantRequirements, String skillLevel, String pace) {
        // Create a unique key for the event
        String eventKey = eventsReference.push().getKey();

        // Save each category separately in the database
        eventsReference.child(eventKey).child("eventType").setValue(eventType);
        eventsReference.child(eventKey).child("eventDetails").setValue(eventDetails);
        eventsReference.child(eventKey).child("participantRequirements").setValue(participantRequirements);
        eventsReference.child(eventKey).child("skillLevel").setValue(skillLevel);
        eventsReference.child(eventKey).child("pace").setValue(pace);
        eventsReference.child(eventKey).child("eventName").setValue(eventType);
    }


    private void updateEventInDatabase(int selectedIndex, String eventType, String eventDetails,
                                       String participantRequirements, String skillLevel, String pace) {
        // Retrieve the event key at the selected index
        String eventKey = eventsReference.getKey();

        // Update each category separately in the database
        eventsReference.child(eventKey).child("eventType").setValue(eventType);
        eventsReference.child(eventKey).child("eventDetails").setValue(eventDetails);
        eventsReference.child(eventKey).child("participantRequirements").setValue(participantRequirements);
        eventsReference.child(eventKey).child("skillLevel").setValue(skillLevel);
        eventsReference.child(eventKey).child("pace").setValue(pace);
    }

    private void deleteEventFromDatabase(int selectedIndex) {
        String eventKey = eventsReference.getKey();
        eventsReference.child(eventKey).removeValue();
    }

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
