package com.example.deliverable_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventDiscovery extends AppCompatActivity {

    List<Event> events = new ArrayList<>();
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<Event> adapterEvent;

    SearchView searchView;
    ListView listView;
    List<String> eventList = new ArrayList<>();
    ArrayAdapter<String> adapterSearchBar;

    DatabaseReference db;


    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_discovery);
        db = FirebaseDatabase.getInstance().getReference("Events");

        autoCompleteTextView = findViewById(R.id.auto_complete_events2);
        adapterEvent = new ArrayAdapter<Event>(this, R.layout.list_item_events, events);
        autoCompleteTextView.setAdapter(adapterEvent);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                eventList.clear();
                events.clear();

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Event event = snapshot.getValue(Event.class);
                    eventList.add(event.getEventName());
                    events.add(event);
                }

                adapterSearchBar.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedEvent = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(EventDiscovery.this, "Item: " + selectedEvent, Toast.LENGTH_SHORT).show();

                // Start EventDescription activity and pass selected event name
                Intent intent = new Intent(EventDiscovery.this, EventDescription.class);
                intent.putExtra("EVENT_NAME", selectedEvent);
                startActivity(intent);
            }
        });

        searchView = findViewById(R.id.search_bar_events);
        listView = findViewById(R.id.list_of_events);
        adapterSearchBar = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, eventList);
        listView.setAdapter(adapterSearchBar);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterSearchBar.getFilter().filter(newText);
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedEvent = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(EventDiscovery.this, "You clicked: " + selectedEvent, Toast.LENGTH_SHORT).show();

                // Start EventDescription activity and pass selected event name
                Intent intent = new Intent(EventDiscovery.this, EventDescription.class);
                intent.putExtra("EVENT_NAME", selectedEvent);
                startActivity(intent);
            }
        });

        backButton = findViewById(R.id.eback_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventDiscovery.this, UserDashboard.class));
            }
        });
    }
}
