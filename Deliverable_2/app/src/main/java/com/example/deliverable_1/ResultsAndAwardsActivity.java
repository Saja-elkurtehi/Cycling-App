package com.example.deliverable_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResultsAndAwardsActivity extends AppCompatActivity {

    private RadioButton goldMedalRadioButton, goodSportRadioButton1, mostEffortRadioButton1;
    private RadioButton silverMedalRadioButton, goodSportRadioButton2, mostEffortRadioButton2;
    private RadioButton bronzeMedalRadioButton, goodSportRadioButton3, mostEffortRadioButton3;
    private Button sendAwardsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_and_rewards);

        // Initialize your views
        goldMedalRadioButton = findViewById(R.id.radioButton);
        goodSportRadioButton1 = findViewById(R.id.radioButton2);
        mostEffortRadioButton1 = findViewById(R.id.radioButton3);

        silverMedalRadioButton = findViewById(R.id.radioButton4);
        goodSportRadioButton2 = findViewById(R.id.radioButton5);
        mostEffortRadioButton2 = findViewById(R.id.radioButton6);

        bronzeMedalRadioButton = findViewById(R.id.radioButton7);
        goodSportRadioButton3 = findViewById(R.id.radioButton8);
        mostEffortRadioButton3 = findViewById(R.id.radioButton9);

        sendAwardsButton = findViewById(R.id.buttonSendAwards);

        sendAwardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event, and process sending awards
                sendAwards();
            }
        });
    }

    private void sendAwards() {
        Toast.makeText(this, "Awards sent successfully", Toast.LENGTH_SHORT).show();
    }
}
