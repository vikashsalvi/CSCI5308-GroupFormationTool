package com.app.group15.passwordPolicy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordPolicyMinLengthTest {

    @Test
    void isPasswordValid() {
        PasswordPolicyMinLength passwordPolicyMinLength = new PasswordPolicyMinLength();

        String length7 = "AAAAAAA";
        String length8 = "AAAAAAAA";
        String length9 = "AAAAAAAAA";

        assertFalse(passwordPolicyMinLength.isPasswordValid(length7));
        assertTrue(passwordPolicyMinLength.isPasswordValid(length8));
        assertTrue(passwordPolicyMinLength.isPasswordValid(length9));
    }
}