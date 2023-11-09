package com.example.deliverable_1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AccountManagementActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private Button addUserButton;
    private ListView accountListView;

    private List<String> userAccounts;
    private ArrayAdapter<String> userAccountsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);

        // Initialize UI components
        accountListView = findViewById(R.id.accountListView);

        // Initialize the list of user accounts
        userAccounts = new ArrayList<>();

        // Initialize the adapter for the user accounts list view
        userAccountsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userAccounts);

        // Set the adapter for the accountListView
        accountListView.setAdapter(userAccountsAdapter);

        // Set click listener for the "Add User" button
        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                if (!username.isEmpty()) {
                    // Add the user account to the list and update the list view
                    userAccounts.add(username);
                    userAccountsAdapter.notifyDataSetChanged();
                    usernameEditText.setText(""); // Clear the input field
                } else {
                    Toast.makeText(AccountManagementActivity.this, "Please enter a username", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set a long-click listener to delete user accounts
        accountListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Remove the user account from the list and update the list view
                userAccounts.remove(position);
                userAccountsAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
