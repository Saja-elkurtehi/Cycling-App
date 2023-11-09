package com.example.deliverable_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private TextView textViewSignUp;
    private EditText inputUsername, inputPassword;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewSignUp = findViewById(R.id.textViewSignUp);
        inputUsername = findViewById(R.id.inputUsername);
        inputPassword = findViewById(R.id.inputPassword);
        loginButton = findViewById(R.id.btnLogin);

        // Set a click listener for the Sign Up TextView
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        // Set a click listener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
            }
        });
    }

    private void checkCredentials() {
        String username = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();

        if (username.isEmpty() || username.length() < 5) {
            showError(inputUsername, "Your username is not valid!");
        } else if (password.isEmpty() || password.length() < 5) {
            showError(inputPassword, "Your password is incorrect");
        } else {
            if (username.equals("admin") && password.equals("admin")) {
                // redirect to admin page
                Intent adminIntent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                startActivity(adminIntent);
            } else {
                // for now assume user is participant
                String accountType = "Participant";
                // Pass the username and account type to HomePage activity
                Intent homeIntent = new Intent(LoginActivity.this, HomePage.class);
                homeIntent.putExtra("USERNAME", username);
                homeIntent.putExtra("ACCOUNT_TYPE", accountType);
                startActivity(homeIntent);
            }
        }
    }

    private void showError(EditText input, String message) {
        input.setError(message);
        input.requestFocus();
    }
}
