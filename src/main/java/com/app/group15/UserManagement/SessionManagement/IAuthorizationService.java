package com.app.group15.UserManagement.SessionManagement;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public interface IAuthorizationService {
	public void setAllowedRoles(String[] args);
	public Set<String> getAllowedRoles();
	public boolean isAuthorized(HttpServletRequest request);

}
