package com.example.deliverable_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ClubDescription extends AppCompatActivity {

    private Button backButton;
    private Button confirm;

    List<ClubOwner> clubList = new ArrayList<>();

    ArrayAdapter<ClubOwner> adapterSearchBar;

    DatabaseReference clubOwnersRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_description);
        clubOwnersRef = FirebaseDatabase.getInstance().getReference("ClubOwners");
        clubOwnersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clubList.clear();

                for (DataSnapshot clubOwnerSnapshot : dataSnapshot.getChildren()){
                    ClubOwner clubOwner = clubOwnerSnapshot.getValue(ClubOwner.class);
                    clubList.add(clubOwner);
                }

                adapterSearchBar.notify();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

        //  DELETE THIS COMMENT AFTERWARDS
        //I hope this provides some direction. Its from the HomePage.class
        String clubname = getIntent().getStringExtra("USERNAME"); // will have to replace USERNAME with club name
        String clubDescription_text = getIntent().getStringExtra("ACCOUNT_TYPE"); //will have to replace ACCOUNT_TYPE with the club description

        // Display the Club Name
        // Find the welcomeMessage TextView and set the welcome message
        TextView clubName = findViewById(R.id.clubName);
        clubName.setText(clubname);

        TextView clubDescription = findViewById(R.id.club_description_text);
        clubDescription.setText(clubDescription_text);

        //now to deal with the confirm & back buttons

        backButton = findViewById(R.id.cDescription_back);
        confirm = findViewById(R.id.cDescription_confirm);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClubDescription.this, ClubDiscovery.class));
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //NOT SURE IF WE SHOULD BOTHER ADDING IT TO SOMEWHERE (WOULD BE ADDED TO FIREBASE)
            }
        });

    }
}