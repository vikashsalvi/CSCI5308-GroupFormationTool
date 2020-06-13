package com.app.group15.services;

import com.app.group15.injectors.service.PasswordPolicyServiceInjector;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PasswordPolicyServiceTest {

    @Test
    public void validatePasswordTest() {
        PasswordPolicyService passwordPolicyService = new PasswordPolicyServiceInjector().getPasswordPolicyService();
        assertNotNull(passwordPolicyService);
    }
}
