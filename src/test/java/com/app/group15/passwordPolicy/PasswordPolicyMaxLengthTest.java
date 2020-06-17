package com.app.group15.passwordPolicy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordPolicyMaxLengthTest {

    @Test
    void isPasswordValid() {
        PasswordPolicyMaxLengthMock passwordPolicyMaxLength = new PasswordPolicyMaxLengthMock();

        String length16 = "AAAAAAAAAAAAAAAA";
        String length17 = "AAAAAAAAAAAAAAAAA";
        String length15 = "AAAAAAAAAAAAAAA";

        assertTrue(passwordPolicyMaxLength.isPasswordValid(length15));
        assertTrue(passwordPolicyMaxLength.isPasswordValid(length16));
        assertFalse(passwordPolicyMaxLength.isPasswordValid(length17));

    }


}