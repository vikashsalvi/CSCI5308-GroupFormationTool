package com.app.group15.passwordPolicy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordPolicyCharNotAllowedTest {

    @Test
    void isPasswordValidTest() {
        PasswordPolicyCharNotAllowed passwordPolicyCharNotAllowed = new PasswordPolicyCharNotAllowed();

        String noBannedChar0 = "abACW";
        String bannedChar1 = "$abACW";
        String noBannedChar1 = "(abACW";

        assertFalse(passwordPolicyCharNotAllowed.isPasswordValid(bannedChar1));
        assertTrue(passwordPolicyCharNotAllowed.isPasswordValid(noBannedChar1));
        assertTrue(passwordPolicyCharNotAllowed.isPasswordValid(noBannedChar0));
    }
}