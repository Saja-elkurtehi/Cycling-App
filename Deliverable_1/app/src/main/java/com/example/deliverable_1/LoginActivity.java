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
            // For now, let's assume the user is a participant
            String accountType = "Participant";

            // Pass the username and account type to HomePage activity
            Intent intent = new Intent(LoginActivity.this, HomePage.class);
            intent.putExtra("USERNAME", username);
            intent.putExtra("ACCOUNT_TYPE", accountType);
            startActivity(intent);
        }
    }

    private void showError(EditText input, String message) {
        input.setError(message);
        input.requestFocus();
    }
}



/*** package com.example.deliverable_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    TextView btn; // sign up text
    EditText inputUsername, inputPassword; // username and pass input fields
    Button btnLogin; // login button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn = findViewById(R.id.textViewSignUp);
        inputUsername = findViewById(R.id.inputUsername);
        inputPassword = findViewById(R.id.inputPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkCredentails();    // call method to check credentials
            }
        });

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

    }


    private void checkCredentails() {
        String username = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();

        if (username.isEmpty() || username.length() < 5){
            showError(inputUsername, "Your username is not valid!");
        } else if (password.isEmpty() || password.length() < 5){ //IF FIREBASE OR SQL IS USED, IT WILL HAVE BE CHECKED PROPERLY HERE
            showError(inputPassword, "Your password is incorrect");
        } else {
            Toast.makeText(this, "Call Registration Method", Toast.LENGTH_SHORT).show();
        }
    }

    private void showError(EditText input, String message) {
        input.setError(message);
        input.requestFocus(); // will show the error
    }
}
***/