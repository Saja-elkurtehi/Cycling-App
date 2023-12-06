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
    List<ClubOwner> clubList = new ArrayList<>();
    ArrayAdapter<ClubOwner> adapterSearchBar;
    DatabaseReference clubOwnersRef;
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
                    String clubOwnerName = clubOwnerSnapshot.child("name").getValue(String.class);
                    ClubOwner clubOwner = new ClubOwner(clubOwnerName); // Assuming ClubOwner has a constructor that takes a name
                    clubList.add(clubOwner);
                }



                adapterSearchBar.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

        searchView = findViewById(R.id.search_bar_clubs);
        listView = findViewById(R.id.list_of_clubs);

        adapterSearchBar = new ArrayAdapter<ClubOwner>(this, android.R.layout.simple_list_item_1, android.R.id.text1, clubList);
        listView.setAdapter(adapterSearchBar);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ClubOwner selectedClubOwner = (ClubOwner) adapterView.getItemAtPosition(position);
                handleClubClick(selectedClubOwner);
            }
        });

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

        backButton = findViewById(R.id.cback_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClubDiscovery.this, UserDashboard.class));
            }
        });
    }

    private void handleClubClick(ClubOwner selectedClubOwner) {
        Toast.makeText(ClubDiscovery.this, "You clicked: " + selectedClubOwner.getName(), Toast.LENGTH_SHORT).show();
        // Handle the click event, for example, navigate to ClubDescription activity
    }
}
