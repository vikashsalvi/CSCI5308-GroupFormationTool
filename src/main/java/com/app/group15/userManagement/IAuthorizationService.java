package com.app.group15.userManagement;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public interface IAuthorizationService {
	public void setAllowedRoles(String[] args);
	public Set<String> getAllowedRoles();
	public boolean isAuthorized(HttpServletRequest request);

}
