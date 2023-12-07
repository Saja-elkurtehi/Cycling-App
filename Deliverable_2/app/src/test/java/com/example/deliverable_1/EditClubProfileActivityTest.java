package com.example.deliverable_1;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EditClubProfileActivityTest {

    @Test
    public void testCheckCredentialsValid() {
        EditClubProfileActivity editClubProfileActivity = new EditClubProfileActivity();

        // Set valid credentials
        editClubProfileActivity.socialMediaLink.setText("http://example.com%22/);
                editClubProfileActivity.contactName.setText("John Doe");
        editClubProfileActivity.contactPhoneNumber.setText("12345678901");
        boolean result = editClubProfileActivity.checkCredentials();
        // assert that the result is true (valid credentials)
        assertTrue(result);
    }

    @Test
    public void testCheckCredentialsInvalidSocialMedia() {
        EditClubProfileActivity editClubProfileActivity = new EditClubProfileActivity();
        // set invalid social media link
        editClubProfileActivity.socialMediaLink.setText("invalid");
        boolean result = editClubProfileActivity.checkCredentials();
        // assert that the result is false (invalid social media link)
        assertFalse(result);
    }

    @Test
    public void testCheckCredentialsInvalidPhoneNumber() {
        EditClubProfileActivity editClubProfileActivity = new EditClubProfileActivity();
        editClubProfileActivity.contactPhoneNumber.setText("123");
        boolean result = editClubProfileActivity.checkCredentials();
        // Assert that the result is false (invalid phone number)
        assertFalse(result);
    }

}