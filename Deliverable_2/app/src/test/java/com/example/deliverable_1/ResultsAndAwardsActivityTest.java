package com.example.deliverable_1;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ResultsAndAwardsActivityTest {

    @Test
    public void testSendAwardsSuccess() {
        ResultsAndAwardsActivity resultsAndAwardsActivity = new ResultsAndAwardsActivity();

        // Assuming all radio buttons are selected
        resultsAndAwardsActivity.goldMedalRadioButton.setChecked(true);
        resultsAndAwardsActivity.goodSportRadioButton1.setChecked(true);
        resultsAndAwardsActivity.mostEffortRadioButton1.setChecked(true);

        resultsAndAwardsActivity.silverMedalRadioButton.setChecked(true);
        resultsAndAwardsActivity.goodSportRadioButton2.setChecked(true);
        resultsAndAwardsActivity.mostEffortRadioButton2.setChecked(true);

        resultsAndAwardsActivity.bronzeMedalRadioButton.setChecked(true);
        resultsAndAwardsActivity.goodSportRadioButton3.setChecked(true);
        resultsAndAwardsActivity.mostEffortRadioButton3.setChecked(true);

        resultsAndAwardsActivity.sendAwards();

        // Assert that the awards were sent successfully (displayed a toast message)
        assertTrue(resultsAndAwardsActivity.toastMessageDisplayed);
    }

    @Test
    public void testSendAwardsNoSelection() {
        ResultsAndAwardsActivity resultsAndAwardsActivity = new ResultsAndAwardsActivity();
        resultsAndAwardsActivity.sendAwards();

        // Assert that the awards were not sent successfully (no toast message)
        assertFalse(resultsAndAwardsActivity.toastMessageDisplayed);
    }
}