package com.app.group15.PasswordPolicyManagement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordPolicyMinUpperCaseTest {

    @Test
    void isPasswordValid() {

        PasswordPolicyMinUpperCaseMock passwordPolicyMinUpperCase = new PasswordPolicyMinUpperCaseMock();

        String uppercase0 = "aa@";
        String uppercase1 = "Aaa@";
        String uppercase2 = "AAaa@";
        String uppercase3 = "AAAaa@";

        assertFalse(passwordPolicyMinUpperCase.isPasswordValid(uppercase0));
        assertFalse(passwordPolicyMinUpperCase.isPasswordValid(uppercase1));
        assertTrue(passwordPolicyMinUpperCase.isPasswordValid(uppercase2));
        assertTrue(passwordPolicyMinUpperCase.isPasswordValid(uppercase3));


    }
}
