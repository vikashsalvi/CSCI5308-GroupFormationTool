package com.app.group15.UserManagement.SessionManagement;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public interface IAuthorizationService {
    Set<String> getAllowedRoles();

    void setAllowedRoles(String[] args);

    boolean isAuthorized(HttpServletRequest request);

}
