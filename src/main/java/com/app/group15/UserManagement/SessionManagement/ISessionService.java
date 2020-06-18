package com.app.group15.UserManagement.SessionManagement;

import com.app.group15.UserManagement.User;

import javax.servlet.http.HttpServletRequest;

public interface ISessionService {
    boolean isUserSignedIn(HttpServletRequest request);

    User getSessionUser(HttpServletRequest request);

    void destroySession(HttpServletRequest request);

    void setSession(HttpServletRequest request, String name, String value);

    String getUserHome(HttpServletRequest request);

}
