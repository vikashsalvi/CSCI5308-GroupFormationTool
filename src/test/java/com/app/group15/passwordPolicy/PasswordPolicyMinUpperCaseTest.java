package com.app.group15.passwordPolicy;

import org.junit.jupiter.api.Test;

import com.app.group15.passwordPolicyManagement.PasswordPolicyMinUpperCase;

import static org.junit.jupiter.api.Assertions.*;

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