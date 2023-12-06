package com.example.deliverable_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class ClubDiscovery extends AppCompatActivity {

    SearchView searchView;
    ListView listView;

    //you may have to replace the "String" with "EventCreationActivity" and will have to populate this
    //array using FireBase
    List<ClubOwner> clubList = new ArrayList<>();

    ArrayAdapter<ClubOwner>  adapterSearchBar;

    DatabaseReference clubOwnersRef;

    //for the back button
    private Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_discovery);
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

        //creating a temporary object for list view and search view

        searchView = findViewById(R.id.search_bar_clubs);
        listView = findViewById(R.id.list_of_clubs);

        adapterSearchBar = new ArrayAdapter<ClubOwner>(this, android.R.layout.simple_list_item_1, android.R.id.text1, clubList);
        listView.setAdapter(adapterSearchBar);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(ClubDiscovery.this, "You clicked: " + adapterView.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

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
                ClubDiscovery.this.adapterSearchBar.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ClubDiscovery.this.adapterSearchBar.getFilter().filter(newText);
                return false;
            }
        });

        //for the back button on the club discovery page
        backButton = findViewById(R.id.cback_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClubDiscovery.this, UserDashboard.class));
            }
        });
    }
}