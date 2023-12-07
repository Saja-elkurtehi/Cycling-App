package com.example.deliverable_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventDescription extends AppCompatActivity {

    private Button backButton;
    private Button confirm;

    List<Event> eventList = new ArrayList<>();
    ArrayAdapter<Event> adapterSearchBar;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_description);
        db = FirebaseDatabase.getInstance().getReference("Events");

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventList.clear();

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Event event = snapshot.getValue(Event.class);
                    eventList.add(event);
                }

                adapterSearchBar.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
                String username = getIntent().getStringExtra("USERNAME");
                String email = getIntent().getStringExtra("EMAIL");

                TextView event = findViewById(R.id.eventName);
                db.push().setValue(event).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(EventDescription.this, "Event joined successfully", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(EventDescription.this, "Failed to join event", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}