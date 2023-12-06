package com.example.deliverable_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.annotations.NonNull;


public class LoginActivity extends AppCompatActivity {
    private String userRole = "participant";
    private TextView textViewSignUp;
    private EditText inputUsername, inputPassword;
    private Button loginButton;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            redirectToDashboard(currentUser);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        textViewSignUp = findViewById(R.id.textViewSignUp);
        inputUsername = findViewById(R.id.inputUsername);
        inputPassword = findViewById(R.id.inputPassword);
        loginButton = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar);

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
        progressBar.setVisibility(View.VISIBLE);

        if (username.isEmpty() || username.length() < 5) {
            showError(inputUsername, "Your username is not valid!");
        } else if (password.isEmpty() || password.length() < 5) {
            showError(inputPassword, "Your password is incorrect");
        } else if (username.equals("admin") && password.equals("admin")) {
            // redirect to admin page
            Intent adminIntent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
            startActivity(adminIntent);
        } else if (username.equals("gccadmin") && password.equals("GCCRocks!")) {
            Intent gccAdminIntent = new Intent(LoginActivity.this, ClubOwnerDashboardActivity.class);
            startActivity(gccAdminIntent);
        } else {
            mAuth.signInWithEmailAndPassword(username , password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser currentUser = mAuth.getCurrentUser();
                                if (currentUser != null) {
                                    redirectToDashboard(currentUser);
                                    finish(); // Finish the LoginActivity
                                }
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void redirectToDashboard(FirebaseUser user) {
        String role = "Participant"; // Default role
        // Get the role from the user object if available
        if (user.getUid() != null) {
            // Fetch the role from the database based on the user's UID
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User userData = snapshot.getValue(User.class);
                    if (userData != null) {
                        userRole = userData.getRole();
                        redirectBasedOnRole(userRole);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle the error
                }
            });
        }
    }

    private void redirectBasedOnRole(String role) {
        Intent intent;
        switch (role) {
            case "Participant":
                intent = new Intent(LoginActivity.this, UserDashboard.class);
                break;
            case "Cycling Club":
                intent = new Intent(LoginActivity.this, ClubOwnerDashboardActivity.class);
                break;
            case "Admin":
                intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                break;
            default:
                intent = new Intent(LoginActivity.this, UserDashboard.class);
                break;
        }
        Log.d("LoginActivity", "Redirecting to Dashboard: " + role);
        startActivity(intent);
    }


    private void showError(EditText input, String message) {
        input.setError(message);
        input.requestFocus();
    }
}
