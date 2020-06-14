package com.app.group15;

import java.sql.Connection;

import com.app.group15.dao.UserPasswordHistoryDao;
import com.app.group15.model.UserPasswordHistory;
import com.app.group15.persistence.DatabaseManager;
import com.app.group15.persistence.UserRole;



public class Test {

	public static void main(String[] args) {


		UserPasswordHistoryDao dao=new UserPasswordHistoryDao();
		
		System.out.println(dao.getPasswordHistory(1).size() +"---------");
		
		UserPasswordHistory his=new UserPasswordHistory();
		his.setHistoryPassword("xyd");
		his.setUserId(1);
		dao.save(his);
	}

}
