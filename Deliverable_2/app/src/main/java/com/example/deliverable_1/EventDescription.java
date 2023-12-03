package com.example.deliverable_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventDescription extends AppCompatActivity {

    private Button backButton;
    private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_description);

        //  DELETE THIS COMMENT AFTERWARDS
        //I hope this provides some direction. Its from the HomePage.class
        String eventName = getIntent().getStringExtra("USERNAME"); // will have to replace USERNAME with club name
        String eventDescription_text = getIntent().getStringExtra("ACCOUNT_TYPE"); //will have to replace ACCOUNT_TYPE with the club description

        // Display the Club Name
        // Find the welcomeMessage TextView and set the welcome message
        TextView event = findViewById(R.id.eventName);
        event.setText(eventName);

        TextView description = findViewById(R.id.event_description_text);
        description.setText(eventDescription_text);

        //now to deal with the confirm & back buttons

        backButton = findViewById(R.id.eDescription_back);
        confirm = findViewById(R.id.eDescription_confirm);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventDescription.this, EventDiscovery.class));
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