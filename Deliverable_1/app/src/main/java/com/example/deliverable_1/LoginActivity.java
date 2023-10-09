package com.example.deliverable_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    TextView btn;
    EditText inputUsername, inputPassword;
    Button btnLogin;

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
                checkCredentails();
            }
        });






        btn.setOnClickListener((v) ->{
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
//        btn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
//            }
//        });

    }

    private void checkCredentails() {
        String username = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();

        if (username.isEmpty() || username.length() < 7){
            showError(inputUsername, "Your username is not valid!");
        } else if (password.isEmpty() || password.length() < 8){ //IF FIREBASE OR SQL IS USED, IT WILL HAVE BE CHECKED PROPERLY HERE
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