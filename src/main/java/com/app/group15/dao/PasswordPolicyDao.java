package com.app.group15.dao;

import com.app.group15.model.PasswordPolicy;
import com.app.group15.persistence.InvokeStoredProcedure;
import com.app.group15.utility.GroupFormationToolLogger;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class PasswordPolicyDao extends PasswordPolicyAbstractDao {


    @Override
    public List<PasswordPolicy> getAll() {

        InvokeStoredProcedure invokeStoredProcedure = null;
        try {
            invokeStoredProcedure = new InvokeStoredProcedure("spFindAllPasswordPolicy()");
            ResultSet results = invokeStoredProcedure.executeWithResults();
            List<PasswordPolicy> listOfPolicy = new ArrayList<>();
            if (results != null) {
                while (results.next()) {
                    PasswordPolicy policy = new PasswordPolicy();
                    policy.setPolicyId(results.getInt("id"));
                    policy.setPolicyName(results.getString("policy_name"));
                    policy.setPolicyDescription(results.getString("policy_desc"));
                    policy.setIs_active(results.getBoolean("is_active"));
                    policy.setPolicyValue(results.getString("policy_value"));
                    listOfPolicy.add(policy);
                }
            }
            return listOfPolicy;
        } catch (Exception e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<PasswordPolicy> getActivePasswordPolicy() {

        InvokeStoredProcedure invokeStoredProcedure = null;
        try {
            invokeStoredProcedure = new InvokeStoredProcedure("spFindActivePasswordPolicy()");
            ResultSet results = invokeStoredProcedure.executeWithResults();
            List<PasswordPolicy> listOfPolicy = new ArrayList<>();
            if (results != null) {
                while (results.next()) {
                    PasswordPolicy policy = new PasswordPolicy();
                    policy.setPolicyId(results.getInt("id"));
                    policy.setPolicyName(results.getString("policy_name"));
                    policy.setPolicyDescription(results.getString("policy_desc"));
                    policy.setIs_active(results.getBoolean("is_active"));
                    policy.setPolicyValue(results.getString("policy_value"));
                    listOfPolicy.add(policy);
                }
            }
            return listOfPolicy;
        } catch (Exception e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        } finally {
            invokeStoredProcedure.closeConnection();
        }
        return null;
    }
}
