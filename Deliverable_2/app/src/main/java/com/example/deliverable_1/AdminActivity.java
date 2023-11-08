package com.example.deliverable_1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_management);

        Button btnAddEvent = findViewById(R.id.btnAddEvent);
        Button btnEditEvent = findViewById(R.id.btnEditEvent);
        Button btnDeleteEvent = findViewById(R.id.btnDeleteEvent);

        btnAddEvent.setOnClickListener(view -> {
            startActivity(new Intent(AdminActivity.this, AddEventActivity.class));
        });

        btnEditEvent.setOnClickListener(view -> {
            startActivity(new Intent(AdminActivity.this, EditEventActivity.class));
            // Add logic to populate dropdown with event options
        });

        btnDeleteEvent.setOnClickListener(view -> {
            // Implement delete functionality here using a dropdown
            // or alert dialog to confirm deletion
        });
    }
}
