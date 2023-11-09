package com.example.deliverable_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        // Initialize UI components
        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);

        // Set a click listener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve the entered username and password
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Check if the username and password match the admin credentials (replace with your authentication logic)
                if (isValidAdmin(username, password)) {
                    // Admin authentication successful, navigate to the AdminDashboardActivity
                    Intent intent = new Intent(AdminLoginActivity.this, AdminDashboardActivity.class);
                    startActivity(intent);
                } else {
                    // Admin authentication failed, display an error message
                    showError("Invalid username or password");
                }
            }
        });
    }

    private boolean isValidAdmin(String username, String password) {
        // Replace this with your actual authentication logic
        return username.equals("admin") && password.equals("adminpassword");
    }

    private void showError(String message) {
        // You can customize how you want to display the error message (e.g., using Toast or setting an error message on the UI).
    }
}
