package com.app.group15.passwordPolicy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordPolicyMinLowerCaseTest {



    @Test
    void isPasswordValid() {
        PasswordPolicyMinLowerCase passwordPolicyMinLowerCase = new PasswordPolicyMinLowerCase();

        String lowercase3 = "aaaAAA@";
        String lowercase2 = "aaAAA@";
        String lowercase1 = "aAAA@";
        String lowercase0 = "AAA@";

        assertTrue(passwordPolicyMinLowerCase.isPasswordValid(lowercase3));
        assertTrue(passwordPolicyMinLowerCase.isPasswordValid(lowercase2));
        assertFalse(passwordPolicyMinLowerCase.isPasswordValid(lowercase1));
        assertFalse(passwordPolicyMinLowerCase.isPasswordValid(lowercase0));

    }
    

}