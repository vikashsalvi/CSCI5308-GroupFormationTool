package com.app.group15.PasswordPolicyManagement;


import com.app.group15.Config.AppConfig;
import com.app.group15.ExceptionHandler.AwsSecretsManagerException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyServiceInjector {

    private PasswordPolicyDao passwordPolicyDao;
    private PasswordPolicyService passwordPolicyService;
    IPasswordPolicyAbstractFactory passwordPolicyAbstractFactory = AppConfig.getInstance().getPasswordPolicyAbstractFactory();


    public PasswordPolicyServiceInjector() throws SQLException, AwsSecretsManagerException {

        passwordPolicyDao = (PasswordPolicyDao) passwordPolicyAbstractFactory.getPasswordPolicyDao();
        List<PasswordPolicy> activePasswordPolicy = passwordPolicyDao.getActivePasswordPolicy();
        passwordPolicyService = (PasswordPolicyService) passwordPolicyAbstractFactory.getPasswordPolicyService();
        List<IPasswordPolicyValidator> activePolicyList = new ArrayList();

        activePasswordPolicy.forEach(policy -> {
            switch (policy.getPolicyId()) {
                case 1:
                    activePolicyList.add(passwordPolicyAbstractFactory.getPasswordPolicyMinLength());
                    break;
                case 2:
                    activePolicyList.add(passwordPolicyAbstractFactory.getPasswordPolicyMaxLength());
                    break;
                case 3:
                    activePolicyList.add(passwordPolicyAbstractFactory.getPasswordPolicyMinUpperCase());
                    break;
                case 4:
                    activePolicyList.add(passwordPolicyAbstractFactory.getPasswordPolicyMinLowerCase());
                    break;
                case 5:
                    activePolicyList.add(passwordPolicyAbstractFactory.getPasswordPolicyMinSpecialChar());
                    break;
                case 6:
                    activePolicyList.add(passwordPolicyAbstractFactory.getPasswordPolicyCharNotAllowed());
                    break;
                case 7:
                    activePolicyList.add(passwordPolicyAbstractFactory.getPasswordPolicyHistoryConstrain());
                    break;
            }
        });
        passwordPolicyService.injectPasswordPolicy(activePolicyList);
    }

    public PasswordPolicyService getPasswordPolicyService() {
        return passwordPolicyService;
    }
}
