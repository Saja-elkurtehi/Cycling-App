package com.example.deliverable_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class EditClubProfileActivity extends AppCompatActivity {
    TextView socialMediaLink, contactName, contactPhoneNumber;
    TextView displaySocialMedia, displayContactName, displayContactPhoneNumber;
    DatabaseReference databaseReference;

    Button saveChangesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_club_profile);

        // Initialize Firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        // Check if the user is authenticated
        if (user != null) {
            // Use the UID of the authenticated user
            String uid = user.getUid();

            // Set the reference to "ClubOwners" node under the UID of the authenticated user
            databaseReference = firebaseDatabase.getReference("ClubOwners").child(uid);

            socialMediaLink = findViewById(R.id.socialMediaLink);
            contactName = findViewById(R.id.contactName);
            contactPhoneNumber = findViewById(R.id.contactPhoneNumber);

            displaySocialMedia = findViewById(R.id.displaySocialMedia);
            displayContactName = findViewById(R.id.displayContactName);
            displayContactPhoneNumber = findViewById(R.id.displayContactPhoneNumber);

            saveChangesButton = findViewById(R.id.btnSaveChanges);

            // Add ValueEventListener to update TextViews when data changes
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // Check if data exists in the "ClubOwners" node
                    if (dataSnapshot.exists()) {
                        // Assuming you only have one ClubOwner for simplicity
                        ClubOwner clubOwner = dataSnapshot.getValue(ClubOwner.class);

                        // Update TextViews with ClubOwner data
                        if (clubOwner != null) {
                            displaySocialMedia.setText("Social Media Link: " + clubOwner.getSocialMedia());
                            displayContactName.setText("Contact Name: " + clubOwner.getName());
                            displayContactPhoneNumber.setText("Contact Phone Number: " + clubOwner.getPhone());
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle errors
                }
            });

            // Set click listener for the event creation button
            saveChangesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkCredentials();
                }
            });
        }
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

        // All fields are valid
        saveClubOwnerData(socialMedia, name, phone);

        return true;
    }

    private void saveClubOwnerData(String socialMedia, String name, String phone) {
        // Create a ClubOwner object with the provided data
        ClubOwner clubOwner = new ClubOwner(socialMedia, name, phone);

        // Save the data to the database under the UID of the authenticated user
        databaseReference.setValue(clubOwner).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Data saved successfully, update TextViews with the new data
                    displaySocialMedia.setText("Social Media Link: " + socialMedia);
                    displayContactName.setText("Contact Name: " + name);
                    displayContactPhoneNumber.setText("Contact Phone Number: " + phone);

                    // Optionally, show a confirmation message
                    Toast.makeText(EditClubProfileActivity.this, "Changes saved successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle the error
                    Toast.makeText(EditClubProfileActivity.this, "Failed to save changes", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
