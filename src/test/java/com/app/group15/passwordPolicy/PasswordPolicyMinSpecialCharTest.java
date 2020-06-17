package com.app.group15.passwordPolicy;

import org.junit.jupiter.api.Test;

import com.app.group15.passwordPolicyManagement.PasswordPolicyMinSpecialChar;

import static org.junit.jupiter.api.Assertions.*;

class PasswordPolicyMinSpecialCharTest {

    @Test
    void isPasswordValid() {
        PasswordPolicyMinSpecialCharMock passwordPolicyMinSpecialChar = new PasswordPolicyMinSpecialCharMock();

        String specialChar0 = "ababab";
        String specialChar1 = "@ababab";
        String specialChar2 = "@#ababab";
        String specialChar3 = "@#%ababab";

        assertFalse(passwordPolicyMinSpecialChar.isPasswordValid(specialChar0));
        assertFalse(passwordPolicyMinSpecialChar.isPasswordValid(specialChar1));
        assertTrue(passwordPolicyMinSpecialChar.isPasswordValid(specialChar2));
        assertTrue(passwordPolicyMinSpecialChar.isPasswordValid(specialChar3));

    }

}