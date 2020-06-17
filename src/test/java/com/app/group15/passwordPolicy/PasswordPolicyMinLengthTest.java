package com.app.group15.passwordPolicy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordPolicyMinLengthTest {

    @Test
    void isPasswordValid() {
        PasswordPolicyMinLengthMock passwordPolicyMinLength = new PasswordPolicyMinLengthMock();

        String length7 = "AAAAAAA";
        String length8 = "AAAAAAAA";
        String length9 = "AAAAAAAAA";

        assertFalse(passwordPolicyMinLength.isPasswordValid(length7));
        assertTrue(passwordPolicyMinLength.isPasswordValid(length8));
        assertTrue(passwordPolicyMinLength.isPasswordValid(length9));
    }
}