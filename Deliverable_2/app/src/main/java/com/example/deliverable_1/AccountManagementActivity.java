package com.example.deliverable_1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AccountManagementActivity extends AppCompatActivity {

    private Button deleteAccountButton;
    private ListView accountListView;

    private List<String> userAccounts;
    private ArrayAdapter<String> userAccountsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);

        // Initialize UI components
        deleteAccountButton = findViewById(R.id.deleteAccountButton);
        accountListView = findViewById(R.id.accountListView);

        // Initialize the list of user accounts
        userAccounts = new ArrayList<>();

        // Initialize the adapter for the user accounts list view
        userAccountsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userAccounts);

        // Set the adapter for the accountListView
        accountListView.setAdapter(userAccountsAdapter);

        // Set click listener for the "Delete Account" button
        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedItemPosition = accountListView.getCheckedItemPosition();
                if (selectedItemPosition != ListView.INVALID_POSITION) {
                    userAccounts.remove(selectedItemPosition);
                    userAccountsAdapter.notifyDataSetChanged();
                    Toast.makeText(AccountManagementActivity.this, "Account deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AccountManagementActivity.this, "Please select an account to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
