package com.app.group15.services;

import javax.servlet.http.HttpServletRequest;

import com.app.group15.model.User;

public interface ISessionService {
	public boolean isUserSignedIn(HttpServletRequest request);
	public  User getSessionUser(HttpServletRequest request);
	public  void destroySession(HttpServletRequest request);
	public void setSession(HttpServletRequest request, String name, String value);
	public String getUserHome(HttpServletRequest request);
	
}
