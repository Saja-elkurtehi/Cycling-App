package com.example.deliverable_1;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Retrieve username and account type from the intent
        String username = getIntent().getStringExtra("USERNAME");
        String accountType = getIntent().getStringExtra("ACCOUNT_TYPE");

        // Display the welcome message
        // Find the welcomeMessage TextView and set the welcome message
        TextView welcomeMessage = findViewById(R.id.textView2);
        welcomeMessage.setText("Welcome, " + username + "!");

// Find the accountType TextView and set the account type
        TextView accountTypeTextView = findViewById(R.id.textView3);
// Assuming you have a variable accountType containing the account type (e.g., "Administrator")
        accountTypeTextView.setText("You are logged in as " + accountType + ".");

    }
}
