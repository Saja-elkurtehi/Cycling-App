package com.example.deliverable_1;

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

public class EventDiscovery extends AppCompatActivity {

    String[] events = {"Event1", "Event2", "Event3", "Event4", "Event5"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterEvent;

    SearchView searchView;
    ListView listView;
    String[] eventList = {"eventTest1", "eventTest2", "eventTest3", "eventTest4", "eventTest5"};
    ArrayAdapter<String> adapterSearchBar;

    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_discovery);

        autoCompleteTextView = findViewById(R.id.auto_complete_events2);
        adapterEvent = new ArrayAdapter<String>(this, R.layout.list_item_events, events);
        autoCompleteTextView.setAdapter(adapterEvent);

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
