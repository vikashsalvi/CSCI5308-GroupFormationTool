package com.app.group15.passwordPolicy;

import org.junit.jupiter.api.Test;

import com.app.group15.passwordPolicyManagement.PasswordPolicyMaxLength;

import static org.junit.jupiter.api.Assertions.*;

class PasswordPolicyMaxLengthTest {

    @Test
    void isPasswordValid() {
        PasswordPolicyMaxLength passwordPolicyMaxLength = new PasswordPolicyMaxLength();

        String length16 = "AAAAAAAAAAAAAAAA";
        String length17 = "AAAAAAAAAAAAAAAAA";
        String length15 = "AAAAAAAAAAAAAAA";

        assertTrue(passwordPolicyMaxLength.isPasswordValid(length15));
        assertTrue(passwordPolicyMaxLength.isPasswordValid(length16));
        assertFalse(passwordPolicyMaxLength.isPasswordValid(length17));

    }
}