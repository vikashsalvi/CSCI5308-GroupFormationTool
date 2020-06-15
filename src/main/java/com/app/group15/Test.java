//package com.app.group15;
//
//import java.sql.Connection;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import com.app.group15.config.ServiceConfig;
//import com.app.group15.dao.UserPasswordHistoryDao;
//import com.app.group15.model.PasswordPolicyValidationResult;
//import com.app.group15.model.UserPasswordHistory;
//import com.app.group15.persistence.DatabaseManager;
//import com.app.group15.persistence.UserRole;
//import com.app.group15.services.IPasswordPolicyService;
//import com.app.group15.services.PasswordPolicyService;
//
//
//
//public class Test {
//
//	public static void main(String[] args) {
//
//
//		UserPasswordHistoryDao dao=new UserPasswordHistoryDao();
//		
//		System.out.println(dao.getPasswordHistory(1).size() +"---------");
//		
//		IPasswordPolicyService service=ServiceConfig.getInstance().getPasswordPolicy();
//		PasswordPolicyValidationResult res=service.validatePassword("UUUUUUUUUl$",1);
//		System.out.println(res.getResult() +" "+res.getFailedPolicyList().get(0));
//		
//	}
//
//}
