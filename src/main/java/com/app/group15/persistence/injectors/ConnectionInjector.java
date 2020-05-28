package com.app.group15.persistence.injectors;

import java.sql.Connection;

public interface ConnectionInjector {
	
	public void injectConnection(Connection connection);

}
