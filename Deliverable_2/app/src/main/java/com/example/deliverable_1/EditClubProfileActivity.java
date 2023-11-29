package com.example.deliverable_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditClubProfileActivity extends AppCompatActivity {
    TextView socialMediaLink, contactName, contactPhoneNumber;

    Button saveChangesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_club_profile);

        socialMediaLink = findViewById(R.id.socialMediaLink);
        contactName = findViewById(R.id.contactName);
        contactPhoneNumber = findViewById(R.id.contactPhoneNumber);

        saveChangesButton = findViewById(R.id.btnSaveChanges);


        // Set click listener for the event creation button
        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
                //saveChanges(); //create the event (NOT IMPLEMENTED)
            }
        });
    }

    private void showError(EditText input, String message) {
        input.setError(message);
        input.requestFocus();
    }

    private boolean checkCredentials() {
        String socialMedia = socialMediaLink.getText().toString();
        String name = contactName.getText().toString();
        String phone = contactPhoneNumber.getText().toString();

        if (socialMedia.isEmpty() || socialMedia.length() < 5) {
            showError((EditText) socialMediaLink, "Please enter a valid link.");
            return false;
        } else if (phone.isEmpty() || phone.length() < 11) {
            showError((EditText) contactPhoneNumber, "Please enter a valid phone number.");
            return false;
        }

        //All fields are valid
        return true;
    }
}
