package com.app.group15.passwordPolicyManagement;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import com.app.group15.persistence.InvokeStoredProcedure;
import com.app.group15.utility.GroupFormationToolLogger;

public class UserPasswordHistoryDao extends UserPasswordHistoryAbstractDao{
	
	@Override
	public List getPasswordHistory(int userId) {
		InvokeStoredProcedure invokeStoredProcedure = null;
		try {
            invokeStoredProcedure = new InvokeStoredProcedure("spGetUserPasswordHistory(?)");
            invokeStoredProcedure.setParameter(1, userId);
            ResultSet results = invokeStoredProcedure.executeWithResults();
            List<UserPasswordHistory> passwordHistoryList = new ArrayList<>();
            if (results != null) {
                while (results.next()) {
                	UserPasswordHistory passwordHistory=new UserPasswordHistory();
                	passwordHistory.setHistoryPassword(results.getString("password"));
                	passwordHistory.setUserId(results.getInt("user_id"));
                	passwordHistoryList.add(passwordHistory);
                	
                }
            }
            return passwordHistoryList;
		}
		catch (Exception e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        } finally {
            invokeStoredProcedure.closeConnection();
        }
		return null;
		
	}
	
	@Override
	public void savePasswordHistory(UserPasswordHistory passwordHistory) {
		InvokeStoredProcedure invokeStoredProcedure = null;
		try {
			invokeStoredProcedure = new InvokeStoredProcedure("spAddUserPasswordHistory(?,?)");
			invokeStoredProcedure.setParameter(1, passwordHistory.getUserId());
			invokeStoredProcedure.setParameter(2, passwordHistory.getHistoryPassword());
			invokeStoredProcedure.execute();
		}
		catch (Exception e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        } finally {
            invokeStoredProcedure.closeConnection();
        }
		
		
	}
}
