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

    //THIS IS WHAT POPULATES THE DROPDOWN SELECTION - HAVE TO CONNECT FIREBASE AND THE EVENTYPES
    String[] events = {"Event1", "Event2", "Event3", "Event4", "Event5"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterEvent;

    //these are for the search bar and list view
    SearchView searchView;
    ListView listView;

    //you may have to replace the "String" with "EventCreationActivity" and will have to populate this
    //array using FireBase
    String[] eventList = {"eventTest1", "eventTest2", "eventTest3", "eventTest4", "eventTest5"};

    ArrayAdapter<String>  adapterSearchBar;

    //for the back button
    private Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_discovery);

        //autocompleting the event types based the "events" array
        autoCompleteTextView = findViewById(R.id.auto_complete_events2);
        adapterEvent = new ArrayAdapter<String>(this, R.layout.list_item_events, events); //list item is an xml file

        autoCompleteTextView.setAdapter(adapterEvent);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //once they click an event, make it filter the array "eventList" here
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String events = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(EventDiscovery.this, "Item: " + events, Toast.LENGTH_SHORT).show();
            }
        });

        //creating a temporary object for list view and search view

        searchView = findViewById(R.id.search_bar_events);
        listView = findViewById(R.id.list_of_events);

        adapterSearchBar = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, eventList);
        listView.setAdapter(adapterSearchBar);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(EventDiscovery.this, "You clicked: " + adapterView.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

            }
        });

        //then search view set filter (this will also have to be changed). I'm not sure how
        //you've create the events (because they don't really look like the umls), but you'll likely have
        //to have a name field for each event created and search like that
        // so instead of "String query", it might be EventCreationActivity.eventName for example (though EventDiscovery
        // isn't created yet

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                EventDiscovery.this.adapterSearchBar.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                EventDiscovery.this.adapterSearchBar.getFilter().filter(newText);
                return false;
            }
        });

        //for the back button
        backButton = findViewById(R.id.eback_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventDiscovery.this, UserDashboard.class));
            }
        });
    }
}