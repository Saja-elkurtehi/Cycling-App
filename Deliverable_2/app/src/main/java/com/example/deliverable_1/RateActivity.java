package com.example.deliverable_1;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;

public class RateActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_event);
    }
    public void onComplete(@NonNull Task<Void> task) {
        if (task.isSuccessful()) {
            // Optionally, show a confirmation message
            Toast.makeText(RateActivity.this, "Event Rated successfully", Toast.LENGTH_SHORT).show();
        } else {
            // Handle the error
            Toast.makeText(RateActivity.this, "Failed to rate event", Toast.LENGTH_SHORT).show();
        }
    }
}