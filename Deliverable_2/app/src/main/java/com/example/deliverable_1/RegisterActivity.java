package com.example.deliverable_1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText inputUsername, inputPassword, inputEmail, inputConfirmPassword;
    private Button btnReg;
    private Spinner roleSpinner;
    FirebaseAuth mAuth;

    ProgressBar progressBar;
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent participant1Intent = new Intent(getApplicationContext(), UserDashboard.class);
            startActivity((participant1Intent));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        inputUsername = findViewById(R.id.inputUsername);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        btnReg = findViewById(R.id.btnRegister);
        roleSpinner = findViewById(R.id.inputRole);
        progressBar = findViewById(R.id.progressBar);

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

    // ...

    private boolean checkCredentials() {
        progressBar.setVisibility(View.VISIBLE);
        String username = inputUsername.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmPassword = inputConfirmPassword.getText().toString();

        if (username.isEmpty() || username.length() < 5) {
            showError(inputUsername, "Please enter a valid username (minimum 5 characters).");
            return false;
        } else if (email.isEmpty()) {
            showError(inputEmail, "Please enter a valid email address.");
            return false;
        } else if (password.isEmpty() || password.length() < 5) {
            showError(inputPassword, "Please enter a password (minimum 5 characters).");
            return false;
        } else if (confirmPassword.isEmpty() || !confirmPassword.equals(password)) {
            showError(inputConfirmPassword, "Passwords do not match.");
            return false;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);

                        if (task.isSuccessful()) {
                            // Get the current user and their UID
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            if (currentUser != null) {
                                String userId = currentUser.getUid();

                                // Get additional user data
                                String username = inputUsername.getText().toString();
                                String selectedRole = roleSpinner.getSelectedItem().toString();

                                // Save additional user data to the database
                                saveUserData(userId, username, email, selectedRole);

                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(RegisterActivity.this, "Account Created.",
                                        Toast.LENGTH_SHORT).show();

                                // Redirect based on the selected role
                                redirectToDashboard(selectedRole);

                            }
                        } else {
                            // If sign up fails, display a message to the user.
                            if (task.getException() != null) {
                                Toast.makeText(RegisterActivity.this, "Authentication failed: " +
                                        task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
        // All fields are valid
        return true;
    }

    private void redirectToDashboard(String selectedRole) {
        Intent intent;

        // Redirect based on the selected role
        switch (selectedRole) {
            case "Participant":
                intent = new Intent(RegisterActivity.this, UserDashboard.class);
                break;
            case "Cycling Club":
                intent = new Intent(RegisterActivity.this, ClubOwnerDashboardActivity.class);
                break;
            case "Admin":
                intent = new Intent(RegisterActivity.this, AdminDashboardActivity.class);
                break;
            default:
                // Default redirect if role is not recognized
                intent = new Intent(RegisterActivity.this, UserDashboard.class);
                break;
        }

        Log.d("RegisterActivity", "Redirecting to Dashboard: " + selectedRole);
        startActivity(intent);
        finish(); // Finish the RegisterActivity
    }
// ...

    private void saveUserData(String userId, String username, String email, String role) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");

        // Create a User object with the provided data
        User user = new User(username, email, role);

        // Save the user data to the database under the user's ID
        databaseReference.child(userId).setValue(user);

        Log.d("RegisterActivity", "User data saved to the database");
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
