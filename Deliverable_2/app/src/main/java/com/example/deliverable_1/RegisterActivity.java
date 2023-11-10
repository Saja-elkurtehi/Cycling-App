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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText inputUsername, inputPassword, inputEmail, inputConfirmPassword;
    private Button btnReg;
    private Spinner roleSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputUsername = findViewById(R.id.inputUsername);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        btnReg = findViewById(R.id.btnRegister);
        roleSpinner = findViewById(R.id.inputRole);

        // Populate the dropdown menu with options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.roles_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(adapter);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
            }
        });

        TextView btn = findViewById(R.id.alreadyHaveAccount);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private boolean checkCredentials() {
        String username = inputUsername.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmPassword = inputConfirmPassword.getText().toString();

        if (username.isEmpty() || username.length() < 5) {
            showError(inputUsername, "Please enter a valid username (minimum 5 characters).");
            return false;
        } else if (email.isEmpty() || validate_email(email)) {
            showError(inputEmail, "Please enter a valid email address.");
            return false;
        } else if (password.isEmpty() || password.length() < 5) {
            showError(inputPassword, "Please enter a password (minimum 5 characters).");
            return false;
        } else if (confirmPassword.isEmpty() || !confirmPassword.equals(password)) {
            showError(inputConfirmPassword, "Passwords do not match.");
            return false;
        }
        // All fields are valid
        return true;
    }

    public boolean validate_email(String email) {
        String regex = "^[a-zA-Z0-9_][a-zA-Z0-9_]+@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)";

        Pattern pattern = Pattern.compile(regex, Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    private void showError(EditText input, String message) {
        input.setError(message);
        input.requestFocus(); // Will show the error
    }

    public void onClick(View v) {
        if (checkCredentials()) {
            // Call registration method or perform appropriate action
            Toast.makeText(RegisterActivity.this, "Call Registration Method", Toast.LENGTH_SHORT).show();
        }
    }

}
