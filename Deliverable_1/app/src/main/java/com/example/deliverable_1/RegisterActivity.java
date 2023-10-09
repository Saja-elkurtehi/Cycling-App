package com.example.deliverable_1;

import androidx.appcompat.app.AppCompatActivity;

//these imports are for the dropdown menu
import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;


import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deliverable_1.R.id;

public class RegisterActivity extends AppCompatActivity{

    //this is for the drop down menu
    private Spinner spinner;
    private static final String[] paths = {"item 1", "item 2", "item 3"};
//    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
//
//        switch (position) {
//            case 0:
//                // Whatever you want to happen when the first item gets selected
//                break;
//            case 1:
//                // Whatever you want to happen when the second item gets selected
//                break;
//            case 2:
//                // Whatever you want to happen when the thrid item gets selected
//                break;
//
//        }
//    }
//
//    public void onNothingSelected(AdapterView<?> parent) {
//        // TODO Auto-generated method stub
//    }



    TextView btn;
    private EditText inputUsername, inputPassword, inputEmail, inputConfirmPassword;
    Button btnReg;

    //these are the regular methods
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //the following is to make sure that the inputs for the username, email, and password are valid
        btn = findViewById(R.id.alreadyHaveAccount);
        inputUsername = findViewById(R.id.inputUsername);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);

        btnReg= findViewById(R.id.btnRegister);
        btnReg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkCredentials();
            }
        });


        checkCredentials(); //a method that checks the values of the inputs

        //START OF DROP DOWN MENU
        spinner = (Spinner)findViewById(R.id.inputRole);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dropdown_roles, android.R.layout.simple_spinner_item);;

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //specifies the layout
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((OnItemSelectedListener) this);

//        String[] items = new String[] { "Chai Latte", "Green Tea", "Black Tea" };
//
//        Spinner dynamicSpinner = (Spinner) findViewById(R.id.dynamicInputRole);
//        ArrayAdapter<String> dAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
//
//        dynamicSpinner.setAdapter(dAdapter);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        //END OF DROP DOWN MENU

        TextView btn = findViewById(R.id.alreadyHaveAccount);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            //this will take you from Register to Login (if they already have an account)
            public void onClick(View v){
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }

    //ensures that the credential are correct
    private void checkCredentials() {
        String username = inputUsername.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmPassword = inputConfirmPassword.getText().toString();

        if (username.isEmpty() || username.length() < 7){
            showError(inputUsername, "Your username is not valid!");
        } else if (email.isEmpty() || !email.contains("@")){
            showError(inputEmail, "Email is not valid!");
        } else if (password.isEmpty() || password.length() < 8){
            showError(inputPassword, "Your password must be longer than 7 characters");
        } else if (confirmPassword.isEmpty() || !confirmPassword.equals(password)){
            showError(inputConfirmPassword, "The passwords don't match");
        } else {
            Toast.makeText(this, "Call Registration Method", Toast.LENGTH_SHORT).show();
        }
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus(); // will show the error
    }
}